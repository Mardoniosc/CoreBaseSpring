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

import br.com.tecnisys.core.base.model.Permissao;
import br.com.tecnisys.core.base.repository.PermissaoRepository;

@RestController
public class PermissaoResource {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@CrossOrigin
	@GetMapping("/permissoes")
	public List<Permissao> getPermissaos(){
		return permissaoRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/permissoes/{id}")
	public Permissao pesquisarPorIdPermissao(@PathVariable long id) {
		Optional<Permissao> permissao = permissaoRepository.findById(id);

		if (!permissao.isPresent())
			throw new RuntimeException("id-" + id);

		return permissao.get();
	}
	
	@CrossOrigin
	@DeleteMapping("/permissoes/{id}")
	public void deletePermissao(@PathVariable long id) {
		permissaoRepository.deleteById(id);
	}
	
	@CrossOrigin
	@PostMapping("/permissoes")
	public ResponseEntity<Object> createPermissao(@RequestBody Permissao permissao) {
		Permissao savedPermissao = permissaoRepository.save(permissao);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPermissao.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@CrossOrigin
	@PutMapping("/permissoes/{id}")
	public ResponseEntity<Object> updatePermissao(@RequestBody Permissao permissao, @PathVariable long id) {

		Optional<Permissao> permissaoOptional = permissaoRepository.findById(id);

		if (!permissaoOptional.isPresent())
			return ResponseEntity.notFound().build();

		permissao.setId(id);
		
		permissaoRepository.save(permissao);

		return ResponseEntity.noContent().build();
	}

}
