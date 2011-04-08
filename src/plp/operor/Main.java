package plp.operor;

import java.util.ArrayList;
import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions1.util.TipoPrimitivo;
import plp.expressions2.expression.ExpSoma;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.ValorBooleano;
import plp.expressions2.expression.ValorInteiro;
import plp.expressions2.expression.ValorString;
import plp.functional1.declaration.DeclaracaoFuncional;
import plp.functional1.expression.IfThenElse;
import plp.functional1.util.TipoPolimorfico;
import plp.operor.declaration.DecOperador;
import plp.operor.expression.ExpDeclaracao;
import plp.operor.expression.IdOperador;
import plp.operor.expression.Operacao;
import plp.operor.expression.ValorFuncao;
import plp.operor.util.ListIdOperador;

public class Main {
	
	private static final ValorInteiro DEZ = new ValorInteiro(10);
	private static final ValorInteiro QUATRO = new ValorInteiro(4);

	/**
	 * @param args
	 */
	public static void main2(String[] args) {
		
		/*
		 * Codigo para o programa:
		 * 
		 *    let op _:INTEIRO + _:INTEIRO > x y = x + y in 10 + 4 
		 * 
		 * 
		 */
		
		IdOperador opSoma = new IdOperador("+");
		Id x = new Id("x");
		Id y = new Id("y");
		
		ListIdOperador<Tipo> listIdTipo = new ListIdOperador<Tipo>();
		
		listIdTipo.addElemento(TipoPrimitivo.INTEIRO);
		listIdTipo.addIdOperador(opSoma);
		listIdTipo.addElemento(TipoPrimitivo.INTEIRO);
		
		List<Id> argsId = new ArrayList<Id>();
		
		argsId.add(x);
		argsId.add(y);
		
		DecOperador decOperador = new DecOperador(listIdTipo, new ValorFuncao(argsId, new ExpSoma(x, y)));
		
		ListIdOperador<Expressao> listIdExpressao = new ListIdOperador<Expressao>();
		
		listIdExpressao.addElemento(DEZ);
		listIdExpressao.addIdOperador(opSoma);
		listIdExpressao.addElemento(QUATRO);
		
		Operacao operacao = new Operacao(listIdExpressao);
		
		List<DeclaracaoFuncional> declaracoes = new ArrayList<DeclaracaoFuncional>();
		
		declaracoes.add(decOperador);
		
		Programa prg = new Programa(new ExpDeclaracao(declaracoes, operacao));
		
		if (prg.checaTipo()) {
			System.out.println(prg.executar().toString());
		}
	}
	
	public static void main22(String[] args) {
		
		IdOperador opSoma = new IdOperador("+");
		
		ListIdOperador<Expressao> listIdExpressao = new ListIdOperador<Expressao>();
		
		listIdExpressao.addElemento(DEZ);
		listIdExpressao.addIdOperador(opSoma);
		listIdExpressao.addElemento(QUATRO);
		
		Operacao operacao = new Operacao(listIdExpressao);
		
		Programa prg = new Programa(operacao);
		
		if (prg.checaTipo()) {
			System.out.println(prg.executar().toString());
		}
	}
	
	public static void main0(String[] args) {

		IdOperador opTernario1 = new IdOperador("?");
		IdOperador opTernario2 = new IdOperador(":");
		Id x = new Id("x");
		Id y = new Id("y");
		Id z = new Id("z");
		
		ListIdOperador<Tipo> listIdTipo = new ListIdOperador<Tipo>();
		
		listIdTipo.addElemento(TipoPrimitivo.BOOLEANO);
		listIdTipo.addIdOperador(opTernario1);
		listIdTipo.addElemento(new TipoPolimorfico());
		listIdTipo.addIdOperador(opTernario2);
		listIdTipo.addElemento(new TipoPolimorfico());
		
		List<Id> argsId = new ArrayList<Id>();
		
		argsId.add(x);
		argsId.add(y);
		argsId.add(z);
		
		DecOperador decOperador = new DecOperador(listIdTipo, new ValorFuncao(argsId, new IfThenElse(x, y, z)));
		
		ListIdOperador<Expressao> listIdExpressao = new ListIdOperador<Expressao>();
		
		listIdExpressao.addElemento(new ValorBooleano(false));
		listIdExpressao.addIdOperador(opTernario1);
		listIdExpressao.addElemento(new ValorString("Oper"));
		listIdExpressao.addIdOperador(opTernario2);
		listIdExpressao.addElemento(new ValorString("OR"));
		
		Operacao operacao = new Operacao(listIdExpressao);
		
		List<DeclaracaoFuncional> declaracoes = new ArrayList<DeclaracaoFuncional>();
		
		declaracoes.add(decOperador);
		
		Programa prg = new Programa(new ExpDeclaracao(declaracoes, operacao));
		
		if (prg.checaTipo()) {
			System.out.println(prg.executar().toString());
		}
	}
	
	public static void main4(String[] args) {
		
		ListIdOperador<Expressao> listIdExpressao = new ListIdOperador<Expressao>();
		
		listIdExpressao.addElemento(DEZ);
		listIdExpressao.addIdOperador(new IdOperador(">"));
		listIdExpressao.addElemento(QUATRO);
		
		Operacao operacao = new Operacao(listIdExpressao);
		
		IfThenElse ifThen = new IfThenElse(operacao, new ValorString("é maior"), new ValorString("é menor"));
		
		Programa prg = new Programa(ifThen);

		if (prg.checaTipo()) {
			System.out.println(prg.executar().toString());
		}
	}
	
	public static void main(String[] args) {
		Expressao exp1 = (Expressao) new ListIdOperador<Expressao>();
		
		if (exp1 instanceof ListIdOperador) {
			
			System.out.println("ok");
		
		} else {
			System.out.println("nok");
		}
	}

}

