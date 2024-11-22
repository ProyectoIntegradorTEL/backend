package com.example.pdsbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    public void registerStompEndpoints(StompEndpointRegistry registry) {

        
        registry.addEndpoint("/ws-connect")
                .setAllowedOriginPatterns("*");
        
    }   
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/dataTopic");
        registry.setApplicationDestinationPrefixes("/app");

    }

    @Autowired
    private WebSocketAuth jwtChannelInterceptor;

    @Autowired
    private WebSocketSubscriptionInterceptor subscriptionInterceptor;

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        
        registration.interceptors(jwtChannelInterceptor);
    }

}
