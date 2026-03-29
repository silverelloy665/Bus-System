package com.busbooking.config;

import com.busbooking.controller.BusLocationWebSocket;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@SuppressWarnings("null")
public class WebSocketConfig implements WebSocketConfigurer {

    private final BusLocationWebSocket busLocationWebSocket;

    public WebSocketConfig(BusLocationWebSocket busLocationWebSocket) {
        this.busLocationWebSocket = busLocationWebSocket;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(busLocationWebSocket, "/ws/bus-location").setAllowedOrigins("*");
    }
}
