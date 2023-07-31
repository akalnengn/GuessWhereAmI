package org.engina.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.engina.dto.request.ScoreUpdateRequestDto;
import org.engina.repository.entity.Score;
import org.engina.service.ScoreManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.engina.constants.EndPoints.*;

@RestController
@RequestMapping(SCORE)
@RequiredArgsConstructor
public class ScoreManagementController {
    private final ScoreManagementService service;

    @PutMapping(UPDATE)
    @Operation(summary = "Kullanıcı skor bilgilerini günceller.")
    public ResponseEntity<Score> update(@RequestBody ScoreUpdateRequestDto dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @GetMapping(LIST)
    @Operation(summary = "Kullanıcıları skorlarına göre azalan şekilde sıralar.")
    public ResponseEntity<List<Score>> listScores() {
        return ResponseEntity.ok(service.listScores());
    }
}
