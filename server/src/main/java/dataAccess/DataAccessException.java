package dataAccess;

/**
 * Indicates there was an error connecting to the database
 */
public class DataAccessException extends Exception{
    final private int statusCode;
    private String message;

    public DataAccessException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
    public String getMessage(){
        return this.message;
    }
    public int statusCode() {
        return statusCode;
    }}
