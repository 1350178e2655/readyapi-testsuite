package me.chrisanabo.db.service;

import me.chrisanabo.db.param.IParam;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Callable;

public interface MessageBuilder {

    public MessageBuilder load(IParam iParam);

    public MessageBuilder transform();

    public int send(CloseableHttpClient client, HttpPost httpPost) throws IOException;

}
