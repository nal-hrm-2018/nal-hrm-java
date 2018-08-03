package nals.hrm.api_nals_hrm.dto;

import org.springframework.http.HttpStatus;

public class LoginResponseDTO {
    private HttpStatus result_code;
    private String result_message;
    private Object data;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(HttpStatus result_code, String result_message, Object data) {
        this.result_code = result_code;
        this.result_message = result_message;
        this.data = data;
    }

    public HttpStatus getResult_code() {
        return result_code;
    }

    public void setResult_code(HttpStatus result_code) {
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
