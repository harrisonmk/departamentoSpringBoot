package com.projeto.departamentoSpringBoot.servico;

import com.projeto.departamentoSpringBoot.dominio.Departamento;
import java.util.List;

public interface DepartamentoServico {

    void salvar(Departamento departamento);

    void editar(Departamento departamento);

    void excluir(Long id);

    Departamento buscarPorId(Long id);

    List<Departamento> buscarTodos();

    boolean departamentoTemCargos(Long id);

}
