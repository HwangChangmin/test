package com.dycgv.vo;

public class JoinVO {
	//email3 ºÒÇÊ¿ä
	String id,name,pass,cpass,gender,email1,email2,email3,addr,
		   phone_comp,phone_number1,phone_number2,phone_number3,intro;
	String[] hobby;

	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getCpass() {
		return cpass;
	}
	public void setCpass(String cpass) {
		this.cpass = cpass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getEmail3() {
		return email3;
	}
	public void setEmail3(String email3) {
		this.email3 = email3;
	}
	public String getEmail() {
		String email = email1+"@"+email2;
		
		return email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone_comp() {
		return phone_comp;
	}
	public void setPhone_comp(String phone_comp) {
		this.phone_comp = phone_comp;
	}
	public String getPhone_number1() {
		return phone_number1;
	}
	public void setPhone_number1(String phone_number1) {
		this.phone_number1 = phone_number1;
	}
	public String getPhone_number2() {
		return phone_number2;
	}
	public void setPhone_number2(String phone_number2) {
		this.phone_number2 = phone_number2;
	}
	public String getPhone_number3() {
		return phone_number3;
	}
	public void setPhone_number3(String phone_number3) {
		this.phone_number3 = phone_number3;
	}
	public String getPhone_number() {
		String phone_number = phone_number1+"-"+phone_number2+"-"+phone_number3;
		
		return phone_number;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getHobbyList() {
		String hobbyList = "";
		for(int i=0;i<hobby.length;i++) {
			hobbyList += hobby[i];
			if(i+1 != hobby.length) {
				hobbyList += ", ";
			}
		}
		
		return hobbyList;
	}
	
}
