package by.huryanchyk.service;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p/>
 * The interface has methods for conversion data in file
 * and importing it into database
 */
public interface ImportService {

    /**
     * Adds text data to blocking queue
     *
     * @param dataArray text data for adding to blocking queue
     * @throws InterruptedException
     */
    void addTextToQueue(String[] dataArray) throws InterruptedException;

}
