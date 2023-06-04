package com.studygroup.securirty.filter;

import com.studygroup.entity.StudyGroup;
import com.studygroup.enums.GroupRole;
import com.studygroup.exception.*;
import com.studygroup.securirty.handler.GroupAuthenticationFailureHandler;
import com.studygroup.service.group.FindGroupService;
import com.studygroup.service.groupmember.FindGroupMemberService;
import com.studygroup.util.GetPathVariableInFilter;
import com.studygroup.util.constant.ConvertObjectToJson;
import com.studygroup.util.constant.ErrorCode;
import com.studygroup.util.ObjectToLong;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
public class GroupAuthorizationFilter extends OncePerRequestFilter {

    private final GroupAuthenticationFailureHandler failureHandler;
    private final FindGroupMemberService findGroupMemberService;
    private final FindGroupService findGroupService ;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return !path.contains("/groups") ||
                path.matches("/api/groups") ||
                path.matches("/api/groups/admins") ||
                path.contains("/applications");

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = ObjectToLong.convert(authentication.getPrincipal());
        String groupName = GetPathVariableInFilter.getFirstPathVarAfterGivenURI(request,"groups/");


        try {
            StudyGroup studyGroup = findGroupService.getGroup(groupName);
            GroupRole groupRole = findGroupMemberService.getGroupMember(studyGroup, memberId).getGroupRole();
            //check the group admin authority
            if (path.contains("/admins")) {
                if (!groupRole.equals(GroupRole.GROUP_ADMIN)) {
                    throw new GroupAdminAuthorizationException(
                            ErrorCode.YOU_ARE_NOT_GROUP_ADMIN.getMessage());
                }
            } else {
                //check the group member authority
                if(groupRole.equals(GroupRole.GROUP_DENIED)){
                    throw new GroupMemberIsDeniedException(ErrorCode.YOU_ARE_DENIED_FROM_GROUP_ADMIN.getMessage());
                }
                else if(groupRole.equals(GroupRole.GROUP_PENDING)){
                    throw new GroupMemberIsPendingException(ErrorCode.YOU_ARE_NOW_PENDING_STATUS.getMessage());
                }

                else if (!groupRole.equals(GroupRole.GROUP_USER)
                        && !groupRole.equals(GroupRole.GROUP_ADMIN)) {
                    throw new GroupMemberAuthorizationException(
                            ErrorCode.YOU_ARE_NOT_GROUP_MEMBER.getMessage());
                }
            }
            filterChain.doFilter(request, response);

        }
        catch (CustomIllegalArgumentException e){
            response.getWriter().write(ConvertObjectToJson.convert(
                            ApiError.
                                    buildApiError(
                                            e.getErrorCode(),
                                            HttpStatus.NOT_FOUND).
                                    getBody()));
            response.setStatus(404);
        }
        catch (AuthenticationException e) {
            failureHandler.onAuthenticationFailure(request, response, e);
        }


    }

}
