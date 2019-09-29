package com.easy.easycan.home.stramer.bean;

/**
 * @author merlin720
 * @date 2019-09-20
 * @mail zy44638@gmail.com
 * @description
 */
public class StreamerInfoBean {
  private String id;
  private String city_id;
  private String title;
  private String address;
  private String image;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getCity_id() {
    return city_id;
  }

  public void setCity_id(String city_id) {
    this.city_id = city_id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
