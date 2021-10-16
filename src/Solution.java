
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Apira
 */
public class Solution extends javax.swing.JFrame {

    StringBuilder sb = new StringBuilder();
    
    /**
     * Creates new form GraphDrawing
     */
    public Solution() {
        initComponents();
//        sb.append("test1\ntest2");
//        String sbStr = sb.toString();
//        System.out.println(sbStr);
//        jLabelSolution.setText(sbStr);
//        jTextArea1.setText(sbStr);
//        jTextArea1.setFocusable(false);
    } 
    
    public Solution(StringBuilder sb,String nameTitle) {
        initComponents();
        String sbStr = sb.toString();
        System.out.println(sbStr);
        setTitle("Solution "+ nameTitle);
        jTextAreaSolution.setText(sbStr);
        jTextAreaSolution.setFocusable(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSolution = new javax.swing.JPanel();
        jButtonDone = new javax.swing.JButton();
        jButtonBrandandBound = new javax.swing.JButton();
        jButtonGraph = new javax.swing.JButton();
        jScrollPaneSolution = new javax.swing.JScrollPane();
        jTextAreaSolution = new javax.swing.JTextArea();

        setTitle("Solution");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanelSolution.setBackground(new java.awt.Color(204, 204, 255));

        jButtonDone.setText("Done");
        jButtonDone.setMinimumSize(new java.awt.Dimension(190, 22));
        jButtonDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoneActionPerformed(evt);
            }
        });

        jButtonBrandandBound.setText(" Brand and Bound");
        jButtonBrandandBound.setToolTipText("");
        jButtonBrandandBound.setMinimumSize(new java.awt.Dimension(190, 22));

        jButtonGraph.setText("Show graph");
        jButtonGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGraphActionPerformed(evt);
            }
        });

        jTextAreaSolution.setBackground(new java.awt.Color(204, 204, 255));
        jTextAreaSolution.setColumns(20);
        jTextAreaSolution.setRows(5);
        jTextAreaSolution.setText("Solution");
        jScrollPaneSolution.setViewportView(jTextAreaSolution);

        javax.swing.GroupLayout jPanelSolutionLayout = new javax.swing.GroupLayout(jPanelSolution);
        jPanelSolution.setLayout(jPanelSolutionLayout);
        jPanelSolutionLayout.setHorizontalGroup(
            jPanelSolutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSolutionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSolutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPaneSolution)
                    .addGroup(jPanelSolutionLayout.createSequentialGroup()
                        .addComponent(jButtonDone, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonBrandandBound, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelSolutionLayout.setVerticalGroup(
            jPanelSolutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSolutionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneSolution, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSolutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBrandandBound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonGraph))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSolution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSolution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoneActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButtonDoneActionPerformed

    private void jButtonGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGraphActionPerformed
        // TODO add your handling code here:
        Graph g = new Graph();
        g.setVisible(true);
    }//GEN-LAST:event_jButtonGraphActionPerformed
    
    
    
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
            java.util.logging.Logger.getLogger(Solution.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Solution.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Solution.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Solution.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Solution().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBrandandBound;
    private javax.swing.JButton jButtonDone;
    private javax.swing.JButton jButtonGraph;
    private javax.swing.JPanel jPanelSolution;
    private javax.swing.JScrollPane jScrollPaneSolution;
    private javax.swing.JTextArea jTextAreaSolution;
    // End of variables declaration//GEN-END:variables
}
