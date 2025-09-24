package app.exceptions;


import app.enums.ErrorTypes;

public class ApiException extends RuntimeException {
    private final int code;
    private final ErrorTypes errorType;

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
        this.errorType = null;
    }

    public int getCode() {
        return code;
    }

    public ApiException(ErrorTypes errorType, String message) {
        super(message);
        this.errorType = errorType;
        this.code = errorType.getErrorCode();
    }

    //TODO: 400 bad request
    public static ApiException badRequest (String msg) {
        return new ApiException(ErrorTypes.BAD_REQUEST, ErrorTypes.BAD_REQUEST.getErrorMessage() + "\n" + msg);
    }

    //TODO: 404 not found
    public static ApiException notFound(String msg) {
        return new ApiException(ErrorTypes.NOT_FOUND, ErrorTypes.NOT_FOUND.getErrorMessage() + "\n" + msg);

    }


    //TODO: 409 already exists
    public static ApiException alreadyExists(String msg) {
        return new ApiException(ErrorTypes.ALREADY_EXISTS, ErrorTypes.ALREADY_EXISTS.getErrorMessage() + "\n" + msg);

    }


    //TODO: 405 conflict
    public static ApiException conflict (String msg) {
        return new ApiException(ErrorTypes.CONFLICT, ErrorTypes.CONFLICT.getErrorMessage() + "\n" + msg);

    }

    //TODO: 500 server error
    public static ApiException serverError (String msg) {
        return new ApiException(ErrorTypes.SERVER_ERROR, ErrorTypes.SERVER_ERROR.getErrorMessage() + "\n" + msg);
    }

}


