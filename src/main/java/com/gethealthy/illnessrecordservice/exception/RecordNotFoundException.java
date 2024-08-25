package com.gethealthy.illnessrecordservice.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(Long id) {
            super("No record found with id{} " + id);
    }
    public RecordNotFoundException(String keyword) {
        super("No record found containing " + keyword);
    }
}
