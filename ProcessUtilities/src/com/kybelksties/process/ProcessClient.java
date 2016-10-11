package com.kybelksties.process;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

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
 * @date: 2016-10-06
 * @author: Dieter J Kybelksties
 */
/**
 *
 * @author Dieter J Kybelksties
 */
public class ProcessClient extends javax.swing.JFrame
{

    private static final Class CLAZZ = ProcessClient.class;
    private static final String CLASS_NAME = CLAZZ.getName();
    private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    /**
     * Creates new form ProcessClient
     */
    public ProcessClient()
    {
        initComponents();
    }

    /**
     * Implements the connection logic by prompting the end user for the
     * server's IP address, connecting, setting up streams, and consuming the
     * welcome messages from the server.
     *
     * @param port
     * @throws java.io.IOException
     */
    public void connectToServer(int port) throws IOException
    {

        // Get the server address from a dialog box.
        String serverAddress = JOptionPane.showInputDialog(
               this,
               "Enter IP Address of the Server:",
               "Welcome to the Process Client",
               JOptionPane.QUESTION_MESSAGE);

        if (serverAddress.isEmpty())
        {
            serverAddress = "localhost";
        }
        // Make connection and initialize streams
        socket = new Socket(serverAddress, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
        ProcessMessage msg;
        try
        {
            // read the welcome message from the server
            msg = (ProcessMessage) in.readObject();
            outputPanel.writeln(msg.toString());
            msg = (ProcessMessage) in.readObject();
            outputPanel.writeln(msg.toString());
        }
        catch (ClassNotFoundException ex)
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

        commandComboBox = new javax.swing.JComboBox();
        additionalParamsInput = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        replyLabel = new javax.swing.JLabel();
        outputPanel = new com.kybelksties.gui.OutputPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        commandComboBox.setModel(new DefaultComboBoxModel(com.kybelksties.process.ProcessMessage.values()));

        additionalParamsInput.setText(org.openide.util.NbBundle.getMessage(ProcessClient.class, "ProcessClient.additionalParamsInput.text")); // NOI18N

        sendButton.setText(org.openide.util.NbBundle.getMessage(ProcessClient.class, "ProcessClient.sendButton.text")); // NOI18N
        sendButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                sendButtonActionPerformed(evt);
            }
        });

        replyLabel.setText(org.openide.util.NbBundle.getMessage(ProcessClient.class, "ProcessClient.replyLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(replyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(sendButton))
            .addGroup(layout.createSequentialGroup()
                .addComponent(commandComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(additionalParamsInput))
            .addComponent(outputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(commandComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(additionalParamsInput, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sendButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(replyLabel)
                        .addGap(4, 4, 4)))
                .addGap(18, 18, 18)
                .addComponent(outputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_sendButtonActionPerformed
    {//GEN-HEADEREND:event_sendButtonActionPerformed
        ProcessMessage.Type type = (ProcessMessage.Type) commandComboBox.
                            getSelectedItem();
        ProcessMessage sendMsg = new ProcessMessage(type,
                                                    additionalParamsInput.
                                                    getText().
                                                    split(" "));
        try
        {
            out.writeObject(sendMsg);
        }
        catch (IOException ex)
        {
            outputPanel.writelnError(ex.toString());
        }
        Object response;
        try
        {
            response = in.readObject();
            if (response == null)
            {
                System.exit(0);
            }
            if (!(response instanceof ProcessMessage))
            {
                throw new IOException("Not a Process Command!");
            }
            ProcessMessage rcvdMsg = (ProcessMessage) response;
            outputPanel.writeln("Sent command:" + sendMsg.toString());
            outputPanel.writelnHighlight("Received response:" +
                                         rcvdMsg.toString());
//            switch (cmd.getType())
//            {
//                case ChitChat:
//                    System.exit(0);
//                    break;
//                case StopServer:
//                    System.exit(0);
//                    break;
//                case Identify:
//                    System.exit(0);
//                    break;
//                case StartProcess:
//                    System.exit(0);
//                    break;
//                case ListProcesses:
//                    System.exit(0);
//                    break;
//                case ProcessList:
//                    System.exit(0);
//                    break;
//                case KillProcess:
//                    System.exit(0);
//                    break;
//                case RestartProcess:
//                    System.exit(0);
//                    break;
//            }

        }
        catch (IOException | ClassNotFoundException ex)
        {
            outputPanel.writelnError(ex.toString());
        }

    }//GEN-LAST:event_sendButtonActionPerformed

    /**
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
            java.util.logging.Logger.getLogger(ProcessClient.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>
        final ProcessClient client = new ProcessClient();
        client.setVisible(true);
        final int port = (args == null || args.length == 0) ?
                         9898 :
                         Integer.parseInt(args[0]);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    client.connectToServer(port);
                }
                catch (IOException ex)
                {
                    LOGGER.log(Level.SEVERE, ex.getLocalizedMessage());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField additionalParamsInput;
    private javax.swing.JComboBox commandComboBox;
    private com.kybelksties.gui.OutputPanel outputPanel;
    private javax.swing.JLabel replyLabel;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables
}