package com.barux.e4cSpring.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameRepository extends JpaRepository<Game, Integer>, PagingAndSortingRepository<Game, Integer> {
}
