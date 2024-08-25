package com.gethealthy.illnessrecordservice.model;

import com.gethealthy.illnessrecordservice.enums.IllnessStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class IllnessRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String illnessName;
    private String illnessStartDate;
    private String illnessEndDate;
    private String illnessDescription;
    private IllnessStatus illnessStatus;
    private String illnessLocation;
}
