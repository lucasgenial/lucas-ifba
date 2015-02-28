package br.edu.ifba.pooII.filtradoremails.comparadores;

import java.util.Comparator;

import br.edu.ifba.pooII.filtradoremails.interfaces.Filtrado;
/**
 * Classe que implementa o Comparador pela Identificação
 * @author Teste
 */
public class ComparadorIdentificacao implements Comparator<Filtrado> {
	/**
	 * Método que compara as identificações
         * @param f1 do tipo Filtrado
         * @param f2 do Tipo Filtrado
	 */
	@Override
	public int compare(Filtrado f1, Filtrado f2) {
		return f1.getIdentificacao().compareTo(f2.getIdentificacao());
	}
}