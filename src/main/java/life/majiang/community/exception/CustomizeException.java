package life.majiang.community.exception;

public class CustomizeException extends  RuntimeException {
    private  String message ;

    public CustomizeException(ICustomizeErrorCode iCustomizeErrorCode) {
        super(iCustomizeErrorCode.getMessage());
        this.message = iCustomizeErrorCode.getMessage();
    }

    public CustomizeException() {
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
