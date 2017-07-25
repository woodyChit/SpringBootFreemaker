package com.wd.websocket01;

import com.wd.Constants;
import com.wd.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wd on 2017/7/17.
 */
public class ChatHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(ChatHandler.class);
    private Map<String,WebSocketSession> userSessions = new ConcurrentHashMap<>();
    /**
     * 建立连接
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        String sessionId = getSessionId(session);
        userSessions.put(sessionId, session);
        logger.info("建立连接 {},sessionId = {}",session.getId(),sessionId);

        //session.close(CloseStatus.BAD_DATA.withReason("not supported")); 调用session.close 可以由服务器端关闭连接
    }
    private String getSessionId(WebSocketSession session){
        List<String> list = session.getHandshakeHeaders().get("cookie");
        String sessionId = null;
        if(!list.isEmpty()){
            sessionId = CookieUtil.get(Constants.COOKIE_SESSION_ID,list.get(0));
        }
        return sessionId;
    }

    /**
     * 收到信息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        logger.info("收到信息 {}",message.getPayload());
        session.sendMessage(new TextMessage("echo:"+message.getPayload()));
    }

    /**
     * 断开连接
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        logger.info("关闭连接 {}, {}",session.getId(),status.getReason());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);

    }
}
