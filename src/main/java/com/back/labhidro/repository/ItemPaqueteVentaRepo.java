package com.back.labhidro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.labhidro.entity.ItemPaqueteVenta;

@Repository
public interface ItemPaqueteVentaRepo extends JpaRepository<ItemPaqueteVenta, Long>{
	
}
