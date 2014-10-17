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

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para almacenar el órden de los nodos
 * 
 * Creada para propósitos educativos, con el fin de entender cómo se guardaría
 * un nodo de información de un árbol sintáctico, pero sin optimizar el código 
 * para que el árbol consuma menos recursos.
 *
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public class Nodo {
    private int tipo;
    private int operador;
    private int valor;
    private final ArrayList<Nodo> hijos;

    public static final int TIPO_EXPRESION=1;
    public static final int TIPO_NUMERO=2;
    public static final int TIPO_OPERADOR=3;
    
    public static final int OP_SUMA = 10;
    public static final int OP_RESTA = 11;
    /**
     *
     * @param tipo de nodo a crear
     * @param valor del nodo a crear
     */
    public Nodo(int tipo, int valor) {
        this.hijos = new ArrayList<>();  
        this.tipo = tipo;
        switch (this.tipo) {
            case Nodo.TIPO_EXPRESION:
                break;
            case Nodo.TIPO_NUMERO:
                this.valor = valor;
                break;
            case Nodo.TIPO_OPERADOR:
                this.operador = valor;
        }
    }
    
    public Nodo(int valor) {
        this.hijos = new ArrayList<>();
        this.tipo=TIPO_NUMERO;
        this.valor=valor;
        this.operador=0;
    }
    
    public Nodo() {
        this.hijos = new ArrayList<>();
        this.tipo=0;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the operador
     */
    public int getOperador() {
        return operador;
    }

    public String getOperadorString() {
        if (operador == OP_SUMA)
            return "+";
        else
            return "-";
    }
    
    /**
     * @param operador the operador to set
     */
    public void setOperador(int operador) {
        this.operador = operador;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the hijos
     */
    public List<Nodo> getHijos() {
        return hijos;
    }
    
    public boolean agregarHijo(Nodo nodo){
        return hijos.add(nodo);
    }
}
