package com.techzen.techlearn.controller;

import com.techzen.techlearn.dto.request.PointRequestDTO;
import com.techzen.techlearn.service.impl.PointServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/points")
public class PointController {
    PointServiceImpl pointService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(required = false, defaultValue = "1") int page,
                                     @RequestParam(required = false, defaultValue = "2") int pageSize) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pointService.findAllPoints(page, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPointsById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pointService.findPointsById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid PointRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pointService.createPoints(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody PointRequestDTO pointRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pointService.updatePoint(id, pointRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePoints(@PathVariable Integer id) {
        pointService.deletePointsById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Deleted point by id " + id);
    }
}
