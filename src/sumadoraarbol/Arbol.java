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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public class Arbol {
    private Nodo raiz;

    private String graphvizPath;

    /**
     * Recorrdido simple, con despliegue a consola
     */
    public static final int TIPO_RECORRIDO_SIMPLE = 1;

    /**
     * Recorrido que convierte a imagen la estructura del arbol
     */
    public static final int TIPO_RECORRIDO_GRAFO = 2;

    /**
     * constructor básico con un nodo vacío
     */
    public Arbol() {
        this.raiz = new Nodo();
    }

    /**
     *
     * @param nodo nodo para inicializar el arbol
     */
    public Arbol(Nodo nodo) {
        this.raiz = nodo;
    }

    /**
     *
     * @param nodo establece el nodo raiz, luego de ser instanciada la clase
     */
    public void setRaiz(Nodo nodo) {
        this.raiz = nodo;
    }

    /**
     *
     * @param nodo agrega un nodo hijo a la raiz
     */
    public void agregarHijo(Nodo nodo) {
        this.raiz.agregarHijo(nodo);
    }

    /**
     *
     * @return Nodo retorna el nodo raiz
     */
    public Nodo getRaiz() {
        return raiz;
    }

    /**
     *
     * @param path dirección absoluta o de sistema hacia el comando dot de
     *      graphviz.
     */
    public void setGraphvizPath(String path) {
        graphvizPath = path;
    }

    /**
     * recorrido In-Orden
     */
    public void recorrerArbol() {
        if (raiz.getHijos().size() > 0) {
            recorrido(raiz);
        }
    }

    private void recorrido(Nodo nodo) {
        if (nodo.getHijos().isEmpty()) {
            switch (nodo.getTipo()) {
                case Nodo.TIPO_NUMERO:
                    System.out.println("Valor :" + nodo.getValor());
                    break;
                case Nodo.TIPO_IDENTIFICADOR:
                    System.out.println("Id :" + nodo.getIdentificador());
                    break;
                case Nodo.TIPO_OPERADOR:
                    imprimirOperador(nodo);
            }

            return;
        }
        for (Nodo hijo : nodo.getHijos()) {
            recorrido(hijo);
        }
        switch(nodo.getTipo()) {
            case Nodo.TIPO_OPERADOR:
                imprimirOperador(nodo);
                break;
            case Nodo.TIPO_EXPRESION:
                System.out.println("Nodo expresión");
                break;
        }
    }

    private void imprimirOperador(Nodo nodo) {
        System.out.print("Operador :");
        switch (nodo.getOperador()) {
            case Nodo.OP_SUMA:
                System.out.println("Suma");
                break;
            case Nodo.OP_RESTA:
                System.out.println("Resta");
                break;
            case Nodo.OP_IGUAL:
                System.out.println("Igual");
                break;
        }
    }

    /**
     *
     * @param tipoRecorrido Elige el tipo de recorrido que se hará
     */
    public void recorrerArbol(int tipoRecorrido) {
        if (tipoRecorrido == TIPO_RECORRIDO_GRAFO) {
            if (raiz.getHijos().size() > 0) {
                crearGrafo(raiz);
            }
        } else if (tipoRecorrido == TIPO_RECORRIDO_SIMPLE) {
            recorrerArbol();
        }
    }

    /*
    * contador Utilzado para controlar el número de nodos que se van
    * utilizando en el arbol y crear un identificador único para cada uno.
    */
    private int contador;

    private void crearGrafo(Nodo raiz) {
        String encabezado = "digraph arbol{";
        contador=0;
        try {
            File file = new File("arbol.gv");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(encabezado);
                bw.newLine();
                for (Nodo hijo : raiz.getHijos()) {
                    recorridoGrafo(hijo, bw, 0, "raiz");
                }
                bw.newLine();
                bw.write("}");
                bw.close();
                crearGraficoPNG(file);
            }

        } catch (IOException ex) {
            Logger.getLogger(Arbol.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void recorridoGrafo(Nodo nodo, BufferedWriter bw, int idPadre,
            String padreNombre) throws IOException {

        if (nodo.getTipo()>0){
            contador++;
            //System.out.println("Hijo no.:"+contador+" Padre:"+idPadre);

            switch(nodo.getTipo()) {
                case Nodo.TIPO_EXPRESION:
                    bw.write(crearContenido(idPadre,padreNombre,contador,"expr"));
                    bw.newLine();
                    break;
                case Nodo.TIPO_OPERADOR:
                    bw.write(crearContenido(idPadre,padreNombre,contador,
                            nodo.getOperadorString()));
                    bw.newLine();
                    break;
                case Nodo.TIPO_NUMERO:
                    bw.write(crearContenido(idPadre,padreNombre,contador,
                            String.valueOf(nodo.getValor())));
                    //System.out.println("  valor:"+nodo.getValor());
                    bw.newLine();
                    break;
                case Nodo.TIPO_IDENTIFICADOR:
                    bw.write(crearContenido(idPadre,padreNombre,contador,
                            nodo.getIdentificador()));
                    break;
            }
            if (!nodo.getHijos().isEmpty()) {
                idPadre=contador;
                for (Nodo hijo : nodo.getHijos()) {
                    recorridoGrafo(hijo, bw, idPadre, null);
                    //System.out.println("  Hijo no.:"+contador+" Padre:"+idPadre);
                }
            }
        }
    }

    private void crearGraficoPNG(File file) throws IOException {

        String name = file.getAbsolutePath();
        int pos = name.lastIndexOf(".");
        if (pos > 0) {
            name = name.substring(0, pos)+".jpeg";
        }
        String tParam = "-Tpng";
        String tOParam = "-o";

        String[] cmd = new String[5];
        cmd[0] = graphvizPath;
        cmd[1] = tParam;
        cmd[2] = file.getAbsolutePath();
        cmd[3] = tOParam;
        cmd[4] = name;

        Runtime rt = Runtime.getRuntime();

        rt.exec(cmd);
    }

    private String crearContenido(int idPadre, String padreNombre, int idHijo, String nombreHijo) {
        String contenido="{"+idPadre;
        if (padreNombre != null)
            contenido+=" [label=\""+padreNombre+"\"]";
        contenido+="}->{"+idHijo+" [label=\""+nombreHijo+"\"]}";
        return contenido;
    }

}
