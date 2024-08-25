package com.gethealthy.illnessrecordservice.repository;

import com.gethealthy.illnessrecordservice.model.IllnessRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IllnessRecordRepository extends JpaRepository<IllnessRecord, Long> {
}
