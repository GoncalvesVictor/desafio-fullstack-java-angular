package br.com.juridico.totvs.fullstack.Backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentario;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioDTO;

@Service
public interface ComentarioService {
	List<Comentario> listComentario = null;
	public ComentarioDTO create(ComentarioCreateUpdateDTO comentarioCreateUpdateDTO);
	public ComentarioDTO update(Long id, ComentarioCreateUpdateDTO comentarioCreateUpdateDTO);
	public void delete(Long id);
	public ComentarioDTO getComentariobyId(Long id);
	public List<ComentarioDTO> getComentarioByPontoTuristico(Long id);
}
