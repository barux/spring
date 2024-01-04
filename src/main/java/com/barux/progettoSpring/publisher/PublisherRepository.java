package com.barux.progettoSpring.publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer>, PagingAndSortingRepository<Publisher, Integer> {
}
