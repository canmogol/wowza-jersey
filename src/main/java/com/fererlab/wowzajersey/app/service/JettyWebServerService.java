package com.fererlab.wowzajersey.app.service;

import com.fererlab.wowzajersey.app.log.JettyEndpointLogger;
import com.fererlab.wowzajersey.app.restful.BaseResource;
import com.fererlab.wowzajersey.core.service.type.StartFastService;
import com.fererlab.wowzajersey.core.service.type.WebServerService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;


public class JettyWebServerService extends StartFastService implements WebServerService {

    private Server jettyServer;
    private JettyEndpointLogger logger = new JettyEndpointLogger();
    private Integer port = 18099;

    @Override
    public void start() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS | ServletContextHandler.GZIP);
        context.setContextPath("/");
        context.setAttribute(JettyWebServerService.class.getName(), this);

        // hostname will be null, so server will bind to all interfaces on this port
        jettyServer = new Server(this.port);
        jettyServer.setHandler(context);

        // we will register "/api" path as the jersey servlet's uri, restful-web-services will be under this uri
        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class,
                "/api/*"
        );
        jerseyServlet.setAsyncSupported(true);
        jerseyServlet.setInitOrder(0);
        // this package will be monitored by jersey and (restful-web-service) resources will be
        jerseyServlet.setInitParameter(
                ServerProperties.PROVIDER_PACKAGES,
                BaseResource.class.getPackage().getName()
        );

        try {
            // start jetty server
            jettyServer.start();
            // join will block
            jettyServer.join();
        } catch (Exception e) {
            logger.couldNotStartJoin(e.getMessage());
        }
    }

    @Override
    public void stop() {
        try {
            // stop jetty server
            jettyServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPort(Integer port) {
        this.port = port;
    }
}
