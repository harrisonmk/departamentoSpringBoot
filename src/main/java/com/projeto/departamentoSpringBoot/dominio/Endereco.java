package com.projeto.departamentoSpringBoot.dominio;

import javax.persistence.*;
import javax.validation.constraints.*;



@Entity
@SuppressWarnings("serial")
@Table(name = "enderecos")
public class Endereco extends AbstractEntity<Long> {

    @NotBlank
    @Size(min=3,max=255)
    @Column(nullable = false) // diz que o campo eh obrigatorio
    private String logradouro;

    @NotBlank
    @Size(min=3,max=255)
    @Column(nullable = false) // diz que o campo eh obrigatorio
    private String bairro;

    @NotBlank
    @Size(min=3,max=255)
    @Column(nullable = false) // diz que o campo eh obrigatorio
    private String cidade;

    @NotNull(message="{NotNull.endereco.uf}") //pega a mensagem de validacao do ValidationMenssages.properties
    @Column(nullable = false, length = 2) // diz que o campo eh obrigatorio e tem tamanho 2
    @Enumerated(EnumType.STRING) // diz que o tipo que vai ser salvo no bd vai ser String
    private Uf uf;

    @NotBlank
    @NotNull(message="{NotNull.endereco.cep}") //pega a mensagem de validacao do ValidationMenssages.properties
    @Column(nullable = false, length = 9) // diz que o campo eh obrigatorio e tem tamanho 9
    private String cep;

    @NotNull(message="{NotNull.endereco.numero}") //pega a mensagem de validacao do ValidationMenssages.properties
    @Digits(integer=5, fraction=0)
    @Column(nullable = false, length = 5) // diz que o campo eh obrigatorio e tem tamanho 5
    private Integer numero;

    @Size(max=255)
    private String complemento;

    
    
    
    
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    
    
    
}
