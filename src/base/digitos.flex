/* primera parte: no hace falta poner nada */
package sumadoraarbol;
import java_cup.runtime.Symbol;
%%

/* segunda parte: declaramos las directivas y los macros */
%class AnalizadorLexico
%line
%char
%column
%cup
%eofval{
  System.out.println("Fin de archivo encontrado");
  return new Symbol(sym.EOF);
%eofval}
%eofclose
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]
Decimal = 0 | [1-9][0-9]*
Identificador = [a-zA-Z][a-zA-Z0-9]*
%%

/* tercera parte: declaramos las reglas */
";" {return new Symbol(sym.PCOMA,new token(yycolumn, yyline, yytext()));}
"+" {return new Symbol(sym.SUMA,new token(yycolumn, yyline, yytext()));}
"-" {return new Symbol(sym.RESTA,new token(yycolumn, yyline, yytext()));}
"=" {return new Symbol(sym.IGUAL, new token(yycolumn, yyline, yytext()));}
{Identificador} {return new Symbol(sym.ID, new token(yycolumn, yyline, yytext()));}
{Decimal} {return new Symbol(sym.DECIMAL, new token(yycolumn, yyline, yytext(), true));}
{WhiteSpace} {/* ignorar */}
. {System.err.println("caracter invalido \"" + yytext() + "\" ["+ (yyline+1) + ":"+ (yycolumn+1) + ":"+ yychar + "]");
    return new Symbol(sym.error);}