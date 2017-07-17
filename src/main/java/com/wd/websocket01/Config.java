package com.wd.websocket01;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by wd on 2017/7/17.
 */
@EnableWebSocket
@Configuration
@EnableAutoConfiguration
public class Config implements WebSocketConfigurer {


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(chatHandler(),"/ws/chat")
                .setAllowedOrigins("http://localhost:8088","http://ouapi.com","http://www.blue-zero.com");
    }

    @Bean
    public ChatHandler chatHandler(){
        return new ChatHandler();
    }


}
