package com.gethealthy.illnessrecordservice.service;

import com.gethealthy.illnessrecordservice.exception.RecordNotFoundException;
import com.gethealthy.illnessrecordservice.feign.AuthenticationInterface;
import com.gethealthy.illnessrecordservice.feign.EventInterface;
import com.gethealthy.illnessrecordservice.model.IllnessRecordDTO;
import com.gethealthy.illnessrecordservice.repository.IllnessRecordRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IllnessRecordServiceImpl implements IllnessRecordService{
    private final IllnessRecordRepository illnessRecordRepository;
    private final IllnessMapperService mapperService;
    private static final Logger logger = LoggerFactory.getLogger(IllnessRecordServiceImpl.class);
    private final EventInterface eventInterface;
    private final AuthenticationInterface authenticationInterface;

    @Override
    public ResponseEntity<IllnessRecordDTO> addIllnessRecord(IllnessRecordDTO illnessRecordDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        try {
            var illnessRecord = mapperService.toEntity(illnessRecordDTO);

            // Pass the Authorization header to Feign client to get the logged-in user ID
            var response = authenticationInterface.getLoggedInUserId(authorizationHeader);
            illnessRecord.setUserID(response.getBody());

            return ResponseEntity.ok(mapperService.toDTO(illnessRecordRepository.save(illnessRecord)));
        } catch (Exception e) {
            logger.info("Error adding illness record with data: {}", illnessRecordDTO);
            throw new RuntimeException(e);
        }
    }


    @Override
    public ResponseEntity<IllnessRecordDTO> getIllnessRecord(Long id) throws RecordNotFoundException {
        try {
            return ResponseEntity.ok(mapperService.toDTO(illnessRecordRepository.findById(id).orElseThrow(
                    () -> new RecordNotFoundException(id)
            )));
        }catch (Exception e){
            logger.info("Error retrieving illness record with data: {}", id);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<List<IllnessRecordDTO>> getAllIllnessRecordsByUserId(Long userID) throws RecordNotFoundException {
        try {
            var illnessDTOs = new ArrayList<IllnessRecordDTO>();
            var illnessRecords = illnessRecordRepository.findAllByUserID(userID).orElseThrow(
                    () -> new RecordNotFoundException(userID)
            );

            illnessRecords.forEach(record -> illnessDTOs.add(mapperService.toDTO(record)));
            return ResponseEntity.ok(illnessDTOs);
        }catch (Exception e){
            logger.info("Error retrieving illness record associated with userID: {}", userID);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<List<IllnessRecordDTO>> getRecordsBySearch(String term, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws RecordNotFoundException {
        try {
            //get userID from header request
            var userID = authenticationInterface.getLoggedInUserId(authorizationHeader).getBody();
            var illnessRecordDTOS = new ArrayList<IllnessRecordDTO>();

            //check if the record matching the recordId is associated with the logged i userID
            var illnessRecords = illnessRecordRepository.searchRecords(term, userID)
                    .orElseThrow(
                        () -> new RecordNotFoundException(term, userID)
                    );

            illnessRecords.forEach(record -> illnessRecordDTOS.add(mapperService.toDTO(record)));

            return ResponseEntity.ok(illnessRecordDTOS);
           }catch (Exception e){
            logger.info("Error retrieving illness record matching: {} and the current logged in user from authorizationHeader: {}", term, authorizationHeader);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<IllnessRecordDTO> update(IllnessRecordDTO illnessRecordDTO, String authorizationHeader) throws RecordNotFoundException {
        try {
            var userID = authenticationInterface.getLoggedInUserId(authorizationHeader).getBody();
            var illnessRecord = illnessRecordRepository.findByIdAndUserID(illnessRecordDTO.getId(), userID).orElseThrow(
                    () -> new RecordNotFoundException(illnessRecordDTO.getId(), userID)
            );
            mapperService.updateEntity(illnessRecordDTO, illnessRecord);
            return ResponseEntity.ok(mapperService.toDTO(illnessRecordRepository.save(illnessRecord)));
        }catch (Exception e){
            logger.info("Error updating illness record with data: {}", illnessRecordDTO);
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<Boolean> deleteIllnessRecord(Long id, String authorizationHeader) throws RecordNotFoundException {
        try {
            var userID = authenticationInterface.getLoggedInUserId(authorizationHeader).getBody();
            illnessRecordRepository.deleteByIdAndUserID(id, userID);
            eventInterface.deleteAllEventsByRecordID(id, authorizationHeader);
            return ResponseEntity.ok(Boolean.TRUE);
        } catch (Exception e){
            logger.info("Error deleting illness record with id: {} and associated with logged in user fom header{}", id, authorizationHeader);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<List<IllnessRecordDTO>> getAllIllnessRecordsByLoggedInUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws RecordNotFoundException {
        var user = authenticationInterface.getLoggedInUserId(authorizationHeader);
        return  getAllIllnessRecordsByUserId(user.getBody());
    }
}
