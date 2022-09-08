package me.chrisanabo.jetty.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Utility {

    public static String requestBody(HttpServletRequest req) throws IOException {
        return req.getReader().lines().reduce("",String::concat);
    }
}
