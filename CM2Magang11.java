import java.util.Scanner; //JESYCA ALICE SUTANTO

public class CM2Magang11 {
    static String [][] dataMagang = new String [100][6];
    static int jumlahData = 0;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;
        
        do {
            System.out.println("\n=== Sistem Pendaftaran Magang Mahasiswa ===");
            System.out.println("1. Tambah Data Magang Mahasiswa");
            System.out.println("2. Tampilkan Semua Pendaftar Magang");
            System.out.println("3. Cari Pendaftar Berdasarkan Program Studi");
            System.out.println("4. Hitung Jumlah Pendaftar untuk Setiap Status");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");
            pilihan = input.nextInt();
            input.nextLine();                     

            if (pilihan == 1)      tambahData(input);
            else if (pilihan == 2) tampilkanData();
            else if (pilihan == 3) cariData(input);
            else if (pilihan == 4) hitungStatus();
            else if (pilihan == 5) System.out.println("Program selesai.");
            else                   System.out.println("Pilihan tidak valid!");

        } while (pilihan != 5);

        input.close();
    }

    //menambah data dan validasi semester dan status
    static void tambahData(Scanner input) {
        if (jumlahData >= 100) {
            System.out.println("Data sudah penuh!");
            return;
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
            if (!semester.equals("6") && !semester.equals("7"))
                System.out.println("Hanya boleh 6 atau 7!");
        } while (!semester.equals("6") && !semester.equals("7"));

        //memvalidasi status
        String status;
        do {
            System.out.print("Status magang (Diterima/Menunggu/Ditolak): ");
            status = input.nextLine();
            if (!status.equalsIgnoreCase("Diterima") && 
                !status.equalsIgnoreCase("Menunggu") && 
                !status.equalsIgnoreCase("Ditolak"))
                System.out.println("Hanya Diterima, Menunggu, atau Ditolak!");
        } while (!status.equalsIgnoreCase("Diterima") && 
                 !status.equalsIgnoreCase("Menunggu") && 
                 !status.equalsIgnoreCase("Ditolak"));

        status = status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
        perusahaan = perusahaan.substring(0, 1).toUpperCase() + perusahaan.substring(1).toLowerCase();

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

    //menampilkan semua data
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
                    dataMagang[i][3], dataMagang[i][4], dataMagang[i][5]);
        }
        System.out.println("Total pendaftar: " + jumlahData);
    }

    //mncari berdasarkan Program Studi 
    static void cariData(Scanner input) {
        System.out.println("\n--- Cari Data Berdasarkan Program Studi ---");
        System.out.print("Masukkan Program Studi: ");
        String cari = input.nextLine().toLowerCase();

        System.out.println("Team Teaching Dasar Pemrograman 2025 Politeknik Negeri Malang"); 
        System.out.printf("%-3s %-15s %-10s %-20s %-15s %-8s %-10s\n",
                "No", "Nama", "NIM", "Prodi", "Perusahaan", "Semester", "Status");
        System.out.println("--------------------------------------------------------------------------------");

        boolean ketemu = false;
        for (int i = 0; i < jumlahData; i++) {
            if (dataMagang[i][2].toLowerCase().contains(cari)) {
                System.out.printf("%-3d %-15s %-10s %-20s %-15s %-8s %-10s\n",
                        (i+1), dataMagang[i][0], dataMagang[i][1], dataMagang[i][2],
                        dataMagang[i][3], dataMagang[i][4], dataMagang[i][5]);
                ketemu = true;
            }
        }
        if (!ketemu) System.out.println("Tidak ditemukan data.");
    }

    //menghitung jumlah per status
    static void hitungStatus() {
        if (jumlahData == 0) {
            System.out.println(" \nBelum ada pendaftar.");
            return;
        }
        int diterima = 0, menunggu = 0, ditolak = 0;

        for (int i = 0; i < jumlahData; i++) {
            String s = dataMagang[i][5].toLowerCase();
            if (s.equals("diterima"))      diterima++;
            else if (s.equals("menunggu")) menunggu++;
            else if (s.equals("ditolak"))  ditolak++;
        }

        System.out.println("\n--- Jumlah Pendaftar per Status ---");
        System.out.println("Diterima : " + diterima);
        System.out.println("Menunggu : " + menunggu);
        System.out.println("Ditolak  : " + ditolak);
        System.out.println("Total    : " + jumlahData);
    }
}