package org.engina.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.engina.dto.request.CityRequestDto;
import org.engina.repository.entity.City;
import org.engina.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.engina.constants.EndPoints.*;
@RestController
@RequestMapping(CITY)
@RequiredArgsConstructor
public class CityController {
    private final CityService service;

    @PostMapping(SAVE)
    @Operation(summary = "Bir citydb'ye bir şehir kaydı oluşturur oluşturur ve aynı zamanda guessdb'ye bir şehir sorusu kaydı oluşturur.")
    public ResponseEntity<String> saveCity(@RequestBody CityRequestDto dto) {
        return ResponseEntity.ok(service.saveCity(dto));
    }
    @GetMapping(GET)
    @Operation(summary = "İd'si sorgulanan şehri @Pathvariable anotasyonu ile getirir.")
    public ResponseEntity<Optional<City>> getCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(service.getCity(cityId));
    }
}
