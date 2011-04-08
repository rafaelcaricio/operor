package plp.orientadaObjetos1.memoria;

import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.orientadaObjetos1.comando.Procedimento;
import plp.orientadaObjetos1.declaracao.procedimento.DecProcedimento;
import plp.orientadaObjetos1.declaracao.variavel.DecVariavel;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.util.Tipo;

/**
 * Uma defini�ao de classe � uma declara�ao de vari�vel e
 * uma declara��o de procedimento. Ambos podem ser simples ou compostos.
 */
public class DefClasse {
    /**
     * Declara��o de vari�vel
     */
    private DecVariavel decVariavel;
    /**
     * Declara�ao do Procedimento
     */
    private DecProcedimento decProcedimento;

    /**
     * Construtor
     * @param decVariavel Declara��o de Vari�veis
     * @param decProcedimento Declara�ao dos Procedimentos.
     */
    public DefClasse(DecVariavel decVariavel, DecProcedimento decProcedimento) {
        this.decVariavel = decVariavel;
        this.decProcedimento = decProcedimento;
    }
    /**
     * Obt�m as declara�oes das vari�veis.
     * @return as declara�oes das vari�veis.
     */
    public DecVariavel getDecVariavel(){
        return decVariavel;
    }

    /**
     * Retorna um m�todo da classe a partir de seu identificador.
     * @param idMetodo Identificador do m�todo
     * @return o m�todo desejado
     * @throws ProcedimentoNaoDeclaradoException
     */
    public Procedimento getMetodo(Id idMetodo)throws ProcedimentoNaoDeclaradoException{
        return decProcedimento.getProcedimento(idMetodo);
    }

    /**
     * O m�todo abaixo deve verificar se existe algum atributo, identificado por idAtributo
     * na definicao da classe
     * @param idAtributo Um identificador de atributo.
     * @param Tipo O tipo do atributo do identificador.
     */
    public Tipo getTipoAtributo(Id idAtributo)throws VariavelNaoDeclaradaException{
        return decVariavel.getTipo(idAtributo);
    }
}
