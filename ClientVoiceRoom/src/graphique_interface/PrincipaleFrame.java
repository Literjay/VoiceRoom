/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique_interface;

import java.awt.Component;
import java.awt.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Betty
 */
public class PrincipaleFrame extends javax.swing.JFrame {
    protected  Room mRoom;
    protected  Tool mTool;
    protected  ObjectOutputStream mOut;
    protected  ObjectInputStream mIn;
    protected  Vector mListData;
    /**
     * Creates new form PrincipaleFrame
     */
    
     public PrincipaleFrame(Room room, Tool tool ,ObjectOutputStream out, ObjectInputStream in) {
         
        initComponents();
        button_micro.setOpaque(true);
        button_soung.setOpaque(true);              
        mRoom = room;
        mTool = tool;
        mIn= in;
        mOut = out;
         ArrayList<Client> clients = mRoom.getClients();
        for(Client c : clients){
            DefaultListModel modele = new DefaultListModel();
        modele.addElement("- " + c.getIdentifiant());
        mList.setModel(modele);
        }
        String [] args = null;
        this.main(args);
         //mList.setListData(mListData);
         
  
        
    }
     
    public PrincipaleFrame() {
        initComponents();
       /* button_micro.setOpaque(true);
        button_soung.setOpaque(true);*/
    /*    this.setVisible(true);
                try {
                        mSocketData = new Socket(mTool.getIp(),1777 );
                        ObjectOutputStream mOut = new ObjectOutputStream(mSocketData.getOutputStream());
                        ObjectInputStream mInt = new ObjectInputStream(mSocketData.getInputStream());
                        while(true){
                            try {
                                String line = (String)mInt.readObject();
                                System.out.println(line);
                                switch(line){
                                    case "Nouveau Client":
                                        mListData.add((String)mInt.readObject());
                                        mList.setListData(mListData);
                                }
                            } catch (Exception e) {
                            }
                        }
                } catch (Exception e) {
                }*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button_micro = new javax.swing.JButton();
        button_soung = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mList = new javax.swing.JList();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button_micro.setBackground(new java.awt.Color(204, 204, 204));
        button_micro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/micro.png"))); // NOI18N
        button_micro.setBorder(null);
        button_micro.setBorderPainted(false);
        button_micro.setContentAreaFilled(false);
        button_micro.setDefaultCapable(false);
        button_micro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_microActionPerformed(evt);
            }
        });

        button_soung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/soung.png"))); // NOI18N
        button_soung.setBorder(null);
        button_soung.setBorderPainted(false);
        button_soung.setContentAreaFilled(false);
        button_soung.setDefaultCapable(false);
        button_soung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_soungActionPerformed(evt);
            }
        });

        mList.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        mList.setForeground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setViewportView(mList);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_micro, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button_soung, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button_micro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_soung, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_microActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_microActionPerformed
        
    }//GEN-LAST:event_button_microActionPerformed

    private void button_soungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_soungActionPerformed
       
    }//GEN-LAST:event_button_soungActionPerformed

    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
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
            java.util.logging.Logger.getLogger(PrincipaleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipaleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipaleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipaleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                    
                    int bytesRead = 0;
                    byte[] soundData = new byte[1];
                try {
                    System.out.println("en attente");
                    while(bytesRead != -1)
                        {
                            bytesRead = mTool.micro.read(soundData, 0, soundData.length);
                            if(mIn.read()==666){
                                System.out.println(mIn.readUTF());
                            }
                            if(bytesRead >= 0)
                            {
                                mOut.write(soundData, 0, bytesRead);
                            }
                        }
                } 
                   catch (IOException ex) {
                    Logger.getLogger(PrincipaleFrame.class.getName()).log(Level.SEVERE, null, ex);
                } 
               /* try {
                        //mSocketData = new Socket(InetAddress.getLocalHost(), mTool.getIp());
                        ObjectOutputStream mOut = new ObjectOutputStream(mSocketData.getOutputStream());
                        ObjectInputStream mInt = new ObjectInputStream(mSocketData.getInputStream());
                        while(true){
                            try {
                                String line = (String)mInt.readObject();
                                System.out.println(line);
                                switch(line){
                                    case "Nouveau Client":
                                        mListData.add((String)mInt.readObject());
                                        mList.setListData(mListData);
                                }
                            } catch (Exception e) {
                            }
                        }
                } catch (Exception e) {
                }*/
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_micro;
    private javax.swing.JButton button_soung;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList mList;
    // End of variables declaration//GEN-END:variables
}
