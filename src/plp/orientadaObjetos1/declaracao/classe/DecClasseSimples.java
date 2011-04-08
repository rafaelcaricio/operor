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
 * Classe que representa a declara��o de uma �nica classe.
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
	 * M�todos da classe.
	 */
    private DecProcedimento metodos;
	/**
	 * Construtor.
	 * @param nomeClasse Nome da classe
	 * @param atributos Atributos da classe
	 * @param metodos M�todos da classe.
	 */
    public  DecClasseSimples(Id nomeClasse, DecVariavel atributos, DecProcedimento metodos){
        this.nomeClasse = nomeClasse;
        this.atributos = atributos;
        this.metodos = metodos;
    }

    /**
     * Cria um mapeamento do identificador para a declara��o desta classe.
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e valores.
     * @return o ambiente modificado pela declara��o da classe.
     */
    public AmbienteExecucaoOO1 elabora(AmbienteExecucaoOO1 ambiente)
        throws ClasseJaDeclaradaException, ClasseNaoDeclaradaException {

        ambiente.mapDefClasse(nomeClasse, new DefClasse(atributos,metodos));
        return ambiente;
    }
    /**
     * Verifica se a declara��o est� bem tipada, ou seja, se a checagem dos tipos
     * dos m�todos e atributos est� ok.
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e seus tipos.
     * @return <code>true</code> se os tipos da declara��o s�o v�lidos;
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