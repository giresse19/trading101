package com.appsdeveloperblog.app.ws.mobileappws.persistence.model.response;

public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field, Please check documentation for required fields"),
    RECORD_ALREADY_EXISTS("User with this email already exist"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    RECORD_NOT_FOUND("Record not found"),
    FAILED_PRECONDITION("Resource precondition failed"),
    NO_RECORD_FOUND("Record with provided id is not found"),
    AUTHENTICATION_FAILED("Authentication failed due to wrong credentials"),
    JWT_EXPIRED("Authentication failed due to expired jwt token"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified"),
    EMAIL_ADDRESS_ALREADY_EXIST("Email address already exist");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public  String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
