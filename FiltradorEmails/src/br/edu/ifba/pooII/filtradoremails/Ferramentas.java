package br.edu.ifba.pooII.filtradoremails;

import br.edu.ifba.pooII.filtradoremails.comparadores.ComparadorEmail;
import br.edu.ifba.pooII.filtradoremails.comparadores.ComparadorIdentificacao;
import br.edu.ifba.pooII.filtradoremails.execoes.CodigoExisteException;
import br.edu.ifba.pooII.filtradoremails.execoes.EmailInvalidoException;
import br.edu.ifba.pooII.filtradoremails.execoes.FiltroNaoLocalizadoException;
import br.edu.ifba.pooII.filtradoremails.execoes.NenhumArquivoSelecionadoException;
import br.edu.ifba.pooII.filtradoremails.execoes.PadraoRegistroEntradaInvalidoException;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtrado;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtravel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A classe ferramenta foi criada com a inten��o de gerenciar metodos que a
 * aplica��o precisa!
 *
 * @author Lucas Matos e Souza
 */
public class Ferramentas implements Serializable {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = 960071059434025101L;

    /**
     * BufferedReader contendo as entradas de arquivos
     */
    private BufferedReader entradas;
    
    /**
     * InputStreamReader Contendo ...
     */
    private InputStreamReader isr;
    
    /**
     * Construtor padr�o da Classe ferramenta
     */
    public Ferramentas() {
    }

    /**
     * Metodo que abre o arquivo qualquer arquivo serializavel
     *
     * @return JFileChooser retorna um JfileChooser
     * @throws IOException Exe��o do tipo IOException
     * @throws NenhumArquivoSelecionadoException Exce��o do Tipo Nenhum arquivo
     * Seleciondado
     */
    public JFileChooser selecionarArquivosSerializavel() throws IOException,NenhumArquivoSelecionadoException {
        //Seleciona o arquivo serializavel
        JFileChooser arquivoEscolhido = new JFileChooser();
        arquivoEscolhido.setDialogTitle("Selecione um arquivo .ser");
        arquivoEscolhido.setFileFilter(new FileNameExtensionFilter("Apenas SER", "ser"));
        arquivoEscolhido.setAcceptAllFileFilterUsed(false);
        arquivoEscolhido.setMultiSelectionEnabled(false);
        arquivoEscolhido.setFileHidingEnabled(false);

        int res1 = arquivoEscolhido.showOpenDialog(null);

        if (res1 == JFileChooser.APPROVE_OPTION) {
        	JOptionPane.showMessageDialog(null, "Arquivo " + arquivoEscolhido.getSelectedFile().getName());
        }
        
        if (res1 == JFileChooser.CANCEL_OPTION) {
            throw new NenhumArquivoSelecionadoException("Nenhum Arquivo foi selecionado!\n Tente Novamente");
        }

        return arquivoEscolhido;
    }

    /**
     * Metodo que abre o arquivo qualquer arquivo padr�o n�o serializado
     *
     * @return JFileChooser Retorna um arquivo escolhido
     * @throws IOException Exce��o do tipo IOException
     * @throws NenhumArquivoSelecionadoException Exce��o do Tipo Nenhum Arquivo
     * Seleciondado
     */
    public JFileChooser selecionarArquivoOficiais() throws IOException,NullPointerException, NenhumArquivoSelecionadoException {
        //Seleciona o arquivo serializavel
        JFileChooser arquivoEscolhido = new JFileChooser();
        arquivoEscolhido.setDialogTitle("Selecione um arquivo .txt");
        arquivoEscolhido.setFileFilter(new FileNameExtensionFilter("Apenas TXT", "txt"));
        arquivoEscolhido.setAcceptAllFileFilterUsed(false);
        arquivoEscolhido.setMultiSelectionEnabled(false);
        arquivoEscolhido.setFileHidingEnabled(false);

        int res1 = arquivoEscolhido.showOpenDialog(null);

        if (res1 == JFileChooser.APPROVE_OPTION) {
        	JOptionPane.showMessageDialog(null, "Arquivo " + arquivoEscolhido.getSelectedFile().getName());
        }
        
        if (res1 == JFileChooser.CANCEL_OPTION) {
            throw new NenhumArquivoSelecionadoException("Nenhum Arquivo foi selecionado!\n Tente Novamente");
        }

        return arquivoEscolhido;
    }

    /**
     * Metodo que valida o Arquivo que o usuario selecionou se est� no padr�o
     * do programa! Tanto para arquivos contendo dados oficiais quantos para os
     * não oficiais
     *
     * @param arquivoEscolhido JFileChooser com arquivo escolhido
     * @param i 1 - para arquivos oficiais, 2 - para arquivos Extra-Oficiais
     * @throws FileNotFoundException Exce��o do Tipo FileNotFoundException
     * @throws PadraoRegistroEntradaInvalidoException Exce��o do Tipo PadraoRegistroEntradaInvalido
     * @throws ClassNotFoundException Exce��o do tipo ClassNotFoundException
     * @throws IOException Exce��o do tipo IOException
     * 
     */
	@SuppressWarnings({ "resource", "unused", "unchecked" })
	public void validarArquivo(JFileChooser arquivoEscolhido, int i) throws FileNotFoundException, IOException, PadraoRegistroEntradaInvalidoException, ClassNotFoundException {
    	
    	//Parte para os arquivos oficiais
    	//Aqui as linhas do arquivo ser�o verificadas para ver a validade do arquivo
    	if(i == 1){
            // Pega o arquivo do arquivo
            File arquivoSelecionado = arquivoEscolhido.getSelectedFile();

            // faz alguma arquivo
            InputStream arquivoEntrada = new FileInputStream(arquivoSelecionado);
            InputStreamReader isr = new InputStreamReader(arquivoEntrada);

            setEntradas(new BufferedReader(isr));
            String linha = getEntradas().readLine(); // primeira linha

            while (linha != null) {
                String[] var = linha.split(";");
                if (var.length != 6) {
                    throw new PadraoRegistroEntradaInvalidoException("Arquivo com Padr�o inv�lido!");
                }
                linha = getEntradas().readLine(); // proxima linha
            }
    	}
    	
    	//Parte para os arquivos Extra-Oficiais
    	//Aqui o objeto guardado no arquivo ser� verificado
    	if(i == 2){
    		//Pega o nome do arquivo com o caminho
            File file2 = new File(arquivoEscolhido.getSelectedFile().getAbsolutePath());

            //Abre os arquivos
            FileInputStream arquivo2 = new FileInputStream(file2);
            ObjectInputStream objeto = new ObjectInputStream(arquivo2);

            //Convete o objeto Serializado em List do tipo String
			List<String>recebe = new ArrayList<>();
			
			recebe.addAll((List<String>) objeto.readObject());
			            
            if(recebe.size() == 0){
            	throw new ClassNotFoundException("Classe n�o encontrada, verifique\n   se o arquivo � v�lido!");
            }
            

            if (arquivo2 == null) {
                throw new IOException("Ocorreu um erro ao tentar abri o arquivo!");
            }
            
            for(String linha:recebe){
            	String[] var = linha.split(";");
            	
                if (var.length != 6) {
                    throw new PadraoRegistroEntradaInvalidoException("Arquivo com Padr�o inv�lido!");
                }
            }
            objeto.close();
    	}
    }

    /**
     * Metodo que passa os dados do arquivo selecionado para a HashSet
     * Professores
     *
     * @param professores Collection de Objetos do tipo Filtravel
     * @param arquivoEscolhido JFileChooser contendo o arquivo escolhido pelo usu�rio
     * @param i 1 - para arquivos oficiais, 2 - para arquivos Extra-Oficiais
     * @throws FileNotFoundException Exce��o do tipo FileNotFoundException
     * @throws EmailInvalidoException Se o email estiver fora do padrão oficial ou n�o oficial
     * @throws IOException Exce��o do tipo IOEXception
     * @throws CodigoExisteException Exce��o do Tipo Codigo Existente
     * @throws ClassNotFoundException Classe n�o encontrada
     */
	public void passarDadosDoArquivo(Collection<Filtravel> professores, JFileChooser arquivoEscolhido, int i) throws EmailInvalidoException,
            FileNotFoundException, IOException, CodigoExisteException, ClassNotFoundException {
		int cont = 0;
		
    	if(i == 1){
    		// Pega o arquivo do arquivo
            File arquivoSelecionado = arquivoEscolhido.getSelectedFile();

            // faz alguma arquivo
            InputStream arquivoEntrada = new FileInputStream(arquivoSelecionado);
            InputStreamReader isr = new InputStreamReader(arquivoEntrada);

            setEntradas(new BufferedReader(isr));
            
            // faz alguma ... arquivo
            String linha = this.getEntradas().readLine(); // primeira linha

            while (linha != null) {
                Professor prof = new Professor();

                if (prof.stringRegistroParaObjeto(linha)) {
                    if (professores.contains(prof)) {
                        throw new CodigoExisteException("C�digo j� existe!");
                    }
                    professores.add(prof);
                    cont++;
                }
                linha = getEntradas().readLine(); // proxima linha
            }
            getEntradas().close();
    	}
    	
    	
    	if(i == 2){
    		//Pega o nome do arquivo com o caminho
            File file2 = new File(arquivoEscolhido.getSelectedFile().getAbsolutePath());

            //Abre os arquivos
            FileInputStream arquivo2 = new FileInputStream(file2);
			@SuppressWarnings("resource")
			ObjectInputStream objeto1 = new ObjectInputStream(arquivo2);

            //Recebe todos os arquivos serializados
            @SuppressWarnings("unchecked")
			List<String> recebe = (List<String>) objeto1.readObject();

            for(String linha:recebe){
            	Professor prof = new Professor();
            	
                if (prof.stringRegistroParaObjeto(linha)) {
                    if (professores.contains(prof)) {
                        throw new CodigoExisteException("C�digo j� existe!");
                    }
                    professores.add(prof);
                    cont++;
                }
            }
            objeto1.close();
    	}
    	
    	//Informa o que aconteceu ao usu�rio
    	if(cont == 0){
    		JOptionPane.showMessageDialog(null,"Nenhum arquivo foi inserido!");
    	} else {
    		JOptionPane.showMessageDialog(null, cont+" Registros foram inseridos com sucesso!");
    	}
        
    }

    /**
     * Metodo que define os crit�rios para o filtro ser executado Esse metodo s�
     * � usado na op��o de visualiza��o em JOPTIONPANE
     *
     * @param cc Objeto Criterio Curso - Tipo CriterioCursos
     * @param cm Objeto Criterio Modalidade - Tipo CriterioModalidade
     * @param cp Objeto Criterio Periodo - Tipo CriterioPeriodo
     * @param filtro Objeto contendo um filtro - Tipo Filtro
     * @throws NullPointerException o metodo peder� retornar um NullPointerException em algum dado momento
     */
    public void definirCriterios(CriterioCursos cc, CriterioModalidades cm,CriterioPeriodos cp, Filtro filtro) 
    		throws NullPointerException {
        boolean concluir = false;

        String[] cursos = null;
        String[] modalidades = null;
        String[] periodos = null;

        while (!concluir) {
            String segundaOpcao = JOptionPane.showInputDialog(
                    "Voc� selecionou a op��o de Definir Crit�rios!\n"
                    + "Por favor selecione qual crit�rio deseja inserir:\n"
                    + "1. Curso\n"
                    + "2. Modalidade\n"
                    + "3. Periodo\n"
                    + "0. Concluir inser��o\n");

            switch (segundaOpcao) {

                case "1":
                    String stringCurso = JOptionPane.showInputDialog("Informe separado por espa�o OS CURSOS que deseja filtrar:");

                    cursos = stringCurso.split(" ");

                    for (String curso : cursos) {
                        cc.getCursos().add(new Curso(curso));
                    }
                    break;
                case "2":
                    String stringModalidade = JOptionPane.showInputDialog("Informe separado por espa�o AS MODALIDADES que deseja filtrar:");

                    modalidades = stringModalidade.split(" ");

                    for (String modalidade : modalidades) {
                        cm.getModalidades().add(new Modalidade(modalidade));
                    }
                    break;
                case "3":
                    String stringPeriodos = JOptionPane.showInputDialog("Informe separado por espa�o OS PERIODOS que deseja filtrar:");

                    periodos = stringPeriodos.split(" ");

                    cp.getPeriodos().addAll(Arrays.asList(periodos));
                    break;
                case "0":
                    if (cursos != null) {
                        filtro.getCriterios().add(cc);
                    }

                    if (modalidades != null) {
                        filtro.getCriterios().add(cm);
                    }

                    if (periodos != null) {
                        filtro.getCriterios().add(cp);
                    }

                    JOptionPane.showMessageDialog(null, "Filtros Inseridos com sucesso!");
                    concluir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao Inv�lida!");
            }
        }
    }

    /**
     * Metodo que define os crit�rios para o filtro ser executado
     *
     * @param cc Objeto Criterio Curso - Tipo CriterioCursos
     * @param cm Objeto Criterio Modalidade - Tipo CriterioModalidade
     * @param cp Objeto Criterio Periodo - Tipo CriterioPeriodo
     * @param txtCursos String contendo todos os cursos escolhidos
     * @param txtModalidades String contendo todas as modalidades escolhidas
     * @param txtPeriodos String contendo todos os periodos escolhidos
     * @param filtro Objeto contendo um filtro - Tipo Filtro
     */
    public void definirCriterios(CriterioCursos cc, CriterioModalidades cm, CriterioPeriodos cp, String txtCursos, String txtModalidades,
            String txtPeriodos, Filtro filtro) {

        String[] cursos = null;
        String[] modalidades = null;
        String[] periodos = null;

        //Cria os CriteriosCursos
        if (!txtCursos.isEmpty()) {
            cursos = txtCursos.substring(0, txtCursos.length() - 1).split(" ");

            for (String curso : cursos) {
                cc.getCursos().add(new Curso(curso));
            }
        }

        //Cria os CriteriosModalidade
        if (!txtModalidades.isEmpty()) {
            modalidades = txtModalidades.substring(0, txtModalidades.length() - 1).split(" ");

            for (String modalidade : modalidades) {
                cm.getModalidades().add(new Modalidade(modalidade));
            }
        }

        //Cria os CriteriosPeriodos
        if (!txtPeriodos.isEmpty()) {
            periodos = txtPeriodos.substring(0, txtPeriodos.length() - 1).split(" ");

            cp.getPeriodos().addAll(Arrays.asList(periodos));
        }

        //Verifica se os criterios n�o est�o nulos
        if (cursos != null) {
            filtro.getCriterios().add(cc);
        }

        if (modalidades != null) {
            filtro.getCriterios().add(cm);
        }

        if (periodos != null) {
            filtro.getCriterios().add(cp);
        }
    }

    /**
     * Metodo que gera o arquivo output.txt contendo os resultados dos filtros
     *
     * @param filtrado do tipo Collection Filtrado
     * @throws NullPointerException o metodo retornar� um nullpointerexception quando o bot�o cancelar for pressionado
     * @throws FileNotFoundException o metodo peder� retornar um  FileNotFoundException em algum dado momento
     * @throws IOException o metodo peder� retornar um IOException em algum dado momento
     * @throws FiltroNaoLocalizadoException Filtro n�o localizado
     */
    public void gerarArquivo(Collection<Filtrado> filtrado) throws NullPointerException, FileNotFoundException, IOException, FiltroNaoLocalizadoException {
        String[] opcoes = {"Por identifica��o", "Por E-mail"};

        String res = (String) JOptionPane.showInputDialog(null,
                "Qual a ordena��o que voc� deseja fazer:",
                "Selecao de Ordena��o", JOptionPane.PLAIN_MESSAGE, null,
                opcoes, "");

        List<Filtrado> arrayListFiltravel = new ArrayList<>();
        
        if(res == null){
        	throw new NullPointerException("N�o foi possivel continuar");
        }
        
        //Tratar a resposta do usu�rio
        if (res.equalsIgnoreCase("Por identifica��o")) {
            //Informa que o arquivo ser� ordenado pela identifica��o
            JOptionPane.showMessageDialog(null, "Ser� ordenado por identifica��o!");

            arrayListFiltravel.addAll(filtrado);

            Collections.sort(arrayListFiltravel, new ComparadorIdentificacao());
        } else {
            //Informa que o arquivo ser� ordenado pelo e-mail
            JOptionPane.showMessageDialog(null, "Ser� ordenado por e-mail!");

            arrayListFiltravel.addAll(filtrado);

            Collections.sort(arrayListFiltravel, new ComparadorEmail());
        }
        
        //Escolhe local onde ser� colocado o arquivo
        JFileChooser local = new JFileChooser();
        local.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        //Avisa que somente arquivo txt
        local.setFileFilter(new FileNameExtensionFilter("Apenas TXT", "txt"));
        local.setDialogTitle("Escolha um local para salvar");
        local.setFileHidingEnabled(false);

        int res1 = local.showSaveDialog(null);

        //O bot�o salvar foi clicado?
        if (res1 == JFileChooser.APPROVE_OPTION) {
            //Pega o caminho e nome do arquivo criado ou selecionado
            String caminho = String.valueOf(local.getSelectedFile());

            //Ler o arquivo ou faz o cria o novo arquivo
            OutputStream os = new FileOutputStream(caminho);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            try (BufferedWriter linha = new BufferedWriter(osw)) {
                //Filtro
                for (Filtrado p : arrayListFiltravel) {
                    //Verifica se neste registro h� um email oficial
                    linha.write(p.getRegistro().toStringEscritaArquivo());
                    linha.newLine();
                }
                linha.close();
            }
        }
    }

    /**
     * Metodo que Solicita ao usu�rio os dados necess�rios para o cadastro de dados extra oficiais
     *
     * @param professores Collection de Objeto do tipo Filtravel
     * @return sim caso
     */
    public boolean inserirExtraOficial(Collection<Filtravel> professores) {

        String nomeProfessor = JOptionPane.showInputDialog("Voc� selecionou a op��o de Inser��o de Dados Extra Oficiais!\n"
                        + "Por favor informe o Nome do Professor:");

        if (nomeProfessor == null) {
            return false;
        }

        String emailEO = JOptionPane.showInputDialog("Por favor informe o email do professor:");

        if (emailEO == null) {
            return false;
        }

        String nomeCursos = JOptionPane.showInputDialog("Por favor informe os cursos que este professor leciona"
                        + "Separados por espa�o:");

        if (nomeCursos == null) {
            return false;
        }

        String nomeModalidades = JOptionPane.showInputDialog("Por favor informe as modalidades"
                        + "Separados por espa�o:");

        if (nomeModalidades == null) {
            return false;
        }

        String nomePeriodos = JOptionPane.showInputDialog("Por favor informe em quais Periodos que o professor leciona"
                        + "Separados por espa�o:");
        if (nomePeriodos == null) {
            return false;
        }
        return true;
    }

    /**
     * M�todo que retorna
     *
     * @return InputStreamReader
     */
    public InputStreamReader getIsr() {
        return isr;
    }

    /**
     * M�todo que implementa
     *
     * @param isr do tipo InputStreamReader
     */
    public void setIsr(InputStreamReader isr) {
        this.isr = isr;
    }

    /**
     * Gera Arquivo Extra Oficial Esse Metodo cria um arquivo com os dados
     * extra-oficiais inseridos durante a execu��o do programa O mesmo dever�
     * ser utilizado durante a execu��o, no menu exportar ou ao ser pressionado
     * o bot�o fechar da janela principal, e logo em seguida o usu�rio informar
     * se quer ou n�o salvar os dados extra oficiais
     *
     * @param professores Collection de Objetos do tipo filtravel
     * @throws FileNotFoundException Exception do tipo File Not Found
     * @throws IOException Exce��o do tipo IOException
     * @throws FiltroNaoLocalizadoException Exce��o do tipo FiltroNaoLocalizadoException
     */
    public void gerarArquivoExtraOficial(Collection<Filtravel> professores) throws FileNotFoundException, IOException, FiltroNaoLocalizadoException {

        //Escolhe local onde ser� colocado o arquivo
        JFileChooser local = new JFileChooser();
        local.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        //Avisa que somente arquivo ser
        local.setFileFilter(new FileNameExtensionFilter("Apenas SER", "ser"));
        local.setDialogTitle("Escolha um local para salvar");
        local.setFileHidingEnabled(false);

        int res1 = local.showSaveDialog(null);

        //O bot�o salvar foi clicado
        if (res1 == JFileChooser.APPROVE_OPTION) {
            //Pega o caminho e nome do arquivo criado ou selecionado
            String caminho = String.valueOf(local.getSelectedFile());

            //Ler o arquivo ou faz o cria o novo arquivo
            FileOutputStream os = new FileOutputStream(caminho);
            
            try(ObjectOutputStream linha = new ObjectOutputStream(os)){
                int cont = 0;

                //Filtro
                List<String> objeto = new ArrayList<>();
                
                for (Filtravel p : professores) {
                    //Verifica se neste registro h� um email oficial
                    if (!Professor.validaEmailOficial(p.getEmail())) {
                        //Caso o e-mail n�o seja oficial o registro � adicionado a um filtrado!
                        cont++;
                        objeto.add(p.toStringEscritaArquivo());
                    }
                }
                
                linha.writeObject(objeto);
                linha.close();
                if (cont == 0) {
                    throw new FiltroNaoLocalizadoException("Nenhum registro Extra Oficial Encontrado,\n impossivel continuar!");
                }
            }
        }
    }

    /**
     * @return the entradas
     */
    public BufferedReader getEntradas() {
        return entradas;
    }

    /**
     * @param entradas the entradas to set
     */
    public void setEntradas(BufferedReader entradas) {
        this.entradas = entradas;
    }
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
