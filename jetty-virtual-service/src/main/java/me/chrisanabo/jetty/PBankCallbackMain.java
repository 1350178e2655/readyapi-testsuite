package me.chrisanabo.jetty;

import me.chrisanabo.jetty.filter.MessageDecryptionFilter;

import me.chrisanabo.jetty.service.MessageContainer;
import me.chrisanabo.jetty.servlet.MessageContainerServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import me.chrisanabo.jetty.servlet.MessageCallbackServlet;
import org.eclipse.jetty.servlet.ServletHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class PBankCallbackMain {

	static final int PORT = 8080;

	private volatile boolean running;

	private Server server = null;


	private static Logger logger = LoggerFactory.getLogger(PBankCallbackMain.class.getSimpleName() );

	private Thread jetty;
	private PBankCallbackMain pBankCallbackMain;

	public  void startServerFromRunnable(int port) throws Exception {

		jetty = new Thread() {

			public void run(){
				try {
					pBankCallbackMain =	new PBankCallbackMain();
					pBankCallbackMain.startServer(8080);
				} catch (Exception e) {
					e.printStackTrace();
				}

				running = true;

			}
		};
		jetty.setDaemon(true);
		jetty.start();
	}

	public  void stopServerFromRunnable() throws Exception {
		pBankCallbackMain.stopServer();
		logger.info(" ending thread");
	}

	public  void startServer(int port) throws Exception {

		String value = System.getProperty("read_from_minus_d");
		String log_dir = System.getenv("read_from_env_variable");
		String contextPath =  System.getenv("read_from_env_variable");


		server = new Server(PORT);
		ServletContextHandler context = new ServletContextHandler(server, "/bank");


		MessageContainer messageContainer = new MessageContainer();
		context.addServlet(new ServletHolder(new MessageCallbackServlet(messageContainer)), "/callback");
		context.addServlet(new ServletHolder(new MessageContainerServlet(messageContainer)), "/checkMsg");

		final EnumSet<DispatcherType> REQUEST_SCOPE = EnumSet.of(DispatcherType.REQUEST);
	    context.addFilter( MessageDecryptionFilter.class, "/*", REQUEST_SCOPE );


		context.setAttribute("globalVariable", "aValue");

		logger.info(" Embedded Jetty was started successfully");
		server.start();

	}




	public  void stopServer() throws Exception {


		if (server != null && server.isRunning()) {
			server.stop();
			logger.info(" Embedded Jetty was ended successfully");
		}
		logger.info(" ending thread");

	}


	public static void main(String[] args) throws Exception {
		int port = PORT;
		logger.info("Starting Jetty server on port " + port);
		PBankCallbackMain jetty = new PBankCallbackMain();
		jetty.startServer(8080);
		logger.info("jetty started and im going to sleep");

	}

}
