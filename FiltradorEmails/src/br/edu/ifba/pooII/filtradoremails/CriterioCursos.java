package br.edu.ifba.pooII.filtradoremails;

import br.edu.ifba.pooII.filtradoremails.interfaces.Criterio;
import br.edu.ifba.pooII.filtradoremails.interfaces.Filtravel;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
/**
 * Classe armazena o critério a ser atendido para curso
 * Classe Trabalha com os Criterios de Cursos que serão colocados
 * no filtro, a classe trabalha com HashSet, apesar de Passar um Collection
 * @author Lucas Matos e Souza
 */
public class CriterioCursos implements Criterio, Serializable {
	/**
	 * SerialVersionUID da classe
	 */
	private static final long serialVersionUID = -2654695799194834930L;
	
	/**
	 * HashSet de Cursos
	 */
	private Collection<Curso> cursos = new HashSet<>();
	
	/**
	 * Construtor Completo da Classe Criterio Curso
	 * @param listaCriteriosCurso do tipo Set Curso 
	 */
	public CriterioCursos(Collection<Curso> listaCriteriosCurso) {
		super();
		cursos.addAll(listaCriteriosCurso);
	}
	
	/**
	 * Construtor padrão
	 */
	public CriterioCursos(){
		
	}
	
	/**
	 * Método que verifica se o Criterio Curso passado é válido!
	 * @param f Filtravel
	 * @return true se f for valido false caso contrario.
	 */
	@Override
	public boolean ehValido(Filtravel f) {
		if (f == null || this.getCursos() == null) {
			return false;
		}

		for (Curso aux : getCursos()) {
			for (Curso s : f.getCurso()) {
				if (s.toString().equalsIgnoreCase(aux.toString())) {
					return true;
				}
			}
		}

		return false;
	}
	/**
	 * Método que retorna um Collection HashSet
	 * Com todos os criterios cursos cadastrados.
	 * @return Collection contendo cursos
	 */
	public Collection<Curso> getCursos() {
		return this.cursos;
	}

	/**
	 * Método HashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getCursos() == null) ? 0 : getCursos().hashCode());
		return result;
	}

	/**
	 * Método Equals da Classe Criterio Cursos
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CriterioCursos other = (CriterioCursos) obj;
		if (getCursos() == null) {
			if (other.getCursos() != null)
				return false;
		} else if (!cursos.equals(other.cursos))
			return false;
		return true;
	}

    /**
     * Métod que seta o curso
     * @param cursos the cursos to set
     */
    public void setCursos(Collection<Curso> cursos) {
        this.cursos = cursos;
    }
    
    /**
     * Metodo que retorna o SerialVersionUID
     * @return o serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
