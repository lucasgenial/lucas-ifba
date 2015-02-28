package br.edu.ifba.pooII.filtradoremails.gui;

import java.awt.EventQueue;
import java.util.HashSet;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.edu.ifba.pooII.filtradoremails.Curso;
import br.edu.ifba.pooII.filtradoremails.Modalidade;
import br.edu.ifba.pooII.filtradoremails.Professor;
import br.edu.ifba.pooII.filtradoremails.execoes.EmailInvalidoException;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtravel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Collection;

/**
 * JFrame que visualiza todos os registros cadastrados em uma Jtable, nela o
 * usuario poderá excluir registros extra-oficiais
 *
 * @author Lucas Matos e Souza
 * @version 1.0
 *
 */
public class JRegistros extends JInternalFrame {

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = -6965466997738941728L;

    //Tabela 
    /**
     * Tabela onde conterá todos os registros
     */
    private JTable tableRegistros;

    /**
     * Inicia a aplicação
     *
     * @param args tipo String
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JRegistros frame = new JRegistros(null);
                frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        });
    }

    /**
     * Cria o Frame
     *
     * @param registros do tipo HashSet Filtravel
     */
    @SuppressWarnings("serial")
    public JRegistros(Collection<Filtravel> registros) {
        setFrameIcon(new ImageIcon(JRegistros.class.getResource("/br/edu/ifba/pooII/filtradoremails/arquivos/listar.png")));
        setResizable(true);
        setRootPaneCheckingEnabled(false);
        setClosable(true);
        setBounds(100, 100, 910, 494);
        getContentPane().setLayout(null);

        //Scrroll que contem a Tabela
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 874, 395);
        getContentPane().add(scrollPane);

        //TABELA QUE CONTERÃO TODOS OS REGISTROS
        tableRegistros = new JTable();
        tableRegistros.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "C\u00F3digo", "Nome", "Curso", "Modalidade", "Periodos", "Email"
                }
        ) {
            @SuppressWarnings("rawtypes")
            Class[] columnTypes = new Class[]{
                String.class, String.class, String.class, String.class, String.class, String.class
            };

            @SuppressWarnings({"unchecked", "rawtypes"})
            @Override
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });

        //Adicionar Informações ao JTABLE
        // TODA VEZ que o Frame for aberto ele vai atualizar os dados
        DefaultTableModel model = (DefaultTableModel) tableRegistros.getModel();

        for (Filtravel prof : registros) {
            String cursos = "";
            for (Curso c : prof.getCurso()) {
                cursos = cursos + c.getSigla() + " ";
            }

            String modalidades = "";
            for (Modalidade m : prof.getModalidade()) {
                modalidades = modalidades + m.getNomeModalidade() + " ";
            }

            String periodos = "";
            for (String p : prof.getPeriodos()) {
                periodos = periodos + p + " ";
            }

            model.addRow(new String[]{Integer.toString(prof.getCodigo()), prof.getNome(), cursos, modalidades, periodos, prof.getEmail()});
        }

        //Coloca 
        scrollPane.setViewportView(tableRegistros);

        //BotÃ£o Escluir Registro Se for Extra-oficial
        JButton btnExcluirRegistro = new JButton("Excluir Registro");
        btnExcluirRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				//Ação de Exlusão de registro

                Professor escolhido = new Professor();

                String emailSelecionado = (String) getTableRegistros().getValueAt(getTableRegistros().getSelectedRow(), 5);

                if (Professor.validaEmailOficial(emailSelecionado)) {
                    JOptionPane.showMessageDialog(null, "Não foi possível excluir o registro\nVerifique o registro selecionado\né um registro oficial!", "ERRO: AO EXCLUIR O REGISTRO", JOptionPane.ERROR_MESSAGE);
                } else {

					//Seta todos os dados no professor escolhido;
                    //Separa os Cursos
                    Collection<Curso> cursosAdicionado = new HashSet<>();
                    for (int i = 0; i < getTableRegistros().getRowCount(); i++) {
                        //Adiciona os Cursos escolhidos em um Set de cursos!
                        cursosAdicionado.add(new Curso((String) getTableRegistros().getValueAt(i, 0)));
                    }

                    //Separa as Modalidades
                    Collection<Modalidade> modalidadesAdicionado = new HashSet<>();
                    for (int i = 0; i < getTableRegistros().getRowCount(); i++) {
                        //Adiciona os Cursos escolhidos em um Set de cursos!
                        modalidadesAdicionado.add(new Modalidade((String) getTableRegistros().getValueAt(i, 1)));
                    }

                    //Separa os Periodos
                    Collection<String> periodosAdicionado = new HashSet<>();
                    for (int i = 0; i < getTableRegistros().getRowCount(); i++) {
                        //Adiciona os Cursos escolhidos em um Set de cursos!
                        periodosAdicionado.add((String) getTableRegistros().getValueAt(i, 2));
                    }

                    int codigo = Integer.parseInt((String) getTableRegistros().getValueAt(getTableRegistros().getSelectedRow(), 0));

                    escolhido.setCodigo(codigo);
                    escolhido.setNome((String) getTableRegistros().getValueAt(getTableRegistros().getSelectedRow(), 1));

                    try {
                        escolhido.setEmail(emailSelecionado);
                    } catch (EmailInvalidoException e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }

                    escolhido.setCursos(cursosAdicionado);
                    escolhido.setModalidades(modalidadesAdicionado);
                    escolhido.setPeriodos(periodosAdicionado);

                    //Aqui o registro é excluido!
                    registros.remove(escolhido);

                    //Mesmo excluindo o registro a tabela mostrará ainda o registro, por isso é necessário este código para excluir a linha selecionada
                    ((DefaultTableModel) getTableRegistros().getModel()).removeRow(getTableRegistros().getSelectedRow());

                    //Exibe ao usuário que o registro foi excluido com sucesso!
                    JOptionPane.showMessageDialog(null, "Registro Excluido com sucesso!");
                }
            }
        });
        btnExcluirRegistro.setBounds(382, 430, 173, 23);
        getContentPane().add(btnExcluirRegistro);

    }

    /**
     * @return a tableRegistros
     */
    public JTable getTableRegistros() {
        return tableRegistros;
    }

    /**
     * @param tableRegistros insere a tabela na variavel tableRegistros
     */
    public void setTableRegistros(JTable tableRegistros) {
        this.tableRegistros = tableRegistros;
    }
    
    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
