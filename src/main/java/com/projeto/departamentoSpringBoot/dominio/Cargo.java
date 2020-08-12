
package com.projeto.departamentoSpringBoot.dominio;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
@Entity
@Table(name="cargos")
public class Cargo extends AbstractEntity<Long>  {
    
     
    @NotBlank(message="O nome do cargo eh obrigatorio")
    @Size(max=60,message="O nome do cargo deve conter no maximo 60 caracteres")
    @Column(name="nome",nullable=false,unique=true,length=100) //Nome da coluna | nao pode ser null | valor unico | tamanho 100
    private String nome;
    
    
    @NotNull(message="Selecione o departamento relativo ao cargo")
    @ManyToOne //um departamento pode ter vários cargos
    @JoinColumn(name="id_departamento_fk") //chave estrangeira
    private Departamento departamento;
    
    @OneToMany(mappedBy = "cargo") //um cargo para varios funcionarios
    private List<Funcionario> funcionarios;
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    
    
    
}
