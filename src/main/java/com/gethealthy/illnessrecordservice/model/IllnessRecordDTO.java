package com.gethealthy.illnessrecordservice.model;

import com.gethealthy.illnessrecordservice.enums.IllnessStatus;
import lombok.Data;

@Data
public class IllnessRecordDTO {
    private Long id;
    private String illnessName;
    private String illnessStartDate;
    private String illnessEndDate;
    private String illnessDescription;
    private IllnessStatus illnessStatus;
    private String illnessLocation;
}
