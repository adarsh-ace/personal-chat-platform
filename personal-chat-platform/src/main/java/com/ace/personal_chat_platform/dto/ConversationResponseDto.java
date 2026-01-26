package com.ace.personal_chat_platform.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversationResponseDto {
    private Long conversationId;
    private Long otherUserId;
}
