package app;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;

public class Bantuan extends javax.swing.JFrame {

    public Bantuan() {
        initComponents();
        tentangPanel.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tentangPanel = new javax.swing.JPanel();
        bantuanBtn = new javax.swing.JPanel();
        backBtn2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        bantuanPanel = new javax.swing.JPanel();
        tentangBtn = new javax.swing.JPanel();
        backBtn = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TUBES_PBO - Bantuan");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tentangPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
            .addGap(0, 130, Short.MAX_VALUE)
        );
        bantuanBtnLayout.setVerticalGroup(
            bantuanBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        tentangPanel.add(bantuanBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 33, 130, 40));

        backBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backBtn2.setOpaque(false);
        backBtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backBtn2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backBtn2Layout = new javax.swing.GroupLayout(backBtn2);
        backBtn2.setLayout(backBtn2Layout);
        backBtn2Layout.setHorizontalGroup(
            backBtn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        backBtn2Layout.setVerticalGroup(
            backBtn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        tentangPanel.add(backBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 30, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tentang_page.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        tentangPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -60, 1250, 820));

        getContentPane().add(tentangPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        bantuanPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tentangBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tentangBtn.setOpaque(false);
        tentangBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tentangBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tentangBtnLayout = new javax.swing.GroupLayout(tentangBtn);
        tentangBtn.setLayout(tentangBtnLayout);
        tentangBtnLayout.setHorizontalGroup(
            tentangBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );
        tentangBtnLayout.setVerticalGroup(
            tentangBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        bantuanPanel.add(tentangBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 33, 130, 40));

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

        bantuanPanel.add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 30, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tentang_page.png"))); // NOI18N
        bantuanPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 700));

        getContentPane().add(bantuanPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseClicked
        this.setVisible(false);
        new Beranda().setVisible(true);
    }//GEN-LAST:event_backBtnMouseClicked

    private void tentangBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tentangBtnMouseClicked
        tentangPanel.setVisible(true);
    }//GEN-LAST:event_tentangBtnMouseClicked

    private void backBtn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtn2MouseClicked
        this.setVisible(false);
        new Beranda().setVisible(true);
    }//GEN-LAST:event_backBtn2MouseClicked

    private void bantuanBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bantuanBtnMouseClicked
        tentangPanel.setVisible(false);
    }//GEN-LAST:event_bantuanBtnMouseClicked

    public static void main(String args[]) {
        try {
            FlatArcIJTheme.setup();
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bantuan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backBtn;
    private javax.swing.JPanel backBtn2;
    private javax.swing.JPanel bantuanBtn;
    private javax.swing.JPanel bantuanPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel tentangBtn;
    private javax.swing.JPanel tentangPanel;
    // End of variables declaration//GEN-END:variables
}
