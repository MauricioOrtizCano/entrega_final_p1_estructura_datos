public class Main {
    public static void main(String[] args) {
        // Árbol de referencia: 50(30(20, 40(35, ·)), 70(·, 80))
        ArbolBinario arbol = new ArbolBinario();
        int[] valores = {50, 30, 20, 40, 35, 70, 80};
        for (int v : valores) {
            arbol.insertar(v);
        }

        System.out.println("=== Árbol de referencia ===");
        // Valores que están en el árbol
        System.out.println(arbol.buscarSucesor(20));
        System.out.println(arbol.buscarSucesor(30));
        System.out.println(arbol.buscarSucesor(35));
        System.out.println(arbol.buscarSucesor(40));
        System.out.println(arbol.buscarSucesor(50));
        System.out.println(arbol.buscarSucesor(70));
        System.out.println(arbol.buscarSucesor(80));

        // Valores que NO están en el árbol
        System.out.println(arbol.buscarSucesor(45));
        System.out.println(arbol.buscarSucesor(10));
        System.out.println(arbol.buscarSucesor(99));

        System.out.println();
        System.out.println("=== Árbol vacío ===");
        ArbolBinario vacio = new ArbolBinario();
        System.out.println(vacio.buscarSucesor(5));

        System.out.println();
        System.out.println("=== Árbol degenerado (1, 2, 3, 4, 5) ===");
        ArbolBinario lista = new ArbolBinario();
        for (int v = 1; v <= 5; v++) {
            lista.insertar(v);
        }
        System.out.println(lista.buscarSucesor(1));
        System.out.println(lista.buscarSucesor(4));
        System.out.println(lista.buscarSucesor(5));
    }
}