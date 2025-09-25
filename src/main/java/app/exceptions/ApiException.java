package app.exceptions;


import app.enums.ErrorTypes;

public class ApiException extends RuntimeException {
    private final int code;
    private final ErrorTypes errorType;
    private static String errorMsg;

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
        this.errorType = ErrorTypes.getType(code);
        this.errorMsg = errorType.getErrorMessage() + "/n";
    }

    public ApiException(ErrorTypes errorType, String msg) {
        super(msg);
        this.errorType = errorType;
        this.code = errorType.getErrorCode();
        this.errorMsg = errorType.getErrorMessage() + "/n";
    }

    public int getCode() {
        return code;
    }

    /* |----------------------|
       |     CLIENT ERRORS    |
       |----------------------|
    */

    //TODO: 400 bad request
    public static ApiException badRequest (String msg) {
        return new ApiException(ErrorTypes.BAD_REQUEST, errorMsg + msg);
    }

    //TODO: 401 Unauthorized
    public static ApiException unauthorized (String msg) {
        return new ApiException(ErrorTypes.UNAUTHORIZED, errorMsg + msg);
    }

    //TODO: 403 Forbidden Access
    public static ApiException forbidden (String msg) {
        return new ApiException(ErrorTypes.FORBIDDEN, errorMsg + msg);
    }

    //TODO: 404 not found
    public static ApiException notFound(String msg) {
        return new ApiException(ErrorTypes.NOT_FOUND, errorMsg + msg);
    }

    //TODO: 405 conflict
    public static ApiException conflict (String msg) {
        return new ApiException(ErrorTypes.METHOD_NOT_ALLOWED, errorMsg + msg);
    }

    //TODO: 406 Not Acceptable
    public static ApiException  notAcceptable (String msg) {
        return new ApiException(ErrorTypes.NOT_ACCEPTABLE, errorMsg + msg);
    }

    //TODO: 409 already exists
    public static ApiException alreadyExists(String msg) {
        return new ApiException(ErrorTypes.ALREADY_EXISTS, errorMsg + msg);

    }

    //TODO: 413 Payload too large
    public static ApiException payloadTooLarge(String msg){
        return new ApiException(ErrorTypes.PAYLOAD_TOO_LARGE, errorMsg + msg);
    }

    //TODO: 429 Too many requests
    public static ApiException tooManyRequests(String msg){
        return new ApiException(ErrorTypes.TOO_MANY_REQUESTS, errorMsg + msg);
    }

    /* |----------------------|
       |     SERVER ERRORS    |
       |----------------------|
    */

    //TODO: 500 server error
    public static ApiException serverError (String msg) {
        return new ApiException(ErrorTypes.SERVER_ERROR, errorMsg + msg);
    }

    //TODO: 501 Not implemented
    public static ApiException notImplemented(String msg){
        return new ApiException(ErrorTypes.NOT_IMPLEMENTED, errorMsg + msg);
    }

    //TODO: 502 Bad Gateway
    public static ApiException badGateway(String msg){
        return new ApiException(ErrorTypes.BAD_GATEWAY, errorMsg + msg);
    }

    //TODO: 503 Service Unavailable
    public static ApiException serviceUnavailable(String msg){
        return new ApiException(ErrorTypes.SERVICE_UNAVAILABLE, errorMsg + msg);
    }

    //TODO: 504 Gateway Timeout
    public static ApiException gatewayTimeout(String msg){
        return new ApiException(ErrorTypes.GATEWAY_TIMEOUT, errorMsg + msg);
    }

    //TODO: 505 HTTP Version not supported
    public static ApiException versionNotSupported(String msg){
        return new ApiException(ErrorTypes.HTTP_VERSION_NOT_SUPPORTED, errorMsg + msg);
    }

    //TODO: 506 Variant also negotiates
    public static ApiException variantNegotiates(String msg){
        return new ApiException(ErrorTypes.VARIANT_ALSO_NEGOTIATES, errorMsg + msg);
    }

    //TODO: 507 Insufficient storage
    public static ApiException insufficientStorage(String msg){
        return new ApiException(ErrorTypes.INSUFFICIENT_STORAGE, errorMsg + msg);
    }

    //TODO: 508 Loop detected
    public static ApiException loopDetected(String msg){
        return new ApiException(ErrorTypes.LOOP_DETECTED, errorMsg + msg);
    }

    //TODO: 510 Not extended
    public static ApiException notExtended(String msg){
        return new ApiException(ErrorTypes.NOT_EXTENDED, errorMsg + msg);
    }
    
    //TODO: 511 Network Authentication Required
    public static ApiException authenticationRequired(String msg){
        return new ApiException(ErrorTypes.NETWORK_AUTHENTICATION_REQUIRED, errorMsg + msg);
    }
}


