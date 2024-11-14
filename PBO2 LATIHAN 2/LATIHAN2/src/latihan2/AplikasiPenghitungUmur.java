/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package latihan2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class AplikasiPenghitungUmur {
    public static void main(String[] args) {
        // Membuat frame utama
        JFrame frame = new JFrame("Aplikasi Penghitung Umur");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));

        // Komponen GUI
        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        JDateChooser dateChooser = new JDateChooser();
        JButton btnHitung = new JButton("Hitung");
        JButton btnHapus = new JButton("Hapus");
        JLabel labelUmur = new JLabel("Umur (Tahun, Bulan, Hari):");
        JTextField textUmur = new JTextField();
        textUmur.setEditable(false);
        JLabel labelUlangTahunBerikutnya = new JLabel("Hari Ulang Tahun Berikutnya:");
        JTextField textUlangTahunBerikutnya = new JTextField();
        textUlangTahunBerikutnya.setEditable(false);

        // Menambahkan komponen ke panel
        panel.add(labelTanggalLahir);
        panel.add(dateChooser);
        panel.add(labelUmur);
        panel.add(textUmur);
        panel.add(labelUlangTahunBerikutnya);
        panel.add(textUlangTahunBerikutnya);
        panel.add(new JLabel());  // Kosong untuk spasi
        panel.add(btnHitung);
        panel.add(new JLabel());  // Kosong untuk spasi
        panel.add(btnHapus);

        // Menambahkan panel ke frame
        frame.add(panel, BorderLayout.CENTER);

        // Logika tombol Hitung
        btnHitung.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (dateChooser.getDate() != null) {
                        LocalDate tanggalLahir = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate sekarang = LocalDate.now();

                        if (!tanggalLahir.isAfter(sekarang)) {
                            Period periode = Period.between(tanggalLahir, sekarang);
                            long totalHari = ChronoUnit.DAYS.between(tanggalLahir, sekarang);
                            textUmur.setText(periode.getYears() + " Tahun, " + periode.getMonths() + " Bulan, " + periode.getDays() + " Hari (" + totalHari + " Hari Total)");

                            // Menghitung hari ulang tahun berikutnya
                            LocalDate nextBirthday = tanggalLahir.withYear(sekarang.getYear());
                            if (nextBirthday.isBefore(sekarang) || nextBirthday.isEqual(sekarang)) {
                                nextBirthday = nextBirthday.plusYears(1);
                            }
                            long daysUntilNextBirthday = ChronoUnit.DAYS.between(sekarang, nextBirthday);
                            textUlangTahunBerikutnya.setText(nextBirthday.toString() + " (" + daysUntilNextBirthday + " Hari Lagi)");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Tanggal lahir tidak boleh di masa depan.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Harap pilih tanggal lahir.", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Terjadi kesalahan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Logika tombol Hapus
        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dateChooser.setDate(null);
                textUmur.setText("");
                textUlangTahunBerikutnya.setText("");
                dateChooser.requestFocus();
            }
        });

        // Menampilkan frame
        frame.setVisible(true);
    }
}
