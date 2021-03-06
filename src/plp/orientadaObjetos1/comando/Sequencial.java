package plp.orientadaObjetos1.comando;

import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import plp.orientadaObjetos1.excecao.execucao.EntradaInvalidaException;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;

/**
 * Representa um comando sequencial, ou seja, um comando seguido de outro
 * comando.
 */
public class Sequencial implements Comando{
	/**
	 * O primeiro comando.
	 */
    private Comando comando1;
    /**
     * O segundo comando.
     */
    private Comando comando2;
	/**
	 * Construtor.
	 * @param comando1 O primeiro comando
	 * @param comando2 O segundo comando.
	 */
    public Sequencial(Comando comando1, Comando comando2){
        this.comando1 = comando1;
        this.comando2 = comando2;
    }

    /**
     * Executa os comandos sequencialmente.
     * @param ambiente o ambiente de execu��o.
     * @return o ambiente depois de modificado pela execu��o
     * dos comandos.
     */
    public AmbienteExecucaoOO1 executar(AmbienteExecucaoOO1 ambiente)
        throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
               ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException,
               ObjetoJaDeclaradoException, ObjetoNaoDeclaradoException,
               ClasseJaDeclaradaException, ClasseNaoDeclaradaException, EntradaInvalidaException {

        ambiente = comando1.executar(ambiente);
        ambiente = comando2.executar(ambiente);
        return ambiente;
    }

    /**
     * Realiza a verificacao de tipos dos comandos
     * @param ambiente o ambiente de compila��o.
     * @return <code>true</code> se os comandos s�o bem tipados;
     *          <code>false</code> caso contrario.
     */
    public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
        throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
        ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException,
        ClasseJaDeclaradaException, ClasseNaoDeclaradaException {
        boolean resposta1 = comando1.checaTipo(ambiente);
        boolean resposta2 = comando2.checaTipo(ambiente);
        return  resposta1 && resposta2;
    }
}
