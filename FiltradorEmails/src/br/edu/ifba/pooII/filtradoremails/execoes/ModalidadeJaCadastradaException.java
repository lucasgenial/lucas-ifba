package br.edu.ifba.pooII.filtradoremails.execoes;

/**
 * Classe que Implementa a exceção Modalidade Já Cadastrada
 *
 * @author Lucas Matos e Souza
 *
 */
public class ModalidadeJaCadastradaException extends Exception {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = -7309301022236953481L;
    
    /**
     * Mensagem de Erro
     */
    private String mensagem;

    /**
     * Construtor Completo
     *
     * @param mensagem String
     */
    public ModalidadeJaCadastradaException(String mensagem) {
        this.mensagem = mensagem;
    }
    
    /**
     * Mensagem de Erro
     * @return String mensagem de erro
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
