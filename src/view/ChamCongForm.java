/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import dao.AccountDAO;
import dao.ChamCongDAO;
import dto.ChamCongDTO;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Account;
import model.ChamCong;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ChamCongForm extends javax.swing.JInternalFrame {

    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");
    
    private List<ChamCongDTO> chamCongList = new ArrayList<>();

    public DecimalFormat getFormatter() {
        return formatter;
    }

    public SimpleDateFormat getFormatDate() {
        return formatDate;
    }

    public ChamCongForm(Account accCur) {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        tblChamCong.setDefaultEditor(Object.class, null);
        initTable();
        loadDataToTable();
        jDateChooserFrom.setDateFormatString("dd/MM/yyyy");
        jDateChooserTo.setDateFormatString("dd/MM/yyyy");
        initAccoutCombobox();
        
    }
    
    void initAccoutCombobox() {
        List<String> accList = AccountDAO.getInstance().selectAll()
                .stream()
                .map(item -> item.getUser() + " - " + item.getFullName())
                .collect(Collectors.toList());
        accList.add(0, "Tất cả");
        String[] accComboboxData = accList.stream().toArray(String[]::new);
        jComboBoxS.setModel(new javax.swing.DefaultComboBoxModel<>(accComboboxData));
        jComboBoxS.addActionListener(this::cbxAccountChange);
    }
    
    void cbxAccountChange(ActionEvent evt) {
        String value = ((JComboBox)evt.getSource()).getSelectedItem().toString();
        String[] data = value.split(" - ");
        String keyword = "";
        if (!value.equals("Tất cả")) {
            if (data.length != 2) return;
            keyword = data[0];
        }   
        List<ChamCongDTO> ccList = ChamCongDAO.getInstance().findByNguoiChamCong(keyword);
        loadDataToTableArr(ccList);
    }
    

    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{
            "STT", "Mã chấm công", "Ngày chấm công", "Mã nhân viên", "Tên nhân viên", 
            "Giờ vào", "Giờ ra", "Trong giờ hành chính",
            "Ngoài giờ hành chính", "Tổng"
        };
        tblModel.setColumnIdentifiers(headerTbl);
        tblChamCong.setModel(tblModel);
        tblChamCong.getColumnModel().getColumn(0).setPreferredWidth(5);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblChamCong.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblChamCong.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblChamCong.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tblChamCong.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tblChamCong.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tblChamCong.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tblChamCong.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
    }

    public void loadDataToTable() {
        try {
            chamCongList = ChamCongDAO.getInstance().getAll();
            tblModel.setRowCount(0);
            for (int i = 0; i < chamCongList.size(); i++) {
                tblModel.addRow(new Object[]{
                    i + 1, 
                    chamCongList.get(i).getMaChamCong(),
                    chamCongList.get(i).getNgayChamCong(),
                    chamCongList.get(i).getMaNguoiChamCong(),
                    chamCongList.get(i).getTenNguoiChamCong(),
                    chamCongList.get(i).getGioVao(),
                    chamCongList.get(i).getGioRa(),
                    String.format("%.2f", chamCongList.get(i).getTrongGioHanhChinh()),
                    String.format("%.2f", chamCongList.get(i).getNgoaiGioHanhChinh()),
                    String.format("%.2f", chamCongList.get(i).getTong())
                });
            }
        } catch (Exception e) {
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

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jComboBoxS = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChamCong = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jDateChooserFrom = new com.toedter.calendar.JDateChooser();
        jDateChooserTo = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        searchDateBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1180, 770));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));
        jToolBar1.setRollover(true);

        btnDelete.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_delete_40px.png"))); // NOI18N
        btnDelete.setText("Xoá");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDelete);

        btnEdit.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_edit_40px.png"))); // NOI18N
        btnEdit.setText("Sửa");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEdit);
        jToolBar1.add(jSeparator1);

        jButton6.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_spreadsheet_file_40px.png"))); // NOI18N
        jButton6.setText("Xuất Excel");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm theo nhân viên"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBoxS.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        jComboBoxS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        jPanel3.add(jComboBoxS, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 180, 40));

        tblChamCong.setFont(new java.awt.Font("SF Pro Display", 0, 15)); // NOI18N
        tblChamCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblChamCong);

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm theo ngày", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jDateChooserFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserFromPropertyChange(evt);
            }
        });

        jDateChooserTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserToPropertyChange(evt);
            }
        });

        jLabel1.setText("Từ ngày");

        jLabel2.setText("Đến ngày");

        searchDateBtn.setBackground(new java.awt.Color(3, 94, 252));
        searchDateBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchDateBtn.setText("Tìm kiếm");
        searchDateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooserFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(searchDateBtn)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchDateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jDateChooserFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addComponent(jDateChooserTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(74, 74, 74))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (tblChamCong.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu cần xoá");
        } else {
            deletePhieu(getChamCongSelect());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    public void deletePhieu(ChamCongDTO cc) {
        ChamCong ccModel = new ChamCong();
        ccModel.setMaChamCong(cc.getMaChamCong());
        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá " + cc.getMaChamCong(), "Xác nhận thông tin chấm công", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            ChamCongDAO.getInstance().delete(ccModel);
            JOptionPane.showMessageDialog(this, "Đã xoá thành công thông tin chấm công " + cc.getMaChamCong());
            loadDataToTable();
        }
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (tblChamCong.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu cần sửa");
        } else {
            SuaChamCong a = new SuaChamCong(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled);
            a.setVisible(true);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("Account");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tblChamCong.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblChamCong.getColumnName(i));
                }

                for (int j = 0; j < tblChamCong.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tblChamCong.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (tblChamCong.getValueAt(j, k) != null) {
                            cell.setCellValue(tblChamCong.getValueAt(j, k).toString());
                        }

                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jDateChooserToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserToPropertyChange
        // TODO add your handling code here:
//        searchAllCheck();
    }//GEN-LAST:event_jDateChooserToPropertyChange

    private void jDateChooserFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserFromPropertyChange
        // TODO add your handling code here:
//        searchAllCheck();
    }//GEN-LAST:event_jDateChooserFromPropertyChange

    private void searchDateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDateBtnActionPerformed
        // TODO add your handling code here:
        if (jDateChooserFrom.getDate() == null && jDateChooserTo.getDate() != null) {
            JOptionPane.showMessageDialog(this, "Nhập ngày bắt đầu!");
        }
        if (jDateChooserTo.getDate() == null && jDateChooserFrom.getDate() != null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày kết thúc!");
        }
        List<ChamCongDTO> ccList = ChamCongDAO.getInstance().findByDate(jDateChooserFrom.getDate(), jDateChooserTo.getDate());
        loadDataToTableArr(ccList);
    }//GEN-LAST:event_searchDateBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jDateChooserFrom.setDate(null);
        jDateChooserTo.setDate(null);
        jComboBoxS.setSelectedItem("Tất cả");
        loadDataToTableArr(chamCongList);
    }//GEN-LAST:event_jButton1ActionPerformed

    public ChamCongDTO getChamCongSelect() {
        int i_row = tblChamCong.getSelectedRow();
        ChamCongDTO cc = chamCongList.get(i_row);
        return cc;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBoxS;
    private com.toedter.calendar.JDateChooser jDateChooserFrom;
    private com.toedter.calendar.JDateChooser jDateChooserTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton searchDateBtn;
    private javax.swing.JTable tblChamCong;
    // End of variables declaration//GEN-END:variables

    private void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void loadDataToTableArr(List<ChamCongDTO> allPhieu) {
        try {
            tblModel.setRowCount(0);
            for (int i = 0; i < allPhieu.size(); i++) {
                tblModel.addRow(new Object[]{
                    i + 1, 
                    allPhieu.get(i).getMaChamCong(),
                    allPhieu.get(i).getNgayChamCong(),
                    allPhieu.get(i).getMaNguoiChamCong(),
                    allPhieu.get(i).getTenNguoiChamCong(),
                    allPhieu.get(i).getGioVao(),
                    allPhieu.get(i).getGioRa(),
                    String.format("%.2f", allPhieu.get(i).getTrongGioHanhChinh()),
                    String.format("%.2f", allPhieu.get(i).getNgoaiGioHanhChinh()),
                    String.format("%.2f", allPhieu.get(i).getTong())
                });
            }
        } catch (Exception e) {
        }
    }

    public void searchAllCheck() {
        String luaChon = jComboBoxS.getSelectedItem().toString();
//        String content = jTextFieldSearch.getText();
//        ArrayList<PhieuXuat> result = null;
//        if (content.length() > 0) {
//            result = new ArrayList<>();
//            switch (luaChon) {
//                case "Tất cả":
//                    result = searchTatCa(content);
//                    break;
//                case "Mã phiếu":
//                    result = searchMaPhieu(content);
//                    break;
//                case "Người tạo":
//                    result = searchNguoiTao(content);
//                    break;
//            }
//        } else if (content.length() == 0) {
//            result = PhieuXuatDAO.getInstance().selectAll();
//        }
//        Iterator<PhieuXuat> itr = result.iterator();
//        if (jDateChooserFrom.getDate() != null || jDateChooserTo.getDate() != null) {
//            Date from;
//            Date to;
//            if (jDateChooserFrom.getDate() != null && jDateChooserTo.getDate() == null) {
//                try {
//                    from = ChangeFrom(jDateChooserFrom.getDate());
//                    to = ChangeTo(new Date());
//                    while (itr.hasNext()) {
//                        PhieuXuat phieu = itr.next();
//                        if (!checkDate(phieu.getThoiGianTao(), from, to)) {
//                            itr.remove();
//                        }
//                    }
//                } catch (ParseException ex) {
//                    Logger.getLogger(ChamCongForm.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else if (jDateChooserTo.getDate() != null && jDateChooserFrom.getDate() == null) {
//                try {
//                    String sDate1 = "01/01/2002";
//                    from = ChangeFrom(new SimpleDateFormat("dd/MM/yyyy").parse(sDate1));
//                    to = ChangeTo(jDateChooserTo.getDate());
//                    while (itr.hasNext()) {
//                        PhieuXuat phieu = itr.next();
//                        if (!checkDate(phieu.getThoiGianTao(), from, to)) {
//                            itr.remove();
//                        }
//                    }
//                } catch (ParseException ex) {
//                    Logger.getLogger(ChamCongForm.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//                try {
//                    from = ChangeFrom(jDateChooserFrom.getDate());
//                    to = ChangeTo(jDateChooserTo.getDate());
//                    if (from.getTime() > to.getTime()) {
//                        JOptionPane.showMessageDialog(this, "Thời gian không hợp lệ !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
//                        jDateChooserFrom.setCalendar(null);
//                        jDateChooserTo.setCalendar(null);
//                    } else {
//                        while (itr.hasNext()) {
//                            PhieuXuat phieu = itr.next();
//                            if (!checkDate(phieu.getThoiGianTao(), from, to)) {
//                                itr.remove();
//                            }
//                        }
//                    }
//                } catch (ParseException ex) {
//                    Logger.getLogger(ChamCongForm.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//
//        ArrayList<PhieuXuat> result1 = new ArrayList<>();
//        if (giaTu.getText().length() > 0 || giaDen.getText().length() > 0) {
//            double a;
//            double b;
//
//            if (giaTu.getText().length() > 0 && giaDen.getText().length() == 0) {
//                a = Double.parseDouble(giaTu.getText());
//                for (int i = 0; i < result.size(); i++) {
//                    if (result.get(i).getTongTien() >= a) {
//                        result1.add(result.get(i));
//                    }
//                }
//            } else if (giaTu.getText().length() == 0 && giaDen.getText().length() > 0) {;
//                b = Double.parseDouble(giaDen.getText());
//                for (int i = 0; i < result.size(); i++) {
//                    if (result.get(i).getTongTien() <= b) {
//                        result1.add(result.get(i));
//                    }
//                }
//            } else if (giaTu.getText().length() > 0 && giaDen.getText().length() > 0) {
//                a = Double.parseDouble(giaTu.getText());
//                b = Double.parseDouble(giaDen.getText());
//                for (int i = 0; i < result.size(); i++) {
//                    if (result.get(i).getTongTien() >= a && result.get(i).getTongTien() <= b) {
//                        result1.add(result.get(i));
//                    }
//                }
//            }
//        }
//        if (giaTu.getText().length() > 0 || giaDen.getText().length() > 0) {
//            loadDataToTableArr(result1);
//        } else {
//            loadDataToTableArr(result);
//        }
    }

    public boolean checkDate(Date dateTest, Date star, Date end) {
        return dateTest.getTime() >= star.getTime() && dateTest.getTime() <= end.getTime();
    }

    public ArrayList<ChamCongDTO> searchTatCa(String text) {
        ArrayList<ChamCongDTO> result = new ArrayList<>();
//        ArrayList<ChamCongDTO> armt = ChamCongDAO.getInstance().getAll();
//        for (var phieu : armt) {
//            if (phieu.getMaPhieu().toLowerCase().contains(text.toLowerCase())
//                    || phieu.getNguoiTao().toLowerCase().contains(text.toLowerCase())) {
//                result.add(phieu);
//            }
//
//        }
        return result;
    }

    public Date ChangeFrom(Date date) throws ParseException {
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy 00:00:00");
        String dateText = fm.format(date);
        SimpleDateFormat par = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date result = par.parse(dateText);
        return result;
    }

    public Date ChangeTo(Date date) throws ParseException {
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy 23:59:59");
        String dateText = fm.format(date);
        SimpleDateFormat par = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Date result = par.parse(dateText);
        return result;
    }

    public ArrayList<ChamCongDTO> searchMaPhieu(String text) {
        ArrayList<ChamCongDTO> result = new ArrayList<>();
//        ArrayList<PhieuXuat> armt = PhieuXuatDAO.getInstance().selectAll();
//        for (var phieu : armt) {
//            if (phieu.getMaPhieu().toLowerCase().contains(text.toLowerCase())) {
//                result.add(phieu);
//            }
//        }
        return result;
    }

    public ArrayList<ChamCongDTO> searchNguoiTao(String text) {
        ArrayList<ChamCongDTO> result = new ArrayList<>();
//        ArrayList<PhieuXuat> armt = PhieuXuatDAO.getInstance().selectAll();
//        for (var phieu : armt) {
//            if (phieu.getNguoiTao().toLowerCase().contains(text.toLowerCase())) {
//                result.add(phieu);
//            }
//
//        }
        return result;
    }

}
