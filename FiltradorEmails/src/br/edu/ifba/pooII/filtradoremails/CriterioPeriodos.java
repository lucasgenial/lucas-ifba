package br.edu.ifba.pooII.filtradoremails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import br.edu.ifba.pooII.filtradoremails.interfaces.Criterio;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtravel;

/**
 * Classe armazena o critério a ser atendido para períodos.<br>
 * Classe Trabalha com os Criterios de Periodos que serão colocados<br>
 * no filtro, a classe trabalha com HashSet, apesar de Passar um Collection<br>
 *
 * @author Lucas Matos e Souza
 */
public class CriterioPeriodos implements Criterio, Serializable {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = -3328373998344053485L;

    /**
     * HashSet de String
     */
    private Collection<String> periodos = new HashSet<>();

    /**
     * Construtor Completo da Classe Criterio Periodo
     *
     * @param listaCriteriosPeriodos do tipo Set String
     */
    public CriterioPeriodos(Collection<String> listaCriteriosPeriodos) {
        super();
        periodos.addAll(listaCriteriosPeriodos);
    }

    /**
     * Construtor Padrão
     */
    public CriterioPeriodos() {

    }

    /**
     * Método que verifica se o Criterio Periodo passado é válido!
     *
     * @param f Filtravel
     * @return true se f for valido false caso contrario.
     */
    @Override
    public boolean ehValido(Filtravel f) {
        if (f == null || this.getPeriodos() == null) {
            return false;
        }

        for (String aux : getPeriodos()) {
            for (String per : f.getPeriodos()) {
                if (per.hashCode() == aux.hashCode()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Método que retorna um Collection HashSet<br>
     * Com todos os criterios periodos cadastrados.
     *
     * @return Collection
     */
    public Collection<String> getPeriodos() {
        return this.periodos;
    }

    /**
     * @param periodos the periodos to set
     */
    public void setPeriodos(Collection<String> periodos) {
        this.periodos = periodos;
    }
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
