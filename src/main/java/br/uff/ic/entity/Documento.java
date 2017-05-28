package br.uff.ic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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

    @Lob
    @Column(name = "doc_conteudo")
    private String conteudo;

    @Column(name = "doc_produzidopor")
    private String produzidoPor;

    @Column(name = "doc_versaoeditor")
    private String versaoEditor;

    public Documento() {
    }

    public Documento(String dataModificacao) {
        this(dataModificacao, null, null, null, null);
    }

    public Documento(String dataModificacao, String conteudo) {
        this(dataModificacao, conteudo, null, null, null);
    }

    public Documento(String dataModificacao, String titulo, String conteudo, String editor, String versaoEditor){
        this.dataModificacao = dataModificacao;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.identificador = 1L;
        this.produzidoPor = editor;
        this.versaoEditor = versaoEditor;
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

    public String getProduzidoPor() {
        return produzidoPor;
    }

    public void setProduzidoPor(String produzidoPor) {
        this.produzidoPor = produzidoPor;
    }

    public String getVersaoEditor() {
        return versaoEditor;
    }

    public void setVersaoEditor(String versaoEditor) {
        this.versaoEditor = versaoEditor;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Documento{");
        sb.append("identificador=").append(identificador);
        sb.append(", dataModificacao='").append(dataModificacao).append('\'');
        sb.append(", titulo='").append(titulo).append('\'');
        sb.append(", conteudo='").append(conteudo).append('\'');
        sb.append(", produzidoPor='").append(produzidoPor).append('\'');
        sb.append(", versaoEditor='").append(versaoEditor).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
