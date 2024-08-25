package com.gethealthy.illnessrecordservice.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(Long id) {
            super("No record found with id{} " + id);
    }

    /**
     * Write a jpquery in springboot to retrieve records from table IllnessRecord(model name)
     * where tile column contain keyword or any similarities (prioritize best match)
     * And also where description column contain keyword or any similarities (prioritize best match)
     *
     */
    public RecordNotFoundException(String keyword) {
        super("No record found matching the term{} " + keyword);
    }
}
