package by.guryanchyck.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Alexey Guryanchyck on 30.08.2015.
 *
 */
public class Settings {
    private static final Settings INSTANCE = new Settings();
    private final Properties properties = new Properties();

    private Settings(){
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource("jdbc.properties").getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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