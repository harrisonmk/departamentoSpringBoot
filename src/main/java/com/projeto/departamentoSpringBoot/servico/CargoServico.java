
package com.projeto.departamentoSpringBoot.servico;

import com.projeto.departamentoSpringBoot.dominio.Cargo;
import com.projeto.departamentoSpringBoot.util.PaginacaoUtil;
import java.util.List;


public interface CargoServico {
    
    void salvar(Cargo cargo);
    void editar(Cargo cargo);
    void excluir(Long id);
    Cargo buscarPorId(Long id);
    List<Cargo> buscarTodos();
    boolean cargoTemFuncionarios(Long id);
    PaginacaoUtil<Cargo> buscarPorPagina(int pagina, String direcao);
    
}
