package dangnhap_dangky;

import Staff_functions.menu_staff;
import java.nio.charset.StandardCharsets;
import tab_main.phanquyennguoidung;
import java.sql.*;
import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import tab_main.DatabaseConnection;



public class login_staff extends javax.swing.JFrame {

    
    public login_staff() {
        initComponents();
    }
    
    private String hashPassword(String password) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // Sử dụng UTF-16LE để khớp với SQL Server
        byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_16LE));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02X", b)); // Hex dạng in hoa
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        return null;
    }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enter_text = new javax.swing.JTextField();
        button_exit = new javax.swing.JButton();
        enter_pass = new javax.swing.JPasswordField();
        label_pass = new javax.swing.JLabel();
        Button_login = new javax.swing.JButton();
        label_pass1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        backgound = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(enter_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 180, 30));

        button_exit.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        button_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagine/sign-in-alt (3).png"))); // NOI18N
        button_exit.setToolTipText("");
        button_exit.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.white, java.awt.Color.darkGray));
        button_exit.setContentAreaFilled(false);
        button_exit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        button_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_exitActionPerformed(evt);
            }
        });
        getContentPane().add(button_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 60, 60));
        getContentPane().add(enter_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 180, 30));

        label_pass.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_pass.setForeground(new java.awt.Color(255, 255, 255));
        label_pass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_pass.setText("Tên đăng nhập:");
        getContentPane().add(label_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 290, 30));

        Button_login.setBackground(new java.awt.Color(102, 102, 102));
        Button_login.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Button_login.setForeground(new java.awt.Color(255, 255, 255));
        Button_login.setText("Đăng nhập");
        Button_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_loginActionPerformed(evt);
            }
        });
        getContentPane().add(Button_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, 130, 30));

        label_pass1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_pass1.setForeground(new java.awt.Color(255, 255, 255));
        label_pass1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_pass1.setText("Mật khẩu:");
        getContentPane().add(label_pass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 290, 30));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Hệ thống quản lí kho");
        jLabel4.setOpaque(true);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 390, 70));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 390, 370));

        backgound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagine/background.jpg"))); // NOI18N
        getContentPane().add(backgound, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_exitActionPerformed
        // TODO add your handling code here:
        new phanquyennguoidung().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_button_exitActionPerformed

    private void Button_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_loginActionPerformed
        // TODO add your handling code here:
        String usernameInput = new String(enter_text.getText()); // Lấy tên đăng nhập
    String passwordInput = new String(enter_pass.getPassword()); // Lấy mật khẩu

    String hashedPassword = hashPassword(passwordInput); // Mã hóa mật khẩu đầu vào

    if (hashedPassword == null) {
        JOptionPane.showMessageDialog(this, "Lỗi trong quá trình mã hóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try (Connection conn = DatabaseConnection.getConnection()) {
        String sql = "SELECT password FROM dbo.EmployeeList WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, usernameInput); // Đặt tên đăng nhập vào câu truy vấn
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Lấy mật khẩu đã mã hóa dưới dạng VARBINARY
            byte[] storedHashedPasswordBytes = rs.getBytes("password");
            // Chuyển đổi byte[] thành chuỗi hex
            String storedHashedPassword = bytesToHex(storedHashedPasswordBytes);

            // Kiểm tra mật khẩu nhập vào với mật khẩu đã lưu trong cơ sở dữ liệu
            if (hashedPassword.equalsIgnoreCase(storedHashedPassword.trim())) {
                new menu_staff().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Sai mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản với tên đăng nhập này!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_Button_loginActionPerformed
   
    public static String bytesToHex(byte[] bytes) {
    StringBuilder hexString = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
        String hex = Integer.toHexString(0xff & bytes[i]);
        if (hex.length() == 1) {
            hexString.append('0');
        }
        hexString.append(hex);
    }
    return hexString.toString().toUpperCase();
}
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login_staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login_staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login_staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login_staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login_staff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_login;
    private javax.swing.JLabel backgound;
    private javax.swing.JButton button_exit;
    private javax.swing.JPasswordField enter_pass;
    private javax.swing.JTextField enter_text;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel label_pass;
    private javax.swing.JLabel label_pass1;
    // End of variables declaration//GEN-END:variables
}
