package app.dashboard;

import app.Beranda;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import java.awt.BorderLayout;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import utils.*;

public class Dashboard extends javax.swing.JFrame {
    
    public Dashboard() {
        initComponents();
        hitungDataBarang();
        hitungDataPengguna();
        hitungDataTransaksi();
        showDataBarChart();
    }
    
    private void hitungDataBarang() {
        try {
            String query = "SELECT COUNT(*) jumlah_barang FROM barang";
            ResultSet rst = Database.queryResultSet(query); //mengeset hasil dari atribut query disimpan di rst
            if (rst.next()) {
                dataBarangVal.setText(rst.getString("jumlah_barang"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
    }
    
    private void hitungDataPengguna() {
        try {
            String query = "SELECT COUNT(*) jumlah_pengguna FROM users";////variabel query digunakan untuk menampilkanjumlah pengguna dari tabrl user 
            ResultSet rst = Database.queryResultSet(query); ////mengeset hasil dari atribut query disimpan di rst
            if (rst.next()) {
                dataPenggunaVal.setText(rst.getString("jumlah_pengguna"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
    }
    
    private void hitungDataTransaksi() {
        try {
            String query = "SELECT COUNT(*) jumlah_transaksi FROM transaksi";
            String query2 = "SELECT COUNT(*) jumlah_transaksi FROM pemasokan";
            ResultSet rst = Database.queryResultSet(query);
            ResultSet rst2 = Database.queryResultSet(query2);
            if (rst.next()) {
                int jml_transaksi = rst.getInt(1);
                if (rst2.next()) {
                    int jml_pemasokan = rst2.getInt(1);
                    dataTransaksiVal.setText(Integer.toString(jml_pemasokan + jml_transaksi));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        
    }
    
    private void showDataBarChart() {
        DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
        barChartData.setValue(Database.queryHitungPendapatanPerBulan("01"), "Pendapatan", "Januari");
        barChartData.setValue(Database.queryHitungPendapatanPerBulan("02"), "Pendapatan", "Februari");
        barChartData.setValue(Database.queryHitungPendapatanPerBulan("03"), "Pendapatan", "Maret");
        barChartData.setValue(Database.queryHitungPendapatanPerBulan("04"), "Pendapatan", "April");
        barChartData.setValue(Database.queryHitungPendapatanPerBulan("05"), "Pendapatan", "Mei");
        barChartData.setValue(Database.queryHitungPendapatanPerBulan("06"), "Pendapatan", "Juni");
        barChartData.setValue(Database.queryHitungPendapatanPerBulan("07"), "Pendapatan", "Juli");
        barChartData.setValue(Database.queryHitungPendapatanPerBulan("08"), "Pendapatan", "Agustus");
        barChartData.setValue(Database.queryHitungPendapatanPerBulan("09"), "Pendapatan", "September");
        barChartData.setValue(Database.queryHitungPendapatanPerBulan("10"), "Pendapatan", "Oktober");
        barChartData.setValue(Database.queryHitungPendapatanPerBulan("11"), "Pendapatan", "November");
        barChartData.setValue(Database.queryHitungPendapatanPerBulan("12"), "Pendapatan", "Desember");
        JFreeChart barChart = ChartFactory.createBarChart("Grafik Pendapatan Perbulan Tahun " + Attr.getDateNow("yyyy"), "Bulan", "Tingkat Pendapatan (Rp.)", barChartData, PlotOrientation.VERTICAL, true, true, true);
        barChart.getTitle().setFont(Attr.quicksandBold(24));
        ChartPanel cp = new ChartPanel(barChart);
        barChartPanel.removeAll();
        barChartPanel.setLayout(new BorderLayout());
        barChartPanel.add(cp, BorderLayout.CENTER);
        barChartPanel.updateUI();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        dataBarangVal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        dataPenggunaVal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        dataTransaksiVal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        barChartPanel = new javax.swing.JPanel();
        barangBtn = new javax.swing.JPanel();
        penggunaBtn = new javax.swing.JPanel();
        kategoriBtn = new javax.swing.JPanel();
        suplierBtn = new javax.swing.JPanel();
        homeBtn = new javax.swing.JPanel();
        pengeluaranBtn = new javax.swing.JPanel();
        laporanBtn = new javax.swing.JPanel();
        transBeliBtn = new javax.swing.JPanel();
        transJualBtn = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TUBES_PBO - Dashboard");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Quicksand SemiBold", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(30, 30, 46));
        jLabel2.setText("Dashboard");

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dataBarangVal.setFont(new java.awt.Font("Quicksand SemiBold", 0, 24)); // NOI18N
        dataBarangVal.setForeground(new java.awt.Color(255, 255, 255));
        dataBarangVal.setText("0");
        jPanel2.add(dataBarangVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/data_barang_card.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dataPenggunaVal.setFont(new java.awt.Font("Quicksand SemiBold", 0, 24)); // NOI18N
        dataPenggunaVal.setForeground(new java.awt.Color(255, 255, 255));
        dataPenggunaVal.setText("0");
        jPanel3.add(dataPenggunaVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/data_pengguna_card.png"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dataTransaksiVal.setFont(new java.awt.Font("Quicksand SemiBold", 0, 24)); // NOI18N
        dataTransaksiVal.setForeground(new java.awt.Color(255, 255, 255));
        dataTransaksiVal.setText("0");
        jPanel4.add(dataTransaksiVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/data_transaksi_card.png"))); // NOI18N
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout barChartPanelLayout = new javax.swing.GroupLayout(barChartPanel);
        barChartPanel.setLayout(barChartPanelLayout);
        barChartPanelLayout.setHorizontalGroup(
            barChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        barChartPanelLayout.setVerticalGroup(
            barChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(barChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(barChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 750, 700));

        barangBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        barangBtn.setOpaque(false);
        barangBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                barangBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout barangBtnLayout = new javax.swing.GroupLayout(barangBtn);
        barangBtn.setLayout(barangBtnLayout);
        barangBtnLayout.setHorizontalGroup(
            barangBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        barangBtnLayout.setVerticalGroup(
            barangBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(barangBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 190, 40));

        penggunaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        penggunaBtn.setOpaque(false);
        penggunaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                penggunaBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout penggunaBtnLayout = new javax.swing.GroupLayout(penggunaBtn);
        penggunaBtn.setLayout(penggunaBtnLayout);
        penggunaBtnLayout.setHorizontalGroup(
            penggunaBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        penggunaBtnLayout.setVerticalGroup(
            penggunaBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(penggunaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 190, 30));

        kategoriBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        kategoriBtn.setOpaque(false);
        kategoriBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kategoriBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout kategoriBtnLayout = new javax.swing.GroupLayout(kategoriBtn);
        kategoriBtn.setLayout(kategoriBtnLayout);
        kategoriBtnLayout.setHorizontalGroup(
            kategoriBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        kategoriBtnLayout.setVerticalGroup(
            kategoriBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(kategoriBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 190, 40));

        suplierBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        suplierBtn.setOpaque(false);
        suplierBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suplierBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout suplierBtnLayout = new javax.swing.GroupLayout(suplierBtn);
        suplierBtn.setLayout(suplierBtnLayout);
        suplierBtnLayout.setHorizontalGroup(
            suplierBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        suplierBtnLayout.setVerticalGroup(
            suplierBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(suplierBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 190, 40));

        homeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        homeBtn.setOpaque(false);
        homeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout homeBtnLayout = new javax.swing.GroupLayout(homeBtn);
        homeBtn.setLayout(homeBtnLayout);
        homeBtnLayout.setHorizontalGroup(
            homeBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        homeBtnLayout.setVerticalGroup(
            homeBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(homeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 190, 40));

        pengeluaranBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pengeluaranBtn.setOpaque(false);
        pengeluaranBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pengeluaranBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pengeluaranBtnLayout = new javax.swing.GroupLayout(pengeluaranBtn);
        pengeluaranBtn.setLayout(pengeluaranBtnLayout);
        pengeluaranBtnLayout.setHorizontalGroup(
            pengeluaranBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        pengeluaranBtnLayout.setVerticalGroup(
            pengeluaranBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(pengeluaranBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, 190, 40));

        laporanBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        laporanBtn.setOpaque(false);
        laporanBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laporanBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout laporanBtnLayout = new javax.swing.GroupLayout(laporanBtn);
        laporanBtn.setLayout(laporanBtnLayout);
        laporanBtnLayout.setHorizontalGroup(
            laporanBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        laporanBtnLayout.setVerticalGroup(
            laporanBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(laporanBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 190, 40));

        transBeliBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        transBeliBtn.setOpaque(false);
        transBeliBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transBeliBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout transBeliBtnLayout = new javax.swing.GroupLayout(transBeliBtn);
        transBeliBtn.setLayout(transBeliBtnLayout);
        transBeliBtnLayout.setHorizontalGroup(
            transBeliBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        transBeliBtnLayout.setVerticalGroup(
            transBeliBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(transBeliBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 444, 190, 40));

        transJualBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        transJualBtn.setOpaque(false);
        transJualBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transJualBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout transJualBtnLayout = new javax.swing.GroupLayout(transJualBtn);
        transJualBtn.setLayout(transJualBtnLayout);
        transJualBtnLayout.setHorizontalGroup(
            transJualBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        transJualBtnLayout.setVerticalGroup(
            transJualBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(transJualBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 388, 190, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dashboard_page.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void barangBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barangBtnMouseClicked
        this.setVisible(false);
        new Barang().setVisible(true);
    }//GEN-LAST:event_barangBtnMouseClicked

    private void penggunaBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penggunaBtnMouseClicked
        this.setVisible(false);
        try {
            new Pengguna().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_penggunaBtnMouseClicked

    private void suplierBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suplierBtnMouseClicked
        this.setVisible(false);
        new Suplier().setVisible(true);
    }//GEN-LAST:event_suplierBtnMouseClicked

    private void kategoriBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kategoriBtnMouseClicked
        this.setVisible(false);
        new Kategori().setVisible(true);
    }//GEN-LAST:event_kategoriBtnMouseClicked

    private void transJualBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transJualBtnMouseClicked
        this.setVisible(false);
        new TransaksiJual().setVisible(true);
    }//GEN-LAST:event_transJualBtnMouseClicked

    private void homeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeBtnMouseClicked
        this.setVisible(false);
        new Beranda().setVisible(true);
    }//GEN-LAST:event_homeBtnMouseClicked

    private void transBeliBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transBeliBtnMouseClicked
        this.setVisible(false);
        try {
            new TransaksiBeli().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_transBeliBtnMouseClicked

    private void pengeluaranBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pengeluaranBtnMouseClicked
        this.setVisible(false);
        try {
            new Pengeluaran().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pengeluaranBtnMouseClicked

    private void laporanBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanBtnMouseClicked
        this.setVisible(false);
        new Laporan().setVisible(true);
    }//GEN-LAST:event_laporanBtnMouseClicked

    public static void main(String args[]) {
        try {
            FlatArcIJTheme.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barChartPanel;
    private javax.swing.JPanel barangBtn;
    private javax.swing.JLabel dataBarangVal;
    private javax.swing.JLabel dataPenggunaVal;
    private javax.swing.JLabel dataTransaksiVal;
    private javax.swing.JPanel homeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel kategoriBtn;
    private javax.swing.JPanel laporanBtn;
    private javax.swing.JPanel pengeluaranBtn;
    private javax.swing.JPanel penggunaBtn;
    private javax.swing.JPanel suplierBtn;
    private javax.swing.JPanel transBeliBtn;
    private javax.swing.JPanel transJualBtn;
    // End of variables declaration//GEN-END:variables
}
