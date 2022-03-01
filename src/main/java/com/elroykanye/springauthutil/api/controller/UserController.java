package com.elroykanye.springauthutil.api.controller;

import com.elroykanye.springauthutil.api.dto.UserDto;
import com.elroykanye.springauthutil.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    // GET
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable(value = "id") Long id) {
        return null;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable(value = "username") String username) {
        return null;
    }

    // POST
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return null;
    }

    // PUT
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
        return null;
    }

    // DELETE
    @PostMapping
    public ResponseEntity<UserDto> delete(@RequestParam(value = "id") Long id) {
        return null;
    }
}