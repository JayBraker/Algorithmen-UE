import java.util.HashMap;
import java.util.Arrays;

/**
 * Aufgabenstellung:
 * 
 * Schreiben Sie eine Funktion public static int[] getTwoSum(int[] arr, int sum) Die Funktion soll
 * die Indizes von zwei Zahlen aus dem Feld arr zurückgeben, die addiert sum ergeben. Die Indizes
 * dürfen nicht gleich sein. Finden sich zwei solche Zahlen nicht, wird null zurückgegeben. Der
 * Algorithmus soll eine möglichst kleine O-Klasse besitzen. O(n2) ist auf keinen Fall ausreichend.
 */
public class StaticMethods {
    public static int[] getTwoSums(int[] arr, int sum) {
        HashMap<Integer, Integer> diff = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!diff.containsKey(sum - arr[i])) {
                diff.put(arr[i], i);
            } else {
                return new int[] {diff.get(sum - arr[i]), i};
            }
        }
        throw new IllegalArgumentException(
                String.format("Keine zwei Elemente des Arrays lassen sich zu %d summieren!", sum));
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 5};
        int[] sums = new int[] {0, 10, 9, 13, 4, 6};
        System.out.println(Arrays.toString(arr));
        for (Integer i : sums)
            try {
                System.out.printf(
                        "Die Summe %d wird aus den Elementen mit den Indizes %s gebildet.\n", i,
                        Arrays.toString(StaticMethods.getTwoSums(arr, i)));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
    }
}
