package com.projeto.departamentoSpringBoot.dao;

import com.projeto.departamentoSpringBoot.dominio.Cargo;
import com.projeto.departamentoSpringBoot.util.PaginacaoUtil;
import java.util.List;

public interface CargoDao {

    void save(Cargo cargo);

    void update(Cargo cargo);

    void delete(Long id);

    Cargo findById(Long id);

    List<Cargo> findAll();
    
    PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao);

}
