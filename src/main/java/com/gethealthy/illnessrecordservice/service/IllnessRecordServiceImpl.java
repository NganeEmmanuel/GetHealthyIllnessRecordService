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
                    () -> new RecordNotFoundException("Illness record with id " + id + " not found")
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
        return List.of(illnessRecordRepository.findAllByUserID(userID));
    }
}
