
/*
 * Copyright (C) 2015 Dieter J Kybelksties
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
 * @date: 2015-12-14
 * @author: Dieter J Kybelksties
 */
package com.kybelksties.process;

import java.util.logging.Logger;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A dialog to customize how many mandatory and optional positional parameters
 * are needed.
 *
 * @author kybelksd
 */
public class PositionalParameterDialog extends javax.swing.JDialog
{

    private static final String CLASS_NAME = PositionalParameterDialog.class.
                                getName();
    private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    ParameterList theParameters;
    Integer theMandValue = 0;
    Integer theOptValue = 0;

    /**
     * Creates new form ParameterPositionalDialog.
     *
     * @param parent
     * @param modal
     */
    public PositionalParameterDialog(java.awt.Dialog parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        mandSpinner.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                theMandValue =
                (Integer) ((JSpinner) e.getSource()).getValue();
                if (theOptValue < theMandValue)
                {
                    theOptValue = theMandValue;
                    optSpinner.setValue(theOptValue);
                }
            }
        });
        optSpinner.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                theOptValue =
                (Integer) ((JSpinner) e.getSource()).getValue();
                if (theOptValue < theMandValue)
                {
                    theMandValue = theOptValue;
                    mandSpinner.setValue(theMandValue);
                }
            }
        });

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

        mandSpinner = new javax.swing.JSpinner();
        optSpinner = new javax.swing.JSpinner();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        mandLabel = new javax.swing.JLabel();
        optLabel = new javax.swing.JLabel();

        setTitle("Positional Parameters");
        setBounds(new java.awt.Rectangle(200, 200, 0, 0));
        setModalityType(java.awt.Dialog.ModalityType.TOOLKIT_MODAL);
        setName("positionalParameterDialog"); // NOI18N

        mandSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));

        optSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 50, 1));

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                okButtonActionPerformed(evt);
            }
        });

        mandLabel.setText("number of mandatory parameters");

        optLabel.setText("number possible parameters");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mandSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(optSpinner))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton))
                    .addComponent(mandLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(optLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mandSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mandLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(optLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_okButtonActionPerformed
    {//GEN-HEADEREND:event_okButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelButtonActionPerformed
    {//GEN-HEADEREND:event_cancelButtonActionPerformed
        Integer numParams = this.theParameters.size();
        Integer numMandatory = this.theParameters.lastMandatoryPosition;
        optSpinner.setValue(numParams);
        mandSpinner.setValue(numMandatory);
        setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel mandLabel;
    private javax.swing.JSpinner mandSpinner;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel optLabel;
    private javax.swing.JSpinner optSpinner;
    // End of variables declaration//GEN-END:variables

    void selectPositions(ParameterList theParameters)
    {
        if (theParameters == null)
        {
            theParameters = new ParameterList();
        }
        this.theParameters = theParameters;
        Integer numParams = this.theParameters.size();
        Integer numMandatory = this.theParameters.getNumberMandatoryParams();
        optSpinner.setValue(numParams);
        mandSpinner.setValue(numMandatory);

        setVisible(true);
    }

    ParameterList getSelection()
    {
        Integer numParams = this.theParameters.size();
        Integer newNumParams = (Integer) optSpinner.getValue();
        Integer newNumMandParams = (Integer) mandSpinner.getValue();

        if (numParams < newNumParams)
        {
            for (Integer i = numParams; i < newNumParams; i++)
            {
                theParameters.addPositionalParam();
            }
        }
        else if (numParams > newNumParams)
        {
            for (Integer i = numParams; i > newNumParams; i--)
            {
                theParameters.removeRow(numParams - 1);
            }
        }

        if (newNumMandParams > 0)
        {
            Integer i = 0;
            for (AbstractParameter param : theParameters)
            {
                param.setMandatory((i < newNumMandParams));
                i++;
            }
        }
        return theParameters;
    }
}