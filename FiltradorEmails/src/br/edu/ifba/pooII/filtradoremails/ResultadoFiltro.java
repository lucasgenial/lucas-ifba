package br.edu.ifba.pooII.filtradoremails;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import br.edu.ifba.pooII.filtradoremails.execoes.EmailInvalidoException;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtrado;
import br.edu.ifba.pooII.filtradoremails.interfaces.Registro;


/**
 * Classe que trabalha com os resultados do Filtro
 * @author Lucas Matos e Souza
 */
public class ResultadoFiltro implements Serializable, Filtrado, Registro {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = 2957844301717144757L;
    
    /**
     * String que conterá a identificação
     */
    private String identificacao;
    
    /**
     * String que conterá o email
     */
    private String email;
    
    /**
     * Construtor padrão da Classe Resultado Filtro
     */
	public ResultadoFiltro() {
	}
	
	/**
	 * Construtor completo da Classe Resultado Filtro
	 * @param identificacao String contendo a identificacação do professor filtrado
	 * @param email String contendo o email do professor filtrado
	 */
	public ResultadoFiltro(String identificacao, String email) {
		super();
		this.setIdentificacao(identificacao);
		this.setEmail(email);
	}
	
    /**
     * Metodo que recebe uma linha dos arquivos e seta no objeto
     *
     * @param linha do tipo String com o padrão correto
     * @return true se o registro foi inserido no objeto com sucesso, false não
     * foi possivel adicionar o registro no Objeto
     * @throws EmailInvalidoException emails no formato invalido
     */
    @Override
    public boolean stringRegistroParaObjeto(String linha) throws EmailInvalidoException {
        if (linha == null || "".equals(linha)) {
            return false;
        }
        
        //Cria um professor
        Professor novo = new Professor();
        
        //Cria variavel auxiliar
        String[] aux;

        //Recebe a linha e fazendo split divide todas as entradas por vetor
        aux = linha.substring(1, (linha.indexOf(">")) - 1).split("-");

        //Cria os Lists necessários para criar o Objeto Professor;
        String emailR;
        Collection<Curso> cursosR = new HashSet<>();
        Collection<Modalidade> modalidadesR = new HashSet<>();
        Collection<String> periodoR = new HashSet<>();

        //Converte o vetor 5 - Periodos
        String[] p;
        p = aux[0].split(" ");
        periodoR.addAll(Arrays.asList(p)); //seta os periodos no List<String> periodoR

        //Recebe o vetor 3 e converte em um objeto Curso.
        String[] c;
        c = aux[1].split(" ");
        for (String a : c) {
            //seta os cursos no List<curso>
            Curso c1 = new Curso(a);
            cursosR.add(c1);
        }

        //Recebe o vetor 4 e converte em um objeto Modalidade.
        String[] m;
        m = aux[2].split(" ");
        for (String md : m) {
            //seta as modalidades no List<modalidade>
            Modalidade mod = new Modalidade(md);
            modalidadesR.add(mod);
        }

        //Converte o restante da linha para o Objeto!
        emailR = linha.substring((linha.indexOf(">") + 1), linha.length());
        
        novo.setEmail(emailR);
        novo.setPeriodos(periodoR);
        novo.setCursos(cursosR);
        novo.setModalidades(modalidadesR);
               
        return true;
        
    }
    
	/**
     * Metodo que insere o email do professor Filtrado
     * @param email String contendo o email do filtrado
     */
    private void setEmail(String email) {
		this.email = email;
	}

    /**
     * Metodo que insere a identificação do professor Filtrado
     * @param identificacao String contendo a indentificacao do filtrado
     */
	private void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}


	/**
     * Metodo que retorna a identificação do professor Filtrado
     * @return String contendo a identificacao do professor
     */
    @Override
    public String getIdentificacao() {
    	return this.identificacao;
    }

    /**
     * Metodo que retorna o email do professor Filtrado
     * @return String contendo o email do professor
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Método que retorna um Registro apartir de um Filtrado
     *
     * @return Registro apartir de um Filtrado
     */
    @Override
    public Registro getRegistro() {    	
        return this;
    }

    /**
     * Metodo que Escreve no documento de saída dos filtros - output.txt
     * @return String contendo o modelo de escrita no arquivo serializado
     */
    @Override
    public String toStringEscritaArquivo() {
        return "<" + this.getIdentificacao() + "> " + this.getEmail();
    }
    
    
    /**
     * Método que retorna a HashCode
     *
     * @return int numero hashcode
     */
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((identificacao == null) ? 0 : identificacao.hashCode());
		return result;
	}
    
    /**
     * Método equals
     *
     * @param obj do Tipo ResultadoFiltro
     * @return boolean Retorna true quando objetos iguais false quando os objetos forem diferentes
     */
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultadoFiltro other = (ResultadoFiltro) obj;
		if (email == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!email.equals(other.getEmail()))
			return false;
		if (identificacao == null) {
			if (other.getIdentificacao() != null)
				return false;
		} else if (!identificacao.equals(other.getIdentificacao()))
			return false;
		return true;
	}

    /**
     * Método ToString
     *
     * @return String da classe
     */
    @Override
	public String toString() {
		return "ResultadoFiltro2 [identificacao=" + identificacao + ", email="+ email + "]";
	}
    
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}