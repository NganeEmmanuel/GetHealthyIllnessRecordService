package com.gethealthy.illnessrecordservice.model;

import lombok.Data;

@Data
public class DeleteRequest {
    private Long illnessRecordID;
    private Long userID;
}
