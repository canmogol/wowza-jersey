package com.fererlab.wowzajersey.core.config;

import com.fererlab.wowzajersey.core.log.DefaultConfiguratorLogger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

/**
 * loads configuration from xml file
 */
public class XmlConfigurator implements Configurator {

    private DefaultConfiguratorLogger logger = new DefaultConfiguratorLogger();
    private Configuration configuration;

    /**
     * loads the Xml mapped Configuration file from given configuration file,
     * default is the configuration.xml inside the jar
     *
     * @param configurationFile configuration xml file
     */
    public void loadConfiguration(String configurationFile) {
        try {
            logger.willLoadConfigurationFile(configurationFile);
            // will use the JAXB (standard implementation to read the xml file to object)
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configurationFile);
            JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            configuration = (Configuration) jaxbUnmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            logger.couldNotReadConfiguration(e.getMessage());
        }
    }

    /**
     * @return Configuration immutable configuration object
     */
    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * calls configure method on object
     *
     * @param configurable Configurable object
     */
    @Override
    public void configure(Configurable configurable) {
        configurable.configure(getConfiguration());
    }

}
