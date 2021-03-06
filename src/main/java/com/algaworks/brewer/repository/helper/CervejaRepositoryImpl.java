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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Repository
public class CervejaRepositoryImpl implements CervejaRepositoryQueries {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public Page<Cerveja> filtrar(CervejaFilterDto cervejaFilterDto, Pageable pageable) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Cerveja> query = builder.createQuery(Cerveja.class);
		Root<Cerveja> cervejaEntity = query.from(Cerveja.class);
		
		query.where(adicionarFiltro(cervejaFilterDto, cervejaEntity));
		TypedQuery<Cerveja> typedQuery = em.createQuery(query);
		
		int totalRegistrosPorPagina = pageable.getPageSize();
		typedQuery.setMaxResults(totalRegistrosPorPagina);
		typedQuery.setFirstResult(pageable.getPageNumber()*totalRegistrosPorPagina);
		
		return new PageImpl<>(typedQuery.getResultList(), pageable, total(cervejaFilterDto));
    }
	
	private Predicate[] adicionarFiltro(CervejaFilterDto cervejaFilterDto, Root<Cerveja> cervejaEntity) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
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

		return predicateList.toArray(new Predicate[0]);
	}

	private boolean isEstiloPresente(CervejaFilterDto cervejaFilterDto) {
		return cervejaFilterDto.getEstilo() != null && cervejaFilterDto.getEstilo().getCodigo() != null;
	}

	private Long total(CervejaFilterDto cervejaFilterDto){
		CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Cerveja> cervejaEntity = query.from(Cerveja.class);
		query.select(builder.count(cervejaEntity));
		query.where(adicionarFiltro(cervejaFilterDto, cervejaEntity));
		
		return em.createQuery(query).getSingleResult();
	}
}
