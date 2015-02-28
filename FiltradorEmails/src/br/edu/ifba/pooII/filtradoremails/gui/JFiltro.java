package br.edu.ifba.pooII.filtradoremails.gui;

import java.awt.EventQueue;
import java.util.HashSet;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import br.edu.ifba.pooII.filtradoremails.CriterioCursos;
import br.edu.ifba.pooII.filtradoremails.CriterioModalidades;
import br.edu.ifba.pooII.filtradoremails.CriterioPeriodos;
import br.edu.ifba.pooII.filtradoremails.Curso;
import br.edu.ifba.pooII.filtradoremails.Ferramentas;
import br.edu.ifba.pooII.filtradoremails.Filtro;
import br.edu.ifba.pooII.filtradoremails.Modalidade;
import br.edu.ifba.pooII.filtradoremails.Professor;
import br.edu.ifba.pooII.filtradoremails.execoes.FiltroNaoLocalizadoException;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtrado;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtravel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

/**
 * JFrame que faz o filtro dos registros cadastrados, nela o usuario poderá
 * salvar os registros em um arquivo (.ser)
 *
 * @author Lucas Matos e Souza
 * @version 1.0
 */
public class JFiltro extends JInternalFrame {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = 2828256735114596398L;

    // Cria-se os objetos critérios
    // CURSO
    /**
     * Lista de Criterios de Cursos
     */
    private Collection<Curso> listaCriteriosCurso = new HashSet<>();
    
    /**
     * Criterios De Cursos
     */
    private CriterioCursos criteriosCurso = new CriterioCursos(getListaCriteriosCurso());

    // MODALIDADE
    /**
     * Cria a Lista de Criterios de Modalidades
     */
    private Collection<Modalidade> listaCriteriosModalidade = new HashSet<>();
    
    /**
     * Cria o Criterio Modalidades
     */
    private CriterioModalidades criteriosModalidade = new CriterioModalidades(getListaCriteriosModalidade());

    // PERIODOS
    /**
     * Cria Lista de criterios Periodos
     */
    private Collection<String> listaCriteriosPeriodos = new HashSet<>();
    
    /**
     * Cria o Criterio Periodos
     */
    private CriterioPeriodos criteriosPeriodo = new CriterioPeriodos(getListaCriteriosPeriodos());

    // Cria-se o filtro
    private Filtro filtro = new Filtro();

    // Cria a Ferramenta
    private Ferramentas ferramentas = new Ferramentas();

    //Cria o campo que vai guardar as respostas
    /**
     * Armazena as respostas de Cursos, ou seja as escolhas do curso pelo usuario
     */
    private String respC = "";
    
    /**
     * Armazena as respostas de Modalidade, ou seja as escolhas do Modalidade pelo usuario
     */
    private String respM = "";
    
    /**
     * Armazena as respostas de Periodos, ou seja as escolhas do Periodos pelo usuario
     */
    private String respP = "";

    //Cria a variavel filtrado
    /**
     * Cria a Lista de Filtrados
     */
    private Collection<Filtrado> filtrados;

    /**
     * Roda a Aplicação
     *
     * @param args String
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
            
            try {
                JFiltro frame = new JFiltro(null);
                frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        });
    }

    /**
     * Cria a Frame
     *
     * @param registros do tipo Collection Filtravel
     */
    @SuppressWarnings("unchecked")
    public JFiltro(Collection<Filtravel> registros) {
        setClosable(true);
        setTitle("Sele\u00E7\u00E3o de Filtros");
        setFrameIcon(new ImageIcon(
                "D:\\workspace\\FiltradorEmails\\src\\br\\edu\\ifba\\pooII\\filtradoremails\\arquivos\\filtrar.png"));
        setBounds(100, 100, 895, 420);
        getContentPane().setLayout(null);

        JPanel painel1 = new JPanel();
        painel1.setBorder(new TitledBorder(null, "Sele\u00E7\u00E3o dos Filtros", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        painel1.setBounds(10, 11, 434, 302);
        getContentPane().add(painel1);
        painel1.setLayout(null);

        // Pega todos os Criterios Válidos
        Collection<String> ListaDeCursos = new HashSet<>();
        Collection<String> ListaDeModalidades = new HashSet<>();
        Collection<String> ListaDePeriodos = new HashSet<>();

        for (Filtravel reg : registros) {
            for (Curso curso : reg.getCurso()) {
                ListaDeCursos.add(curso.getSigla());
            }

            for (Modalidade modalidade : reg.getModalidade()) {
                ListaDeModalidades.add(modalidade.getNomeModalidade());
            }

            for (String periodo : reg.getPeriodos()) {
                ListaDePeriodos.add(periodo);
            }
        }

        // COMBO DOS CURSOS
        JLabel lblCursos = new JLabel("Cursos:");
        lblCursos.setBounds(10, 18, 46, 14);
        painel1.add(lblCursos);

        @SuppressWarnings("rawtypes")
        JComboBox comboCursos = new JComboBox();

        // Seta cada item
        for (String opc : ListaDeCursos) {
            comboCursos.addItem(opc);
        }

        comboCursos.setBounds(50, 15, 374, 20);
        painel1.add(comboCursos);

        JTextPane txtCursos = new JTextPane();
        txtCursos.setBounds(10, 76, 414, 23);
        painel1.add(txtCursos);

        // BOTAO DOS CURSOS
        JButton btnAdicionarCursos = new JButton("Adicionar");

        btnAdicionarCursos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				// Toda Vez que o botão adicionar for clicado ele adicionará à
                // String com os Critérios!

                String resposta;

                resposta = (String) comboCursos.getSelectedItem();

                setRespC(getRespC() + resposta + " ");

                txtCursos.setText(getRespC());

                JOptionPane.showMessageDialog(null, "Filtro Inserido com sucesso!");
            }
        });
        btnAdicionarCursos.setBounds(335, 43, 89, 23);
        painel1.add(btnAdicionarCursos);

        // COMBO DAS MODALIDADES
        JLabel lblModalidades = new JLabel("Modalidades:");
        lblModalidades.setBounds(10, 110, 71, 14);
        painel1.add(lblModalidades);

        @SuppressWarnings("rawtypes")
        JComboBox comboModalidades = new JComboBox();
        // Seta cada item
        for (String opm : ListaDeModalidades) {
            comboModalidades.addItem(opm);
        }

        comboModalidades.setBounds(75, 107, 349, 20);
        painel1.add(comboModalidades);

        // BOTÃO DAS MODALIDADES
        JTextPane txtModalidades = new JTextPane();
        txtModalidades.setBounds(10, 170, 414, 23);
        painel1.add(txtModalidades);

        JButton btnAdicionarModalidades = new JButton("Adicionar");

        btnAdicionarModalidades.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Toda Vez que o botão adicionar for clicado ele adicionará à 
                // String com os Critérios!

                String resposta;

                resposta = (String) comboModalidades.getSelectedItem();

                setRespM(getRespM() + resposta + " ");

                txtModalidades.setText(getRespM());
                JOptionPane.showMessageDialog(null, "Filtro Inserido com sucesso!");
            }
        });

        btnAdicionarModalidades.setBounds(335, 138, 89, 23);
        painel1.add(btnAdicionarModalidades);

        // COMBO PERIODOS
        JLabel lblPeriodos = new JLabel("Periodos:");
        lblPeriodos.setBounds(10, 211, 46, 14);
        painel1.add(lblPeriodos);

        @SuppressWarnings("rawtypes")
        JComboBox comboPeriodos = new JComboBox();

        // Seta cada item
        for (String opp : ListaDePeriodos) {
            comboPeriodos.addItem(opp);
        }
        comboPeriodos.setBounds(58, 208, 366, 20);
        painel1.add(comboPeriodos);

        // BOTÃO DOS PERIODOS
        JTextPane txtPeriodos = new JTextPane();
        txtPeriodos.setBounds(10, 271, 414, 20);
        painel1.add(txtPeriodos);

        JButton btnAdicionarPeriodos = new JButton("Adicionar");

        btnAdicionarPeriodos.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	// Toda Vez que o botão adicionar for clicado ele adicionará à
                // String com os Critérios!
                String resposta;

                resposta = (String) comboPeriodos.getSelectedItem();

                setRespP(getRespP() + resposta + " ");

                txtPeriodos.setText(getRespP());
                JOptionPane.showMessageDialog(null, "Filtro Inserido com sucesso!");
            }
        });

        btnAdicionarPeriodos.setBounds(335, 237, 89, 23);
        painel1.add(btnAdicionarPeriodos);

        // SELEÇÃO DOS TIPOS DE E-MAILS
        JPanel panel2 = new JPanel();
        panel2.setBorder(new TitledBorder(null, "Sele\u00E7\u00E3o Tipo de E-mail", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        panel2.setBounds(10, 311, 301, 68);
        getContentPane().add(panel2);
        panel2.setLayout(null);

        JRadioButton rdbtnEmailsOficiais = new JRadioButton("E-mails Oficiais");
        rdbtnEmailsOficiais.setBounds(39, 26, 109, 23);
        panel2.add(rdbtnEmailsOficiais);

        JRadioButton rdbtnEmailExtaOficial = new JRadioButton("E-mail Exta Oficial");
        rdbtnEmailExtaOficial.setBounds(165, 26, 117, 23);
        panel2.add(rdbtnEmailExtaOficial);

        // Painel com Dados Filtrados
        JPanel painel3 = new JPanel();
        painel3.setBorder(new TitledBorder(null, "\u00C1rea de Resultados",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        painel3.setBounds(446, 11, 423, 368);
        getContentPane().add(painel3);
        painel3.setLayout(null);

        // Texto que ficará a resposta do filtro
        JTextPane txtFiltrados = new JTextPane();

        // BOTÃO FILTRAR
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//Limpa os filtrados toda vez que o botão filtrar é clicado 
            	setFiltrados();
            	
            	// Define os Critérios que o usuário Estipulou para a classe
                // filtro
                getFerramentas().definirCriterios(getCriteriosCurso(), getCriteriosModalidade(), getCriteriosPeriodo(), txtCursos.getText(), txtModalidades.getText(),
                        txtPeriodos.getText(), getFiltro());

                //Imprime todos os critérios que foram selecionados
                String texto1;

                texto1 = "CRITÉRIOS SELECIONADOS\n";

                texto1 = texto1 + "Cursos Filtrados: ";

                for (Curso c : getCriteriosCurso().getCursos()) {
                    texto1 = texto1 + c + ", ";
                }

                texto1 = texto1 + "\nModalidades Filtrados: ";

                for (Modalidade c : getCriteriosModalidade().getModalidades()) {
                    texto1 = texto1 + c + ", ";
                }

                texto1 = texto1 + "\nPeriodos Filtrados: ";

                for (String c : getCriteriosPeriodo().getPeriodos()) {
                    texto1 = texto1 + c + ", ";
                }

                Collection<Filtrado> filtrado = new HashSet<>();

                try {
                    filtrado = (Collection<Filtrado>) getFiltro().getFiltrados(registros);

                    String escrita = "";

                    // Verifica os botões Clicados
                    if (rdbtnEmailsOficiais.isSelected() && rdbtnEmailExtaOficial.isSelected()) {
                        texto1 = texto1 + "\nTodos os emails serão localizados";
                        texto1 = texto1 + "\n\n";

                        escrita = texto1;
                        for (Filtrado n : filtrado) {
                            escrita = escrita + n.getRegistro().toStringEscritaArquivo() + "\n";
                            getFiltrados().add(n);
                        }

                    } else if (rdbtnEmailExtaOficial.isSelected()) {
                        texto1 = texto1 + "\nSomente os emails Extra oficiais serão localizados";
                        texto1 = texto1 + "\n\n";

                        escrita = texto1;
                        for (Filtrado n : filtrado) {
                            //Verifica se nos filtrados tem algum email 
                            if (!Professor.validaEmailOficial(n.getEmail())) {
                                escrita = escrita + n.getRegistro().toStringEscritaArquivo() + "\n";
                                getFiltrados().add(n);
                            }
                        }

                    } else if (rdbtnEmailsOficiais.isSelected()) {
                        texto1 = texto1 + "\nSomente os emails Oficiais serão localizados";
                        texto1 = texto1 + "\n\n";

                        escrita = texto1;
                        for (Filtrado n : filtrado) {
                            //verifica se nos filtrados tem algum email 
                            if (Professor.validaEmailOficial(n.getEmail())) {
                                escrita = escrita + n.getRegistro().toStringEscritaArquivo() + "\n";
                                getFiltrados().add(n);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhuma opção foi marcada, \ntodos os emails serão localizados!");
                        texto1 = texto1 + "\nNenhuma opção foi marcada, \ntodos os emails serão localizados!";
                        texto1 = texto1 + "\n\n";
                        escrita = texto1;
                        for (Filtrado n : filtrado) {
                            escrita = escrita + n.getRegistro().toStringEscritaArquivo() + "\n";
                            getFiltrados().add(n);
                        }
                    }

                    //Seta resultados no campo de texto ao lado!
                    txtFiltrados.setText(escrita);

                } catch (FiltroNaoLocalizadoException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }

                //Remove os Criterios
                getCriteriosCurso().getCursos().removeAll(getCriteriosCurso().getCursos());
                getCriteriosModalidade().getModalidades().removeAll(getCriteriosModalidade().getModalidades());
                getCriteriosPeriodo().getPeriodos().removeAll(getCriteriosPeriodo().getPeriodos());

                //Remove os critérios do Filtro
                getFiltro().getCriterios().remove(getCriteriosCurso());
                getFiltro().getCriterios().remove(getCriteriosModalidade());
                getFiltro().getCriterios().remove(getCriteriosPeriodo());

                //Limpa os campos de texto
                txtCursos.setText("");
                txtModalidades.setText("");
                txtPeriodos.setText("");

                setRespC("");
                setRespM("");
                setRespP("");
                
            }
        });
        btnFiltrar.setBounds(332, 324, 89, 44);
        getContentPane().add(btnFiltrar);

        txtFiltrados.setBounds(10, 22, 403, 295);
        painel3.add(txtFiltrados);

        // BOTÃO GERAR ARQUIVO
        JButton btnGerarArquivoDe = new JButton("Gerar Arquivo de Sa\u00EDda");
        btnGerarArquivoDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getFerramentas().gerarArquivo(getFiltrados());
                    JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso");
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (IOException | NullPointerException | FiltroNaoLocalizadoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        btnGerarArquivoDe.setBounds(245, 328, 149, 23);
        painel3.add(btnGerarArquivoDe);
    }
    
    /**
     * Metodo retorna um Collection de Cursos contendo a lista de Criterios
     * Cursos Inserida no Filtro
     *
     * @return Collection cursos
     */
    public Collection<Curso> getListaCriteriosCurso() {
        return listaCriteriosCurso;
    }

    /**
     * Metodo seta a lista de Criterios Cursos no JFiltro
     *
     * @param criteriosCursos de Collection do tipo Curso
     */
    public void setListaCriteriosCurso(Collection<Curso> criteriosCursos) {
        this.listaCriteriosCurso.addAll(criteriosCursos);
    }

    /**
     * Metodo que retorna todos os criterios modalidade inserido no JFiltro
     *
     * @return Collection Modalidade
     */
    public Collection<Modalidade> getListaCriteriosModalidade() {
        return listaCriteriosModalidade;
    }

    /**
     * Metodo que insere os Criterios Modalidades no Jfiltro
     *
     * @param listaDeModalidade Collection Modalidades contendo a lista de
     * modalidades
     */
    public void setListaCriteriosModalidade(Collection<Modalidade> listaDeModalidade) {
        this.listaCriteriosModalidade.addAll(listaDeModalidade);
    }

    /**
     * Metodo que retorna todos os Periodos passados como criterios para o JFILTRO
     *
     * @return Collection String
     */
    public Collection<String> getListaCriteriosPeriodos() {
        return listaCriteriosPeriodos;
    }

    /**
     * Metodo que insere a Lista de Criterios Periodos ao JFILTRO
     *
     * @param listaDePeriodos Collection String
     */
    public void setListaCriteriosPeriodos(Collection<String> listaDePeriodos) {
        this.listaCriteriosPeriodos.addAll(listaDePeriodos);
    }

    /**
     * Metodo que retorna o filtro do JFILTRO
     *
     * @return Filtro
     */
    public Filtro getFiltro() {
        return filtro;
    }

    /**
     * Metodo que insere o Filtro ao JFILTRO
     *
     * @param filtro Filtro
     */
    public void setFiltro(Filtro filtro) {
        this.filtro = filtro;
    }

    /**
     * Metodo que retorna os criterios Curso do JFiltro
     *
     * @return CriterioCurso
     */
    public CriterioCursos getCriteriosCurso() {
        return criteriosCurso;
    }

    /**
     * Metodo que seta todos os Criterios Cursos ao Jfiltro
     *
     * @param criteriosCurso CriterioCursos
     */
    public void setCriteriosCurso(CriterioCursos criteriosCurso) {
        this.criteriosCurso = criteriosCurso;
    }

    /**
     * Metodo que retorna todos os Criterios Modalidades do JFiltro
     *
     * @return CriterioModalidades do tipo CriteiroModalidades
     */
    public CriterioModalidades getCriteriosModalidade() {
        return criteriosModalidade;
    }

    /**
     * Metodo que insere os CriteriosModalidades do JFILTRO
     *
     * @param criteriosModalidade criterioModalidades
     */
    public void setCriteriosModalidade(CriterioModalidades criteriosModalidade) {
        this.criteriosModalidade = criteriosModalidade;
    }

    /**
     * Metodo que retorno os CriteirosPeriodos do JFILTRO
     *
     * @return CriterioPeriodos CriterioPeriodos
     */
    public CriterioPeriodos getCriteriosPeriodo() {
        return criteriosPeriodo;
    }

    /**
     * Metodo que insere os CriteriosPeriodo ao JFILTRO
     *
     * @param criteriosPeriodo do tipo Criterio Periodos
     */
    public void setCriteriosPeriodo(CriterioPeriodos criteriosPeriodo) {
        this.criteriosPeriodo = criteriosPeriodo;
    }

    /**
     * Metodo que resgata as ferramentas do JFILTRO
     *
     * @return Ferramentas Ferramentas
     */
    public Ferramentas getFerramentas() {
        return ferramentas;
    }

    /**
     * Metodo que adiciona as ferramentas ao JFILTRO
     *
     * @param ferramentas ferramentas
     */
    public void setFerramentas(Ferramentas ferramentas) {
        this.ferramentas = ferramentas;
    }

    /**
     * @return the respC
     */
    public String getRespC() {
        return respC;
    }

    /**
     * @param respC the respC to set
     */
    public void setRespC(String respC) {
        this.respC = respC;
    }

    /**
     * @return the respM
     */
    public String getRespM() {
        return respM;
    }

    /**
     * @param respM the respM to set
     */
    public void setRespM(String respM) {
        this.respM = respM;
    }

    /**
     * @return the respP
     */
    public String getRespP() {
        return respP;
    }

    /**
     * @param respP the respP to set
     */
    public void setRespP(String respP) {
        this.respP = respP;
    }

    /**
     * @return the filtrados
     */
    public Collection<Filtrado> getFiltrados() {
        return filtrados;
    }

    /**
     * inicializa a HashSet Filtrados
     */
    public void setFiltrados() {
    	this.filtrados = new HashSet<>();
    }
    
    /**
     * @return o serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
