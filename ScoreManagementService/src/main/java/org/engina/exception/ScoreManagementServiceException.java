package org.engina.exception;

import lombok.Getter;

@Getter
public class ScoreManagementServiceException extends RuntimeException{

    private final ErrorType errorType;

    public ScoreManagementServiceException(ErrorType errorType, String  customMessage ) {
        super(customMessage);
        this.errorType = errorType;

    }

    public ScoreManagementServiceException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
