package com.gethealthy.illnessrecordservice.controller;

import com.gethealthy.illnessrecordservice.model.DeleteRequest;
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

    @GetMapping("get/records")
    public ResponseEntity<List<IllnessRecordDTO>> getAllIllnessRecordsByUserId(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(illnessRecordService.getAllIllnessRecordsByUserId(userId));
    }

    @GetMapping("search")
    public ResponseEntity<List<IllnessRecordDTO>> getRecordsBySearch(@RequestParam("search") String search){
        return ResponseEntity.ok(illnessRecordService.getRecordsBySearch(search));
    }

    @PutMapping("update")
    public ResponseEntity<IllnessRecordDTO> updateIllnessRecord(@RequestBody IllnessRecordDTO illnessRecordDTO){
        return ResponseEntity.ok(illnessRecordService.update(illnessRecordDTO));
    }

    @DeleteMapping("delete")
    public ResponseEntity<Boolean> deleteIllnessRecord(@RequestBody DeleteRequest deleteRequest){
        return ResponseEntity.ok(illnessRecordService.deleteIllnessRecord(deleteRequest));
    }

}
