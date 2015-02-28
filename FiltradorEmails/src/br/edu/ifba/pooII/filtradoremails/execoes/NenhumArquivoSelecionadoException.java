package br.edu.ifba.pooII.filtradoremails.execoes;

/**
 * Classe que implementa a exceção Nenhum Arquivo Selecionado
 *
 * @author Lucas Matos e Souza
 *
 */
public class NenhumArquivoSelecionadoException extends Exception {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = -8009309886902923619L;
    
    /**
     * Mensagem de Erro
     */
    private String mensagem;

    /**
     * Construtor Completo
     *
     * @param mensagem String
     */
    public NenhumArquivoSelecionadoException(String mensagem) {
        this.mensagem = mensagem;
    }
    
    /**
     * Mensagem de Erro
     * @return String Mensagem de erro
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
