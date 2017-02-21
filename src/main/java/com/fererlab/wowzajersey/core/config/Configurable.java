package com.fererlab.wowzajersey.core.config;

/**
 * this is a helper interface for configuration details
 */
public interface Configurable {

    /**
     * implemented classes should be able to configure their internal states using configuration object
     *
     * @param configuration {@link Configuration}
     */
    void configure(Configuration configuration);

}
