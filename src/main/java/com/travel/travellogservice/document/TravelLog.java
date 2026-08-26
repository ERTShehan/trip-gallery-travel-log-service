package com.travel.travellogservice.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "travel_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelLog {

    @Id
    private String id;

    private String tripId;

    private String note;

    private List<String> mediaLinks;

}
