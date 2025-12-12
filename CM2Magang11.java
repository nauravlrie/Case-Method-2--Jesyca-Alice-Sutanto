import java.util.Scanner; //JESYCA ALICE SUTANTO SIB 1A NO 11

public class CM2Magang11 {
    static String [][] dataMagang = new String [100][6]; //nama, nim, prodi dll
    static int jumlahData = 0;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;
       
        do { //menggunakan loop do while sampai pengguna memilih keluar
            System.out.println("\n=== Sistem Pendaftaran Magang Mahasiswa ===");
            System.out.println("1. Tambah Data Magang Mahasiswa");
            System.out.println("2. Tampilkan Semua Pendaftar Magang");
            System.out.println("3. Cari Pendaftar Berdasarkan Program Studi");
            System.out.println("4. Hitung Jumlah Pendaftar untuk Setiap Status");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");
           
            pilihan = input.nextInt();
            //if else untuk piihan
            if (pilihan == 1) tambahData(input);
            else if (pilihan == 2) tampilkanData();
            else if (pilihan == 3) cariData(input);
            else if (pilihan == 4) hitungStatus();
            else if (pilihan == 5) System.out.println("Program selesai.");
            else System.out.println("Pilihan tidak valid!");
        } while (pilihan != 5); //kondisi untuk terus mengulang jika pilihan diluar 5, karena kalau 5 sudah berhenti dan tidak akan mengulangi pertanyaan lgi
            //kalau enter 5 maka keluar loop
        input.close(); //tutup scanner
    }

    //menambah data magang dan memvalidasi agar jumlah tidak melebihi 100
    static void tambahData(Scanner input) {
        if (jumlahData >= 100) {
            System.out.println("Data sudah penuh!");
            return; //keluar dari pengecekan
        }

        System.out.println("\n--- Tambah Data Magang ---");
        System.out.print("Nama Mahasiswa: ");
        String nama = input.nextLine();
        System.out.print("NIM: ");
        String nim = input.nextLine();
        System.out.print("Program Studi: ");
        String prodi = input.nextLine();
        System.out.print("Perusahaan Tujuan Magang: ");
        String perusahaan = input.nextLine();


        //memvalidasi semester, 6 atau 7
        String semester;
        do {
            System.out.print("Semester pengambilan magang (6 atau 7): ");
            semester = input.nextLine();
            if (semester.equals("6") == false && semester.equals("7") == false)
                System.out.println("Hanya boleh 6 atau 7!"); 
        } while (semester.equals("6") == false && semester.equals("7") == false); //mengulangi loop jika input bukan 6 atau 7
        
        
        String status;
        do {
            System.out.print("Status magang (Diterima/Menunggu/Ditolak): ");
            status = input.nextLine();
            if (status.equalsIgnoreCase("Diterima") == false &&
                status.equalsIgnoreCase("Menunggu") == false &&
                status.equalsIgnoreCase("Ditolak") == false)
                System.out.println("Hanya Diterima, Menunggu, atau Ditolak!");
        } while (status.equalsIgnoreCase("Diterima") == false &&
                 status.equalsIgnoreCase("Menunggu") == false &&
                 status.equalsIgnoreCase("Ditolak") == false);


        //menyimpan ke array 2D
        dataMagang[jumlahData][0] = nama;
        dataMagang[jumlahData][1] = nim;
        dataMagang[jumlahData][2] = prodi;
        dataMagang[jumlahData][3] = perusahaan;
        dataMagang[jumlahData][4] = semester;
        dataMagang[jumlahData][5] = status;
        jumlahData++;

        System.out.println("Data pendaftaran magang berhasil ditambahkan. Total pendaftar: " + jumlahData);
    }

    //menampilkan semua data pendaftar
    static void tampilkanData() {
        if (jumlahData == 0) {
            System.out.println("\nBelum ada data pendaftar.");
            return;
        }

        System.out.println("\n--- Seluruh Data Pendaftar Magang ---");
        System.out.println("Team Teaching Dasar Pemrograman 2025 Politeknik Negeri Malang");
        System.out.printf("%-3s %-15s %-10s %-20s %-15s %-8s %-10s\n",
                "No", "Nama", "NIM", "Prodi", "Perusahaan", "Semester", "Status");
        System.out.println("--------------------------------------------------------------------------------");
        for (int i = 0; i < jumlahData; i++) {
            System.out.printf("%-3d %-15s %-10s %-20s %-15s %-8s %-10s\n",
                    (i+1), dataMagang[i][0], dataMagang[i][1], dataMagang[i][2],
                    dataMagang[i][3], dataMagang[i][4], dataMagang[i][5]); //mencetak data lanjutan sesuai format
        }

        System.out.println("Total pendaftar: " + jumlahData);
    }

    //mncari berdasarkan Program Studi
    static void cariData(Scanner input) {
        System.out.println("\n--- Cari Data Berdasarkan Program Studi ---");
        System.out.print("Masukkan Program Studi: ");
        String cari = input.nextLine();

        System.out.println("Team Teaching Dasar Pemrograman 2025 Politeknik Negeri Malang");
        System.out.printf("%-3s %-15s %-10s %-20s %-15s %-8s %-10s\n",
                "No", "Nama", "NIM", "Prodi", "Perusahaan", "Semester", "Status");
        System.out.println("--------------------------------------------------------------------------------");
        
        boolean ketemu = false; //deklarasi 
        //loop untuk setiap data
        for (int i = 0; i < jumlahData; i++) {
            if (dataMagang[i][2].equals(cari)) { //indeks 2/ kolom ketiga,berarti mencari dalam prodi

                System.out.printf("%-3d %-15s %-10s %-20s %-15s %-8s %-10s\n",
                        (i+1), dataMagang[i][0], dataMagang[i][1], dataMagang[i][2],
                        dataMagang[i][3], dataMagang[i][4], dataMagang[i][5]);
                ketemu = true;
            }
        }
        if (ketemu == false) System.out.println("Tidak ditemukan data.");
    }

    //menghitung jumlah per status
    static void hitungStatus() {
        if (jumlahData == 0) {
            System.out.println(" \nBelum ada pendaftar.");
            return;
        }

        int diterima = 0, menunggu = 0, ditolak = 0;

        for (int i = 0; i < jumlahData; i++) {
            String status = dataMagang[i][5]; //mengambil status
            if (status.equalsIgnoreCase("diterima")) diterima++;
            else if (status.equalsIgnoreCase("menunggu")) menunggu++;
            else if (status.equalsIgnoreCase("ditolak")) ditolak++;
        }
        System.out.println("\n--- Jumlah Pendaftar per Status ---");
        System.out.println("Diterima : " + diterima);
        System.out.println("Menunggu : " + menunggu);
        System.out.println("Ditolak : " + ditolak);
        System.out.println("Total : " + jumlahData);
    }
}
