package com.gethealthy.illnessrecordservice.controller;

import com.gethealthy.illnessrecordservice.model.IllnessRecordDTO;
import com.gethealthy.illnessrecordservice.service.IllnessRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1/illness")
public class IllnessRecordController {
    private final IllnessRecordService illnessRecordService;

    @PostMapping("/add")
    public ResponseEntity<IllnessRecordDTO> addIllnessRecord(@RequestBody IllnessRecordDTO illnessRecordDTO){
        return ResponseEntity.ok(illnessRecordService.addIllnessRecord(illnessRecordDTO));
    }

    @GetMapping("/get/record")
    public ResponseEntity<IllnessRecordDTO> getIllnessRecord(@RequestParam("id") Long id){
        return ResponseEntity.ok(illnessRecordService.getIllnessRecord(id));
    }

    public ResponseEntity<List<IllnessRecordDTO>> getAllIllnessRecordsByUserId(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(illnessRecordService.getAllIllnessRecordsByUserId(userId));
    }

}
