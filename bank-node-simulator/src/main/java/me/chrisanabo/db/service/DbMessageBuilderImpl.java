package me.chrisanabo.db.service;

import me.chrisanabo.db.dao.TestInputDao;
import me.chrisanabo.db.dao.TestInputDaoImpl;
import me.chrisanabo.db.model.TestInput;
import me.chrisanabo.db.param.AmDecryptionParam;
import me.chrisanabo.db.param.IParam;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class DbMessageBuilderImpl implements MessageBuilder{

    private String payload;
    private AmDecryptionParam amDecryptionParam;

    private TestInputDao testInputDao;

    public DbMessageBuilderImpl() throws SQLException {
        testInputDao = new TestInputDaoImpl();
    }


    @Override
    public MessageBuilder load(IParam iParam) {
        this.amDecryptionParam = (AmDecryptionParam)iParam;

        try {

            TestInput testInput = testInputDao.getTestInput(amDecryptionParam.getxMsgId());

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return this;
    }

    @Override
    public MessageBuilder transform() {
        // use jsonPath
        return this;
    }

    @Override
    public int send(CloseableHttpClient client, HttpPost httpPost) throws IOException {
        //httpPost.setHeader();

        httpPost.setEntity(new StringEntity(this.payload));
        CloseableHttpResponse response = client.execute(httpPost);
        client.close();
        return response.getStatusLine().getStatusCode();
    }


}
