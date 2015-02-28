package br.edu.ifba.pooII.filtradoremails.execoes;

/**
 * Classe que implementa a exceção de Padrão de Registro de Entrada Invalida
 *
 * @author Lucas Matos e Souza
 *
 */
public class PadraoRegistroEntradaInvalidoException extends Exception {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = 3844824153478513981L;
    
    /**
     * Mensagem de Erro
     */
    private String mensagem;

    /**
     * Construtor Completo
     *
     * @param mensagem String
     */
    public PadraoRegistroEntradaInvalidoException(String mensagem) {
        this.mensagem = mensagem;
    }
    
    /**
     * Mensagem de Erro
     * @return Mensagem de Erro
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
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
