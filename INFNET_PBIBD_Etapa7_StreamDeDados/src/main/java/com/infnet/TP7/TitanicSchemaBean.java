package com.infnet.TP7;

import java.io.Serializable;

import scala.annotation.meta.setter;

//Questão 04 - Implementando um JavaBean para serializar objetos com o esquema de dados definido
public class TitanicSchemaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long PassengerId;
	private long Survived;
	private long Pclass;
	private String Sex;
	private String Cabin;
	private String Embarked;
	//	private String Name;	
	private long Age;
	private long SibSp;
	private long Parch;
	private long Ticket;
	private long Fare;
	
	public TitanicSchemaBean() {
		
	}
	


	public String getCabin() {
		return Cabin;
	}


	public void setCabin(String cabin) {
		Cabin = cabin;
	}


	public String getEmbarked() {
		return Embarked;
	}


	public void setEmbarked(String embarked) {
		Embarked = embarked;
	}


	public String getSex() {
		return Sex;
	}


	public void setSex(String sex) {
		Sex = sex;
	}


	public long getPassengerId() {
		return PassengerId;
	}


	public void setPassengerId(long passengerId) {
		PassengerId = passengerId;
	}


	public long getSurvived() {
		return Survived;
	}


	public void setSurvived(long survived) {
		Survived = survived;
	}


	public long getPclass() {
		return Pclass;
	}


	public void setPclass(long pclass) {
		Pclass = pclass;
	}


	public long getAge() {
		return Age;
	}


	public void setAge(long age) {
		Age = age;
	}


	public long getSibSp() {
		return SibSp;
	}


	public void setSibSp(long sibSp) {
		SibSp = sibSp;
	}


	public long getParch() {
		return Parch;
	}


	public void setParch(long parch) {
		Parch = parch;
	}


	public long getTicket() {
		return Ticket;
	}


	public void setTicket(long ticket) {
		Ticket = ticket;
	}


	public long getFare() {
		return Fare;
	}


	public void setFare(long fare) {
		Fare = fare;
	}



//	public String getName() {
//		return Name;
//	}
//
//
//
//	public void setName(String name) {
//		Name = name;
//	}

	
	
	
	
	
	
}
