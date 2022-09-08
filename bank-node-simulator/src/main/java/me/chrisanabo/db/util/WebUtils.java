package me.chrisanabo.db.util;

import com.nimbusds.jwt.SignedJWT;

import me.chrisanabo.model.payload.*;



import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WebUtils {





    public static String getJWTClientId(String jwtToken) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(jwtToken);
        return (String) signedJWT.getJWTClaimsSet().getClaim("clientId");
    }

    public static List<String> getJWTRoles(String jwtToken, String clientName) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(jwtToken);
        Map<String, Object> resourceAccess = (Map<String, Object>) signedJWT.getJWTClaimsSet().getClaim("resource_access");
        Map<String, List<String>> roles = (Map<String, List<String>>) resourceAccess.get(clientName);
        return roles.get("roles");
    }

    public static Optional<String> getToSystemId(BaseRequest msg) {
        try {
            BusMsg busMsg = msg.getBusMsg();
            AppHdr appHdr = busMsg.getAppHdr();
            To to = appHdr.getTo();
            FIId fiid = to.getFIId();
            FinInstnId finInstnId = fiid.getFinInstnId();
            return Optional.of(finInstnId.getBICFI());
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    public static Optional<String> getFrSystemId(BaseRequest msg) {
        try {
            BusMsg busMsg = msg.getBusMsg();
            AppHdr appHdr = busMsg.getAppHdr();
            Fr fr = appHdr.getFr();
            FIId fiid = fr.getFIId();
            FinInstnId finInstnId = fiid.getFinInstnId();
            return Optional.of(finInstnId.getBICFI());
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    public static Optional<String> getMessageId(BaseRequest msg) {
        try {
            BusMsg busMsg = msg.getBusMsg();
            AppHdr appHdr = busMsg.getAppHdr();
            return Optional.of(appHdr.getBizMsgIdr());
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    public static Optional<String> getMessageType(BaseRequest msg) {
        try {
            BusMsg busMsg = msg.getBusMsg();
            AppHdr appHdr = busMsg.getAppHdr();
            return Optional.of(appHdr.getMsgDefIdr());
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }
}
