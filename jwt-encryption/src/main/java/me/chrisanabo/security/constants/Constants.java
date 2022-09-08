package me.chrisanabo.security.constants;

public class Constants {
    public static final String SYSTEM_ID_HEADER = "X-System-Id";

    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String SWIFTBIC_FORMAT= "^[A-Z]{6}[A-Z0-9]{2}([A-Z0-9]{3})?$";

    public static final String KEY_ALGO_RSA = "RSA";

    public static final String PUBLIC_KEY_START = "-----BEGIN PUBLIC KEY-----";
    public static final String PUBLIC_KEY_END = "-----END PUBLIC KEY-----";

    public static final String PRIVATE_KEY_START = "-----BEGIN PRIVATE KEY-----";
    public static final String PRIVATE_KEY_END = "-----END PRIVATE KEY-----";

    public static final String CLIENT_KEY_ID_SUFFIX = "_CLIENT_RSA";
    public static final String NETWORK_KEY_ID_SUFFIX = "_PARTIOR_RSA";

    public static final String FILE_PUBLIC_KEY_EXTENSION = ".pem";
    public static final String FILE_PRIVATE_KEY_EXTENSION = ".pk8";


    public static final String RESOURCE_DIR = "keys";

    public static final String JSON_MSG_DIR = "message";
    public static final String JSON_MSG_DIR_EXTENSION = ".json";

    public static final String DEFAULT_MSG_DEST = "PARTIOR_PUBLIC_RSA";

    public static final String PUBLIC_RSA = "_PUBLIC_RSA";
    public static final String PRIVATE_RSA = "_PRIVATE_RSA";


}
