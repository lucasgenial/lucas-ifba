package br.edu.ifba.pooII.filtradoremails.interfaces;

/**
 * Estabele comportamentos comuns para itens a serem de um filtro
 * @author Lucas Matos e Souza
 */
public interface ItemFiltro {
	// identificacao segue padrao predefinido
	public String getIdentificacao();

	//Get do Email
	public String getEmail();
}