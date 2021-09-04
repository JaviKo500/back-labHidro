package com.back.labhidro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.Rol;

@Repository
public interface IRolRepo extends JpaRepository<Rol, Long>{

}
