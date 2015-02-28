package br.edu.ifba.pooII.filtradoremails.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;
import java.util.HashSet;

import javax.swing.ImageIcon;

import br.edu.ifba.pooII.filtradoremails.Curso;
import br.edu.ifba.pooII.filtradoremails.Filtro;
import br.edu.ifba.pooII.filtradoremails.Modalidade;
import br.edu.ifba.pooII.filtradoremails.Professor;
import br.edu.ifba.pooII.filtradoremails.execoes.EmailInvalidoException;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtravel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Collection;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * JFrame que abre cadastro de e-mails Extra-oficiais
 *
 * @author Lucas Matos e Souza
 * @version 1.0
 *
 */
public class JCadastro extends JInternalFrame {

	/**
	 * SerialVersionUID da classe
	 */
	private static final long serialVersionUID = 1076085683748952016L;

	// Cria um Filtro
	private Filtro filtro = new Filtro();
	private JTextField txtEmail;
	private JTextField txtNome;
	private JTable tableDeCursos;

	// Respostas dos Combos
	private Collection<String[]> respostasCombos = new HashSet<>();

	// Cria o campo que vai guardar as respostas
	private String[][] dados;
	private Object[] respC;

	/**
	 * Launch the application.
	 *
	 * @param args
	 *            do tipo String
	 */
	public static void main(String[] args) {
		EventQueue
				.invokeLater(() -> {
					try {
						UIManager
								.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}

					try {
						JCadastro frame = new JCadastro(null);
						frame.setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				});
	}

	/**
	 * Cria o Frame
	 *
	 * @param registros Collection Filtravel
	 */
	@SuppressWarnings({ "serial", "unchecked" })
	public JCadastro(Collection<Filtravel> registros) {
		setFrameIcon(new ImageIcon(
				JCadastro.class.getResource("/br/edu/ifba/pooII/filtradoremails/arquivos/adicionar-add.png")));
		setTitle("Janela de Cadastro");
		setClosable(true);
		setBounds(100, 100, 447, 430);
		getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 24, 31, 14);
		getContentPane().add(lblNome);

		JPanel painelDadosCursos = new JPanel();
		painelDadosCursos.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Dados dos Cursos:",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		painelDadosCursos.setBounds(10, 94, 414, 249);
		getContentPane().add(painelDadosCursos);
		painelDadosCursos.setLayout(null);

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
		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setBounds(10, 24, 32, 14);
		painelDadosCursos.add(lblCurso);

		@SuppressWarnings("rawtypes")
		JComboBox comboCurso = new JComboBox();
		comboCurso.setBounds(48, 21, 356, 20);
		// Seta cada item
		for (String opc : ListaDeCursos) {
			comboCurso.addItem(opc);
		}

		painelDadosCursos.add(comboCurso);

		// COMBO DAS MODALIDADES
		JLabel lblModalidade = new JLabel("Modalidade:");
		lblModalidade.setBounds(10, 51, 58, 14);
		painelDadosCursos.add(lblModalidade);

		@SuppressWarnings("rawtypes")
		JComboBox comboModalidade = new JComboBox();
		comboModalidade.setBounds(71, 48, 333, 20);
		// Seta cada item
		for (String opm : ListaDeModalidades) {
			comboModalidade.addItem(opm);
		}
		painelDadosCursos.add(comboModalidade);

		// COMBO DOS PERIODOS
		JLabel lblPeriodos = new JLabel("Periodos:");
		lblPeriodos.setBounds(10, 76, 46, 14);
		painelDadosCursos.add(lblPeriodos);

		@SuppressWarnings("rawtypes")
		JComboBox comboPeriodo = new JComboBox();
		comboPeriodo.setBounds(61, 73, 56, 20);
		// Seta cada item
		for (String opp : ListaDePeriodos) {
			comboPeriodo.addItem(opp);
		}
		painelDadosCursos.add(comboPeriodo);

		// CRIAÇÃO DA TABELA
		JTable tableDeCursos = new JTable();
		tableDeCursos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Curso", "Modalidade", "Periodo" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class };

			@SuppressWarnings("rawtypes")
			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false };

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		// BOTÃO ADICIONAR CURSO
		JButton btnAdicionarCurso = new JButton("Adicionar Curso");
		btnAdicionarCurso.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			int cont = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODA VEZ em que o botão Adionar cursos for clicado
				// ele pegará o que está nos critério selecionados!
				String curso = (String) comboCurso.getSelectedItem();
				String modalidade = (String) comboModalidade.getSelectedItem();
				String periodo = (String) comboPeriodo.getSelectedItem();

				DefaultTableModel model = (DefaultTableModel) tableDeCursos.getModel();

				// Coloca
				model.addRow(new String[] { curso, modalidade, periodo });

			}
		});
		btnAdicionarCurso.setBounds(152, 72, 114, 23);
		painelDadosCursos.add(btnAdicionarCurso);

		// Botão excluir
		JButton btnExcluirCurso = new JButton("Excluir Curso");

		btnExcluirCurso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Exclui os Cursos Inseridos no JTable
				((DefaultTableModel) tableDeCursos.getModel()).removeRow(tableDeCursos.getSelectedRow());
			}
		});
		btnExcluirCurso.setBounds(276, 72, 114, 23);
		painelDadosCursos.add(btnExcluirCurso);

		// PAINEL ONDE A TABELA ESTARÃO
		JScrollPane scrollPainelTabela = new JScrollPane();
		scrollPainelTabela.setBounds(10, 101, 394, 137);
		scrollPainelTabela.setViewportView(tableDeCursos);
		painelDadosCursos.add(scrollPainelTabela);

		JButton btnCadastrar = new JButton("Cadastrar Novo");

		btnCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Botão Cadastrar
				Professor novo = new Professor();

				int codigo = 0;

				for (Filtravel f : registros) {
					if (codigo < f.getCodigo()) {
						// Procura o maior código
						codigo = f.getCodigo();
					}
				}

				boolean completo = true;

				// Verifica se os campos Estão preenchidos!
				if (getTxtNome().getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
									"O campo NOME é obrigatório",
									"ERRO: NOME OBRIGATÓRIO",
									JOptionPane.ERROR_MESSAGE);
					completo = false;
				}

				if (getTxtEmail().getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"O campo EMAIL é obrigatório",
							"ERRO: EMAIL OBRIGATÓRIO",
							JOptionPane.ERROR_MESSAGE);
					completo = false;
				} else {
					if (!novo.validaEmail(txtEmail.getText())) {
						JOptionPane.showMessageDialog(null,
								"O EMAIL e invalido!", "ERRO: EMAIL INVALIDO!",
								JOptionPane.ERROR_MESSAGE);
						completo = false;
					}
				}

				if (tableDeCursos.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null,
							"Por favor insira ao menos 1 curso!",
							"ERRO: NUNHUM CURSO INFORMADO",
							JOptionPane.ERROR_MESSAGE);
					completo = false;
				}

				// SE TODOS OS CAMPOS ESTIVEREM CORRETOS...
				if (completo) {
					// Separa os Cursos
					Collection<Curso> cursosAdicionado = new HashSet<>();
					for (int i = 0; i < tableDeCursos.getRowCount(); i++) {
						// Adiciona os Cursos escolhidos em um Set de cursos!
						cursosAdicionado.add(new Curso((String) tableDeCursos
								.getValueAt(i, 0)));
					}

					// Separa as Modalidades
					Collection<Modalidade> modalidadesAdicionado = new HashSet<>();
					for (int i = 0; i < tableDeCursos.getRowCount(); i++) {
						// Adiciona os Cursos escolhidos em um Set de cursos!
						modalidadesAdicionado.add(new Modalidade(
								(String) tableDeCursos.getValueAt(i, 1)));
					}

					// Separa os Periodos
					Collection<String> periodosAdicionado = new HashSet<>();
					for (int i = 0; i < tableDeCursos.getRowCount(); i++) {
						// Adiciona os Cursos escolhidos em um Set de cursos!
						periodosAdicionado.add((String) tableDeCursos
								.getValueAt(i, 2));
					}

					novo.setCodigo(codigo + 1);
					novo.setNome(getTxtNome().getText().trim());
					try {
						novo.setEmail(getTxtEmail().getText());
					} catch (EmailInvalidoException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					novo.setCursos(cursosAdicionado);
					novo.setModalidades(modalidadesAdicionado);
					novo.setPeriodos(periodosAdicionado);

					// Adiciona o professor aos registros
					if (registros.add(novo)) {
						JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
						
						// Zera os dados para um novo cadastro!
						getTxtEmail().setText("");
						getTxtNome().setText("");

						getTxtNome().requestFocus();

						// Forma de Excluir os dados da Tabela
						((DefaultTableModel) tableDeCursos.getModel()).setNumRows(0);
						tableDeCursos.updateUI();

					} else {
						JOptionPane.showMessageDialog(null,
								"Não foi possível adicionar o novo registro!",
								"ERRO: AO INSERIR NOVO CADASTRO",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 52, 31, 14);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(45, 49, 379, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		btnCadastrar.setBounds(160, 354, 129, 34);
		getContentPane().add(btnCadastrar);

		txtNome = new JTextField();
		txtNome.setBounds(45, 21, 379, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
	}
	
	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * Metodo que retorna o Filtro do JCadastro
	 * 
	 * @return Filtro Filtro
	 */
	public Filtro getFiltro() {
		return filtro;
	}

	/**
	 * Metodo que insere o Filtro no JCadastro
	 * 
	 * @param filtro
	 *            Filtro
	 */
	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}

	/**
	 * @return o txtEmail
	 */
	public JTextField getTxtEmail() {
		return txtEmail;
	}

	/**
	 * @param txtEmail
	 *            insere o Email para txtEmail
	 */
	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	/**
	 * @return o txtNome
	 */
	public JTextField getTxtNome() {
		return txtNome;
	}

	/**
	 * @param txtNome
	 *            insere o Nome ao txtNome
	 */
	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}

	/**
	 * @return a tableDeCursos
	 */
	public JTable getTableDeCursos() {
		return tableDeCursos;
	}

	/**
	 * @param tableDeCursos
	 *            a tableDeCursos para um tabela de cursos
	 */
	public void setTableDeCursos(JTable tableDeCursos) {
		this.tableDeCursos = tableDeCursos;
	}

	/**
	 * @return o que estÃ¡ em respostasCombos
	 */
	public Collection<String[]> getRespostasCombos() {
		return respostasCombos;
	}

	/**
	 * @param respostasCombos
	 *            insere as respostas para o respostas Combos
	 */
	public void setRespostasCombos(Collection<String[]> respostasCombos) {
		this.respostasCombos = respostasCombos;
	}

	/**
	 * @return os dados
	 */
	public String[][] getDados() {
		return dados;
	}

	/**
	 * @param dados
	 *            insere os dados nos dados set
	 */
	public void setDados(String[][] dados) {
		this.dados = dados;
	}

	/**
	 * @return as respostas do Curso em respC
	 */
	public Object[] getRespC() {
		return respC;
	}

	/**
	 * @param respC
	 *            as respostas do Curso e insere em respC
	 */
	public void setRespC(Object[] respC) {
		this.respC = respC;
	}
}
