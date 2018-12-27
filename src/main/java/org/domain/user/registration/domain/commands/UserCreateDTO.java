package org.domain.user.registration.domain.commands;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "User Creation Model")
public class UserCreateDTO {

    @ApiModelProperty(notes = "Unique identifier for a user", required = true)
    @NotNull
    @Size(min = 2, message = "User Name should have at least 2 characters")
    private String userName;

    @ApiModelProperty(notes = "First Name", example = "Charles", required = true)
    @NotNull
    @Size(min = 1, message = "First Name should have at least 1 character")
    private String firstName;

    @ApiModelProperty(notes = "Last Name", example = "Darwin", required = true)
    @NotNull
    @Size(min = 1, message = "Last Name should have at least 1 character")
    private String lastName;

    @ApiModelProperty(notes = "Email Id for communication", example = "charles.darwin@gmail.com", required = true)
    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    @ApiModelProperty(notes = "Only the user should know this!!", required = true)
    @NotNull
    @Size(min=8, max = 15, message = "Password should have between 8 to 15 characters")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
