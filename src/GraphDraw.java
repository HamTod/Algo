
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
public class GraphDraw extends javax.swing.JFrame {

    /**
     * Creates new form GraphDraw
     */
    public GraphDraw() {
        initComponents();
    }

    ArrayList<Vertex> Vertexs = new ArrayList<>();
    ArrayList<Edge_> Edge_s = new ArrayList<>();
    File file;

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
        graphDrawJPanel = new GraphDrawJPanel();
        manu = new javax.swing.JPanel();
        jLabelSet = new javax.swing.JLabel();
        jTextFieldSet = new javax.swing.JTextField();
        jLabelSort = new javax.swing.JLabel();
        jButtonGenerateTree = new javax.swing.JButton();
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
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        canvas.setBackground(new java.awt.Color(204, 255, 255));
        canvas.setPreferredSize(new java.awt.Dimension(800, 680));
        canvas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                canvasMouseDragged(evt);
            }
        });
        canvas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canvasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                canvasMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                canvasMouseReleased(evt);
            }
        });
        canvas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                canvasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                canvasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                canvasKeyTyped(evt);
            }
        });

        graphDrawJPanel.setBackground(new java.awt.Color(204, 255, 255));
        graphDrawJPanel.setPreferredSize(new java.awt.Dimension(800, 680));

        javax.swing.GroupLayout graphDrawJPanelLayout = new javax.swing.GroupLayout(graphDrawJPanel);
        graphDrawJPanel.setLayout(graphDrawJPanelLayout);
        graphDrawJPanelLayout.setHorizontalGroup(
            graphDrawJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        graphDrawJPanelLayout.setVerticalGroup(
            graphDrawJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout canvasLayout = new javax.swing.GroupLayout(canvas);
        canvas.setLayout(canvasLayout);
        canvasLayout.setHorizontalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graphDrawJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        canvasLayout.setVerticalGroup(
            canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graphDrawJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        manu.setBackground(new java.awt.Color(255, 204, 204));

        jLabelSet.setText("Set");

        jTextFieldSet.setText("{1,2,3,4,5,6,7,8,9}");
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
        jButtonGenerateTree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerateTreeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout manuLayout = new javax.swing.GroupLayout(manu);
        manu.setLayout(manuLayout);
        manuLayout.setHorizontalGroup(
            manuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(manuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldSet, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jButtonGenerateTree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(manuLayout.createSequentialGroup()
                        .addGroup(manuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSet)
                            .addComponent(jLabelSort))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
    }// </editor-fold>//GEN-END:initComponents

    private void canvasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasMouseClicked
        // TODO add your handling code here:
        /*int x = evt.getX();
        int y = evt.getY();
        selected(x, y);
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            if (!Vertexs.contains(selected)) {
                Vertex TempVertex = new Vertex(x, y);
                Vertexs.add(TempVertex);
            }
        }
        draw();*/
    }//GEN-LAST:event_canvasMouseClicked

    private void canvasMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasMouseDragged
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY();
        if (mode.equals("Vertex")) {
            if (selected != null) {
                if (selected instanceof Vertex) {
                    Vertex s = (Vertex) selected;
                    for (Edge_ t : Edge_s) {
                        if (t.vertexA == s || t.vertexB == s) {
                            int difx = x - s.x;
                            int dify = y - s.y;
                            if (t.vertexA != t.vertexB) {
                                if (t.vertexA != null) {
                                    t.x_center = (t.vertexA.x + t.vertexB.x) / 2;
                                    t.y_center = (t.vertexA.y + t.vertexB.y) / 2;
                                }
                            } else {
                                t.x_center += difx;
                                t.y_center += dify;
                            }

                        }
                    }
                    s.x = x;
                    s.y = y;
                } else {
                    Edge_ t = (Edge_) selected;
                    t.x_center = x;
                    t.y_center = y;
                }
            }

        } else if (mode.equals("Edge_")) {
            try {
                Vertex Vertex = null;
                for (Vertex s : Vertexs) {
                    if (s.inCircle(x, y)) {
                        Vertex = s;
                    }
                }
                if (Vertex != null) {
                    if (Vertex != TempEdge.vertexA) {
                        double angle = Math.atan2(TempEdge.vertexA.y - Vertex.y, TempEdge.vertexA.x - Vertex.x);
                        double dx = Math.cos(angle);
                        double dy = Math.sin(angle);
                        TempEdge.x1 = Vertex.x + (int) (Vertex.r * dx);
                        TempEdge.y1 = Vertex.y + (int) (Vertex.r * dy);
                    } else {
                        TempEdge.x1 = x;
                        TempEdge.y1 = y;
                    }
                } else {
                    TempEdge.x1 = x;
                    TempEdge.y1 = y;
                }
            } catch (Exception ex) {

            }
        }
        draw();
    }//GEN-LAST:event_canvasMouseDragged

    private void canvasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasMouseReleased
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY();
        if (mode.equals("Vertex")) {
            TempEdge = null;
        } else if (mode.equals("Edge_")) {
            try {
                TempEdge.x1 = x;
                TempEdge.y1 = y;
                Vertex vertexB = null;
                for (Vertex s : Vertexs) {
                    if (s.inCircle(x, y)) {
                        TempEdge.x1 = s.x;
                        TempEdge.y1 = s.y;
                        vertexB = s;
                        Edge_ edge = new Edge_(TempEdge.vertexA, vertexB);

                        if (s != TempEdge.vertexA) {
                            edge.x_center = (TempEdge.vertexA.x + s.x) / 2;
                            edge.y_center = (TempEdge.vertexA.y + s.y) / 2;
                        } else {
                            double angle = Math.atan2(y - TempEdge.vertexA.y, x - TempEdge.vertexA.x);
                            double dx = Math.cos(angle);
                            double dy = Math.sin(angle);

                            int rc = 3 * TempEdge.vertexA.r;

                            edge.x_center = TempEdge.vertexA.x + (int) (dx * rc);
                            edge.y_center = TempEdge.vertexA.y + (int) (dy * rc);

                        }

                        Edge_s.add(edge);
                        break;
                    }
                }
                TempEdge = null;
            } catch (Exception ex) {
            }
        }
        draw();
    }//GEN-LAST:event_canvasMouseReleased

    private void canvasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasMousePressed
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY(); //testgit
        if (mode.equals("Edge_")) {
            TempEdge = new TempEdge(x, y);
            for (Vertex s : Vertexs) {
                if (s.inCircle(x, y)) {
                    TempEdge.setA(s);
                    break;
                }
            }
        }
    }//GEN-LAST:event_canvasMousePressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        /*if ((int) evt.getKeyChar() == 32) {
            //press space bar
            mode = "Edge_";
        }*/
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        // mode = "Vertex";
    }//GEN-LAST:event_formKeyReleased

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        // TODO add your handling code here:
        //System.out.println("key " + evt.getKeyChar() + " = " + (int) evt.getKeyChar());
/*       if ((int) evt.getKeyChar() == 19) {
            /*try {
                //ctrl + S
                save("backup.json");
            } catch (IOException ex) {

            }*/
/*        } else if ((int) evt.getKeyChar() == 15) {
            /*try {
                //ctrl + O 
                open("backup.json");
            } catch (IOException ex) {
                
            }*/
/*       } else if ((int) evt.getKeyChar() == 14) {
            //ctrl + N 

        } else if ((int) evt.getKeyChar() == 12) {
            //ctrl + L

        } else if ((int) evt.getKeyChar() == 18) {
            //ctrl + R 

        } else if ((int) evt.getKeyChar() == 9) {

        } else if ((int) evt.getKeyChar() == 1) {
            //ctrl + A 

        }

        if (selected instanceof Vertex) {
            Vertex s = (Vertex) selected;
            int status = (int) evt.getKeyChar();
            if (status == 8) { //delete
                if (s.name.length() > 1) {
                    s.name = s.name.substring(0, s.name.length() - 1).trim();
                } else {
                    s.name = "".trim();
                }

            } else if (status == 127) { // space
                ArrayList<Edge_> TempEdge = new ArrayList<>();
                for (Edge_ t : Edge_s) {
                    if (t.vertexA == selected || t.vertexB == selected) {
                        TempEdge.add(t);
                    }
                }
                for (Edge_ t : TempEdge) {
                    Edge_s.remove(t);
                }
                Vertexs.remove(selected);
                selected = null;

            } else {
                s.name += evt.getKeyChar();
                s.name = s.name.trim();
            }

        } else if (selected instanceof Edge_) {
            Edge_ t = (Edge_) selected;
            int status = (int) evt.getKeyChar();
            if (status == 8) {
                if (t.weight.length() > 1) {
                    t.weight = t.weight.substring(0, t.weight.length() - 1).trim();
                } else {
                    t.weight = "".trim();
                }
            } else if (status == 127) {
                Edge_s.remove(selected);
                selected = null;

            } else {
                if (evt.getKeyChar() == ' ') {
                    return;
                }
                t.weight += evt.getKeyChar();
                t.weight = t.weight.trim();
            }

        }
        draw();*/
    }//GEN-LAST:event_formKeyTyped

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
                Logger.getLogger(GraphDraw.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println(set);
        set = set.substring(1, set.length() - 1);
        System.out.println(set);
        String[] setArray = set.split(",");
        System.out.println("------------");
        for (int i = 0; i < setArray.length; i++) {
            System.out.println(setArray[i]);
        }
        System.out.println("------------");
        /*ArrayList<Integer> setArrayList = new ArrayList<Integer>();
        for (int i = 0; i < set.length() - 1; i++) {
            setArrayList.add(Integer.parseInt(set.split(",")[i]));
        }*/
        jTextFieldSet.setFocusable(false);
        jButtonGenerateTree.setFocusable(false);
    }//GEN-LAST:event_jButtonGenerateTreeMouseClicked

    private void canvasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_canvasKeyPressed
        // TODO add your handling code here:
        formKeyPressed(evt);
    }//GEN-LAST:event_canvasKeyPressed

    private void canvasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_canvasKeyReleased
        // TODO add your handling code here:
        formKeyReleased(evt);
    }//GEN-LAST:event_canvasKeyReleased

    private void canvasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_canvasKeyTyped
        // TODO add your handling code here:
        formKeyTyped(evt);
    }//GEN-LAST:event_canvasKeyTyped

    private void jButtonGenerateTreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerateTreeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGenerateTreeActionPerformed

    private void jTextFieldSetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSetMouseEntered
        // TODO add your handling code here:
        jTextFieldSet.setFocusable(true);
        jButtonGenerateTree.setFocusable(true);
    }//GEN-LAST:event_jTextFieldSetMouseEntered

    class GraphDrawJPanel extends javax.swing.JPanel {

        public GraphDrawJPanel() {
            setKeyBindings();
            initComponents();
        }

        private void setKeyBindings() {
            ActionMap actionMap = getActionMap();
            int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
            InputMap inputMap = getInputMap(condition);

            String vkDelete = "VK_Delete";
            String vkSpace = "VK_Space";
            
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), vkSpace);
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), vkDelete);

            actionMap.put(vkSpace, new KeyAction(vkSpace));
            actionMap.put(vkDelete, new KeyAction(vkDelete));
        }

        private class KeyAction extends AbstractAction {

            public KeyAction(String actionCommand) {
                putValue(ACTION_COMMAND_KEY, actionCommand);
            }

            @Override
            public void actionPerformed(ActionEvent actionEvt) {
                System.out.println(actionEvt.getActionCommand() + " pressed");
                if (selected instanceof Vertex) {
                    Vertex s = (Vertex) selected;
                    // int status = (int) evt.getKeyChar();
                    if (actionEvt.getActionCommand().equals("VK_Space")) { //delete
                        if (s.name.length() > 1) {
                            s.name = s.name.substring(0, s.name.length() - 1).trim();
                        } else {
                            s.name = "".trim();
                        }
                    } else if (actionEvt.getActionCommand().equals("VK_Delete")) { // space
                        ArrayList<Edge_> TempEdge = new ArrayList<>();
                        for (Edge_ t : Edge_s) {
                            if (t.vertexA == selected || t.vertexB == selected) {
                                TempEdge.add(t);
                            }
                        }
                        for (Edge_ t : TempEdge) {
                            Edge_s.remove(t);
                        }
                        Vertexs.remove(selected);
                        selected = null;
                    } else {
                        s.name += actionEvt;
                        s.name = s.name.trim();
                    }
                } else if (selected instanceof Edge_) {
                    Edge_ t = (Edge_) selected;
                    //int status = (int) evt.getKeyChar();
                    if (actionEvt.getActionCommand().equals("VK_Space")) {
                        if (t.weight.length() > 1) {
                            t.weight = t.weight.substring(0, t.weight.length() - 1).trim();
                        } else {
                            t.weight = "".trim();
                        }
                    } else if (actionEvt.getActionCommand().equals("VK_Delete")) {
                        Edge_s.remove(selected);
                        selected = null;
                    } else {
                       /* if (evt.getKeyChar() == ' ') {
                            return;
                        } */
                        t.weight += actionEvt;
                        t.weight = t.weight.trim();
                    }
                }
                draw();
            }
            
            
        }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                public void mouseDragged(java.awt.event.MouseEvent evt) {
                    formMouseDragged(evt);
                }
            });
            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    formMouseClicked(evt);
                }

                public void mousePressed(java.awt.event.MouseEvent evt) {
                    formMousePressed(evt);
                }

                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    formMouseReleased(evt);
                }
            });

        }// </editor-fold>                        

        private void formMouseClicked(java.awt.event.MouseEvent evt) {
            // TODO add your handling code here:
            int x = evt.getX();
            int y = evt.getY();
            selected(x, y);
            if (evt.getClickCount() == 2 && !evt.isConsumed()) {
                evt.consume();
                if (!Vertexs.contains(selected)) {
                    Vertex TempVertex = new Vertex(x, y);
                    Vertexs.add(TempVertex);
                }
            }
            draw();
        }

        private void formMouseDragged(java.awt.event.MouseEvent evt) {
            // TODO add your handling code here:
        }

        private void formMousePressed(java.awt.event.MouseEvent evt) {
            // TODO add your handling code here:
        }

        private void formMouseReleased(java.awt.event.MouseEvent evt) {
            // TODO add your handling code here:
        }

        // Variables declaration - do not modify                     
        // End of variables declaration                   
    }

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
            java.util.logging.Logger.getLogger(GraphDraw.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphDraw.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphDraw.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphDraw.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GraphDraw GD = new GraphDraw();
                GD.setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JPanel canvas;
    private GraphDrawJPanel graphDrawJPanel;
    private javax.swing.JButton jButtonGenerateTree;
    private javax.swing.JFileChooser jFileChooserOpen;
    private javax.swing.JFileChooser jFileChooserSaveAs;
    private javax.swing.JLabel jLabelSet;
    private javax.swing.JLabel jLabelSort;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemOpen;
    private javax.swing.JMenuItem jMenuItemSave;
    private javax.swing.JMenuItem jMenuItemSaveAs;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField jTextFieldSet;
    private javax.swing.JPanel manu;
    // End of variables declaration//GEN-END:variables
}
