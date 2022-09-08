package me.chrisanabo.jetty.servlet;

import me.chrisanabo.jetty.service.MessageContainer;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessageContainerServlet extends HttpServlet {

    private MessageContainer messageContainer;

    public MessageContainerServlet(MessageContainer messageContainer) {
        this.messageContainer = messageContainer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String msgId = req.getParameter("msgId");

        /* test application scope*/
        ServletContext context =  req.getServletContext();
        Object attribute = context.getAttribute("globalVariable");

        resp.setStatus(HttpStatus.OK_200);

        String payload = messageContainer.getMessagePayload(msgId);
        resp.getWriter().println(payload == null?"message_not_found":payload);

    }
}
