package com.fererlab.wowzajersey.app.controller;

import com.fererlab.wowzajersey.app.model.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * canm
 */
public class RWSModuleControllerTest {

    private Client client;
    private RWSModuleController controller;

    @Before
    public void setUp() throws Exception {
        controller = new RWSModuleController();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAuthenticateClient() throws Exception {
        client = new Client.Builder()
                .connected(true)
                .id(123)
                .ip("192.168.1.1")
                .streamType("streamType")
                .uri("/stream/1")
                .build();
        boolean isAuthorized = controller.authorizeClient(client);
        assertTrue(isAuthorized);
    }

    @Test
    public void testAuthorizeHttpSession() throws Exception {

    }

    @Test
    public void testClientConnected() throws Exception {

    }

    @Test
    public void testClientDisconnected() throws Exception {

    }
}