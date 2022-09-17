package app;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import java.sql.*;
import javax.swing.JOptionPane;
import utils.Database; ////mengimport class database dari package utils 
import utils.User; ////mengimport class user dari package utils 

public class Login extends javax.swing.JFrame { //class login extends jframe bawaan dari netbeans jika kita mendesain menggunakan javax.swing
    //Encapsulation
    private boolean isShowPassword = false; //atribut
    //Private berarti bahwa pengaksesan suatu variabel instan  hanya
    //dapat dilakukan didalam kelas itu saja, tidak bisa diakses diluar kelas.
    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        loginBtn = new javax.swing.JPanel();
        usernameField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        showPwBtn = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TUBES_PBO - Login");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginBtn.setOpaque(false);
        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout loginBtnLayout = new javax.swing.GroupLayout(loginBtn);
        loginBtn.setLayout(loginBtnLayout);
        loginBtnLayout.setHorizontalGroup(
            loginBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        loginBtnLayout.setVerticalGroup(
            loginBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 490, 330, 40));

        usernameField.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        usernameField.setForeground(new java.awt.Color(48, 45, 65));
        usernameField.setBorder(null);
        usernameField.setOpaque(false);
        jPanel1.add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, 310, 30));

        passwordField.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        passwordField.setForeground(new java.awt.Color(48, 45, 65));
        passwordField.setBorder(null);
        passwordField.setOpaque(false);
        jPanel1.add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 420, 270, 40));

        showPwBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        showPwBtn.setOpaque(false);
        showPwBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showPwBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout showPwBtnLayout = new javax.swing.GroupLayout(showPwBtn);
        showPwBtn.setLayout(showPwBtnLayout);
        showPwBtnLayout.setHorizontalGroup(
            showPwBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        showPwBtnLayout.setVerticalGroup(
            showPwBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(showPwBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 420, 30, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/login_page.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void loginBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnMouseClicked
        if (usernameField.getText().equals("")) { //jika user menginputkan username namun isinya kosong
            JOptionPane.showMessageDialog(null, "Username tidak boleh kosong!"); //maka akan menampilkan message 
        } else if (passwordField.getText().equals("")) { //jika user menginputkan password kosong
            JOptionPane.showMessageDialog(null, "Password tidak boleh kosong!");//maka akan menampilkan pesan 
        } else if (passwordField.getText().length() < 8) { //jika user menginputkan password < 8 
            JOptionPane.showMessageDialog(null, "Password tidak boleh kurang dari 8 karakter!"); //maka akan menampilkan 
        } else {
            try { //exception handling /(error handling
                String username = usernameField.getText().toLowerCase(); //atribut username mempunyai isi inputan username dari user
                String password = passwordField.getText(); ////atribut password mempunyai isi inputan password dari user
                
                String query = "SELECT * FROM users WHERE username='" + username + "'"; //variabel query digunakan untuk menampilkan tabel user menggunakan keyword username yng dimasukan user 
                ResultSet result = Database.queryResultSet(query); //untuk mengeset hasil dari database

                if (result.next()) {
                    if (password.equals(result.getString("password_user"))) {
                        new User().setUser_id(result.getString("id_user"));
                        new User().setUser_fullname(result.getString("nama_user"));
                        new User().setUser_username(result.getString("username"));
                        new User().setUser_role(result.getString("role"));
                        this.setVisible(false);
                        new Beranda().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Password salah!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Username salah!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_loginBtnMouseClicked

    private void showPwBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPwBtnMouseClicked
        if (isShowPassword) { //method untuk menampilkan password,
            isShowPassword = false; //jika isshowpassword(btn) false atau tidak ditekan 
            passwordField.setEchoChar('*');//maka secara default password akan disembunyikan menggunakan tanda *
        } else {
            isShowPassword = true; //jika isShowpassword true/ditekan maka 
            passwordField.setEchoChar((char) 0);//maka akan mengecset character mejadi tampil atau menampilkan text yang diketikan di password
        }
    }//GEN-LAST:event_showPwBtnMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            FlatArcIJTheme.setup();
        } catch (Exception e) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel loginBtn;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPanel showPwBtn;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables

    private Object User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
