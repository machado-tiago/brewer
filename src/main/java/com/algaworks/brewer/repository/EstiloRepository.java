package com.algaworks.brewer.repository;

import com.algaworks.brewer.model.Estilo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo,Long>{
    
}
