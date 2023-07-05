package com.studygroup.securirty.filter;
<<<<<<< HEAD

import com.studygroup.domain.Member;
import com.studygroup.exception.ApiError;
import com.studygroup.service.chatroom.FindMemberIsInChatRoom;
import com.studygroup.service.user.RetrieveMemberByAuthPrinciple;
import com.studygroup.util.extractor.ExtractRoomNameByUri;
import com.studygroup.util.convertor.ObjectToLong;
import com.studygroup.util.convertor.ConvertObjectToJson;
import com.studygroup.util.error.ErrorCode;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
=======
import com.studygroup.entity.Member;
import com.studygroup.exception.ApiError;
import com.studygroup.service.chatroom.FindMemberIsInChatRoom;
import com.studygroup.service.user.RetrieveMemberByAuthPrinciple;
import com.studygroup.util.ExtractRoomNameByUri;
import com.studygroup.util.ObjectToLong;
import com.studygroup.util.constant.ConvertObjectToJson;
import com.studygroup.util.constant.ErrorCode;
>>>>>>> 7c3dbf05b4ae7da5f5b0cb57a6434bd46693aa91
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

<<<<<<< HEAD
@RequiredArgsConstructor
public class ChatRoomMembershipFilter extends OncePerRequestFilter {

  private final RetrieveMemberByAuthPrinciple retrieveMemberByAuthPrinciple;
  private final FindMemberIsInChatRoom findMemberIsInChatRoom;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();
    return !path.contains("/messages");
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws IOException, ServletException {

    String path = request.getRequestURI();
    String roomName = ExtractRoomNameByUri.extractRoomName(request, path);

    Object memberId =
        SecurityContextHolder.
            getContext().
            getAuthentication().
            getPrincipal();

    Member member =
        retrieveMemberByAuthPrinciple.
            getMember(ObjectToLong.convert(memberId));

    if (findMemberIsInChatRoom.find(member, roomName).isEmpty()) {
      response.
          getWriter().
          write(ConvertObjectToJson.convert(
              ApiError.
                  buildApiError(
                      ErrorCode.USER_IS_NOT_IN_CHAT_ROOM,
                      HttpStatus.UNAUTHORIZED).
                  getBody()));
      response.setStatus(401);
    } else {

      filterChain.doFilter(request, response);

    }
  }
=======
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class ChatRoomMembershipFilter extends OncePerRequestFilter {

    private final RetrieveMemberByAuthPrinciple retrieveMemberByAuthPrinciple;
    private final FindMemberIsInChatRoom findMemberIsInChatRoom;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return !path.contains("/messages");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        String path = request.getRequestURI();
        String roomName = ExtractRoomNameByUri.extractRoomName(request,path);

        Object memberId =
                SecurityContextHolder.
                        getContext().
                        getAuthentication().
                        getPrincipal();

        Member member =
                retrieveMemberByAuthPrinciple.
                        getMember(ObjectToLong.convert(memberId));

        if(findMemberIsInChatRoom.find(member,roomName).isEmpty()){
            response.
                    getWriter().
                    write(ConvertObjectToJson.convert(
                    ApiError.
                            buildApiError(
                                    ErrorCode.USER_IS_NOT_IN_CHAT_ROOM,
                                    HttpStatus.UNAUTHORIZED).
                            getBody()));
            response.setStatus(401);
        }
        else {

            filterChain.doFilter(request, response);

        }
    }
>>>>>>> 7c3dbf05b4ae7da5f5b0cb57a6434bd46693aa91

}