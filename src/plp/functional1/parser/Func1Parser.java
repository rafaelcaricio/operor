/* Generated By:JavaCC: Do not edit this line. Func1Parser.java */
package plp.functional1.parser;

import plp.functional1.*;
import plp.functional1.declaration.DecVariavel;
import plp.functional1.declaration.DeclaracaoFuncional;
import plp.functional1.declaration.DecFuncao;
import plp.functional1.expression.ExpDeclaracao;
import plp.functional1.expression.IfThenElse;
import plp.functional1.expression.Aplicacao;
import java.util.*;
import plp.expressions2.expression.ExpAnd;
import plp.expressions2.expression.ExpConcat;
import plp.expressions2.expression.ExpEquals;
import plp.expressions2.expression.ExpLength;
import plp.expressions2.expression.ExpMenos;
import plp.expressions2.expression.ExpNot;
import plp.expressions2.expression.ExpOr;
import plp.expressions2.expression.ExpSoma;
import plp.expressions2.expression.ExpSub;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.expression.ValorBooleano;
import plp.expressions2.expression.ValorInteiro;
import plp.expressions2.expression.ValorString;


public class Func1Parser implements Func1ParserConstants {

    public static void main(String args[]) {
            Func1Parser parser;
            if (args.length == 0) {
                System.out.println("Funcional 1 PLP Parser Version 0.0.1:  Reading from standard input . . .");
                parser = new Func1Parser(System.in);
            } else if (args.length == 1) {
                System.out.println("Funcional 1 PLP Parser Version 0.0.1:  Reading from file " + args[0] + " . . .");
                try {
                        parser = new Func1Parser(new java.io.FileInputStream(args[0]));
                } catch (java.io.FileNotFoundException e) {
                        System.out.println("Funcional 1 PLP Parser Version 0.0.1:  File " + args[0] + " not found.");
                        return;
                }
            } else {
                System.out.println("Funcional 1 PLP Parser Version 0.0.1:  Usage is one of:");
                System.out.println("         java Func1Parser < inputfile");
                System.out.println("OR");
                System.out.println("         java Func1Parser inputfile");
                return;
            }
            Programa programa = null;
            try {
                programa = parser.Input();
                System.out.println("Funcional 1 PLP Parser Version 0.0.1: Expressoes1 program parsed successfully.");
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Funcional 1 PLP Parser Version 0.0.1: Encountered errors during parse.");
                System.exit(0);
            }
            try {
                System.out.println("Funcional 1 PLP Parser Version 0.0.1: running...");
                Valor val = programa.executar();
                if (val instanceof ValorString) {
                        ValorString valStr = (ValorString) val;
                        System.out.println("Funcional 1 PLP Parser Version 0.0.1: resultado="+valStr.valor());
                } else if (val instanceof ValorInteiro) {
                    ValorInteiro valInt = (ValorInteiro) val;
                        System.out.println("Funcional 1 PLP Parser Version 0.0.1: resultado="+valInt.valor());
            } else if (val instanceof ValorBooleano) {
                ValorBooleano valBool = (ValorBooleano) val;
                System.out.println("Funcional 1 PLP Parser Version 0.0.1: resultado="+valBool.valor());
            }
                } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Funcional 1 PLP Parser Version 0.0.1:  Encountered errors during execution.");
            }
        }

  static final public Programa Input() throws ParseException {
    Programa retorno;
    retorno = PPrograma();
    jj_consume_token(0);
        {if (true) return retorno;}
    throw new Error("Missing return statement in function");
  }

  static final public Valor PValorInteiro() throws ParseException {
        Token token;
    token = jj_consume_token(INTEGER_LITERAL);
                //System.out.println("ValorInteiro=" + token.toString());
                {if (true) return new ValorInteiro(Integer.parseInt(token.toString()));}
    throw new Error("Missing return statement in function");
  }

  static final public Valor PValorBooleano() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FALSE:
      jj_consume_token(FALSE);
                    {if (true) return new ValorBooleano(false);}
      break;
    case TRUE:
      jj_consume_token(TRUE);
                    {if (true) return new ValorBooleano(true);}
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Valor PValorString() throws ParseException {
        Token token;
    token = jj_consume_token(STRING_LITERAL);
//		System.out.println("ValorString=" + token.toString().length());
                String tokenStr = token.toString();
                tokenStr = tokenStr.substring(1,tokenStr.length()-1);
                {if (true) return new ValorString(tokenStr);}
    throw new Error("Missing return statement in function");
  }

  static final public Valor PValor() throws ParseException {
        Valor retorno;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER_LITERAL:
      retorno = PValorInteiro();
      break;
    case TRUE:
    case FALSE:
      retorno = PValorBooleano();
      break;
    case STRING_LITERAL:
      retorno = PValorString();
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                {if (true) return retorno;}
    throw new Error("Missing return statement in function");
  }

  static final public Id PId() throws ParseException {
        Token token;
    token = jj_consume_token(IDENTIFIER);
                //System.out.println("Id=" + token.toString());
                //System.out.println("tam id=" + token.toString().length());
                String tokenStr = token.toString();
//		tokenStr = tokenStr.substring(1,tokenStr.length()-1);
                {if (true) return new Id(tokenStr);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao PExpMenos() throws ParseException {
        Expressao retorno;
    jj_consume_token(MINUS);
    retorno = PExpPrimaria();
                {if (true) return new ExpMenos(retorno);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao PExpNot() throws ParseException {
        Expressao retorno;
    jj_consume_token(NOT);
    retorno = PExpPrimaria();
                {if (true) return new ExpNot(retorno);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao PExpLength() throws ParseException {
        Expressao retorno;
    jj_consume_token(LENGTH);
    retorno = PExpPrimaria();
                if (retorno instanceof ValorString) {
                    ValorString val = (ValorString) retorno;
//		    System.out.println("Length val=" + val.valor());
                }
//		System.out.println("1-" + retorno.toString());
                {if (true) return new ExpLength(retorno);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao PExpPrimaria() throws ParseException {
    Expressao retorno;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
    case FALSE:
    case INTEGER_LITERAL:
    case STRING_LITERAL:
      retorno = PValor();
      break;
    case IDENTIFIER:
      retorno = PId();
      break;
    case LPAREN:
      jj_consume_token(LPAREN);
      retorno = PExpressao();
      jj_consume_token(RPAREN);
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                //System.out.println("PExpPrimaria=" + retorno);
                {if (true) return retorno;}
    throw new Error("Missing return statement in function");
  }

  static final public List PListaId() throws ParseException {
        List<Id> retorno = null;
        Id id;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENTIFIER:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_1;
      }
      id = PId();
         if (retorno == null) {
             retorno = new ArrayList<Id>();
             retorno.add(id);
         } else {
             retorno.add(id);
         }
    }
        {if (true) return retorno;}
    throw new Error("Missing return statement in function");
  }

  static final public DeclaracaoFuncional PDeclVar() throws ParseException {
        Id id;
        Expressao expressao;
        DeclaracaoFuncional retorno;
    jj_consume_token(VAR);
    id = PId();
    jj_consume_token(ASSIGN);
    expressao = PExpressao();
                {if (true) return new DecVariavel(id, expressao);}
    throw new Error("Missing return statement in function");
  }

  static final public DeclaracaoFuncional PDeclFuncao() throws ParseException {
        Id id;
        Expressao expressao;
        DeclaracaoFuncional retorno;
        List<Id> lista;
    jj_consume_token(FUNC);
    id = PId();
    lista = PListaId();
    jj_consume_token(ASSIGN);
    expressao = PExpressao();
                {if (true) return new DecFuncao(id, lista, expressao);}
    throw new Error("Missing return statement in function");
  }

  static final public List PDeclFuncional() throws ParseException {
        List retorno=null;
        DeclaracaoFuncional decl;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VAR:
      decl = PDeclVar();
      break;
    case FUNC:
      decl = PDeclFuncao();
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
         retorno = new ArrayList();
         retorno.add(decl);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_2;
      }
      if (jj_2_1(2147483647)) {
        jj_consume_token(COMMA);
        decl = PDeclVar();
      } else if (jj_2_2(2147483647)) {
        jj_consume_token(COMMA);
        decl = PDeclFuncao();
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
             retorno.add(decl);
    }
            {if (true) return retorno;}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao PExpDeclaracao() throws ParseException {
        List declaracoes;
        Expressao expressao;
    jj_consume_token(LET);
    declaracoes = PDeclFuncional();
    jj_consume_token(IN);
    expressao = PExpressao();
                {if (true) return new ExpDeclaracao(declaracoes, expressao);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao PExpCondicional() throws ParseException {
        Expressao expCond, expThen, expElse;
    jj_consume_token(IF);
    expCond = PExpressao();
    jj_consume_token(THEN);
    expThen = PExpressao();
    jj_consume_token(ELSE);
    expElse = PExpressao();
                {if (true) return new IfThenElse(expCond, expThen, expElse);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao PAplicacao() throws ParseException {
        List expressoes = null;
        Expressao expressao;
        Id id;
    id = PId();
    jj_consume_token(LPAREN);
    expressao = PExpressao();
            expressoes = new ArrayList();
            expressoes.add(expressao);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[6] = jj_gen;
        break label_3;
      }
      jj_consume_token(COMMA);
      expressao = PExpressao();
          expressoes.add(expressao);
    }
    jj_consume_token(RPAREN);
                {if (true) return new Aplicacao(id, expressoes);}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao PExpUnaria() throws ParseException {
        Expressao retorno;
    if (jj_2_3(2147483647)) {
      retorno = PExpMenos();
    } else if (jj_2_4(2147483647)) {
      retorno = PExpNot();
    } else if (jj_2_5(2147483647)) {
      retorno = PExpLength();
    } else if (jj_2_6(2147483647)) {
      retorno = PExpDeclaracao();
    } else if (jj_2_7(2147483647)) {
      retorno = PExpCondicional();
    } else if (jj_2_8(2147483647)) {
      retorno = PAplicacao();
    } else if (jj_2_9(2147483647)) {
      retorno = PExpPrimaria();
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
                //System.out.println("ExpressaoUnaria=" + retorno);
                {if (true) return retorno;}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao PExpBinaria() throws ParseException {
        Expressao retorno, param2;
    retorno = PExpUnaria();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
      case OR:
      case EQ:
      case CONCAT:
      case PLUS:
      case MINUS:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PLUS:
        jj_consume_token(PLUS);
        param2 = PExpUnaria();
                retorno = new ExpSoma(retorno, param2);
        break;
      case MINUS:
        jj_consume_token(MINUS);
        param2 = PExpUnaria();
                retorno = new ExpSub(retorno, param2);
        break;
      case AND:
        jj_consume_token(AND);
        param2 = PExpUnaria();
                retorno = new ExpAnd(retorno, param2);
        break;
      case OR:
        jj_consume_token(OR);
        param2 = PExpUnaria();
                retorno = new ExpOr(retorno, param2);
        break;
      case EQ:
        jj_consume_token(EQ);
        param2 = PExpUnaria();
                retorno = new ExpEquals(retorno, param2);
        break;
      case CONCAT:
        jj_consume_token(CONCAT);
        param2 = PExpUnaria();
                retorno = new ExpConcat(retorno, param2);
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
                //System.out.println("ExpressaoBinaria=" + retorno);
                {if (true) return retorno;}
    throw new Error("Missing return statement in function");
  }

  static final public Expressao PExpressao() throws ParseException {
        Expressao retorno;
    retorno = PExpBinaria();
                {if (true) return retorno;}
    throw new Error("Missing return statement in function");
  }

  static final public Programa PPrograma() throws ParseException {
        Expressao retorno;
    retorno = PExpressao();
                //System.out.println("Expressao=" + retorno);
                {if (true) return new Programa(retorno);}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  static private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  static private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  static private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  static private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  static private boolean jj_2_9(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  static private boolean jj_3R_42() {
    if (jj_scan_token(OR)) return true;
    if (jj_3R_26()) return true;
    return false;
  }

  static private boolean jj_3R_31() {
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_48()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_47() {
    if (jj_scan_token(STRING_LITERAL)) return true;
    return false;
  }

  static private boolean jj_3R_41() {
    if (jj_scan_token(AND)) return true;
    if (jj_3R_26()) return true;
    return false;
  }

  static private boolean jj_3R_8() {
    if (jj_scan_token(LET)) return true;
    if (jj_3R_12()) return true;
    if (jj_scan_token(IN)) return true;
    if (jj_3R_13()) return true;
    return false;
  }

  static private boolean jj_3R_16() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_13()) return true;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  static private boolean jj_3R_51() {
    if (jj_scan_token(TRUE)) return true;
    return false;
  }

  static private boolean jj_3R_50() {
    if (jj_scan_token(FALSE)) return true;
    return false;
  }

  static private boolean jj_3R_46() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_50()) {
    jj_scanpos = xsp;
    if (jj_3R_51()) return true;
    }
    return false;
  }

  static private boolean jj_3R_40() {
    if (jj_scan_token(MINUS)) return true;
    if (jj_3R_26()) return true;
    return false;
  }

  static private boolean jj_3R_15() {
    if (jj_3R_10()) return true;
    return false;
  }

  static private boolean jj_3_2() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_scan_token(FUNC)) return true;
    return false;
  }

  static private boolean jj_3R_14() {
    if (jj_3R_21()) return true;
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_scan_token(VAR)) return true;
    return false;
  }

  static private boolean jj_3R_39() {
    if (jj_scan_token(PLUS)) return true;
    if (jj_3R_26()) return true;
    return false;
  }

  static private boolean jj_3R_11() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_14()) {
    jj_scanpos = xsp;
    if (jj_3R_15()) {
    jj_scanpos = xsp;
    if (jj_3R_16()) return true;
    }
    }
    return false;
  }

  static private boolean jj_3R_27() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_39()) {
    jj_scanpos = xsp;
    if (jj_3R_40()) {
    jj_scanpos = xsp;
    if (jj_3R_41()) {
    jj_scanpos = xsp;
    if (jj_3R_42()) {
    jj_scanpos = xsp;
    if (jj_3R_43()) {
    jj_scanpos = xsp;
    if (jj_3R_44()) return true;
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_20() {
    if (jj_3R_26()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_27()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_25() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_23()) return true;
    return false;
  }

  static private boolean jj_3_9() {
    if (jj_3R_11()) return true;
    return false;
  }

  static private boolean jj_3R_45() {
    if (jj_scan_token(INTEGER_LITERAL)) return true;
    return false;
  }

  static private boolean jj_3R_24() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_22()) return true;
    return false;
  }

  static private boolean jj_3_8() {
    if (jj_3R_10()) return true;
    if (jj_scan_token(LPAREN)) return true;
    return false;
  }

  static private boolean jj_3R_19() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_24()) {
    jj_scanpos = xsp;
    if (jj_3R_25()) return true;
    }
    return false;
  }

  static private boolean jj_3_7() {
    if (jj_3R_9()) return true;
    return false;
  }

  static private boolean jj_3_6() {
    if (jj_3R_8()) return true;
    return false;
  }

  static private boolean jj_3R_7() {
    if (jj_scan_token(LENGTH)) return true;
    if (jj_3R_11()) return true;
    return false;
  }

  static private boolean jj_3R_38() {
    if (jj_3R_11()) return true;
    return false;
  }

  static private boolean jj_3R_18() {
    if (jj_3R_23()) return true;
    return false;
  }

  static private boolean jj_3_5() {
    if (jj_3R_7()) return true;
    return false;
  }

  static private boolean jj_3R_17() {
    if (jj_3R_22()) return true;
    return false;
  }

  static private boolean jj_3R_37() {
    if (jj_3R_49()) return true;
    return false;
  }

  static private boolean jj_3_4() {
    if (jj_3R_6()) return true;
    return false;
  }

  static private boolean jj_3R_12() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_17()) {
    jj_scanpos = xsp;
    if (jj_3R_18()) return true;
    }
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_19()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_36() {
    if (jj_3R_9()) return true;
    return false;
  }

  static private boolean jj_3_3() {
    if (jj_3R_5()) return true;
    return false;
  }

  static private boolean jj_3R_35() {
    if (jj_3R_8()) return true;
    return false;
  }

  static private boolean jj_3R_6() {
    if (jj_scan_token(NOT)) return true;
    if (jj_3R_11()) return true;
    return false;
  }

  static private boolean jj_3R_34() {
    if (jj_3R_7()) return true;
    return false;
  }

  static private boolean jj_3R_33() {
    if (jj_3R_6()) return true;
    return false;
  }

  static private boolean jj_3R_32() {
    if (jj_3R_5()) return true;
    return false;
  }

  static private boolean jj_3R_23() {
    if (jj_scan_token(FUNC)) return true;
    if (jj_3R_10()) return true;
    if (jj_3R_31()) return true;
    if (jj_scan_token(ASSIGN)) return true;
    if (jj_3R_13()) return true;
    return false;
  }

  static private boolean jj_3R_5() {
    if (jj_scan_token(MINUS)) return true;
    if (jj_3R_11()) return true;
    return false;
  }

  static private boolean jj_3R_26() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_32()) {
    jj_scanpos = xsp;
    if (jj_3R_33()) {
    jj_scanpos = xsp;
    if (jj_3R_34()) {
    jj_scanpos = xsp;
    if (jj_3R_35()) {
    jj_scanpos = xsp;
    if (jj_3R_36()) {
    jj_scanpos = xsp;
    if (jj_3R_37()) {
    jj_scanpos = xsp;
    if (jj_3R_38()) return true;
    }
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_22() {
    if (jj_scan_token(VAR)) return true;
    if (jj_3R_10()) return true;
    if (jj_scan_token(ASSIGN)) return true;
    if (jj_3R_13()) return true;
    return false;
  }

  static private boolean jj_3R_10() {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  static private boolean jj_3R_52() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_13()) return true;
    return false;
  }

  static private boolean jj_3R_13() {
    if (jj_3R_20()) return true;
    return false;
  }

  static private boolean jj_3R_49() {
    if (jj_3R_10()) return true;
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_13()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_52()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  static private boolean jj_3R_30() {
    if (jj_3R_47()) return true;
    return false;
  }

  static private boolean jj_3R_29() {
    if (jj_3R_46()) return true;
    return false;
  }

  static private boolean jj_3R_28() {
    if (jj_3R_45()) return true;
    return false;
  }

  static private boolean jj_3R_21() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_28()) {
    jj_scanpos = xsp;
    if (jj_3R_29()) {
    jj_scanpos = xsp;
    if (jj_3R_30()) return true;
    }
    }
    return false;
  }

  static private boolean jj_3R_44() {
    if (jj_scan_token(CONCAT)) return true;
    if (jj_3R_26()) return true;
    return false;
  }

  static private boolean jj_3R_9() {
    if (jj_scan_token(IF)) return true;
    if (jj_3R_13()) return true;
    if (jj_scan_token(THEN)) return true;
    if (jj_3R_13()) return true;
    if (jj_scan_token(ELSE)) return true;
    if (jj_3R_13()) return true;
    return false;
  }

  static private boolean jj_3R_43() {
    if (jj_scan_token(EQ)) return true;
    if (jj_3R_26()) return true;
    return false;
  }

  static private boolean jj_3R_48() {
    if (jj_3R_10()) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public Func1ParserTokenManager token_source;
  static JavaCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[9];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x6000,0x4406000,0x4c406000,0x8000000,0x210000,0x0,0x0,0x600,0x600,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x20,0x20,0x704000,0x704000,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[9];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Func1Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Func1Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new Func1ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Func1Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new Func1ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Func1Parser(Func1ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(Func1ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List jj_expentries = new java.util.ArrayList();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[61];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 9; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 61; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 9; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
            case 8: jj_3_9(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}