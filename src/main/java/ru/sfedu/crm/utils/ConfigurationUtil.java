package ru.sfedu.crm.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.crm.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Configuration utility. Allows to get configuration properties from the
 * default configuration file
 *
 * @author Boris Jmailov
 */
public class ConfigurationUtil {

    private static final Logger log = LogManager.getLogger(ConfigurationUtil.class);
    private static final String DEFAULT_CONFIG_PATH = Constants.DEFAULT_CONFIG_PATH;
    private static final Properties configuration = new Properties();
    private static final String CUSTOM_CONFIG_PATH = System.getProperty("config.path");
    /**
     * Hides default constructor
     */
    public ConfigurationUtil() {
    }

    private static Properties getConfiguration() throws IOException {
        if(configuration.isEmpty()){
            loadConfiguration();
        }
        return configuration;
    }

    /**
     * Loads configuration from <code>DEFAULT_CONFIG_PATH</code>
     * @throws IOException In case of the configuration file read failure
     */
    private static void loadConfiguration() throws IOException{
        File nf;
        nf = new File(Objects.requireNonNullElse(CUSTOM_CONFIG_PATH, DEFAULT_CONFIG_PATH));
        InputStream in = new FileInputStream(nf);
        try {
            configuration.load(in);
        } catch (IOException ex) {
            log.error("loadConfiguration[0]: Load config file error");
            throw new IOException(ex);
        } finally{
            in.close();
        }
    }
    /**
     * Gets configuration entry value
     * @param key Entry key
     * @return Entry value by key
     * @throws IOException In case of the configuration file read failure
     */
    public static String getConfigurationEntry(String key) throws IOException{
        return getConfiguration().getProperty(key);
    }

}