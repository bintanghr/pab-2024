// Nama : Bintang Harida Ramadhan
// NIM  : L0122034

open class MataKuliah(name: String, sks: Int) {
  var name: String
  var sks: Int
  var nilaiTeori: Double = 0.0

  init {
    this.name = name
    this.sks = sks
  }
}

interface Project {
  val nilaiProject: Double

  fun nilaiAkhir(): Double
}

class MataKuliahPraktikum(name: String, sks: Int) : MataKuliah(name, sks), Project {
  override var nilaiProject: Double = 0.0

  init {
    super.name = name
    super.sks = sks
  }

  override fun nilaiAkhir(): Double {
    return 0.7 * nilaiProject + 0.3 * nilaiTeori
  }
}

class Mahasiswa(name: String, nim: String) {
  val name: String
  val nim: String

  init {
    this.name = name
    this.nim = nim
  }

  val ai = MataKuliah("Kecerdasan Buatan", 3)
  val pab = MataKuliahPraktikum("Pengembangan Aplikasi Bergerak", 4)
  val pemweb = MataKuliahPraktikum("Pemrograman Web", 4)

  fun setNilaiAi(nilai: Double) {
    ai.nilaiTeori = nilai
  }

  fun setNilaiPab(nilaiTeori: Double, nilaiProject: Double) {
    pab.nilaiTeori = nilaiTeori
    pab.nilaiProject = nilaiProject
  }

  fun setNilaiPemweb(nilaiTeori: Double, nilaiProject: Double) {
    pemweb.nilaiTeori = nilaiTeori
    pemweb.nilaiProject = nilaiProject
  }

  fun getIndeksPrestasi(): Double {
    val sksTotal = ai.sks + pab.sks + pemweb.sks
    val totalNilai = (ai.nilaiTeori * ai.sks) + (pab.nilaiAkhir() * pab.sks) + (pemweb.nilaiAkhir() * pemweb.sks)
    var indeksPrestasi = totalNilai / sksTotal / 25

    return indeksPrestasi
  }

  fun nilaiHuruf(nilai: Double): Char {
    when {
      (nilai>80) -> return 'A'
      (nilai>60) -> return 'B'
      (nilai>40) -> return 'C'
      (nilai>20) -> return 'D'
      else -> return 'E'
    }
  }

  fun mhsInfo() {
    val sksTotal = ai.sks + pab.sks + pemweb.sks

    println("\n\n-------------------------------------------------------------");
    println("| Nama  : %-30s %20s".format(name, "|"));
    println("| NIM   : %s %42s".format(nim, "|"));
    println("-------------------------------------------------------------");
    print("| %-30s | %-11s | %-10s |\n".format("Mata Kuliah","Nilai", "Jumlah SKS"));
    println("-------------------------------------------------------------");
    print("| %-30s | %-5.0f | %-3c | %5d %6s\n".format(ai.name, ai.nilaiTeori, nilaiHuruf(ai.nilaiTeori), ai.sks, "|"));
    print("| %-30s | %-5.0f | %-3c | %5d %6s\n".format(pab.name, pab.nilaiAkhir(), nilaiHuruf(pab.nilaiAkhir()), pab.sks, "|"));
    print("| %-30s | %-5.0f | %-3c | %5d %6s\n".format(pemweb.name, pemweb.nilaiAkhir(), nilaiHuruf(pemweb.nilaiAkhir()), pemweb.sks, "|"));
    println("-------------------------------------------------------------");
    print("| Total SKS  : %1d %43s\n".format(sksTotal, "|"));
    print("| IP         : %.2f %41s\n".format(getIndeksPrestasi(), "|"));
    println("-------------------------------------------------------------");
  }
}

fun main() {
  val mahasiswa = mapOf<String,Mahasiswa>(
    "L0122001" to Mahasiswa("Addin Hadi", "L0122001"),
    "L0122002" to Mahasiswa("Afif Imam", "L0122002"),
    "L0122003" to Mahasiswa("Alfath Roziq", "L0122003"),
    "L0122004" to Mahasiswa("Bintang Harida", "L0122004")
  )
  
  mahasiswa["L0122001"]?.setNilaiAi(90.0)
  mahasiswa["L0122001"]?.setNilaiPab(95.0, 90.0)
  mahasiswa["L0122001"]?.setNilaiPemweb(70.0, 95.0)

  mahasiswa["L0122002"]?.setNilaiAi(80.0)
  mahasiswa["L0122002"]?.setNilaiPab(85.0, 70.0)
  mahasiswa["L0122002"]?.setNilaiPemweb(80.0, 95.0)
  
  mahasiswa["L0122003"]?.setNilaiAi(70.0)
  mahasiswa["L0122003"]?.setNilaiPab(75.0, 70.0)
  mahasiswa["L0122003"]?.setNilaiPemweb(90.0, 95.0)
  
  mahasiswa["L0122004"]?.setNilaiAi(90.0)
  mahasiswa["L0122004"]?.setNilaiPab(95.0, 90.0)
  mahasiswa["L0122004"]?.setNilaiPemweb(90.0, 95.0)

  mahasiswa.forEach{ (_, mhs) -> println(mhs.mhsInfo()) }
}