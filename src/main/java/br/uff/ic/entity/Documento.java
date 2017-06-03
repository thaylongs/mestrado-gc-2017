package br.uff.ic.entity;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "editor_id")
    private Editor editor;

    @Column(name = "doc_inconf_erros")
    private Integer inconf_erros;

    @Column(name = "doc_inconf_avisos")
    private Integer inconf_avisos;

    @Column(name = "doc_inconf_notifs")
    private Integer inconf_notifs;

    public Documento() {
    }

    public Documento(String dataModificacao) {
        this(dataModificacao, null, null, null, null);
    }

    public Documento(String dataModificacao, String conteudo) {
        this(dataModificacao, conteudo, null, null, null);
    }

    public Documento(String dataModificacao, String titulo, String conteudo, Editor editor, Integer[] inconformidades){
        this.dataModificacao = dataModificacao;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.identificador = 1L;
        this.editor = editor;
        this.inconf_erros = inconformidades[0];
        this.inconf_avisos = inconformidades[1];
        this.inconf_notifs = inconformidades[2];
    }

    public long getIdentificador() {
        return identificador;
    }

    public String getDataModificacao() {
        return dataModificacao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Editor getEditor() {
        return editor;
    }

    public int getInconf_erros() {
        return inconf_erros;
    }

    public int getInconf_avisos() {
        return inconf_avisos;
    }

    public int getInconf_notifs() {
        return inconf_notifs;
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
        sb.append(", editor=").append(editor);
        sb.append(", erros=").append(inconf_erros);
        sb.append(", avisos=").append(inconf_avisos);
        sb.append(", notifs=").append(inconf_notifs);
        sb.append('}');
        return sb.toString();
    }
}
