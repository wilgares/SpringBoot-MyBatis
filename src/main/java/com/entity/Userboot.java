package com.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Userboot {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Size(min = 2, max =50)
    @Pattern(regexp = "[A-Za-z. ]*", message = "First name requires valid character")
    private String firstname;

    @Size(min = 2, max =50)
    @Pattern(regexp = "[A-Za-z. ]*", message = "First name requires valid character")
    private String lastname;


    //@Column(unique = true)
    @NotNull(message =  "Email requires valid value")
    @NotEmpty(message = "Email requires non empty value")
    @Email(message =    "Email requires valid format")
    private String email;

    @Size(min = 2, max =50)
    @Pattern(regexp = "[A-Za-z.0-9 ]*", message = "First name requires valid character")
    private String password;

    @Pattern(regexp = "[0-9.\\-+ ]*", message = "Phone requires valid alphanumaric characters")
    private String phone;

    private int age;
    /*setter getter method here*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


    
}
