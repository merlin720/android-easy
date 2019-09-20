package com.easy.easycan.home.bean;

/**
 * @author merlin720
 * @date 2019-09-20
 * @mail zy44638@gmail.com
 * @description
 */
public class NewsTitleBean {

  private String id;

  private String name;
  private String code;
  private String seo_list_title;
  private NewsTitleChannel channel;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getSeo_list_title() {
    return seo_list_title;
  }

  public void setSeo_list_title(String seo_list_title) {
    this.seo_list_title = seo_list_title;
  }

  public NewsTitleChannel getChannel() {
    return channel;
  }

  public void setChannel(NewsTitleChannel channel) {
    this.channel = channel;
  }
}
