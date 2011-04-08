package plp.orientadaObjetos1.excecao.declaracao;

import plp.orientadaObjetos1.expressao.leftExpression.Id;
/**
 * Exce��o lan�ada qunado o objeto que est� sendo declarado j� o foi
 * anteriormente.
 */
public class ObjetoJaDeclaradoException extends Exception {
    /**
     * Construtor
     * @param id Identificador representando o objeto.
     */
    public ObjetoJaDeclaradoException(Id id) {
        super("Objeto" + id + " j� declarado.");
    }

}
