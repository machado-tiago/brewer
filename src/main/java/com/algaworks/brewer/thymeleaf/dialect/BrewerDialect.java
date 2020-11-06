package com.algaworks.brewer.thymeleaf.dialect;

import java.util.HashSet;
import java.util.Set;

import com.algaworks.brewer.thymeleaf.processor.ClassForErrorAttributeTagProcessor;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

public class BrewerDialect extends AbstractProcessorDialect {

    public BrewerDialect() {
        super("Brewer", "brewer", StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        final Set<IProcessor> processadores = new HashSet<>();
        processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
        return processadores;
    }
    
}
