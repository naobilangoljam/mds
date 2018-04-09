package com.kangleiit.manipuridictionary.model.login;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Result{
  @SerializedName("access_token")
  @Expose
  private String access_token;
  @SerializedName("google_id")
  @Expose
  private Integer google_id;
  @SerializedName("nos_word_approved")
  @Expose
  private Integer nos_word_approved;
  @SerializedName("user_id")
  @Expose
  private String user_id;
  @SerializedName("nos_word_rejected")
  @Expose
  private Integer nos_word_rejected;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("email")
  @Expose
  private String email;
  @SerializedName("nos_word_submitted")
  @Expose
  private Integer nos_word_submitted;
  public void setAccess_token(String access_token){
   this.access_token=access_token;
  }
  public String getAccess_token(){
   return access_token;
  }
  public void setGoogle_id(Integer google_id){
   this.google_id=google_id;
  }
  public Integer getGoogle_id(){
   return google_id;
  }
  public void setNos_word_approved(Integer nos_word_approved){
   this.nos_word_approved=nos_word_approved;
  }
  public Integer getNos_word_approved(){
   return nos_word_approved;
  }
  public void setUser_id(String user_id){
   this.user_id=user_id;
  }
  public String getUser_id(){
   return user_id;
  }
  public void setNos_word_rejected(Integer nos_word_rejected){
   this.nos_word_rejected=nos_word_rejected;
  }
  public Integer getNos_word_rejected(){
   return nos_word_rejected;
  }
  public void setName(String name){
   this.name=name;
  }
  public String getName(){
   return name;
  }
  public void setEmail(String email){
   this.email=email;
  }
  public String getEmail(){
   return email;
  }
  public void setNos_word_submitted(Integer nos_word_submitted){
   this.nos_word_submitted=nos_word_submitted;
  }
  public Integer getNos_word_submitted(){
   return nos_word_submitted;
  }
}