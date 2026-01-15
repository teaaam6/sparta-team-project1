package sparta.spartateamproject1.exception;

public class SamePasswordException extends RuntimeException{

    public SamePasswordException(String message) {
        super(message);
    }
}
