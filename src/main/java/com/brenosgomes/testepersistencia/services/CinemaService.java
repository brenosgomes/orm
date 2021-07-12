package com.brenosgomes.testepersistencia.services;

import com.brenosgomes.testepersistencia.domain.Cinema;
import com.brenosgomes.testepersistencia.dto.CinemaDTO;
import com.brenosgomes.testepersistencia.repositories.CinemaRepository;
import com.brenosgomes.testepersistencia.services.exceptions.DataIntegrityException;
import com.brenosgomes.testepersistencia.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository repo;

    public Cinema find(Integer id){
        Optional<Cinema> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cinema.class.getName()
        ));
    }

    public Cinema insert (Cinema obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public Cinema update (Cinema obj){
        Cinema newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try{
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir o nucleo");
        }
    }

    public List<Cinema> findAll(){
        return repo.findAll();
    }

    public Page<Cinema> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Cinema fromDTO(CinemaDTO objDto){
        return new Cinema(objDto.getId(), objDto.getNome(), objDto.getEndereco());
    }

    private void updateData(Cinema newObj, Cinema obj){
        newObj.setNome(obj.getNome());
        newObj.setEndereco(obj.getEndereco());
    }
}

