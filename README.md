# Punto 1: Sucesor de un valor en un árbol binario ordenado

Entrega final, Semana 7. Módulo: Estructuras de Datos.

**Integrantes:** _(completar con los nombres del grupo)_

## Enunciado

Implementar un método que, dado un árbol binario ordenado y un valor, retorne el sucesor del valor en el árbol, y calcular la complejidad temporal del método.

El **sucesor** de un valor `x` es el menor valor del árbol que es estrictamente mayor que `x`. Es el siguiente elemento de `x` en el recorrido inorden. El valor `x` puede estar o no en el árbol. Si no existe ningún valor mayor, el método informa que no hay sucesor.

## Archivos

| Archivo | Contenido |
|---|---|
| `Nodo.java` | Nodo del árbol: `valor`, `izquierda` y `derecha`. |
| `ArbolBinario.java` | Árbol binario ordenado con `insertar` y `buscarSucesor`. |
| `Main.java` | Clase con pruebas que construye el árbol y muestra los resultados. |

## Cómo compilar y ejecutar

Desde la carpeta que contiene los tres archivos:

```bash
javac Nodo.java ArbolBinario.java Main.java
java Main
```

## Diseño

- `Nodo` solo guarda datos. No tiene referencia al padre.
- `ArbolBinario` guarda la raíz y expone dos métodos públicos. Cada uno delega en un método privado recursivo que recibe el nodo actual:
  - `insertar(int valor)`: inserta el valor en su posición. Si el valor ya existe, no hace nada.
  - `buscarSucesor(int valorbuscado)`: retorna un mensaje con el sucesor, o `No se encontró sucesor` si no existe.

## Algoritmo

Como los nodos no tienen referencia al padre, el método **baja desde la raíz** llevando un **candidato** (el último nodo mayor que el valor buscado por el que se pasó). El candidato empieza en `null`.

En cada nodo:

1. **Valor buscado < nodo:** el nodo es mayor que el valor, así que se anota como candidato y se baja a la izquierda.
2. **Valor buscado > nodo:** el nodo no sirve. Se baja a la derecha sin cambiar el candidato.
3. **Valor buscado == nodo:**
   - Si el nodo **tiene hijo derecho**, el sucesor es el menor de ese subárbol, que se encuentra bajando siempre a la izquierda (método auxiliar `minimo`).
   - Si **no tiene hijo derecho**, el sucesor es el candidato anotado.
4. **Se llega a `null`** (el valor no está en el árbol): el sucesor es el candidato anotado, que puede ser `null`.

El candidato viaja como parámetro de la recursión, porque las variables locales no se comparten entre llamadas.

## Ejemplo

Árbol de referencia, con la notación `raíz(izquierdo, derecho)` y `·` para un subárbol vacío:

```
50(30(20, 40(35, ·)), 70(·, 80))
```

Su recorrido inorden es `20, 30, 35, 40, 50, 70, 80`.

| Valor | Sucesor | Cómo se obtiene |
|---|---|---|
| 20 | 30 | Sin hijo derecho. El candidato es 30. |
| 30 | 35 | Con hijo derecho (40). El menor de ese subárbol es 35. |
| 35 | 40 | Sin hijo derecho. El candidato es 40. |
| 40 | 50 | Sin hijo derecho. El candidato es 50. |
| 50 | 70 | Con hijo derecho (70). Su menor es 70. |
| 70 | 80 | Con hijo derecho (80). Su menor es 80. |
| 80 | no hay | Es el máximo. No queda ningún candidato. |
| 45 (no está) | 50 | Se baja hasta un `null` con candidato 50. |
| 99 (no está) | no hay | Se baja hasta un `null` sin candidato. |

## Pruebas incluidas en `Main`

- Todos los valores del árbol de referencia (con y sin hijo derecho).
- Valores que no están en el árbol (`45`, `10`, `99`).
- Árbol vacío.
- Árbol degenerado, insertando `1, 2, 3, 4, 5` en orden.

Salida esperada para el árbol de referencia:

```
El sucesor del valor 20 es 30
El sucesor del valor 30 es 35
El sucesor del valor 35 es 40
El sucesor del valor 40 es 50
El sucesor del valor 50 es 70
El sucesor del valor 70 es 80
No se encontró sucesor
El sucesor del valor 45 es 50
El sucesor del valor 10 es 20
No se encontró sucesor
```

## Complejidad temporal

El método recorre un único camino desde la raíz hacia abajo. Incluso en el caso de igualdad con hijo derecho, `minimo` continúa ese mismo camino por la izquierda. Por eso visita como máximo **h + 1 nodos**, donde `h` es la altura del árbol, y en cada nodo hace trabajo constante.

**Complejidad temporal: O(h).**

| Árbol | Altura | Complejidad |
|---|---|---|
| Degenerado (similar a una lista) | h = n − 1 | O(n) |
| Balanceado (por ejemplo, AVL) | h ≤ 1.44 · log₂ n | O(log n) |

- **Mejor caso:** O(1), cuando el árbol está vacío o el valor está en la raíz y esta no tiene hijo derecho.
- **Espacio adicional:** O(h), por la pila de llamadas de la recursión.