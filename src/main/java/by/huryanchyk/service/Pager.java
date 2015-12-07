package by.huryanchyk.service;

/**
 * Created by Alexei Huryanchyk on 05.12.2015.
 * <p/>
 * The  class stores values for pagination and sorting
 */
public class Pager {

    private int recordsPerPage = 10;
    private String sortedMethod = "name";
    private int currentPage;

    /**
     * Sets default params
     */
    public void setDefaultParams(){
        recordsPerPage = 10;
        sortedMethod = "name";
        currentPage = 1;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public String getSortedMethod() {
        return sortedMethod;
    }

    public void setSortedMethod(String sortedMethod) {
        this.sortedMethod = sortedMethod;
    }
}
