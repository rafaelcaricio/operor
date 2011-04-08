package plp.operor.declaration;

import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional1.declaration.DeclaracaoFuncional;
import plp.operor.expression.IdOperador;
import plp.operor.expression.ValorFuncao;
import plp.operor.util.ListIdOperador;

/**
 * 
 * @author Rafael Fonsêca
 * @author Romeu Guimarães
 *
 */
public class DecOperador implements DeclaracaoFuncional {
	
	private ListIdOperador<Tipo> listaOperadores;
	
	private ValorFuncao valorFuncao;
	
	public DecOperador(ListIdOperador<Tipo> listaOperadores, ValorFuncao valorFuncao) {
		this.listaOperadores = listaOperadores;
		this.setValorFuncao(valorFuncao);
	}

	public boolean checaTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		boolean retorno = true;
		
		retorno &= listaOperadores.getListaElementos().size() == this.getValorFuncao().getAridade();
		
		return retorno;
	}
	
	public ListIdOperador<Tipo> getListaOperadores() {
		return this.listaOperadores;
	}

	public Id getId() {
		return new Id(this.listaOperadores.toString());
	}

	public Tipo getTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		ambiente.incrementa();
		
		List<Tipo> params = this.listaOperadores.getListaElementos();
		
		
		for (int i=0; i < this.getValorFuncao().getAridade(); i++) {
			ambiente.map(this.getValorFuncao().getListaId().get(i), params.get(i));
		}
		
		Tipo retorno = this.getValorFuncao().getTipo(ambiente);
		
		ambiente.restaura();
		return retorno;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("op ");
		
		for (Object o : this.listaOperadores.getListaOrdenacao()) {
			if (o instanceof Tipo) {
				sb.append("_:");
				sb.append(((Tipo)o).getNome());
				sb.append(" ");
			} else if (o instanceof IdOperador) {
				sb.append(o);
				sb.append(" ");
			}
		}
		
		sb.append("on ");
		
		for (Id id : this.getValorFuncao().getListaId()) {
			sb.append(id);
			sb.append(" ");
		}
		
		sb.append("= ");
		sb.append(this.getValorFuncao().toString());
		
		return sb.toString();
	}

	public int getAridade() {
		return this.getValorFuncao().getAridade();
	}

	public Expressao getExpressao() {
		return this.getValorFuncao().getExp();
	}

	public void setValorFuncao(ValorFuncao valorFuncao) {
		this.valorFuncao = valorFuncao;
	}

	public ValorFuncao getValorFuncao() {
		return valorFuncao;
	}

}
