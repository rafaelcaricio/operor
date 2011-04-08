package plp.functional2.expression;

import static plp.expressions1.util.ToStringProvider.listToString;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteExecucao;
import plp.functional1.util.DefFuncao;
import plp.functional2.util.PartialInstantiatorVisitor;

/**
 * @author Sérgio
 */
public class ValorFuncao extends DefFuncao implements ValorAbstrato {

	public ValorFuncao(List<Id> argsId, Expressao exp) {
		super(argsId, exp);
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

}
