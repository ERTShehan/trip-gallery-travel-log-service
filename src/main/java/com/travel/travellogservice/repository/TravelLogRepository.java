package com.travel.travellogservice.repository;

import com.travel.travellogservice.document.TravelLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelLogRepository extends MongoRepository<TravelLog, String> {
    List<TravelLog> findByTripId(String tripId);
}
