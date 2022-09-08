package me.chrisanabo.security.service;

import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;

public interface NimbusFactory {

    JWEDecrypter getDecrypter(String privateKeyPath);

    JWEEncrypter getEncrypter(String publicKeyPath);

    JWSSigner getSigner(String privateKeyPath);

    JWSVerifier getVerifier(String publicKeyPath);

}
