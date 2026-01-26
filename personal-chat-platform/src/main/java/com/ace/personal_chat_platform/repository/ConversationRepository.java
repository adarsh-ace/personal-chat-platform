package com.ace.personal_chat_platform.repository;

import com.ace.personal_chat_platform.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
