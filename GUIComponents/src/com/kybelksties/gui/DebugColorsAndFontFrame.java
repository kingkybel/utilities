
/*
 * Copyright (C) 2015 Dieter J Kybelksties
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * @date: 2015-12-16
 * @author: Dieter J Kybelksties
 */
package com.kybelksties.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.logging.Logger;

/**
 *
 * @author kybelksd
 */
public class DebugColorsAndFontFrame extends javax.swing.JFrame
{

    Color foregroundColor4 = Color.RED;
    Color foregroundColor5 = Color.GREEN;
    Color foregroundColor6 = Color.BLUE;

    /**
     * Creates new form TestColors
     */
    public DebugColorsAndFontFrame()
    {
        initComponents();
        //      fontChooser2.setShowSample(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        colorButton = new javax.swing.JButton();
        fontButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        colorButton.setText(org.openide.util.NbBundle.getMessage(DebugColorsAndFontFrame.class, "DebugColorsAndFontFrame.colorButton.text")); // NOI18N
        colorButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                colorButtonActionPerformed(evt);
            }
        });
        getContentPane().add(colorButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, -1, -1));

        fontButton.setText(org.openide.util.NbBundle.getMessage(DebugColorsAndFontFrame.class, "DebugColorsAndFontFrame.fontButton.text")); // NOI18N
        fontButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                fontButtonActionPerformed(evt);
            }
        });
        getContentPane().add(fontButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 170));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void colorButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_colorButtonActionPerformed
    {//GEN-HEADEREND:event_colorButtonActionPerformed
        ColorUtils.ContrastColorSet colSet =
                                    ForegroundBackgroundColorChooser.showDialog(
                                            this,
                                            Color.BLACK,
                                            128,
                                            true,
                                            Color.PINK,
                                            Color.CYAN,
                                            Color.MAGENTA);

        jTable1.setModel(colSet);
        repaint();
    }//GEN-LAST:event_colorButtonActionPerformed

    private void fontButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_fontButtonActionPerformed
    {//GEN-HEADEREND:event_fontButtonActionPerformed
        Font f = FontChooser.showDialog(this, null);
        System.out.println("Font=" + f.toString());
    }//GEN-LAST:event_fontButtonActionPerformed

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
            java.util.logging.Logger.getLogger(DebugColorsAndFontFrame.class.
                    getName()).log(
                            java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new DebugColorsAndFontFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton colorButton;
    private javax.swing.JButton fontButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG =
                                Logger.getLogger(DebugColorsAndFontFrame.class.
                                        getName());
}
