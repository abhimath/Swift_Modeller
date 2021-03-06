/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModelSingle.java
 *
 * Created on 16 Nov, 2009, 1:01:17 AM
 */

package modellergui;

import java.io.*;
import java.util.StringTokenizer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
/**
 *
 * @author WOLVERINE
 */
class RadioButtonRenderer1 implements TableCellRenderer
{
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        if (value==null) return null;
        return (Component)value;
    }
}

class RadioButtonEditor1 extends DefaultCellEditor implements ItemListener
{
    private JRadioButton button;

    public RadioButtonEditor1(JCheckBox checkBox)
    {
        super(checkBox);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        if (value==null) return null;
        button = (JRadioButton)value;
        button.addItemListener(this);
        return (Component)value;
    }

    public Object getCellEditorValue()
    {
        button.removeItemListener(this);
        return button;
    }

    public void itemStateChanged(ItemEvent e)
    {
        super.fireEditingStopped();
    }
}

public class SelectModel extends javax.swing.JFrame {

    /** Creates new form SelectModel */
    public SelectModel() {

        labels = new String[4];
        colours = new String[4];
        profiles = new String[4];
        extractdata();
        data = new Object[temp.length][5];
        columnNames = new String[5];
        jr = new JRadioButton[temp.length];

        for(int i = 0 ; i < temp.length; i++)
        {
            jr[i] = new JRadioButton("");
            data[i][0] = jr[i];
            int j = 1;
            StringTokenizer st = new StringTokenizer(temp[i]);
            while(st.hasMoreTokens())
            {
                data[i][j] = st.nextToken();
                j++;
            }
        }

        columnNames[0] = "Select File";
        columnNames[1] = "Filename";
        columnNames[2] = "molpdf";
        columnNames[3] = "DOPE score";
        columnNames[4] = "GA341 score";

        dm = new DefaultTableModel(data, columnNames);

        initComponents();
        this.addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent e)
            {
                pulltheplug();
            }
        });
        
        jTable1.getColumn("Select File").setCellRenderer(new RadioButtonRenderer1());
        jTable1.getColumn("Select File").setCellEditor(new RadioButtonEditor1(new JCheckBox()));

        buttonGroup1 = new ButtonGroup();
        for(int i = 0 ; i < temp.length; i++)
        {
            buttonGroup1.add((JRadioButton)dm.getValueAt(i,0));
        }
        
        this.setTitle("Models");
    }

    private void extractdata()
    {
        File file = new File(workpath + sep + readfile);
        contents = new StringBuffer();
        BufferedReader reader = null;

        try
        {
            String text = "";
            reader = new BufferedReader(new FileReader(file));
            while (text != null)
            {
                text = reader.readLine();
                if(text.equals(">> Summary of successfully produced models:"))
                {
                    while ((text = reader.readLine()) != null)
                    {
                        StringTokenizer st = new StringTokenizer(text);
                        while(st.hasMoreTokens())
                        {
                            if(!(st.nextToken().equals("Total")))
                            {
                                contents.append(text).append("\n");
                                break;
                            }
                            else
                            {
                                break;
                            }
                        }
                    }
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

        contents.replace(0, contents.length(), contents.substring(contents.lastIndexOf("--") + 3, contents.length()));
        temp = contents.toString().split("\n");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(modellergui.ModellerGUIApp.class).getContext().getResourceMap(SelectModel.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        jTable1 = new JTable(dm) {
            public void tableChanged(TableModelEvent e) {
                super.tableChanged(e);
                repaint();
            }
        };
        jTable1.setRowHeight(24);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jLabel1)
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(jButton1)
                .addContainerGap(259, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jButton1)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JRadioButton chk = new JRadioButton();
        cmod = new String();
        for(int i = 0; i < temp.length; i++)
        {
            chk = (JRadioButton)dm.getValueAt(i,0);
            if(chk.isSelected() == true)
            {
                cmod = dm.getValueAt(i,1).toString();
            }
        }

        if(template.length == 2)
        {
            labels[0] = "Basic Model";
            labels[1] = "Template";
            colours[0] = "red";
            colours[1] = "green";
            profiles[0] = cmod;
            profiles[1] = template[0] + ".pdb";
            formSequence();
            File log = new File(workpath + sep + "evaluate_model.log");
            System.gc();
            while(log.exists())
            {
                log.delete();
            }
            new EvaluateModel(cmod, this.getComponent(0));
            new FlushScript(this);
            log = new File(workpath + sep + "evaluate_template.log");
            System.gc();
            while(log.exists())
            {
                log.delete();
            }
            new EvaluateTemp(template[0], this.getComponent(0));
            new FlushScript(this);
            StringBuffer sb = new StringBuffer(template[0]);
            sb.insert(4, ".pdb:").append(";");
            log = new File(workpath + sep + "align2d.log");
            System.gc();
            while(log.exists())
            {
                log.delete();
            }
            new SUploadAlign(sb, profiles[0].substring(0, profiles[0].length()-4), this.getComponent(0));
            new FlushScript(this);
            log = new File(workpath + sep + "plot_profiles.log");
            System.gc();
            while(log.exists())
            {
                log.delete();
            }
            new PlotGraph(labels, colours, profiles, this.getComponent(0));
            new FlushScript(this);
        }
        else if(template.length > 2)
        {
            labels[0] = "Basic Model";
            labels[2] = "Multiple Templates";
            colours[0] = "red";
            colours[2] = "blue";
            File log = new File(workpath + sep + "evaluate_model.log");
            System.gc();
            while(log.exists())
            {
                log.delete();
            }
            new EvaluateModel(cmod, this.getComponent(0));
            new FlushScript(this);
            readfile = "model-single.log";
            extractdata();
            StringTokenizer st = new StringTokenizer(temp[0]);
            rmod = st.nextToken();
            profiles[0] = rmod;
            profiles[2] = cmod;
            System.gc();
            while(log.exists())
            {
                log.delete();
            }
            new EvaluateModel(rmod, this.getComponent(0));
            new FlushScript(this);
            log = new File(workpath + sep + "align_seq.log");
            System.gc();
            while(log.exists())
            {
                log.delete();
            }
            new SeqAlignment(profiles, this.getComponent(0));
            new FlushScript(this);
            log = new File(workpath + "/plot_profiles.log");
            System.gc();
            while(log.exists())
            {
                log.delete();
            }
            new PlotGraph(labels, colours, profiles, this.getComponent(0));
            new FlushScript(this);
        }
        
        new EvaluationGraph().setVisible(true);
        this.setCursor(new Cursor(0));
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formSequence()
    {
        File file = new File(workpath + sep + title + ".ali");
        BufferedReader reader = null;
        String seqpath =  workpath + sep + profiles[0].substring(0, profiles[0].length()-4) + ".ali";
        StringBuffer seqcontents = new StringBuffer();

        File seq = new File(seqpath);
        BufferedWriter writer = null;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            while ((text = reader.readLine()) != null)
            {
                seqcontents.append(text).append("\n");
            }

            seqcontents.replace((seqcontents.indexOf("P1;") + 3), (seqcontents.indexOf("sequence")), (profiles[0].substring(0, profiles[0].length()-4) + "\n"));
            seqcontents.replace((seqcontents.indexOf("sequence:") + 9), seqcontents.indexOf(":::::::"), profiles[0].substring(0, profiles[0].length()-4));

            writer = new BufferedWriter(new FileWriter(seq));
            writer.write(seqcontents.toString());
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            new ErrorClose(this, true, "File Not Found : " + file.getName());
        }
        catch (IOException e)
        {
            new ErrorClose(this, true, "I/O Error");
        }
    }

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
                new SelectModel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    private String workpath = ModellerGUIView.workpath;
    private String title = ModellerGUIView.title;
    private String sep = ModellerGUIView.sep;
    private String readfile = Alignment.readfile;
    private String rmod;
    private String[] template = Alignment.chk;
    private String[] columnNames, temp;
    private StringBuffer contents;
    private DefaultTableModel dm;
    private Object[][] data;
    private JRadioButton[] jr;
    public static String cmod;
    public static String[] labels, colours, profiles;
}