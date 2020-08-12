package com.projeto.departamentoSpringBoot.web.conversor;

import com.projeto.departamentoSpringBoot.dominio.Departamento;
import com.projeto.departamentoSpringBoot.servico.DepartamentoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {

    @Autowired
    private DepartamentoServico servico;

    @Override
    public Departamento convert(String s) {

        if (s.isEmpty()) {
            return null;
        }

        Long id = Long.valueOf(s);

        return servico.buscarPorId(id);
    }

}
