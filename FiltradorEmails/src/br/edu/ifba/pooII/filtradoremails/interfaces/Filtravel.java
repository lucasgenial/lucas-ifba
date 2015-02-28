package br.edu.ifba.pooII.filtradoremails.interfaces;

import java.util.Collection;

import br.edu.ifba.pooII.filtradoremails.Curso;
import br.edu.ifba.pooII.filtradoremails.Modalidade;

/*
* Filtraveis e filtrados devem ter comportamentos comuns,
* ja que a partir de um filtravel obtem-se um filtrado.
* Estes comportamentos sao definidos em ItemFiltro
*/
/**
 * Interface Filtravel extends ItemFiltro
 * @author Lucas Matos e Souza
 */
public interface Filtravel extends ItemFiltro {

	/*
	* informacao(oes) necessaria(s) para filtraveis e filtrados, uma vez que a
	* partir de um filtravel se obtem um filtrado
	*/
	public int getCodigo();
	public String getNome();
	public Collection<Curso> getCurso();
	public Collection<Modalidade> getModalidade();
	public Collection<String> getPeriodos();
	public String toStringEscritaArquivo();
	
	/*
	* Cria um objeto resultante de um filtro a partir de si. Este novo objeto
	* nao eh um filtravel.
	*
	* @return um novo objeto filtrado
	*/
	public Filtrado getRegistroFiltrado();
}