package me.chrisanabo.security;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import me.chrisanabo.security.constants.Constants;
import me.chrisanabo.security.service.CryptoService;
import me.chrisanabo.security.utils.KeyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;


public class NimbusJose {

    private CryptoService cryptoService;

    public NimbusJose() {
        this.cryptoService = new CryptoService();
    }


    /**
     *
     * @param serializedPayload
     * @param msgSrcSignerPriKey
     * @param msgDestPubKey
     * @return
     * @throws Exception
     */
    private String signAndEncryptPayload(String serializedPayload, String msgSrcSignerPriKey,
                                         String msgDestPubKey) throws Exception {
        try {

            JWTClaimsSet jwtClaimsSet = JWTClaimsSet.parse(serializedPayload);
            SignedJWT signedJWT       = cryptoService.sign(jwtClaimsSet, msgSrcSignerPriKey);
            String encryptedPayload   = cryptoService.encrypt(signedJWT, msgDestPubKey);
            return encryptedPayload;
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @param payload
     * @param msgSrcVerificationPubKey
     * @return
     * @throws Exception
     */
    private String verifyAndDecryptPayload(String payload, String msgSrcVerificationPubKey, String msgDestDecriptionPrivKey ) throws Exception {
        SignedJWT decryptedJwt = cryptoService.decrypt(payload, msgDestDecriptionPrivKey);
        if (!cryptoService.validateSignature(decryptedJwt, msgSrcVerificationPubKey)) {
            throw new JOSEException("Message wasn't sign by the sender: Validation failed");
        }
        return decryptedJwt.getJWTClaimsSet().toString();
    }

    /**
     *
     * @param payload
     * @param clientId
     * @return
     */
    public String readyApiEncryptMsgToPartior(String payload, String clientId) throws Exception {
       return  this.signAndEncryptPayload(payload,
                Constants.RESOURCE_DIR + "/" + clientId + Constants.CLIENT_KEY_ID_SUFFIX,
               Constants.RESOURCE_DIR + "/" + "PARTIOR_PUBLIC_RSA");
    }

    public String readyApiDecryptMsgFromPartior(String payload, String clientId) throws Exception {

        return verifyAndDecryptPayload(payload,
                Constants.RESOURCE_DIR + "/" + clientId + Constants.CLIENT_KEY_ID_SUFFIX,
                Constants.RESOURCE_DIR + "/" + "PARTIOR_PRIVATE_RSA");

    }



    public static void maintest() throws Exception {

        NimbusJose nimbusJose = new NimbusJose();
        String msg = "{\"issuer\":\"CHASSGSGXXX\",\"issueTime\":\"2022-08-24T02:58:57.461Z\",\"payload\":{\"BusMsg\":{\"AppHdr\":{\"xmlns\":\"head.001.001.01\",\"Fr\":{\"FIId\":{\"FinInstnId\":{\"BICFI\":\"CHASSGSGXXX\"}}},\"To\":{\"FIId\":{\"FinInstnId\":{\"BICFI\":\"CHASSGSGPTR\"}}},\"BizMsgIdr\":\"PTESTBICXXXX9202208240258570000001\",\"MsgDefIdr\":\"camt.050.001.05\",\"CreDt\":\"2021-06-01T12:12:12.123Z\"},\"Document\":{\"LqdtyCdtTrf\":{\"MsgHdr\":{\"MsgId\":\"PTESTBICXXXX9202208240258570000001\",\"CreDtTm\":\"2021-06-01T12:12:12.123Z\"},\"LqdtyCdtTrf\":{\"LqdtyTrfId\":{\"InstrId\":\"SY0502021112900012116\",\"EndToEndId\":\"2116999\",\"TxId\":\"str2116\",\"UETR\":\"71cc8a4e-83a9-4a19-a60f-78dacbb02116\"},\"Cdtr\":{\"FinInstnId\":{\"BICFI\":\"CHASSGSGXXX\"}},\"CdtrAcct\":{\"Id\":{\"Othr\":{\"Id\":\"0xe5faFa951361f7452593ea85Fa40B3D8A615A653\",\"Issr\":\"CHASUS33MCY\"}},\"Ccy\":\"USD\"},\"TrfdAmt\":{\"AmtWthCcy\":{\"Ccy\":\"USD\",\"Amount\":200}},\"Dbtr\":{\"FinInstnId\":{\"BICFI\":\"CHASSGSGXXX\"}},\"DbtrAcct\":{\"Id\":{\"Othr\":{\"Id\":\"0xe5faFa951361f7452593ea85Fa40B3D8A615A653\",\"Issr\":\"CHASUS33MCY\"}},\"Ccy\":\"USD\"},\"SttlmDt\":\"2021-07-16\"},\"SplmtryData\":null}}}}}";
        System.out.println("original messasge:" + msg);
        String encrypted =  nimbusJose.readyApiEncryptMsgToPartior( msg, "CHASSGSGXXX");
        System.out.println("encrypted:" + encrypted);
        String decrypted =  nimbusJose.readyApiDecryptMsgFromPartior (  encrypted, "CHASSGSGXXX");
        System.out.println("decrypted:" + decrypted);

    }

    public static void main(String[] args) throws Exception {
       // NimbusJose.testRead();
        NimbusJose.maintest();
    }

    public static void testRead() throws IOException
    {
        //Creating instance to avoid static member methods

        String resource = "keys/AAAAAA00000_CLIENT_RSA.pem";
        resource ="keys/AAAAAA00000_CLIENT_RSA.pem";

        System.out.println(KeyUtils.readFileContent(resource));

    }

    private InputStream getFileAsIOStream(final String fileName)
    {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    private String readFileContent(InputStream is) throws IOException
    {
        StringBuffer sb = new StringBuffer();
        try (InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr);)
        {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            is.close();
        }
        return sb.toString();
    }

}



