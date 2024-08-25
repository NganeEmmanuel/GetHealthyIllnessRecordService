package com.gethealthy.illnessrecordservice.repository;

import com.gethealthy.illnessrecordservice.model.IllnessRecord;
import com.gethealthy.illnessrecordservice.model.IllnessRecordDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IllnessRecordRepository extends JpaRepository<IllnessRecord, Long> {

    Optional<List<IllnessRecordDTO>> findAllByUserID(Long userID);

    /**
     * query for postgresql that retrieves all records from the IllnessRecord(model name) table
     * WHERE the tile column contains or is the closest match to the term (prioritize best match)
     * AND description column contains or is the closest match to the term (prioritize best match)
     * ORDER BY dateAdded
     *
     * @param term string to search for or match against
     * @return a list of the best possible matched records if available
     */
    @Query(value = "SELECT * FROM IllnessRecord " +
            "WHERE to_tsvector('english', title) @@ plainto_tsquery('english', :term) " +
            "   OR to_tsvector('english', description) @@ plainto_tsquery('english', :term) " +
            "ORDER BY dateAdded", nativeQuery = true)
    Optional<List<IllnessRecordDTO>> searchRecords(@Param("term") String term);

    void deleteByIdAndUserID(Long id, Long userID);
}
