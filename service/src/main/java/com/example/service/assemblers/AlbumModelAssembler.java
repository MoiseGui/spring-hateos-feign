package com.example.service.assemblers;

import com.example.service.controllers.WebController;
import com.example.service.models.ActorEntity;
import com.example.service.models.AlbumEntity;
import ma.example.commons.dto.ActorModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import ma.example.commons.dto.AlbumModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlbumModelAssembler
        extends RepresentationModelAssemblerSupport<AlbumEntity, AlbumModel> {

    public AlbumModelAssembler() {
        super(WebController.class, AlbumModel.class);
    }

    @Override
    public AlbumModel toModel(AlbumEntity entity)
    {
        AlbumModel albumModel = instantiateModel(entity);

        albumModel.add(linkTo(
                methodOn(WebController.class)
                        .getActorById(entity.getId()))
                .withSelfRel());

        albumModel.setId(entity.getId());
        albumModel.setTitle(entity.getTitle());
        albumModel.setDescription(entity.getDescription());
        albumModel.setReleaseDate(entity.getReleaseDate());
        albumModel.setActors(toActorModel(entity.getActors()));
        return albumModel;
    }

    @Override
    public CollectionModel<AlbumModel> toCollectionModel(Iterable<? extends AlbumEntity> entities)
    {
        CollectionModel<AlbumModel> actorModels = super.toCollectionModel(entities);

        actorModels.add(linkTo(methodOn(WebController.class).getAllAlbums()).withSelfRel());

        return actorModels;
    }

    private List<ActorModel> toActorModel(List<ActorEntity> actors) {
        if (actors.isEmpty())
            return Collections.emptyList();

        return actors.stream()
                .map(actor -> ActorModel.builder()
                        .id(actor.getId())
                        .firstName(actor.getFirstName())
                        .lastName(actor.getLastName())
                        .build()
                        .add(linkTo(
                                methodOn(WebController.class)
                                        .getActorById(actor.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());
    }
}
