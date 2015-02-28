package br.edu.ifba.pooII.filtradoremails;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe que modela a entidade Cursos.
 *
 * @author Lucas Matos e Souza
 */
public class Curso implements Serializable, Comparable<Curso> {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = -5815884694280615912L;

    /**
     * String contendo a sigla do Curso
     */
    private String sigla = null;

    /**
     * Construtor padr�o da classe Curso
     */
    public Curso() {
    }

    /**
     * Construtor Completo
     *
     * @param sigla tipo String
     */
    public Curso(String sigla) {
        super();
        this.setSigla(sigla);
    }

    /**
     * M�todo que retorna o nome do Curso
     *
     * @return String com o nome do Curso
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * M�todo que seta a Sigla do Curso
     *
     * @param sigla do tipo String
     */
    private void setSigla(String sigla) {
        this.sigla = sigla;
    }

    /**
     *
     * @param outro objeto do Tipo Curso como paramentro e retorna
     * @return (igual a zero) os objetos s�o iguais
     */
    @Override
    public int compareTo(Curso outro) {
        return 0;
    }

    /**
     * Método que retorna a HashCode do curso
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.getSigla());
        return hash;
    }

    /**
     * Método que retorna se os objetos s�o iguais return true e false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Curso other = (Curso) obj;
        if (!Objects.equals(this.sigla, other.sigla)) {
            return false;
        }
        return true;
    }

    /**
     * M�todo que retorna o toString da Classe
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.getSigla();
    }
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
