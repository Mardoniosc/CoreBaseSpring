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

import br.com.tecnisys.core.base.model.Perfil;
import br.com.tecnisys.core.base.repository.PerfilRepository;

@RestController
public class PerfilResource {
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@CrossOrigin
	@GetMapping("/perfils")
	public List<Perfil> getPerfils(){
		return perfilRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/perfils/{id}")
	public Perfil pesquisarPorIdPerfil(@PathVariable long id) {
		Optional<Perfil> perfil = perfilRepository.findById(id);

		if (!perfil.isPresent())
			throw new RuntimeException("id-" + id);

		return perfil.get();
	}
	
	@CrossOrigin
	@DeleteMapping("/perfils/{id}")
	public void deleteUsuario(@PathVariable long id) {
		perfilRepository.deleteById(id);
	}
	
	@CrossOrigin
	@PostMapping("/perfils")
	public ResponseEntity<Object> createUsuario(@RequestBody Perfil perfil) {
		Perfil savedPerfil = perfilRepository.save(perfil);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPerfil.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@CrossOrigin
	@PutMapping("/perfils/{id}")
	public ResponseEntity<Object> updatePerfil(@RequestBody Perfil perfil, @PathVariable long id) {

		Optional<Perfil> perfilOptional = perfilRepository.findById(id);

		if (!perfilOptional.isPresent())
			return ResponseEntity.notFound().build();

		perfil.setId(id);
		
		perfilRepository.save(perfil);

		return ResponseEntity.noContent().build();
	}

}
