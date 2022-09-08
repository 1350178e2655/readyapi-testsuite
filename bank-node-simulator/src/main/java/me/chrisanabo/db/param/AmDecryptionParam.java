package me.chrisanabo.db.param;


import me.chrisanabo.model.MessagePayload;

public class AmDecryptionParam implements IParam{

    private MessagePayload payload;
    private String xOutBoundUri;
    private String xMsgId;
    private String xMsgType;
    private String xSystemId;
    private String xReceiverSystemId;




    public MessagePayload getPayload() {
        return payload;
    }

    public void setPayload(MessagePayload payload) {
        this.payload = payload;
    }

    public String getxOutBoundUri() {
        return xOutBoundUri;
    }

    public void setxOutBoundUri(String xOutBoundUri) {
        this.xOutBoundUri = xOutBoundUri;
    }

    public String getxMsgId() {
        return xMsgId;
    }

    public void setxMsgId(String xMsgId) {
        this.xMsgId = xMsgId;
    }

    public String getxMsgType() {
        return xMsgType;
    }

    public void setxMsgType(String xMsgType) {
        this.xMsgType = xMsgType;
    }

    public String getxSystemId() {
        return xSystemId;
    }

    public void setxSystemId(String xSystemId) {
        this.xSystemId = xSystemId;
    }

    public String getxReceiverSystemId() {
        return xReceiverSystemId;
    }

    public void setxReceiverSystemId(String xReceiverSystemId) {
        this.xReceiverSystemId = xReceiverSystemId;
    }



}
