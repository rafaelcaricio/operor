package plp.operor.util;

import java.util.ArrayList;
import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Id;
import plp.operor.expression.IdOperador;

/**
 * 
 * @author Rafael Fons�ca
 *
 */
public class ListIdOperador<T> {
	
	private List<T> listaElementos;
	
	private List<IdOperador> listaIdOperador;

	private List listaOrdenacao;
	
	public ListIdOperador() {
		this.listaElementos = new ArrayList<T>();
		this.listaIdOperador = new ArrayList<IdOperador>();
		this.listaOrdenacao = new ArrayList();
		
	}

	public List<T> getListaElementos() {
		return new ArrayList<T>(listaElementos);
	}

	public List<IdOperador> getListaIdOperador() {
		return new ArrayList<IdOperador>(listaIdOperador);
	}
	
	
	public List getListaOrdenacao() {
		return new ArrayList(listaOrdenacao);
	}

	public void addElemento(T tipo) {
		this.listaOrdenacao.add(tipo);
		this.listaElementos.add(tipo);
	}
	
	public void addElemento(int posicao, T tipo) {
		this.listaOrdenacao.add(posicao, tipo);
		this.listaElementos.add(posicao, tipo);
	}
	
	public void addIdOperador(IdOperador operador) {
		this.listaOrdenacao.add(operador);
		this.listaIdOperador.add(operador);
	}
	
	public void addIdOperador(int posicao, IdOperador operador) {
		this.listaOrdenacao.add(posicao, operador);
		this.listaIdOperador.add(posicao, operador);
	}
	
	public Id getDefinicaoGenerica() {
		StringBuffer sb = new StringBuffer();
		for (Object o : this.getListaOrdenacao()) {
			if (o instanceof IdOperador) {
				sb.append(o);
			} else {
				sb.append("_");
			}
		}
		return new Id(sb.toString());
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("*");
		for (Object o : this.listaOrdenacao) {
			sb.append(o.toString());
			sb.append("*");
		}
		return sb.toString();
	}
	
	public boolean equals(Object o) {
		boolean retorno = false;
		if (o instanceof ListIdOperador) {
			ListIdOperador listId = (ListIdOperador) o;
			for (int i = 0; i < listId.getListaOrdenacao().size(); i++) {
				Object atual = listId.getListaOrdenacao().get(i);
				if (atual.equals(this.getListaOrdenacao().get(i))) {
					retorno = true;
				} else {
					return false;
				}
			}
		}
		return retorno ;
	}

}
