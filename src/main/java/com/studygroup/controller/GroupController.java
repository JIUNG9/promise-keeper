package com.studygroup.controller;

import com.studygroup.dto.CreateGroupForm;
import com.studygroup.dto.GroupDto;
import com.studygroup.dto.UpdateGroupInfoForm;
import com.studygroup.entity.StudyGroup;
import com.studygroup.enums.MainCategory;
import com.studygroup.exception.ApiError;
import com.studygroup.service.CheckDuplicationService;
import com.studygroup.service.group.*;
import com.studygroup.service.groupmember.ApplyTheGroupService;
import com.studygroup.service.groupmember.RetrieveTheNumberOfGroupMemberService;
import com.studygroup.service.user.RetrieveMemberByIdService;
import com.studygroup.util.GroupToDto;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.constant.GroupAdminIntro;
import com.studygroup.util.constant.ObjectToLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
public class GroupController {
    private final UpdateGroupIntroService updateGroupIntroService;
    private final RetrieveTheNumberOfGroupMemberService retrieveTheNumberOfGroupMemberService;
    private final RetrieveAllGroupsService retrieveAllGroupsService;
    private final DeleteGroupService deleteGroupService;
    private final RetrieveMemberByIdService retrieveMemberByIdService;
    private final RetrieveGroupsBySubjectService retrieveGroupsBySubjectService;
    private final FindGroupService findGroupService;
    private final RetrieveGroupsByCategoryService groupsByCategoryService;
    private final GroupUpdateNameService updateGroupNameService;
    private final CreateGroupService createGroupService;
    private final CheckDuplicationService checkGroupNameDuplicationService;
    private final ApplyTheGroupService initialGroupMemberAsAdminService;
    private static final Logger logger = LoggerFactory
            .getLogger(GroupController.class);

    public GroupController(UpdateGroupIntroService updateGroupIntroService, RetrieveTheNumberOfGroupMemberService retrieveTheNumberOfGroupMemberService,
                           RetrieveAllGroupsService retrieveAllGroupsService,
                           DeleteGroupService deleteGroupService,
                           RetrieveMemberByIdService retrieveMemberByIdService,
                           RetrieveGroupsBySubjectService retrieveGroupsBySubjectService,
                           FindGroupService findGroupService,
                           @Qualifier("RetrieveGroupsByCategoryServiceImpl") RetrieveGroupsByCategoryService groupsByCategoryService,
                           GroupUpdateNameService updateGroupNameService,
                           CreateGroupService createGroupService,
                           @Qualifier("CheckGroupNameDuplicationService") CheckDuplicationService checkGroupNameDuplicationService,
                           @Qualifier("InitialGroupMemberAsAdminService") ApplyTheGroupService initialGroupMemberAsAdminService) {
        this.updateGroupIntroService = updateGroupIntroService;
        this.retrieveTheNumberOfGroupMemberService = retrieveTheNumberOfGroupMemberService;
        this.retrieveAllGroupsService = retrieveAllGroupsService;
        this.deleteGroupService = deleteGroupService;
        this.retrieveMemberByIdService = retrieveMemberByIdService;
        this.retrieveGroupsBySubjectService = retrieveGroupsBySubjectService;
        this.findGroupService = findGroupService;
        this.groupsByCategoryService = groupsByCategoryService;
        this.updateGroupNameService = updateGroupNameService;
        this.createGroupService = createGroupService;
        this.checkGroupNameDuplicationService = checkGroupNameDuplicationService;
        this.initialGroupMemberAsAdminService = initialGroupMemberAsAdminService;
    }






    @PostMapping("/api/groups")
    public ResponseEntity<Object> createGroup(@Valid @RequestBody CreateGroupForm createGroupForm)
    {
        //JwtUtil parse the token and set the Authentication principle as memberId
        Object memberId = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info(memberId.toString());

        if(!checkGroupNameDuplicationService.isDuplicated(createGroupForm.getName())){
            StudyGroup studyGroup = createGroupService.create(createGroupForm);
            initialGroupMemberAsAdminService.apply(
                    retrieveMemberByIdService.getMember(ObjectToLong.convert(memberId)),
                            studyGroup, createGroupForm.getNickName(),
                            GroupAdminIntro.INTRO_AS_ADMIN);

            return ResponseEntity.
                    status(HttpStatus.CREATED).
                    body("Successfully create the group!");
        }
         return ApiError.buildApiError(
                 ErrorCode.GROUP_NAME_IS_DUPLICATED,
                 HttpStatus.BAD_REQUEST);


    }


    @PutMapping("/api/groups/{groupName}/admins/name/{newName}")
    public ResponseEntity<Object> updateGroupName(
                                                         @PathVariable String groupName,
                                                         @PathVariable String newName) {

        StudyGroup studyGroup = findGroupService.getGroup(groupName);
        if(!checkGroupNameDuplicationService.isDuplicated(newName)){
            updateGroupNameService.update(studyGroup, newName);
            return ResponseEntity.
                    status(HttpStatus.OK).
                    body("update group name is succeeded ");

        }
        return ApiError.
                buildApiError(
                        ErrorCode.GROUP_NAME_IS_DUPLICATED,
                        HttpStatus.BAD_REQUEST);
    }




    @PutMapping("/api/groups/{groupName}/admins/intro")
    public ResponseEntity<Object> updateGroupInfo(
                                                  @PathVariable String groupName,
                                                  @Valid @RequestBody UpdateGroupInfoForm updateGroupInfoForm
                                                  ) {
        StudyGroup studyGroup = findGroupService.getGroup(groupName);
        updateGroupIntroService.update(studyGroup, updateGroupInfoForm.getIntro());

            return ResponseEntity.
                    status(HttpStatus.OK).
                    body("updating group intro is succeeded");

    }


    @GetMapping("/api/groups")
    public ResponseEntity<Object> findGroup(
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) MainCategory mainCategory,
                                            @RequestParam(required = false) String subject) {

        List<GroupDto> groupDtoList = new ArrayList<>();
        if (name == null && mainCategory == null && subject == null) {
            //전체 검색
            groupDtoList = GroupToDto.convert(retrieveAllGroupsService.getAll());
        } else if (name != null) {
            //이름 검색
            groupDtoList.add(GroupToDto.convert(findGroupService.getGroup(name)));
        } else if(mainCategory != null && subject == null){
            //카테고리 별 검색
            groupDtoList = GroupToDto.convert(groupsByCategoryService.get(mainCategory));
        }
        else{
            //주제 검색
            groupDtoList = GroupToDto.convert(retrieveGroupsBySubjectService.find(subject));
        }

        if(groupDtoList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(groupDtoList);
    }

    @DeleteMapping("/api/groups/{groupName}/admins")
    public ResponseEntity<Object> deleteGroup (@PathVariable String groupName) {

        StudyGroup studyGroup = findGroupService.getGroup(groupName);
        int groupMemberNumber = retrieveTheNumberOfGroupMemberService.getTheNumberOfGroupMember(studyGroup);
        if(groupMemberNumber<=1) {
            deleteGroupService.delete(studyGroup);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
            return ApiError.buildApiError(ErrorCode.CAN_NOT_DELETE_GROUP, HttpStatus.BAD_REQUEST);


    }
}
