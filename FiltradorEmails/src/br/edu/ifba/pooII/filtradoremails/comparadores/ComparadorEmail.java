package br.edu.ifba.pooII.filtradoremails.comparadores;

import java.util.Comparator;

import br.edu.ifba.pooII.filtradoremails.interfaces.Filtrado;
/**
 * Classe que implementa o Comparador de Email
 * @author Teste
 */
public class ComparadorEmail implements Comparator<Filtrado> {
	/**
	 * Método que compara os e-mail
         * @param f1 do tipo Filtrado
         * @param f2 do Tipo Filtrado
	 */
	@Override
	public int compare(Filtrado f1, Filtrado f2) {
		return f1.getEmail().compareTo(f2.getEmail());
	}

}