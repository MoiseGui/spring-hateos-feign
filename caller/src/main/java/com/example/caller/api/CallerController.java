package com.example.caller.api;

import com.example.caller.sdk.ServiceApi;
import ma.example.commons.dto.ActorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallerController {
    @Autowired
    private ServiceApi serviceApi;

    @GetMapping("/api/actors")
    public ResponseEntity<CollectionModel<ActorModel>> getAllActors(){
        return serviceApi.getAllActors();
    }

    public @GetMapping("/api/actors-list")
    ResponseEntity<PagedModel<ActorModel>> getAllActors(Pageable pageable){
        return serviceApi.getAllActors(pageable);
    }
}
