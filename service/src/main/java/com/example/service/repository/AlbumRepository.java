package com.example.service.repository;

import com.example.service.models.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlbumRepository extends PagingAndSortingRepository<AlbumEntity, Long> {
}
