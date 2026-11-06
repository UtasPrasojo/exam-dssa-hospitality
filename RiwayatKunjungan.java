public class RiwayatKunjungan {
    private static class Node {
        String catatan;
        Node prev;
        Node next;

        Node(String catatan) {
            this.catatan = catatan;
        }
    }

    private Node head; // kunjungan paling awal
    private Node tail; // kunjungan paling akhir
    private Node saatIni; // kunjungan yang sedang dilihat
    private int size;

    // tambahKunjungan
    public void tambahKunjungan(String catatan) {
        Node baru = new Node(catatan);
        if (head == null) {
            head = baru;
            tail = baru;
        } else {
            tail.next = baru;
            baru.prev = tail;
            tail = baru;
        }
        size++;
        saatIni = baru;
    }

    public void sebelumnya() {
        if (saatIni == null) {
            System.out.println("[!] Riwayat kosong.");
        } else if (saatIni.prev == null) {
            System.out.println("[!] Sudah di kunjungan paling awal.");
        } else {
            saatIni = saatIni.prev;
        }
    }

    public void berikutnya() {
        if (saatIni == null) {
            System.out.println("[!] Riwayat kosong.");
        } else if (saatIni.next == null) {
            System.out.println("[!] Sudah di kunjungan paling akhir.");
        } else {
            saatIni = saatIni.next;
        }
    }

    public void tampilkanSaatIni() {
        if (saatIni == null) {
            System.out.println("Kunjungan saat ini : (kosong)");
            return;
        }
        int posisi = 1;
        for (Node n = head; n != saatIni; n = n.next)
            posisi++;
        System.out.println("Kunjungan saat ini : [" + posisi + "/" + size + "] " + saatIni.catatan);
    }

    // menghapus node yang sedang ditunjuk
    public void hapusSaatIni() {
        if (saatIni == null) {
            System.out.println("[!] Tidak ada kunjungan untuk dihapus.");
            return;
        }
        Node target = saatIni;

        if (target.prev != null)
            target.prev.next = target.next;
        else
            head = target.next;

        if (target.next != null)
            target.next.prev = target.prev;
        else
            tail = target.prev;

        saatIni = (target.next != null) ? target.next : target.prev;
        target.prev = null;
        target.next = null;
        size--;
    }

    // tampilkan seluruh riwayat dari awal ke akhir
    public void tampilkanSemua() {
        System.out.println("Seluruh riwayat:");
        if (head == null) {
            System.out.println("    (kosong)");
            return;
        }
        int i = 1;
        for (Node n = head; n != null; n = n.next, i++) {
            System.out.println((n == saatIni ? " -> " : "    ") + i + ". " + n.catatan);
        }
    }

    public static void main(String[] args) {
        RiwayatKunjungan r = new RiwayatKunjungan();

        System.out.println("=== Langkah 1: Tambah 4 kunjungan ===");
        r.tambahKunjungan("Kunjungan 1 - Demam dan batuk");
        r.tampilkanSaatIni();
        r.tambahKunjungan("Kunjungan 2 - Kontrol tekanan darah");
        r.tampilkanSaatIni();
        r.tambahKunjungan("Kunjungan 3 - Cek laboratorium");
        r.tampilkanSaatIni();
        r.tambahKunjungan("Kunjungan 4 - Konsultasi hasil lab");
        r.tampilkanSaatIni();

        System.out.println("\n=== Langkah 2: Navigasi mundur 2 kali ===");
        r.sebelumnya();
        System.out.print("Mundur ke-1 -> ");
        r.tampilkanSaatIni();
        r.sebelumnya();
        System.out.print("Mundur ke-2 -> ");
        r.tampilkanSaatIni();

        System.out.println("\n=== Langkah 3: Navigasi maju 1 kali ===");
        r.berikutnya();
        System.out.print("Maju ke-1   -> ");
        r.tampilkanSaatIni();

        System.out.println("\n=== Bonus (soal 3c): Hapus kunjungan salah input ===");
        r.tampilkanSemua();
        System.out.println("Menghapus kunjungan saat ini...");
        r.hapusSaatIni();
        r.tampilkanSemua();
        r.tampilkanSaatIni();
        r.sebelumnya();
        System.out.print("Mundur      -> ");
        r.tampilkanSaatIni();
        r.berikutnya();
        System.out.print("Maju        -> ");
        r.tampilkanSaatIni();
    }
}