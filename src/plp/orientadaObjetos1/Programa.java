package plp.orientadaObjetos1;

import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.orientadaObjetos1.comando.Comando;
import plp.orientadaObjetos1.declaracao.classe.DecClasse;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.excecao.execucao.EntradaInvalidaException;
import plp.orientadaObjetos1.excecao.execucao.EntradaNaoFornecidaException;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import plp.orientadaObjetos1.memoria.colecao.ListaValor;
/**
 * Classe que representa um programa na linguagem OO.
 */
public class Programa {
    /**
     * Declaraçao de classe
     */
    private DecClasse decClasse;
    /**
     * Comando executado após a declaraçao de classes
     */
    private Comando comando;

    /**
     * Construtor.
     * @param decClasse A declaraçao de classe(s)
     * @param comando O comando executado após a declaraçao.
     */
    public Programa(DecClasse decClasse, Comando comando){
        this.decClasse = decClasse;
        this.comando = comando;
    }

     /**
     * Executa o programa.
     *
     * @param ambiente o ambiente de execução.
     *
     * @return o ambiente depois de modificado pela execução
     * do programa.
     *
     * @exception EntradaNaoFornecidaException se não for fornecida
     *  a tail de valores de entrada do programa.
     *
     */
    public ListaValor executar(AmbienteExecucaoOO1 ambiente)
        throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
               ObjetoNaoDeclaradoException, ObjetoJaDeclaradoException,
               ProcedimentoJaDeclaradoException,ProcedimentoNaoDeclaradoException,
               ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
               EntradaNaoFornecidaException, EntradaInvalidaException {
        if(ambiente == null)
            throw new EntradaNaoFornecidaException();
        ambiente.incrementa();
        ambiente = comando.executar(decClasse.elabora(ambiente));
        ambiente.restaura();
        return ambiente.getSaida();
    }

    /**
     * Realiza a verificacao de tipos do programa
     *
     * @param ambiente o ambiente de compilação.
     * @return <code>true</code> se o programa está bem tipado;
     *          <code>false</code> caso contrario.
     *
     * @exception EntradaNaoFornecidaException se não for fornecida
     *  a tail de valores de entrada do programa.
     *
     */
    public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
        throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
               ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException,
               ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
               EntradaNaoFornecidaException{
        boolean resposta;
        if(ambiente == null) {
            throw new EntradaNaoFornecidaException();
        }
        ambiente.incrementa();
        resposta = decClasse.checaTipo(ambiente) && comando.checaTipo(ambiente);
        ambiente.restaura();
        return resposta;
    }
}