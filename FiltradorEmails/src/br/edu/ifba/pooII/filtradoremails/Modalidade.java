package br.edu.ifba.pooII.filtradoremails;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe que modela as modalidades dos cursos
 *
 * @author Lucas Matos e Souza
 */
public class Modalidade implements Serializable, Comparable<Modalidade> {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = 1886429512927284734L;

    /**
     * Nome da Modalidade
     */
    private String nomeModalidade;

    //Construtores
    /**
     * Construtor Completo
     *
     * @param nomeModalidade String com o nome da modalidade
     */
    public Modalidade(String nomeModalidade) {
        this.nomeModalidade = nomeModalidade;
    }

    /**
     * Construtor Padr√£o
     */
    public Modalidade() {
    }

    /**
     * MÈtodo que retorna o nome da modalidade
     *
     * @return String com o nome da Modalidade
     */
    public String getNomeModalidade() {
        return nomeModalidade;
    }

    /**
     * MÈtodo que seta a modalidade
     *
     * @param nomeModalidade String com o nome da modalidade
     */
    public void setNomeModalidade(String nomeModalidade) {
        this.nomeModalidade = nomeModalidade;
    }

    /**
     * MÈtodo que Compara a modalidade pelo nome<br>
     * MÈtodo gerado pois a classe modalidade implementa o
     * Comparable(Modalidade)
     *
     * @return int 0 se iguais, qualquer outro valor diferentes
     * @param outro outra modalidade
     */
    @Override
    public int compareTo(Modalidade outro) {
        return (this.getNomeModalidade().compareTo(outro.getNomeModalidade()));
    }

    /**
     * MÈtodo que gera HashCode da classe
     *
     * @return int hashCode da classe
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.getNomeModalidade());
        return hash;
    }

    /**
     * MÈtodo Equals
     *
     * @return true se objetos iguais false se objetos diferentes
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Modalidade other = (Modalidade) obj;
        if (!Objects.equals(this.nomeModalidade, other.nomeModalidade)) {
            return false;
        }
        return true;
    }

    /**
     * MÈtodo Tostring
     *
     * @return retorna String contendo todos os dados da Classe
     */
    @Override
    public String toString() {
        return this.getNomeModalidade();
    }
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
