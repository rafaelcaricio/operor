package plp.functional3.expression;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional3.util.TipoLista;

public class ExpCompreensaoLista implements Expressao {

	private Expressao expressao;
	private Expressao filtro;
	private Gerador gerador;

	public ExpCompreensaoLista(Expressao expressao) {
		this.expressao = expressao;
	}

	public void setFiltro(Expressao filtro) {
		this.filtro = filtro;
	}

	public void add(Gerador gerador) {
		if (this.gerador == null) {
			this.gerador = gerador;
		} else {
			this.gerador.addProximoGerador(gerador);
		}
	}

	public void setGeradores(List<Gerador> geradores) {
		for (Gerador geradorTemp : geradores) {
			this.add(geradorTemp);
		}
	}

	public Valor avaliar(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		ValorLista result = ValorLista.getInstancia(null, null);

		gerador.gerarValores(amb, result, expressao, filtro);

		return result.inverter();
	}

	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		if (!temGerador())
			return false;

		boolean retorno = gerador.checaTipo(amb);

		amb.incrementa();

		mapTypeBindings(amb);

		retorno &= expressao.checaTipo(amb);
		retorno &= filtroChecaTipo(amb);

		amb.restaura();

		return retorno;
	}

	private void mapTypeBindings(AmbienteCompilacao amb) {
		Map<Id, Tipo> typeBindings = gerador.checkTypeBindings(amb);
		Set<Entry<Id, Tipo>> entrySet = typeBindings.entrySet();
		for (Entry<Id, Tipo> entry : entrySet) {
			amb.map(entry.getKey(), entry.getValue());
		}
	}

	private boolean temGerador() {
		return gerador != null;
	}

	private boolean filtroChecaTipo(AmbienteCompilacao amb) {
		return filtro == null || filtro.checaTipo(amb)
				&& filtro.getTipo(amb).eBooleano();
	}

	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

		amb.incrementa();

		mapTypeBindings(amb);

		TipoLista retorno = new TipoLista(expressao.getTipo(amb));

		amb.restaura();

		return retorno;
	}

}
