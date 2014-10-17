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

/**
 * 
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 * 
 * Clase para almacenar la estructura de cada token devuelto por el analizador
 * léxico.
 * 
 * Su funcionalidad se limita a la explicación de cómo debe almacenarse la 
 * información de los tokens y nó está optimizado para funcionar de la manera 
 * mas óptima.
 * 
 */
public class token {
    private final int col; // columnas y fila de cada token encontrado
    private final int row;
    private final String cadena; //cadenas de texto devuletas por el analizador léxico
    private int entero; // el valor encontrado.
    
    public token(int col, int row, String cadena, boolean esDigito ) {
        this.col = col;
        this.row = row;
        this.cadena = cadena;
        if (esDigito){
        this.entero = Integer.parseInt(cadena);
        }
    }
    
    public token(int col, int row, String cadena ) {
        this.col = col;
        this.row = row;
        this.cadena = cadena;
    }
    
    public int getCol() {
        return this.col;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public String getCadena() {
        return this.cadena;
    }
    
    public int getEntero() {
        return this.entero;
    }
}
