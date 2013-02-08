/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Compare.java
 *
 * Created on 15 Nov, 2009, 10:14:14 PM
 */

package swiftmodeller;
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
class RadioButtonRenderer implements TableCellRenderer
{
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        if (value==null) return null;
        return (Component)value;
    }
}

class RadioButtonEditor extends DefaultCellEditor implements ItemListener
{
    private JRadioButton button;

    public RadioButtonEditor(JCheckBox checkBox)
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

public class Compare extends javax.swing.JFrame {

    /** Creates new form Compare */
    public Compare() {

        File file = new File(workpath + sep + "compare.log");
        contents = new StringBuffer();
        templates = new StringBuffer();
        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = "";

            while (text != null)
            {
                text = reader.readLine();
                if(text.equals("Weighted pair-group average clustering based on a distance matrix:"))
                {
                    while ((text = reader.readLine()) != null)
                    {
                        StringTokenizer st = new StringTokenizer(text);
                        while(st.hasMoreTokens())
                        {
                            if(!(st.nextToken().equals("Total")))
                            {
                                contents.append(text).append("\n");
                                StringTokenizer strtok = new StringTokenizer(text);
                                while(strtok.hasMoreTokens())
                                {
                                    String str = strtok.nextToken();
                                    if(Character.isDigit(str.charAt(0)) && Character.isLetter(str.charAt(1)))
                                    {
                                        templates.append(str).append("\n");
                                    }
                                }
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
            if(!(contents.toString().length() > 0))
            {
                new ErrorClose(this, true, "Too few templates to compare\nKindly use multiple templates option");
            }
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

        temp = templates.toString().split("\n");
        data = new Object[temp.length][2];
        columnNames = new String[2];
        jr = new JRadioButton[temp.length];
        
        for(int i = 0 ; i < temp.length; i++)
        {
            jr[i] = new JRadioButton("");
            data[i][0] = temp[i];
            data[i][1] = jr[i];
        }
        columnNames[0] = "Sequence Code";
        columnNames[1] = "Select Template";
        dm = new DefaultTableModel(data, columnNames);

        initComponents();
        this.addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent e)
            {
                pulltheplug();
            }
        });

        jTable1.getColumn("Select Template").setCellRenderer(new RadioButtonRenderer());
        jTable1.getColumn("Select Template").setCellEditor(new RadioButtonEditor(new JCheckBox()));

        buttonGroup1 = new ButtonGroup();
        for(int i = 0 ; i < temp.length; i++)
        {
            buttonGroup1.add((JRadioButton)dm.getValueAt(i,1));
        }

        this.setTitle("Template Comparison");
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
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(swiftmodeller.SwiftModellerApp.class).getContext().getResourceMap(Compare.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setBackground(resourceMap.getColor("jTextArea1.background")); // NOI18N
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(resourceMap.getFont("jTextArea1.font")); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText(contents.toString());
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

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
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel1)
                .addContainerGap(104, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(416, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(408, 408, 408))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE))
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JRadioButton chk = new JRadioButton();
        sb = new StringBuffer();
        for(int i = 0; i < temp.length; i++)
        {
            chk = (JRadioButton)dm.getValueAt(i,1);
            if(chk.isSelected() == true)
            {
                String str = dm.getValueAt(i,0).toString();
                sb.append(str.substring(0, 4)).append(":").append(str.substring(4)).append(";");
            }
        }
        new SUploadAlign(sb, title, this.getComponent(0));
        new FlushScript(this);
        suaopfile = SUploadAlign.opfile + ".pap";
        notalign = 1;
        new Alignment().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        jButton1.setText("PROCESSING ...");
    }//GEN-LAST:event_jButton1MousePressed

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
                new Compare().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    private String workpath = SwiftModellerView.workpath;
    private String title = SwiftModellerView.title;
    private String sep = SwiftModellerView.sep;
    private StringBuffer contents, templates, sb;
    private DefaultTableModel dm;
    private Object[][] data;
    private String[] columnNames, temp;
    private JRadioButton[] jr;
    public static String suaopfile = new String();
    public static int notalign = 0;
}