package org.domain.user.registration.domain.commands;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "User Update Model")
public class UserUpdateDTO {

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
}
