package assignments.assignment2;

/**
 * Kelas yang berisi data-data mengenai komponen penilaian.
 */
public class KomponenPenilaian {
    //datafield kelas KomponenPenilaian
    private String nama;
    private ButirPenilaian[] butirPenilaian;
    private int bobot;

    /**
     * Constructor dari kelas KomponenPenilaian.
     * @param nama -> memberi masukan pada variabel instance nama.
     * @param banyakButirPenilaian -> memberi size pada array butirPenilaian.
     * @param bobot -> memberi masukan pada variabel instance bobot.
     */
    public KomponenPenilaian(String nama, int banyakButirPenilaian, int bobot) {
        this.nama = nama;
        this.bobot = bobot;
        this.butirPenilaian = new ButirPenilaian[banyakButirPenilaian];
    }

    /**
     * Membuat objek KomponenPenilaian baru berdasarkan bentuk KomponenPenilaian templat.
     * @param templat templat KomponenPenilaian.
     */
    private KomponenPenilaian(KomponenPenilaian templat) {
        this(templat.nama, templat.butirPenilaian.length, templat.bobot);
    }

    /**
     * Mengembalikan salinan skema penilaian berdasarkan templat yang diberikan.
     * @param templat templat skema penilaian sebagai sumber.
     * @return objek baru yang menyerupai templat.
     */
    public static KomponenPenilaian[] salinTemplat(KomponenPenilaian[] templat) {
        KomponenPenilaian[] salinan = new KomponenPenilaian[templat.length];
        for (int i = 0; i < salinan.length; i++) {
            salinan[i] = new KomponenPenilaian(templat[i]);
        }
        return salinan;
    }

    /**
     * Memasukkan objek butir penilaian ke dalam array butirPenilaian.
     * @param idx -> index butir penilaian.
     * @param butir -> objek butir yang akan dimasukkan.
     */
    public void masukkanButirPenilaian(int idx, ButirPenilaian butir) {
        this.butirPenilaian[idx] = butir;
    }

    /**
     * Getter.
     * @return nama dari komponen penilaian.
     */
    public String getNama() {
        return this.nama;
    }

    /**
     * Menghitung rata-rata dari nilai komponen berdasarkan butir yang sudah diinput asdos.
     * @return rata-rata nilai komponen.
     */
    public double getRerata() {
        double jumlahNilai = 0.0, jumlahButir = 0.0;
        for (ButirPenilaian butir: this.butirPenilaian){
            if (butir != null){
                jumlahNilai += butir.getNilai();
                jumlahButir += 1;
            }
        }
        return jumlahNilai / (jumlahButir == 0.0 ? 1.0 : jumlahButir);
    }

    /**
     * Mendapatkan nilai yang sudah sesuai dengan bobotnya.
     * @return nilai yang sudah disesuaikan dengan bobot.
     */
    public double getNilai() {
        return getRerata() * this.bobot / 100;
    }

    /**
     * Memberikan detail nilai dari masing-masing komponen penilaian.
     * Selain itu juga dicetak rerata dan kontribusi nilai akhir.
     * @return detail dari masing-masing komponen penilaian yang sudah diinput asdos.
     */
    public String getDetail() {
        String detail = String.format("~~~ %s (%d%%) ~~~%n", this.nama, this.bobot);
        int index = 1;

        //Kasus jika butir penilaian lebih dari 1.
        if (this.butirPenilaian.length > 1) {
            //Memastikan apakah asdos sudah menginput seluruh butir penilaian.
            boolean nullSemua = true;
            for (ButirPenilaian butir : this.butirPenilaian) {
                nullSemua = nullSemua && (butir == null ? true : false);
            }

            //Kasus jika asdos sudah menginput minimal 1 butir penilaian.
            if (!nullSemua){
                //Mencetak butir penilaian.
                for (ButirPenilaian butir : this.butirPenilaian) {
                    if (butir != null)
                        detail += String.format("%s %d: %s%n", this.nama, index, butir.toString());
                    index++;
                }
            }

            //Mencetak rerata dari komponen penilaian beserta nilai akhir yang sudah disesuaikan dengan bobot.
            detail += String.format("Rerata: %.02f%n", getRerata()).replace(",", ".");
            detail += String.format("Kontribusi nilai akhir: %.2f%n%n", getNilai()).replace(",", ".");
        }

        //Kasus jika butir penilaian 1.
        else {
            //Kasus jika asdos sudah menginput nilainya.
            if (this.butirPenilaian[0] != null) {
                detail += String.format("%s: %s%n", this.nama, this.butirPenilaian[0].toString());
            }

            //Kasus jika asdos belum menginput nilainya.
            else {
                detail += String.format("%s: 0.00%n", this.nama);
            }

            //Mencetak kontribusi nilai akhir sesuai bobot.
            detail += String.format("Kontribusi nilai akhir: %.2f%n%n", getNilai()).replace(",", ".");
        }

        return detail;
    }

    /**
     * Mencetak rerata nilai dari komponen penilaian.
     * @return rerata si komponen penilaian.
     */
    @Override
    public String toString() {
        return String.format("Rerata %s: %.2f", this.nama, getRerata()).replace(",", ".");
    }

}
