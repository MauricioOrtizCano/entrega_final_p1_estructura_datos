public class ArbolBinario {
    Nodo raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public Nodo insertar(int valor) {
        raiz = insertarRecursivamente(raiz, valor);
        return raiz;
    }

    private Nodo insertarRecursivamente(Nodo nodoActual, int valor) {
        if (nodoActual == null) {
            return new Nodo(valor);
        }

        if (valor < nodoActual.valor) {
            nodoActual.izquierda = insertarRecursivamente(nodoActual.izquierda, valor);
        } else if (valor > nodoActual.valor) {
            nodoActual.derecha = insertarRecursivamente(nodoActual.derecha, valor);
        }

        return nodoActual;
    }

    /**
     * El método buscarSucesor desciende desde la raíz por un único camino, comparando el valor buscado con cada nodo y yendo a la izquierda o a la derecha, 
     * y en el caso de que haya  igualdad con hijo derecho continúa por el hijo izquierdo hasta el final. En cada nodo realiza trabajo constante, y el número de nodos 
     * visitados es como máximo h + 1, donde h es la altura del árbol. Por lo tanto, la complejidad temporal es O(h). En un árbol degenerado h = n − 1 y la 
     * complejidad es O(n).
     * @param valorbuscado
     * @return
     */
    public String buscarSucesor(int valorbuscado) {
        Nodo nodo = buscarSucesorRecursivamente(this.raiz, valorbuscado, null);

        if (nodo == null) return "No se encontró sucesor";

        return "El sucesor del valor " + valorbuscado + " es " + nodo.valor;
    }


    /**
     * Buscamos el menor de los valores mayores del valor buscado
     * @param nodoActual
     * @param valorbuscado
     * @param candidato
     * @return
     */
    private Nodo buscarSucesorRecursivamente(Nodo nodoActual, int valorbuscado, Nodo candidato) {
        // Llegamos a un hueco vacío donde el sucesor es el último candidato anotado que puede ser null
        if (nodoActual == null) {
            return candidato;
        }

        // Este nodo es mayor que el valor buscado y por ende lo anotamos como candidato y bajamos a la izquierda
        if (valorbuscado < nodoActual.valor) {
            return buscarSucesorRecursivamente(nodoActual.izquierda, valorbuscado, nodoActual);
        }

        // Este nodo es menor por ende  no sirve, y debemos bajar a la derecha sin tocar el candidato
        if (valorbuscado > nodoActual.valor) {
            return buscarSucesorRecursivamente(nodoActual.derecha, valorbuscado, candidato);
        }

        // Encontramos el valor si tiene hijo derecho, el sucesor es el menor de ese subárbol
        if (nodoActual.derecha != null) {
            return minimo(nodoActual.derecha);
        }

        // Si no tiene hijo derecho, el sucesor es el candidato anotado
        return candidato;
    }

    private Nodo minimo(Nodo nodo) {
        while (nodo.izquierda != null) {
            nodo = nodo.izquierda;
        }
        return nodo;
    }
}