package com.projeto.departamentoSpringBoot.web.conversor;

import com.projeto.departamentoSpringBoot.dominio.Cargo;
import com.projeto.departamentoSpringBoot.servico.CargoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCargoConversor implements Converter<String, Cargo> {

    @Autowired
    private CargoServico cargoServico;

    @Override
    public Cargo convert(String s) {

        if (s.isEmpty()) {
            return null;
        }

        Long id = Long.valueOf(s);
        return cargoServico.buscarPorId(id);

    }

}
