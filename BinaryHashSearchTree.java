import java.util.HashMap;

/**
 * Aufgabenstellung
 * 
 * Heaps sind gut für eine Feldeinbettung geeignet, weil sie linksvollständig sind. Für
 * binäre Suchbäume (und allgemein für Binärbäume) allgemein entstehen bei der Feldeinbettung
 * Lücken. Dem kann man aber abhelfen, in dem man für die Einbettung statt des Feldes eine
 * Hashtabelle nimmt. Der Schlüssel der Hashtabelle entspricht dem Index des Feldes. Schreiben Sie
 * eine Klasse BinaryHashSearchTree, die einen binären Suchbaum implementiert. Intern wird der
 * binäre Suchbaum durch eine „Feld“-Einbettung in einer Hashtabelle realisiert. Implementieren Sie
 * die unten angegebenen Methoden.
 * 
 *  public class BinaryHashSearchTree<T extends Comparable> { 
 *      private HashMap<Integer, T> hash = new HashMap<>();
 * 
 *      // Fuegt element zum Binaerbaum hinzu. Falls element
 *      // schon vorhanden ist, passiert nichts
 *      public void add(T element) { .. }
 *      
 *      // Gibt true zurueck, falls element im Binaerbaum vorhanden ist,
 *      // sonst false.
 *      public boolean contains(T element) { .. }
 *  }
 */
public class BinaryHashSearchTree<T extends Comparable<? super T>> {
    private HashMap<Integer, T> hash = new HashMap<>();

    public void add(T element) {
        if (contains(element))
            return;
        add(element, 0);
    }

    public void add(T element, int index) {
        if (contains(element))
            return;
        T node = hash.get(index);

        int comp = node.compareTo(element);

        if (node == null) {
            hash.put(index, element);
            return;
        } else if (comp > 0) {
            add(element, 2 * index + 1);
        } else if (comp < 0) {
            add(element, 2 * index + 2);
        }
    }

    public boolean contains(T element) {
        if (hash.size() > 0)
            return contains(element, 0);
        return false;
    }

    public boolean contains(T element, int index) {
        if (hash.size() > index) {
            T root = hash.get(index);

            int comp = root.compareTo(element);

            if (root != null) {
                if (comp == 0)
                    return true;

                else if (comp > 0)
                    return contains(element, 2 * index + 1);
                else if (comp < 0)
                    return contains(element, 2 * index + 2);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BinaryHashSearchTree<String> names = new BinaryHashSearchTree<>();
        names.add("Josha");
        names.add("Tim");
        names.add("Florian");
        names.add("Tim");
        names.add("Finn");
        names.add("Falka");
        System.out.println(names.contains("Josha"));
        System.out.println(names.contains("Müller"));
    }
}
