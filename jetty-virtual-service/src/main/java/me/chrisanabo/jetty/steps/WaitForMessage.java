package me.chrisanabo.jetty.steps;

import me.chrisanabo.jetty.service.MessageContainer;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * will do the polling to the webhook http service
 * will do a total retry of 5 at 10 seconds interval
 *
 */
public class WaitForMessage implements Callable<String> {

    private String msgId;
    private MessageContainer messageContainer;

    public WaitForMessage(String msgId, MessageContainer messageContainer){
        this.msgId = msgId;
        this.messageContainer = messageContainer;
    }

    @Override
    public String call() throws Exception {

        return null;
    }

    public static void main(String[] args) {

    }

}



