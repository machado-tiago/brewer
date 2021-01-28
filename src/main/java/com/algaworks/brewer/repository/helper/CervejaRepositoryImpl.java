package com.algaworks.brewer.repository.helper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.algaworks.brewer.dto.CervejaFilterDto;
import com.algaworks.brewer.model.Cerveja;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Repository
public class CervejaRepositoryImpl implements CervejaRepositoryQueries {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Cerveja> filtrar(CervejaFilterDto cervejaFilterDto) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Cerveja> query = builder.createQuery(Cerveja.class);
		Root<Cerveja> cervejaEntity = query.from(Cerveja.class);
        List<Predicate> predicateList = new ArrayList<>();
        
        if (cervejaFilterDto != null) {
			if (!StringUtils.isEmpty(cervejaFilterDto.getSku())) {
				predicateList.add(builder.equal(cervejaEntity.get("sku"), cervejaFilterDto.getSku()));
			}
			
			if (!StringUtils.isEmpty(cervejaFilterDto.getNome())) {
				predicateList.add(builder.like(cervejaEntity.get("nome"), "%" + cervejaFilterDto.getNome() + "%"));
			}

			if (isEstiloPresente(cervejaFilterDto)) {
				predicateList.add(builder.equal(cervejaEntity.get("estilo"), cervejaFilterDto.getEstilo()));
			}

			if (cervejaFilterDto.getSabor() != null) {
				predicateList.add(builder.equal(cervejaEntity.get("sabor"), cervejaFilterDto.getSabor()));
			}

			if (cervejaFilterDto.getOrigem() != null) {
				predicateList.add(builder.equal(cervejaEntity.get("origem"), cervejaFilterDto.getOrigem()));
			}

			if (cervejaFilterDto.getValorDe() != null) {
				predicateList.add(builder.equal(cervejaEntity.get("valor"), cervejaFilterDto.getValorDe()));
			}

			if (cervejaFilterDto.getValorAte() != null) {
				predicateList.add(builder.equal(cervejaEntity.get("valor"), cervejaFilterDto.getValorAte()));
			}
        }
        
		query.where(predicateList.toArray(new Predicate[0]));
		TypedQuery<Cerveja> typedQuery = em.createQuery(query);

		/*
		TypedQuery<Cerveja> typedQuery =  (TypedQuery<Cerveja>) paginacaoUtil.prepararOrdem(query, cervejaEntity, pageable);
		typedQuery = (TypedQuery<Cerveja>) paginacaoUtil.prepararIntervalo(typedQuery, pageable);
		
        return new PageImpl<>(typedQuery.getResultList(), pageable, total(filtro));
		*/

		return typedQuery.getResultList();
    }

	private boolean isEstiloPresente(CervejaFilterDto cervejaFilterDto) {
		return cervejaFilterDto.getEstilo() != null && cervejaFilterDto.getEstilo().getCodigo() != null;
	}
}
