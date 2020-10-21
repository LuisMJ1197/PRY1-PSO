/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.view;

import java.awt.Color;

/**
 *
 * @author Luism
 */
public class CPUCorePanel extends javax.swing.JPanel {
    
    /**
     * Creates new form CPUCorePanel
     */
    public CPUCorePanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        processIDShowLabel2 = new javax.swing.JLabel();
        processIDShowLabel1 = new javax.swing.JLabel();
        irRegisterShowLabel = new javax.swing.JLabel();
        processIDShowLabel = new javax.swing.JLabel();
        irRegisterLabel = new javax.swing.JLabel();
        processIdLabel = new javax.swing.JLabel();
        processIDShowLabel3 = new javax.swing.JLabel();
        zeroFlagLabel = new javax.swing.JLabel();

        processIDShowLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        processIDShowLabel2.setText("ZeroFlag   =");

        processIDShowLabel1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        processIDShowLabel1.setText("Process id =");

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setPreferredSize(new java.awt.Dimension(214, 77));

        irRegisterShowLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        irRegisterShowLabel.setText("IR Register =");

        processIDShowLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        processIDShowLabel.setText("Process id =");

        irRegisterLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        irRegisterLabel.setText("----------------");

        processIdLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        processIdLabel.setText("-1");

        processIDShowLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        processIDShowLabel3.setText("ZeroFlag   =");

        zeroFlagLabel.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        zeroFlagLabel.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(processIDShowLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(processIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(processIDShowLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zeroFlagLabel)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(irRegisterShowLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(irRegisterLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(irRegisterShowLabel)
                    .addComponent(irRegisterLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(processIDShowLabel)
                    .addComponent(processIdLabel)
                    .addComponent(zeroFlagLabel)
                    .addComponent(processIDShowLabel3))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public void setBorderColor(Color color) {
        setBorder(javax.swing.BorderFactory.createLineBorder(color));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel irRegisterLabel;
    private javax.swing.JLabel irRegisterShowLabel;
    private javax.swing.JLabel processIDShowLabel;
    private javax.swing.JLabel processIDShowLabel1;
    private javax.swing.JLabel processIDShowLabel2;
    private javax.swing.JLabel processIDShowLabel3;
    public javax.swing.JLabel processIdLabel;
    public javax.swing.JLabel zeroFlagLabel;
    // End of variables declaration//GEN-END:variables
}
