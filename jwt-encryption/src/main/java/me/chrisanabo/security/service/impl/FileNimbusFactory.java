package me.chrisanabo.security.service.impl;

import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import me.chrisanabo.security.utils.KeyUtils;
import me.chrisanabo.security.service.NimbusFactory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

import static me.chrisanabo.security.constants.Constants.*;


public class FileNimbusFactory implements NimbusFactory {

    private static Logger log = LoggerFactory.getLogger(FileNimbusFactory.class);

    public PublicKey getPublicKey(String path) {
        try {
            String publicKey = KeyUtils.readFileContent(( getCertificatePath(path, FILE_PUBLIC_KEY_EXTENSION) ));
            return KeyUtils.encodePublicKey(publicKey);
        } catch (Exception e) {
            log.error ("Error occurred while reading public key from file:" + path );
        }
        return null;
    }

    public PrivateKey getPrivateKey(String path) {
        try {
            String privateKey = KeyUtils.readFileContent(( getCertificatePath(path, FILE_PRIVATE_KEY_EXTENSION) ));
            return KeyUtils.encodePrivateKey(privateKey);
        } catch (Exception e) {
           log.error ("Error occurred while reading private key from file:" + path );
        }
        return null;
    }

    @Override
    public JWEDecrypter getDecrypter(String privateKeyPath) {
        return new RSADecrypter(getPrivateKey(privateKeyPath)); // + CLIENT_KEY_ID_SUFFIX
    }

    @Override
    public JWEEncrypter getEncrypter(String publicKeyPath) {
        return new RSAEncrypter((RSAPublicKey) getPublicKey(publicKeyPath )); // + NETWORK_KEY_ID_SUFFIX
    }

    @Override
    public JWSSigner getSigner(String privateKeyPath) {
        return new RSASSASigner(getPrivateKey(privateKeyPath)); // + CLIENT_KEY_ID_SUFFIX
    }

    @Override
    public JWSVerifier getVerifier(String publicKeyPath) {
        return new RSASSAVerifier((RSAPublicKey) getPublicKey(publicKeyPath)); // + NETWORK_KEY_ID_SUFFIX
    }

    private String getCertificatePath(String path, String filePathExtention){
       return path + filePathExtention;
    }


}