//package com.ace.personal_chat_platform.service;
//
//import com.ace.personal_chat_platform.chat.dto.ConversationResponseDto;
//import com.ace.personal_chat_platform.chat.repository.ConversationRepository;
//import com.ace.personal_chat_platform.chat.repository.ParticipantRepository;
//import com.ace.personal_chat_platform.entity.Conversation;
//import com.ace.personal_chat_platform.entity.ConversationParticipant;
//import com.ace.personal_chat_platform.entity.User;
//import com.ace.personal_chat_platform.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class ConversationService {
//
//    private final ConversationRepository conversationRepository;
//    private final ParticipantRepository participantRepository;
//    private final UserRepository userRepository;
//
//    public ConversationResponseDto createOrGetPrivateChat(Long myId, Long otherUserId) {
//
//        // (Later: we’ll optimize search. For now always create.)
//
//        User me = userRepository.findById(myId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        User other = userRepository.findById(otherUserId)
//                .orElseThrow(() -> new RuntimeException("Other user not found"));
//
//        Conversation conversation = new Conversation();
//        conversation.setPrivate(true);
//        conversation = conversationRepository.save(conversation);
//
//        ConversationParticipant p1 = new ConversationParticipant();
//        p1.setConversation(conversation);
//        p1.setUser(me);
//
//        ConversationParticipant p2 = new ConversationParticipant();
//        p2.setConversation(conversation);
//        p2.setUser(other);
//
//        participantRepository.save(p1);
//        participantRepository.save(p2);
//
//        ConversationResponseDto dto = new ConversationResponseDto();
//        dto.setConversationId(conversation.getId());
//        dto.setOtherUserId(other.getId());
//
//        return dto;
//    }
//}
