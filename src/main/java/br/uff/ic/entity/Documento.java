package br.uff.ic.entity;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by guilherme on 07/05/17.
 */
@Entity
public class Documento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long identificador;

    @Column
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dataModificacao;

    @Column
    private String conteudo;

    public Documento() {
    }

    public Documento(LocalDate dataModificacao) {
        this(dataModificacao, null);
    }

    public Documento(LocalDate dataModificacao, String conteudo) {
        this.dataModificacao = dataModificacao;
        this.conteudo = conteudo;
    }

    public long getIdentificador() {
        return identificador;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Documento documento = (Documento) o;

        if (getIdentificador() != documento.getIdentificador()) return false;
        return getConteudo().equals(documento.getConteudo());
    }

    @Override
    public int hashCode() {
        int result = (int) (getIdentificador() ^ (getIdentificador() >>> 32));
        result = 31 * result + getConteudo().hashCode();
        return result;
    }
}
