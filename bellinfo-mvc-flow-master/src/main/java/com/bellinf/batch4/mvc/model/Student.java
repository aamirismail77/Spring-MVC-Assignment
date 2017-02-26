package com.bellinf.batch4.mvc.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.bellinf.batch4.mvc.model.DateAndTime;


public class Student {
	
	@Size(min=4,max=15) 
	@NotNull 
	private String name;
	@NotNull
	private String course;
	@Min(100)
	private int fee;
	
	private  String dt;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getDt() {
		DateAndTime	dt = new DateAndTime();
		return dt.currentdatetime();
	}

	@Override
	
	public String toString() {
		return "Student [name=" + name + ", course=" + course + ", fee=" + fee + ", dt=" + dt + "]";
	}

}
