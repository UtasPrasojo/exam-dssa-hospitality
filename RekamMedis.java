enum Peran { DOKTER, PERAWAT, ADMIN }

public final class RekamMedis {
    private final String idKunjungan;
    private final String diagnosis;    
    private final String resep;       
    private final String tandaVital;

    public RekamMedis(String idKunjungan, String diagnosis, String resep, String tandaVital) {
        if (idKunjungan == null || idKunjungan.trim().isEmpty()) {
            throw new IllegalArgumentException("ID kunjungan wajib diisi");
        }
        this.idKunjungan = idKunjungan;
        this.diagnosis = diagnosis;
        this.resep = resep;
        this.tandaVital = tandaVital;
    }

    //Data non-sensitif
    public String lihatRingkasan() {
        return "Kunjungan " + idKunjungan;
    }

    //Least privilege
    public String lihatDiagnosis(Peran peminta) {
        if (peminta != Peran.DOKTER) {
            throw new SecurityException("Akses diagnosis ditolak untuk peran " + peminta);
        }
        return diagnosis;
    }

    //dokter dan Perawat boleh melihat resep (perawat perlu untuk memberikan obat); Admin tidak
    public String lihatResep(Peran peminta) {
        if (peminta != Peran.DOKTER && peminta != Peran.PERAWAT) {
            throw new SecurityException("Akses resep ditolak untuk peran " + peminta);
        }
        return resep;
    }

    //dokter dan Perawat boleh melihat tanda vital
    public String lihatTandaVital(Peran peminta) {
        if (peminta != Peran.DOKTER && peminta != Peran.PERAWAT) {
            throw new SecurityException("Akses tanda vital ditolak untuk peran " + peminta);
        }
        return tandaVital;
    }
}