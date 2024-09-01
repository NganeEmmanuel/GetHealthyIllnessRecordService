package com.gethealthy.illnessrecordservice.service;

import com.gethealthy.illnessrecordservice.exception.RecordNotFoundException;
import com.gethealthy.illnessrecordservice.model.DeleteRequest;
import com.gethealthy.illnessrecordservice.model.IllnessRecordDTO;
import com.gethealthy.illnessrecordservice.model.SearchRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface IllnessRecordService {
    /**
     *Adds illness record to the database
     *
     * @param illnessRecordDTO DTO object to add to the database
     * @return IllnessDTO object that has been added to the database
     */
    IllnessRecordDTO addIllnessRecord(IllnessRecordDTO illnessRecordDTO,  @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader);

    /**
     *Get a single record associated with a given ID
     *
     * @param id data type of Long identifying the record in the database
     * @return a single recordDTO object if found in the database
     * @throws RecordNotFoundException if record is not found in the database
     */
    IllnessRecordDTO getIllnessRecord(Long id) throws RecordNotFoundException;

    /**
     *Retrieves all Illness records that are associated with a userID
     *
     * @param userId data type of Long identifying the userId associated with records in the database
     * @return a list of recordDTO object if found in the database
     * @throws RecordNotFoundException if record is not found in the database
     */
   List<IllnessRecordDTO> getAllIllnessRecordsByUserId(Long userId) throws RecordNotFoundException;

    /**
     *Retrieves all Illness records that can match to the search term(contains)
     *
     * @param term the search term to search by
     * @return a list of recordDTO object if found in the database
     * @throws RecordNotFoundException if record is not found in the database
     */
    List<IllnessRecordDTO> getRecordsBySearch(String term, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws RecordNotFoundException;

    /**
     *Adds illness record to the database
     *
     * @param illnessRecordDTO DTO object to update existing records in the database
     * @return IllnessDTO object with updated user information
     * @throws RecordNotFoundException if record doesn't match any existing record
     */
    IllnessRecordDTO update(IllnessRecordDTO illnessRecordDTO) throws RecordNotFoundException;

    /**
     * Deletes the record associated with the id in the database
     *
     * @param deleteRequest Long data type that identify the illness record in the database
     * @return true if successful and false if not
     * @throws RecordNotFoundException if no record is found matching the recordID and userID
     */
    Boolean deleteIllnessRecord(DeleteRequest deleteRequest) throws RecordNotFoundException;

    /**
     *Retrieves all Illness records that are associated with a logged-in user
     *
     * @param authorizationHeader the header of the request
     * @return a list of recordDTO object if found in the database matching the logged-in user
     * @throws RecordNotFoundException if record is not found in the database
     */
    List<IllnessRecordDTO> getAllIllnessRecordsByLoggedInUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws RecordNotFoundException;
}
