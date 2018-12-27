package org.domain.user.registration.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.domain.user.registration.domain.queries.UserQueryDTO;
import org.domain.user.registration.services.queries.UserQueryService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
@Api(value = "User Queries", description = "Read user details", tags = "User Queries")
public class UserQueryController {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(UserQueryController.class);

    @Autowired
    private UserQueryService userQueryService;

    @GetMapping(value = "/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns a specific user based on username")
    private ResponseEntity<UserQueryDTO> getUserByUserName(@ApiParam("User Name for which details should be fetched")
                    @PathVariable(value = "userName") String userName){
        logger.info("GET request received for username: " + userName);
        return new ResponseEntity<>(userQueryService.findByUserName(userName), HttpStatus.OK);
    }

    @GetMapping(value="/hello")
    @ResponseStatus(HttpStatus.OK)
    private String helloWorld(){
        return new String("Hello World");
    }
}
