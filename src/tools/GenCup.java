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
package tools;

import java.io.File;

/**
 *
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public class GenCup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String opciones[] = new String[5];
        opciones[0]="-destdir";
        opciones[1]="src"
                +File.separator+
                "sumadoraarbol"; //cambiar destino
        opciones[2]="-parser";
        opciones[3]="AnalizadorSintactico"; //cambiar el nombre de la clase
        opciones[4]="src"
                +File.separator+
                "base"
                +File.separator+
                "calculadora.cup"; //cambiar el nombre de la fuente
        try {     
            java_cup.Main.main(opciones);
            } 
        catch (Exception e) {
            System.out.print(e);
            }

        
    }
}
