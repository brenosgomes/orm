package com.brenosgomes.testepersistencia.services;

import com.brenosgomes.testepersistencia.domain.Cinema;
import com.brenosgomes.testepersistencia.dto.FilmeDTO;
import com.brenosgomes.testepersistencia.domain.Filme;
import com.brenosgomes.testepersistencia.repositories.FilmeRepository;
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
public class FilmeService {

    @Autowired
    private FilmeRepository repo;

    @Autowired
    private CinemaService cinemaService;

    public Filme find(Integer id){
        Optional<Filme> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Filme.class.getName()
        ));
    }

    public Filme insert (Filme obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public Filme update (Filme obj){
        Filme newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try{
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir a aplicacao");
        }
    }

    public List<Filme> findAll(){
        return repo.findAll();
    }

    public Page<Filme> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Filme fromDTO(FilmeDTO objDto){
        Cinema cinema = cinemaService.find(objDto.getCinema_id());
        return new Filme(objDto.getId(), objDto.getNome(), objDto.getDuracao(), objDto.getNota(), objDto.getRank(), cinema);
    }

    private void updateData(Filme newObj, Filme obj){
        newObj.setNome(obj.getNome());
        newObj.setDuracao(obj.getDuracao());
        newObj.setNota(obj.getNota());
        newObj.setRank(obj.getRank());
    }
}

