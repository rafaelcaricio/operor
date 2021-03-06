package plp.orientadaObjetos1.comando;

/*
 * A execucao de um comando ocorre em um determinado ambiente. O
 * resultado de tal execucao � a modifica��o deste ambiente, i.e., comandos
 * tem efeito colateral.
 */
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.orientadaObjetos1.declaracao.variavel.DecVariavel;
import plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoJaDeclaradoException;
import plp.orientadaObjetos1.excecao.declaracao.ObjetoNaoDeclaradoException;
import plp.orientadaObjetos1.expressao.leftExpression.Id;
import plp.orientadaObjetos1.expressao.leftExpression.LeftExpression;
import plp.orientadaObjetos1.expressao.valor.ValorRef;
import plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import plp.orientadaObjetos1.memoria.ContextoExecucaoOO1;
import plp.orientadaObjetos1.memoria.DefClasse;
import plp.orientadaObjetos1.memoria.Objeto;
import plp.orientadaObjetos1.util.TipoClasse;

/**
 * Comando de cria��o de objeto e atribui��o deste a uma express�o esquerda.
 */
public class New implements Comando{
	/**
	 * Lado esquerdo da atribui��o.
	 */
    private LeftExpression av;
	/**
	 * Identificador da classe, com o seu nome.
	 */
    private Id classe;
	/**
	 * Construtor.
	 * @param av Lado esquerdo da atribui��o.
	 * @param classe Identificador com o nome da classe.
	 */
    public New(LeftExpression av, Id classe){
          this.av = av;
          this.classe = classe;
    }
	/**
	 * Execu��o da atribui��o de um novo objeto criado a uma left expression.
	 * @param ambiente O ambiente contendo o mapeamento entre identificadores
	 * e valores.
	 * @return o ambiente de execu��o atualizado.
	 */
    public AmbienteExecucaoOO1 executar(AmbienteExecucaoOO1 ambiente)    throws VariavelJaDeclaradaException,
          VariavelNaoDeclaradaException,
          ClasseJaDeclaradaException, ClasseNaoDeclaradaException,
          ObjetoJaDeclaradoException,ObjetoNaoDeclaradoException{

          DefClasse defClasse = ambiente.getDefClasse(classe);
          DecVariavel decVariavel = defClasse.getDecVariavel();
          AmbienteExecucaoOO1 aux = decVariavel.elabora(new ContextoExecucaoOO1(ambiente));
          Objeto objeto = new Objeto(classe, aux.getContextoIdValor());
          ValorRef vr = ambiente.getProxRef();
          ambiente.mapObjeto(vr, objeto);
          ambiente = new Atribuicao(av,vr).executar(ambiente);
          return ambiente;
    }

	/**
	 * Verifica se a atribui��o � poss�vel comparando os tipos do objeto
	 * e da left expression.
	 * @param ambiente O ambiente de compila��o, com o mapeamento
	 * entre identificadores e tipos.
	 */
    public boolean checaTipo(AmbienteCompilacaoOO1 ambiente) throws VariavelNaoDeclaradaException,
      ClasseJaDeclaradaException, ClasseNaoDeclaradaException {
          TipoClasse tpClasse = new TipoClasse(classe);
          return av.checaTipo(ambiente) &&
            tpClasse.eValido(ambiente) &&
            tpClasse.equals(av.getTipo(ambiente));
    }
}