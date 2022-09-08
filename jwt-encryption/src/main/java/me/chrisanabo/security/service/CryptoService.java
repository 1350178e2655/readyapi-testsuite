package me.chrisanabo.security.service;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import me.chrisanabo.security.service.impl.FileNimbusFactory;

import java.text.ParseException;

public class CryptoService {

    private NimbusFactory nimbusFactory;

    public CryptoService() {
        this.nimbusFactory = new FileNimbusFactory();
    }

    public SignedJWT decrypt(String toDecrypt, String privateKeyPath) throws ParseException, JOSEException {
        EncryptedJWT jwt = EncryptedJWT.parse(toDecrypt);
        JWEDecrypter decrypter = nimbusFactory.getDecrypter(privateKeyPath);
        jwt.decrypt(decrypter);
        return jwt.getPayload().toSignedJWT();
    }


    public String encrypt(SignedJWT toEncrypt, String publicKeyPath) throws JOSEException {
        JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256CBC_HS512);
        JWEObject jweObject = new JWEObject(header, new Payload(toEncrypt));
        JWEEncrypter encrypter = nimbusFactory.getEncrypter(publicKeyPath);
        jweObject.encrypt(encrypter);
        return jweObject.serialize();
    }


    public SignedJWT sign(JWTClaimsSet jwtClaimsSet, String privateKeyPath) throws JOSEException {
        JWSSigner signer = nimbusFactory.getSigner(privateKeyPath);
        JWSHeader.Builder headerBuilder = new JWSHeader.Builder(JWSAlgorithm.RS256);
        SignedJWT jwt = new SignedJWT(headerBuilder.build(), jwtClaimsSet);
        jwt.sign(signer);
        return jwt;
    }

    public boolean validateSignature(SignedJWT jwt, String publicKeyPath) throws JOSEException {
        return jwt.verify(nimbusFactory.getVerifier(publicKeyPath));
    }

}
