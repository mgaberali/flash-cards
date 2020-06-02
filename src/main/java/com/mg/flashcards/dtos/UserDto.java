package com.mg.flashcards.dtos;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class UserDto {

    @Pattern(regexp="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
    @ApiModelProperty(example = "userx@demo.com", required = true)
    private String email;

    @ApiModelProperty(example = "password", required = true)
    private String password;
	
    @ApiModelProperty(example = "taher", required = true)
    private String firstName;
    @ApiModelProperty(example = "ayoub", required = true)
	private String lastName;
	
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
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "UserDto [email=" + email + ", password=" + password + "]";
	}
}
