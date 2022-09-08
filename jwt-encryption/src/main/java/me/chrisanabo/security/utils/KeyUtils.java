package me.chrisanabo.security.utils;

import me.chrisanabo.security.NimbusJose;
import me.chrisanabo.security.constants.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyUtils {

    public static PublicKey encodePublicKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        key = key.replace(Constants.PUBLIC_KEY_START, "")
                .replace(Constants.PUBLIC_KEY_END, "")
                .replaceAll("\n", "");

        byte[] encodedKey = decodeBase64(key);
        KeyFactory keyFactory = KeyFactory.getInstance(Constants.KEY_ALGO_RSA);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey encodePrivateKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        key = key.replace(Constants.PRIVATE_KEY_START, "")
                .replace(Constants.PRIVATE_KEY_END, "")
                .replaceAll("\n", "");

        byte[] encodedKey = decodeBase64(key);
        KeyFactory keyFactory = KeyFactory.getInstance(Constants.KEY_ALGO_RSA);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
        return keyFactory.generatePrivate(keySpec);

    }

    private static byte[] decodeBase64(String toDecode) {
        return Base64.getMimeDecoder().decode(toDecode);
    }

    private static InputStream getFileAsIOStream(final String fileName)
    {
        InputStream ioStream = NimbusJose.class
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    public static String readFileContent(final String fileName) throws IOException {


        InputStream is = getFileAsIOStream(fileName);

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

    public static void main(String[] args) {

    }
}