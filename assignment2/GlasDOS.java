package assignments.assignment2;

//Mengimport library untuk menerima input.
import java.util.Scanner;

/**
 * Kelas yang berisi sistem untuk memudahkan penilaian asdos.
 */
public class GlasDOS {
    //menyiapkan variabel yang akan dipakai nantinya
    private static final int INPUT_NUM = 1;
    private static final int DETAIL_NUM = 2;
    private static final int REKAP_NUM = 3;
    private static final int GASDOS_NUM = 4;
    private static final int KELUAR_NUM = 99;
    private AsistenDosen[] asistenDosen;
    private KomponenPenilaian[] templatSkemaPenilaian;
    private Scanner input = new Scanner(System.in);

    /**
     * Memberikan pesan selamat datang kepada user.
     */
    private void initPesan() {
        System.out.println("Selamat datang di Global Asdos Data Organizing System!");
        System.out.println("====================== GlasDOS =======================");
        System.out.println();
    }

    /**
     * Menginisialisasi detail penilaian yang akan dipakai.
     */
    private void initSkemaPenilaian() {
        //Meminta banyaknya komponen penilaian yang akan dibuat
        System.out.println("Mari buat skema penilaian!");
        System.out.println("Masukkan banyaknya komponen penilaian:");
        int numKomponen = Integer.parseInt(input.nextLine());
        templatSkemaPenilaian = new KomponenPenilaian[numKomponen];

        //Memasukkan data-data masing-masing komponen penilaian.
        for (int i = 1; i <= numKomponen; i++) {
            System.out.println("> Komponen Penilaian " + i);

            //Meminta nama komponen penilaian.
            System.out.println("Masukkan nama komponen penilaian " + i + ": ");
            String namaKomponen = input.nextLine();

            //Meminta banyaknya butir penilaian dari komponen tersebut.
            System.out.println("Masukkan banyaknya butir penilaian " + namaKomponen + ":");
            int numButir = Integer.parseInt(input.nextLine());

            //Meminta bobot dari komponen penilaian tersebut.
            System.out.println("Masukkan bobot penilaian " + namaKomponen + " (dalam persen):");
            int bobot = Integer.parseInt(input.nextLine());
            templatSkemaPenilaian[i - 1] = new KomponenPenilaian(namaKomponen, numButir, bobot);
        }
        System.out.println("Skema penilaian berhasil dibuat!");
        System.out.println();
    }

    /**
     * Memasukkan data asdos dengan nama mahasiswa yang dipegang si asdos.
     */
    private void initAsdosMahasiswa() {
        //Meminta banyaknya asdos yang ingin dimasukkan pada sistem.
        System.out.println("Mari masukkan data asdos dan mahasiswa!");
        System.out.println("Masukkan banyaknya asdos:");
        int numAsistenDosen = Integer.parseInt(input.nextLine());
        asistenDosen = new AsistenDosen[numAsistenDosen];

        //Memasukkan data-data dari masing-masing asisten dosen.
        for (int i = 1; i <= numAsistenDosen; i++) {
            //Meminta data asdos yaitu kode dan nama asdos.
            System.out.println("Data asdos " + i + ":");
            String[] dataAsdos = input.nextLine().split(" ", 2);
            AsistenDosen newAsdos = new AsistenDosen(dataAsdos[0], dataAsdos[1]);
            asistenDosen[i - 1] = newAsdos;

            //Meminta banyaknya mahasiswa yang ditangani oleh asdos tersebut.
            System.out.println("Masukkan banyaknya mahasiswa dengan kode asdos " + dataAsdos[0] + ":");
            int numAsdosan = Integer.parseInt(input.nextLine());

            //Meminta data-data mahasiswa yang ditangani oleh si asdos.
            for (int j = 1; j <= numAsdosan; j++) {
                //Meminta data mahhasiswa yaitu npm dan nama.
                System.out.println("Data mahasiswa " + j + ":");
                String[] dataMhs = input.nextLine().split(" ", 2);
                KomponenPenilaian[] skema = KomponenPenilaian.salinTemplat(templatSkemaPenilaian);
                Mahasiswa baru = new Mahasiswa(dataMhs[0], dataMhs[1], skema);
                newAsdos.addMahasiswa(baru);
            }
        }
        System.out.println();
    }

    /**
     * Mencari objek asisten dosen yang sesuai dengan kode asdos.
     * @param kode -> data yang mencirikan suatu asdos tertentu.
     * @return objek asisten dosen yang sesuai dengan kode asdos.
     */
    private AsistenDosen getAsistenDosen(String kode) {
        AsistenDosen asisten = null;
        for (AsistenDosen asistenTemp : this.asistenDosen) {
            if (asistenTemp.getKode().equalsIgnoreCase(kode))
                asisten = asistenTemp;
        }
        return asisten;
    }

    /**
     * Mencari objek mahasiswa yang sesuai dengan npmnya dan kode asdosnya.
     * @param kodeAsdos -> kode asdos si mahasiswa.
     * @param npm -> npm milik si mahasiswa.
     * @return objek mahasiswa yang sesuai dengan npm dan kode asdosnya.
     */
    private Mahasiswa getMahasiswa(String kodeAsdos, String npm) {
        AsistenDosen kodeAsisten = getAsistenDosen(kodeAsdos);
        if (kodeAsisten != null){
            Mahasiswa mahasiswa = kodeAsisten.getMahasiswa(npm);
            return mahasiswa;
        } else {
            return null;
        }
    }

    /**
     * Mencari komponen penilaian tertentu dari mahasiswa.
     * @param kodeAsdos -> kode asdos si mahasiswa.
     * @param npm -> npm milik si mahasiswa.
     * @param namaKomponen -> suatu komponen penilaian yang ingin didapat.
     * @return komponen penilaian yang sesuai dengan parameternya.
     */
    private KomponenPenilaian getKomponenPenilaian(String kodeAsdos, String npm, String namaKomponen) {
        Mahasiswa mahasiswa = getMahasiswa(kodeAsdos, npm);
        if (mahasiswa != null){
            return mahasiswa.getKomponenPenilaian(namaKomponen);
        } else {
            return null;
        }
    }

    /**
     * Method untuk meminta inputan nilai yang ingin dimasukkan.
     */
    private void menuInput() {
        //Memberikan penjelasan mengenai menu input.
        System.out.println("--- Input nilai ---");
        System.out.println("Masukkan perintah input nilai dalam format berikut:");
        System.out.println(
                "[KodeAsdos] [NPM] [KomponenPenilaian] [NomorButirPenilaian] [Nilai] [Terlambat]"
        );
        System.out.println("Contoh: SMA 1234567890 Lab 1 90.00 true");
        System.out.println("Jika sudah selesai, tulis SELESAI dan tekan tombol Enter.");
        System.out.println();

        //Menginput nilai masing-masing komponen penilaian beserta butir-butirnya.
        while (!input.hasNext("(?i)SELESAI")) {
            //Memisahkan data-data.
            String masukan[] = input.nextLine().split(" ");
            String kodeAsdos = masukan[0];
            String npm = masukan[1];
            String namaKomponen = masukan[2];
            int idx = Integer.parseInt(masukan[3]);
            double nilaiButir = Double.parseDouble(masukan[4]);
            boolean terlambat = Boolean.parseBoolean(masukan[5]);

            //Membuat objek butir penilaian dan mendapatkan objek komponen penilaian berdasarkan inputannya.
            ButirPenilaian butir = new ButirPenilaian(nilaiButir, terlambat);
            KomponenPenilaian komponenPenilaian = getKomponenPenilaian(kodeAsdos, npm, namaKomponen);

            //Memasukkan butir penilaian jika objek komponen penilaian tersedia
            if (komponenPenilaian != null) {
                komponenPenilaian.masukkanButirPenilaian(idx - 1, butir);
            } else {
                System.out.println(
                        "Komponen penilaian untuk NPM " + npm + " pada kode asdos "
                                + kodeAsdos + "tidak ditemukan!"
                );
            };
        }
        input.nextLine();
        System.out.println("Sukses! Kembali ke menu utama...");
        System.out.println();
    }

    /**
     * Method untuk menampilkan detail penilaian dari seorang mahasiswa.
     */
    private void menuDetail() {
        //Menjelaskan menu detail dan memisahkan data-data.
        System.out.println("--- Lihat detail nilai mahasiswa ---");
        System.out.println("Masukkan data mahasiswa dalam format berikut:");
        System.out.println("[KodeAsdos] [NPM]");
        System.out.println("Contoh: SMA 134567890");
        System.out.println();
        String[] dataMhs = input.nextLine().split(" ");
        Mahasiswa mahasiswa = getMahasiswa(dataMhs[0], dataMhs[1]);

        //Mencetak detail dari nilai mahasiswa jika mahasiswa tersedia.
        if (mahasiswa != null) {
            System.out.println(mahasiswa);
            System.out.println(mahasiswa.getDetail());
        } else {
            System.out.println("Mahasiswa dengan kode asdos dan NPM tersebut tidak ditemukan!");
        }

        System.out.println();
        System.out.println("Kembali ke menu utama...");
        System.out.println();
    }

    /**
     * Method untuk merekap nilai-nilai mahasiswa yang ada di sistem GlasDOS.
     */
    private void menuRekap() {
        System.out.println("--- Rekap ---");

        //Merekap nilai-nilai mahasiswa dari masing-masing asdos.
        for (AsistenDosen asdos : asistenDosen) {
            System.out.println(asdos);
            for (int i = 0; i < asdos.toString().length(); i++) System.out.print("~");
            System.out.println();
            System.out.println();
            System.out.print(asdos.rekap());
        }
        System.out.println("Kembali ke menu utama...");
        System.out.println();
    }

    /**
     * Method yang dapat mengganti asdos dari seorang mahasiswa.
     */
    private void menuGasdos() {
        //Menjelaskan menu ganti asdos.
        System.out.println("--- Ganti Asdos ---");
        System.out.println("Masukkan perintah input nilai dalam format berikut: ");
        System.out.println("[KodeAsdosAsal] [NPM] [KodeAsdosBaru]");
        System.out.println("Contoh: SMA 134567890 SMP");
        System.out.println();

        //Memisahkan data-data.
        String[] dataPergantian = input.nextLine().split(" ");
        String kodeAsdosLama = dataPergantian[0];
        String npm = dataPergantian[1];
        String kodeAsdosBaru = dataPergantian[2];
        AsistenDosen asdosLama = getAsistenDosen(kodeAsdosLama);
        AsistenDosen asdosBaru = getAsistenDosen(kodeAsdosBaru);
        Mahasiswa mahasiswa = getMahasiswa(kodeAsdosLama, npm);

        //Kasus jika asdos lama, mahasiswa, dan asdos baru berhasil ditemukan.
        if (asdosLama != null && mahasiswa != null && asdosBaru != null) {
            asdosLama.removeMahasiswa(mahasiswa);
            asdosBaru.addMahasiswa(mahasiswa);
            System.out.println("Kode Asdos dari siswa yang memiliki npm " + npm + " telah berhasil diubah " +
                    "dari " + kodeAsdosLama + " menjadi " + kodeAsdosBaru);
        }

        //Kasus jika selain yang diatas.
        else {
            if (asdosLama != null) {
                if (mahasiswa != null) {
                    //Kasus jika asdos baru tidak tersedia pada sistem.
                    if (asdosBaru == null) {
                        System.out.println("Asdos dengan kode " + kodeAsdosBaru + " tidak ditemukan!");
                    }
                }
                //Kasus jika mahasiswa tidak ada pada asdos lama.
                else {
                    System.out.println("Mahasiswa tidak ditemukan!");
                }
            } else {
                //Kasus jika asdos lama dan asdos baru tidak tersedia pada sistem.
                if (asdosLama == null && asdosBaru == null){
                    System.out.println("Asdos dengan kode " + kodeAsdosLama + " dan " +
                            kodeAsdosBaru + " tidak ditemukan!");
                }
                //Kasus jika asdos lama tidak ditemukan pada sistem.
                else {
                    System.out.println("Asdos dengan kode " + kodeAsdosLama + " tidak ditemukan!");
                }
            }
        }

        System.out.println();
        System.out.println("Kembali ke menu utama...");
        System.out.println();
    }

    /**
     * Menu utama untuk menjalankan fitur-fitur yang tersedia di GlasDOS.
     */
    private void menu() {
        int operation = 0;
        boolean hasChosenExit = false;
        while (!hasChosenExit) {
            System.out.println("=== Menu utama ===");
            System.out.println("1. Input nilai");
            System.out.println("2. Lihat detail nilai mahasiswa");
            System.out.println("3. Rekap");
            System.out.println("4. Ganti Kode Asdos");
            System.out.println("99. Keluar");
            System.out.println("Masukkan pilihan menu:");
            try {
                operation = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
            System.out.println();
            if (operation == INPUT_NUM) {
                menuInput();
            } else if (operation == DETAIL_NUM) {
                menuDetail();
            } else if (operation == REKAP_NUM) {
                menuRekap();
            } else if (operation == GASDOS_NUM){
                menuGasdos();
            } else if (operation == KELUAR_NUM) {
                System.out.println("Terima kasih telah menggunakan GlasDOS!");
                hasChosenExit = true;
            }
        }
    }

    /**
     * Menjalankan program seutuhnya.
     */
    private void run() {
        initPesan();
        initSkemaPenilaian();
        initAsdosMahasiswa();
        System.out.println("Inisialisasi selesai!");
        System.out.println();
        menu();
        input.close();
    }

    /**
     * Method utama agar program ini dapat berjalan.
     * @param args -> parameter default.
     */
    public static void main(String[] args) {
        GlasDOS program = new GlasDOS();
        program.run();
    }
}
