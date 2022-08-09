package cinema.exceptions;

public class UnauthorizedException extends RuntimeException {

    private String message;

    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
