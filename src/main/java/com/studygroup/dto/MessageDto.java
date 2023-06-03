package com.studygroup.dto;

import com.studygroup.entity.ChatMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MessageDto {
    private String payload;
    private String sender;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Message: \n");
        sb.append("payload: ");
        sb.append(payload);
        sb.append("\n");
        sb.append("sender: ");
        sb.append(sender);
        sb.append("\n");
        return sb.toString();
    }
}
