package br.uff.ic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by guilherme on 03/06/17.
 */
@Entity
public class Editor implements Serializable{
    @Id
    @Column(name = "edi_id")
    private long identificador;

    @Column(name = "edi_nome")
    private String nome;

    @Column(name = "edi_versao")
    private String versao;

    public Editor() {
    }

    public Editor(String nome, String versao) {
        this();
        this.nome = nome;
        this.versao = versao;
        this.identificador = 1L;
    }

    public Editor(Editor editor) {
        this(editor.getNome(), editor.getVersao());
    }

    public long getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Editor{");
        sb.append("identificador=").append(identificador);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", versao='").append(versao).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
