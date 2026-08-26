package com.travel.travellogservice.controller;

import com.travel.travellogservice.document.TravelLog;
import com.travel.travellogservice.repository.TravelLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel-logs")
@RequiredArgsConstructor
public class TravelLogController {

    private final TravelLogRepository travelLogRepository;

    @GetMapping
    public ResponseEntity<List<TravelLog>> getAllLogs() {
        return ResponseEntity.ok(travelLogRepository.findAll());
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<TravelLog>> getLogsByTripId(@PathVariable String tripId) {
        return ResponseEntity.ok(travelLogRepository.findByTripId(tripId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelLog> getLogById(@PathVariable String id) {
        return travelLogRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TravelLog> createLog(@RequestBody TravelLog travelLog) {
        TravelLog saved = travelLogRepository.save(travelLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelLog> updateLog(@PathVariable String id, @RequestBody TravelLog logDetails) {
        return travelLogRepository.findById(id)
                .map(log -> {
                    log.setTripId(logDetails.getTripId());
                    log.setNote(logDetails.getNote());
                    return ResponseEntity.ok(travelLogRepository.save(log));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable String id) {
        if (travelLogRepository.existsById(id)) {
            travelLogRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
