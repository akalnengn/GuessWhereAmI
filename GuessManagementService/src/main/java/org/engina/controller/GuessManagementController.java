package org.engina.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.engina.service.GuessManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.engina.constants.EndPoints.*;
@RestController
@RequestMapping(GUESS)
@RequiredArgsConstructor
public class GuessManagementController {
    private final GuessManagementService service;

    @GetMapping(CHOOSEQUESTION)
    @Operation(summary = "Id'si sorgulanan soruyla alakalı hint'i ve blur'lu resmi getirir.")
    public ResponseEntity<String> chooseQuestion(@RequestParam Long questionId) {
        return ResponseEntity.ok(service.chooseQuestion(questionId));
    }

    @PostMapping(DO_GUESS)
    @Operation(summary = "id:Kullanıcının cevaplamak istediği soru,userid:soruyu cevaplayan user,answer:kullanıcının cevabı.Geriye string döner.")
    public ResponseEntity<String> doGuess(@RequestParam Long id,@RequestParam Long userid,@RequestParam String answer) {
        return ResponseEntity.ok(service.doGuess(id, userid, answer));
    }
}
