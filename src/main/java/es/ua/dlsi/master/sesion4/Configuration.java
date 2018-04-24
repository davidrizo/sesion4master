package es.ua.dlsi.master.sesion4;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import es.ua.dlsi.master.sesion4.persistence.IUsersDAO;
import es.ua.dlsi.master.sesion4.persistence.xstream.XStreamUsersDAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @autor drizo
 */
public class Configuration {
    private final Injector injector;
    private String version="<conf.not.loaded>";
    private String language;

    public Configuration() throws Sesion4Exception {
        Logger.getLogger(Configuration.class.getName()).log(Level.INFO, "Loading configuration");
        String propertiesFile = System.getProperty("configurationfile");
        InputStream configurationInputStream = null;
        if (propertiesFile == null) {
            configurationInputStream = this.getClass().getResourceAsStream("/defaultconfiguration.properties");
            Logger.getLogger(Configuration.class.getName()).log(Level.INFO, "Using default configuration");
        } else {
            try {
                Logger.getLogger(Configuration.class.getName()).log(Level.INFO, "Using supplied configuration '" + propertiesFile + "'");
                configurationInputStream = new FileInputStream(propertiesFile);
                // podría ser loadXML si queremos unas properties en XML
            } catch (IOException e) {
                throw new Sesion4Exception("Cannot load the properties file " + propertiesFile);
            }
        }
        Properties properties = new Properties();
        try {
            properties.load(configurationInputStream);
            // podría ser loadXML si queremos unas properties en XML
        } catch (IOException e) {
            throw new Sesion4Exception("Cannot read the properties");
        }

        InputStream loggerInputStream = null;
        String loggerProperties = properties.getProperty("logger");
        if (loggerProperties == null) {
            Logger.getLogger(Configuration.class.getName()).log(Level.INFO, "Using default log compiled configuration from logger.properties");
            loggerInputStream = this.getClass().getResourceAsStream("/logger.properties");
        } else {
            try {
                loggerInputStream = new FileInputStream(loggerProperties);
            } catch (FileNotFoundException e) {
                throw new Sesion4Exception("Cannot open the logger properties :'" + loggerProperties + "'", e);
            }
        }
        try {
            LogManager.getLogManager().readConfiguration(loggerInputStream);
        } catch (IOException e) {
            throw new Sesion4Exception("Cannot read the logger properties :'" + loggerProperties + "'", e);
        }


        version = properties.getProperty("version");
        if (version == null) {
            String message = "The configuration file does not contain a 'version' entry";
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, message);
            throw new Sesion4Exception(message);
        } else {
            Logger.getLogger(Configuration.class.getName()).log(Level.INFO, "Version {0}", version);
        }

        language = properties.getProperty("language");
        if (version == null) {
            String message = "The configuration file does not contain a 'language' entry";
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, message);
            throw new Sesion4Exception(message);
        } else {
            Logger.getLogger(Configuration.class.getName()).log(Level.INFO, "Using language Version {0}", language);
        }

        // inject dependencies
        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(IUsersDAO.class).to(XStreamUsersDAO.class);
            }
        });
    }

    public Injector getInjector() {
        return injector;
    }

    public String getVersion() {
        return version;
    }

    public String getLanguage() {
        return language;
    }
}
