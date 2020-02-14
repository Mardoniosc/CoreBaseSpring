package br.com.tecnisys.core.base.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tecnisys.core.base.model.HistoricoAcesso;
import br.com.tecnisys.core.base.repository.HistoricoAcessoRepository;

@RestController
public class HistoricoAcessoResource {
	
	@Autowired
	private HistoricoAcessoRepository historicoAcessoRepository;
	
	@CrossOrigin
	@GetMapping("/historicoacessos")
	public List<HistoricoAcesso> gerHistoricoAcesso(){
		return historicoAcessoRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/historicoacessos/{id}")
	public HistoricoAcesso pesquisarPorIdHistoricoAcesso(@PathVariable long id) {
		Optional<HistoricoAcesso> historicoAcesso = historicoAcessoRepository.findById(id);

		if (!historicoAcesso.isPresent())
			throw new RuntimeException("id-" + id);

		return historicoAcesso.get();
	}
	
	@CrossOrigin
	@DeleteMapping("/historicoacessos/{id}")
	public void deleteHistoricoAcesso(@PathVariable long id) {
		historicoAcessoRepository.deleteById(id);
	}
	
	@CrossOrigin
	@PostMapping("/historicoacessos")
	public ResponseEntity<Object> createHistoricoAcesso(@RequestBody HistoricoAcesso historicoAcesso) {
		HistoricoAcesso savedHistoricoAcesso = historicoAcessoRepository.save(historicoAcesso);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedHistoricoAcesso.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@CrossOrigin
	@PutMapping("/historicoacessos/{id}")
	public ResponseEntity<Object> updateHistoricoAcesso(@RequestBody HistoricoAcesso historicoAcesso, @PathVariable long id) {

		Optional<HistoricoAcesso> historicoAcessoOptional = historicoAcessoRepository.findById(id);

		if (!historicoAcessoOptional.isPresent())
			return ResponseEntity.notFound().build();

		historicoAcesso.setId(id);
		
		historicoAcessoRepository.save(historicoAcesso);

		return ResponseEntity.noContent().build();
	}

}
