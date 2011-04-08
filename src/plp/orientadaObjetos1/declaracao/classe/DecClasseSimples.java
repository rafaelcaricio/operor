package plp.orientadaObjetos1.declaracao.classe;

import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.orientadaObjetos1.declaracao.procedimento.DecProcedimento;
import plp.orientadaObjetos1.declaracao.variavel.DecVariavel;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.orientadaObjetos1.util.TipoClasse;
/**
 * Classe que representa a declaração de uma única classe.
 */
public class DecClasseSimples implements DecClasse {
	/**
	 * Identificador do nome da classe.
	 */
    private Id nomeClasse;
	/**
	 * Atributos da classe.
	 */
    private DecVariavel atributos;
	/**
	 * Métodos da classe.
	 */
    private DecProcedimento metodos;
	/**
	 * Construtor.
	 * @param nomeClasse Nome da classe
	 * @param atributos Atributos da classe
	 * @param metodos Métodos da classe.
	 */
    public  DecClasseSimples(Id nomeClasse, DecVariavel atributos, DecProcedimento metodos){
        this.nomeClasse = nomeClasse;
        this.atributos = atributos;
        this.metodos = metodos;
    }

    /**
     * Cria um mapeamento do identificador para a declaração desta classe.
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e valores.
     * @return o ambiente modificado pela declaração da classe.
     */
    public AmbienteExecucaoOO1 elabora(AmbienteExecucaoOO1 ambiente)
        throws ClasseJaDeclaradaException, ClasseNaoDeclaradaException {

        ambiente.mapDefClasse(nomeClasse, new DefClasse(atributos,metodos));
        return ambiente;
    }
    /**
     * Verifica se a declaração está bem tipada, ou seja, se a checagem dos tipos
     * dos métodos e atributos está ok.
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e seus tipos.
     * @return <code>true</code> se os tipos da declaração são válidos;
     *          <code>false</code> caso contrario.
     */
    public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
        throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
               ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
               ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException {

        ambiente.mapDefClasse(nomeClasse, new DefClasse(atributos,metodos));
        boolean resposta = false;
        ambiente.incrementa();
        if (atributos.checaTipo(ambiente)){
            ambiente.map(new Id("this"), new TipoClasse(nomeClasse));
            resposta =  metodos.checaTipo(ambiente);
        }
        ambiente.restaura();
        return resposta;
    }
}