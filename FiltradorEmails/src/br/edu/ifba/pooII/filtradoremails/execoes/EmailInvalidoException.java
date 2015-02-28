package br.edu.ifba.pooII.filtradoremails.execoes;

/**
 * Classe que implenta a exceção do email invalido
 *
 * @author Lucas Matos e Souza
 *
 */
public class EmailInvalidoException extends Exception {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = 7126785270331226595L;
    
    /**
     * Mensagem do Erro
     */
    private String mensagem;

    /**
     * Construtor Completo
     *
     * @param mensagem String
     */
    public EmailInvalidoException(String mensagem) {
        this.mensagem = mensagem;
    }

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
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
