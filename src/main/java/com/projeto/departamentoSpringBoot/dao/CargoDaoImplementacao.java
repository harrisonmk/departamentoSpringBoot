package com.projeto.departamentoSpringBoot.dao;

import com.projeto.departamentoSpringBoot.dominio.Cargo;
import com.projeto.departamentoSpringBoot.util.PaginacaoUtil;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CargoDaoImplementacao extends AbstractDao<Cargo, Long> implements CargoDao {

    
    public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao) {
        
        int tamanho = 5; //Vai possuir 5 registros por pagina
        int inicio = (pagina-1) * tamanho; // 0*5=0 1*5=5 2*5=10

     List<Cargo> cargos = getEntityManager()
             .createQuery("select c from Cargo c order by c.nome "+direcao,Cargo.class)  //ordena os resultados de A a Z
             .setFirstResult(inicio) // pega o resultado do primeiro registro
             .setMaxResults(tamanho) // indica quantos registros eu vou trazer por pagina
             .getResultList(); 
     
      long totalRegistros = count();
      long totalDePaginas = (totalRegistros + (tamanho-1))/tamanho;
        
        return new PaginacaoUtil<>(tamanho,pagina,totalDePaginas,direcao,cargos);
    }
    
    
    //Metodo retorna o total de registro que a tabela possui
    public long count(){
        
        return getEntityManager().createQuery("select count(*) from Cargo",Long.class).getSingleResult();
        
        
        
    }
    

}
