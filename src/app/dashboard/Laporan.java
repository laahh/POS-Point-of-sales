package app.dashboard;

import app.Beranda;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import utils.Attr;
import utils.Database;
import utils.Model;

public class Laporan extends javax.swing.JFrame {
    int omsetBulanIni = 0;
    int omsetTahunIni = 0;
    Date date = new Date();
    
    public Laporan() {
        initComponents();
        
        Model.table(tabelAnalisaBarangBulan);
        Model.table(tabelAnalisaBarangTahun);
        
        Attr.showDataAnalisa("MONTH", tabelAnalisaBarangBulan);
        Attr.showDataAnalisa("YEAR", tabelAnalisaBarangTahun);
        omsetBulanIni = Attr.hitungOmset(omsetBulan, "MONTH");
        omsetTahunIni = Attr.hitungOmset(omsetTahun, "YEAR");
        
        keuntungan();
        keuntungan2();
        
    }
    
    void keuntungan() {
        try {
            String query = "SELECT SUM((qty * harga_beli)) harga FROM detail_transaksi WHERE id_transaksi LIKE '%TR2022%'";
            ResultSet result = Database.queryResultSet(query);
            if (result.next()) {
                int keuntungan = omsetTahunIni - result.getInt(1);
                keuntunganTahun.setText(Attr.kursIndo(Integer.toString(keuntungan)));
            }
        } catch (Exception e) {
        }
    }
    
    void keuntungan2() {
        try {
            String query = "SELECT SUM((qty * harga_beli)) harga FROM detail_transaksi WHERE id_transaksi LIKE '%TR" + new SimpleDateFormat("yyyyMM").format(date) + "%'";
            ResultSet result = Database.queryResultSet(query);
            if (result.next()) {
                int keuntungan = omsetBulanIni - result.getInt(1);
                keuntunganBulan.setText(Attr.kursIndo(Integer.toString(keuntungan)));
            }
        } catch (Exception e) {
        }
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel11 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        cetakLaporanTahunBtn1 = new javax.swing.JPanel();
        cetakLaporanTahunBtn = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelAnalisaBarangTahun = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelAnalisaBarangBulan = new javax.swing.JTable();
        keuntunganTahun = new javax.swing.JLabel();
        omsetTahun = new javax.swing.JLabel();
        keuntunganBulan = new javax.swing.JLabel();
        omsetBulan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TUBES_PBO- Dashboard Laporan");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel11.setOpaque(false);
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 190, 40));

        jPanel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel10.setOpaque(false);
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, 190, 40));

        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel9.setOpaque(false);
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 190, 40));

        jPanel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel8.setOpaque(false);
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 190, 30));

        jPanel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel7.setOpaque(false);
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 190, 40));

        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel6.setOpaque(false);
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 190, 40));

        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.setOpaque(false);
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 190, 30));

        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.setOpaque(false);
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 190, 40));

        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.setOpaque(false);
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 190, 40));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cetakLaporanTahunBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cetakLaporanTahunBtn1.setOpaque(false);
        cetakLaporanTahunBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetakLaporanTahunBtn1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout cetakLaporanTahunBtn1Layout = new javax.swing.GroupLayout(cetakLaporanTahunBtn1);
        cetakLaporanTahunBtn1.setLayout(cetakLaporanTahunBtn1Layout);
        cetakLaporanTahunBtn1Layout.setHorizontalGroup(
            cetakLaporanTahunBtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        cetakLaporanTahunBtn1Layout.setVerticalGroup(
            cetakLaporanTahunBtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2.add(cetakLaporanTahunBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 90, 30));

        cetakLaporanTahunBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cetakLaporanTahunBtn.setOpaque(false);
        cetakLaporanTahunBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetakLaporanTahunBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout cetakLaporanTahunBtnLayout = new javax.swing.GroupLayout(cetakLaporanTahunBtn);
        cetakLaporanTahunBtn.setLayout(cetakLaporanTahunBtnLayout);
        cetakLaporanTahunBtnLayout.setHorizontalGroup(
            cetakLaporanTahunBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        cetakLaporanTahunBtnLayout.setVerticalGroup(
            cetakLaporanTahunBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2.add(cetakLaporanTahunBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 900, 90, 30));

        tabelAnalisaBarangTahun.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tabelAnalisaBarangTahun);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 990, 600, 350));

        tabelAnalisaBarangBulan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabelAnalisaBarangBulan);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 600, 350));

        keuntunganTahun.setFont(new java.awt.Font("Quicksand", 1, 20)); // NOI18N
        keuntunganTahun.setForeground(new java.awt.Color(255, 255, 255));
        keuntunganTahun.setText("Rp. 0,00");
        jPanel2.add(keuntunganTahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 830, -1, -1));

        omsetTahun.setFont(new java.awt.Font("Quicksand", 1, 20)); // NOI18N
        omsetTahun.setForeground(new java.awt.Color(255, 255, 255));
        omsetTahun.setText("Rp. 0,00");
        jPanel2.add(omsetTahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 830, -1, -1));

        keuntunganBulan.setFont(new java.awt.Font("Quicksand", 1, 20)); // NOI18N
        keuntunganBulan.setForeground(new java.awt.Color(255, 255, 255));
        keuntunganBulan.setText("Rp. 0,00");
        jPanel2.add(keuntunganBulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 150, -1, -1));

        omsetBulan.setFont(new java.awt.Font("Quicksand", 1, 20)); // NOI18N
        omsetBulan.setForeground(new java.awt.Color(255, 255, 255));
        omsetBulan.setText("Rp. 0,00");
        jPanel2.add(omsetBulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 150, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/laporan_panel.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jScrollPane1.setViewportView(jPanel2);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 750, 700));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/laporan_page.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 750, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        this.setVisible(false);
        new Beranda().setVisible(true);
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        this.setVisible(false);
        try {
            new Pengeluaran().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        this.setVisible(false);
        try {
            new TransaksiBeli().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        this.setVisible(false);
        new TransaksiJual().setVisible(true);
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        this.setVisible(false);
        new Kategori().setVisible(true);
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        this.setVisible(false);
        new Suplier().setVisible(true);
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        this.setVisible(false);
        try {
            new Pengguna().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        this.setVisible(false);
        new Barang().setVisible(true);
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        this.setVisible(false);
        new Dashboard().setVisible(true);
    }//GEN-LAST:event_jPanel3MouseClicked

    private void cetakLaporanTahunBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakLaporanTahunBtn1MouseClicked
        try {
            File reportPath = new File("src/report/laporan.jrxml");
            HashMap hashMap = new HashMap();
            hashMap.put("keuntungan", keuntunganBulan.getText());
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath.getAbsolutePath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, Database.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Tidak dapat menampilkan JasperReport karena: " + e.getMessage());
        }
    }//GEN-LAST:event_cetakLaporanTahunBtn1MouseClicked

    private void cetakLaporanTahunBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakLaporanTahunBtnMouseClicked
        try {
            File reportPath = new File("src/report/laporan_tahunan.jrxml");
            HashMap hashMap = new HashMap();
            hashMap.put("keuntungan", keuntunganTahun.getText());
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath.getAbsolutePath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, Database.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Tidak dapat menampilkan JasperReport karena: " + e.getMessage());
        }
    }//GEN-LAST:event_cetakLaporanTahunBtnMouseClicked

    public static void main(String args[]) {
        try {
            FlatArcIJTheme.setup();
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cetakLaporanTahunBtn;
    private javax.swing.JPanel cetakLaporanTahunBtn1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel keuntunganBulan;
    private javax.swing.JLabel keuntunganTahun;
    private javax.swing.JLabel omsetBulan;
    private javax.swing.JLabel omsetTahun;
    private javax.swing.JTable tabelAnalisaBarangBulan;
    private javax.swing.JTable tabelAnalisaBarangTahun;
    // End of variables declaration//GEN-END:variables
}
