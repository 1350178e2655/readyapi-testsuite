package me.chrisanabo.db.service;

import me.chrisanabo.db.dao.TestInputDao;
import me.chrisanabo.db.dao.TestInputDaoImpl;
import me.chrisanabo.db.param.AmDecryptionParam;
import me.chrisanabo.db.util.JsonUtils;
import me.chrisanabo.model.MessagePayload;
import me.chrisanabo.security.NimbusJose;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class GenerateResponse {

    private ExecutorService executor;

    private TestInputDao testInputDao;

    private Map<String, MessageBuilder> messageBuilderMap = new HashMap<>();

    private String msgPayload;
    private CloseableHttpClient httpClient;
    private HttpPost httpPost;


    public GenerateResponse(String id) throws SQLException {
        System.out.println(id);
        testInputDao = new TestInputDaoImpl();
        executor = Executors.newFixedThreadPool(50);
        httpClient = HttpClientBuilder.create().build();
        httpPost = new HttpPost("http://localhost:8080/register");
    }


    /**
     *
     * @param jsonMessage
     * @return https response status after sending to access manager
     */
    public void process(String jsonMessage,Map<String,String> headerValue) throws Exception {
        NimbusJose nimbusJose = new NimbusJose();

        AmDecryptionParam iParam = new AmDecryptionParam();
            iParam.setxOutBoundUri(headerValue.get("X-OUTBOUND-URI"));
            iParam.setxMsgId(headerValue.get("X-Msg-Id"));
            iParam.setxMsgType(headerValue.get("X-Msg-Type"));
            iParam.setxSystemId(headerValue.get("X-System-Id"));
            iParam.setxReceiverSystemId(headerValue.get("X-Receiver-System-Id"));

            jsonMessage = nimbusJose.readyApiDecryptMsgFromPartior(jsonMessage,iParam.getxSystemId());

            MessagePayload payload = JsonUtils.deserialize( jsonMessage, MessagePayload.class);

            iParam.setPayload(payload);



        executor.submit(  new MessageSender(messageBuilderMap.get(iParam.getxMsgId()),
                httpClient, httpPost, iParam) );
    }



}
