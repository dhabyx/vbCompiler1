/*
 * Copyright (C) 2014 Dhaby Xiloj <dhabyx@gmail.com>
 *
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

import java.util.Iterator;

/**
 *
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public class Arbol {
    private Nodo raiz;

    public Arbol() {
        this.raiz = new Nodo();
    }
    
    public Arbol(Nodo nodo) {
        this.raiz = nodo;
    }
    
    public void setRaiz(Nodo nodo) {
        this.raiz = nodo;
    }
    
    public void agregarHijo(Nodo nodo) {
        this.raiz.agregarHijo(nodo);
    }
    
    public Nodo getRaiz() {
        return raiz;
    }
    
    public void recorrerArbol() {
        if (raiz.getHijos().size() > 0) {
            recorrido(raiz);
        }
    }
    
    private void recorrido(Nodo nodo) {
        if (nodo.getHijos().isEmpty()) {
            System.out.println("Valor :" + nodo.getValor());
            return;
        }
        for (Nodo hijo : nodo.getHijos()) {
            recorrido(hijo);
        }
        switch(nodo.getTipo()) {
            case Nodo.TIPO_OPERADOR:
                System.out.print("Operador :");
                if (nodo.getOperador() == Nodo.OP_SUMA)
                    System.out.println("Suma");
                else
                    System.out.println("Resta");
                break;
            case Nodo.TIPO_EXPRESION:
                System.out.println("Nodo expresión");
                break;
        }
    }
}
