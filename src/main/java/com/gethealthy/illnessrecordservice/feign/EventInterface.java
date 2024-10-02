package com.gethealthy.illnessrecordservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient("EVENT-SERVICE")
public interface EventInterface {
    @DeleteMapping("api/v1/events/record/delete-all")
    ResponseEntity<Boolean> deleteAllEventsByRecordID(@RequestParam Long recordID, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader);
    }
