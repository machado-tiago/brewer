package com.algaworks.brewer.repository;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.helper.CervejaRepositoryQueries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CervejaRepository extends JpaRepository<Cerveja,Long>, CervejaRepositoryQueries{

}