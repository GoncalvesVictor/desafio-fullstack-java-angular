package br.com.juridico.totvs.fullstack.Backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.juridico.totvs.fullstack.Backend.service.ComentarioService;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioDTO;

@CrossOrigin()
@RestController()
@RequestMapping("ponto-turistico/{idPontoTuristico}/comentario")
public class ComentarioController {
	private final ComentarioService comentarioService;
	
	public ComentarioController(ComentarioService comentarioService) {
		this.comentarioService = comentarioService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioDTO create(@RequestBody ComentarioCreateUpdateDTO comentarioCreateUpdateDTO) {
		return this.comentarioService.create(comentarioCreateUpdateDTO);
	}
	
	@GetMapping
	public List<ComentarioDTO> getComentarioByPontoTuristico(@PathVariable Long idPontoTuristico){
		return this.comentarioService.getComentarioByPontoTuristico(idPontoTuristico);
	}
	
	@DeleteMapping("{idComentario}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long idComentario) {
		this.comentarioService.delete(idComentario);
	}
	
	@PutMapping("{idComentario}")
	public ComentarioDTO update(@PathVariable Long idComentario, 
			@RequestBody ComentarioCreateUpdateDTO comentarioCreateUpdateDTO) {
		return this.comentarioService.update(idComentario, comentarioCreateUpdateDTO);
	}
	
	@GetMapping("{idComentario}")
	public ComentarioDTO getComentarioById(@PathVariable Long idComentario) {
		return this.comentarioService.getComentariobyId(idComentario);
	}
}
