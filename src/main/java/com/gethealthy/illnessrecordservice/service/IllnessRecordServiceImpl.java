package com.gethealthy.illnessrecordservice.service;

import com.gethealthy.illnessrecordservice.exception.RecordNotFoundException;
import com.gethealthy.illnessrecordservice.model.IllnessRecordDTO;
import com.gethealthy.illnessrecordservice.repository.IllnessRecordRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
            return illnessRecordRepository.findAllByUserID(userID).orElseThrow(
                    () -> new RecordNotFoundException(userID)
            );
        }catch(RecordNotFoundException recordNotFoundException){
            logger.info("No illness record in the database associated with the userID: {}", userID);
            throw new RuntimeException(recordNotFoundException);
        }catch (Exception e){
            logger.info("Error retrieving illness record associated with userID: {}", userID);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<IllnessRecordDTO> getRecordsBySearch(String term) throws RecordNotFoundException {
        try {
            return illnessRecordRepository.searchRecords(term).orElseThrow(
                    () -> new RecordNotFoundException(term)
            );
        }catch(RecordNotFoundException recordNotFoundException){
            logger.info("No illness record in the database matching: {}", term);
            throw new RuntimeException(recordNotFoundException);
        }catch (Exception e){
            logger.info("Error retrieving illness record matching: {}", term);
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
}
