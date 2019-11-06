package life.majiang.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"该问题不存在！"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或进行回复！"),
    NO_LOGIN(2003,"未登录，请登录再试，3ku！"),
    SYS_ERROR(2004,"服务太热，爆了！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或者不存在"),
    COMMENT_NOT_FOUND(2006,"该评论不存在！")

    ;
    private Integer code ;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
