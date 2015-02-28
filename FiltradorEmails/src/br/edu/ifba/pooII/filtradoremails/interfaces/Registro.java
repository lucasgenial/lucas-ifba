package br.edu.ifba.pooII.filtradoremails.interfaces;

import br.edu.ifba.pooII.filtradoremails.execoes.EmailInvalidoException;

/**
 * Interface Registro
 * @author Lucas Matos e Souza
 */
public interface Registro {
	/**
	 * Retorna uma String a ser escrita no arquivo, conforme padrao predefinido
	 * de campos
	 *
	 * @return uma string no padrao
	 */
	public String toStringEscritaArquivo();

	/**
	 * A partir de uma string predefinida extrai as informacoes e seta os campos
	 * do registro
	 *
	 * @param linha String
	 * @return true caso os campos sejam setados, false caso contrario.
	 * @throws EmailInvalidoException Lançado quando o email for invalido
	 */
	public boolean stringRegistroParaObjeto(String linha) throws EmailInvalidoException;
}
