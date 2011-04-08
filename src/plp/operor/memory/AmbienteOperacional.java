/*
 * Universidade Federal de Pernambuco - UFPE
 * Centro de Inform�tica - CIn
 * 
 * Paradigmas de Linguagem de Programa��o - PLP
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
 * @param <T> A classe que representa uma fun��o na linguagem.
 */
public interface AmbienteOperacional<T> {

	/**
	 * Este m�todo mapeia um identificador em uma fun��o.
	 *
	 * @param id O identificador
	 * @param funcao A fun��o mapeada.
	 * 
	 * @throws IdentificadorJaDeclaradoException Se j� houver uma fun��o declarada
	 *                                       com o id fornecido.
	 */
	public void mapOperador(Id id, T funcao, boolean avaliarAnterior)
			throws IdentificadorJaDeclaradoException;

	/**
	 * Este m�todo retorna a fun��o cujo id foi dado.
	 *
	 * @param id O identificador que mapeia a fun��o.
	 * 
	 * @throws IdentificadorNaoDeclaradoException Se a fun��o n�o estiver declarada.
	 */
	public ValorFuncao getOperacao(Id id, List<Tipo> tiposParametros) throws IdentificadorNaoDeclaradoException;

}
