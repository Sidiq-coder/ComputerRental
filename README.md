✅ 1. Class Komputer
Atribut:
idKomputer : String
nomorKomputer : int
status : String (contoh: "KOSONG", "AKTIF", "MAINTENANCE")
durasiPenggunaan : int (dalam menit)
pelangganAktif : Pelanggan
Method:
aktifkan(Pelanggan pelanggan)
matikan()
setStatus(String status)
getStatus()
hitungDurasi(LocalDateTime mulai, LocalDateTime selesai) : int

✅ 2. Class Pelanggan
Atribut:
idPelanggan : String
nama : String
tipeAkun : String (contoh: "MEMBER", "NON_MEMBER")
noTelepon : String
Method:
login()
logout()
tampilInfo()

✅ 3. Class Operator
Atribut:
idOperator : String
nama : String
username : String
password : String
Method:
login(String username, String password)
monitorKomputer(List<Komputer> komputerList)
prosesTransaksi(Pelanggan p, Komputer k)

✅ 4. Class Transaksi
Atribut:
idTransaksi : String
pelanggan : Pelanggan
komputer : Komputer
waktuMulai : LocalDateTime
waktuSelesai : LocalDateTime
totalDurasi : int
tarifPerJam : double
totalBayar : double

Method:
hitungDurasi() : int
hitungTotalBayar() : double
cetakStruk()

✅ 5. Class Member
Atribut:
idMember : String
pelanggan : Pelanggan
tanggalDaftar : LocalDate
tanggalKadaluarsa : LocalDate
saldo : double

Method:
cekAktif() : boolean
isiSaldo(double nominal)
potongSaldo(double nominal)

✅ 6. Class Main
Atribut:
(Biasanya class Main tidak memiliki atribut, tapi digunakan untuk mengeksekusi program)
Method:
main(String[] args)
menuUtama()
prosesLoginOperator()
prosesLoginPelanggan()
mulaiSesi()
akhiriSesi()

✅ 7. Class Laporan
Atribut:
idLaporan : String
periodeAwal : LocalDate
periodeAkhir : LocalDate
daftarTransaksi : List<Transaksi>
totalPendapatan : double

Method:
generateLaporan()
tampilkanRingkasan()
cetakLaporan()

