package br.edu.ifba.pooII.filtradoremails.execoes;

/**
 * Classe que implementa a exceção do Filtro Não Localizado
 *
 * @author Lucas Matos e Souza
 *
 */
public class FiltroNaoLocalizadoException extends Exception {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = -6479110208960028913L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    private String mensagem;

    /**
     * Construtor Completo
     *
     * @param mensagem String
     */
    public FiltroNaoLocalizadoException(String mensagem) {
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
}
