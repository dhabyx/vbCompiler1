/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sumadoraarbol;
import java_cup.runtime.Symbol;
/**
 * 
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 * 
 */

%%
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
/* Reconicimiento de tabulación simple o formada por 4 espacios.
 * No considera que éstos solo deben ir al inicio de una línea y
 * no importará si encuentra éstos caracteres dentro de cualquier parte
 * de una expresión.
 */
Tabulator = [\t] | [ ]{4}
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \f]
Decimal = 0 | [1-9][0-9]*
%%

/* tercera parte: declaramos las reglas */
";" {return new Symbol(sym.PCOMA,new token(yycolumn, yyline, yytext()));}
"+" {return new Symbol(sym.SUMA,new token(yycolumn, yyline, yytext()));}
"-" {return new Symbol(sym.RESTA,new token(yycolumn, yyline, yytext()));}
{Decimal} {return new Symbol(sym.DECIMAL, new token(yycolumn, yyline, yytext(), true));}
{Tabulator} {return new Symbol(sym.TAB, new token(yycolumn, yyline, yytext(),false, true)); }
{WhiteSpace} {/* ignorar */}
. {System.err.println("caracter invalido" + yytext() + "["+ yyline + ":"+ yycolumn + ":"+ yychar + "]");
    return new Symbol(sym.error);}