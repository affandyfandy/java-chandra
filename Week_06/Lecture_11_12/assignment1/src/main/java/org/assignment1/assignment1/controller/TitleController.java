package org.assignment1.assignment1.controller;

import org.assignment1.assignment1.dto.TitleDTO;
import org.assignment1.assignment1.service.TitleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/titles")
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping
    public ResponseEntity<List<TitleDTO>> getAllTitles() {
        return ResponseEntity.ok(titleService.getAllTitles());
    }

    @PostMapping
    public ResponseEntity<TitleDTO> saveTitle(@RequestBody TitleDTO titleDTO) {
        return ResponseEntity.ok(titleService.saveTitle(titleDTO));
    }

    @PutMapping
    public ResponseEntity<TitleDTO> updateTitle(@RequestBody TitleDTO titleDTO) {
        return ResponseEntity.ok(titleService.updateTitle(titleDTO));
    }
}