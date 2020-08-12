package assignments.assignment2;

//Mengimport library yang dibutuhkan.
import java.util.ArrayList;
import java.util.List;

/**
 * Kelas yang berisi data-data mengenai asisten dosen.
 */
public class AsistenDosen {
    //datafield dari kelas AsistenDosen.
    private List<Mahasiswa> mahasiswa = new ArrayList<>();
    private String kode;
    private String nama;

    /**
     * Constructor dari kelas AsistenDosen.
     * @param kode -> memberi masukan pada variabel instance kode.
     * @param nama -> memberi masukan pada variabel instance nama.
     */
    public AsistenDosen(String kode, String nama) {
        this.kode = kode;
        this.nama = nama;
    }

    /**
     * Getter.
     * @return kode asisten dosen.
     */
    public String getKode() {
        return this.kode;
    }

    /**
     * Menambahkan objek mahasiswa baru ke dalam list mahasiswa si asdos (dengan memperhatikan urutan npm).
     * @param mahasiswa -> objek yang akan dimasukkan ke dalam list mahasiswa si asdos.
     */
    public void addMahasiswa(Mahasiswa mahasiswa) {
        int index = 0;
        for (Mahasiswa mahasiswa1 : this.mahasiswa){
            int compare = mahasiswa.compareTo(mahasiswa1);
            if (compare < 0) {
                break;
            }
            index += 1;
        }
        this.mahasiswa.add(index, mahasiswa);
    }

    /**
     * Menghapus objek mahasiswa yang dimaksud dari list mahasiswa asdos.
     * @param mahasiswa -> objek yang akan dihapus dari list mahasiswa asdos.
     */
    public void removeMahasiswa(Mahasiswa mahasiswa) {
        for (Mahasiswa mahasiswa1 : this.mahasiswa){
            if (mahasiswa1 == mahasiswa)
                this.mahasiswa.remove(mahasiswa);
            break;
        }
    }

    /**
     * Method yang mengembalikan objek mahasiswa berdasarkan npm yang diminta.
     * @param npm -> identitas objek mahasiswa yang dibutuhkan.
     * @return objek mahasiswa dengan npm yang diminta.
     */
    public Mahasiswa getMahasiswa(String npm) {
        Mahasiswa mahasiswa = null;
        for (Mahasiswa mahasiswaTemp : this.mahasiswa){
            if (mahasiswaTemp.getNpm().equalsIgnoreCase(npm))
                mahasiswa = mahasiswaTemp;
        }
        return mahasiswa;
    }

    /**
     * Merekap penilaian dari setiap mahasiswa yang dipegang si asdos.
     * @return rekapan nilai dari setiap mahasiswa yang dipegang si asdos.
     */
    public String rekap() {
        String rekap = "";
        for (Mahasiswa mahasiswa : this.mahasiswa){
            rekap += mahasiswa.rekap();
        }
        return rekap;
    }

    /**
     * Mencetak data-data asdos.
     * @return kode asdos dengan nama asdos.
     */
    public String toString() {
        return this.kode + " - " + this.nama;
    }
}
