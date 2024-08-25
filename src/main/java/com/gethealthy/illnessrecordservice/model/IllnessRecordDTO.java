package com.gethealthy.illnessrecordservice.model;

import com.gethealthy.illnessrecordservice.enums.IllnessStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IllnessRecordDTO {
    private Long id;
    private String illnessName;
    private String illnessStartDate;
    private String illnessEndDate;
    private String illnessDescription;
    private IllnessStatus illnessStatus;
    private String illnessLocation;
}
