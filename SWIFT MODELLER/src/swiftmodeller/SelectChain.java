/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SelectChain.java
 *
 * Created on 2 Apr, 2010, 10:43:45 PM
 */

package swiftmodeller;

import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
/**
 *
 * @author WOLVERINE
 */
public class SelectChain extends javax.swing.JDialog {

    /** Creates new form SelectChain */
    public SelectChain(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initialize();

        initComponents();
    }

    private void initialize()
    {
        File file = new File(workpath + sep + "Template List.txt");
        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new FileReader(file));

            String text = null;
            while ((text = reader.readLine()) != null)
            {
                contents.append(text).append("\n");
                rowcount++;
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

        record = new Object[rowcount][columncount];
        combobox = new JComboBox[rowcount];
        editors = new ArrayList(rowcount);
        columnNames = new String[columncount];
        dce = new DefaultCellEditor[rowcount];
        int i = 0;
        StringTokenizer st1 = new StringTokenizer(contents.toString(), "\n");

        while(st1.hasMoreTokens() && i < rowcount)
        {
            String temp = st1.nextToken();
            String str1 = temp.substring(9, (temp.length()-1));
            StringTokenizer st2 = new StringTokenizer(str1, ",");
            combobox[i] = new JComboBox();
            while(st2.hasMoreTokens())
            {
                String str2 = st2.nextToken().trim();
                combobox[i].addItem(str2);
            }
            record[i][0] = new Boolean(false);
            record[i][1] = temp.substring(0, 4);
            record[i][2] = "A";
            dce[i] = new DefaultCellEditor(combobox[i]);
            editors.add(dce[i]);
            i++;
        }
        columnNames[0] = "Select Template(s)";
        columnNames[1] = "Sequence Code";
        columnNames[2] = "Select Chain";
        model = new DefaultTableModel(record, columnNames)
        {
            public void setValueAt(Object value, int rowIndex, int columnIndex)
            {
                getRecord(rowIndex)[columnIndex] = value;
                super.fireTableCellUpdated(rowIndex, columnIndex);
            }
            public Object getValueAt(int rowIndex, int columnIndex)
            {
                return getRecord(rowIndex)[columnIndex];
            }
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                if(columnIndex == 1)
                    return false;
                else
                    return true;
            }
            public Class getColumnClass(int columnIndex)
            {
                if (record == null || record.length == 0)
                {
                    return Object.class;
                }
                Object o = getValueAt(0, columnIndex);
                return o == null ? Object.class : o.getClass();
            }
            public Object[] getRecord(int rowIndex)
            {
                return record[rowIndex];
            }
        };
    }

    public void setUpChainColumn(JTable jTable1, TableColumn chainColumn) {

        for(int i = 0; i < rowcount; i++)
        {
            chainColumn.setCellEditor(new DefaultCellEditor(combobox[i]));
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setToolTipText("Click for combo box");
            chainColumn.setCellRenderer(renderer);
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

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(swiftmodeller.SwiftModellerApp.class).getContext().getResourceMap(SelectChain.class);
        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1 = new JTable(model)
        {
            public TableCellEditor getCellEditor(int row, int column)
            {
                if (column == 2)
                {
                    return (TableCellEditor)editors.get(row);
                }
                else
                return super.getCellEditor(row, column);
            }
        };
        jTable1.setName("jTable1"); // NOI18N
        setUpChainColumn(jTable1, jTable1.getColumnModel().getColumn(2));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, 0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jButton1)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        StringBuffer str = new StringBuffer();
        template = new StringBuffer();
        String chaindata = new String();
        String pdbdata = new String();
        for(int i = 0; i < rowcount; i++)
        {
            if(model.getValueAt(i, 0) == Boolean.TRUE)
            {
                chaindata = (String) model.getValueAt(i, 2);
                pdbdata = (String) model.getValueAt(i, 1);
                str.append(pdbdata).append(".pdb").append(":").append(chaindata).append(";").append("\n");
                template.append(pdbdata).append(".pdb, ");
            }
        }
        template.substring(0,(template.length()-2));

        try
        {
            File templates = new File(workpath + sep + "Template List.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(templates));
            writer.write(str.toString());
            writer.close();
        }
        catch (IOException e)
        {
            new ErrorClose(this, true, "I/O Error");
        }

        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SelectChain dialog = new SelectChain(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    private String workpath = SwiftModellerView.workpath;
    private String sep = SwiftModellerView.sep;
    private int columncount = 3;
    private int rowcount = 0;
    private Object[][] record;
    private StringBuffer contents = new StringBuffer();
    private JComboBox[] combobox;
    private String[] columnNames;
    private DefaultCellEditor[] dce;
    private ArrayList editors;
    private DefaultTableModel model;
    public static StringBuffer template;
}