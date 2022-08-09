package cinema.exceptions;

public class BuiyngException extends RuntimeException {


    private String message;

    public BuiyngException() {
    }

    public BuiyngException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}