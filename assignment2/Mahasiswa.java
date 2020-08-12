package assignments.assignment2;

/**
 * Kelas yang berisi data-data mengenai mahasiswa.
 */
public class Mahasiswa implements Comparable<Mahasiswa> {
    //datafield dari kelas Mahasiswa.
    private String npm;
    private String nama;
    private KomponenPenilaian[] komponenPenilaian;

    /**
     * Constructor dari kelas Mahasiswa.
     * @param npm -> memberi masukan pada variabel instance npm.
     * @param nama -> memberi masukan pada variabel instance nama.
     * @param komponenPenilaian -> memberi masukan pada variabel instance komponenPenilaian.
     */
    public Mahasiswa(String npm, String nama, KomponenPenilaian[] komponenPenilaian) {
        this.npm = npm;
        this.nama = nama;
        this.komponenPenilaian = komponenPenilaian;
    }

    /**
     * Mendapatkan komponen penilaian berdasarkan nama komponen.
     * @param namaKomponen -> identitas si komponen yang dicari.
     * @return objek KomponenPenilaian yang sesuai dengan identitas namanya.
     */
    public KomponenPenilaian getKomponenPenilaian(String namaKomponen) {
        KomponenPenilaian komponen = null;
        for (KomponenPenilaian komponenTemp: this.komponenPenilaian){
            if (komponenTemp.getNama().equalsIgnoreCase(namaKomponen))
                komponen = komponenTemp;
        }
        return komponen;
    }

    /**
     * Mendapatkan npm dari mahasiswa
     * @return npm dari mahasiswa.
     */
    public String getNpm() {
        return this.npm;
    }

    /**
     * Mengembalikan huruf berdasarkan nilai yang diberikan.
     * @param nilaiAkhir nilai untuk dicari hurufnya.
     * @return huruf dari nilai.
     */
    private static String getHuruf(double nilai) {
        return nilai >= 85 ? "A" :
                nilai >= 80 ? "A-" :
                        nilai >= 75 ? "B+" :
                                nilai >= 70 ? "B" :
                                        nilai >= 65 ? "B-" :
                                                nilai >= 60 ? "C+" :
                                                        nilai >= 55 ? "C" :
                                                                nilai >= 40 ? "D" : "E";
    }

    /**
     * Mengembalikan status kelulusan berdasarkan nilaiAkhir yang diberikan.
     * @param nilaiAkhir nilai akhir mahasiswa.
     * @return status kelulusan (LULUS/TIDAK LULUS).
     */
    private static String getKelulusan(double nilaiAkhir) {
        return nilaiAkhir >= 55 ? "LULUS" : "TIDAK LULUS";
    }

    /**
     * Merekap nilai dari si mahasiswa.
     * @return rekapan dari nilai si mahasiswa.
     */
    public String rekap() {
        String rekapMahasiswa = "";
        double nilaiAkhir = 0.0;
        rekapMahasiswa += String.format("%s%n", this.toString());

        //Mendapatkan rerata dari masing-masing komponen penilaian sekaligus menghitung nilai akhir.
        for (KomponenPenilaian komponen : this.komponenPenilaian){
            rekapMahasiswa += komponen.toString() + "\n";
            nilaiAkhir += komponen.getNilai();
        }

        ////Menambahkan nilai akhir besar huruf dan keterangan kelulusan.
        rekapMahasiswa += String.format("Nilai akhir: %.2f%n", nilaiAkhir).replace(",", ".");
        rekapMahasiswa += String.format("Huruf: %s%n", getHuruf(nilaiAkhir));
        rekapMahasiswa += String.format("%s%n%n", getKelulusan(nilaiAkhir));

        return rekapMahasiswa;
    }

    /**
     * Mencetak npm dan nama dari mahasiswa.
     * @return npm mahasiswa dengan namanya.
     */
    public String toString() {
        return this.npm + " - " + this.nama;
    }

    /**
     * Mendapatkan detail nilai dari masing-masing komponen penilaian si mahasiswa.
     * @return hasil detail dari nilai masing-masing komponen setiap mahasiswa.
     */
    public String getDetail() {
        String detail = "";
        double nilaiAkhir = 0.0;

        //Menambahkan detail dari setiap komponen penilaian si mahasiswa.
        //Sekaligus dengan nilai akhir si mahasiswa.
        for (KomponenPenilaian komponen : this.komponenPenilaian){
            detail += komponen.getDetail();
            nilaiAkhir += komponen.getNilai();
        }

        //Menambahkan nilai akhir besar huruf dan keterangan kelulusan.
        detail += String.format("Nilai akhir: %.2f%n", nilaiAkhir).replace(",", ".");
        detail += String.format("Huruf: %s%n", getHuruf(nilaiAkhir));
        detail += String.format("%s", getKelulusan(nilaiAkhir));

        return detail;
    }

    /**
     * Membandingkan npm mahasiswa 1 dengan mahasiswa 2
     * @param other -> objek pembanding mahasiswa 1
     * @return positif jika npm mahasiswa 1 di atas npm mahasiswa 2
     *         negatif jika npm mahasiswa 1 di bawah npm mahasiswa 2
     */
    @Override
    public int compareTo(Mahasiswa other) {
        return this.npm.compareTo(other.npm);
    }
}
