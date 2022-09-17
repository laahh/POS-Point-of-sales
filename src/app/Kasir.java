package app;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import utils.*; ///////mengimport semua kelas dari package utils 
import static utils.Database.queryResultSet;

public class Kasir extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    int no = 1;
    ArrayList<String> idBarangList = new ArrayList<>();
    int baris = 0;
    int totalHargaBarang = 0;
    String harga_beli = "";
    String sqlInsertQuery = "INSERT INTO detail_transaksi VALUES ";

    public Kasir() {
        initComponents();

        model.addColumn("Id Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Qty");
        model.addColumn("Harga Jual");
        model.addColumn("Harga Beli");

        tabelBarang.setModel(model);

        barcode.requestFocus();

        Model.table(tabelBarang);
        tanggal.setText(Attr.getDateNow("yyyy-MM-dd"));//menampilkan tanggal otomatis 
        idTransaksi.setText("TR" + Attr.getDateNow("yyyyMMdd") + Attr.generateRandomNumber(99999));//id transaksi men set otomatis dengan huruf depan TR + tanggal sekarang+random angka 

        queryHitungPendapatanHariIni();
        eventHandler();
        barcodeAction();

//        ((AbstractDocument) barcode.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        ((AbstractDocument) jumlah.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        ((AbstractDocument) totalHarga.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        ((AbstractDocument) nominal.getDocument()).setDocumentFilter(new CustomDocumentFilter());

    }

    void queryHitungPendapatanHariIni() {
        try {
            String query = "SELECT SUM(total_harga) t_harga FROM transaksi WHERE tanggal='" + Attr.getDateNow("yyyy-MM-dd") + "'";
            ResultSet res = queryResultSet(query);
            if (res.next()) {
                if (res.getString(1) == null) {
                    pendapatan.setText(Attr.kursIndo("0"));
                } else {
                    pendapatan.setText(Attr.kursIndo(res.getString(1)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void generateIdTrRandom() {
        idTransaksi.setText("TR" + Attr.getDateNow("yyyyMMdd") + Attr.generateRandomNumber(99999));
    }

    void emptyTextField() {
        barcode.setText(null);
        idBarang.setText(null);
        namaBarang.setText(null);
        harga.setText("0");
        jumlah.setText("0");
        nominal.setText("0");
        total.setText(Attr.kursIndo(Integer.toString(totalHargaBarang)));
        barcode.requestFocus();
    }

    void eventHandler() {
        jumlah.getDocument().addDocumentListener(Attr.jumlahDL(harga, jumlah, totalHarga));
    }

    void barcodeAction() {
        barcode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "SELECT * FROM barang WHERE barcode='" + barcode.getText() + "'";
                try {
                    ResultSet res = Database.queryResultSet(query);
                    if (res.next()) {
                        idBarang.setText(res.getString("id_barang"));
                        namaBarang.setText(res.getString("nama_barang"));
                        harga.setText(res.getString("harga_jual"));
                        harga_beli = res.getString("harga_beli");
                        jumlah.requestFocus();
                    }
                } catch (SQLException sqlE) {
                    JOptionPane.showMessageDialog(null, "Error: " + sqlE.getMessage());
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pendapatan = new javax.swing.JTextField();
        tanggal = new javax.swing.JTextField();
        idTransaksi = new javax.swing.JTextField();
        idBarang = new javax.swing.JTextField();
        barcode = new javax.swing.JTextField();
        kembalian = new javax.swing.JTextField();
        namaBarang = new javax.swing.JTextField();
        harga = new javax.swing.JTextField();
        jumlah = new javax.swing.JTextField();
        totalHarga = new javax.swing.JTextField();
        total = new javax.swing.JTextField();
        nominal = new javax.swing.JTextField();
        bayarBtn = new javax.swing.JPanel();
        tambahBtn = new javax.swing.JPanel();
        backBtn = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        cetakBtn = new javax.swing.JPanel();
        resetBtn = new javax.swing.JPanel();
        hapusBtn = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TUBES_PBO - Kasir");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pendapatan.setEditable(false);
        pendapatan.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        pendapatan.setForeground(new java.awt.Color(56, 57, 71));
        pendapatan.setBorder(null);
        pendapatan.setOpaque(false);
        getContentPane().add(pendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, 260, 20));

        tanggal.setEditable(false);
        tanggal.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        tanggal.setForeground(new java.awt.Color(56, 57, 71));
        tanggal.setBorder(null);
        tanggal.setOpaque(false);
        getContentPane().add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 260, 20));

        idTransaksi.setEditable(false);
        idTransaksi.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        idTransaksi.setForeground(new java.awt.Color(56, 57, 71));
        idTransaksi.setBorder(null);
        idTransaksi.setOpaque(false);
        getContentPane().add(idTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 260, 20));

        idBarang.setEditable(false);
        idBarang.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        idBarang.setForeground(new java.awt.Color(56, 57, 71));
        idBarang.setBorder(null);
        idBarang.setOpaque(false);
        getContentPane().add(idBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 260, 30));

        barcode.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        barcode.setForeground(new java.awt.Color(56, 57, 71));
        barcode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        barcode.setBorder(null);
        barcode.setOpaque(false);
        getContentPane().add(barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 810, 30));

        kembalian.setEditable(false);
        kembalian.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        kembalian.setForeground(new java.awt.Color(56, 57, 71));
        kembalian.setBorder(null);
        kembalian.setOpaque(false);
        getContentPane().add(kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 580, 260, 20));

        namaBarang.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        namaBarang.setForeground(new java.awt.Color(56, 57, 71));
        namaBarang.setBorder(null);
        namaBarang.setOpaque(false);
        getContentPane().add(namaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, 260, 30));

        harga.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        harga.setForeground(new java.awt.Color(56, 57, 71));
        harga.setBorder(null);
        harga.setOpaque(false);
        getContentPane().add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 250, 260, 30));

        jumlah.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        jumlah.setForeground(new java.awt.Color(56, 57, 71));
        jumlah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jumlah.setBorder(null);
        jumlah.setOpaque(false);
        getContentPane().add(jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 260, 30));

        totalHarga.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        totalHarga.setForeground(new java.awt.Color(56, 57, 71));
        totalHarga.setBorder(null);
        totalHarga.setOpaque(false);
        getContentPane().add(totalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, 260, 30));

        total.setEditable(false);
        total.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        total.setForeground(new java.awt.Color(56, 57, 71));
        total.setBorder(null);
        total.setOpaque(false);
        getContentPane().add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 400, 260, 20));

        nominal.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        nominal.setForeground(new java.awt.Color(56, 57, 71));
        nominal.setBorder(null);
        nominal.setOpaque(false);
        getContentPane().add(nominal, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 470, 260, 20));

        bayarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bayarBtn.setOpaque(false);
        bayarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bayarBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout bayarBtnLayout = new javax.swing.GroupLayout(bayarBtn);
        bayarBtn.setLayout(bayarBtnLayout);
        bayarBtnLayout.setHorizontalGroup(
            bayarBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        bayarBtnLayout.setVerticalGroup(
            bayarBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(bayarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 510, 260, 30));

        tambahBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tambahBtn.setOpaque(false);
        tambahBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tambahBtnLayout = new javax.swing.GroupLayout(tambahBtn);
        tambahBtn.setLayout(tambahBtnLayout);
        tambahBtnLayout.setHorizontalGroup(
            tambahBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        tambahBtnLayout.setVerticalGroup(
            tambahBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(tambahBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 320, 270, 30));

        backBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backBtn.setOpaque(false);
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backBtnLayout = new javax.swing.GroupLayout(backBtn);
        backBtn.setLayout(backBtnLayout);
        backBtnLayout.setHorizontalGroup(
            backBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        backBtnLayout.setVerticalGroup(
            backBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 30, 30));

        tabelBarang.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelBarang.setSelectionBackground(new java.awt.Color(229, 229, 229));
        tabelBarang.setSelectionForeground(new java.awt.Color(30, 30, 46));
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBarang);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 580, 230));

        cetakBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cetakBtn.setOpaque(false);
        cetakBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetakBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout cetakBtnLayout = new javax.swing.GroupLayout(cetakBtn);
        cetakBtn.setLayout(cetakBtnLayout);
        cetakBtnLayout.setHorizontalGroup(
            cetakBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        cetakBtnLayout.setVerticalGroup(
            cetakBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(cetakBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 630, 80, 30));

        resetBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resetBtn.setOpaque(false);
        resetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout resetBtnLayout = new javax.swing.GroupLayout(resetBtn);
        resetBtn.setLayout(resetBtnLayout);
        resetBtnLayout.setHorizontalGroup(
            resetBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        resetBtnLayout.setVerticalGroup(
            resetBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(resetBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 630, 80, 30));

        hapusBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hapusBtn.setOpaque(false);
        hapusBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapusBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout hapusBtnLayout = new javax.swing.GroupLayout(hapusBtn);
        hapusBtn.setLayout(hapusBtnLayout);
        hapusBtnLayout.setHorizontalGroup(
            hapusBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        hapusBtnLayout.setVerticalGroup(
            hapusBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(hapusBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 630, 70, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/kasir_page.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        this.setVisible(false);
        new Beranda().setVisible(true);
    }//GEN-LAST:event_backBtnMouseClicked

    private void tambahBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahBtnMouseClicked
        if (barcode.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan masukan barcode barang terlebih dahulu!");
        } else if (jumlah.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan masukan jumlah harga terlebih dahulu!");
        } else {
            boolean result = idBarangList.contains(idBarang.getText());

            if (!result) {
                idBarangList.add(barcode.getText());
                model.addRow(new Object[]{idBarang.getText(), namaBarang.getText(), jumlah.getText(), harga.getText(), harga_beli});
                tabelBarang.setModel(model);
                int lenght = model.getDataVector().size() - 1;
                String kuantitas = model.getValueAt(lenght, 2).toString();
                String hargaText = model.getValueAt(lenght, 3).toString();
                totalHargaBarang += ((Integer.parseInt(kuantitas) * Integer.parseInt(hargaText)));
                total.setText(Attr.kursIndo(Integer.toString(totalHargaBarang)));
            }
            emptyTextField();
        }
    }//GEN-LAST:event_tambahBtnMouseClicked

    private void resetBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetBtnMouseClicked
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin?");
        int rowsToRemove = model.getRowCount();
        if (confirm == 0) {
            for (int i = rowsToRemove - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            emptyTextField();
            idBarangList.removeAll(idBarangList);
            nominal.setText("");
            total.setText("");
            kembalian.setText("");
            no = 0;
            totalHargaBarang = 0;
            sqlInsertQuery = "INSERT INTO detail_transaksi VALUES ";
            generateIdTrRandom();
        }
    }//GEN-LAST:event_resetBtnMouseClicked

    private void tabelBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseClicked
        baris = tabelBarang.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_tabelBarangMouseClicked

    private void hapusBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusBtnMouseClicked
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data?");
        String idBarangText = tabelBarang.getValueAt(baris, 1).toString();
        String hargaBarang = tabelBarang.getValueAt(baris, 3).toString();
        String kuantitas = tabelBarang.getValueAt(baris, 2).toString();
        if (confirm == 0) {
            model.removeRow(baris);
            no--;
            totalHargaBarang -= (Integer.parseInt(hargaBarang) * Integer.parseInt(kuantitas));
            total.setText(Attr.kursIndo(Integer.toString(totalHargaBarang)));
            idBarangList.remove(idBarangText);
        }
        emptyTextField();
    }//GEN-LAST:event_hapusBtnMouseClicked

    private void bayarBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bayarBtnMouseClicked
        for (int i = 0; i < model.getDataVector().size(); i++) {
            String idBarang = model.getValueAt(i, 0).toString();
            String qty = model.getValueAt(i, 2).toString();
            String hargaBrng = model.getValueAt(i, 3).toString();
            if (i != 0) {
                sqlInsertQuery += ", ";
            }
            sqlInsertQuery += "(" + qty + ", '" + idTransaksi.getText() + "', '" + idBarang + "', " + hargaBrng + ", " + harga_beli + ")";
        }
        if (nominal.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nominal tidak boleh kosong");
        } else if (Integer.parseInt(nominal.getText()) < totalHargaBarang) {
            JOptionPane.showMessageDialog(null, "Nominal tidak boleh kurang dari total harga barang!");
        } else {
            try {
                String query = "SELECT * FROM transaksi WHERE id_transaksi='" + idTransaksi.getText() + "'";
                Connection conn = (Connection) Database.getConnection();
                ResultSet res = Database.queryResultSet(query);

                String queryUpdateTr = "UPDATE transaksi SET total_harga=" + totalHargaBarang + ", nominal=" + nominal.getText() + " "
                        + "WHERE id_transaksi='" + idTransaksi.getText() + "'";
                try {
                    conn.setAutoCommit(false);
                    if (!res.next()) {
                        String query2 = "INSERT INTO transaksi (id_transaksi, tanggal, id_user) VALUES ('" + idTransaksi.getText() + "', '" + Attr.getDateNow("yyyy-MM-dd") + "', '" + new User().getUser_id() + "')";
                        Database.queryExecute(query2);
                    }
                    Database.queryExecute(sqlInsertQuery);
                    Database.queryExecute(queryUpdateTr);

                    conn.commit();
                } catch (Exception e) {
                    conn.rollback();
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada saat menambahkan transaksi. Error:" + e.getMessage());
                }
                emptyTextField();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada saat mencari transaksi yang sama. Error: " + e.getMessage());
            }

            if (Integer.parseInt(nominal.getText()) == totalHargaBarang) {
                kembalian.setText(Attr.kursIndo("0"));
            } else if (Integer.parseInt(nominal.getText()) > totalHargaBarang) {
                int totalKembalian = Integer.parseInt(nominal.getText()) - totalHargaBarang;
                kembalian.setText(Attr.kursIndo(Integer.toString(totalKembalian)));
            }
        }
        queryHitungPendapatanHariIni();
    }//GEN-LAST:event_bayarBtnMouseClicked

    private void cetakBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakBtnMouseClicked
        try {
            File reportPath = new File("src/report/struk_kasir.jrxml");
            HashMap hashMap = new HashMap();
            hashMap.put("id_transaksi", idTransaksi.getText());
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath.getAbsolutePath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, Database.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Tidak dapat menampilkan JasperReport karena: " + e.getMessage());
        }
    }//GEN-LAST:event_cetakBtnMouseClicked

    public static void main(String args[]) {
        try {
            FlatArcIJTheme.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backBtn;
    private javax.swing.JTextField barcode;
    private javax.swing.JPanel bayarBtn;
    private javax.swing.JPanel cetakBtn;
    private javax.swing.JPanel hapusBtn;
    private javax.swing.JTextField harga;
    private javax.swing.JTextField idBarang;
    private javax.swing.JTextField idTransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField kembalian;
    private javax.swing.JTextField namaBarang;
    private javax.swing.JTextField nominal;
    private javax.swing.JTextField pendapatan;
    private javax.swing.JPanel resetBtn;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JPanel tambahBtn;
    private javax.swing.JTextField tanggal;
    private javax.swing.JTextField total;
    private javax.swing.JTextField totalHarga;
    // End of variables declaration//GEN-END:variables
}
