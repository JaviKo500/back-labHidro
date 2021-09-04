package com.back.labhidro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.Matriz;

@Repository
public interface IMatrizRepo extends JpaRepository<Matriz, Long>{

}
