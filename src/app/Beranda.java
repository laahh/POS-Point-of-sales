package app;

import app.dashboard.Dashboard;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import utils.Database; //mengimport class database dari package utils 
import utils.User; //mengimport class user dari package utils 

public class Beranda extends javax.swing.JFrame {

    public Beranda() {
        initComponents();
        userGreeting.setText("Halo, " + new User().getUser_fullname());
        profilePanel.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        profilePanel = new javax.swing.JPanel();
        userUsername = new javax.swing.JLabel();
        presensi = new javax.swing.JLabel();
        userFullname = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JPanel();
        backBtn = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        bantuanBtn = new javax.swing.JPanel();
        dashboardBtn = new javax.swing.JPanel();
        riwayatTrBtn = new javax.swing.JPanel();
        kasirBtn = new javax.swing.JPanel();
        profilePic = new javax.swing.JLabel();
        userGreeting = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TUBES_PBO - Beranda");
        setBackground(new java.awt.Color(241, 245, 249));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(241, 245, 249));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profilePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userUsername.setFont(new java.awt.Font("Quicksand Medium", 0, 20)); // NOI18N
        userUsername.setForeground(new java.awt.Color(196, 196, 196));
        userUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userUsername.setText("jLabel3");
        profilePanel.add(userUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 354, 300, -1));

        presensi.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        presensi.setForeground(new java.awt.Color(30, 30, 46));
        presensi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        presensi.setText("Jlabel");
        profilePanel.add(presensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 390, 310, -1));

        userFullname.setFont(new java.awt.Font("Quicksand", 1, 20)); // NOI18N
        userFullname.setForeground(new java.awt.Color(30, 30, 46));
        userFullname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userFullname.setText("jLabel3");
        profilePanel.add(userFullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 322, 310, -1));

        logoutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutBtn.setOpaque(false);
        logoutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout logoutBtnLayout = new javax.swing.GroupLayout(logoutBtn);
        logoutBtn.setLayout(logoutBtnLayout);
        logoutBtnLayout.setHorizontalGroup(
            logoutBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        logoutBtnLayout.setVerticalGroup(
            logoutBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        profilePanel.add(logoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, 90, 30));

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
            .addGap(0, 90, Short.MAX_VALUE)
        );
        backBtnLayout.setVerticalGroup(
            backBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        profilePanel.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 430, 90, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/profile_page.png"))); // NOI18N
        profilePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(profilePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        bantuanBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bantuanBtn.setOpaque(false);
        bantuanBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bantuanBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout bantuanBtnLayout = new javax.swing.GroupLayout(bantuanBtn);
        bantuanBtn.setLayout(bantuanBtnLayout);
        bantuanBtnLayout.setHorizontalGroup(
            bantuanBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        bantuanBtnLayout.setVerticalGroup(
            bantuanBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        jPanel1.add(bantuanBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 460, 220, 120));

        dashboardBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardBtn.setOpaque(false);
        dashboardBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout dashboardBtnLayout = new javax.swing.GroupLayout(dashboardBtn);
        dashboardBtn.setLayout(dashboardBtnLayout);
        dashboardBtnLayout.setHorizontalGroup(
            dashboardBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        dashboardBtnLayout.setVerticalGroup(
            dashboardBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        jPanel1.add(dashboardBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 460, 220, 120));

        riwayatTrBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        riwayatTrBtn.setOpaque(false);
        riwayatTrBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                riwayatTrBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout riwayatTrBtnLayout = new javax.swing.GroupLayout(riwayatTrBtn);
        riwayatTrBtn.setLayout(riwayatTrBtnLayout);
        riwayatTrBtnLayout.setHorizontalGroup(
            riwayatTrBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        riwayatTrBtnLayout.setVerticalGroup(
            riwayatTrBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        jPanel1.add(riwayatTrBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 220, 120));

        kasirBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        kasirBtn.setOpaque(false);
        kasirBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kasirBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout kasirBtnLayout = new javax.swing.GroupLayout(kasirBtn);
        kasirBtn.setLayout(kasirBtnLayout);
        kasirBtnLayout.setHorizontalGroup(
            kasirBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        kasirBtnLayout.setVerticalGroup(
            kasirBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        jPanel1.add(kasirBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, 220, 120));

        profilePic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N
        profilePic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profilePic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilePicMouseClicked(evt);
            }
        });
        jPanel1.add(profilePic, new org.netbeans.lib.awtextra.AbsoluteConstraints(875, 40, -1, -1));

        userGreeting.setFont(new java.awt.Font("Quicksand Medium", 0, 16)); // NOI18N
        userGreeting.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        userGreeting.setText("Halo, Administrator");
        jPanel1.add(userGreeting, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 40, 150, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home_page.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        profilePanel.setVisible(false);
    }//GEN-LAST:event_backBtnMouseClicked

    private void logoutBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutBtnMouseClicked
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin keluar?");
        if (confirm == 0) {
            new User().setUser_fullname("");
            new User().setUser_id("");
            new User().setUser_role("");
            new User().setUser_username("");
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }//GEN-LAST:event_logoutBtnMouseClicked

    private void profilePicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilePicMouseClicked
        profilePanel.setVisible(true);
        userUsername.setText("@" + new User().getUser_username());
        try {
            int no = 1;
            String query = "SELECT id_user, tanggal FROM transaksi WHERE id_user='" + new User().getUser_id() + "' AND MONTH(tanggal)=MONTH(CURDATE()) GROUP BY tanggal";
            ResultSet result = Database.queryResultSet(query);
            while(result.next()) {
                no++;
            }
            userFullname.setText(new User().getUser_fullname());
            userUsername.setText("@" + new User().getUser_username());
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_profilePicMouseClicked

    private void kasirBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kasirBtnMouseClicked
        this.setVisible(false);//ketika user mengklik btn kasir maka tidak akan menampilkan kelas beranda
        new Kasir().setVisible(true);//kemudian akan menampilkan kelas kasir
    }//GEN-LAST:event_kasirBtnMouseClicked

    private void riwayatTrBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_riwayatTrBtnMouseClicked
        this.setVisible(false);//ketika user mengklik btn riwayat maka tidak akan menampilkan kelas beranda
        new RiwayatTransaksi().setVisible(true);//kemudian akan menampilkan kelas riwayat 
    }//GEN-LAST:event_riwayatTrBtnMouseClicked

    private void dashboardBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardBtnMouseClicked
        if (!new User().getUser_role().equals("Administrator")) { //jika user yang masuk bukan role admin
            JOptionPane.showMessageDialog(null, "Anda tidak memiliki akses untuk menggunakan form ini!"); //maka akna menampilkan pessan
        } else {
            this.setVisible(false);//jika role admin maka tidak akan menampilkan kelas beranda
            new Dashboard().setVisible(true);//kemudian akan menampilkan kelas dashoard
        }
    }//GEN-LAST:event_dashboardBtnMouseClicked

    private void bantuanBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bantuanBtnMouseClicked
        this.setVisible(false); //ketika user mengklik btn bantuan maka tidak akan menampilkan kelas beranda
        new Bantuan().setVisible(true); ////kemudian akan menampilkan kelas bantuan
    }//GEN-LAST:event_bantuanBtnMouseClicked

    public static void main(String args[]) {
        try {
            FlatArcIJTheme.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Beranda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backBtn;
    private javax.swing.JPanel bantuanBtn;
    private javax.swing.JPanel dashboardBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel kasirBtn;
    private javax.swing.JPanel logoutBtn;
    private javax.swing.JLabel presensi;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JLabel profilePic;
    private javax.swing.JPanel riwayatTrBtn;
    private javax.swing.JLabel userFullname;
    private javax.swing.JLabel userGreeting;
    private javax.swing.JLabel userUsername;
    // End of variables declaration//GEN-END:variables
}
