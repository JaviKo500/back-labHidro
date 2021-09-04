package com.back.labhidro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.Usuario;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Long>{
	public List<Usuario> findByEstado(Boolean estado);
}
