package br.edu.ifba.pooII.filtradoremails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.edu.ifba.pooII.filtradoremails.execoes.EmailInvalidoException;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtrado;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtravel;
import br.edu.ifba.pooII.filtradoremails.interfaces.Registro;

import java.util.Arrays;

/**
 * Classe que modela a entidade professor, que por sua vez tem Cursos,
 * Modalidades
 *
 * @author Lucas Matos e Souza
 * @version 1.0
 */
public class Professor implements Filtravel, Serializable, Registro, Comparable<Professor> {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = 2623805435096172070L;
    
    /**
     * Codigo do Professor
     * Nao se deve permitir cadastrar 2 professores com codigos iguais
     */
    // 
    private int codigo;
    
    /**
     * Nome do Professor
     */
    private String nome;
    
    /**
     * Email do professor
     * O campo email deve ser validado.Caso seja informado um email
     * invalido deve ser gerada a exceção EmailInvalidoException.
     */
    private String email;
    
    /**
     * Collection do tipo HashSet que contem todos os cursos no qual o professor ministra
     */
    private Collection<Curso> cursos = new HashSet<>();
    
    /**
     * Collection do tipo HashSet contem todas as modalidades em que o professor ministra os cursos
     */
    private Collection<Modalidade> modalidades = new HashSet<>();
    
    /**
     * Collection do tipo HashSet contem todas as modalidade em que o professor ministra os cursso
     */
    private Collection<String> periodos = new HashSet<>(); 

    /**
     * Construtor PadrÃ£o
     */
    public Professor() {
    }

    /**
     * Construtor Completo
     *
     * @param codigo int contendo o código do registro
     * @param nome String contendo o nome do Professor
     * @param email String contendo o email
     * @param cursos Collection contendo os cursos
     * @param modalidades Collection contendo as modalidades
     * @param periodos perido do tipo SetString
     * @throws EmailInvalidoException Email Invalido
     */
    public Professor(int codigo, String nome, String email, Collection<Curso> cursos, Collection<Modalidade> modalidades, Collection<String> periodos) throws EmailInvalidoException {
        this.setCodigo(codigo);
        this.setNome(nome);
        this.setCursos(cursos);
        this.setModalidades(modalidades);
        this.setEmail(email);
        this.setPeriodos(periodos);
    }

    //GETS E SET    
    /**
     * Metodo que retorna o nome do professor
     *
     * @return String Contendo o nome do professor
     */
    @Override
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo que cadastra o nome do professor
     *
     * @param nome String contendo o nome do professor
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo que retorna o codigo do professor
     *
     * @return int inteiro contendo o código do professor
     */
    @Override
    public int getCodigo() {
        return codigo;
    }

    /**
     * Metodo que cadastra o código do professor
     *
     * @param codigo inteiro com o numero do código
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Metodo que cadastra os emails que o professor possui
     *
     * @param emailR Email a cadastra
     * @exception EmailInvalidoException Exceção do tipo Email Invalido
     */
    public void setEmail(String emailR) throws EmailInvalidoException {
        if (validaEmailOficial(emailR)) {
            this.email = emailR;
        } else if (validaEmail(emailR)) {
            this.email = emailR;
        } else {
            this.email = null;
            throw new EmailInvalidoException("Email Invalido!");
        }
    }

    /**
     * Valida o Email do professor
     *
     * @param emailR Email para cadastrar (String)
     * @return boolean true se email valido
     */
    public boolean validaEmail(String emailR) {
        if (emailR == null) {
            return false;
        }

        Pattern pattern;
        Matcher matcher = null;

        if (emailR.trim().length() == 0) {
            return false;
        }

        String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
        pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(emailR);
        return matcher.matches();
    }

    /**
     * Metodo que cadastra que cadastra todos os cursos em que o professor leciona
     *
     * @param cursosAdicionado Collection (Curso) -  adicionar
     */
    public void setCursos(Collection<Curso> cursosAdicionado) {
        this.cursos.addAll((Set<Curso>) cursosAdicionado);
    }

    /**
     * Metodo que cadastra todas as modalidades em que o professor leciona
     *
     * @param modalidadesAdicionado Collection (Modalidade) - adicionar
     */
    public void setModalidades(Collection<Modalidade> modalidadesAdicionado) {
        this.modalidades.addAll((Set<Modalidade>) modalidadesAdicionado);
    }

    /**
     * Metodo que cadastra todos os periodos de cursos em que o professor
     * leciona
     *
     * @param periodosAdicionado Collection contendo os periodos (String) - adicionar
     */
    public void setPeriodos(Collection<String> periodosAdicionado) {
        this.periodos.addAll((Set<String>) periodosAdicionado);
    }

    //CODIGO IMPLEMENTADOS
    /**
     * Metodo que retorna um HashSet contendo todos os cursos ministrados pelo professor
     *
     * @return HashSet de Cursos
     */
    @Override
    public Collection<Curso> getCurso() {
        return this.getCursos();
    }

    /**
     * Metodo que retorna um HashSet contendo todas as modalidades cadastradas no professor
     *
     * @return HashSet de Modalidades
     */
    @Override
    public Collection<Modalidade> getModalidade() {
        return this.getModalidades();
    }

    /**
     * Metodo que retorna um HashSet contendo todos os periodos cadastrados no professor
     *
     * @return HashSet de String
     */
    @Override
    public Collection<String> getPeriodos() {
        return this.periodos;
    }

    /**
     * Metodo que retorna um Filtrado
     *
     * @return Filtrado
     */
    @Override
    public Filtrado getRegistroFiltrado() {
        return (Filtrado) this;
    }

    /**
     * Metodo que retorna a identificação do professor
     *
     * @return String contendo a Identificação do objeto
     */
    @Override
    public String getIdentificacao() {

        String periodosI = "";
        for (String p : this.getPeriodos()) {
            periodosI = periodosI + p + " ";
        }

        String cursosI = "";
        for (Curso p : this.getCurso()) {
            cursosI = cursosI + p.toString() + " ";
        }

        String modalidadesI = "";
        for (Modalidade p : this.getModalidade()) {
            modalidadesI = modalidadesI + p.toString() + " ";
        }
        
        return periodosI.substring(0, periodosI.length() - 1) + "-"
                + cursosI.substring(0, cursosI.length() - 1) + "-"
                + modalidadesI.substring(0, modalidadesI.length() - 1);
    }

    /**
     * Metodo que retorna uma HashSet contendo todos os emails cadastrados
     *
     * @return HashSet de String contendo os emails cadastrados
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Metodo que retorna uma String que será escrita no documento de saída
     *
     * @return String
     */
    @Override
    public String toStringEscritaArquivo() {
        String periodosI = "";
        for (String p : this.getPeriodos()) {
            periodosI = periodosI + p + " ";
        }

        String cursosI = "";
        for (Curso p : this.getCurso()) {
            cursosI = cursosI + p.toString() + " ";
        }

        String modalidadesI = "";
        for (Modalidade p : this.getModalidade()) {
            modalidadesI = modalidadesI + p.toString() + " ";
        }

        return this.getCodigo() + ";" + this.getNome() + ";" + this.getEmail() + ";"
                + cursosI.substring(0, cursosI.length() - 1) + ";"
                + modalidadesI.substring(0, modalidadesI.length() - 1) + ";"
                + periodosI.substring(0, periodosI.length() - 1);
    }

    /**
     * Metodo que pega uma linha dos arquivos e seta os atributos no professor
     *
     * @return Boolean true se o registro foi passado corretamente para o e-mail
     * @throws EmailInvalidoException Exceção do tipo Email Inválido
     */
    @Override
    public boolean stringRegistroParaObjeto(String linha) throws EmailInvalidoException {
        if (linha == null || "".equals(linha)) {
            return false;
        }

        //Cria variavel auxiliar
        String[] aux;

        //Recebe a linha e fazendo split divide todas as entradas por vetor
        aux = linha.split(";");

        //Cria os Lists necessários para criar o Objeto Professor;
        String nomeR;
        String emailR;
        Set<Curso> cursosR = new HashSet<>();
        Set<Modalidade> modalidadesR = new HashSet<>();
        Set<String> periodoR = new HashSet<>();

        //Converte o vetor 0 - Transforma string em inteiro - Codigo
        int codigoR = Integer.parseInt(aux[0]);

        //Converte o vetor 1 - Nome
        nomeR = aux[1];

        //Converte o vetor 2  - Email
        emailR = aux[2];

        //Recebe o vetor 3 e converte em um objeto Curso.
        String[] c;
        c = aux[3].split(" ");
        for (String a : c) {
            //seta os cursos no List<curso>
            Curso c1 = new Curso(a);
            cursosR.add(c1);
        }

        //Recebe o vetor 4 e converte em um objeto Modalidade.
        String[] m;
        m = aux[4].split(" ");
        for (String md : m) {
            //seta as modalidades no List<modalidade>
            Modalidade mod = new Modalidade(md);
            modalidadesR.add(mod);
        }

        //Converte o vetor 5 - Periodos
        String[] p;
        p = aux[5].split(" ");
        periodoR.addAll(Arrays.asList(p)); //seta os periodos no List<String> periodoR

        this.setCodigo(codigoR);
        this.setNome(nomeR);
        this.setEmail(emailR);
        this.setCursos(cursosR);
        this.setModalidades(modalidadesR);
        this.setPeriodos(periodoR);

        return true;
    }

    /**
     * Metodo compareTO, este compara os professores pelo código
     *
     * @param professor Objeto do tipo Professor
     * @return int 0 se igual
     */
    @Override
    public int compareTo(Professor professor) {
        return (this.getCodigo() - professor.getCodigo());
    }

    /**
     * Metodo que retorna o TOString
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Professor{" + "codigo=" + this.getCodigo() + ", nome=" + this.getNome() + ", email="
                + this.getEmail() + ", cursos=" + this.getCurso() + ", modalidades=" + this.getModalidade()
                + ", periodos=" + this.getPeriodos() + '}';
    }

    /**
     * Metodo que retorna o HashCode
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.getCodigo();
        return hash;
    }

    /**
     * Metodo que retorna o equals
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Professor other = (Professor) obj;
        return this.getCodigo() == other.getCodigo();
    }

    /**
     * Metodo que retorna a validação do email oficial ou não oficial
     *
     * @return boolean true se o email for oficial false se o email não for oficial ou inválido
     * @param emailR String contendo o email do registro
     */
    public static boolean validaEmailOficial(String emailR) {
        return "@ifba.edu.br".equalsIgnoreCase(emailR.substring(emailR.indexOf("@"), emailR.length()));
    }

    /**
     * @return the cursos
     */
    public Collection<Curso> getCursos() {
        return cursos;
    }

    /**
     * @return the modalidades
     */
    public Collection<Modalidade> getModalidades() {
        return modalidades;
    }
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
