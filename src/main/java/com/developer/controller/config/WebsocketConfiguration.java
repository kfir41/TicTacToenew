package com.developer.controller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket
@EnableJpaRepositories(basePackages="com.developer.model")
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints (StompEndpointRegistry registry) {
        registry.addEndpoint ("/gameplay"). withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app").enableSimpleBroker("/topic");
    }

}