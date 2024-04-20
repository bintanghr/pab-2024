// Nama : Bintang Harida Ramadhan
// NIMM : L0122034

// import class Scanner dan ArrayList
import java.util.Scanner;
import java.util.ArrayList;

// class pasien untuk memudahkan penyimpanan data pasien
class Pasien
{
  // data member dari class
  private String nama;
  private int usia;
  private String alamat;

  // constructor class Pasien() untuk mengisi value data member
  Pasien(String nama, int usia, String alamat)
  {
    this.nama = nama;
    this.usia = usia;
    this.alamat = alamat;
  }

  // method untuk menampilkan informasi pasien (Data diri)
  public void detailInfo()
  {
    System.out.println("\nNama    : " + nama);
    System.out.println("Usia    : " + usia);
    System.out.println("Alamat  : " + alamat);
  }
}

public class PPBO_6_L0122034 {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    // ArrayList dari class Pasien
    ArrayList<Pasien> antrianPeriksa = new ArrayList<Pasien>();

    // looping while, jika kondisi variable "lanjut" true
    boolean lanjut = true;
    while (lanjut)
    {
      System.out.println("Menu");
      System.out.println("\n(1) Masukkan Antrian \n(2) Panggil Antrian \n(3) Hapus Seluruh Antrian Pasien \n(4) Tampilkan Seluruh Antrian \n(0) Keluar Program ");
      System.out.print("Masukkan pilihan menu anda : ");
      // menerima dan menyimpan inputan user
      int pilihan = scan.nextInt();
      // mengatasi inputan selanjutnya yang dilewati
      // karena method Scanner nextInt() tidak membaca karakter baris baru setelah menekan enter
      scan.nextLine();

      // switch case dari variable "pilihan"
      switch(pilihan)
      {
        case 0:
          // merubah value "lanjut" menjadi false agar keluar dari looping while
          lanjut = false;
        break;

        case 1:
          // menerima inputan user (Data diri)
          System.out.print("Nama Pasien : ");
          String nama = scan.nextLine();
          System.out.print("Usia Pasien : ");
          int usia = scan.nextInt();
          scan.nextLine();
          System.out.print("Alamat Pasien : ");
          String alamat = scan.nextLine();
      
          // menambahkan inputan user ke dalam ArrayList "antrianPeriksa" dengan membuat object baru dari class "Pasien"
          antrianPeriksa.add( new Pasien(nama, usia, alamat) );
        break;

        case 2:
          // exception jika belum terdapat antrian pasien
          try
          {
            // menampilkan info dari pasien pertama (datang paling awal) dan menghapus dari ArrayList "antrianPeriksa"
            antrianPeriksa.get(0).detailInfo();
            antrianPeriksa.remove(0);
          } 
          catch (IndexOutOfBoundsException e)
          {
            // akan ditampilkan jika ArrayList antrianPeriksa masih kosong
            System.out.println("\nAntrian Periksa Masih Kosong,");
            System.out.println("Masukkan Antrian Pasien Terlebih Dahulu !\n");
          }
        break;

        case 3:
          // menghapus seluruh ArrayList "antrianPasien"
          antrianPeriksa.removeAll(antrianPeriksa);
        break;

        case 4:
          // kondisi jika belum terdapat antrian pada ArrayList "antrianPasien"
          if (antrianPeriksa.isEmpty())
          {
            System.out.println("\nAntrian Periksa Masih Kosong,");
            System.out.println("Masukkan Antrian Pasien Terlebih Dahulu !\n");
          }
          else
          {
            // menampilkan seluruh antrian pasien beserta data diri
            System.out.println("Antrian Periksa Saat Ini");
            for (int i=0; i<antrianPeriksa.size(); i++)
            {
              antrianPeriksa.get(i).detailInfo();
            }
          }
        break;
      }
    }

    scan.close();
  }
}

// Alasan saya memilih struktur data ini adalah karena ArrayList dapat diisi dengan object Class "Pasien" 
// Program berkaitan dengan antrian tetapi tidak menggunakan struktur data "Queue" 
// karena pada Queue tidak dapat diisi dengan object Class
// Sehingga saya memanipulasi dalam pemanggilan elemen terdepan dengan cara menggunakan method .get(0) "elemen pertama pada ArrayList"