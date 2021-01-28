package com.algaworks.brewer.repository.helper;
import java.util.List;
import com.algaworks.brewer.dto.CervejaFilterDto;
import com.algaworks.brewer.model.Cerveja;

public interface CervejaRepositoryQueries {
    public List<Cerveja> filtrar (CervejaFilterDto cervejaFilterDto);
    
}
