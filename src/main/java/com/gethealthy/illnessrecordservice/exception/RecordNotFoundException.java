package com.gethealthy.illnessrecordservice.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(Long id) {
            super("No record found with id " + id);
    }

    public RecordNotFoundException(String keyword) {
        super("No record found matching the term" + keyword);
    }

    public RecordNotFoundException(String keyword, Long id) {
        super("No record found matching the term{" + keyword + "} and userID: {" + id + "}");
    }

    public RecordNotFoundException(Long id, Long userID) {
        super("No record found matching with id{" + id + "} and userID: " + userID);
    }
}
