package plp.operor;

import java.util.ArrayList;
import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions1.util.TipoPrimitivo;
import plp.expressions2.expression.ExpAnd;
import plp.expressions2.expression.ExpConcat;
import plp.expressions2.expression.ExpEquals;
import plp.expressions2.expression.ExpOr;
import plp.expressions2.expression.ExpSoma;
import plp.expressions2.expression.ExpSub;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.ContextoCompilacao;
import plp.expressions2.memory.ContextoExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional1.declaration.DeclaracaoFuncional;
import plp.functional3.expression.ExpMaiorQue;
import plp.functional3.expression.ExpMenorQue;
import plp.operor.declaration.DecOperador;
import plp.operor.expression.ExpDeclaracao;
import plp.operor.expression.IdOperador;
import plp.operor.expression.ValorFuncao;
import plp.operor.memory.ContextoExecucaoOperacional;
import plp.operor.util.ListIdOperador;

public class Programa {
	private Expressao exp;

	public Programa(Expressao exp) {
		Id x = new Id("x");
		Id y = new Id("y");
		
		List<Id> argsId = new ArrayList<Id>();
		
		argsId.add(x);
		argsId.add(y);
		
		List<DeclaracaoFuncional> declaracoes = new ArrayList<DeclaracaoFuncional>();
		
		declaracoes.add(new DecOperador(
				criarDeclaracaoOpBinario(new IdOperador("+"), TipoPrimitivo.INTEIRO), 
				new ValorFuncao(argsId,
				new ExpSoma(x, y))));
		
		declaracoes.add(new DecOperador(
				criarDeclaracaoOpBinario(new IdOperador("-"), TipoPrimitivo.INTEIRO), 
				new ValorFuncao(argsId,
				new ExpSub(x, y))));

		declaracoes.add(new DecOperador(
				criarDeclaracaoOpBinario(new IdOperador("<"), TipoPrimitivo.INTEIRO), 
				new ValorFuncao(argsId,
				new ExpMenorQue(x, y))));		
		
		declaracoes.add(new DecOperador(
				criarDeclaracaoOpBinario(new IdOperador(">"), TipoPrimitivo.INTEIRO), 
				new ValorFuncao(argsId,
				new ExpMaiorQue(x, y))));
		
		declaracoes.add(new DecOperador(
				criarDeclaracaoOpBinario(new IdOperador("+"), TipoPrimitivo.STRING), 
				new ValorFuncao(argsId,
				new ExpConcat(x, y))));
		
		declaracoes.add(new DecOperador(
				criarDeclaracaoOpBinario(new IdOperador("=="), TipoPrimitivo.STRING), 
				new ValorFuncao(argsId,
				new ExpEquals(x, y))));
		
		declaracoes.add(new DecOperador(
				criarDeclaracaoOpBinario(new IdOperador("=="), TipoPrimitivo.INTEIRO), 
				new ValorFuncao(argsId,
				new ExpEquals(x, y))));
		
		declaracoes.add(new DecOperador(
				criarDeclaracaoOpBinario(new IdOperador("=="), TipoPrimitivo.BOOLEANO), 
				new ValorFuncao(argsId,
				new ExpEquals(x, y))));
		
		declaracoes.add(new DecOperador(
				criarDeclaracaoOpBinario(new IdOperador("&&"), TipoPrimitivo.BOOLEANO), 
				new ValorFuncao(argsId,
				new ExpAnd(x, y))));
		
		declaracoes.add(new DecOperador(
				criarDeclaracaoOpBinario(new IdOperador("||"), TipoPrimitivo.BOOLEANO), 
				new ValorFuncao(argsId,
				new ExpOr(x, y))));
		
		this.exp = new ExpDeclaracao(declaracoes, exp);
	}
	
	private ListIdOperador<Tipo> criarDeclaracaoOpBinario(IdOperador op, TipoPrimitivo tp) {
		
		ListIdOperador<Tipo> listIdTipo = new ListIdOperador<Tipo>();
		
		listIdTipo.addElemento(tp);
		listIdTipo.addIdOperador(op);
		listIdTipo.addElemento(tp);
		
		return listIdTipo;
	}

	public Valor executar()
		throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		AmbienteExecucao ambExec = new ContextoExecucaoOperacional();
		return exp.avaliar(ambExec);
	}

	public boolean checaTipo()
		throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		AmbienteCompilacao ambComp = new ContextoCompilacao();
		return exp.checaTipo(ambComp);
	}

	public Expressao getExpressao() {
		return exp;
	}
}
