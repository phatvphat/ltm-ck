/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noname;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

/**
 *
 * @author phatv
 */
public class FileStorage extends javax.swing.JFrame {

    DefaultTableModel listFilesLocal;
    DefaultTableModel listFilesRemote;
    public NtlmPasswordAuthentication authRemote;
    public String urlRemote = "smb://192.168.1.123/GoFlex Home Public/laptrinhmang/";
    public SmbFile dirRemote;

    /**
     * Creates new form FileStorage
     */
    public FileStorage() {
        initComponents();

        listFilesLocal = (DefaultTableModel) tblFilesLocal.getModel();
        listFilesRemote = (DefaultTableModel) tblFilesRemote.getModel();

        authRemote = new NtlmPasswordAuthentication(null, "admin", "admin1");
        try {
            dirRemote = new SmbFile(urlRemote, authRemote); // gán folder
            ListFilesRemote();
        } catch (Exception ex) {
            Logger.getLogger(FileStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ListFilesLocal() {
        File dir = new File(txtPath.getText());
        File dsFile[] = dir.listFiles();
        if (dsFile != null) {
            listFilesLocal.setRowCount(0);
            for (int i = 0; i < dsFile.length; i++) {
                File f_temp = dsFile[i];
                if (f_temp.isFile()) {
                    listFilesLocal.addRow(new Object[]{f_temp.getName(), Utils.ValidateSize(f_temp.length())});
                }
            }
        }
    }

    public void ListFilesRemote() {
        try {
            listFilesRemote.setRowCount(0);
            for (SmbFile f : dirRemote.listFiles()) {
                if (f.isFile()) {
                    listFilesRemote.addRow(new Object[]{f.getName(), Utils.ValidateSize(f.length())});
                }
            }
        } catch (SmbException ex) {
            Logger.getLogger(FileStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fcPath = new javax.swing.JFileChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnChooseFolder = new javax.swing.JButton();
        txtPath = new javax.swing.JTextField();
        btnUpload = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnRenameFileRemote = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblFilesLocal = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFilesRemote = new javax.swing.JTable();
        btnOpenFileRemote = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        btnChooseFolder.setText("Chọn thư mục trên máy:");
        btnChooseFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseFolderActionPerformed(evt);
            }
        });

        txtPath.setEditable(false);

        btnUpload.setText("Upload >");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        jButton3.setText("< Download");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnRenameFileRemote.setText("Đổi tên >");
        btnRenameFileRemote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenameFileRemoteActionPerformed(evt);
            }
        });

        jButton5.setText("Xoá >");

        tblFilesLocal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Name", "Size"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFilesLocal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(tblFilesLocal);

        tblFilesRemote.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Name", "Size"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFilesRemote.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblFilesRemote);

        btnOpenFileRemote.setText("Mở file >");
        btnOpenFileRemote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenFileRemoteActionPerformed(evt);
            }
        });

        btnRefresh.setText("< Làm mới >");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRenameFileRemote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOpenFileRemote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnChooseFolder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChooseFolder)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUpload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOpenFileRemote)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRenameFileRemote)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseFolderActionPerformed
        fcPath.setVisible(true);
        fcPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (fcPath.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                txtPath.setText(fcPath.getSelectedFile().getCanonicalPath());
                ListFilesLocal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
    }//GEN-LAST:event_btnChooseFolderActionPerformed

    private void btnOpenFileRemoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenFileRemoteActionPerformed
        if (tblFilesRemote.getSelectedRow() > -1) {
            String fname = String.valueOf(listFilesRemote.getValueAt(tblFilesRemote.getSelectedRow(), 0));
            try {
                boolean isOpen = false;
                for (SmbFile f : dirRemote.listFiles()) {
                    if (f.isFile()) {
                        if (fname.equals(f.getName())) {
                            if (f.canRead()) {
                                isOpen = true;
                                try {
                                    String mimeType = URLConnection.guessContentTypeFromName(f.getName());
                                    System.out.println("mimeType: " + mimeType);
                                    if (mimeType != null) {
                                        if (mimeType.contains("text/")) {
                                            PopupText popupText = new PopupText(f);
                                            popupText.setVisible(true);
                                        } else if (mimeType.contains("image/")) {
                                            PopupImage popupImg = new PopupImage(f);
                                            popupImg.setVisible(true);
                                        }
                                    }
                                } catch (Exception ex) {
                                    Logger.getLogger(FileStorage.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            }
                        }
                    }
                }
                if (!isOpen) {
                    JOptionPane.showMessageDialog(null, "Không thể mở file.", "Lỗi", JOptionPane.CANCEL_OPTION);
                }
            } catch (SmbException ex) {
                Logger.getLogger(FileStorage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnOpenFileRemoteActionPerformed

    private void btnRenameFileRemoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenameFileRemoteActionPerformed
        if (tblFilesRemote.getSelectedRow() > -1) {
            String fname = String.valueOf(listFilesRemote.getValueAt(tblFilesRemote.getSelectedRow(), 0));
            try {
                boolean isWrite = false;
                for (SmbFile f : dirRemote.listFiles()) {
                    if (f.isFile()) {
                        if (fname.equals(f.getName())) {
                            if (f.canWrite()) {
                                isWrite = true;
                                try {
                                    RenameF renameF = new RenameF(urlRemote, authRemote, f);
//                                    RenameF renameF = new RenameF(this, f);
                                    renameF.setVisible(true);
                                    renameF.addWindowListener(new java.awt.event.WindowAdapter() {
                                        @Override
                                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                            renameF.dispose();
                                            ListFilesRemote();
                                        }
                                    });
                                } catch (Exception ex) {
                                    Logger.getLogger(FileStorage.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            }
                        }
                    }
                }
                if (!isWrite) {
                    JOptionPane.showMessageDialog(null, "Không thể đổi tên file.", "Lỗi", JOptionPane.CANCEL_OPTION);
                }
            } catch (SmbException ex) {
            }
        }
    }//GEN-LAST:event_btnRenameFileRemoteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        ListFilesLocal();
        ListFilesRemote();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        if (tblFilesLocal.getSelectedRow() > -1) {
            String fname = String.valueOf(listFilesLocal.getValueAt(tblFilesLocal.getSelectedRow(), 0));

            File dir = new File(txtPath.getText());
            File dsFile[] = dir.listFiles();
            if (dsFile != null) {
                for (int i = 0; i < dsFile.length; i++) {
                    File f_temp = dsFile[i];
                    if (f_temp.getName().equals(fname)) {
                        try {
                            File fileSource = new File(f_temp.getCanonicalPath());
                            SmbFile smbToFile = new SmbFile(urlRemote + f_temp.getName(), authRemote);

                            FileInputStream fis = new FileInputStream(fileSource);
                            SmbFileOutputStream smbfos = new SmbFileOutputStream(smbToFile);

                            // writing data
                            try {
                                final byte[] b = new byte[1024];
                                int read = 0;
                                while ((read = fis.read(b, 0, b.length)) > 0) {
                                    smbfos.write(b, 0, read);
                                }
                            } finally {
                                fis.close();
                                smbfos.close();
                            }

                            JOptionPane.showMessageDialog(null, "Tải lên thành công.");
                            ListFilesLocal();
                            ListFilesRemote();
                            break;
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Không thể tải lên file.", "Lỗi", JOptionPane.CANCEL_OPTION);
                        }

                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Có lỗi đã xảy ra.", "Lỗi", JOptionPane.CANCEL_OPTION);
            }
        }
    }//GEN-LAST:event_btnUploadActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (tblFilesRemote.getSelectedRow() > -1) {
            String fname = String.valueOf(listFilesRemote.getValueAt(tblFilesRemote.getSelectedRow(), 0));
            try {
                boolean isOpen = false;
                for (SmbFile f : dirRemote.listFiles()) {
                    if (f.isFile()) {
                        if (fname.equals(f.getName())) {
                            if (f.canRead()) {
                                isOpen = true;
                                try {
//                                    SmbFile smbFromFile = new SmbFile(urlRemote + f.getName(), authRemote);
                                    File fileTarget = new File(txtPath.getText() + "/" + f.getName());

                                    SmbFileInputStream smbfis = new SmbFileInputStream(f);
                                    FileOutputStream fos = new FileOutputStream(fileTarget);

                                    // writing data
                                    try {
                                        final byte[] b = new byte[1024];
                                        int read = 0;
                                        while ((read = smbfis.read(b, 0, b.length)) > 0) {
                                            fos.write(b, 0, read);
                                        }
                                    } finally {
                                        fos.close();
                                        smbfis.close();
                                    }

                                    JOptionPane.showMessageDialog(null, "Tải xuống thành công.");
                                    ListFilesLocal();
                                    ListFilesRemote();
                                    break;
                                } catch (Exception ex) {
                                }
                                break;
                            }
                        }
                    }
                }
                if (!isOpen) {
                    JOptionPane.showMessageDialog(null, "Có lỗi đã xảy ra.", "Lỗi", JOptionPane.CANCEL_OPTION);
                }
            } catch (SmbException ex) {
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FileStorage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FileStorage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FileStorage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FileStorage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FileStorage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseFolder;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnOpenFileRemote;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRenameFileRemote;
    private javax.swing.JButton btnUpload;
    private javax.swing.JFileChooser fcPath;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblFilesLocal;
    private javax.swing.JTable tblFilesRemote;
    private javax.swing.JTextField txtPath;
    // End of variables declaration//GEN-END:variables
}
