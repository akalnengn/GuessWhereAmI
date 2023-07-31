package org.engina.exception;

import lombok.Getter;

@Getter
public class GuessManagementServiceException extends RuntimeException{

    private final ErrorType errorType;

    public GuessManagementServiceException(ErrorType errorType, String  customMessage ) {
        super(customMessage);
        this.errorType = errorType;

    }

    public GuessManagementServiceException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
