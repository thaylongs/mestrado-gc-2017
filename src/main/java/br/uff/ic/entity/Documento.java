package br.uff.ic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by guilherme on 07/05/17.
 */
@Entity
public class Documento implements Serializable{
    @Id
    @Column(name = "doc_id")
    private long identificador;

    @Column(name = "doc_datamodificacao")
    private String dataModificacao;

    @Column(name = "doc_titulo")
    private String titulo;

    @Column(name = "doc_conteudo")
    private String conteudo;

    public Documento() {
    }

    public Documento(String dataModificacao) {
        this(dataModificacao, null, null);
    }

    public Documento(String dataModificacao, String conteudo) {
        this(dataModificacao, conteudo, null);
    }

    public Documento(String dataModificacao, String titulo, String conteudo) {
        this.dataModificacao = dataModificacao;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.identificador = 1L;
    }

    public long getIdentificador() {
        return identificador;
    }

    public String getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(String dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
