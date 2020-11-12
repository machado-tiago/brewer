package com.algaworks.brewer.dto;

public class FotoDto {
    private String nome;
    private byte[] foto;
    private String contentType;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public FotoDto(String nome, byte[] foto, String contentType) {
        this.nome = nome;
        this.foto = foto;
        this.contentType = contentType;
    }
    
    
}
