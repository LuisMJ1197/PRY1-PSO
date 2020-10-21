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
public class ProcessPanel extends javax.swing.JPanel {
    
    /** Creates new form ProcessPanel */
    public ProcessPanel() {
        initComponents();
    }

    public void setProcessColor(Color color) {
        this.colorPane.setBackground(color);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pidLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        pcLabel = new javax.swing.JLabel();
        acLabel = new javax.swing.JLabel();
        axLabel = new javax.swing.JLabel();
        bxLabel = new javax.swing.JLabel();
        cxLabel = new javax.swing.JLabel();
        dxLabel = new javax.swing.JLabel();
        colorPane = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(766, 25));

        pidLabel.setText("100");

        nameLabel.setText("Name");

        statusLabel.setText("Terminated");

        pcLabel.setText("0000000000");

        acLabel.setText("0000000000000000");

        axLabel.setText("0000000000000000");

        bxLabel.setText("0000000000000000");

        cxLabel.setText("0000000000000000");

        dxLabel.setText("0000000000000000");

        colorPane.setBackground(new java.awt.Color(153, 153, 0));

        javax.swing.GroupLayout colorPaneLayout = new javax.swing.GroupLayout(colorPane);
        colorPane.setLayout(colorPaneLayout);
        colorPaneLayout.setHorizontalGroup(
            colorPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        colorPaneLayout.setVerticalGroup(
            colorPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(colorPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(pidLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pcLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(axLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bxLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cxLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dxLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(colorPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pidLabel)
                        .addComponent(nameLabel)
                        .addComponent(statusLabel)
                        .addComponent(pcLabel)
                        .addComponent(acLabel)
                        .addComponent(axLabel)
                        .addComponent(bxLabel)
                        .addComponent(cxLabel)
                        .addComponent(dxLabel)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel acLabel;
    public javax.swing.JLabel axLabel;
    public javax.swing.JLabel bxLabel;
    private javax.swing.JPanel colorPane;
    public javax.swing.JLabel cxLabel;
    public javax.swing.JLabel dxLabel;
    public javax.swing.JLabel nameLabel;
    public javax.swing.JLabel pcLabel;
    public javax.swing.JLabel pidLabel;
    public javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

}
