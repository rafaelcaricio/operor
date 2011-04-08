package plp.operor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import plp.expressions1.excecao.ErroTipoException;
import plp.expressions1.util.Tipo;
import plp.expressions1.util.TipoPrimitivo;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.operor.expression.ValorFuncao;

/**
 * Armazena os operadores cuja aplicação são semelhantes por exemplo:
 * 
 * Todos os operadores que usam 
 * 
 * 		_ + _
 *  
 * ou seja:
 * 
 * 		umParametro + outroParametro
 * 
 * @author Rafael Caricio
 *
 */
public class OperadorGenerico {
	
	private Map<ListIdOperador<Tipo>, ValorFuncao> operadoresCompativeis;
	private Id definicaoGenerica;

	public OperadorGenerico() {
		this.operadoresCompativeis = new HashMap<ListIdOperador<Tipo>, ValorFuncao>();
	}

	/**
	 * Mapeia um operador de acordo com a definição genérica do mesmo.
	 * 
	 * @param definicao
	 * @param valorFuncao 
	 * @throws ErroTipoException Caso a definição sendo adicionada não seja compatível com a primeira definição genérica inserida.
	 * 
	 */
	public void addDefinicao(ListIdOperador<Tipo> definicao, ValorFuncao valorFuncao) throws ErroTipoException {
		if (this.operadoresCompativeis.size() == 0) {
			this.operadoresCompativeis.put(definicao, valorFuncao);
			this.definicaoGenerica = definicao.getDefinicaoGenerica();
		} else {
			if (definicaoGenerica.equals(definicao.getDefinicaoGenerica())) {
				this.operadoresCompativeis.put(definicao, valorFuncao);
			} else {
				throw new ErroTipoException(); 
			}
		}
	}
	
	/**
	 * Verifica o operador que mais se adequa a determinados parametros.
	 * Depende do nível de compatibilidade requerido:
	 * 
	 *   0 - Casamento perfeito dos tipos de parametros
	 *   1 - Casamento quase perfeito precisa converter 1 parametro
	 *   2 - Casamento precisa converter 2 parametros
	 *   N - Casamento precisa converter N parametros
	 *   
	 * N é um número menor ou igual a aridade do operador.
	 * 
	 * @param aplicacao
	 * @param nivelCompatibilidade
	 * @return
	 */
	public ValorFuncao getDefinicaoCompativel(List<Tipo> tiposAplicacao) {
		
		ValorFuncao retorno = null;
		
		for (Map.Entry<ListIdOperador<Tipo>, ValorFuncao> operador : this.operadoresCompativeis.entrySet()) {
			List<Tipo> tiposOperador = operador.getKey().getListaElementos();
			
			// Perfeitamente compatível
			if (tiposOperador.equals(tiposAplicacao)) {
				retorno = operador.getValue();
			} 
			
		}
		
		return retorno;
	}

}
