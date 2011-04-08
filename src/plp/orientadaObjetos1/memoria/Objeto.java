package plp.orientadaObjetos1.memoria;

import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
/**
 * Conjunto formado pelo nome da classe de um objeto e o seu estado
 * representado pelo ambiente de execução.
 */
public class Objeto {
    /**
     * Identificador da classe a que pertence o objeto.
     */
    private Id classeObjeto;

    /**
     * Estado do objeto no ambiente de execução.
     */
    private AmbienteExecucaoOO1 estado;

    /**
     * Construtor.
     * @param classeObjeto Classe a que pertence este objeto.
     * @param estado Estado do objeto no ambiente de execução.
     */
    public Objeto(Id classeObjeto,  AmbienteExecucaoOO1 estado) {
        this.classeObjeto = classeObjeto;
        this.estado = estado;
    }
    /**
     * Obtem o identificador da classe do objeto.
     * @return o identificador da classe do objeto.
     */
    public Id getClasse() {
         return classeObjeto;
    }

    /**
     * Obtém o atual estado do objeto, conforme o ambiente de execução.
     * @return o atual estado do objeto, conforme o ambiente de execução.
     */
    public AmbienteExecucaoOO1 getEstado() {
         return estado;
    }

    /**
     * Altera o ambiente de Execução, que representa o novo estado do objeto.
     * @param novoEstado o novo estado do objeto.
     */
    public void setEstado(AmbienteExecucaoOO1 novoEstado) {
         this.estado = novoEstado;
    }

}
