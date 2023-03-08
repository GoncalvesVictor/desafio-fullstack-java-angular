package br.com.juridico.totvs.fullstack.Backend.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.juridico.totvs.fullstack.Backend.domain.PontoTuristico;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PontoTuristicoDTO;

@Service
public class PontoTuristicoServiceImpl implements PontoTuristicoService{
	List<PontoTuristico> listPontoTuristico = null;
	
	PontoTuristicoServiceImpl() {
		this.listPontoTuristico = new ArrayList<>();
		this.listPontoTuristico.add(new PontoTuristico(1L,
				"Brasil",
				"Sao Paulo",
				"Merco Municipal de Sao Paulo",
				"Todas",
				"Conhecido como 'Mercadao', o local esta cheio espacos gastronomicos e quitandas de frutas, legumes e verduras fresquinhas."));
	}
	
    @Override
    public PontoTuristicoDTO create(PontoTuristicoCreateUpdateDTO pontoTuristicoCreateUpdateDTO) {
    	PontoTuristico novoPontoTuristico = new PontoTuristico(
                this.getNewId(),
                pontoTuristicoCreateUpdateDTO.getPais(),
                pontoTuristicoCreateUpdateDTO.getCidade(),
                pontoTuristicoCreateUpdateDTO.getNome(),
                pontoTuristicoCreateUpdateDTO.getEstacao(),
                pontoTuristicoCreateUpdateDTO.getDescricao());

        this.listPontoTuristico.add(novoPontoTuristico);

        return new PontoTuristicoDTO(novoPontoTuristico);
    }

    @Override
    public PontoTuristicoDTO update(Long id, PontoTuristicoCreateUpdateDTO pontoTuristicoCreateUpdateDTO) {
    	PontoTuristico pontoTuristico = this.getPontoTuristicoById(id);
        int index = this.listPontoTuristico.indexOf(pontoTuristico);
        if (pontoTuristico == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        pontoTuristico.setPais(pontoTuristicoCreateUpdateDTO.getPais());
        pontoTuristico.setCidade(pontoTuristicoCreateUpdateDTO.getCidade());
        pontoTuristico.setNome(pontoTuristicoCreateUpdateDTO.getNome());
        pontoTuristico.setEstacao(pontoTuristicoCreateUpdateDTO.getEstacao());
        pontoTuristico.setDescricao(pontoTuristicoCreateUpdateDTO.getDescricao());

        this.listPontoTuristico.set(index, pontoTuristico);
        return new PontoTuristicoDTO(pontoTuristico);
    }

    @Override
    public void delete(Long id) {
        PontoTuristico pontoTuristico = this.getPontoTuristicoById(id);
        this.listPontoTuristico.remove(pontoTuristico);
    }

    @Override
    public PontoTuristicoDTO getPontoTuristicobyId(Long id) {
        PontoTuristico pontoTuristico = this.getPontoTuristicoById(id);
        if (pontoTuristico == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new PontoTuristicoDTO(pontoTuristico);
    }

    @Override
    public List<PontoTuristicoDTO> getAllPontoTuristico() {
        return this.listPontoTuristico.stream()
                .map(pontoTuristico -> new PontoTuristicoDTO(pontoTuristico))
                .collect(Collectors.toList());
    }

    private Long getNewId(){
        if (this.listPontoTuristico.size() > 0){
            return this.listPontoTuristico.stream().max(Comparator
                            .comparingDouble(PontoTuristico::getId))
                    .get()
                    .getId()+1;
        } else {
            return Long.valueOf(1);
        }
    }

    private PontoTuristico getPontoTuristicoById(Long id){
        return this.listPontoTuristico.stream()
                .filter(x -> Objects.equals(x.getId(), id))
                .findFirst()
                .orElse(null);
    }
}

