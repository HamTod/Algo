
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.image.BufferedImage;
/*
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.Action.ACTION_COMMAND_KEY;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Stack;
import javax.swing.AbstractAction;
import static javax.swing.Action.ACTION_COMMAND_KEY;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Apira
 */
@SuppressWarnings("serial")
public class Mainmanu extends javax.swing.JFrame {

    /**
     * Creates new form GraphDraw
     */
    public Mainmanu() {
        initComponents();
    }

    ArrayList<Vertex> Vertexs = new ArrayList<>();
    ArrayList<Edge_> Edge_s = new ArrayList<>();
    File file;
    String pathTemplateSet = "F:\\learn\\64-1\\Algo\\homework\\Subset Sum\\TestSS\\template tree\\templateSet";

    Object selected = null;
    TempEdge TempEdge = null; //TempEdge edge

    //Canvas c;
    String mode = "Vertex"; //Vertex,Edge_

    Font sanSerifFont = new Font("SanSerif", Font.PLAIN, 24);

    //////////////////////////////// Backup ////////////////////////////////
    class Backup {

        ArrayList<Vertex> VertexsBackup;
        ArrayList<Edge_> Edge_sBackup;

        public Backup() {
            this.VertexsBackup = Vertexs;
            this.Edge_sBackup = Edge_s;
        }

    }

    public void selected(int x, int y) {
        Object obj = null;
        for (Vertex s : Vertexs) {
            if (s.inCircle(x, y)) {
                s.isSelect = true;
                obj = s;
                break;
            }
        }
        if (obj == null) {
            for (Edge_ t : Edge_s) {
                if (t.inLine(x, y)) {
                    t.isSelect = true;
                    obj = t;
                    break;
                }
            }
        }
        if (obj == null) {
            if (selected == null) {
                return;
            } else {
                if (selected instanceof Vertex) {
                    Vertex s = (Vertex) selected;
                    s.isSelect = false;
                } else {
                    Edge_ t = (Edge_) selected;
                    t.isSelect = false;
                }
                selected = null;
            }
        } else {
            if (selected == null) {
                selected = obj;
            } else {
                if (obj == selected) {
                    return;
                } else {
                    if (selected instanceof Vertex) {
                        Vertex s = (Vertex) selected;
                        s.isSelect = false;
                    } else {
                        Edge_ t = (Edge_) selected;
                        t.isSelect = false;
                    }
                    selected = obj;
                }
            }
        }
    }

    BufferedImage grid = null;

    public void draw() {

        Graphics2D g = (Graphics2D) canvas.getGraphics();
        g.setFont(sanSerifFont);

        if (grid == null) {
            grid = (BufferedImage) createImage(canvas.getWidth(), canvas.getHeight());
        }

        Graphics2D g2 = grid.createGraphics();

        g2.setColor(new java.awt.Color(204, 255, 255));
        g2.fillRect(0, 0, getWidth(), getHeight());

        for (Edge_ t : Edge_s) {
            t.draw(g2);
        }

        if (TempEdge != null) {
            TempEdge.line(g2);
        }
        for (Vertex s : Vertexs) {
            s.draw(g2);
        }

        /*  for (Edge_ t : Edge_s) { // edit
            t.color = Color.BLACK;
        }   */
        // clear();
        g.drawImage(grid, null, 0, 0);
        //drawall

    }

    public void save(String path) throws IOException {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        FileWriter writer = new FileWriter(path);

        Backup backup = new Backup();
        writer.write(gson.toJson(backup));
        writer.close();
    }

    public void open(String path) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        Backup backup = gson.fromJson(bufferedReader, Backup.class);

        Vertexs = backup.VertexsBackup;
        Edge_s = backup.Edge_sBackup;

        //bind object reference
        for (Edge_ e : Edge_s) {
            if (e.vertexA != null) {
                int id = e.vertexA.id;
                for (Vertex v : Vertexs) {
                    if (v.id == id) {
                        e.vertexA = v;
                        break;
                    }
                }
            }
            if (e.vertexB != null) {
                int id = e.vertexB.id;
                for (Vertex v : Vertexs) {
                    if (v.id == id) {
                        e.vertexB = v;
                        break;
                    }
                }
            }
        }
    }

    void removeTemplate(String[] setArray) {
        for (int i = 0; i < setArray.length + 1; i++) {
            //System.out.println("i :" + i);
            if (i < setArray.length) {
                Vertexs.get(i).name = setArray[i];
            } else {
                if (Vertexs.size() > setArray.length) {
                    Vertexs.remove(i);
                    i--;
                } else {
                    break;
                }
            }
        }
        int edgeSize = Edge_s.size();

        for (int i = 0; i < edgeSize; i++) {
            //System.out.println("---------------");
            //System.out.println("Edge : " + i + "\nvertexA : " + Edge_s.get(i).vertexA.name
            //        + "\nvertexB : " + Edge_s.get(i).vertexB.name);
            String vA = Edge_s.get(i).vertexA.name;
            String vB = Edge_s.get(i).vertexB.name;
            if (vA.equals("") || vB.equals("")) {
                Edge_s.remove(i);
                edgeSize--;
                i--;
            }
        }

        draw();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooserOpen = new javax.swing.JFileChooser();
        jFileChooserSaveAs = new javax.swing.JFileChooser();
        canvas = new javax.swing.JPanel();
        manu = new javax.swing.JPanel();
        jLabelSet = new javax.swing.JLabel();
        jTextFieldSet = new javax.swing.JTextField();
        jLabelSort = new javax.swing.JLabel();
        jButtonGenerateTree = new javax.swing.JButton();
        jButtonSort = new javax.swing.JButton();
        jLabelTerget = new javax.swing.JLabel();
        jTextFieldTerget = new javax.swing.JTextField();
        jButtonBacktracking = new javax.swing.JButton();
        jButtonBrandandBound = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemOpen = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSave = new javax.swing.JMenuItem();
        jMenuItemSaveAs = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();

        jFileChooserOpen.setCurrentDirectory(new java.io.File("F:\\learn\\64-1\\Algo\\homework\\Subset Sum\\TestSS"));

        jFileChooserSaveAs.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        jFileChooserSaveAs.setApproveButtonToolTipText("");
        jFileChooserSaveAs.setCurrentDirectory(new java.io.File("F:\\learn\\64-1\\Algo\\homework\\Subset Sum\\TestSS"));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Supset Sum");

        canvas.setBackground(new java.awt.Color(204, 255, 255));
        canvas.setPreferredSize(new java.awt.Dimension(800, 680));

        javax.swing.GroupLayout canvasLayout = new javax.swing.GroupLayout(canvas);
        canvas.setLayout(canvasLayout);
        canvasLayout.setHorizontalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        canvasLayout.setVerticalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        manu.setBackground(new java.awt.Color(255, 204, 204));

        jLabelSet.setText("Set");

        jTextFieldSet.setText("5,10,12,13,15,18");
        jTextFieldSet.setFocusable(false);
        jTextFieldSet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldSetMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextFieldSetMouseEntered(evt);
            }
        });

        jLabelSort.setText("Sort");

        jButtonGenerateTree.setText("Generate Tree");
        jButtonGenerateTree.setFocusPainted(false);
        jButtonGenerateTree.setFocusable(false);
        jButtonGenerateTree.setMinimumSize(new java.awt.Dimension(103, 23));
        jButtonGenerateTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonGenerateTreeMouseClicked(evt);
            }
        });

        jButtonSort.setText("Sort");

        jLabelTerget.setText("Terget");

        jTextFieldTerget.setText("30");

        jButtonBacktracking.setText("Backtracking");
        jButtonBacktracking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBacktrackingActionPerformed(evt);
            }
        });

        jButtonBrandandBound.setText("brand and bound");

        javax.swing.GroupLayout manuLayout = new javax.swing.GroupLayout(manu);
        manu.setLayout(manuLayout);
        manuLayout.setHorizontalGroup(
            manuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(manuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldSet)
                    .addComponent(jButtonGenerateTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldTerget)
                    .addGroup(manuLayout.createSequentialGroup()
                        .addGroup(manuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSet)
                            .addComponent(jLabelSort)
                            .addComponent(jLabelTerget))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(manuLayout.createSequentialGroup()
                        .addComponent(jButtonBacktracking)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonBrandandBound)))
                .addContainerGap())
        );
        manuLayout.setVerticalGroup(
            manuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGenerateTree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTerget)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTerget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(manuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBacktracking)
                    .addComponent(jButtonBrandandBound))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar.setMinimumSize(new java.awt.Dimension(65, 20));
        jMenuBar.setPreferredSize(new java.awt.Dimension(65, 20));

        jMenuFile.setText("File");

        jMenuItemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemOpen.setText("Open");
        jMenuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOpenActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemOpen);
        jMenuFile.add(jSeparator1);

        jMenuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemSave.setText("Save");
        jMenuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSave);

        jMenuItemSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemSaveAs.setText("Save As...");
        jMenuItemSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveAsActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSaveAs);

        jMenuBar.add(jMenuFile);

        jMenuEdit.setText("Edit");
        jMenuBar.add(jMenuEdit);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(manu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(manu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenActionPerformed
        // TODO add your handling code here:
        int returnVal = jFileChooserOpen.showOpenDialog(this);
        if (returnVal == jFileChooserOpen.APPROVE_OPTION) {
            file = jFileChooserOpen.getSelectedFile();
            try {
                // What to do with the file, e.g. display it in a TextArea
                open(file.getPath());
            } catch (IOException ex) {
                System.out.println("problem accessing file" + file.getAbsolutePath());
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
        draw();

    }//GEN-LAST:event_jMenuItemOpenActionPerformed

    private void jMenuItemSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveAsActionPerformed
        // TODO add your handling code here:
        int returnVal = jFileChooserSaveAs.showSaveDialog(this);
        if (returnVal == jFileChooserSaveAs.APPROVE_OPTION) {
            file = jFileChooserSaveAs.getSelectedFile();
            try {
                // What to do with the file, e.g. display it in a TextArea
                save(file.getPath());
            } catch (IOException ex) {
                System.out.println("problem accessing file" + file.getAbsolutePath());
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_jMenuItemSaveAsActionPerformed

    private void jMenuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveActionPerformed
        if (file == null) {
            jMenuItemSaveAsActionPerformed(evt);
        } else {
            try {
                // TODO add your handling code here:
                save(file.getPath());
            } catch (IOException ex) {
                Logger.getLogger(Mainmanu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jMenuItemSaveActionPerformed

    private void jTextFieldSetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSetMouseClicked
        // TODO add your handling code here:
        if (jTextFieldSet.getText().equals("Only pattern {...,...,...,..}")) {
            jTextFieldSet.setText("");
        }
    }//GEN-LAST:event_jTextFieldSetMouseClicked

    private void jButtonGenerateTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGenerateTreeMouseClicked
        // TODO add your handling code here:
        String set = jTextFieldSet.getText();
        //System.out.println(set);
        //set = set.substring(1, set.length() - 1);
        //System.out.println(set);
        String[] setArray = set.split(",");
        //System.out.println("------------");
        /*for (int i = 0; i < setArray.length; i++) {
            System.out.println(setArray[i]);
        }*/
        //System.out.println("------------");
        /*ArrayList<Integer> setArrayList = new ArrayList<Integer>();
        for (int i = 0; i < set.length() - 1; i++) {
            setArrayList.add(Integer.parseInt(set.split(",")[i]));
        }*/
        int[] setIntArr = new int[setArray.length];
        for (int i = 0; i < setArray.length; i++) {
            setIntArr[i] = Integer.parseInt(setArray[i]);
        }
        try {
            open(pathTemplateSet);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mainmanu.class.getName()).log(Level.SEVERE, null, ex);
        }
        removeTemplate(setArray);

    }//GEN-LAST:event_jButtonGenerateTreeMouseClicked

    private void jTextFieldSetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSetMouseEntered
        // TODO add your handling code here:
        jTextFieldSet.setFocusable(true);
        jButtonGenerateTree.setFocusable(true);
    }//GEN-LAST:event_jTextFieldSetMouseEntered

    private void jButtonBacktrackingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBacktrackingActionPerformed
        // TODO add your handling code here:
        String setStr = jTextFieldSet.getText();
        String[] setStrArr = setStr.split(",");
        int[] setIntArr = new int[setStrArr.length];
        for (int i = 0; i < setStrArr.length; i++) {
            setIntArr[i] = Integer.parseInt(setStrArr[i]);
        }
        String tergetStr = jTextFieldTerget.getText();
        int tergetInt = Integer.parseInt(tergetStr);
        SubSet bt = new SubSet(setIntArr, tergetInt);
        bt.solve(0, 0);

        /*JOptionPane.showMessageDialog(jFrameGraph,
                bt.sb,
                "Solution",
                JOptionPane.PLAIN_MESSAGE);*/
        String btTitle = "Backtracking";

        Solution sol = new Solution(bt.sb, btTitle);
        sol.setVisible(true);
    }//GEN-LAST:event_jButtonBacktrackingActionPerformed

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
            java.util.logging.Logger.getLogger(Mainmanu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mainmanu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mainmanu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mainmanu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Mainmanu MM = new Mainmanu();
                MM.setVisible(true);

            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JPanel canvas;
    private javax.swing.JButton jButtonBacktracking;
    private javax.swing.JButton jButtonBrandandBound;
    private javax.swing.JButton jButtonGenerateTree;
    private javax.swing.JButton jButtonSort;
    private javax.swing.JFileChooser jFileChooserOpen;
    private javax.swing.JFileChooser jFileChooserSaveAs;
    private javax.swing.JLabel jLabelSet;
    private javax.swing.JLabel jLabelSort;
    private javax.swing.JLabel jLabelTerget;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemOpen;
    private javax.swing.JMenuItem jMenuItemSave;
    private javax.swing.JMenuItem jMenuItemSaveAs;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField jTextFieldSet;
    private javax.swing.JTextField jTextFieldTerget;
    private javax.swing.JPanel manu;
    // End of variables declaration//GEN-END:variables
}

class SubSet {

    int set[];
    int sum;
    int k;

    Stack<Integer> solutionSet;
    Stack<Integer> solutionSetbinary;
    StringBuilder sb = new StringBuilder();
    boolean hasSolution;

    SubSet(int set[], int sum) {
        this.set = set;
        this.sum = sum;
        this.solutionSet = new Stack<>();
        this.solutionSetbinary = new Stack<>();
        hasSolution = false;
    }

    public Stack<Integer> getSolutionSet() {
        return solutionSet;
    }

    public Stack<Integer> getSolutionSetbinary() {
        return solutionSetbinary;
    }

    public void solve(int s, int idx) {
        this.k = idx;
        if (s > sum) {
            return;
        }

        if (s == sum) {
            hasSolution = true;

            displaySolutionSet();

            return;
        }

        for (int i = idx; i < set.length; i++) {

            solutionSet.push(set[i]);
            solutionSetbinary.push(1);

            solve(s + set[i], i + 1);

            for (int j = i; j < k; j++) {
                solutionSetbinary.pop();
            }
            solutionSetbinary.push(0);
            solutionSet.pop();
        }
    }

    public void displaySolutionSet() {

        sb.append("solutionSet : ");
        System.out.print("solutionSet : ");
        for (Integer item : solutionSet) {
            sb.append(item.toString() + " ");
            System.out.print(item + " ");
        }
        System.out.println();
        sb.append("\nsolutionSetBinary : ");
        System.out.print("solutionSetBinary : ");
        for (int i = 0; i < set.length; i++) {
            if (solutionSetbinary.size() > i) {
                sb.append(solutionSetbinary.get(i).toString() + " ");
                System.out.print(solutionSetbinary.get(i) + " ");
            } else {
                sb.append("0 ");
                System.out.print("0 ");
            }
        }
        sb.append("\n");
        System.out.println();

    }

}
