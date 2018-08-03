package nals.hrm.api_nals_hrm.exception;

import org.springframework.http.HttpStatus;

public class ErrorDetails {
    private static final long serialVersionUID = 1L;

    private String message;
    private HttpStatus httpStatus;

    public ErrorDetails() {
    }

    public ErrorDetails(HttpStatus httpStatus, String message) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
