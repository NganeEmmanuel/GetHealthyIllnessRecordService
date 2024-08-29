package com.gethealthy.illnessrecordservice.repository;

import com.gethealthy.illnessrecordservice.model.IllnessRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IllnessRecordRepository extends JpaRepository<IllnessRecord, Long> {

    Optional<List<IllnessRecord>> findAllByUserID(Long userID);

    /**
     * query for postgresql that retrieves all records from the IllnessRecord(model name) table
     * WHERE the tile column contains or is the closest match to the term (prioritize best match)
     * OR  description column contains or is the closest match to the term (prioritize best match)
     * AND the userID is the same as the userID parameter
     * ORDER BY dateAdded
     *
     * @param term string to search for or match against
     * @param userID Long data type for user identifier
     * @return a list of the best possible matched records if available
     */
    @Query(value = "SELECT * FROM illness_record " +
            "WHERE (to_tsvector('english', illness_name) @@ plainto_tsquery('english', :term) " +
            "   OR to_tsvector('english', illness_description) @@ plainto_tsquery('english', :term)) " +
            "AND userid = :userID " +
            "ORDER BY illness_start_date", nativeQuery = true)
    Optional<List<IllnessRecord>> searchRecords(@Param("term") String term, @Param("userID") Long userID);



    void deleteByIdAndUserID(Long id, Long userID);
}
