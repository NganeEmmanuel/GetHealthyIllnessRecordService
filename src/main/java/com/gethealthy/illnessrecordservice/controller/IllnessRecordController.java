package com.gethealthy.illnessrecordservice.controller;

import com.gethealthy.illnessrecordservice.model.DeleteRequest;
import com.gethealthy.illnessrecordservice.model.IllnessRecordDTO;
import com.gethealthy.illnessrecordservice.model.SearchRequest;
import com.gethealthy.illnessrecordservice.service.IllnessRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1/illnesses")
public class IllnessRecordController {
    private final IllnessRecordService illnessRecordService;

    @PostMapping("/illness/add")
    public ResponseEntity<IllnessRecordDTO> addIllnessRecord(@RequestBody IllnessRecordDTO illnessRecordDTO,
                                                             @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        return ResponseEntity.ok(illnessRecordService.addIllnessRecord(illnessRecordDTO, authorizationHeader));
    }

    @GetMapping("/illness/get-with-id")
    public ResponseEntity<IllnessRecordDTO> getIllnessRecordByID(@RequestParam("id") Long id){
        return ResponseEntity.ok(illnessRecordService.getIllnessRecord(id));
    }


    @GetMapping("/get-with-userid")
    public ResponseEntity<List<IllnessRecordDTO>> getAllIllnessRecordsByUserID(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(illnessRecordService.getAllIllnessRecordsByUserId(userId));
    }

    @GetMapping("/get-with-logged-in-user")
    public ResponseEntity<List<IllnessRecordDTO>> getAllIllnessRecordsByLoggedInUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        return ResponseEntity.ok(illnessRecordService.getAllIllnessRecordsByLoggedInUser(authorizationHeader));
    }

    @PostMapping("/search")
    public ResponseEntity<List<IllnessRecordDTO>> getRecordsBySearch(@RequestParam String term, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        return ResponseEntity.ok(illnessRecordService.getRecordsBySearch(term, authorizationHeader));
    }

    @PutMapping("/illness/update")
    public ResponseEntity<IllnessRecordDTO> updateIllnessRecord(@RequestBody IllnessRecordDTO illnessRecordDTO){
        return ResponseEntity.ok(illnessRecordService.update(illnessRecordDTO));
    }

    @DeleteMapping("/illness/delete")
    public ResponseEntity<Boolean> deleteIllnessRecord(@RequestBody DeleteRequest deleteRequest){
        return ResponseEntity.ok(illnessRecordService.deleteIllnessRecord(deleteRequest));
    }

}
