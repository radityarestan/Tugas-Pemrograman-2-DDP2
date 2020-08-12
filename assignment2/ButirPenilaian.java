package assignments.assignment2;

/**
 * Kelas yang berisi data-data mengenai butir penilaian.
 */
public class ButirPenilaian {
    //datafield dari kelas ButirPenilaian.
    private static final int PENALTI_KETERLAMBATAN = 20;
    private double nilai;
    private boolean terlambat;

    /**
     * Constructor dari kelas ButirPenlaian.
     * @param nilai -> memberi masukan pada variabel instance nilai.
     * @param terlambat -> memberi masukan pada variabel instance terlambat.
     */
    public ButirPenilaian(double nilai, boolean terlambat) {
        this.nilai = nilai;
        this.terlambat = terlambat;
    }

    /**
     * Menghitung nilai yang sudah disesuaikan dengan keterlambatan(jika ada).
     * @return nilai berdasarkan keterlambatan.
     */
    public double getNilai() {
        if (this.nilai >= 0.0) {
            return (terlambat ? 100 - PENALTI_KETERLAMBATAN : 100) * this.nilai / 100;
        } else {
            return 0;
        }
    }

    /**
     * Mencetak nilai yang sudah disesuakan keterlambatan jika ada.
     * @return nilai yang sudah disesuaikan dengan keterlambatan + keterangannya.
     */
    @Override
    public String toString() {
        return String.format("%.2f%s", getNilai(), (terlambat ? " (T)" : "")).replace(",", ".");
    }
}
