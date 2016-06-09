package com.tootors.tootors.client.model;

import java.util.Date;

import io.swagger.annotations.*;
import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class Tootor  {
  
  @SerializedName("id")
  private Long id = null;
  @SerializedName("is_tootor")
  private Boolean isTootor = null;
  @SerializedName("username")
  private String username = null;
  @SerializedName("seo_name")
  private String seoName = null;
  @SerializedName("email")
  private String email = null;
  @SerializedName("password")
  private String password = null;
  @SerializedName("name")
  private String name = null;
  @SerializedName("phone")
  private String phone = null;
  @SerializedName("price")
  private Double price = null;
  @SerializedName("street")
  private String street = null;
  @SerializedName("city")
  private String city = null;
  @SerializedName("state")
  private String state = null;
  @SerializedName("zip")
  private String zip = null;
  @SerializedName("focus")
  private String focus = null;
  @SerializedName("description")
  private String description = null;
  @SerializedName("picture")
  private String picture = null;
  @SerializedName("video")
  private String video = null;
  @SerializedName("created_at")
  private Date createdAt = null;
  @SerializedName("updated_at")
  private Date updatedAt = null;
  @SerializedName("visited_at")
  private Date visitedAt = null;

  /**
   **/
  @ApiModelProperty(value = "")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Boolean getIsTootor() {
    return isTootor;
  }
  public void setIsTootor(Boolean isTootor) {
    this.isTootor = isTootor;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getSeoName() {
    return seoName;
  }
  public void setSeoName(String seoName) {
    this.seoName = seoName;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Double getPrice() {
    return price;
  }
  public void setPrice(Double price) {
    this.price = price;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getStreet() {
    return street;
  }
  public void setStreet(String street) {
    this.street = street;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getZip() {
    return zip;
  }
  public void setZip(String zip) {
    this.zip = zip;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getFocus() {
    return focus;
  }
  public void setFocus(String focus) {
    this.focus = focus;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getPicture() {
    return picture;
  }
  public void setPicture(String picture) {
    this.picture = picture;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public String getVideo() {
    return video;
  }
  public void setVideo(String video) {
    this.video = video;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Date getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Date getUpdatedAt() {
    return updatedAt;
  }
  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  /**
   **/
  @ApiModelProperty(value = "")
  public Date getVisitedAt() {
    return visitedAt;
  }
  public void setVisitedAt(Date visitedAt) {
    this.visitedAt = visitedAt;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tootor tootor = (Tootor) o;
    return (id == null ? tootor.id == null : id.equals(tootor.id)) &&
        (isTootor == null ? tootor.isTootor == null : isTootor.equals(tootor.isTootor)) &&
        (username == null ? tootor.username == null : username.equals(tootor.username)) &&
        (seoName == null ? tootor.seoName == null : seoName.equals(tootor.seoName)) &&
        (email == null ? tootor.email == null : email.equals(tootor.email)) &&
        (password == null ? tootor.password == null : password.equals(tootor.password)) &&
        (name == null ? tootor.name == null : name.equals(tootor.name)) &&
        (phone == null ? tootor.phone == null : phone.equals(tootor.phone)) &&
        (price == null ? tootor.price == null : price.equals(tootor.price)) &&
        (street == null ? tootor.street == null : street.equals(tootor.street)) &&
        (city == null ? tootor.city == null : city.equals(tootor.city)) &&
        (state == null ? tootor.state == null : state.equals(tootor.state)) &&
        (zip == null ? tootor.zip == null : zip.equals(tootor.zip)) &&
        (focus == null ? tootor.focus == null : focus.equals(tootor.focus)) &&
        (description == null ? tootor.description == null : description.equals(tootor.description)) &&
        (picture == null ? tootor.picture == null : picture.equals(tootor.picture)) &&
        (video == null ? tootor.video == null : video.equals(tootor.video)) &&
        (createdAt == null ? tootor.createdAt == null : createdAt.equals(tootor.createdAt)) &&
        (updatedAt == null ? tootor.updatedAt == null : updatedAt.equals(tootor.updatedAt)) &&
        (visitedAt == null ? tootor.visitedAt == null : visitedAt.equals(tootor.visitedAt));
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (id == null ? 0: id.hashCode());
    result = 31 * result + (isTootor == null ? 0: isTootor.hashCode());
    result = 31 * result + (username == null ? 0: username.hashCode());
    result = 31 * result + (seoName == null ? 0: seoName.hashCode());
    result = 31 * result + (email == null ? 0: email.hashCode());
    result = 31 * result + (password == null ? 0: password.hashCode());
    result = 31 * result + (name == null ? 0: name.hashCode());
    result = 31 * result + (phone == null ? 0: phone.hashCode());
    result = 31 * result + (price == null ? 0: price.hashCode());
    result = 31 * result + (street == null ? 0: street.hashCode());
    result = 31 * result + (city == null ? 0: city.hashCode());
    result = 31 * result + (state == null ? 0: state.hashCode());
    result = 31 * result + (zip == null ? 0: zip.hashCode());
    result = 31 * result + (focus == null ? 0: focus.hashCode());
    result = 31 * result + (description == null ? 0: description.hashCode());
    result = 31 * result + (picture == null ? 0: picture.hashCode());
    result = 31 * result + (video == null ? 0: video.hashCode());
    result = 31 * result + (createdAt == null ? 0: createdAt.hashCode());
    result = 31 * result + (updatedAt == null ? 0: updatedAt.hashCode());
    result = 31 * result + (visitedAt == null ? 0: visitedAt.hashCode());
    return result;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tootor {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  isTootor: ").append(isTootor).append("\n");
    sb.append("  username: ").append(username).append("\n");
    sb.append("  seoName: ").append(seoName).append("\n");
    sb.append("  email: ").append(email).append("\n");
    sb.append("  password: ").append(password).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  phone: ").append(phone).append("\n");
    sb.append("  price: ").append(price).append("\n");
    sb.append("  street: ").append(street).append("\n");
    sb.append("  city: ").append(city).append("\n");
    sb.append("  state: ").append(state).append("\n");
    sb.append("  zip: ").append(zip).append("\n");
    sb.append("  focus: ").append(focus).append("\n");
    sb.append("  description: ").append(description).append("\n");
    sb.append("  picture: ").append(picture).append("\n");
    sb.append("  video: ").append(video).append("\n");
    sb.append("  createdAt: ").append(createdAt).append("\n");
    sb.append("  updatedAt: ").append(updatedAt).append("\n");
    sb.append("  visitedAt: ").append(visitedAt).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
