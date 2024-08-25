package com.gethealthy.illnessrecordservice.repository;

import com.gethealthy.illnessrecordservice.model.IllnessRecord;
import com.gethealthy.illnessrecordservice.model.IllnessRecordDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IllnessRecordRepository extends JpaRepository<IllnessRecord, Long> {
    IllnessRecordDTO findAllByUserID(Long userID);
}
