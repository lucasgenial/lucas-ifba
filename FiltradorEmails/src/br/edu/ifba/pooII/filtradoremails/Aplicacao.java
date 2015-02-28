package br.edu.ifba.pooII.filtradoremails;

import br.edu.ifba.pooII.filtradoremails.execoes.CodigoExisteException;
import br.edu.ifba.pooII.filtradoremails.execoes.EmailInvalidoException;
import br.edu.ifba.pooII.filtradoremails.execoes.FiltroNaoLocalizadoException;
import br.edu.ifba.pooII.filtradoremails.execoes.NenhumArquivoSelecionadoException;
import br.edu.ifba.pooII.filtradoremails.execoes.PadraoRegistroEntradaInvalidoException;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtrado;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtravel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe que testa todas a aplicação, nela contem o Main que fará a aplicação ser executar
 * Ela usa o JOptionPane
 * @author Lucas Matos e Souza
 * @version 1.0
 */
public class Aplicacao {
	/**
	 * @param args String
	 * @throws IOException lançado quando o arquivo escolhido não puder ser aberto
	 * @throws EmailInvalidoException Lançado quando o email for invalido
	 * @throws NenhumArquivoSelecionadoException Lançado quando nenhum arquivo for selecionado
	 * @throws ClassNotFoundException Exceção do tipo ClassNotFoundException
	 */
    public static void main(String[] args) throws IOException, EmailInvalidoException, NenhumArquivoSelecionadoException, ClassNotFoundException {
        Ferramentas ferramentas = new Ferramentas();
        boolean sair = false;
        /* Set the Nimbus look and feel */
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
        
        try {
            //cria-se um objeto Hashset do tipo Filtravel e ResultadoFiltro
            Collection<Filtravel> professores = new HashSet<>();
            //Set<ResultadoFiltro> rsFiltro = new HashSet<>();

            //Cria-se os objetos critérios
            //CURSO
            Collection<Curso> listaCriteriosCurso = new HashSet<>();
            CriterioCursos cc = new CriterioCursos(listaCriteriosCurso);

            //MODALIDADE
            Collection<Modalidade> listaCriteriosModalidade = new HashSet<>();
            CriterioModalidades cm = new CriterioModalidades(listaCriteriosModalidade);

            //PERIODOS
            Collection<String> listaCriteriosPeriodos = new HashSet<>();
            CriterioPeriodos cp = new CriterioPeriodos(listaCriteriosPeriodos);

            //Cria-se o filtro
            Filtro filtro = new Filtro();

            while (!sair) {
                String opcao = JOptionPane.showInputDialog(
                        "0. Escolher arquivo .txt contendo dados oficiais\n" + ""
                        + "1. Carregar dados extraoficiais (que podem ou não existir)\n"
                        + "2. Inserir novo registro extraoficial\n"
                        + "3. Remover registro extraoficial\n"
                        + "4. Definir critérios (cursos, modalidades e períodos)\n"
                        + "5. Gerar dados de saída\n"
                        + "S. Sair do programa\n");

                switch (opcao) {
                    /*
                     permite o usário informar o caminho onde se encontra o arquivo com os registros encontram-se.
                     Caso neste arquivo exista(m) linha(s) fora do padrÃ£o esperado, deve ser lançada a
                     exceção PadraoRegistroEntradaInvalidoException, do tipo checada.
                        
                     Padrão predefinido: codigo;nome;email;cursos;modalidades;periodos
                        
                     Considerando que a qualquer momento o usuário pode optar por carregar registros de um arquivo .txt,
                     se faz necessário, no momento de sua leitura, evitar que seja adicionado o mesmo professor (registro)
                     mais de uma vez, evitando duplicidade (desperdício de memória, processador, etc.).
                    
                     Por exemplo, se formos nesta opção por 2 vezes e informarmos o mesmo arquivo, a primeira vez todos os
                     registros serão considerados. Já na segunda, nenhum deveria ser.
                     */
                    case "0":
                        //Trabalhando com Arquivo Oficial
                        try {
                            JFileChooser arquivoEscolhido = ferramentas.selecionarArquivoOficiais();
                            System.out.println(arquivoEscolhido);
                            ferramentas.validarArquivo(arquivoEscolhido, 1);
                            ferramentas.passarDadosDoArquivo(professores, arquivoEscolhido, 1);
                            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
                        } catch (EmailInvalidoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            break;
                        } catch (IOException | PadraoRegistroEntradaInvalidoException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                            break;
                        } catch (CodigoExisteException e) {
                        	JOptionPane.showMessageDialog(null, e.getMessage());
                        	break;
						}
                        break;
                    case "1":
                        //Trabalhando com Arquivo Extra Oficial
                        try {
                            JFileChooser arquivoExtraEscolhido = ferramentas.selecionarArquivosSerializavel();
                            ferramentas.validarArquivo(arquivoExtraEscolhido, 1);
                            ferramentas.passarDadosDoArquivo(professores, arquivoExtraEscolhido, 2);
                            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
                        } catch (EmailInvalidoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            break;
                        } catch (IOException | PadraoRegistroEntradaInvalidoException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                            break;
                        }catch (CodigoExisteException e) {
                        	JOptionPane.showMessageDialog(null, e.getMessage());
                        	break;
						}
                        break;
                    case "2":
                        ferramentas.inserirExtraOficial(professores);
                        JOptionPane.showMessageDialog(null, "Esta opção Ainda não está implementada!");
                        break;
                    case "3":
                            JOptionPane.showMessageDialog(null, "Esta opção Ainda não está implementada!");
                            break;
                    case "4":
                        try {
                            ferramentas.definirCriterios(cc, cm, cp, filtro);
                        } catch (NullPointerException e) {
                            JOptionPane.showMessageDialog(null, "Filtros Inseridos com sucesso!");
                            break;
                        }
                        break;
                    case "5":
                        try {
                            ferramentas.gerarArquivo((Collection<Filtrado>) filtro.getFiltrados(professores));
                        } catch (FileNotFoundException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            break;
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            break;
                        } catch (FiltroNaoLocalizadoException e) {
                        	JOptionPane.showMessageDialog(null, e.getMessage());
                            break;
						}

                        JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso");
                        break;
                    case "S":
                        JOptionPane.showMessageDialog(null, "Obrigado por utilizar o sistema!");
                        sair = true;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção Invalida!");
                        break;
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Obrigado por utilizar o sistema!");
        }
    }
}
