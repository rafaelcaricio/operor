package plp.imperative1.memory;

import plp.expressions2.expression.Valor;
import plp.imperative1.util.Lista;

public class ListaValor extends Lista<Valor> {

	public ListaValor() {

	}

	public ListaValor(Valor valor) {
		super(valor, new ListaValor());

	}

	public ListaValor(Valor valor, ListaValor listaValor) {
		super(valor, listaValor);
	}

}
