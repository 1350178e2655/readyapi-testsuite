package me.chrisanabo.jetty.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.chrisanabo.jetty.utils.Utility;
import me.chrisanabo.security.NimbusJose;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class MessageDecryptionFilter implements javax.servlet.Filter {

    private NimbusJose nimbusJose;

    public void init(FilterConfig filterConfig) throws ServletException {
        nimbusJose = new NimbusJose();
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        if (req instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) req;

            String systemId = request.getHeader("X-System-Id");

            if(request.getServletPath().equalsIgnoreCase("/callback")){


                String decryptedMessage = null;
                try {

                    decryptedMessage = nimbusJose.readyApiDecryptMsgFromPartior (Utility.requestBody(request), systemId);
                    DecrytMessageRequestWrapper decryptedRequest = new DecrytMessageRequestWrapper( request, decryptedMessage);
                    filterChain.doFilter(decryptedRequest, resp);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


            // modify request body

        }

        filterChain.doFilter(req, resp);

    }

    public void destroy() {

    }



    private class DecrytMessageRequestWrapper extends HttpServletRequestWrapper {
        private final String body;
        private ObjectMapper objectMapper = new ObjectMapper();

        public DecrytMessageRequestWrapper(HttpServletRequest request, String payload) throws IOException {
            super(request);
            body = payload;

        }

        @Override
        public ServletInputStream getInputStream() throws IOException {

            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
            ServletInputStream servletInputStream = new ServletInputStream() {
                public int read() throws IOException {
                    return byteArrayInputStream.read();
                }

                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener listener) {

                }
            };
            return servletInputStream;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(this.getInputStream()));
        }
    }

}
