package com.mycomp.swapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "planet")
public class Planet {
    @Id
    @Indexed(unique=true, sparse=true)
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private int films;
}
