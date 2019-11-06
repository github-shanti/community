package life.majiang.community.exception;

public class CustomizeException extends  RuntimeException {

    private  Integer code ;
    private  String message ;


    public CustomizeException(ICustomizeErrorCode iCustomizeErrorCode) {
//        super(iCustomizeErrorCode.getMessage());
        this.code = iCustomizeErrorCode.getCode();

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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
