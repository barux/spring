package com.barux.e4cSpring.game;

import com.barux.e4cSpring.publisher.Publisher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private Date releaseDate;
    private Float price;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
}
