package com.fererlab.wowzajersey.core.config;

import com.fererlab.wowzajersey.app.controller.RWSModuleController;
import com.fererlab.wowzajersey.app.service.JettyWebServerService;
import com.fererlab.wowzajersey.core.controller.Controller;
import com.fererlab.wowzajersey.core.service.type.Service;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * XmlConfigurator's loadConfiguration operation tester
 */
public class XmlConfiguratorTest {

    private Configuration configurationOriginal;
    private XmlConfigurator xmlConfigurator;
    private String configurationTestFile;

    @Before
    public void prepareConfiguration() throws JAXBException {
        // set configuration file
        configurationTestFile = "configuration-xml-test.xml";

        // create configuration object
        configurationOriginal = new Configuration();
        configurationOriginal.setAuthenticationUrl("http://localhost/wowzajersey/public/api/authenticate");
        configurationOriginal.setAuthorizationUrl("http://localhost/wowzajersey/public/api/authorize");
        configurationOriginal.setSessionKey("laravel_session");
        List<Class<? extends Controller>> controllers = new ArrayList<>();
        controllers.add(RWSModuleController.class);
        configurationOriginal.setControllers(controllers);
        List<Class<? extends Service>> services = new ArrayList<>();
        services.add(JettyWebServerService.class);
        configurationOriginal.setServices(services);

        // write configuration object to file
        String path = null;
        try {
            path = getClass().getClassLoader().getResource(".").getPath();
        } catch (NullPointerException e) {
            path = "target/test-classes/";
        }
        File file = new File(path + configurationTestFile);
        JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(configurationOriginal, file);

        // create XmlConfigurator
        xmlConfigurator = new XmlConfigurator();
    }

    @Test
    public void testLoadConfiguration() throws Exception {
        // load configuration file created for this test
        xmlConfigurator.loadConfiguration(configurationTestFile);
        Configuration configurationFromXmlConfiguratorConfigurator = xmlConfigurator.getConfiguration();

        // assert fields
        assertEquals(configurationOriginal.getAuthenticationUrl(), configurationFromXmlConfiguratorConfigurator.getAuthenticationUrl());
        assertEquals(configurationOriginal.getAuthorizationUrl(), configurationFromXmlConfiguratorConfigurator.getAuthorizationUrl());
        assertEquals(configurationOriginal.getSessionKey(), configurationFromXmlConfiguratorConfigurator.getSessionKey());
        assertEquals(configurationOriginal.getServices(), configurationFromXmlConfiguratorConfigurator.getServices());
        assertEquals(configurationOriginal.getControllers(), configurationFromXmlConfiguratorConfigurator.getControllers());
    }

}