package me.chrisanabo.jetty.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageContainer {

    private Map<String, String> msgContainer = new ConcurrentHashMap<>();

    public String getMessagePayload(String msgId){
        String msgPayload = msgContainer.get(msgId);
        // can implement a retry here
        return msgPayload;
    }

    public void storePayload(String msgId, String msgPayload){
        msgContainer.putIfAbsent(msgId, msgPayload);
    }

}
