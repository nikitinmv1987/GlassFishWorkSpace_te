package com.corejsf;

import java.io.Serializable;
import javax.inject.Named; 
   // or import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped; 
   // or import javax.faces.bean.SessionScoped;
 
@SuppressWarnings("serial")
@Named("user") // or @ManagedBean(name="user")  
@SessionScoped     
public class User implements Serializable {
  private String name;
  private String password;
  
  public String getName() { return name; }
  public void setName(String newValue) { 
	  name = newValue;
      System.out.println("blah");
      System.out.println(name);
      
	  }
    
  public String getPassword() { return password; }
  public void setPassword(String newValue) { password = newValue; }  
}
