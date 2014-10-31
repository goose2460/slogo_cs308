/* Generated By:JavaCC: Do not edit this line. LogoParser.java */
package parser;
import commands.*;
import java.io.InputStream;

public class LogoParser implements LogoParserConstants {
  public ICommand parse(CommandsFactory factory) throws ParseException
  {
    return this.list(factory);
  }

/* entire line */
  final public ICommand expression(CommandsFactory factory) throws ParseException {
  Token t;
  String name;
  ICommand command;
    command = list(factory);
    jj_consume_token(EOL);
    {if (true) return command;}
    throw new Error("Missing return statement in function");
  }

/* block statements */
  final public ICommand list(CommandsFactory factory) throws ParseException {
  ICommand command;
  ICommand altCommand;
  ICommand result;
  ICommand rest = null;
  CommandsQueue commandsQueue;
  double val;
  Token t;
  String name;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case REPEAT:
      jj_consume_token(REPEAT);
      val = eval();
      jj_consume_token(LBRACKET);
      command = list(factory);
      jj_consume_token(RBRACKET);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case REPEAT:
      case IF:
      case IFELSE:
      case TO:
      case FORWARD:
      case BACK:
      case RIGHT:
      case LEFT:
      case CLEAR:
      case PENUP:
      case PENDOWN:
      case HIDE:
      case SHOW:
      case HOME:
      case SETXY:
      case SETHEADING:
      case TOWARDS:
      case SETPENSIZE:
      case STRING:
        rest = list(factory);
        break;
      default:
        jj_la1[0] = jj_gen;
        ;
      }
    result = factory.CommandsRepeat((int) val, command);
    if (rest != null)
    {
      commandsQueue = factory.CommandsQueue();
      commandsQueue.addCommand(result);
      commandsQueue.addCommand(rest);
      {if (true) return commandsQueue;}
    }
    {if (true) return result;}
      break;
    case IFELSE:
      jj_consume_token(IFELSE);
      val = eval();
      jj_consume_token(LBRACKET);
      command = list(factory);
      jj_consume_token(RBRACKET);
      jj_consume_token(LBRACKET);
      altCommand = list(factory);
      jj_consume_token(RBRACKET);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case REPEAT:
      case IF:
      case IFELSE:
      case TO:
      case FORWARD:
      case BACK:
      case RIGHT:
      case LEFT:
      case CLEAR:
      case PENUP:
      case PENDOWN:
      case HIDE:
      case SHOW:
      case HOME:
      case SETXY:
      case SETHEADING:
      case TOWARDS:
      case SETPENSIZE:
      case STRING:
        rest = list(factory);
        break;
      default:
        jj_la1[1] = jj_gen;
        ;
      }
    if (val != 0)
    {
      result = command;
    }
    else
    {
      result = altCommand;
    }
    if (rest != null)
    {
      commandsQueue = factory.CommandsQueue();
      commandsQueue.addCommand(result);
      commandsQueue.addCommand(rest);
      {if (true) return commandsQueue;}
    }
    {if (true) return result;}
      break;
    case IF:
      jj_consume_token(IF);
      val = eval();
      jj_consume_token(LBRACKET);
      command = list(factory);
      jj_consume_token(RBRACKET);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case REPEAT:
      case IF:
      case IFELSE:
      case TO:
      case FORWARD:
      case BACK:
      case RIGHT:
      case LEFT:
      case CLEAR:
      case PENUP:
      case PENDOWN:
      case HIDE:
      case SHOW:
      case HOME:
      case SETXY:
      case SETHEADING:
      case TOWARDS:
      case SETPENSIZE:
      case STRING:
        rest = list(factory);
        break;
      default:
        jj_la1[2] = jj_gen;
        ;
      }
    if (val != 0)
    {
      result = command;
    }
    else
    {
      result = null;
    }
    if (rest != null)
    {
      commandsQueue = factory.CommandsQueue();
      commandsQueue.addCommand(result);
      commandsQueue.addCommand(rest);
      {if (true) return commandsQueue;}
    }
    {if (true) return result;}
      break;
    case FORWARD:
    case BACK:
    case RIGHT:
    case LEFT:
    case CLEAR:
    case PENUP:
    case PENDOWN:
    case HIDE:
    case SHOW:
    case HOME:
    case SETXY:
    case SETHEADING:
    case TOWARDS:
    case SETPENSIZE:
      command = statement(factory);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case REPEAT:
      case IF:
      case IFELSE:
      case TO:
      case FORWARD:
      case BACK:
      case RIGHT:
      case LEFT:
      case CLEAR:
      case PENUP:
      case PENDOWN:
      case HIDE:
      case SHOW:
      case HOME:
      case SETXY:
      case SETHEADING:
      case TOWARDS:
      case SETPENSIZE:
      case STRING:
        rest = list(factory);
        break;
      default:
        jj_la1[3] = jj_gen;
        ;
      }
    if (rest != null)
    {
      commandsQueue = factory.CommandsQueue();
      commandsQueue.addCommand(command);
      commandsQueue.addCommand(rest);
      {if (true) return commandsQueue;}
    }
    {if (true) return command;}
      break;
    case TO:
      jj_consume_token(TO);
      t = jj_consume_token(STRING);
      command = list(factory);
      jj_consume_token(END);
    name = t.toString();
    {if (true) return factory.CommandMake(name, command);}
      break;
    case STRING:
      t = jj_consume_token(STRING);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case REPEAT:
      case IF:
      case IFELSE:
      case TO:
      case FORWARD:
      case BACK:
      case RIGHT:
      case LEFT:
      case CLEAR:
      case PENUP:
      case PENDOWN:
      case HIDE:
      case SHOW:
      case HOME:
      case SETXY:
      case SETHEADING:
      case TOWARDS:
      case SETPENSIZE:
      case STRING:
        rest = list(factory);
        break;
      default:
        jj_la1[4] = jj_gen;
        ;
      }
    name = t.toString();
    result = factory.CommandExecute(name);

    if (rest != null)
    {
      commandsQueue = factory.CommandsQueue();
      commandsQueue.addCommand(result);
      commandsQueue.addCommand(rest);
      {if (true) return commandsQueue;}
    }
    {if (true) return result;}
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

/* instructions */
  final public ICommand statement(CommandsFactory factory) throws ParseException {
  double parameter;
  double parameter2;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FORWARD:
      jj_consume_token(FORWARD);
      parameter = eval();
    {if (true) return factory.turtleGoForward(parameter);}
      break;
    case BACK:
      jj_consume_token(BACK);
      parameter = eval();
    {if (true) return factory.turtleGoBack(parameter);}
      break;
    case RIGHT:
      jj_consume_token(RIGHT);
      parameter = eval();
    {if (true) return factory.rotateTurtleRight(parameter);}
      break;
    case LEFT:
      jj_consume_token(LEFT);
      parameter = eval();
    {if (true) return factory.rotateTurtleLeft(parameter);}
      break;
    case CLEAR:
      jj_consume_token(CLEAR);
    {if (true) return factory.clearScreen();}
      break;
    case PENUP:
      jj_consume_token(PENUP);
    {if (true) return factory.pickUpTurtle();}
      break;
    case PENDOWN:
      jj_consume_token(PENDOWN);
    {if (true) return factory.putTurtle();}
      break;
    case HIDE:
      jj_consume_token(HIDE);
    {if (true) return factory.hideTurtle();}
      break;
    case SHOW:
      jj_consume_token(SHOW);
    {if (true) return factory.showTurtle();}
      break;
    case HOME:
      jj_consume_token(HOME);
    {if (true) return factory.setTurtlePosition(0, 0);}
      break;
    case SETXY:
      jj_consume_token(SETXY);
      parameter = eval();
      parameter2 = eval();
    {if (true) return factory.setTurtlePosition(parameter, parameter2);}
      break;
    case SETHEADING:
      jj_consume_token(SETHEADING);
      parameter = eval();
    {if (true) return factory.setTurtleHeading(parameter);}
      break;
    case TOWARDS:
      jj_consume_token(TOWARDS);
      parameter = eval();
      parameter2 = eval();
    {if (true) return factory.setTurtleTowards(parameter, parameter2);}
      break;
    case SETPENSIZE:
      jj_consume_token(SETPENSIZE);
      parameter = eval();
    {if (true) return factory.setPenSize(parameter);}
      break;      
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

/* evaluated expression - either arithmetical or logical */
  final public double eval() throws ParseException {
  double val;
  double result;
    val = arithm();
    result = val;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case GT:
    case LT:
    case GE:
    case LE:
    case EQ:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case GT:
        jj_consume_token(GT);
        val = arithm();
      result = result > val ? 1.0 : 0.0;
        break;
      case LT:
        jj_consume_token(LT);
        val = arithm();
      result = result < val ? 1.0 : 0.0;
        break;
      case GE:
        jj_consume_token(GE);
        val = arithm();
      result = result >= val ? 1.0 : 0.0;
        break;
      case LE:
        jj_consume_token(LE);
        val = arithm();
      result = result <= val ? 1.0 : 0.0;
        break;
      case EQ:
        jj_consume_token(EQ);
        val = arithm();
      result = result == val ? 1.0 : 0.0;
        break;
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
    {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

/* addition and substraction*/
  final public double arithm() throws ParseException {
  double term;
  double result;
    term = term();
    result = term;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SUM:
      case SUB:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SUM:
        jj_consume_token(SUM);
        term = term();
      result += term;
        break;
      case SUB:
        jj_consume_token(SUB);
        term = term();
      result -= term;
        break;
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

/* division and multiplication */
  final public double term() throws ParseException {
  double pow;
  double result;
    pow = pow();
    result = pow;
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULT:
      case DIV:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MULT:
        jj_consume_token(MULT);
        pow = pow();
      result *= pow;
        break;
      case DIV:
        jj_consume_token(DIV);
        pow = pow();
      result /= pow;
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

/* exponentation */
  final public double pow() throws ParseException {
  Token t;
  double num;
  double result;
    t = jj_consume_token(NUM);
    num = Double.parseDouble(t.toString());
    result = num;
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case POW:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_3;
      }
      jj_consume_token(POW);
      t = jj_consume_token(NUM);
      num = Double.parseDouble(t.toString());
      result = Math.pow(result, num);
    }
    {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public LogoParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[14];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1ffd320,0x1ffd320,0x1ffd320,0x1ffd320,0x1ffd320,0x1ffd320,0x1ffc000,0x0,0x0,0x30000000,0x30000000,0xc0000000,0xc0000000,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x40,0x40,0x40,0x40,0x40,0x40,0x0,0x3e,0x3e,0x0,0x0,0x0,0x0,0x1,};
   }

  /** Constructor with InputStream. */
  public LogoParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public LogoParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new LogoParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public LogoParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new LogoParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public LogoParser(LogoParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(LogoParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[42];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 14; i++) {
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
    for (int i = 0; i < 42; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}