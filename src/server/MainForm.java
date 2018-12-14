/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;

public class MainForm extends javax.swing.JFrame {

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
    Thread t;
    ServerThread serverThread;
    /**
     * Chat List *
     */
    public Vector socketList = new Vector();
    public Vector clientList = new Vector();
    /**
     * File Sharing List *
     */
    public Vector clientFileSharingUsername = new Vector();
    public Vector clientFileSharingSocket = new Vector();
    /**
     * Server *
     */
    ServerSocket server;
    ArrayList<Pemain> pemainList;
    ArrayList<Dadu> daduList;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        pemainList = new ArrayList<Pemain>();
        daduList = new ArrayList<Dadu>();
    }

    public void playerMemilih(String username, String pilihan) {
        for (int i = 0; i < pemainList.size(); i++) {
            if (pemainList.get(i).getUsername().equals(username)) {
                pemainList.get(i).setDaduPilihan(Integer.valueOf(pilihan));
            }
        }
    }
    
    public void sendResult(){
        String content = "";
        for (int i = 0; i < pemainList.size(); i++) {
            int daduPilihan = pemainList.get(i).getDaduPilihan();
            int jumlahDadu = daduList.get(daduPilihan).getJumlah();
            
            content += pemainList.get(i).getUsername()+"::"+jumlahDadu+"~";
        }
         for (int x = 0; x < clientList.size(); x++) {
            try {
                Socket tsoc2 = (Socket) socketList.elementAt(x);
                DataOutputStream dos2 = new DataOutputStream(tsoc2.getOutputStream());
                dos2.writeUTF("CMD_RESULTWINNER " + content);
            } catch (IOException e) {
                appendMessage("[CMD_CHATALL]: " + e.getMessage());
            }

        }
    }

    public void refreshButton() {
        String content = "";
        int idxPlayer = 0;
        
        for (int i = 0; i < 5; i++) {
            boolean occupied = false;
            for (int j = 0; j < pemainList.size(); j++) {
                if (pemainList.get(j).getDaduPilihan() == i) {
                    content += pemainList.get(j).getUsername() + "~";
                    occupied = true;
                }
            }
            if (!occupied) {
                content += "Dadu"+String.valueOf(i+1)+"~";
            }
        }
        for (int x = 0; x < clientList.size(); x++) {
            try {
                Socket tsoc2 = (Socket) socketList.elementAt(x);
                DataOutputStream dos2 = new DataOutputStream(tsoc2.getOutputStream());
                dos2.writeUTF("CMD_REFRESH " + content);
            } catch (IOException e) {
                appendMessage("[CMD_CHATALL]: " + e.getMessage());
            }

        }

    }

    public void tambahPemain(String username) {
        Pemain p = new Pemain(username);
        pemainList.add(p);
    }

    public void acakDadu() {
        daduList.add(new Dadu());
    }

    public void appendMessage(String msg) {
        Date date = new Date();
        jTextArea1.append(sdf.format(date) + ": " + msg + "\n");
        jTextArea1.setCaretPosition(jTextArea1.getText().length() - 1);
    }

    /**
     * Setters *
     */
    public void setSocketList(Socket socket) {
        try {
            socketList.add(socket);
            appendMessage("[setSocketList]: Added");
        } catch (Exception e) {
            appendMessage("[setSocketList]: " + e.getMessage());
        }
    }

    public void setClientList(String client) {
        try {
            clientList.add(client);
            appendMessage("[setClientList]: Added");
        } catch (Exception e) {
            appendMessage("[setClientList]: " + e.getMessage());
        }
    }

    public void setClientFileSharingUsername(String user) {
        try {
            clientFileSharingUsername.add(user);
        } catch (Exception e) {
        }
    }

    public void setClientFileSharingSocket(Socket soc) {
        try {
            clientFileSharingSocket.add(soc);
        } catch (Exception e) {
        }
    }

    /**
     * Getters
     *
     * @param client
     * @return *
     */
    public Socket getClientList(String client) {
        Socket tsoc = null;
        for (int x = 0; x < clientList.size(); x++) {
            if (clientList.get(x).equals(client)) {
                tsoc = (Socket) socketList.get(x);
                break;
            }
        }
        return tsoc;
    }

    public void removeFromTheList(String client) {
        try {
            for (int x = 0; x < clientList.size(); x++) {
                if (clientList.elementAt(x).equals(client)) {
                    clientList.removeElementAt(x);
                    socketList.removeElementAt(x);
                    appendMessage("[Removed]: " + client);
                    break;
                }
            }
        } catch (Exception e) {
            appendMessage("[RemovedException]: " + e.getMessage());
        }
    }

    public Socket getClientFileSharingSocket(String username) {
        Socket tsoc = null;
        for (int x = 0; x < clientFileSharingUsername.size(); x++) {
            if (clientFileSharingUsername.elementAt(x).equals(username)) {
                tsoc = (Socket) clientFileSharingSocket.elementAt(x);
                break;
            }
        }
        return tsoc;
    }

    /*
     Remove Client File Sharing List
     */
    public void removeClientFileSharing(String username) {
        for (int x = 0; x < clientFileSharingUsername.size(); x++) {
            if (clientFileSharingUsername.elementAt(x).equals(username)) {
                try {
                    Socket rSock = getClientFileSharingSocket(username);
                    if (rSock != null) {
                        rSock.close();
                    }
                    clientFileSharingUsername.removeElementAt(x);
                    clientFileSharingSocket.removeElementAt(x);
                    appendMessage("[FileSharing]: Removed " + username);
                } catch (IOException e) {
                    appendMessage("[FileSharing]: " + e.getMessage());
                    appendMessage("[FileSharing]: Unable to Remove " + username);
                }
                break;
            }
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

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnStart = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server Aplikasi Chat IP");
        setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setText("Port:");

        jTextField1.setText("8080");

        jButton1.setText("Start Server");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Stop Server");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(0, 204, 51));
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        btnStart.setText("TENTUKAN PEMENANG");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        jButton3.setText("ACAK DADU");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnStart)
                            .addComponent(jButton3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(27, 27, 27)
                        .addComponent(btnStart)
                        .addGap(104, 104, 104))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int port = Integer.parseInt(jTextField1.getText());
        serverThread = new ServerThread(port, this);
        t = new Thread(serverThread);
        t.start();

        new Thread(new OnlineListThread(this)).start();

        jButton1.setEnabled(false);
        jButton2.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Tutup Server.?");
        if (confirm == 0) {
            serverThread.stop();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:

        String content = "";
        for (int i = 0; i < daduList.size(); i++) {
            content += daduList.get(i).getJumlah()+"~";
        }
        for (int x = 0; x < clientList.size(); x++) {
            try {
                Socket tsoc2 = (Socket) socketList.elementAt(x);
                DataOutputStream dos2 = new DataOutputStream(tsoc2.getOutputStream());
                dos2.writeUTF("CMD_RESULT " + content);
            } catch (IOException e) {
                appendMessage("[CMD_CHATALL]: " + e.getMessage());
            }

        }
        refreshButton();
        
        sendResult();
    }//GEN-LAST:event_btnStartActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        daduList.clear();
        for (int i = 0; i < 5; i++) {
            acakDadu();
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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
