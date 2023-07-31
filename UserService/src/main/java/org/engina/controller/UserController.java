package org.engina.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.engina.dto.request.DoLoginRequestDto;
import org.engina.dto.request.SaveUserRequestDto;
import org.engina.repository.entity.User;
import org.engina.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.engina.constants.EndPoints.*;
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    @PostMapping(SAVE)
    @Operation(summary = "Kullanıcı kaydeder.")
    public ResponseEntity<String> saveUserDto(@RequestBody SaveUserRequestDto dto) {
        return ResponseEntity.ok(service.saveUserDto(dto));
    }

    @PostMapping(LOGIN)
    @Operation(summary = "Kullanıcı giriş yapar.")
    public ResponseEntity<String> doLogin(@RequestBody DoLoginRequestDto dto) {
        return ResponseEntity.ok(service.doLogin(dto));
    }

    @GetMapping(LIST)
    @Operation(summary = "Kullanıcıları listeler.")
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(service.findAll());
    }
}
