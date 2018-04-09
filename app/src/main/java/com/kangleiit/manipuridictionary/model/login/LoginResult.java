package com.kangleiit.manipuridictionary.model.login;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class LoginResult{
  @SerializedName("result")
  @Expose
  private List<Result> result;
  @SerializedName("success")
  @Expose
  private Boolean success;
  public void setResult(List<Result> result){
   this.result=result;
  }
  public List<Result> getResult(){
   return result;
  }
  public void setSuccess(Boolean success){
   this.success=success;
  }
  public Boolean getSuccess(){
   return success;
  }
}