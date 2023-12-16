private void LoginActionPerformed(java.awt.event.ActionEvent evt) {                                      
    try {
        String sql = "SELECT * FROM user WHERE id = '" + jTextPane1.getText() + "' AND nama = '" + jTextPane2.getText() + "'";
        java.sql.Connection conn = config.configDB();
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        java.sql.ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            int idUser = rs.getInt("id");
            String namaUser = rs.getString("nama");
            
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "Login berhasil");
                login.this.setVisible(false);
                
                // Contoh penggunaan metode saveUser
                DatabaseManager.saveUser(idUser, namaUser);
                
                // Contoh penggunaan metode getAllUser
                DatabaseManager.getAllUser();
                
                // Ganti dengan nama kelas yang benar
                new NamaKelasYangBenar().setVisible(true);
            });
        } else {
            JOptionPane.showMessageDialog(null, "Login ulang");
        }

    } catch (SQLException ex) {
        Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage());
    }
}
