
package com.projeto.departamentoSpringBoot.dominio;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
@Entity
@Table(name="departamentos")
public class Departamento extends AbstractEntity<Long> {
    
    //Nome da coluna | nao pode ser null | valor unico | tamanho 100
    @NotBlank(message = "Informe um nome.")
    @Size(min = 2,max=60, message="O nome do departamento deve ter entre {min} e {max caracteres}.")//minimo 2 caracteres e maximo 60 caracteres
    @Column(name="nome",nullable=false,unique=true,length=100)
    private String nome; // quando coloca o atributo como unique ele ordena pelo nome e nao pelo id
    
    @OneToMany(mappedBy="departamento") //muitos cargos para um departamento | possui relacionamento bidimensional com o atributo departamento
    private List<Cargo> cargos;
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }
    
    
    
    
}
