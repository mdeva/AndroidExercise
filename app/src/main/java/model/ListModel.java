package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by deverajan on 12/2/18.
 */

public class ListModel {

    @SerializedName("title")
    String title;

    @SerializedName("rows")
    List<Listrow> listrows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Listrow> getListrows() {
        return listrows;
    }

    public void setListrows(List<Listrow> listrows) {
        this.listrows = listrows;
    }
}
