package com.fererlab.wowzajersey.app.controller;

import com.fererlab.wowzajersey.core.config.Configuration;
import com.fererlab.wowzajersey.app.log.RWSModuleControllerLogger;
import com.fererlab.wowzajersey.app.model.Client;
import com.fererlab.wowzajersey.app.model.HttpSession;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * this controller should call a restful web service for operations
 */
public class RWSModuleController extends LoggingModuleController {

    private RWSModuleControllerLogger logger = new RWSModuleControllerLogger();
    private String authorizationUrl = "http://localhost/wowzajersey/public/api/authorize";
    private String authenticationUrl = "http://localhost/wowzajersey/public/api/authenticate";
    private String sessionKey = "laravel_session";

    enum Key {
        // response types
        True("true"), False("false"),
        // authorize params
        ip("ip"), uri("uri"), id("id"), uniqueId("uniqueId"), authorized("authorized"), connected("connected"), streamType("streamType"),
        // authenticate params
        ipAddressClient("ipAddressClient"),
        ipAddressServer("ipAddressServer"),
        queryStr("queryStr"),
        referrer("referrer"),
        cookieStr("cookieStr"),
        userAgent("userAgent"),
        sessionId("sessionId"),
        applicationFullName("applicationFullName"),
        streamName("streamName"),
        authenticated("authenticated");

        private final String value;

        Key(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * authorize client against a restful service
     *
     * @param client {@link Client}
     * @return true if the service authorizes the client
     */
    @Override
    public boolean authorizeClient(Client client) {
        logger.authorizeClient(client);

        // create client proxy
        ResteasyClient resteasyClient = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = resteasyClient.target(this.authorizationUrl);

        // prepare request object
        Map<String, Object> map = new HashMap<>();
        map.put(Key.ip.value, client.getIp());
        map.put(Key.uri.value, client.getUri());
        map.put(Key.id.value, String.valueOf(client.getId()));
        map.put(Key.uniqueId.value, client.getUuid());
        map.put(Key.connected.value, client.isConnected());
        map.put(Key.streamType.value, client.getStreamType());
        map.put("@CLASS", "com.fererlab.client.dto.ClientDTO");// TODO: REMOVE THIS LINE!

        // do request, this should return a json object, read object to a map
        Map resultMap = target.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE),
                        new GenericType<Map>() {
                        });

        // check if this client is authorized
        boolean isAuthorized =
                (resultMap.containsKey(Key.authorized.value) && resultMap.get(Key.authorized.value) != null)
                        && resultMap.get(Key.authorized.value).toString().equalsIgnoreCase(Key.True.value);
        logger.isAuthorized(isAuthorized);

        return isAuthorized;
    }

    /**
     * authenticate http session against a restful service
     *
     * @param httpSession {@link HttpSession}
     * @return true if the service authenticates the session
     */
    @Override
    public boolean authenticateHttpSession(HttpSession httpSession) {
        logger.authenticateHttpSession(httpSession);

        String sessionValue = "";
        // httpSession.getCookieStr() should look something like this
        // JSESSIONID=qHqra9lNswdRNWcOtfCkxQdq.canm; com.ndi.app.NDI_fr_ck_sn_ky=ea226244f36f3a060544fd6ec0d5b8533bde4d0d;
        String cookiePairs[] = httpSession.getCookieStr().split(";");
        // after split there should be "key=value" pairs, like this
        // JSESSIONID=qHqra9lNswdRNWcOtfCkxQdq.canm
        for (String cookiePair : cookiePairs) {
            // for each pair try to split them with "="
            String[] cookieKeyValue = cookiePair.split("=");
            // now key and value are available to compare against sessionKey
            // JSESSIONID
            // qHqra9lNswdRNWcOtfCkxQdq.canm
            if (cookieKeyValue.length > 1 && sessionKey.equalsIgnoreCase(cookieKeyValue[0])) {
                sessionValue = cookieKeyValue[1];
                break;
            }
        }

        // create client proxy
        ResteasyClient resteasyClient = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = resteasyClient.target(this.authenticationUrl);

        // prepare request object
        Map<String, Object> map = new HashMap<>();
        map.put(Key.ipAddressClient.value, httpSession.getIpAddressClient());
        map.put(Key.ipAddressServer.value, httpSession.getIpAddressServer());
        map.put(Key.queryStr.value, httpSession.getQueryStr());
        map.put(Key.referrer.value, httpSession.getReferrer());
        map.put(Key.cookieStr.value, httpSession.getCookieStr());
        map.put(Key.userAgent.value, httpSession.getUserAgent());
        map.put(Key.sessionId.value, httpSession.getSessionId());
        map.put(Key.applicationFullName.value, httpSession.getApplicationFullName());
        map.put(Key.streamName.value, httpSession.getStreamName());
        map.put(Key.ip.value, httpSession.getClient().getIp());
        map.put(Key.uri.value, httpSession.getClient().getUri());
        map.put(Key.id.value, httpSession.getClient().getId());
        map.put(Key.uniqueId.value, httpSession.getClient().getUuid());
        map.put(Key.connected.value, httpSession.getClient().isConnected());
        map.put(Key.streamType.value, httpSession.getClient().getStreamType());
        map.put(sessionKey, sessionValue);
        map.put("@CLASS", "com.fererlab.client.dto.ClientDTO");// TODO: REMOVE THIS LINE!

        // do request, this should return a json object, read object to a map
        Map resultMap = target.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(map, MediaType.APPLICATION_JSON_TYPE),
                        new GenericType<Map>() {
                        });

        // check if this session is authenticated
        boolean isAuthenticated =
                (resultMap.containsKey(Key.authenticated.value) && resultMap.get(Key.authenticated.value) != null)
                        && resultMap.get(Key.authenticated.value).toString().equalsIgnoreCase(Key.True.value);
        logger.isAuthenticated(isAuthenticated);
        return isAuthenticated;
    }

    /**
     * notify the restful service that a client connected
     *
     * @param client {@link Client}
     */
    @Override
    public void clientConnected(Client client) {
        logger.clientConnected(client);
        //TODO: do a client connected request to restful web service
    }

    /**
     * notify the restful service that a client disconnected
     *
     * @param client {@link Client}
     */
    @Override
    public void clientDisconnected(Client client) {
        logger.clientDisconnected(client);
        //TODO: do a client disconnected request to restful web service
    }

    @Override
    public void configure(Configuration configuration) {
        this.authorizationUrl = configuration.getAuthorizationUrl();
        this.authenticationUrl = configuration.getAuthenticationUrl();
        this.sessionKey = configuration.getSessionKey();
    }

}
