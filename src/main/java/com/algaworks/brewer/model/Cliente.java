package com.algaworks.brewer.model;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


public class Cliente {
    @Id
    @NotBlank(message = "O documento é obrigatório")
    private String documento;
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    private Boolean tipoPessoa;
    private String telefone;
    private String email;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String cep;
    private String estado;
    private String cidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Boolean tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    
}
