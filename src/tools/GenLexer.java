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

/**
 *
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
import jflex.SilentExit;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenLexer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println( System.getProperty("user.dir"));
        String opciones[] = new String[3];
        opciones[0]="-d";
        opciones[1]="src"
                +File.separator
                +"sumadoraarbol"; //destino para la clase resultante
        opciones[2]="src"
                +File.separator
                +"base"
                +File.separator+
                "digitos.flex"; //cambiar el nombre de la fuente
        try {
            jflex.Main.generate(opciones);
        } catch (SilentExit ex) {
            Logger.getLogger(GenLexer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
