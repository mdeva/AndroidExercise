package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by deverajan on 12/2/18.
 */

public class Listrow {

    @SerializedName("title")
    String title;

    @SerializedName("description")
    String description;

    @SerializedName("imageHref")
    String imageHref;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }
}
