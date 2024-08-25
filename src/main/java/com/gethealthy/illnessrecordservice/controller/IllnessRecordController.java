package com.gethealthy.illnessrecordservice.controller;

import com.gethealthy.illnessrecordservice.model.IllnessRecordDTO;
import com.gethealthy.illnessrecordservice.service.IllnessRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
