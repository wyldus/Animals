package edu.cnm.deepdive.animals.model;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;


public class Animal {

  @Expose
  private String name;


  @Expose
  @SerializedName("image")

  private String imageURL;

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

  @NonNull
  @Override
  public String toString() {
    return name;
  }


}
