package plp.imperative1.command;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.ValorBooleano;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;

public class IfThenElse implements Comando {

	private Expressao expressao;

	private Comando comandoThen;

	private Comando comandoElse;

	public IfThenElse(Expressao expressao, Comando comandoThen,
			Comando comandoElse) {
		this.expressao = expressao;
		this.comandoThen = comandoThen;
		this.comandoElse = comandoElse;
	}

	/**
	 * Implementa o comando <code>if then else</code>.
	 * 
	 * @param ambiente
	 *            o ambiente de execu��o.
	 * 
	 * @return o ambiente depois de modificado pela execu��o do comando
	 *         <code>if then else</code>.
	 * 
	 */
	public AmbienteExecucaoImperativa executar(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		if (((ValorBooleano) expressao.avaliar(ambiente)).valor())
			return comandoThen.executar(ambiente);
		else
			return comandoElse.executar(ambiente);
	}

	/**
	 * Realiza a verificacao de tipos da express�o e dos comandos do comando
	 * <code>if then else</code>
	 * 
	 * @param ambiente
	 *            o ambiente de compila��o.
	 * @return <code>true</code> se a express�o e os comando s�o bem tipados;
	 *         <code>false</code> caso contrario.
	 */
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		return expressao.checaTipo(ambiente)
				&& expressao.getTipo(ambiente).eBooleano()
				&& comandoThen.checaTipo(ambiente)
				&& comandoElse.checaTipo(ambiente);
	}

}
