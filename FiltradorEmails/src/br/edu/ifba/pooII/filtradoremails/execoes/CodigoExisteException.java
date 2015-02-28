package br.edu.ifba.pooII.filtradoremails.execoes;

/**
 * Classe que implementa a Exception CodigoExisteException <br>
 * ele é chamada sempre que um código lançado já existe
 *
 * @author Teste
 *
 */
public class CodigoExisteException extends Exception {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = -7763662971027121518L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * String com a mensagem do erro
     */
    private String mensagem;

    /**
     * Construtor Completo
     *
     * @param mensagem String
     */
    public CodigoExisteException(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * Insere a Mensagem de Codigo Existente
     *
     * @return String
     */
    @Override
    public String getMessage() {
        return this.getMensagem();
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
