package com.example.caller.sdk;

import ma.example.commons.dto.ActorModel;
import ma.example.commons.dto.AlbumModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-api", url = "http://localhost:9090")
public interface ServiceApi {
    @GetMapping("/api/actors")
    ResponseEntity<CollectionModel<ActorModel>> getAllActors();

    @GetMapping("/api/actors-list")
    ResponseEntity<PagedModel<ActorModel>> getAllActors(Pageable pageable);

    @GetMapping("/api/actors/{id}")
    ResponseEntity<ActorModel> getActorById(@PathVariable("id") Long id);

    @GetMapping("/api/albums")
    ResponseEntity<CollectionModel<AlbumModel>> getAllAlbums();

    @GetMapping("/api/albums/{id}")
    ResponseEntity<AlbumModel> getAlbumById(@PathVariable("id") Long id);
}
