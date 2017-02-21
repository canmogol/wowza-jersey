package com.fererlab.wowzajersey.core.config;

/**
 * Configuration utility
 */
public interface Configurator {

    /**
     * loads configuration file
     *
     * @param configurationFile file name
     */
    void loadConfiguration(String configurationFile);

    /**
     * returns object representation of configuration file
     * which might be in XML or some other format
     *
     * @return Configuration object
     */
    Configuration getConfiguration();

    /**
     * calls {@link Configurable#configure(Configuration)} method
     * on configurable object
     *
     * @param configurable Configurable object
     */
    void configure(Configurable configurable);

}
