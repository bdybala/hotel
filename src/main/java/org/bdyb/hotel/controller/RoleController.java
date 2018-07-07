package org.bdyb.hotel.controller;

import lombok.RequiredArgsConstructor;
import org.bdyb.hotel.enums.RoleNameEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {

    @GetMapping
    public ResponseEntity<List> getAllRoles() {
        return new ResponseEntity<>(Arrays.asList(RoleNameEnum.values()), HttpStatus.OK);
    }
}
