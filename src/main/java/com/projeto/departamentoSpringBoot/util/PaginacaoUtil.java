package com.projeto.departamentoSpringBoot.util;

import java.util.List;

public class PaginacaoUtil<T> {

    private int tamanho; //armazena o numero de linhas da pagina
    private int pagina; //armazena o numero da pagina atual selecionada "to na pagina 2 ele armazena o numero 2"
    private Long totalDePaginas; //armazena o total de paginas da aplicacao
    private String direcao;
    private List<T> registros; //Recebe o resultado da consulta no banco de dados

    
    
    public PaginacaoUtil(int tamanho, int pagina, Long totalDePaginas,String direcao, List<T> registros) {
        super();
        this.tamanho = tamanho;
        this.pagina = pagina;
        this.totalDePaginas = totalDePaginas;
        this.direcao = direcao;
        this.registros = registros;
    }

    
    
    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public Long getTotalDePaginas() {
        return totalDePaginas;
    }

    public void setTotalDePaginas(Long totalDePaginas) {
        this.totalDePaginas = totalDePaginas;
    }

    public List<T> getRegistros() {
        return registros;
    }

    public void setRegistros(List<T> registros) {
        this.registros = registros;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    
    
    
}
