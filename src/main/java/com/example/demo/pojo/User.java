package com.example.demo.pojo;

import org.springframework.data.mongodb.core.mapping.Document;
import com.example.demo.utils.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Document(collection = "user")
public class User extends BaseModel {
	private String name;
	
	private String account;

    private Integer age;
    
    private String password;

    private String description;
}
