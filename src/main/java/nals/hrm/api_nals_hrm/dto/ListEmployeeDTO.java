package nals.hrm.api_nals_hrm.dto;

import java.util.ArrayList;

public class ListEmployeeDTO {
    private int total;
    private ArrayList<ProfileDTO> list;

    public ListEmployeeDTO() {
    }

    public ListEmployeeDTO(int total, ArrayList<ProfileDTO> list) {
        this.total = total;
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<ProfileDTO> getList() {
        return list;
    }

    public void setList(ArrayList<ProfileDTO> list) {
        this.list = list;
    }
}
