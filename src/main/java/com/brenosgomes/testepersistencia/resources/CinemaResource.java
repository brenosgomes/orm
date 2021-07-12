package com.brenosgomes.testepersistencia.resources;

import com.brenosgomes.testepersistencia.domain.Cinema;
import com.brenosgomes.testepersistencia.dto.CinemaDTO;
import com.brenosgomes.testepersistencia.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/nucleos")
public class CinemaResource {

    @Autowired
    private CinemaService service;

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cinema> find(@PathVariable Integer id){
        Cinema obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void>insert(@RequestBody CinemaDTO objDto){
        Cinema obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody CinemaDTO objDto, @PathVariable Integer id){
        Cinema obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CinemaDTO>> findAll(){
        List<Cinema> list = service.findAll();
        List<CinemaDTO> listDto = list.stream().map(obj -> new CinemaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value ="/page",method = RequestMethod.GET)
    public ResponseEntity<Page<CinemaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        Page<Cinema> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<CinemaDTO> listDto = list.map(obj -> new CinemaDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
