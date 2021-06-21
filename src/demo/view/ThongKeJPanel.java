/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.view;

import demo.dao.DBConnect;
import demo.model.ThongKe;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MY PC
 */
public class ThongKeJPanel extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienJPanel
     */
    List<ThongKe> listn = new ArrayList<>();
    List<ThongKe> listx = new ArrayList<>();
    private DefaultTableModel tab = new DefaultTableModel();

    
    public ThongKeJPanel() {
        initComponents();
        initTab();
//        System.out.println(listn.size());
    }

    private void initTab() {
        String[] column =  new String[]{"ID","Tên hàng hóa","Đơn vị","Tồn kho hiện tại","Tổng số lượng nhập","Tổng số lượng xuất"};
            tab.setColumnIdentifiers(column);
            jtbNhap.setModel(tab);
           
        jtbNhap.setRowHeight(30);
       
        jtbNhap.getTableHeader().setFont(new Font("Arrial", Font.BOLD, 14));
        jtbNhap.getTableHeader().setPreferredSize(new Dimension(60,40));
    
        jtbNhap.getColumnModel().getColumn(0).setMaxWidth(60);
        jtbNhap.getColumnModel().getColumn(2).setMaxWidth(60);
        jtbNhap.getColumnModel().getColumn(1).setMinWidth(160);
      

    }
    
    private void fillTable() {
            tab.setRowCount(0);
            for(ThongKe tkn :listn){
                tab.addRow(new String[]{tkn.getMahh(),tkn.getTenhh(),tkn.getDvt(),Integer.toString(tkn.getTonkho()),Integer.toString(tkn.getTongsln()),Integer.toString(tkn.getTongslx())});
            }
            tab.fireTableDataChanged();           
        }
    
    private void setData(){
        jlbL.setText("Thống Kê Tổng Sản Phẩm Tháng " + (jmcM.getMonth()+1));
       Connection cons = DBConnect.getConnection();
            try {
                String sql = 
                "select hanghoa.mahh, tenhh, donvitinh, hanghoa.soluong,\n" +
                "case when month(ngaynhap) = '"+ (jmcM.getMonth()+1) +"' then sum(chitietnhap.soluong ) else 0 end as tongsln,\n" +
                "case when month(ngayxuat) = '"+ (jmcM.getMonth()+1) +"' then sum(chitietxuat.soluong ) else 0 end as tongslx\n" +
                "from hanghoa left join chitietnhap on hanghoa.mahh = chitietnhap.mahh left join hoadonnhap on hoadonnhap.manhap = chitietnhap.manhap left join chitietxuat on chitietxuat.mahh = hanghoa.mahh left join hoadonxuat on hoadonxuat.maxuat = chitietxuat.maxuat\n" +
                "group by mahh\n" +
                "order by mahh asc";  
                
                PreparedStatement ps = cons.prepareCall(sql);                
                ResultSet rs = ps.executeQuery();     
                
                while(rs.next()) {
                   ThongKe tkn = new ThongKe();
                   tkn.setMahh(rs.getString("mahh"));
                   tkn.setTenhh(rs.getString("tenhh"));
                   tkn.setDvt(rs.getString("donvitinh"));
                   tkn.setTongsln(rs.getInt("tongsln"));
                   tkn.setTongslx(rs.getInt("tongslx"));
                   tkn.setTonkho(rs.getInt("soluong"));
                   listn.add(tkn); 
                }
                int t = 0, n = 0, x = 0;
                for(ThongKe tk : listn) {
                    t+=tk.getTonkho();
                    n+=tk.getTongsln();
                    x+=tk.getTongslx();
                }
                jtfTK.setText(Integer.toString(t));
                jtfN.setText(Integer.toString(n));
                jtfX.setText(Integer.toString(x));
//                System.out.println(listn);
                rs.close();
                ps.close();
                cons.close();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
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

        jpnRoot = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbNhap = new javax.swing.JTable();
        jlbL = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtfTK = new javax.swing.JTextField();
        jtfN = new javax.swing.JTextField();
        jtfX = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jmcM = new com.toedter.calendar.JMonthChooser();
        btnXem = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));

        jpnRoot.setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jtbNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtbNhap);

        jlbL.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jlbL.setForeground(new java.awt.Color(255, 0, 51));
        jlbL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbL.setText("Thống Kê Tổng Sản Phẩm ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jlbL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbL, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Tổng đơn vị tồn kho:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Tổng đơn vị nhập:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Tổng đơn vị xuất:");

        jtfTK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jtfN.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jtfX.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Chọn tháng:");

        btnXem.setText("Xem");
        btnXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnRootLayout = new javax.swing.GroupLayout(jpnRoot);
        jpnRoot.setLayout(jpnRootLayout);
        jpnRootLayout.setHorizontalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnRootLayout.createSequentialGroup()
                        .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnRootLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jmcM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnRootLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(btnXem, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 343, Short.MAX_VALUE)
                        .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfTK)
                            .addComponent(jtfN)
                            .addComponent(jtfX, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        jpnRootLayout.setVerticalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnRootLayout.createSequentialGroup()
                        .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtfTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtfN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtfX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnRootLayout.createSequentialGroup()
                        .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jmcM, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnXem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jpnRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemActionPerformed
        // TODO add your handling code here:
        listn.removeAll(listn);
        setData();
        fillTable();
    }//GEN-LAST:event_btnXemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlbL;
    private com.toedter.calendar.JMonthChooser jmcM;
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JTable jtbNhap;
    private javax.swing.JTextField jtfN;
    private javax.swing.JTextField jtfTK;
    private javax.swing.JTextField jtfX;
    // End of variables declaration//GEN-END:variables
}