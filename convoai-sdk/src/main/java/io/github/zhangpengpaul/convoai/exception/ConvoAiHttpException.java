package io.github.zhangpengpaul.convoai.exception;

public final class ConvoAiHttpException extends ConvoAiException {
    private final int statusCode;
    private final String errorCode;
    private final String responseBody;

    public ConvoAiHttpException(int statusCode, String errorCode, String message, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.responseBody = responseBody;
    }

    public int statusCode() {
        return statusCode;
    }

    public String errorCode() {
        return errorCode;
    }

    public String responseBody() {
        return responseBody;
    }
}
