package com.studygroup.service.chat;

import com.studygroup.dto.MessageDto;
import org.springframework.stereotype.Service;

@Service
public interface AnnounceMessageService {

  MessageDto getMessage(LiveSessionUserStatusForm liveSessionUserStatusForm);
}

