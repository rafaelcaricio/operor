package plp.operor.expression;

import static plp.expressions1.util.ToStringProvider.listToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteExecucao;
import plp.functional2.expression.ValorAbstrato;
import plp.operor.declaration.DecOperador;
import plp.operor.util.DefFuncao;
import plp.operor.util.PartialInstantiatorVisitor;

/**
 * @author Sérgio
 * @author Rafael Caricio
 */
public class ValorFuncao extends DefFuncao implements ValorAbstrato {

	private DecOperador opAnterior;
	
	private List<DecOperador> opCriadores;
	

	public List<DecOperador> getOpCriadores() {
		return opCriadores;
	}

	public void setOpCriadores(List<DecOperador> opCriadores) {
		this.opCriadores = opCriadores;
	}

	public ValorFuncao(List<Id> argsId, Expressao exp) {
		super(argsId, exp);
		this.opCriadores = new ArrayList<DecOperador>();
	}

	public Valor avaliar(AmbienteExecucao ambiente) {

		Set<Id> variaveisLocais = Collections.unmodifiableSet(new HashSet<Id>(
				this.getListaId()));

		return (Valor) PartialInstantiatorVisitor.getInstance().visit(this,
				ambiente, variaveisLocais);

	}

	@Override
	public String toString() {
		return String.format("fn %s . %s", listToString(getListaId(), " "),
				getExp());
	}

	public DecOperador getOpAnterior() {
		return opAnterior;
	}

	public void setOpAnterior(DecOperador opAnterior) {
		this.opAnterior = opAnterior;
	}


}
