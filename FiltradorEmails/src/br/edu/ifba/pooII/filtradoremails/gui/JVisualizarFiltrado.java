package br.edu.ifba.pooII.filtradoremails.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.edu.ifba.pooII.filtradoremails.Ferramentas;
import br.edu.ifba.pooII.filtradoremails.execoes.NenhumArquivoSelecionadoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Jframe que visualiza apartir de um arquivo (.ser) todos os registros
 * filtrados.
 *
 * @author Lucas Matos e Souza
 * @version 1.0
 */
public class JVisualizarFiltrado extends JInternalFrame{

    /**
     * SerialVersionUID da classe
     */
    private static final long serialVersionUID = 7637405049373353140L;

    /**
     * Cria um tabela que conterá todos os registros fitrados
     */
    private JTable tableFiltrados;

    /**
     * Cria um ferramenta para trabalhar na Frame
     */
    private Ferramentas ferramentas = new Ferramentas();

    /**
     * Roda a Aplicação
     * @param args String
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JVisualizarFiltrado frame = new JVisualizarFiltrado();
                frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        );
    }


    /**
     * Cria a Frame
     */
    @SuppressWarnings("serial")
    public JVisualizarFiltrado() {
        setClosable(true);
        setTitle("Visualizar Arquivos Filtrados");
        setBounds(100, 100, 615, 444);
        getContentPane().setLayout(null);

        JButton btnSelecionarArquivo = new JButton("Selecionar Arquivo");
        btnSelecionarArquivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Seleciona o arquivo
                // Trabalhando com Arquivo Extra Oficial
                              	
            	// Pega o arquivo do arquivo
                File arquivoSelecionado = null;
				try {
					arquivoSelecionado = getFerramentas().selecionarArquivoOficiais().getSelectedFile();
					// faz alguma arquivo
					InputStream arquivoEntrada = null;
					arquivoEntrada = new FileInputStream(arquivoSelecionado);
					InputStreamReader isr = new InputStreamReader(arquivoEntrada);
					
					@SuppressWarnings("resource")
					BufferedReader entradas = new BufferedReader(isr);
					
					String linha = entradas.readLine();// primeira linha
											
	                //Adicionar Informações ao JTABLE
	                //TODA VEZ que o Frame for aberto ele vai atualizar os dados
	                //Primeiro excluo qualquer linha que esteja na tabela
	                ((DefaultTableModel) getTableFiltrados().getModel()).setNumRows(0);
	                getTableFiltrados().updateUI();
	
	                //Só então adiciono as novas!
	                DefaultTableModel model = (DefaultTableModel) getTableFiltrados().getModel();
	                
	                while (linha != null) {
	                    String[] var = linha.split("> ");
	                    
	                    String identificacao = var[0].toString().substring(1, var[0].toString().length());
	
	                    String email = var[1];
	                    
	                    model.addRow(new String[]{identificacao, email});
	                    linha = entradas.readLine();// proxima linha
	                }
				}catch (IOException | ArrayIndexOutOfBoundsException | NenhumArquivoSelecionadoException | NullPointerException e1) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel continuar, verifique o arquivo\n tente novamente!");
                }
            }
        });

        btnSelecionarArquivo.setBounds(10, 11, 133, 46);
        getContentPane().add(btnSelecionarArquivo);

        JScrollPane scrollPaneDaTabela = new JScrollPane();
        scrollPaneDaTabela.setBounds(153, 11, 436, 397);
        getContentPane().add(scrollPaneDaTabela);

        //TABELA QUE CONTERÃO TODOS OS REGISTROS FILTRADOS
        tableFiltrados = new JTable();
        tableFiltrados.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Identifica\u00E7\u00E3o", "E-mail"
                }
        ) {
            @SuppressWarnings("rawtypes")
            Class[] columnTypes = new Class[]{
                String.class, String.class
            };

            @SuppressWarnings({"rawtypes", "unchecked"})
            @Override
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        scrollPaneDaTabela.setViewportView(tableFiltrados);

    }

    /**
     * @return a tableFiltrados
     */
    public JTable getTableFiltrados() {
        return tableFiltrados;
    }

    /**
     * @param tableFiltrados insere a tabela de Filtrados à variavel tableFiltrados
     */
    public void setTableFiltrados(JTable tableFiltrados) {
        this.tableFiltrados = tableFiltrados;
    }

    /**
     * @return a ferramentas
     */
    public Ferramentas getFerramentas() {
        return ferramentas;
    }

    /**
     * @param ferramentas insere a ferramenta na variavel ferramentas
     */
    public void setFerramentas(Ferramentas ferramentas) {
        this.ferramentas = ferramentas;
    }

    /**
     * @return o serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
