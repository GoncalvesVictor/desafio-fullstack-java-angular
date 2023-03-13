package br.com.juridico.totvs.fullstack.Backend.service.dto;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentario;

public class ComentarioDTO {
	private Long id;
	private String comentario;
	private String autor;
	private String dtComentario;
	private String idPontoTuristico;
	
	public ComentarioDTO(Long id, String comentario, String autor, String dtComentario, String idPontoTuristico) {
		super();
		this.id = id;
		this.comentario = comentario;
		this.autor = autor;
		this.dtComentario = dtComentario;
		this.idPontoTuristico = idPontoTuristico;
	}
	
	public ComentarioDTO(Comentario comentario) {
		this.id = comentario.getId();
		this.comentario = comentario.getComentario();
		this.autor = comentario.getAutor();
		this.dtComentario = comentario.getDtComentario();
		this.idPontoTuristico = comentario.getIdPontoTuristico();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDtComentario() {
		return dtComentario;
	}

	public void setDtComentario(String dtComentario) {
		this.dtComentario = dtComentario;
	}

	public String getIdPontoTuristico() {
		return idPontoTuristico;
	}

	public void setIdPontoTuristico(String idPontoTuristico) {
		this.idPontoTuristico = idPontoTuristico;
	}
	
}
