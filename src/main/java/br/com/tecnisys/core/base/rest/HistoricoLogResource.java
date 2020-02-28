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

import br.com.tecnisys.core.base.model.HistoricoLog;
import br.com.tecnisys.core.base.repository.HistoricoLogRepository;

@RestController
public class HistoricoLogResource {
	
	@Autowired
	private HistoricoLogRepository historicoLogRepository;
	
	@CrossOrigin
	@GetMapping("/historicologs")
	public List<HistoricoLog> gerHistoricoLog(){
		return historicoLogRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/historicologs/{id}")
	public HistoricoLog pesquisarPorIdHistoricoLog(@PathVariable long id) {
		Optional<HistoricoLog> historicoLog = historicoLogRepository.findById(id);

		if (!historicoLog.isPresent())
			throw new RuntimeException("id-" + id);

		return historicoLog.get();
	}
	
	@CrossOrigin
	@DeleteMapping("/historicologs/{id}")
	public void deleteHistoricoLog(@PathVariable long id) {
		historicoLogRepository.deleteById(id);
	}
	
	@CrossOrigin
	@PostMapping("/historicologs")
	public ResponseEntity<Object> createHistoricoLog(@RequestBody HistoricoLog historicoLog) {
		HistoricoLog savedHistoricoLog = historicoLogRepository.save(historicoLog);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedHistoricoLog.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@CrossOrigin
	@PutMapping("/historicologs/{id}")
	public ResponseEntity<Object> updateHistoricoLog(@RequestBody HistoricoLog historicoLog, @PathVariable long id) {

		Optional<HistoricoLog> historicoLogOptional = historicoLogRepository.findById(id);

		if (!historicoLogOptional.isPresent())
			return ResponseEntity.notFound().build();

		historicoLog.setId(id);
		
		historicoLogRepository.save(historicoLog);

		return ResponseEntity.noContent().build();
	}

}
