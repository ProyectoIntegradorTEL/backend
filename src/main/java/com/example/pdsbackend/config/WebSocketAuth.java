package com.example.pdsbackend.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.pdsbackend.service.JwtUserDetailsService;

@Component
public class WebSocketAuth implements ChannelInterceptor {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel)  {
     
        System.out.println("-----------------On auth WS... -------------------");



        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        System.out.println("Mensajde capturado: " + message);

        List<String> authorizations = accessor.getNativeHeader("Authorization");

        if (authorizations == null && authorizations.isEmpty()) {
            
            throw new SecurityException();

        }

        String token = authorizations.get(0).substring(7);

        try {

                
    
                String username = jwtTokenUtil.getUsernameFromToken(token);
    
                System.out.println("Username on interceptor: " + username);

                UserDetails userDetails = applicationContext.getBean(JwtUserDetailsService.class).loadUserByUsername(username);

                System.out.println("After user details");

                if(!jwtTokenUtil.validateToken(token, userDetails)) {
                    throw new SecurityException("Invalid token");
                }

                accessor.setUser(new UsernamePasswordAuthenticationToken(username,null, userDetails.getAuthorities()));
            
        } catch (Exception e) {
            e.printStackTrace();    
            throw new SecurityException("Error while authenticating: " + e.getMessage(), e );

        }

        return message;
    }



}
