public class DynamicArrayDemo {

    //dynamic array sederhana untuk menyimpan catatan kunjungan
    static class DaftarKunjungan {
        private String[] data = new String[1];   // kapasitas awal 1
        private int size = 0;
        long totalSalinan = 0;                   // berapa kali elemen disalin saat resize
        int jumlahResize = 0;

        void tambah(String catatan) {
            if (size == data.length) {                   
                String[] baru = new String[data.length * 2];
                for (int i = 0; i < size; i++) {         
                    baru[i] = data[i];
                    totalSalinan++;
                }
                data = baru;
                jumlahResize++;
            }
            data[size] = catatan;                 
            size++;
        }

        String ambil(int i) {               
            if (i < 0 || i >= size) throw new IndexOutOfBoundsException("indeks " + i);
            return data[i];
        }

        int ukuran() { return size; }
        int kapasitas() { return data.length; }
    }

    static double bulatkan(double x) {
        return Math.round(x * 100) / 100.0;
    }

    public static void main(String[] args) {
        System.out.println("=== A. Array kapasitas tetap (5 slot): kapasitas habis ===");
        String[] tetap = new String[5];
        int terisi = 0;
        try {
            for (int i = 1; i <= 6; i++) {
                tetap[terisi] = "kunjungan " + i;
                terisi++;
                System.out.println("tambah kunjungan " + i + " -> OK");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("tambah kunjungan 6 -> GAGAL: array penuh (" + e.getClass().getSimpleName() + ")");
        }

        System.out.println();
        System.out.println("=== B. Dynamic array buatan sendiri: biaya n kali tambah ===");
        System.out.println("n        kapasitas  resize  total_salin  salin/n  (n+salin)/n");
        int[] uji = {10, 100, 1000, 100000};
        for (int n : uji) {
            DaftarKunjungan d = new DaftarKunjungan();
            for (int i = 0; i < n; i++) {
                d.tambah("kunjungan");
            }
            double salinPerN = (double) d.totalSalinan / n;
            double biayaPerOp = (double) (n + d.totalSalinan) / n;
            System.out.println(pad(n, 9) + pad(d.kapasitas(), 11) + pad(d.jumlahResize, 8)
                    + pad(d.totalSalinan, 13) + pad(bulatkan(salinPerN), 9) + bulatkan(biayaPerOp));
        }
        System.out.println();
        System.out.println("Kesimpulan: total salinan < 2n, sehingga biaya rata-rata per tambah < 3 = O(1) amortized.");
    }

    static String pad(Object o, int lebar) {
        String s = String.valueOf(o);
        while (s.length() < lebar) s = s + " ";
        return s;
    }
}