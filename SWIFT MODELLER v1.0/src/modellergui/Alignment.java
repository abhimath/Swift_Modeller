/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Alignment.java
 *
 * Created on 15 Nov, 2009, 11:52:50 PM
 */

package modellergui;

import java.io.*;
/**
 *
 * @author WOLVERINE
 */
public class Alignment extends javax.swing.JFrame {
    /** Creates new form Alignment */
    public Alignment() {
        
        if (ModellerGUIView.notalign == 1)
        {
            filename = ModellerGUIView.suaopfile;
        }
        else if (ModellerGUIView.notalign == 2)
        {
            filename = ModellerGUIView.muaopfile;
        }
        else if (ProfileSearch.notalign == 1)
        {
            filename = ProfileSearch.suaopfile;
        }
        else if (ProfileSearch.notalign == 2)
        {
            filename = ProfileSearch.muaopfile;
        }
        else if (Compare.notalign == 1)
        {
            filename = Compare.suaopfile;
        }

        extractdata();
        extracttemplates();
        initComponents();
        this.addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent e)
            {
                pulltheplug();
            }
        });
        jButton1.setVisible(false);

        this.setTitle("Template - Target Alignment");
    }

    private void extractdata()
    {
        file = new File(workpath + sep + filename);
        BufferedReader reader = null;
        contents = new StringBuffer();

        try
        {
            reader = new BufferedReader(new FileReader(file));

            String text = null;
            while ((text = reader.readLine()) != null)
            {
                contents.append(text).append("\n");
            }
        }
        catch (FileNotFoundException e)
        {
            new ErrorClose(this, true, "File Not Found : " + file.getName());
        }
        catch (IOException e)
        {
            new ErrorClose(this, true, "I/O Error");
        }
        finally
        {
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            } catch (IOException e)
            {
                new ErrorClose(this, true, "I/O Error");
            }
        }
    }

    private void extracttemplates()
    {
        sb = new StringBuffer(filename.substring(0, (filename.length()-3)));
        sb.append("ali");
        file = new File(workpath + sep + sb.toString());
        BufferedReader reader = null;
        templates = new StringBuffer();

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            while ((text = reader.readLine()) != null)
            {
                if(text.startsWith(">"))
                {
                    templates.append(text.substring((text.indexOf(";") +1), text.length())).append("\n");
                }
            }
        }
        catch (FileNotFoundException e)
        {
            new ErrorClose(this, true, "File Not Found : " + file.getName());
        }
        catch (IOException e)
        {
            new ErrorClose(this, true, "I/O Error");
        }
        finally
        {
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            } catch (IOException e)
            {
                new ErrorClose(this, true, "I/O Error");
            }
        }
    }

    /**
	 * This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 * @return 
	 */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(modellergui.ModellerGUIApp.class).getContext().getResourceMap(Alignment.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setBackground(resourceMap.getColor("jTextArea1.background")); // NOI18N
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.setText(contents.toString());
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addGap(251, 251, 251)))
                .addGap(54, 54, 54))
            .addGroup(layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(114, 114, 114)))
                .addContainerGap(278, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        chk = templates.toString().split("\n");
        int start = Integer.valueOf(jTextField1.getText()) + 1;
        String str = Integer.toString(start);
        if(chk.length == 2)
        {
            new BuildModelSingle(sb.toString(), chk, "1", jTextField1.getText(), this.getComponent(0));
            readfile = "model-single.log";
            new FlushScript(this);
        }
        else if(chk.length > 2)
        {
            new BuildModelSingle(sb.toString(), chk, str, str, this.getComponent(0));
            new FlushScript(this);
            new BuildModelMult(sb.toString(), chk, jTextField1.getText(), this.getComponent(0));
            readfile = "model_mult.log";
            new FlushScript(this);
        }
        this.setVisible(false);
        new SelectModel().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped

        char abc = evt.getKeyChar();
        if(!(Character.isDigit(abc)))
        {
            evt.consume();
        }
        chkva = jTextField1.getText().length() + 1;
        if(chkva > 0)
        {
            jButton1.setVisible(true);
        }
        else
        {
            jButton1.setVisible(false);
        }
        if(jTextField1.getText().length() >= 2)
        {
            jTextField1.setEditable(false);
            if(abc == 8)
            {
                jTextField1.setEditable(true);
            }
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    public void pulltheplug()
    {
        new CloseApp(this, true);
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Alignment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    private String filename;
    private String workpath = ModellerGUIView.workpath;
    private String sep = ModellerGUIView.sep;
    private StringBuffer contents, templates, sb;
    private File file;
    private int chkva;
    public static String readfile;
    public static String[] chk;
}