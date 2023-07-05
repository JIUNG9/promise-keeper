package com.studygroup.controller;

import com.studygroup.dto.CreateGroupMeetingForm;
import com.studygroup.dto.UpdateGroupMeetingTimeForm;
import com.studygroup.domain.GroupMeeting;
import com.studygroup.domain.StudyGroup;
import com.studygroup.exception.ApiError;
import com.studygroup.service.common.CheckDuplicationService;
import com.studygroup.service.group.FindGroupService;
import com.studygroup.service.group.meeting.CreateGroupMeetingService;
import com.studygroup.service.group.meeting.DeleteGroupMeetingService;
import com.studygroup.service.group.meeting.RetrieveGroupMeetingByStudyGroupAndSubject;
import com.studygroup.service.group.meeting.UpdateDayOfWeekService;
import com.studygroup.service.group.meeting.UpdateGroupMeetingTime;
import com.studygroup.util.error.ErrorCode;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupMeetingController {

  private static final Logger logger = LoggerFactory
      .getLogger(GroupMeetingController.class);
  private final DeleteGroupMeetingService deleteGroupMeetingService;
  private final RetrieveGroupMeetingByStudyGroupAndSubject retrieveGroupMeetingByStudyGroupAndSubject;
  private final CreateGroupMeetingService createGroupMeetingService;
  private final CheckDuplicationService checkGroupMeetingSubjectDuplicationService;
  private final UpdateGroupMeetingTime updateMeetingTimeGroupMeeting;
  private final UpdateDayOfWeekService addDayOfWeekService;
  private final UpdateDayOfWeekService deleteDayOfWeekService;
  private final FindGroupService findGroupService;


  public GroupMeetingController(DeleteGroupMeetingService deleteGroupMeetingService,
      @Qualifier("RetrieveGroupMeetingByStudyGroupAndSubjectImpl") RetrieveGroupMeetingByStudyGroupAndSubject retrieveGroupMeetingByMeetingSubjectService,
      @Qualifier("CreateGroupMeetingServiceImpl") CreateGroupMeetingService createGroupMeetingService,
      @Qualifier("CheckGroupMeetingSubjectDuplicationService") CheckDuplicationService checkGroupMeetingSubjectDuplicationService,
      @Qualifier("UpdateMeetingTimeGroupMeeting") UpdateGroupMeetingTime updateMeetingTimeGroupMeeting,
      @Qualifier("AddDayOfWeekService") UpdateDayOfWeekService addDayOfWeekService,
      @Qualifier("DeleteDayOfWeekService") UpdateDayOfWeekService deleteDayOfWeekService,
      FindGroupService findGroupService) {
    this.deleteGroupMeetingService = deleteGroupMeetingService;
    this.retrieveGroupMeetingByStudyGroupAndSubject = retrieveGroupMeetingByMeetingSubjectService;
    this.createGroupMeetingService = createGroupMeetingService;
    this.checkGroupMeetingSubjectDuplicationService = checkGroupMeetingSubjectDuplicationService;
    this.updateMeetingTimeGroupMeeting = updateMeetingTimeGroupMeeting;
    this.addDayOfWeekService = addDayOfWeekService;
    this.deleteDayOfWeekService = deleteDayOfWeekService;
    this.findGroupService = findGroupService;
  }

  @PostMapping("/api/groups/{groupName}/admins/meeting")
  public ResponseEntity<Object> createGroupMeeting(@PathVariable String groupName,
      @Valid @RequestBody CreateGroupMeetingForm createGroupMeetingForm) {

    String subject = createGroupMeetingForm.getSubject();
    StudyGroup studyGroup = findGroupService.getGroup(groupName);

    if (!checkGroupMeetingSubjectDuplicationService.isDuplicated(subject)) {
      createGroupMeetingService.create(createGroupMeetingForm, studyGroup);
      return ResponseEntity.
          status(HttpStatus.CREATED).
          body("successfully create group meeting");
    }
    logger.info("subject is duplicated");
    return ApiError.
        buildApiError(
            ErrorCode.GROUP_MEETING_SUBJECT_IS_DUPLICATED,
            HttpStatus.BAD_REQUEST);

  }

  @PutMapping("/api/groups/{groupName}/admins/meeting/subjects/{subject}/time")
  public ResponseEntity<Object> updateGroupMeetingTime(
      @Valid @RequestBody UpdateGroupMeetingTimeForm updateGroupMeetingTimeForm,
      @PathVariable String groupName,
      @PathVariable String subject) {

    StudyGroup studyGroup = findGroupService.getGroup(groupName);
    GroupMeeting groupMeeting = retrieveGroupMeetingByStudyGroupAndSubject.get(studyGroup, subject);
    LocalTime startTime = updateGroupMeetingTimeForm.getMeetingStartTime();
    LocalTime endTime = updateGroupMeetingTimeForm.getMeetingEndTime();

    updateMeetingTimeGroupMeeting.update(groupMeeting, startTime, endTime);

    return ResponseEntity.
        status(HttpStatus.OK).
        body("update meeting time successfully");

  }

  @PutMapping("/api/groups/{groupName}/admins/meeting/subjects/{subject}/day-of-week")
  public ResponseEntity<Object> AddDayOfWeek(@RequestBody List<DayOfWeek> dayOfWeeks,
      @PathVariable String groupName,
      @PathVariable String subject) {

    StudyGroup studyGroup = findGroupService.getGroup(groupName);
    GroupMeeting groupMeeting = retrieveGroupMeetingByStudyGroupAndSubject.get(studyGroup, subject);

    addDayOfWeekService.update(groupMeeting, dayOfWeeks);

    return ResponseEntity.
        status(HttpStatus.OK).
        body("The DayOfWeek is added successfully");

  }

  @DeleteMapping("/api/groups/{groupName}/admins/meeting/subjects/{subject}/day-of-week")
  public ResponseEntity<Object> deleteDayOfWeek(@RequestBody List<DayOfWeek> dayOfWeeks,
      @PathVariable String groupName,
      @PathVariable String subject) {

    StudyGroup studyGroup = findGroupService.getGroup(groupName);
    GroupMeeting groupMeeting = retrieveGroupMeetingByStudyGroupAndSubject.get(studyGroup, subject);
    deleteDayOfWeekService.update(groupMeeting, dayOfWeeks);

    return ResponseEntity.
        status(HttpStatus.OK).
        body("The DayOfWeek is  deleted");

  }

  @DeleteMapping("/api/groups/{groupName}/admins/meeting/subjects/{subject}")
  public ResponseEntity<Object> deleteGroupMeeting(@PathVariable String groupName,
      @PathVariable String subject) {

    StudyGroup studyGroup = findGroupService.getGroup(groupName);
    GroupMeeting groupMeeting = retrieveGroupMeetingByStudyGroupAndSubject.get(studyGroup, subject);

    deleteGroupMeetingService.delete(groupMeeting);

    return ResponseEntity.
        status(HttpStatus.OK).
        body("delete meeting is succeeded");

  }
}
