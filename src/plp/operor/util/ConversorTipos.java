package plp.operor.util;

import java.util.ArrayList;
import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions1.util.TipoPrimitivo;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.expression.ValorBooleano;
import plp.expressions2.expression.ValorConcreto;
import plp.expressions2.expression.ValorInteiro;
import plp.expressions2.expression.ValorString;
import plp.functional1.util.TipoPolimorfico;

/**
 * Efetua todas as convers�es de tipos permitidas na linguagem.
 * 
 * @author Rafael Caricio
 *
 */
public class ConversorTipos {
	
	
	/**
	 * �rvore de convers�es permitida:
	 * 
	 *      string
	 *      |   |
	 *    int - bool
	 * 
	 * @param tipo
	 * @return
	 */
	public static final Valor widening(Valor valor, Tipo forcarTipo) {
		if (valor instanceof ValorConcreto) {
			if (valor instanceof ValorInteiro && forcarTipo.equals(TipoPrimitivo.BOOLEANO)) {
				if (((ValorInteiro)valor).valor() > 0) {
					return new ValorBooleano(true);
				} else {
					return new ValorBooleano(false);
				}
			} else if (valor instanceof ValorBooleano && forcarTipo.equals(TipoPrimitivo.INTEIRO)) {
				if (((ValorBooleano)valor).valor()) {
					return new ValorInteiro(1);
				} else {
					return new ValorInteiro(0);
				}
			} else {
				return new ValorString(((ValorConcreto)valor).valor().toString());
			}
		} else {
			return new ValorDesconhecido();
		}
	}
	
	/**
	 * Dada uma lista de valores retorna uma lista com os Tipos dos respectivos valores.
	 * 
	 * @param aplicacao
	 * @param tiposAplicacao
	 */
	public static List<Tipo> getTiposAplicacao(List<Valor> aplicacao) {
		List<Tipo> result = new ArrayList<Tipo>();
		for (Valor valorResultado : aplicacao) {
			if (valorResultado instanceof ValorBooleano) {
				result.add(TipoPrimitivo.BOOLEANO);
			} else if (valorResultado instanceof ValorInteiro) {
				result.add(TipoPrimitivo.INTEIRO);
			} else if (valorResultado instanceof ValorString) {
				result.add(TipoPrimitivo.STRING);
			} else {
				result.add( TipoPolimorfico.CURINGA );
			}
		}
		return result;
	}
	
	
	public static final Tipo max(Tipo ... tipos) {
		Tipo maximo = null;
		
		for (Tipo tipo : tipos) {
			if (!tipo.equals(maximo)) {
				
			}
		}
		return null;
	}
	
	public static int count = 0;
	public static final Id gerarId() {
		return new Id("$" + count++);
	}

}
