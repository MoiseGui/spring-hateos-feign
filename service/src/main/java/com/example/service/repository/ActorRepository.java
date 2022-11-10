package com.example.service.repository;

import com.example.service.models.ActorEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActorRepository extends PagingAndSortingRepository<ActorEntity, Long> {
}
