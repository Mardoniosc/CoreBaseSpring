package br.com.tecnisys.core.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tecnisys.core.base.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {


}
