package nals.hrm.api_nals_hrm.exception;

public class CustomException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final String message;
  private final int httpStatus;

  public CustomException(String message, int httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public int getHttpStatus() {
    return httpStatus;
  }

}
