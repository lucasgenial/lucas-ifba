package br.edu.ifba.pooII.filtradoremails.interfaces;
/**
 * Interface de Criterio
 * @author Lucas Matos e Souza
 */
public interface Criterio {
	/**
	 * Recebe um filtravel e retorna indicando se ele eh valido
	 *
	 * @param f Filtravel
	 * @return true se f for valido false caso contrario.
	 */
	public boolean ehValido(Filtravel f);
}