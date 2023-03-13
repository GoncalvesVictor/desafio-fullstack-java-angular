package br.com.juridico.totvs.fullstack.Backend.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentario;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioDTO;

@Service
public class ComentarioServiceImpl implements ComentarioService{
	List<Comentario> listComentario = null;
	
	public ComentarioServiceImpl() {
		this.listComentario = new ArrayList<>();
		this.listComentario.add(new Comentario(1L,
				"Teste de comentario",
				"Victor",
				"2023-03-12",
				"1"));
	}

	@Override
	public ComentarioDTO create(ComentarioCreateUpdateDTO comentarioCreateUpdateDTO) {
		Comentario novoComentario = new Comentario(
				this.getNewId(),
				comentarioCreateUpdateDTO.getComentario(),
				comentarioCreateUpdateDTO.getAutor(),
				comentarioCreateUpdateDTO.getDtComentario(),
				comentarioCreateUpdateDTO.getIdPontoTuristico());
		
		this.listComentario.add(novoComentario);
		return new ComentarioDTO(novoComentario);
	}

	@Override
	public ComentarioDTO update(Long id, ComentarioCreateUpdateDTO comentarioCreateUpdateDTO) {
		Comentario comentario = getComentarioById(id);
		int index = this.listComentario.indexOf(comentario);
		if(comentario == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		comentario.setComentario(comentarioCreateUpdateDTO.getComentario());
		comentario.setAutor(comentarioCreateUpdateDTO.getAutor());
		comentario.setDtComentario(comentarioCreateUpdateDTO.getDtComentario());
		comentario.setIdPontoTuristico(comentarioCreateUpdateDTO.getIdPontoTuristico());
		
		this.listComentario.set(index, comentario);
		return new ComentarioDTO(comentario);
	}

	@Override
	public void delete(Long id) {
		Comentario comentario = this.getComentarioById(id);
		this.listComentario.remove(comentario);
	}
	
	@Override
	public List<ComentarioDTO> getComentarioByPontoTuristico(Long id) {
		
			return this.listComentario.stream()
	                .filter(x -> x.getIdPontoTuristico().equals(id.toString()))
	                .map(comentario -> new ComentarioDTO(comentario))
	                .collect(Collectors.toList());
	}
	
    private Long getNewId(){
        if (this.listComentario.size() > 0){
            return this.listComentario.stream().max(Comparator
                            .comparingDouble(Comentario::getId))
                    .get()
                    .getId()+1;
        } else {
            return Long.valueOf(1);
        }
    }
    
    private Comentario getComentarioById(Long id) {
    	return this.listComentario.stream()
                .filter(x -> Objects.equals(x.getId(), id))
                .findFirst()
                .orElse(null);
    }

	@Override
	public ComentarioDTO getComentariobyId(Long id) {
		Comentario comentario = this.getComentarioById(id);
		if(comentario == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return new ComentarioDTO(comentario);
	}

}
