interface Autentikasi {
    void login();
}

interface Penjadwalan {
    void lihatJadwal();
}

interface Peresep {                       // hanya relevan untuk Dokter
    void tulisResep(String pasien, String obat);
}

interface PencatatTandaVital {            // hanya relevan untuk Perawat
    void catatTandaVital(String pasien, int sistol, int diastol, double suhu);
}

abstract class PetugasMedis implements Autentikasi, Penjadwalan {
    protected final String id;
    protected final String nama;

    PetugasMedis(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    @Override
    public void login() {
        System.out.println(nama + " (" + id + ") berhasil login.");
    }

    @Override
    public void lihatJadwal() {
        System.out.println("Menampilkan jadwal jaga milik " + nama + ".");
    }
}

class Dokter extends PetugasMedis implements Peresep {
    Dokter(String id, String nama) {
        super(id, nama);
    }

    @Override
    public void tulisResep(String pasien, String obat) {
        System.out.println("dr. " + nama + " menulis resep untuk " + pasien + ": " + obat);
    }
}

class Perawat extends PetugasMedis implements PencatatTandaVital {
    Perawat(String id, String nama) {
        super(id, nama);
    }

    @Override
    public void catatTandaVital(String pasien, int sistol, int diastol, double suhu) {
        System.out.println("Ns. " + nama + " mencatat tanda vital " + pasien
                + ": TD " + sistol + "/" + diastol + " mmHg, suhu " + suhu + " C");
    }
}


public class PetugasMedisDemo {
    public static void main(String[] args) {
        PetugasMedis[] semua = new PetugasMedis[2]; 
        semua[0] = new Dokter("D-001", "Andi Wijaya");
        semua[1] = new Perawat("P-001", "Siti Rahma");

        System.out.println("=== Perilaku umum (polimorfisme lewat PetugasMedis) ===");
        for (PetugasMedis p : semua) {
            p.login();
            p.lihatJadwal();
        }

        System.out.println("\n=== Perilaku spesifik (lewat interface kecil) ===");
        for (PetugasMedis p : semua) {
            if (p instanceof Peresep) {
                ((Peresep) p).tulisResep("Budi", "Paracetamol 500 mg");
            }
            if (p instanceof PencatatTandaVital) {
                ((PencatatTandaVital) p).catatTandaVital("Budi", 120, 80, 36.8);
            }
        }
    }
}