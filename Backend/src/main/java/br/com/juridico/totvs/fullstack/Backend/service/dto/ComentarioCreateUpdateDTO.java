package br.com.juridico.totvs.fullstack.Backend.service.dto;

public class ComentarioCreateUpdateDTO {
	private String comentario;
	private String autor;
	private String dtComentario;
	private String idPontoTuristico;
	
	public ComentarioCreateUpdateDTO() {
		
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
