package br.edu.ifba.pooII.filtradoremails.gui;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.edu.ifba.pooII.filtradoremails.Ferramentas;
import br.edu.ifba.pooII.filtradoremails.execoes.CodigoExisteException;
import br.edu.ifba.pooII.filtradoremails.execoes.EmailInvalidoException;
import br.edu.ifba.pooII.filtradoremails.execoes.FiltroNaoLocalizadoException;
import br.edu.ifba.pooII.filtradoremails.execoes.NenhumArquivoSelecionadoException;
import br.edu.ifba.pooII.filtradoremails.execoes.PadraoRegistroEntradaInvalidoException;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtravel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.awt.Color;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;

import java.awt.Toolkit;
import java.util.Collection;

import javax.swing.JFrame;

/**
 * JFrame principal da aplicação
 *
 * @author Lucas Matos e Souza
 * @version 1.0
 */
public class Principal extends javax.swing.JFrame {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = 4562801133321086020L;
    
    /**
     * Barra de Menus
     */
    private JMenuBar jMenuBar1;

    /**
     * Item da Barra de Menus - Carregar
     */
    private JMenu jMenu2_carregar;
    
    /**
     * Item da Barra de Menus - Arquivo
     */
    private JMenu jMenu1_aquivo;
    
    /**
     * Item da Barra de Menus - Cadastros
     */
    private JMenu jMenu3_cadastros;
    
    /**
     * Item da Barra de Menus - Novo
     * NÃ£o foi usado na implementação
     */
    private JMenu jMenu4_novo;
    
    /**
     * Item da Barra de Menus - Relatórios
     */
    private JMenu jMenu4_relatorios;
    
    /**
     * SubItem do item da barra de Menus Arquigo/Carregar - Carregar Dados Oficiais
     */
    private JMenuItem jMenuCarregarDadosOficiais;
    
    /**
     * SubItem do Item da Barra de Menus Arquivo/Carregar - Carregar Dados Extra Oficiais
     */
    private JMenuItem jMenuCarregaDadosExtraOficiais;
    
    /**
     * SubItem do item da Barra de Menus Arquivo - Exportar
     */
    private JMenuItem jMenuExportar;
    
    /**
     * SubItem do item da Barra de Menus - Cadastro Extra Oficial
     */
    private JMenuItem jMenuCadastroExtraOficial;
    
    /**
     * SubItem do Item da barra de Menus - Visualizar/Excluir
     */
    private JMenuItem jMenuVisualizar;
    
    /**
     * SubItem do Item da Barra de Menus - Filtro
     */
    private JMenuItem jMenuFiltro;
    
    /**
     * SubItem da Barra do Menu - Visualizar Filtrados
     */
    private JMenuItem mntmVisualizarFiltrado;
    
    /**
     * JDesktopPane usado para aplicação
     * Nela todas as janelas adicionais serão exibidas
     */
    private JDesktopPane jdpPrincipal;
    
    /**
     * Registro de Professores
     * cria-se um objeto Hashset do tipo Filtravel e ResultadoFiltro
     */
    private Collection<Filtravel> professores;
    
    /**
     * Ferramenta gerada para auxiliar a Frame Principal
     */
    private Ferramentas ferramentas;

    // Fim das Declaraçõees de variaveis
    /**
     * Creates new form Principal
     */
    public Principal() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/br/edu/ifba/pooII/filtradoremails/arquivos/home.png")));
        initComponents();
    }

    /**
     * Esse método é chamado de dentro do construtor para inicializar o formulário.
     * ATENÇÃO: Não modifique este código. O conteúdo deste método é sempre
     * Regenerado pelo editor de formulários.
     */
    private void initComponents() {
        // Instancia as variaveis
        setjMenuBar1(new JMenuBar());
        setjMenu1_aquivo(new JMenu());
        setjMenu2_carregar(new JMenu());
        setjMenuCarregarDadosOficiais(new JMenuItem());
        setjMenuCarregaDadosExtraOficiais(new JMenuItem());
        setjMenuExportar(new JMenuItem());
        
        getjMenuExportar().addActionListener((ActionEvent e) -> {
            //Botão exportar dados extra-oficiais
            if (getProfessores().isEmpty()) {
                // Informo o erro
                JOptionPane.showMessageDialog(null, "Por favor importe os dados de origem");
            } else {
                // Cria uma nova janela Cadastro
                try {
                    getFerramentas().gerarArquivoExtraOficial(getProfessores());
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                } catch (IOException | FiltroNaoLocalizadoException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }
            }
        });
        
        setjMenu3_cadastros(new JMenu());
        setjMenuVisualizar(new JMenuItem());

        setJdpPrincipal(new JDesktopPane()); // Janela Principal
        setProfessores(new HashSet<>()); // Registros dos Professores
        setFerramentas(new Ferramentas()); // Cria Ferramentas

        // Seta o fundo branco no background da tela principal
        getJdpPrincipal().setBackground(Color.WHITE);
        getJdpPrincipal().setForeground(Color.WHITE);

        // MENU CARREGA DADOS OFICIAIS
        getjMenuCarregarDadosOficiais().setText("Dados Oficiais");
        
        getjMenuCarregarDadosOficiais().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Trabalhando com Arquivo Oficial
                try {
                    JFileChooser arquivoEscolhido = getFerramentas().selecionarArquivoOficiais();
                    getFerramentas().validarArquivo(arquivoEscolhido, 1);
                    getFerramentas().passarDadosDoArquivo(getProfessores(), arquivoEscolhido, 1);
                    getjMenuCarregarDadosOficiais().setEnabled(false);
                } catch (ClassNotFoundException | IOException | PadraoRegistroEntradaInvalidoException | CodigoExisteException | EmailInvalidoException | NenhumArquivoSelecionadoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    getjMenuCarregarDadosOficiais().setEnabled(true);
                }
            }
        });
        // adicionando ao menu
        getjMenu2_carregar().add(getjMenuCarregarDadosOficiais());

        // MENU CARREGA DADOS EXTRA OFICIAIS
        getjMenuCarregaDadosExtraOficiais().setText("Dados Extra Oficiais");
        getjMenuCarregaDadosExtraOficiais().addActionListener(new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Trabalhando com Arquivo Extra Oficial
                try {
                    JFileChooser arquivoExtraEscolhido = getFerramentas().selecionarArquivosSerializavel();
                    getFerramentas().validarArquivo(arquivoExtraEscolhido, 2);
                    getFerramentas().passarDadosDoArquivo(getProfessores(), arquivoExtraEscolhido, 2);
                } catch (ClassNotFoundException | IOException | PadraoRegistroEntradaInvalidoException | CodigoExisteException | EmailInvalidoException | NenhumArquivoSelecionadoException ex) {
                    //JOptionPane.showMessageDialog(null, ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        // adicionando ao menu
        getjMenu2_carregar().add(getjMenuCarregaDadosExtraOficiais());

        // MENU VISUALIZAR CADASTROS
        getjMenuVisualizar().setText("Visualizar/Excluir");
        getjMenuVisualizar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verifica se os professores foram inseridos
                if (getProfessores().isEmpty()) {
                    // Informo o erro
                    JOptionPane.showMessageDialog(null,"Por favor importe os dados de origem");
                } else {
                    // Cria uma janela de Filtro
                    JRegistros janelaRegistro = new JRegistros(getProfessores());

                    // seta a janela na JDesktopPanel
                    getJdpPrincipal().add(janelaRegistro);

                    // Ativo a Janela
                    janelaRegistro.setVisible(true);
                }
            }
        });
        setjMenu4_novo(new JMenu()); // Menu novo gera sub-menu
        setjMenuCadastroExtraOficial(new JMenuItem()); // Menu Cadastro Extra oficial

        // MENU CADASTRO EXTRA OFICIAL
        getjMenuCadastroExtraOficial().setText("Cadastro Extra Oficial");
        getjMenuCadastroExtraOficial().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verifica se os professores foram inseridos
                if (getProfessores().isEmpty()) {
                    // Informo o erro
                    JOptionPane.showMessageDialog(null, "Por favor importe os dados de origem");
                } else {
                    // Cria uma nova janela Cadastro
                    JCadastro cad = new JCadastro(getProfessores());

                    // Adiciona a janela ao JDesktopPanel
                    getJdpPrincipal().add(cad);

                    // Ativa a Janela Cadastro
                    cad.setVisible(true);
                }
            }
        });

        getjMenu4_novo().add(getjMenuCadastroExtraOficial());

        getjMenu4_novo().setText("Novo");

        getjMenu3_cadastros().add(getjMenu4_novo());

        getjMenu3_cadastros().add(getjMenuVisualizar());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getjMenu1_aquivo().setText("Arquivo");

        getjMenu2_carregar().setText("Carregar");
        getjMenu1_aquivo().add(getjMenu2_carregar());

        getjMenuExportar().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                InputEvent.CTRL_MASK));
        getjMenuExportar().setText("Exportar");
        getjMenu1_aquivo().add(getjMenuExportar());

        getjMenuBar1().add(getjMenu1_aquivo());

        getjMenu3_cadastros().setText("Cadastros");

        getjMenuBar1().add(getjMenu3_cadastros());

        setJMenuBar(getjMenuBar1());

        setjMenu4_relatorios(new JMenu("Relatórios"));
        getjMenuBar1().add(getjMenu4_relatorios());

        setjMenuFiltro(new JMenuItem("Filtro"));
        getjMenuFiltro().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Verifica se os professores foram inseridos
                if (getProfessores().isEmpty()) {
                    // Informo o erro
                    JOptionPane.showMessageDialog(null,
                            "Por favor importe os dados de origem");
                } else {
                    // Cria uma janela de Filtro
                    JFiltro janelaFiltro = new JFiltro(getProfessores());

                    // seta a janela na JDesktopPanel
                    getJdpPrincipal().add(janelaFiltro);

                    // Ativo a Janela
                    janelaFiltro.setVisible(true);
                }
            }
        });
        getjMenu4_relatorios().add(getjMenuFiltro());

        setMntmVisualizarFiltrado(new JMenuItem("Visualizar Filtrado"));
        getMntmVisualizarFiltrado().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){
               
        	   //BotÃ£o que usa para visualizar filtrado!
        	   JVisualizarFiltrado janelaVisualizarFiltrado = new JVisualizarFiltrado();

               // seta a janela na JDesktopPanel
               getJdpPrincipal().add(janelaVisualizarFiltrado);

               // Ativo a Janela
               janelaVisualizarFiltrado.setVisible(true);
               
            }
        });
        getjMenu4_relatorios().add(getMntmVisualizarFiltrado());

        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addComponent(jdpPrincipal, Alignment.LEADING)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jdpPrincipal)
        			.addContainerGap())
        );
        jdpPrincipal.setLayout(null);

        getContentPane().setLayout(layout);

        getJdpPrincipal().getAccessibleContext().setAccessibleName("");

        setBounds(0, 0, 1200, 660);

        this.setDefaultCloseOperation(3);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {

                int i = JOptionPane.showConfirmDialog(null, "Deseja salvar as alterações dos \n    dados Extra Oficiais?", "Sair", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    if (getProfessores().isEmpty()) {
                        // Informo o erro
                        JOptionPane.showMessageDialog(null, "Por favor importe os dados de origem");
                        System.exit(1);
                    } else {
                        // Cria uma nova janela Cadastro
                        try {
                            getFerramentas().gerarArquivoExtraOficial(getProfessores());
                        } catch (FileNotFoundException e1) {
                            JOptionPane.showMessageDialog(null, e1.getMessage());
                            System.exit(1);
                        } catch (IOException | FiltroNaoLocalizadoException e1) {
                            JOptionPane.showMessageDialog(null, e1.getMessage());
                            repaint();
                        }
                    }
                } else if (i == JOptionPane.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(null, "Você será direcionado a tela inicial");
                    System.exit(1);
                } else if (i == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Obrigado por utilizar o sistema!");
                }
            }
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }

    /**
     * @return the jMenuBar1
     */
    public JMenuBar getjMenuBar1() {
        return jMenuBar1;
    }

    /**
     * @param jMenuBar1 the jMenuBar1 to set
     */
    public void setjMenuBar1(JMenuBar jMenuBar1) {
        this.jMenuBar1 = jMenuBar1;
    }

    /**
     * @return the jMenu2_carregar
     */
    public JMenu getjMenu2_carregar() {
        return jMenu2_carregar;
    }

    /**
     * @param jMenu2_carregar the jMenu2_carregar to set
     */
    public void setjMenu2_carregar(JMenu jMenu2_carregar) {
        this.jMenu2_carregar = jMenu2_carregar;
    }

    /**
     * @return the jMenu1_aquivo
     */
    public JMenu getjMenu1_aquivo() {
        return jMenu1_aquivo;
    }

    /**
     * @param jMenu1_aquivo the jMenu1_aquivo to set
     */
    public void setjMenu1_aquivo(JMenu jMenu1_aquivo) {
        this.jMenu1_aquivo = jMenu1_aquivo;
    }

    /**
     * @return the jMenu3_cadastros
     */
    public JMenu getjMenu3_cadastros() {
        return jMenu3_cadastros;
    }

    /**
     * @param jMenu3_cadastros the jMenu3_cadastros to set
     */
    public void setjMenu3_cadastros(JMenu jMenu3_cadastros) {
        this.jMenu3_cadastros = jMenu3_cadastros;
    }

    /**
     * @return the jMenu4_novo
     */
    public JMenu getjMenu4_novo() {
        return jMenu4_novo;
    }

    /**
     * @param jMenu4_novo the jMenu4_novo to set
     */
    public void setjMenu4_novo(JMenu jMenu4_novo) {
        this.jMenu4_novo = jMenu4_novo;
    }

    /**
     * @return the jMenu4_relatorios
     */
    public JMenu getjMenu4_relatorios() {
        return jMenu4_relatorios;
    }

    /**
     * @param jMenu4_relatorios the jMenu4_relatorios to set
     */
    public void setjMenu4_relatorios(JMenu jMenu4_relatorios) {
        this.jMenu4_relatorios = jMenu4_relatorios;
    }

    /**
     * @return the jMenuCarregarDadosOficiais
     */
    public JMenuItem getjMenuCarregarDadosOficiais() {
        return jMenuCarregarDadosOficiais;
    }

    /**
     * @param jMenuCarregarDadosOficiais the jMenuCarregarDadosOficiais to set
     */
    public void setjMenuCarregarDadosOficiais(JMenuItem jMenuCarregarDadosOficiais) {
        this.jMenuCarregarDadosOficiais = jMenuCarregarDadosOficiais;
    }

    /**
     * @return the jMenuExportar
     */
    public JMenuItem getjMenuExportar() {
        return jMenuExportar;
    }

    /**
     * @param jMenuExportar the jMenuExportar to set
     */
    public void setjMenuExportar(JMenuItem jMenuExportar) {
        this.jMenuExportar = jMenuExportar;
    }

    /**
     * @return the jMenuCarregaDadosExtraOficiais
     */
    public JMenuItem getjMenuCarregaDadosExtraOficiais() {
        return jMenuCarregaDadosExtraOficiais;
    }

    /**
     * @param jMenuCarregaDadosExtraOficiais the jMenuCarregaDadosExtraOficiais
     * to set
     */
    public void setjMenuCarregaDadosExtraOficiais(JMenuItem jMenuCarregaDadosExtraOficiais) {
        this.jMenuCarregaDadosExtraOficiais = jMenuCarregaDadosExtraOficiais;
    }

    /**
     * @return the jMenuCadastroExtraOficial
     */
    public JMenuItem getjMenuCadastroExtraOficial() {
        return jMenuCadastroExtraOficial;
    }

    /**
     * @param jMenuCadastroExtraOficial the jMenuCadastroExtraOficial to set
     */
    public void setjMenuCadastroExtraOficial(JMenuItem jMenuCadastroExtraOficial) {
        this.jMenuCadastroExtraOficial = jMenuCadastroExtraOficial;
    }

    /**
     * @return the jMenuVisualizar
     */
    public JMenuItem getjMenuVisualizar() {
        return jMenuVisualizar;
    }

    /**
     * @param jMenuVisualizar the jMenuVisualizar to set
     */
    public void setjMenuVisualizar(JMenuItem jMenuVisualizar) {
        this.jMenuVisualizar = jMenuVisualizar;
    }

    /**
     * @return the jMenuFiltro
     */
    public JMenuItem getjMenuFiltro() {
        return jMenuFiltro;
    }

    /**
     * @param jMenuFiltro the jMenuFiltro to set
     */
    public void setjMenuFiltro(JMenuItem jMenuFiltro) {
        this.jMenuFiltro = jMenuFiltro;
    }

    /**
     * @return the jdpPrincipal
     */
    public JDesktopPane getJdpPrincipal() {
        return jdpPrincipal;
    }

    /**
     * @param jdpPrincipal the jdpPrincipal to set
     */
    public void setJdpPrincipal(JDesktopPane jdpPrincipal) {
        this.jdpPrincipal = jdpPrincipal;
    }

    /**
     * @return the professores
     */
    public Collection<Filtravel> getProfessores() {
        return professores;
    }

    /**
     * @param professores the professores to set
     */
    public void setProfessores(Collection<Filtravel> professores) {
    	professores = new HashSet<>();
        this.professores = professores;
    }

    /**
     * @return the ferramentas
     */
    public Ferramentas getFerramentas() {
        return ferramentas;
    }

    /**
     * @param ferramentas the ferramentas to set
     */
    public void setFerramentas(Ferramentas ferramentas) {
        this.ferramentas = ferramentas;
    }

    /**
     * @return the mntmVisualizarFiltrado
     */
    public JMenuItem getMntmVisualizarFiltrado() {
        return mntmVisualizarFiltrado;
    }

    /**
     * @param mntmVisualizarFiltrado the mntmVisualizarFiltrado to set
     */
    public void setMntmVisualizarFiltrado(JMenuItem mntmVisualizarFiltrado) {
        this.mntmVisualizarFiltrado = mntmVisualizarFiltrado;
    }
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
