package br.edu.ifba.pooII.filtradoremails.interfaces;

/*
 * Filtraveis e filtrados devem ter comportamentos comuns,
 * ja que a partir de um filtravel obtem-se um filtrado.
 * Estes comportamentos sao definidos em ItemFiltro
 */
/**
 * Interface Filtrado extends ItemFiltro
 * A exemplo da interface Serializable, mas nao necessariamente pelo menos
 * motivo, esta interface nao define nenhum metodo.
 * @author Lucas Matos e Souza
 */

public interface Filtrado extends ItemFiltro {
	/**
	 * A exemplo da interface Serializable, mas nao necessariamente pelo menos
	 * motivo, esta interface nao define nenhum metodo.
	 */
	
	/**
	* Deseja-se ter um elo entre Filtrado e registro,
	* visto que o que se deseja eh armazenadar os
	* filtrados no output.txt para uso posterior.
	* @return Registro Registro 
	* */
	public Registro getRegistro();

}