//package com.studygroup.service.chatroom;
//
//import com.studygroup.util.ChatRoomNameGenerator;
//import com.studygroup.util.RabbitMqNamingGenerator;
//import com.studygroup.util.constant.ErrorCode;
//import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class ChatRoomManager {
//
//    private final AmqpAdmin amqpAdmin;
//    private final DirectExchange directExchange;
//
//    public void createInquiryChatRoom(String groupName, String requestMemberName) {
//        String queueName = RabbitMqNamingGenerator.
//                getInquiryChatQueueAndKeyName(requestMemberName, groupName);
//        String routingKey = RabbitMqNamingGenerator.
//                getInquiryChatQueueAndKeyName(requestMemberName, groupName);
//
//        try {
//            Queue queue = new Queue(queueName);
//            amqpAdmin.declareQueue(queue);
//
//            Binding binding = BindingBuilder.bind(queue).to(directExchange).with(routingKey);
//            amqpAdmin.declareBinding(binding);
//        } catch (AmqpException e) {
//            throw new AmqpException(e.getMessage());
//        }
//    }
//
//    public void createGroupChatRoom(String groupName) {
//            String queueName = RabbitMqNamingGenerator.
//                    getGroupChatQueueAndKeyName(groupName);
//            String routingKey = RabbitMqNamingGenerator.
//                    getGroupChatQueueAndKeyName(groupName);
//        try {
//            Queue queue = new Queue(queueName);
//            amqpAdmin.declareQueue(queue);
//
//            Binding binding = BindingBuilder.bind(queue).to(directExchange).with(routingKey);
//            amqpAdmin.declareBinding(binding);
//        }
//        catch (AmqpException e){
//            throw new AmqpException(e.getMessage());
//        }
//    }
//
//    public void createLiveGroupChatRoom(String groupName) {
//
//        String queueName = RabbitMqNamingGenerator.
//                getLiveGroupChatQueueAndKeyName(groupName);
//        String routingKey = RabbitMqNamingGenerator.
//                getLiveGroupChatQueueAndKeyName(groupName);
//        try {
//            Queue queue = new Queue(queueName);
//            amqpAdmin.declareQueue(queue);
//
//            Binding binding = BindingBuilder.bind(queue).to(directExchange).with(routingKey);
//            amqpAdmin.declareBinding(binding);
//        } catch (AmqpException e) {
//            throw new AmqpException(e.getMessage());
//        }
//
//    }
//
//    public void deleteLiveGroupChatRoom(String groupName) {
//        String queueName = RabbitMqNamingGenerator.
//                getLiveGroupChatQueueAndKeyName(groupName);
//        try {
//            amqpAdmin.deleteQueue(queueName);
//        }
//        catch (AmqpException e){
//            throw new AmqpException(e.getMessage());
//        }
//    }
//
//    public void deleteGroupChatRoom(String groupName) {
//
//        String queueName = RabbitMqNamingGenerator.
//                getGroupChatQueueAndKeyName(groupName);
//        try {
//            amqpAdmin.deleteQueue(queueName);
//        }
//         catch(AmqpException e){
//            throw new AmqpException(e.getMessage());
//        }
//    }
//
//    public void deleteInquiryChatRoom(String requestMemberName, String groupName) {
//        String queueName = RabbitMqNamingGenerator.
//                getInquiryChatQueueAndKeyName(requestMemberName, groupName);
//        amqpAdmin.deleteQueue(queueName);
//    }
//}