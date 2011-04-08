package plp.operor.expression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional1.util.TipoPolimorfico;
import plp.operor.declaration.DecOperador;
import plp.operor.memory.AmbienteExecucaoOperacional;
import plp.operor.util.ConversorTipos;
import plp.operor.util.ListIdOperador;
import plp.operor.util.ValorDesconhecido;

/**
 * 
 * @author Rafael Fonsï¿½ca
 *
 */
public class Operacao implements Expressao {
	
	private ListIdOperador<Expressao> listaOperadores;
	
	public Operacao(ListIdOperador<Expressao> listaOperadores) {
		this.listaOperadores = listaOperadores;
	}

	public ListIdOperador<Expressao> getListaOperadores() {
		return listaOperadores;
	}

	public Valor avaliar(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		AmbienteExecucaoOperacional ambOperacional = (AmbienteExecucaoOperacional) amb;
		
		List<Valor> valores = new ArrayList<Valor>();
		ListIdOperador<Expressao> listParametros = new ListIdOperador<Expressao>();
		List<Id> listId = new ArrayList<Id>();
		
		boolean isAbstrato = false;
		
		// Avalia as expressões passadas por parametro
		for (Object o : listaOperadores.getListaOrdenacao()) {
			if (!(o instanceof IdOperador)) {
				Expressao exp = (Expressao) o;
				Expressao valorResultado = exp.avaliar(amb);
				if (valorResultado instanceof ValorDesconhecido) {
					isAbstrato = true;
					Id id = ConversorTipos.gerarId();
					valorResultado = id;
					listId.add(id);
				} else if (valorResultado instanceof ValorFuncao) {
					isAbstrato = true;
					ValorFuncao vfun = (ValorFuncao)valorResultado;
					listId.addAll(vfun.getListaId());
					valorResultado = vfun.getExp();
				} else {
					valores.add((Valor)valorResultado);
				}
				listParametros.addElemento(valorResultado);
			} else {
				listParametros.addIdOperador((IdOperador)o);
			}
		}
		
		Valor vresult = null;
		
		if (isAbstrato == false) {
			
			ValorFuncao valorFuncao = ambOperacional.getOperacao(listaOperadores.getDefinicaoGenerica(), ConversorTipos.getTiposAplicacao(valores));
						
			if (valorFuncao != null) {
				
				ambOperacional.incrementa();

				List<Id> parametrosId = valorFuncao.getListaId();
				
				for (int i = 0; i < parametrosId.size(); i++) {
					ambOperacional.map(parametrosId.get(i), valores.get(i));
				}
				
				// Só é executado para aplicacoes dentro de declaration
				if (valorFuncao.getOpAnterior() != null) {
					// Re-declara novamente o operador antigo
					ambOperacional.mapOperador(listaOperadores.getDefinicaoGenerica(),
							valorFuncao.getOpAnterior(), false);
				}
				
				vresult = valorFuncao.getExp().avaliar(ambOperacional);
				
				if (vresult instanceof ValorFuncao && valorFuncao.getOpAnterior() != null) {
					((ValorFuncao)vresult).getOpCriadores().add(valorFuncao.getOpAnterior());
				}
				
				ambOperacional.restaura();
				
			} else {
				throw new VariavelNaoDeclaradaException(listaOperadores.getDefinicaoGenerica());
			}
		} else {
			Operacao op = new Operacao(listParametros);
			vresult = new ValorFuncao(listId, (Expressao)op);
		}
		
		return vresult;
	}

	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return this.getTipo(amb) != null;
	}

	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		/*OperadorGenerico opGenerico = (OperadorGenerico) amb.get(listaOperadores.getDefinicaoGenerica());
		
		if (opGenerico == null) {
			throw new VariavelNaoDeclaradaException(listaOperadores.getDefinicaoGenerica());
		}
		
		List<Tipo> tipos = new ArrayList<Tipo>();
		
		for (Object o : listaOperadores.getListaOrdenacao()) {
			if (!(o instanceof IdOperador)) {
				Expressao exp = (Expressao) o;
				tipos.add(exp.getTipo(amb));
			}
		}
		
		// TODO verificar tipos
		// caso tenha apenas um operador declarado para esta operaï¿½ï¿½o retorna o tipo dela
		// caso tenha mais de um operador verifica os tipos de retorno, caso sejam tipos diferentes 
		// retorna o tipoPolimorfico;
		*/
		
		return new TipoPolimorfico();
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Object o : this.listaOperadores.getListaOrdenacao()) { 
			sb.append(o);
			sb.append(" ");
		}
		return sb.toString();
	}


}
