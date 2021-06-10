package edu.cnm.deepdive.animals.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;




public class Animal {
  @Expose
  private String name;

  @SerializedName("image")

  private String  imageURL;

    public String getName() {
    return name;
  }

    public void setName(String name) {
    this.name = name;
  }

    public String getImageURL() {
    return imageURL;
  }

    public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

}
