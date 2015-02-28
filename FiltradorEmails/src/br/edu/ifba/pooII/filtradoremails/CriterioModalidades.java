package br.edu.ifba.pooII.filtradoremails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import br.edu.ifba.pooII.filtradoremails.interfaces.Criterio;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtravel;

/**
 * Classe armazena o critério a ser atendido para modalidades. Classe Trabalha
 * com os Criterios de Modalidades que serão colocados no filtro, a classe
 * trabalha com HashSet, apesar de Passar um Collection
 *
 * @author Lucas Matos e Souza
 */
public class CriterioModalidades implements Criterio, Serializable {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = 1757546096536447135L;

    /**
     * HashSet de Modalidades
     */
    private Collection<Modalidade> modalidades = new HashSet<>();

    /**
     * Construtor Completo da Classe Criterio Modalidade
     *
     * @param listaCriteriosModalidade do tipo Set Modalidade
     */
    public CriterioModalidades(Collection<Modalidade> listaCriteriosModalidade) {
        super();
        modalidades.addAll(listaCriteriosModalidade);
    }

    /**
     * Construtor Padrão
     */
    public CriterioModalidades() {

    }

    /**
     * Método que verifica se o Criterio Modalidade passado é válido!
     *
     * @param f Filtravel
     * @return true se f for valido false caso contrario.
     */
    @Override
    public boolean ehValido(Filtravel f) {
        if (f == null || this.getModalidades() == null) {
            return false;
        }

        for (Modalidade aux : getModalidades()) {
            for (Modalidade s : f.getModalidade()) {
                if (s.toString().equalsIgnoreCase(aux.toString())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Método que retorna um Collection HashSet Com todos os criterios
     * modalidade cadastrados.
     *
     * @return Collection
     */
    public Collection<Modalidade> getModalidades() {
        return this.modalidades;
    }

    /**
     * Metodo HashCode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((getModalidades() == null) ? 0 : getModalidades().hashCode());
        return result;
    }

    /**
     * Metodo equals da Classe CriterioModalidades
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CriterioModalidades other = (CriterioModalidades) obj;
        if (getModalidades() == null) {
            if (other.getModalidades() != null) {
                return false;
            }
        } else if (!modalidades.equals(other.modalidades)) {
            return false;
        }
        return true;
    }

    /**
     * @param modalidades the modalidades to set
     */
    public void setModalidades(Collection<Modalidade> modalidades) {
        this.modalidades = modalidades;
    }
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
