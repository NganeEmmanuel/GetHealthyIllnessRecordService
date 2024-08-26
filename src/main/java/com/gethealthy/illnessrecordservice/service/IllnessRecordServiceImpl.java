package com.gethealthy.illnessrecordservice.service;

import com.gethealthy.illnessrecordservice.exception.RecordNotFoundException;
import com.gethealthy.illnessrecordservice.model.DeleteRequest;
import com.gethealthy.illnessrecordservice.model.IllnessRecordDTO;
import com.gethealthy.illnessrecordservice.model.SearchRequest;
import com.gethealthy.illnessrecordservice.repository.IllnessRecordRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IllnessRecordServiceImpl implements IllnessRecordService{
    private final IllnessRecordRepository illnessRecordRepository;
    private final IllnessMapperService mapperService;
    private static final Logger logger = LoggerFactory.getLogger(IllnessRecordServiceImpl.class);
    @Override
    public IllnessRecordDTO addIllnessRecord(IllnessRecordDTO illnessRecordDTO) {
        try{
            return mapperService.toDTO(illnessRecordRepository.save(mapperService.toEntity(illnessRecordDTO)));
        }catch(Exception e){
            logger.info("Error adding illness record with data: {}", illnessRecordDTO);
            throw new RuntimeException(e);
        }
    }

    @Override
    public IllnessRecordDTO getIllnessRecord(Long id) throws RecordNotFoundException {
        try {
            return mapperService.toDTO(illnessRecordRepository.findById(id).orElseThrow(
                    () -> new RecordNotFoundException(id)
            ));
        }catch(RecordNotFoundException recordNotFoundException){
            logger.info("No illness record in the database with ID: {}", id);
            throw new RuntimeException(recordNotFoundException);
        }catch (Exception e){
            logger.info("Error retrieving illness record with data: {}", id);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<IllnessRecordDTO> getAllIllnessRecordsByUserId(Long userID) throws RecordNotFoundException {
        try {
            var illnessDTOs = new ArrayList<IllnessRecordDTO>();
            var ilnessRecords = illnessRecordRepository.findAllByUserID(userID).orElseThrow(
                    () -> new RecordNotFoundException(userID)
            );

            ilnessRecords.forEach(record -> illnessDTOs.add(mapperService.toDTO(record)));
            return illnessDTOs;
        }catch(RecordNotFoundException recordNotFoundException){
            logger.info("No illness record in the database associated with the userID: {}", userID);
            throw new RuntimeException(recordNotFoundException);
        }catch (Exception e){
            logger.info("Error retrieving illness record associated with userID: {}", userID);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<IllnessRecordDTO> getRecordsBySearch(SearchRequest searchRequest) throws RecordNotFoundException {
        try {
            var illnessRecordDTOS = new ArrayList<IllnessRecordDTO>();
            var illnesRecords = illnessRecordRepository.searchRecords(searchRequest.getTerm(), searchRequest.getUserID())
                    .orElseThrow(
                        () -> new RecordNotFoundException(searchRequest.getTerm(), searchRequest.getUserID())
                    );
            illnesRecords.forEach(record -> illnessRecordDTOS.add(mapperService.toDTO(record)));

            return illnessRecordDTOS;
        }catch(RecordNotFoundException recordNotFoundException){
            logger.info("No illness record in the database matching: {} and userID: {}", searchRequest.getTerm(), searchRequest.getUserID());
            throw new RuntimeException(recordNotFoundException);
        }catch (Exception e){
            logger.info("Error retrieving illness record matching: {} and userID: {}", searchRequest.getTerm(), searchRequest.getUserID());
            throw new RuntimeException(e);
        }
    }

    @Override
    public IllnessRecordDTO update(IllnessRecordDTO illnessRecordDTO) throws RecordNotFoundException {
        try {
            var illnessRecord = illnessRecordRepository.findById(illnessRecordDTO.getId()).orElseThrow(
                    () -> new RecordNotFoundException(illnessRecordDTO.getId())
            );
            mapperService.updateEntity(illnessRecordDTO, illnessRecord);
            return mapperService.toDTO(illnessRecordRepository.save(illnessRecord));
        }catch (RecordNotFoundException recordNotFoundException){
            logger.info("No illness records found with id: {}", illnessRecordDTO.getId());
            throw new RuntimeException(recordNotFoundException);
        }catch (Exception e){
            logger.info("Error updating illness record with data: {}", illnessRecordDTO);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean deleteIllnessRecord(DeleteRequest deleteRequest) throws RecordNotFoundException {
        try {
            illnessRecordRepository.deleteByIdAndUserID(deleteRequest.getIllnessRecordID(), deleteRequest.getUserID());
            return Boolean.TRUE;
        }catch (RecordNotFoundException recordNotFoundException){
            logger.info("No illness records found with id{} and userID{}", deleteRequest.getIllnessRecordID(), deleteRequest.getUserID());
            throw new RuntimeException(recordNotFoundException);
        }catch (Exception e){
            logger.info("Error deleting illness record with data: {}", deleteRequest.getIllnessRecordID());
            throw new RuntimeException(e);
        }
    }
}
