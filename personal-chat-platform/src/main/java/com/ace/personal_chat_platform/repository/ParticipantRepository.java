package com.ace.personal_chat_platform.repository;

import com.ace.personal_chat_platform.entity.ConversationParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<ConversationParticipant, Long> {
    List<ConversationParticipant> findByUserId(Long userId);
    boolean existsByConversationIdAndUserId(Long conversationId, Long userId);
}
