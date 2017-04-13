package br.uff.ic.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by guilherme on 13/04/17.
 */
@Entity
public class TesteJavers {
    @Id
    private long identificador;
    private String value;

    public TesteJavers() {
    }

    public TesteJavers(long identificador, String value) {
        this();
        this.identificador = identificador;
        this.value = value;
    }

    public long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(long identificador) {
        this.identificador = identificador;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
