package com.algaworks.brewer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.brewer.validation.SKU;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "cerveja")
public class Cerveja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotBlank(message = "SKU é obrigatório.")
    @SKU
    private String sku;

    private String fileType;
    private String nomeFoto;

    @NotBlank(message = "Nome é obrigatório.")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória.")
    @Size(min=1, max = 50, message = "Tamanho da descrição deve ser entre 1 e 50.")
    private String descricao;

    @NotNull(message = "Valor é obrigatório.")
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "9999999.99", message = "O valor da cerveja deve ser menor que R$9.999.999,99.")
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valor;

    @NotNull(message = "O teor alcoólico é obrigatório")
    @NumberFormat(pattern = "#,##0.00")
    @DecimalMax(value = "100.00", message = "O valor do teor alcoólico deve ser menor que 100.")
    @Column(name = "teor_alcoolico")
    private BigDecimal teorAlcoolico;

    @NotNull(message = "A comissão  é obrigatória.")
    @NumberFormat(pattern = "#,##0.00")
    @DecimalMax(value = "100.00", message = "A comissão deve ser igual ou menor que 100.")
    private BigDecimal comissao;

    @NotNull(message = "A quantidade em estoque é obrigatória.")
    @Column(name = "quantidade_estoque")
    @NumberFormat
    @Max(value = 9999, message = "A quantidade em estoque deve ser menor que 9.999.")
    private Integer quantidadeEstoque;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O sabor é obrigatório.")
    private Sabor sabor;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "A origem é obrigatória.")
    private Origem origem;

    @ManyToOne
    @JoinColumn(name = "codigo_estilo")
    @NotNull(message = "O estilo é obrigatório.")
    private Estilo estilo;

    @PrePersist @PreUpdate
    private void prePersist(){
        sku=sku.toUpperCase();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getTeorAlcoolico() {
        return teorAlcoolico;
    }

    public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
        this.teorAlcoolico = teorAlcoolico;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Sabor getSabor() {
        return sabor;
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cerveja other = (Cerveja) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    public String getNomeFoto() {
        return nomeFoto;
    }

    public void setNomeFoto(String nomeFoto) {
        this.nomeFoto = nomeFoto;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    public String getFotoOuMock(){
        return StringUtils.isEmpty(this.nomeFoto)? "cerveja-mock.png":nomeFoto;
    }
    
}
