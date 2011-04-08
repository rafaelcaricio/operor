/*
 * Universidade Federal de Pernambuco - UFPE
 * Centro de Informática - CIn
 * 
 * Paradigmas de Linguagem de Programação - PLP
 * 
 * Tipo: AmbienteFuncional
 */
package plp.operor.memory;

import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.operor.expression.ValorFuncao;


/**
 * Esta interface define um ambiente funcional.
 * 
 * @author Joabe Jesus
 * @author Rafael Caricio
 *
 * @param <T> A classe que representa uma função na linguagem.
 */
public interface AmbienteOperacional<T> {

	/**
	 * Este método mapeia um identificador em uma função.
	 *
	 * @param id O identificador
	 * @param funcao A função mapeada.
	 * 
	 * @throws IdentificadorJaDeclaradoException Se já houver uma função declarada
	 *                                       com o id fornecido.
	 */
	public void mapOperador(Id id, T funcao, boolean avaliarAnterior)
			throws IdentificadorJaDeclaradoException;

	/**
	 * Este método retorna a função cujo id foi dado.
	 *
	 * @param id O identificador que mapeia a função.
	 * 
	 * @throws IdentificadorNaoDeclaradoException Se a função não estiver declarada.
	 */
	public ValorFuncao getOperacao(Id id, List<Tipo> tiposParametros) throws IdentificadorNaoDeclaradoException;

}
