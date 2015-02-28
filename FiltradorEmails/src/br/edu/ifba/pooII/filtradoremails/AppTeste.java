package br.edu.ifba.pooII.filtradoremails;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.edu.ifba.pooII.filtradoremails.execoes.EmailInvalidoException;
import br.edu.ifba.pooII.filtradoremails.execoes.FiltroNaoLocalizadoException;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtrado;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtravel;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;
/**
 * Classe de Teste via texto
 * @author Teste
 *
 */
public class AppTeste {
	/**
	 * Método Main
	 * @param args String
	 * @throws FileNotFoundException Lançado quando o arquivo selecionado não for encontrado
	 * @throws IOException Lançado quando não for possivel abrir o arquivo selecionado 
	 * @throws EmailInvalidoException Lançada quando o email for invalido
	 * @throws FiltroNaoLocalizadoException Lançada quando o filtro não for localizado
	 */
    public static void main(String[] args) throws FileNotFoundException, IOException, EmailInvalidoException, FiltroNaoLocalizadoException {
        Set<Filtravel> professores = new HashSet<>();

        InputStream arquivoEntrada = new FileInputStream("input.txt");
        InputStreamReader isr = new InputStreamReader(arquivoEntrada);

        try (BufferedReader entradas = new BufferedReader(isr)) {
            String linha = entradas.readLine(); // primeira linha

            int cont = 0;

            while (linha != null) {
                Professor prof = new Professor();
                if (prof.stringRegistroParaObjeto(linha)) {
                    professores.add(prof);
                    cont++;
                } else {
                    System.out.println("Erro ao exportar!");
                }
                linha = entradas.readLine(); // proxima linha
            }
            entradas.close();

            for (Filtravel p : professores) {
                System.out.println(p);
            }

            System.out.println(cont + " Registros Exportados com Sucesso!");
        }

        System.out.println("\n \n \nINICIO!");

        // TRABALHANDO COM CURSOS
        Set<Curso> listaCriteriosCurso = new HashSet<>();
        CriterioCursos cc = new CriterioCursos(listaCriteriosCurso);
        cc.getCursos().add(new Curso("Linter"));
        cc.getCursos().add(new Curso("TI"));

        // TRABALHANDO COM MODALIDADES
        Set<Modalidade> listaCriteriosModalidade = new HashSet<>();
        CriterioModalidades cm = new CriterioModalidades(listaCriteriosModalidade);

        //Adicionando Modalidade no CriterioModalidade
        cm.getModalidades().add(new Modalidade("SuPerior"));
        cm.getModalidades().add(new Modalidade("Subsequente"));
        
        // TRABALHANDO COM PERIODOS
        Set<String> periodos = new HashSet<>();
        CriterioPeriodos cp = new CriterioPeriodos(periodos);
        
        cp.getPeriodos().add("1");
        cp.getPeriodos().add("4");
        cp.getPeriodos().add("7");
        cp.getPeriodos().add("3");

        // COMEÇANDO A TRATAR OS FILTROS
        //Cria o filtro e passa todos os critérios por meio da List de criterios criada acima
        Filtro filtro = new Filtro();

        filtro.getCriterios().add(cc);
        filtro.getCriterios().add(cp);
        filtro.getCriterios().add(cm);

        System.out.println(filtro);

        //Imprime todos os critérios que foram selecionados
        System.out.println("CRITÉRIOS SELECIONADOS");

        System.out.print("Cursos Filtrados: ");
        for (Curso c : cc.getCursos()) {
            System.out.print(c + ", ");
        }

        System.out.print("\nModalidades Filtradas: ");
        for (Modalidade c : cm.getModalidades()) {
            System.out.print(c + ", ");
        }

        System.out.print("\nPeriodos Filtrados: ");
        for (String c : cp.getPeriodos()) {
            System.out.print(c + ", ");
        }

        //Imprime o resultado Final do Filtro aplicado!
        System.out.println("\nRESULTADO FILTRO");

        try {
            for (Filtrado p : filtro.getFiltrados(professores)) {
                JOptionPane.showMessageDialog(null, p.getRegistro().toStringEscritaArquivo());
                System.out.println(p.getRegistro().toStringEscritaArquivo());
            }
        } catch (FiltroNaoLocalizadoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
