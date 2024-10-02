package com.gethealthy.illnessrecordservice.controller;

import com.gethealthy.illnessrecordservice.model.IllnessRecordDTO;
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
        return illnessRecordService.addIllnessRecord(illnessRecordDTO, authorizationHeader);
    }

    @GetMapping("/illness/get-with-id")
    public ResponseEntity<IllnessRecordDTO> getIllnessRecordByID(@RequestParam("id") Long id){
        return illnessRecordService.getIllnessRecord(id);
    }


    @GetMapping("/get-with-userid")
    public ResponseEntity<List<IllnessRecordDTO>> getAllIllnessRecordsByUserID(@RequestParam("userId") Long userId){
        return illnessRecordService.getAllIllnessRecordsByUserId(userId);
    }

    @GetMapping("/get-with-logged-in-user")
    public ResponseEntity<List<IllnessRecordDTO>> getAllIllnessRecordsByLoggedInUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        return illnessRecordService.getAllIllnessRecordsByLoggedInUser(authorizationHeader);
    }

    @PostMapping("/search")
    public ResponseEntity<List<IllnessRecordDTO>> SearchRecords(@RequestParam String term, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        return illnessRecordService.getRecordsBySearch(term, authorizationHeader);
    }

    @PutMapping("/illness/update")
    public ResponseEntity<IllnessRecordDTO> updateIllnessRecord(@RequestBody IllnessRecordDTO illnessRecordDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        return illnessRecordService.update(illnessRecordDTO, authorizationHeader);
    }

    @DeleteMapping("/illness/delete")
    public ResponseEntity<Boolean> deleteIllnessRecord(@RequestParam Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        return illnessRecordService.deleteIllnessRecord(id, authorizationHeader);
    }

}
