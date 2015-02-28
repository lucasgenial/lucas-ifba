package br.edu.ifba.pooII.filtradoremails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import br.edu.ifba.pooII.filtradoremails.execoes.FiltroNaoLocalizadoException;
import br.edu.ifba.pooII.filtradoremails.interfaces.Criterio;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtrado;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtravel;

import java.util.Objects;

/**
 * Classe que modela o filtro. Essa classe será chamada quando os e-mails forem
 * filtrados
 *
 * @author Lucas Matos e Souza
 * @version 1.0
 */
public class Filtro implements Serializable {
    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = 8770037869985699273L;
    // os criterios a serem aplicados no filtro

    /**
     * Collection que guardará todos os critérios a serem usados no filtro
     */
    private Collection<Criterio> criterios;

    /**
     * Construtor padrao do Filtro
     */
    public Filtro() {
    }

    /**
     * Construtor completo do Filtro
     *
     * @param criterios Collection do Tipo Criterio
     */
    public Filtro(Collection<Criterio> criterios) {
        super();
        criterios = new HashSet<>();
        this.criterios.addAll(criterios);
    }

    /**
     * Metodo que retorna todos os criterios do filtro
     *
     * @return Collection retorna os HashSet contendo os Critérios passados para
     * o Filtro
     */
    public Collection<Criterio> getCriterios() {
        if (criterios == null) {
            criterios = new HashSet<>();
        }

        return this.criterios;
    }

    /**
     * Filtra os Registros (Filtravel) para (Filtrado)
     *
     * @param filtraveis ArrayList do Tipo Filtravel
     * @return Collection de Filtrados contendo todos os critérios do filtro
     * @throws FiltroNaoLocalizadoException Exceção do Filtro não Localizado
     */
    public Collection<Filtrado> getFiltrados(Collection<Filtravel> filtraveis) throws FiltroNaoLocalizadoException {
    	Collection<Filtrado> filtrado = new HashSet<>();

        int cont = 0;

        if (getCriterios() == null) {
            for (Filtravel f : filtraveis) {
            	filtrado.add(new ResultadoFiltro(f.getIdentificacao(), f.getEmail()));
                cont++;
            }
        } else {
            for (Filtravel f : filtraveis) {
                boolean valido = true;
                for (Criterio c : this.getCriterios()) {
                    //Verifica se o criterio passado é válido!
                    if (!c.ehValido(f)) {
                        valido = false;
                        break;
                    }
                }
                //Se valido o filtravel é adicionado ao filtrado
                if (valido) {
                    cont++;
                    filtrado.add(new ResultadoFiltro(f.getIdentificacao(), f.getEmail()));
                }
            }
        }
        if (cont == 0) {
            throw new FiltroNaoLocalizadoException("Filtro não localizado.");
        }
        return filtrado;
    }

    /**
     * Metodo Hashcode
     *
     * @return int numero hashcode
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.getCriterios());
        return hash;
    }

    /**
     * MÃ©todo Equals
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
        final Filtro other = (Filtro) obj;
        if (!Objects.equals(this.criterios, other.criterios)) {
            return false;
        }
        return true;
    }

    /**
     * Método Tostring
     *
     * @return retorna String contendo todos os dados da Classe
     */
    @Override
    public String toString() {
        return "Filtro{" + "criterios=" + getCriterios() + '}';
    }

    /**
     * @param criterios the criterios to set
     */
    public void setCriterios(Collection<Criterio> criterios) {
        this.criterios = criterios;
    }
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
