package client;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainForm extends javax.swing.JFrame {

    String username;
    String host;
    int port;
    Socket socket;
    DataOutputStream dos;
    public boolean attachmentOpen = false;
    private boolean isConnected = false;
    private String mydownloadfolder = "D:\\";

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
    }

    public void refreshButton(String content){
        String[] listButton = content.split("~");
         btnDadu1.setText(listButton[0]);
        btnDadu2.setText(listButton[1]);
        btnDadu3.setText(listButton[2]);
        btnDadu4.setText(listButton[3]);
        btnDadu5.setText(listButton[4]);
    }
    public void getDadu(String content) {
        String[] listDadu = content.split("~");
        txtDadu1.setText(listDadu[0]);
        txtDadu2.setText(listDadu[1]);
        txtDadu3.setText(listDadu[2]);
        txtDadu4.setText(listDadu[3]);
        txtDadu5.setText(listDadu[4]);

    }

    public void initFrame(String username, String host, int port) {
        this.username = username;
        this.host = host;
        this.port = port;
        setTitle("Masuk sebagai : " + username);
        /**
         * Connect *
         */
        connect();
    }

    public void connect() {
        appendMessage(" Connecting...", "Status", Color.BLACK, Color.BLACK);
        try {
            socket = new Socket(host, port);
            dos = new DataOutputStream(socket.getOutputStream());
            /**
             * Send our username *
             */
            dos.writeUTF("CMD_JOIN " + username);
            appendMessage(" Connected", "Status", Color.BLACK, Color.BLACK);
            appendMessage(" Ketikan pesan ...", "Status", Color.BLACK, Color.BLACK);

            /**
             * Start Client Thread *
             */
            new Thread(new ClientThread(socket, this)).start();
            // were now connected
            isConnected = true;

        } catch (IOException e) {
            isConnected = false;
            JOptionPane.showMessageDialog(this, "Gagal konek ke server, silahkan coba beberapa saat lagi.!", "Koneksi putus", JOptionPane.ERROR_MESSAGE);
            appendMessage("[IOException]: " + e.getMessage(), "Error", Color.RED, Color.RED);
        }
    }

    /*
     is Connected
     */
    public boolean isConnected() {
        return this.isConnected;
    }

    /*
     System Message
     */
    public void appendMessage(String msg, String header, Color headerColor, Color contentColor) {
  
        getMsgHeader(header, headerColor);
        getMsgContent(msg, contentColor);
        
    }

    /*
     My Message
     */
    public void appendMyMessage(String msg, String header) {
        
        getMsgHeader(header, Color.BLUE);
        getMsgContent(msg, Color.YELLOW);
        
    }

    /*
     Message Header
     */
    public void getMsgHeader(String header, Color color) {
        
    }
    /*
     Message Content
     */

    public void getMsgContent(String msg, Color color) {
       
    }

    public void appendOnlineList(Vector list) {
        //  showOnLineList(list);  -  Original Method()
        sampleOnlineList(list);  // - Sample Method()
    }

    /*
     Append online list
     */
    public void showOnLineList(Vector list) {
        try {
            StringBuilder sb = new StringBuilder();
            Iterator it = list.iterator();
            sb.append("<html><table>");
            while (it.hasNext()) {
                Object e = it.next();
                URL url = getImageFile();
                Icon icon = new ImageIcon(this.getClass().getResource("/images/online.png"));
                //sb.append("<tr><td><img src='").append(url).append("'></td><td>").append(e).append("</td></tr>");
                sb.append("<tr><td><b>></b></td><td>").append(e).append("</td></tr>");
                System.out.println("Online: " + e);
            }
            sb.append("</table></body></html>");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     ************************************  Show Online Sample  *********************************************
     */
    private void sampleOnlineList(Vector list) {
        
        Iterator i = list.iterator();
        while (i.hasNext()) {
            Object e = i.next();
            /*  Show Online Username   */
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));
            panel.setBackground(Color.white);

            JLabel label = new JLabel();
            label.setText(" " + e);
            panel.add(label);
            
            /*  Append Next Line   */
            sampleAppend();
        }
        
    }

    private void sampleAppend() {
        
    }
    /*
     ************************************  Show Online Sample  *********************************************
     */

    /*
     Get image file path
     */
    public URL getImageFile() {
        URL url = this.getClass().getResource("/images/online.png");
        return url;
    }

    /*
     Set myTitle
     */
    public void setMyTitle(String s) {
        setTitle(s);
    }

    /*
     Get My Download Folder
     */
    public String getMyDownloadFolder() {
        return this.mydownloadfolder;
    }

    /*
     Get Host
     */
    public String getMyHost() {
        return this.host;
    }

    /*
     Get Port
     */
    public int getMyPort() {
        return this.port;
    }

    /*
     Get My Username
     */
    public String getMyUsername() {
        return this.username;
    }

    /*
     Update Attachment 
     */
    public void updateAttachment(boolean b) {
        this.attachmentOpen = b;
    }

    /*
     This will open a file chooser
     */
    public void openFolder() {
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int open = chooser.showDialog(this, "Browse Folder");
        if (open == chooser.APPROVE_OPTION) {
            mydownloadfolder = chooser.getSelectedFile().toString() + "\\";
        } else {
            mydownloadfolder = "D:\\";
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

        chooser = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        btnDadu1 = new javax.swing.JButton();
        btnDadu2 = new javax.swing.JButton();
        btnDadu3 = new javax.swing.JButton();
        btnDadu4 = new javax.swing.JButton();
        btnDadu5 = new javax.swing.JButton();
        txtDadu1 = new javax.swing.JLabel();
        txtDadu2 = new javax.swing.JLabel();
        txtDadu3 = new javax.swing.JLabel();
        txtDadu4 = new javax.swing.JLabel();
        txtDadu5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Aplikasi Chat IP");
        setBackground(new java.awt.Color(145, 53, 53));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnDadu1.setText("Dadu 1");
        btnDadu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDadu1ActionPerformed(evt);
            }
        });

        btnDadu2.setText("Dadu 2");
        btnDadu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDadu2ActionPerformed(evt);
            }
        });

        btnDadu3.setText("Dadu 3");
        btnDadu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDadu3ActionPerformed(evt);
            }
        });

        btnDadu4.setText("Dadu 4");
        btnDadu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDadu4ActionPerformed(evt);
            }
        });

        btnDadu5.setText("Dadu 5");
        btnDadu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDadu5ActionPerformed(evt);
            }
        });

        txtDadu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDadu1.setText("-");
        txtDadu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtDadu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDadu2.setText("-");
        txtDadu2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtDadu3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDadu3.setText("-");
        txtDadu3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtDadu4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDadu4.setText("-");
        txtDadu4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txtDadu5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtDadu5.setText("-");
        txtDadu5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDadu1)
                    .addComponent(txtDadu1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDadu2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDadu3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDadu4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDadu5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDadu2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDadu3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDadu4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDadu5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDadu1)
                    .addComponent(btnDadu2)
                    .addComponent(btnDadu3)
                    .addComponent(btnDadu4)
                    .addComponent(btnDadu5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDadu1)
                    .addComponent(txtDadu2)
                    .addComponent(txtDadu3)
                    .addComponent(txtDadu4)
                    .addComponent(txtDadu5))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Tutup aplikasi ini.?");
        if (confirm == 0) {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            this.dispose();
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnDadu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDadu1ActionPerformed
        // TODO add your handling code here:
        try {
            dos.writeUTF("CMD_CHOOSE_DADU " + username+ " "+0);
        } catch (IOException e) {
            appendMessage("Gagal mengirim pesan, server sedang bermasalah atau coba restart aplikasi.!", "Error", Color.RED, Color.RED);
        }
    }//GEN-LAST:event_btnDadu1ActionPerformed

    private void btnDadu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDadu2ActionPerformed
        // TODO add your handling code here:
        try {
            dos.writeUTF("CMD_CHOOSE_DADU " + username+ " "+1);
        } catch (IOException e) {
            appendMessage("Gagal mengirim pesan, server sedang bermasalah atau coba restart aplikasi.!", "Error", Color.RED, Color.RED);
        }
    }//GEN-LAST:event_btnDadu2ActionPerformed

    private void btnDadu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDadu3ActionPerformed
        // TODO add your handling code here:
        try {
            dos.writeUTF("CMD_CHOOSE_DADU " + username+ " "+2);
        } catch (IOException e) {
            appendMessage("Gagal mengirim pesan, server sedang bermasalah atau coba restart aplikasi.!", "Error", Color.RED, Color.RED);
        }
    }//GEN-LAST:event_btnDadu3ActionPerformed

    private void btnDadu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDadu4ActionPerformed
        // TODO add your handling code here:
        try {
            dos.writeUTF("CMD_CHOOSE_DADU " + username+ " "+3);
        } catch (IOException e) {
            appendMessage("Gagal mengirim pesan, server sedang bermasalah atau coba restart aplikasi.!", "Error", Color.RED, Color.RED);
        }
    }//GEN-LAST:event_btnDadu4ActionPerformed

    private void btnDadu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDadu5ActionPerformed
        // TODO add your handling code here:
        try {
            dos.writeUTF("CMD_CHOOSE_DADU " + username+ " "+4);
        } catch (IOException e) {
            appendMessage("Gagal mengirim pesan, server sedang bermasalah atau coba restart aplikasi.!", "Error", Color.RED, Color.RED);
        }
    }//GEN-LAST:event_btnDadu5ActionPerformed

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
    private javax.swing.JButton btnDadu1;
    private javax.swing.JButton btnDadu2;
    private javax.swing.JButton btnDadu3;
    private javax.swing.JButton btnDadu4;
    private javax.swing.JButton btnDadu5;
    private javax.swing.JFileChooser chooser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtDadu1;
    private javax.swing.JLabel txtDadu2;
    private javax.swing.JLabel txtDadu3;
    private javax.swing.JLabel txtDadu4;
    private javax.swing.JLabel txtDadu5;
    // End of variables declaration//GEN-END:variables

    void resultWinner(String content) {
        int largest = 0;
        String[] listPlayer = content.split("~");
        for (int i = 0; i < listPlayer.length; i++) {
            String[] player = listPlayer[i].split("::");
            if (largest <= Integer.valueOf(player[1])) {
                largest = Integer.valueOf(player[1]);
            }
        }
        for (int i = 0; i < listPlayer.length; i++) {
            String[] player = listPlayer[i].split("::");
            if (largest == Integer.valueOf(player[1])) {
                JOptionPane.showMessageDialog(rootPane, player[0]+" WINNIER");
            }
        }
    }
}
