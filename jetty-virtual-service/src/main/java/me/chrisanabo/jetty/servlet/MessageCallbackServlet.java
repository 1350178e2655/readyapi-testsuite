package me.chrisanabo.jetty.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import me.chrisanabo.jetty.service.MessageContainer;
import me.chrisanabo.jetty.utils.Utility;
import org.eclipse.jetty.http.HttpStatus;


public class MessageCallbackServlet extends HttpServlet {

	private MessageContainer messageContainer;

	public MessageCallbackServlet(MessageContainer messageContainer) {
		this.messageContainer = messageContainer;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		/* test application scope*/
		ServletContext context =  req.getServletContext();
		Object attribute = context.getAttribute("globalVariable");
		resp.setStatus(HttpStatus.OK_200);
		resp.getWriter().println("EmbeddedJetty:" + attribute);

	}

//	curl -X POST http://localhost:8080/bank/callback -H 'Content-Type: application/json' -d '{"login":"my_login","password":"my_password"}'
// 	curl -X GET http://localhost:8080/bank/checkMsg?msgId=chris

//

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String endpoint = req.getHeader("X-OUTBOUND-URI");
		String msgId = req.getHeader("X-Msg-Id");
		String msgType = req.getHeader("X-Msg-Type");
		String systemId = req.getHeader("X-System-Id");
		String receiverSystemId = req.getHeader("X-Receiver-System-Id");

		String contentType = req.getContentType();

		storeMsgPayload(msgId, Utility.requestBody(req));
		resp.setStatus(HttpStatus.OK_200);
	}

	private void storeMsgPayload(String msgId, String msgBody){
		messageContainer.storePayload( msgId, msgBody );
	}

	private boolean checkSecurityHeaders(HttpServletRequest req){
		String xOutboundUri = req.getHeader("X-OUTBOUND-URI");
		return true;
	}

	private Map<String, String> getHeadersInfo(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();

		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}

}
