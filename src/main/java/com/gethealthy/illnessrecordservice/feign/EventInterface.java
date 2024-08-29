package com.gethealthy.illnessrecordservice.feign;

import com.gethealthy.illnessrecordservice.model.RecordEventsDeleteRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient
public interface EventInterface {
    @DeleteMapping("api/v1/event/delete/all/record")
    ResponseEntity<Boolean> deleteAllEventsByRecordID(@RequestBody RecordEventsDeleteRequest deleteRequest);
}
