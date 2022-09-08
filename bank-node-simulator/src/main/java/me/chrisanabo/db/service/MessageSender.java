package me.chrisanabo.db.service;

import me.chrisanabo.db.param.AmDecryptionParam;
import me.chrisanabo.db.param.IParam;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.concurrent.Callable;

public class MessageSender implements Callable<Integer> {



    private MessageBuilder messageBuilder;
    private AmDecryptionParam amDecryptionParam;
    private CloseableHttpClient httpClient;
    private HttpPost httpPost;


    public MessageSender(MessageBuilder messageBuilder, CloseableHttpClient httpClient, HttpPost httpPost, IParam iParam){
        this.messageBuilder = messageBuilder;
         this.httpClient = httpClient;
         this.httpPost = httpPost;
         this.amDecryptionParam = (AmDecryptionParam)iParam;
    }

    @Override
    public Integer call() throws Exception {
        return messageBuilder.load(amDecryptionParam).transform().send(httpClient, httpPost);
    }
}
