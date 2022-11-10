package com.example.service.controllers;

import com.example.service.assemblers.ActorModelAssembler;
import com.example.service.assemblers.AlbumModelAssembler;
import com.example.service.models.ActorEntity;
import com.example.service.models.AlbumEntity;
import com.example.service.repository.ActorRepository;
import com.example.service.repository.AlbumRepository;
import ma.example.commons.dto.ActorModel;
import ma.example.commons.dto.AlbumModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorModelAssembler actorModelAssembler;

    @Autowired
    private AlbumModelAssembler albumModelAssembler;

    @Autowired
    private PagedResourcesAssembler<AlbumEntity> albumEntityPagedResourcesAssembler;
    @Autowired
    private PagedResourcesAssembler<ActorEntity> actorEntityPagedResourcesAssembler;

    @GetMapping("/api/actors")
    public ResponseEntity<CollectionModel<ActorModel>> getAllActors()
    {
        List<ActorEntity> actorEntities = (List<ActorEntity>) actorRepository.findAll();
        return new ResponseEntity<>(
                actorModelAssembler.toCollectionModel(actorEntities),
                HttpStatus.OK);
    }

    @GetMapping("/api/actors-list")
    public  ResponseEntity<PagedModel<ActorModel>> getAllActors(Pageable pageable)
    {
        Page<ActorEntity> actorEntities = actorRepository.findAll(pageable);
        PagedModel<ActorModel> collModel = actorEntityPagedResourcesAssembler
                .toModel(actorEntities, actorModelAssembler);

        return new ResponseEntity<>(collModel,HttpStatus.OK);
    }

    @GetMapping("/api/actors/{id}")
    public ResponseEntity<ActorModel> getActorById(@PathVariable("id") Long id)
    {
        return actorRepository.findById(id)
                .map(actorModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/albums")
    public ResponseEntity<CollectionModel<AlbumModel>> getAllAlbums()
    {
        List<AlbumEntity> albumEntities = (List<AlbumEntity>) albumRepository.findAll();

        return new ResponseEntity<>(
                albumModelAssembler.toCollectionModel(albumEntities),
                HttpStatus.OK);
    }

    @GetMapping("/api/albums/{id}")
    public ResponseEntity<AlbumModel> getAlbumById(@PathVariable("id") Long id)
    {
        return albumRepository.findById(id)
                .map(albumModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
