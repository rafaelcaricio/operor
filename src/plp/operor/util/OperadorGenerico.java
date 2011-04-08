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
 * Armazena os operadores cuja aplica��o s�o semelhantes por exemplo:
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
	 * Mapeia um operador de acordo com a defini��o gen�rica do mesmo.
	 * 
	 * @param definicao
	 * @param valorFuncao 
	 * @throws ErroTipoException Caso a defini��o sendo adicionada n�o seja compat�vel com a primeira defini��o gen�rica inserida.
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
	 * Depende do n�vel de compatibilidade requerido:
	 * 
	 *   0 - Casamento perfeito dos tipos de parametros
	 *   1 - Casamento quase perfeito precisa converter 1 parametro
	 *   2 - Casamento precisa converter 2 parametros
	 *   N - Casamento precisa converter N parametros
	 *   
	 * N � um n�mero menor ou igual a aridade do operador.
	 * 
	 * @param aplicacao
	 * @param nivelCompatibilidade
	 * @return
	 */
	public ValorFuncao getDefinicaoCompativel(List<Tipo> tiposAplicacao) {
		
		ValorFuncao retorno = null;
		
		for (Map.Entry<ListIdOperador<Tipo>, ValorFuncao> operador : this.operadoresCompativeis.entrySet()) {
			List<Tipo> tiposOperador = operador.getKey().getListaElementos();
			
			// Perfeitamente compat�vel
			if (tiposOperador.equals(tiposAplicacao)) {
				retorno = operador.getValue();
			} 
			
		}
		
		return retorno;
	}

}
