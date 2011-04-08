package plp.operor.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.ContextoExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.operor.declaration.DecOperador;
import plp.operor.expression.ValorFuncao;
import plp.operor.util.ConversorTipos;
import plp.operor.util.OperadorGenerico;

public class ContextoExecucaoOperacional extends ContextoExecucao
		implements AmbienteExecucaoOperacional {

	/**
	 * A pilha de blocos de funcao deste contexto.
	 */
	private Stack<HashMap<Id, OperadorGenerico>> pilhaOperadores;

	/**
	 * Construtor da classe.
	 */
	public ContextoExecucaoOperacional() {
		pilhaOperadores = new Stack<HashMap<Id, OperadorGenerico>>();
	}
	
	public void incrementa() {
		super.incrementa();
		pilhaOperadores.push(new HashMap<Id, OperadorGenerico>());
	}

	public void restaura() {
		super.restaura();
		pilhaOperadores.pop();
	}

	// Remover esse método. Já tem em ContextoExecucao
	/**
	 * Mapeia um identificador em uma funcao.
	 * 
	 * @param idArg
	 *            o identificador
	 * @param funcao
	 *            a funcao.
	 * @exception VariavelJaDeclaradaException
	 *                se o id ja' estiver declarado.
	 */
	public void mapOperador(Id idArg, DecOperador decOperador, boolean valAnterior)
			throws VariavelJaDeclaradaException {
		HashMap<Id, OperadorGenerico> aux = pilhaOperadores.peek();
		OperadorGenerico opGenerico = aux.get(idArg);
		if (opGenerico == null) {
			opGenerico = new OperadorGenerico();
		}
		if (valAnterior) {
			// verificar se ja existe + mesmo operador| se existe seta a definicao
			try {
				ValorFuncao valFuncaoAnterior = this.getOperacao(idArg,
									decOperador.getListaOperadores().getListaElementos());
				DecOperador opAnterior = new DecOperador(decOperador.getListaOperadores(), valFuncaoAnterior);
				decOperador.getValorFuncao().setOpAnterior(opAnterior);
			} catch (VariavelNaoDeclaradaException e) { } // Não é interessante para o caso
		}
		opGenerico.addDefinicao(decOperador.getListaOperadores(), decOperador.getValorFuncao());
		aux.put(idArg, opGenerico);
	}

	// Remover esse método. Já tem em ContextoExecucao
	/**
	 * Retorna uma funcao.
	 * 
	 * @param idArg
	 *            o identificador que mapeia a funcao
	 * @param funcao
	 *            a funcao.
	 * @exception VariavelNaoDeclaradaException
	 *                se o id nao estiver declarado.
	 */
	public ValorFuncao getOperacao(Id idArg, List<Tipo> tipos) throws VariavelNaoDeclaradaException {
		ValorFuncao result = null;
		
		Stack<HashMap<Id, OperadorGenerico>> auxStack = new Stack<HashMap<Id, OperadorGenerico>>();
			
		while (result == null && !pilhaOperadores.empty()) {
			
			HashMap<Id, OperadorGenerico> aux = pilhaOperadores.pop();
			auxStack.push(aux);
			OperadorGenerico opGenerico = aux.get(idArg);
			
			if (opGenerico != null) {
				result = opGenerico.getDefinicaoCompativel(tipos);
			}
		}
		
		while (!auxStack.empty()) {
			pilhaOperadores.push(auxStack.pop());
		}
		if (result == null) {
			throw new VariavelNaoDeclaradaException(idArg);
		}

		return result;
	}
	

}
