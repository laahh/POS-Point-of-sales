package app;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import java.io.File;
import java.sql.*;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import utils.*;

public class RiwayatTransaksi extends javax.swing.JFrame {

    public RiwayatTransaksi() {
        initComponents();
        Model.table(tabelTransaksi);
        showDataTable();

        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFieldState();
            }

            protected void updateFieldState() {
                DefaultTableModel model = new DefaultTableModel();

                model.addColumn("No");
                model.addColumn("Id Transaksi");
                model.addColumn("Id User");
                model.addColumn("Tanggal");
                model.addColumn("Total Harga");

                int no = 1;

                try {
                    String query = "SELECT * FROM transaksi WHERE id_transaksi LIKE '%" + cariTransaksi.getText() + "%'";
                    ResultSet res = Database.queryResultSet(query);
                    while (res.next()) {
                        model.addRow(new Object[]{no++, res.getString("id_transaksi"), res.getString("id_user"), res.getString("tanggal"), res.getString("total_harga")});
                    }
                    tabelTransaksi.setModel(model);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        };

        cariTransaksi.getDocument().addDocumentListener(dl);
    }

    void showDataTable() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("No");
        model.addColumn("Id Transaksi");
        model.addColumn("Id User");
        model.addColumn("Tanggal");
        model.addColumn("Total Harga");

        int no = 1;
        String sql = "SELECT * FROM transaksi";
        try {
            ResultSet res = Database.queryResultSet(sql);
            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString("id_transaksi"), res.getString("id_user"), res.getString("tanggal"), res.getString("total_harga")});
            }
            tabelTransaksi.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelTransaksi = new javax.swing.JTable();
        backBtn = new javax.swing.JPanel();
        cariTransaksi = new javax.swing.JTextField();
        cetakBtn = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        tabelTransaksi.setFont(new java.awt.Font("Quicksand Medium", 0, 13)); // NOI18N
        tabelTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelTransaksi.setSelectionBackground(new java.awt.Color(229, 229, 229));
        tabelTransaksi.setSelectionForeground(new java.awt.Color(30, 30, 46));
        jScrollPane1.setViewportView(tabelTransaksi);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RePOSin - Riwayat Transaksi");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        cariTransaksi.setFont(new java.awt.Font("Quicksand Medium", 0, 14)); // NOI18N
        cariTransaksi.setForeground(new java.awt.Color(56, 57, 71));
        cariTransaksi.setBorder(null);
        cariTransaksi.setOpaque(false);
        cariTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariTransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(cariTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 139, 180, 30));

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
            .addGap(0, 90, Short.MAX_VALUE)
        );
        cetakBtnLayout.setVerticalGroup(
            cetakBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(cetakBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 140, 90, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/riwayat_transaksi_page.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        this.setVisible(false);
        new Beranda().setVisible(true);
    }//GEN-LAST:event_backBtnMouseClicked

    private void cetakBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakBtnMouseClicked
        try {
            File reportPath = new File("src/report/laporan_riwayat_transaksi.jrxml");
            HashMap hashMap = new HashMap();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath.getAbsolutePath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, Database.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Tidak dapat menampilkan JasperReport karena: " + e.getMessage());
        }
    }//GEN-LAST:event_cetakBtnMouseClicked

    private void cariTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariTransaksiActionPerformed

    public static void main(String args[]) {
        try {
            FlatArcIJTheme.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RiwayatTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backBtn;
    private javax.swing.JTextField cariTransaksi;
    private javax.swing.JPanel cetakBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelTransaksi;
    // End of variables declaration//GEN-END:variables
}
