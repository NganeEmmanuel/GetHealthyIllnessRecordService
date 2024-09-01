package com.gethealthy.illnessrecordservice.service;

import com.gethealthy.illnessrecordservice.model.IllnessRecord;
import com.gethealthy.illnessrecordservice.model.IllnessRecordDTO;
import org.springframework.stereotype.Service;

@Service
public class IllnessMapperService implements MapperService<IllnessRecordDTO, IllnessRecord> {

    @Override
    public IllnessRecordDTO toDTO(IllnessRecord illnessRecord) {
        return IllnessRecordDTO.builder()
                .id(illnessRecord.getId())
                .illnessName(illnessRecord.getIllnessName())
                .illnessDescription(illnessRecord.getIllnessDescription())
                .illnessStatus(illnessRecord.getIllnessStatus())
                .illnessLocation(illnessRecord.getIllnessLocation())
                .illnessStartDate(illnessRecord.getIllnessStartDate())
                .illnessEndDate(illnessRecord.getIllnessEndDate())
                .build();
    }

    @Override
    public IllnessRecord toEntity(IllnessRecordDTO illnessRecordDTO) {
        return IllnessRecord.builder()
                .illnessName(illnessRecordDTO.getIllnessName())
                .illnessDescription(illnessRecordDTO.getIllnessDescription())
                .illnessStatus(illnessRecordDTO.getIllnessStatus())
                .illnessStartDate(illnessRecordDTO.getIllnessStartDate())
                .illnessEndDate(illnessRecordDTO.getIllnessEndDate())
                .illnessLocation(illnessRecordDTO.getIllnessLocation())
                .build();
    }

    @Override
    public void updateEntity(IllnessRecordDTO illnessRecordDTO, IllnessRecord illnessRecord) {
        illnessRecord.setIllnessName(illnessRecordDTO.getIllnessName());
        illnessRecord.setIllnessDescription(illnessRecordDTO.getIllnessDescription());
        illnessRecord.setIllnessStatus(illnessRecordDTO.getIllnessStatus());
        illnessRecord.setIllnessLocation(illnessRecordDTO.getIllnessLocation());
        illnessRecord.setIllnessStartDate(illnessRecordDTO.getIllnessStartDate());
        illnessRecord.setIllnessEndDate(illnessRecordDTO.getIllnessEndDate());
    }
}
