package nals.hrm.api_nals_hrm.dto;


public class APIResponseDTO {
  private int result_code;
  private String result_message;
  private Object data;

  public APIResponseDTO() {
  }

  public APIResponseDTO(int result_code, String result_message, Object data) {
    this.result_code = result_code;
    this.result_message = result_message;
    this.data = data;
  }

  public int getResult_code() {
    return result_code;
  }

  public void setResult_code(int result_code) {
    this.result_code = result_code;
  }

  public String getResult_message() {
    return result_message;
  }

  public void setResult_message(String result_message) {
    this.result_message = result_message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
