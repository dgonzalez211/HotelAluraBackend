package org.diegodev.hotelalurabackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    protected ResponseEntity<Map<String, String>> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "Field " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
