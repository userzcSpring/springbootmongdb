package com.example.demo.utils;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BaseModel {
	@Id
	private String id;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date createDate;
	
	private String creator;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date updateDate;
	
	private String reviser;
	
	public BaseModel() {
    	if(this.id==null) {
    		this.createDate=new Date();
    	}
    	if (this.creator==null) {
    		this.creator = "0";
    	}
    	if (this.reviser==null && this.id!=null) {
    		this.reviser = "0";
    	}
    }
}
