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

import br.com.tecnisys.core.base.model.Usuario;
import br.com.tecnisys.core.base.repository.UsuarioRepository;

@RestController
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@CrossOrigin
	@GetMapping("/usuarios")
	public List<Usuario> getUsuarios(){
		return usuarioRepository.findAll();
	}
	
	@CrossOrigin
	@GetMapping("/usuarios/{id}")
	public Usuario pesquisarPorIdUsuario(@PathVariable long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (!usuario.isPresent())
			throw new RuntimeException("id-" + id);

		return usuario.get();
	}
	
	@CrossOrigin
	@DeleteMapping("/usuarios/{id}")
	public void deleteUsuario(@PathVariable long id) {
		usuarioRepository.deleteById(id);
	}
	
	@CrossOrigin
	@PostMapping("/usuarios")
	public ResponseEntity<Object> createUsuario(@RequestBody Usuario usuario) {
		Usuario savedUsuario = usuarioRepository.save(usuario);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUsuario.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@CrossOrigin
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Object> updateUsuario(@RequestBody Usuario usuario, @PathVariable long id) {

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

		if (!usuarioOptional.isPresent())
			return ResponseEntity.notFound().build();

		usuario.setId(id);
		
		usuarioRepository.save(usuario);

		return ResponseEntity.noContent().build();
	}

}
