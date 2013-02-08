/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProfileSearch.java
 *
 * Created on 13 Nov, 2009, 8:33:53 PM
 */

package swiftmodeller;
import java.io.*;
import javax.swing.table.AbstractTableModel;
import java.util.StringTokenizer;
import java.net.*;
import java.awt.*;
/**
 *
 * @author WOLVERINE
 */
public class ProfileSearch extends javax.swing.JFrame {

    /** Creates new form ProfileSearch */
    public ProfileSearch() {
        
        File file = new File(workpath + sep + "build_profile.prf");
        contents = new StringBuffer();
        BufferedReader reader = null;
        contents2 = new StringBuffer();
        
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            while ((text = reader.readLine()) != null)
            {
                if(Character.isDigit(text.charAt(4)))
                {
                    contents.append(text.substring(0, 108)).append("\n");
                }
                else
                {
                    contents2.append(text).append("\n");
                }
            }
            String str = contents2.substring((contents2.indexOf("\n")-2),contents2.indexOf("\n"));
            if(str.charAt(0) == ' ')
            {
                str = str.substring(1);
            }
            rowcount = Integer.parseInt(str);

            if(rowcount == 1)
            {
                new ErrorClose(this, true, "No Match Found\nKindly do a BLASTp search and download the templates");
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

        initialize();
        initComponents();
        this.addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent e)
            {
                pulltheplug();
            }
        });
        jCheckBox1.setVisible(false);
        jButton5.setVisible(false);
        this.setTitle("DataBase Search Results");
    }

    private void initialize()
    {
        Object record[][] = new Object[rowcount][columncount];
        String str = new String();
        int i = 0;
        StringTokenizer st1 = new StringTokenizer(contents.toString(), "\n");
        while (st1.hasMoreTokens() && i < rowcount)
        {
            record[i][0] = Boolean.FALSE;
            str = st1.nextToken();
            StringTokenizer st2 = new StringTokenizer(str);
            int j = 1;
            int counter = 0;
            while (st2.hasMoreTokens() && j < columncount)
            {
                if(counter == 1 || counter == 2 || counter == 10 || counter == 11)
                {
                    record[i][j] = st2.nextToken();
                    j++;
                }
                else
                {
                    st2.nextToken();
                }
                counter++;
            }
            i++;
        }
        model = new TblModel(record);
    }

    class TblModel extends AbstractTableModel {
        Object record[][];
        private String[] columnNames = {"SELECT TEMPLATE",
                                        "SEQUENCE CODE",
                                        "TYPE",
                                        "SEQUENCE IDENTITY %",
                                        "E-VALUE"};

        public TblModel(Object record[][]) {
            this.record = record;
        }
        public int getColumnCount() {
            return columncount;
        }
        public int getRowCount() {
            return rowcount;
        }
        public String getColumnName(int col) {
            return columnNames[col];
        }
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            getRecord(rowIndex)[columnIndex] = value;
            super.fireTableCellUpdated(rowIndex, columnIndex);
        }
        public Object getValueAt(int rowIndex, int columnIndex) {
            return getRecord(rowIndex)[columnIndex];
        }
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if(columnIndex == 0)
                return true;
            else
                return false;
        }
        public Class getColumnClass(int columnIndex) {
            if (record == null || record.length == 0) {
                return Object.class;
            }
            Object o = getValueAt(0, columnIndex);
            return o == null ? Object.class : o.getClass();
        }
        private Object[] getRecord(int rowIndex) {
            return record[rowIndex];
        }
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(swiftmodeller.SwiftModellerApp.class).getContext().getResourceMap(ProfileSearch.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTextArea1.setBackground(resourceMap.getColor("jTextArea1.background")); // NOI18N
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(resourceMap.getFont("jTextArea1.font")); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText(contents2.toString());
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane3.setViewportView(jTextArea1);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setFont(resourceMap.getFont("jTable1.font")); // NOI18N
        jTable1.setModel(model);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setRowHeight(23);
        jTable1.setRowMargin(5);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(resourceMap.getFont("jButton2.font")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(resourceMap.getFont("jButton3.font")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(resourceMap.getFont("jCheckBox1.font")); // NOI18N
        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N

        jButton5.setFont(resourceMap.getFont("jButton5.font")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton5MousePressed(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE))
                .addGap(63, 63, 63))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(326, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(85, 85, 85)
                .addComponent(jButton2)
                .addGap(78, 78, 78)
                .addComponent(jButton3)
                .addGap(262, 262, 262))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(479, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(421, 421, 421))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(391, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addGap(381, 381, 381))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(29, 29, 29)
                .addComponent(jCheckBox1)
                .addGap(26, 26, 26)
                .addComponent(jButton5)
                .addGap(20, 20, 20))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-955)/2, (screenSize.height-682)/2, 955, 682);
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        for (int i=0; i<rowcount; i++)
        {
            model.setValueAt(Boolean.FALSE, i, 0);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        for (int i=0; i<rowcount; i++)
        {
            model.setValueAt(Boolean.TRUE, i, 0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if((jCheckBox1.isSelected() == false) && (chngv == 1))
        {
            new SUploadAlign(seltmpltchn, title, this.getComponent(0));
            new FlushScript(this);
            suaopfile = SUploadAlign.opfile + ".pap";
            notalign = 1;
            new Alignment().setVisible(true);
        }
        else if((jCheckBox1.isSelected() == false) && (chngv > 1))
        {
            new MUploadCompare(seltmpltchn, this.getComponent(0));
            new FlushScript(this);
            new Compare().setVisible(true);
        }
        else if (jCheckBox1.isSelected() == true)
        {
            new MUploadAlign(seltmpltchn, title, this.getComponent(0));
            new FlushScript(this);
            muaopfile = MUploadAlign.muaopfile + ".pap";
            notalign = 2;
            new Alignment().setVisible(true);
        }
        this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        Object rowdata[];
        this.setCursor(new Cursor(3));
        for(int i = 1; i < rowcount; i++)
        {
            rowdata = model.getRecord(i);
            if(rowdata[0] == Boolean.TRUE)
            {
                seltmplt.append(rowdata[1].toString().substring(0, 4)).append("\n");
                seltmpltchn.append(rowdata[1].toString().substring(0, 4)).append(":");
                if(rowdata[1].toString().length() == 5)
                {
                    seltmpltchn.append(rowdata[1].toString().substring(4)).append(";").append("\n");
                }
                else
                {
                    seltmpltchn.append("A;").append("\n");
                }
                chngv++;
            }
        }
        dwnldtmplt(seltmplt.toString());
        if(chngv > 1)
        {
            jCheckBox1.setVisible(true);
        }
        jButton5.setVisible(true);
        jButton3.setText("Download");
        this.setCursor(new Cursor(0));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MousePressed
        jButton5.setText("PROCESSING ...");
    }//GEN-LAST:event_jButton5MousePressed

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        jButton3.setText("DOWNLOADING ...");
    }//GEN-LAST:event_jButton3MousePressed

    private void dwnldtmplt(String tmplts)
    {
        StringTokenizer st = new StringTokenizer(tmplts, "\n");
        while(st.hasMoreTokens())
        {
            String path = new String("ftp://ftp.wwpdb.org/pub/pdb/data/structures/all/pdb/");
            String filename = new String("pdb" + st.nextToken() + ".ent.gz");
            File file = null;
            path = path + filename;
            fileDownload(path, (workpath + sep));
            try
            {
                file = new File(workpath + sep + filename);
                if(file.length() == 0)
                {
                    Thread.sleep(1000);
                }
            }
            catch (InterruptedException e)
            {
                new ErrorClose(this, true, "Thread Interrupted");
            }
            new ExtractFile(filename, this);
            System.gc();
            while(file.exists())
            {
                file.delete();
            }
        }
    }

    private void fileUrl(String fAddress, String localFileName, String destinationDir)
    {
        OutputStream outStream = null;
        URLConnection  uCon = null;
        InputStream is = null;
        try
        {
            URL Url;
            byte[] buf;
            int ByteRead, ByteWritten=0;
            Url= new URL(fAddress);
            outStream = new BufferedOutputStream(new FileOutputStream(destinationDir + sep + localFileName));

            uCon = Url.openConnection();
            is = uCon.getInputStream();
            buf = new byte[size];
            while ((ByteRead = is.read(buf)) != -1)
            {
                outStream.write(buf, 0, ByteRead);
                ByteWritten += ByteRead;
            }
        }
        catch (MalformedURLException e)
        {
            new ErrorClose(this, true, "URL Error");
        }
        catch (IOException e)
        {
            new ErrorClose(this, true, "I/O Error");
        }
        finally
        {
            try
            {
                is.close();
                outStream.close();
            }
            catch (IOException e)
            {
                new ErrorClose(this, true, "I/O Error");
            }
        }
    }
    private void fileDownload(String fAddress, String destinationDir)
    {

        int slashIndex =fAddress.lastIndexOf('/');
        int periodIndex =fAddress.lastIndexOf('.');

        String fileName=fAddress.substring(slashIndex + 1);

        if ((periodIndex >= 1) && (slashIndex >= 0) && (slashIndex < fAddress.length()-1))
        {
            fileUrl(fAddress,fileName,destinationDir);
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
                new ProfileSearch().setVisible(true);
                }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    private String title = SwiftModellerView.title;
    private String sep = SwiftModellerView.sep;
    private String workpath = SwiftModellerView.workpath;
    public static String suaopfile = new String();
    public static String muaopfile = new String();
    public static int notalign = 0;
    private TblModel model;
    private StringBuffer contents;
    private StringBuffer contents2;
    private int rowcount;
    private int columncount = 5;
    private StringBuffer seltmplt = new StringBuffer();
    private StringBuffer seltmpltchn = new StringBuffer();
    final static int size = 1024;
    public static int chngv = 0;
}