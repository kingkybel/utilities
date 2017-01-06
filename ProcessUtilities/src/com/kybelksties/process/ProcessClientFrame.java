/*
 * Copyright (C) 2016 Dieter J Kybelksties
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @date: 2016-10-28
 * @author: Dieter J Kybelksties
 */
package com.kybelksties.process;

import com.kybelksties.general.StringUtils;
import com.kybelksties.general.SystemProperties;
import java.awt.Component;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Dieter J Kybelksties
 */
public class ProcessClientFrame extends javax.swing.JFrame
{

    private static final Class CLAZZ = ProcessClientFrame.class;
    private static final String CLASS_NAME = CLAZZ.getName();
    private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    ProcessClient client;
    private static final String LOCALHOST = localHostName();
    String serverAddress = null;
    int port = 9898;

    static private String localHostName()
    {
        String reval = (String) SystemProperties.get("HOSTNAME");
        return reval == null || reval.isEmpty() ? "localhost" : reval;
    }

    /**
     * Creates new form ProcessClientFrame.
     *
     * @param parent
     * @param serverAddress
     * @param port
     */
    public ProcessClientFrame(Component parent, String serverAddress, int port)
    {
        initComponents();
        this.serverAddress = serverAddress;
        this.port = port;
        if (this.serverAddress == null || this.serverAddress.isEmpty())
        {
            // Get the server address from a dialog box.
            this.serverAddress = JOptionPane.showInputDialog(
            parent,
            "Enter IP Address of the Server:",
            "Welcome to the Process Client",
            JOptionPane.QUESTION_MESSAGE);
            if (this.serverAddress == null || this.serverAddress.isEmpty())
            {
                this.serverAddress = LOCALHOST;
            }
        }
        try
        {
            client = new ProcessClient(serverAddress, port);
        }
        catch (IOException ex)
        {
            outputPanel.writelnError(ex.toString());
        }
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        commandComboBox = new javax.swing.JComboBox();
        sendButton = new javax.swing.JButton();
        additionalParamsInput = new javax.swing.JTextField();
        outputPanel = new com.kybelksties.gui.OutputPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        commandComboBox.setModel(new DefaultComboBoxModel(com.kybelksties.process.ProcessMessage.instructionMessageTypes()));

        sendButton.setText(org.openide.util.NbBundle.getMessage(ProcessClientFrame.class, "ProcessClientFrame.sendButton.text")); // NOI18N
        sendButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                sendButtonActionPerformed(evt);
            }
        });

        additionalParamsInput.setText(org.openide.util.NbBundle.getMessage(ProcessClientFrame.class, "ProcessClientFrame.additionalParamsInput.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(sendButton))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(commandComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(171, Short.MAX_VALUE))
            .addComponent(additionalParamsInput)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(commandComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(additionalParamsInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(sendButton))
        );

        jSplitPane1.setTopComponent(jPanel1);
        jSplitPane1.setRightComponent(outputPanel);

        getContentPane().add(jSplitPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_sendButtonActionPerformed
    {//GEN-HEADEREND:event_sendButtonActionPerformed
        ProcessMessage.Type type = (ProcessMessage.Type) commandComboBox.
                            getSelectedItem();
        ProcessMessage sendMsg = new ProcessMessage(type,
                                                    (Serializable[]) additionalParamsInput.
                                                    getText().
                                                    split(" "));
        try
        {
            ProcessMessage rcvdMsg = client.sendMessage(sendMsg);
            outputPanel.writeln("Sent command:" + sendMsg.toString());
            if (rcvdMsg.isInvalid())
            {
                outputPanel.writelnError("Received error:" +
                                         StringUtils.NEWLINE +
                                         rcvdMsg.toString());
            }
            else
            {
                outputPanel.writelnHighlight("Received valid response:" +
                                             StringUtils.NEWLINE +
                                             rcvdMsg.toString());
            }
        }
        catch (IOException | ClassNotFoundException ex)
        {
            outputPanel.writelnError(ex.toString());
        }

    }//GEN-LAST:event_sendButtonActionPerformed

    /**
     * Main entry point.
     *
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info
                         : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException |
               IllegalAccessException |
               javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ProcessClientFrame.class.
                    getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        final int port = (args == null || args.length == 0) ?
                         9898 :
                         Integer.parseInt(args[0]);
        final String serverAddress = (args == null || args.length < 2) ?
                                     LOCALHOST :
                                     args[1];
        final ProcessClientFrame frame = new ProcessClientFrame(null,
                                                                serverAddress,
                                                                port);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    frame.setVisible(true);
                    ArrayList<ProcessMessage> responses = frame.
                                              connectToServer();
                    for (ProcessMessage response : responses)
                    {
                        frame.outputPanel.writelnMeta(response.toString());
                    }
                }
                catch (IOException | ClassNotFoundException ex)
                {
                    frame.outputPanel.writelnError(ex.toString());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField additionalParamsInput;
    private javax.swing.JComboBox commandComboBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    private com.kybelksties.gui.OutputPanel outputPanel;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables

    private ArrayList<ProcessMessage> connectToServer()
            throws IOException,
                   ClassNotFoundException
    {
        return client.connectToServer();
    }
}