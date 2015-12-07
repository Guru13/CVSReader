package by.huryanchyk.service;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Alexei Huryanchyk on 05.12.2015..
 *
 * Class for getting properties for connection with database
 */
public class Settings {

    private final static Logger logger = Logger.getLogger(Settings.class);

    private static final Properties properties = new Properties();

    public static String value(String key){
        try {
            properties.load(new FileInputStream(Settings.class.getClassLoader().getResource("jdbc.properties").getFile()));

        } catch (IOException e) {
            logger.error("Could not load file", e);
        }
        return properties.getProperty(key);
    }
}