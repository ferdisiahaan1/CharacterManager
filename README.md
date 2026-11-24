## TP14 — Form Input, Validasi & Analisis MVC (Sprint #6)

### 1. Dokumentasi Tampilan (Screenshots)
* **Error Nama Kosong:**  
  `![Error Nama Kosong](screenshots/tp14-error-nama-kosong.png)`
* **Error Nama Duplikat:**  
  `![Error Nama Duplikat](screenshots/tp14-error-nama-duplikat.png)`
* **Sukses dengan Kartu Karakter Baru:**  
  `![Sukses Kartu Karakter Baru](screenshots/tp14-sukses-kartu-baru.png)`

*(Pastikan nama file gambar di atas disesuaikan dengan nama file tangkapan layar yang kamu simpan di folder `screenshots/`.)*

---

### 2. Tabel Uji Validasi (Bagian B)

| No | Input (Nama, Role, Level) | Hasil Diharapkan | Hasil Nyata | Lapisan yang Menangkap |
| :- | :--- | :--- | :--- | :--- |
| 1 | `""` (kosong), `WARRIOR`, `10` | Ditolak; border merah, fokus kembali ke field nama, pesan error nama wajib diisi | Sesuai harapan | View (`CharacterFormPanel`) |
| 2 | `"   "` (hanya spasi), `ARCHER`, `5` | Ditolak setelah `trim()`; pesan error nama wajib diisi | Sesuai harapan | View (`CharacterFormPanel`) |
| 3 | `"  Aria"` (diawali spasi ganda), `MAGE`, `15` | Ditolak; pesan larangan nama diawali spasi ganda tampil | Sesuai harapan | View (`CharacterFormPanel`) |
| 4 | `"Li"` (2 karakter), `WARRIOR`, `12` | Ditolak; pesan panjang nama minimal 3 karakter | Sesuai harapan | View (`CharacterFormPanel`) |
| 5 | `"Kael@#1"`, `ROGUE`, `20` | Ditolak; pesan format nama tidak valid | Sesuai harapan | View (`CharacterFormPanel`) |
| 6 | `"Merlin"`, `MAGE`, `65` (Mini-Challenge) | Ditolak; pesan `MAGE` tidak boleh level di atas 60 tampil di form | Awalnya hanya muncul di konsol / belum tampil di label form | Model (`Character` constructor) |
| 7 | `"Arthur"` (sudah ada di daftar), `WARRIOR`, `25` | Ditolak; pesan nama karakter sudah digunakan | Sesuai harapan | View (`isNameTaken`) & Model |
| 8 | Input karakter ke-21 saat daftar sudah penuh (20 karakter) | Ditolak; pesan kapasitas daftar maksimal 20 karakter tercapai | Sesuai harapan | Model (`CharacterManager`) |
| 9 | `"  Lyra  "`, `MAGE`, `50` | Diterima; nama tersimpan bersih `"Lyra"` & kartu baru muncul | Sesuai harapan | Lolos View & Model |

#### Catatan Perbedaan Hasil Nyata & Tindakan Perbaikan
* **Kasus #6 (`MAGE` level `65`):** Pada pengujian awal, aturan di lapisan `Model` sudah melemparkan `IllegalArgumentException`, tetapi `CharacterFormPanel` belum menangkap pengecualian tersebut untuk ditampilkan ke komponen label error di form. **Tindakan perbaikan:** Membungkus pembuatan objek `Character` dan pemanggilan `manager.addCharacter(...)` di dalam blok `try-catch (IllegalArgumentException ex)` pada `CharacterFormPanel` lalu menampilkan `ex.getMessage()` langsung ke form tanpa mengubah aturan di `CharacterFormPanel`.

---

### 3. Kompromi Sementara (Sprint #6)
Pada implementasi Sprint #6 ini, `CharacterFormPanel` masih menggunakan `ActionListener` minimal secara langsung pada tombol submit dan memanggil `manager.addCharacter(...)` dari dalam lapisan `View`. Kompromi ini dipilih agar alur validasi serta pembaruan daftar kartu (`refreshList()`) dapat berjalan dan diuji secara langsung sebelum lapisan `Controller` diperkenalkan. Konsekuensinya, `View` masih memiliki ketergantungan langsung terhadap logika pemrosesan data dan alur aksi. Pada Pertemuan 9, tanggung jawab koordinasi aksi dan pemanggilan `Model` akan dipindahkan ke kelas `Controller` terpisah agar `View` lebih bersih dan sesuai prinsip MVC.

---

### 4. Tautan Analisis Input & Validasi (Bagian D)
* **Google Docs Analisis Sprint #6:** [Masukkan Link Google Docs Kamu di Sini](https://docs.google.com/document/d/1qofRVnpWOUovml4l37HdXcjzWKipnxC1sKc58TLrYZk/edit?usp=sharing)