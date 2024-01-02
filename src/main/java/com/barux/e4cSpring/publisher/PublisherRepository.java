package com.barux.e4cSpring.publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long>, PagingAndSortingRepository<Publisher, Long> {
}
