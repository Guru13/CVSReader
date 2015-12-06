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

    private static final Settings INSTANCE = new Settings();
    private final Properties properties = new Properties();

    private Settings(){
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource("jdbc.properties").getFile()));
        } catch (IOException e) {
            logger.error("Could not load file", e);
            e.printStackTrace();
        }
    }

    /**
     * Represents singleton pattern that gets the
     * only instance of {@code Settings}.
     *
     * @return single instance of {@code Settings}.
     */
    public static Settings getInstance(){
        return INSTANCE;
    }


    public String value(String key){
        return this.properties.getProperty(key);
    }
}