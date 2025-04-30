# ComputerRental
1. Anggota 1 – Class Komputer
Deskripsi: Merepresentasikan data komputer yang tersedia untuk disewa.
Atribut: idKomputer, merk, spesifikasi, hargaPerJam, status
Method: tampilkanInfo(), setStatus(boolean tersedia)

2. Anggota 2 – Class Pelanggan
Deskripsi: Menyimpan informasi pelanggan yang melakukan penyewaan.
Atribut: idPelanggan, nama, alamat, noTelepon
Method: tampilkanDataPelanggan()

3. Anggota 3 – Class Transaksi
Deskripsi: Mengelola proses penyewaan komputer.
Atribut: idTransaksi, pelanggan, komputer, waktuMulai, waktuSelesai, totalBayar
Method: hitungTotal(), tampilkanDetailTransaksi()

4. Anggota 4 – Class Admin
Deskripsi: Mengelola komputer dan pelanggan (CRUD data).
Atribut: idAdmin, username, password
Method: tambahKomputer(), hapusKomputer(), tambahPelanggan(), hapusPelanggan()

5. Anggota 5 – Class Pembayaran
Deskripsi: Menangani pembayaran sewa komputer.
Atribut: idPembayaran, transaksi, metodePembayaran, statusPembayaran
Method: prosesPembayaran(), tampilkanStruk()

6. Anggota 6 – Class RentalApp (Main Class)
Deskripsi: Menjalankan aplikasi, menyediakan menu utama.
Tugas: Menghubungkan semua class, input/output pengguna.
Method: main(), menuUtama()
