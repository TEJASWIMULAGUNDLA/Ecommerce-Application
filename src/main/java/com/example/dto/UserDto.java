package com.example.dto;

public class UserDto {
private Integer userid;
public UserDto() {
	super();
}
public UserDto(Integer userid, Integer name, Integer email, String password, String phonenumber) {
	super();
	this.userid = userid;
	this.name = name;
	this.email = email;
	this.password = password;
	this.phonenumber = phonenumber;
}
public Integer getUserid() {
	return userid;
}
public void setUserid(Integer userid) {
	this.userid = userid;
}
public Integer getName() {
	return name;
}
public void setName(Integer name) {
	this.name = name;
}
public Integer getEmail() {
	return email;
}
public void setEmail(Integer email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getPhonenumber() {
	return phonenumber;
}
public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}
private Integer name;
private Integer email;
private String password;
private String phonenumber;
}
