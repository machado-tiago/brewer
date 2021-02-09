package com.algaworks.brewer.repository.helper;
import com.algaworks.brewer.dto.CervejaFilterDto;
import com.algaworks.brewer.model.Cerveja;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CervejaRepositoryQueries {
    public Page<Cerveja> filtrar (CervejaFilterDto cervejaFilterDto, Pageable pageable);
    
}
