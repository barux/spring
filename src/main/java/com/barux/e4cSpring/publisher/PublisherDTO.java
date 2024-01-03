package com.barux.e4cSpring.publisher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDTO {
    private Integer id;
    private String name;
    private Date foundedAt;
}

// TODO aggiungere controlli di validazione