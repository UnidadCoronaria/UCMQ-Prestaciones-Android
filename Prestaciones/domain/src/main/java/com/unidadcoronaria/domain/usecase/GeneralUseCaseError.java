package com.unidadcoronaria.domain.usecase;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GeneralUseCaseError {

    //region Properties
    private String message;
    private int code;
    private String httpMessage;
    private String errorMessage;
    //endregion

    //region Constructor
    public GeneralUseCaseError(String message) {
        this.message = message;
    }

    public GeneralUseCaseError(int code, String httpMessage, String errorMessage) {
        this.code = code;
        this.httpMessage = httpMessage;
        this.errorMessage = errorMessage;
    }

    //endregion


    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getHttpMessage() {
        return httpMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
