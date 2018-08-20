package nals.hrm.api_nals_hrm.dto;

import java.util.ArrayList;

public class ListDTO {
    private int total;
    private ArrayList<Object> list;

    public ListDTO() {
    }

    public ListDTO(int total, ArrayList<Object> list) {
        this.total = total;
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<Object> getList() {
        return list;
    }

    public void setList(ArrayList<Object> list) {
        this.list = list;
    }
}
