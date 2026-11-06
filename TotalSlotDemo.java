public class TotalSlotDemo {

    static long rekursif(int n, int k) {
        if (n == 0) return 0;
        return rekursif(n - 1, k) + k;
    }

    static long iteratif(int n, int k) {
        long total = 0;
        for (int i = 1; i <= n; i++) {
            total += k;
        }
        return total;
    }

    static long closedForm(int n, int k) {
        return (long) n * k;
    }

    public static void main(String[] args) {
        int k = 10; // slot per hari
        System.out.println("k = " + k + " slot/hari");
        System.out.println("n\trekursif\titeratif\tn*k");
        for (int n : new int[] {0, 1, 5, 7, 30}) {
            System.out.println(n + "\t" + rekursif(n, k) + "\t\t" + iteratif(n, k) + "\t\t" + closedForm(n, k));
        }
    }
}