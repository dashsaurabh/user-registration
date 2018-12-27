package org.domain.user.registration.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.domain.user.registration.domain.commands.UserCreateDTO;
import org.domain.user.registration.domain.commands.UserUpdateDTO;
import org.domain.user.registration.events.UserSource;
import org.domain.user.registration.services.commands.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/api/users")
@Api(value = "User Commands", description = "Create, Update or Delete a User", tags = "User Commands")
@EnableBinding(UserSource.class)
public class UserCommandController {

    @Autowired
    private UserCommandService userCommandService;

    @Autowired
    private UserSource userSource;

    @PostMapping
    @ApiOperation("Registers a new user")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<String> createNewUser(@Valid @RequestBody UserCreateDTO userCreateDTO){
        return new ResponseEntity<>("New User Created: " + userCommandService.createUser(userCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userName}")
    @ApiOperation("Updates a user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private ResponseEntity<String> updateUser(@PathVariable(name = "userName") String userName, @Valid @RequestBody UserUpdateDTO userUpdateDTO){
        return new ResponseEntity<>("User Updated: " + userCommandService.updateUser(userName, userUpdateDTO), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/{userName}/passwords")
    @ApiOperation("Updates password of user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private ResponseEntity<String> updatePassword(@PathVariable(name = "userName") String userName, @Valid @RequestBody @NotNull String newPassword){
        return new ResponseEntity<>("Password Updated for User: " + userCommandService.updatePassword(userName, newPassword), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{userName}")
    @ApiOperation("Deletes a user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private ResponseEntity<String> deletePassword(@PathVariable(name = "userName") String userName){
        return new ResponseEntity<>("User Deleted: " + userCommandService.deleteUser(userName), HttpStatus.ACCEPTED);
    }

}
