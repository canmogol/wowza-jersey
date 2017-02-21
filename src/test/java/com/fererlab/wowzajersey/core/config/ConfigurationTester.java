package com.fererlab.wowzajersey.core.config;

import com.fererlab.wowzajersey.app.controller.RWSModuleController;
import com.fererlab.wowzajersey.app.service.JettyWebServerService;
import com.fererlab.wowzajersey.core.controller.Controller;
import com.fererlab.wowzajersey.core.service.type.Service;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ConfigurationTester {

    @Test
    public void testConfiguration() throws JAXBException {
        // set configuration test file
        String configurationTestFile = "configuration-test.xml";

        // create configuration object
        Configuration configurationOriginal = new Configuration();
        configurationOriginal.setAuthenticationUrl("http://localhost/wowzajersey/public/api/authenticate");
        configurationOriginal.setAuthorizationUrl("http://localhost/wowzajersey/public/api/authorize");
        configurationOriginal.setSessionKey("laravel_session");
        List<Class<? extends Controller>> controllers = new ArrayList<>();
        controllers.add(RWSModuleController.class);
        controllers.add(RWSModuleController.class);
        configurationOriginal.setControllers(controllers);
        List<Class<? extends Service>> services = new ArrayList<>();
        services.add(JettyWebServerService.class);
        services.add(JettyWebServerService.class);
        configurationOriginal.setServices(services);

        // write configuration object to file
        File file = new File(configurationTestFile);
        JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(configurationOriginal, file);

        // read configuration object from file
        file = new File(configurationTestFile);
        jaxbContext = JAXBContext.newInstance(Configuration.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Configuration configurationFromXml = (Configuration) unmarshaller.unmarshal(file);

        // assert fields
        assertEquals(configurationOriginal.getAuthenticationUrl(), configurationFromXml.getAuthenticationUrl());
        assertEquals(configurationOriginal.getAuthorizationUrl(), configurationFromXml.getAuthorizationUrl());
        assertEquals(configurationOriginal.getSessionKey(), configurationFromXml.getSessionKey());
        assertEquals(configurationOriginal.getServices(), configurationFromXml.getServices());
        assertEquals(configurationOriginal.getControllers(), configurationFromXml.getControllers());
    }

}
