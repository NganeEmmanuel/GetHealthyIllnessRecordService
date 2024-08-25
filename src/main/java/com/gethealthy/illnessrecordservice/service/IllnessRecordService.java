package com.gethealthy.illnessrecordservice.service;

import com.gethealthy.illnessrecordservice.model.IllnessRecordDTO;

public interface IllnessRecordService {
    /**
     *
     * @param illnessRecordDTO DTO object to add to the database
     * @return IllnessDTO object that has been added to the database
     */
    IllnessRecordDTO addIllnessRecord(IllnessRecordDTO illnessRecordDTO);
}
