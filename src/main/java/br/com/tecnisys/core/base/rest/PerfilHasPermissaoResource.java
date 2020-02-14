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

import br.com.tecnisys.core.base.model.PerfilHasPermissao;
import br.com.tecnisys.core.base.repository.PerfilHasPermissaoRepository;

@RestController
public class PerfilHasPermissaoResource {
	
	@Autowired
	private PerfilHasPermissaoRepository perfilHasPermissaoRepository;
	
	@CrossOrigin
	@GetMapping("/perfil-permissoes")
	public List<PerfilHasPermissao> getPerfilHasPermissaos(){
		return perfilHasPermissaoRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/perfil-permissoes/{id}")
	public PerfilHasPermissao pesquisarPorIdPerfilHasPermissao(@PathVariable long id) {
		Optional<PerfilHasPermissao> perfilHasPermissao = perfilHasPermissaoRepository.findById(id);

		if (!perfilHasPermissao.isPresent())
			throw new RuntimeException("id-" + id);

		return perfilHasPermissao.get();
	}
	
	@CrossOrigin
	@DeleteMapping("/perfil-permissoes/{id}")
	public void deleteUsuario(@PathVariable long id) {
		perfilHasPermissaoRepository.deleteById(id);
	}
	
	@CrossOrigin
	@PostMapping("/perfil-permissoes")
	public ResponseEntity<Object> createUsuario(@RequestBody PerfilHasPermissao perfilHasPermissao) {
		PerfilHasPermissao savedPerfilHasPermissao = perfilHasPermissaoRepository.save(perfilHasPermissao);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPerfilHasPermissao.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@CrossOrigin
	@PutMapping("/perfil-permissoes/{id}")
	public ResponseEntity<Object> updatePerfilHasPermissao(@RequestBody PerfilHasPermissao perfilHasPermissao, @PathVariable long id) {

		Optional<PerfilHasPermissao> perfilHasPermissaoOptional = perfilHasPermissaoRepository.findById(id);

		if (!perfilHasPermissaoOptional.isPresent())
			return ResponseEntity.notFound().build();

		perfilHasPermissao.setId(id);
		
		perfilHasPermissaoRepository.save(perfilHasPermissao);

		return ResponseEntity.noContent().build();
	}

}
