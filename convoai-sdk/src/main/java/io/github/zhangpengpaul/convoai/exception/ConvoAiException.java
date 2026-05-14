package io.github.zhangpengpaul.convoai.exception;

public class ConvoAiException extends RuntimeException {
    public ConvoAiException(String message) {
        super(message);
    }

    public ConvoAiException(String message, Throwable cause) {
        super(message, cause);
    }
}
