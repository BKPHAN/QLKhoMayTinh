/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import controller.ConvertDate;
import controller.SanPhamController;
import dao.AccountDAO;
import dao.NhaCungCapDAO;
import dao.PhieuNhapDAO;
import dao.PhieuXuatDAO;
import dao.SanPhamDAO;
import dao.ThongKeDAO;
import dto.SanPhamDTO;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.PhieuNhap;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import model.Phieu;
import model.PhieuXuat;
import model.ThongKeProduct;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Robot
 */
public class ThongKeForm extends javax.swing.JInternalFrame {

    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");

    public DecimalFormat getFormatter() {
        return formatter;
    }

    public SimpleDateFormat getFormatDate() {
        return formatDate;
    }

    public ThongKeForm() {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        jDateChooserFromLN.setDateFormatString("dd/MM/yyyy");
        jDateChooserToLN.setDateFormatString("dd/MM/yyyy");
        jDateChooserFromPr.setDateFormatString("dd/MM/yyyy");
        jDateChooserToPr.setDateFormatString("dd/MM/yyyy");
        txtQuantityProduct.setText(Integer.toString(SanPhamController.getInstance().getSl()));
        txtQuantityNcc.setText(Integer.toString(NhaCungCapDAO.getInstance().selectAll().size()));
        txtQuantityUser.setText(Integer.toString(AccountDAO.getInstance().selectAll().size()));
        //
        initTable();
        loadDataToTable();
        changeTextFind();
        //
//        loadDataToTableAcc(AccountDAO.getInstance().selectAll());
//        tblAccount.setDefaultEditor(Object.class, null);
        tblThongKeProduct.setDefaultEditor(Object.class, null);
        tblCTLoiNhuan.setDefaultEditor(Object.class, null);
        //
        loadDataToTableThongKeProduct(ThongKeDAO.getInstance().getThongKe());
    }

    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{"STT", "Mã sản phẩm", "Loại sản phẩm", "Tên sản phẩm", "Số lượng nhập", "Số lượng xuất", "Giá nhập", "Giá Xuất", "Lợi Nhuận"};
        tblModel.setColumnIdentifiers(headerTbl);
        tblCTLoiNhuan.setModel(tblModel);
        tblCTLoiNhuan.getColumnModel().getColumn(0).setPreferredWidth(5);
    }

    private void loadDataToTable() {
        try {
            ArrayList<SanPhamDTO> armt = SanPhamDAO.getInstance().getSanPhamToCTXuat();
            tblModel.setRowCount(0);
            int index = 1; // Biến đếm cho số thứ tự
            int sumMoney = 0;
            int sumCount = 0;
            for (SanPhamDTO i : armt) {
                if (i.getTrangThai() == 1) {
                    int gia_xuat;
                    int loi_nhuan;
                    int soLuongXuat;
                    soLuongXuat = (int) (i.getSoLuongNhap() - i.getSoLuong());
                    gia_xuat = (int) (i.getGia() + i.getGia() * i.getTiLeLai() / 100);
                    loi_nhuan = (int) ((gia_xuat - i.getGia()) * soLuongXuat);
                    tblModel.addRow(new Object[]{
                        index++, i.getMaMay(), i.getMaLoaiSanPham(), i.getTenMay(), i.getSoLuongNhap(), soLuongXuat, formatter.format(i.getGia()) + "đ", formatter.format(gia_xuat) + "đ", formatter.format(loi_nhuan) + "đ"
                    });
                    sumCount += soLuongXuat;
                    sumMoney += loi_nhuan;
                }
            }
            soLuong.setText(sumCount + " Sản phẩm");
            tongTien.setText(formatter.format(sumMoney) + "đ");
            // Gọi căn chỉnh cột sau khi thêm dữ liệu
            alignColumns();
        } catch (Exception e) {
        }
    }

    // can chinh cot trong bangtblSanPham.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
    private void alignColumns() {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Căn giữa các cột 0, 1, 2, 3 (tính từ 0)
        tblCTLoiNhuan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblCTLoiNhuan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblCTLoiNhuan.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblCTLoiNhuan.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        // Căn phải các cột 4, 5, 6, 7, 8 (tính từ 0)
        tblCTLoiNhuan.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        tblCTLoiNhuan.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        tblCTLoiNhuan.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        tblCTLoiNhuan.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
        tblCTLoiNhuan.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
    }

    private void loadDataToTableSearch(ArrayList<SanPhamDTO> result) {
        try {
            ArrayList<SanPhamDTO> armt = SanPhamDAO.getInstance().getSanPhamToCTXuat();
            tblModel.setRowCount(0);
            int index = 1; // Biến đếm cho số thứ tự
            int sumMoney = 0;
            int sumCount = 0;
            for (SanPhamDTO i : result) {
                if (i.getTrangThai() == 1) {
                    int gia_xuat;
                    int loi_nhuan;
                    int soLuongXuat;
                    soLuongXuat = (int) (i.getSoLuongNhap() - i.getSoLuong());
                    gia_xuat = (int) (i.getGia() + i.getGia() * i.getTiLeLai() / 100);
                    loi_nhuan = (int) ((gia_xuat - i.getGia()) * soLuongXuat);

                    tblModel.addRow(new Object[]{
                        index++,
                        i.getMaMay(),
                        i.getMaLoaiSanPham(),
                        i.getTenMay(),
                        i.getSoLuongNhap(),
                        soLuongXuat,
                        formatter.format(i.getGia()) + "đ",
                        formatter.format(gia_xuat) + "đ",
                        formatter.format(loi_nhuan) + "đ"
                    });
                    sumCount += soLuongXuat;
                    sumMoney += loi_nhuan;
                }
            }

            soLuong.setText(sumCount + " Sản phẩm");
            tongTien.setText(formatter.format(sumMoney) + "đ");
            // Gọi căn chỉnh cột sau khi thêm dữ liệu
            alignColumns();
        } catch (Exception e) {
        }
    }

    private void changeTextFind() {
        jTextFieldSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                /* do nothing */
                if (jTextFieldSearch.getText().length() == 0) {
                    loadDataToTable();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        txtNamePr = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblThongKeProduct = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jDateChooserFromPr = new com.toedter.calendar.JDateChooser();
        jDateChooserToPr = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnResetThongKePr = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTLoiNhuan = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        soLuong = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tongTien = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jDateChooserFromLN = new com.toedter.calendar.JDateChooser();
        jDateChooserToLN = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        txtQuantityProduct = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtQuantityNcc = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtQuantityUser = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1180, 770));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1180, 770));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm"));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNamePr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNamePrKeyReleased(evt);
            }
        });
        jPanel16.add(txtNamePr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 350, 40));

        tblThongKeProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã máy", "Tên máy", "Số lượng nhập", "Số lượng xuất"
            }
        ));
        tblThongKeProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongKeProductMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblThongKeProduct);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Theo Ngày"));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDateChooserFromPr.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooserFromPr.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserFromPrPropertyChange(evt);
            }
        });
        jDateChooserFromPr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDateChooserFromPrKeyReleased(evt);
            }
        });
        jPanel13.add(jDateChooserFromPr, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 170, -1));

        jDateChooserToPr.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooserToPr.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserToPrPropertyChange(evt);
            }
        });
        jDateChooserToPr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDateChooserToPrKeyReleased(evt);
            }
        });
        jPanel13.add(jDateChooserToPr, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 170, -1));

        jLabel6.setText("Đến");
        jPanel13.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 40, 20));

        jLabel8.setText("Từ");
        jPanel13.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 20, 20));

        btnResetThongKePr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reset_25px_1.png"))); // NOI18N
        btnResetThongKePr.setText("Làm mới");
        btnResetThongKePr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetThongKePrActionPerformed(evt);
            }
        });
        jPanel13.add(btnResetThongKePr, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 140, 40));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1162, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(510, 510, 510))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel12);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });
        jPanel3.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 430, 40));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reset_25px_1.png"))); // NOI18N
        jButton7.setText("Làm mới");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 140, 40));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng"));
        jToolBar1.setRollover(true);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_spreadsheet_file_40px.png"))); // NOI18N
        jButton6.setText("Xuất Excel");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportExcel(evt);
            }
        });
        jToolBar1.add(jButton6);

        tblCTLoiNhuan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblCTLoiNhuan);

        jLabel2.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel2.setText("TỔNG LỢI NHUẬN");

        soLuong.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        soLuong.setText("0");

        jLabel7.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel7.setText("TỔNG SẢN PHẨM ĐÃ BÁN");

        tongTien.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        tongTien.setForeground(new java.awt.Color(255, 0, 51));
        tongTien.setText("0");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Theo Ngày"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDateChooserFromLN.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooserFromLN.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserFromLNPropertyChange(evt);
            }
        });
        jDateChooserFromLN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDateChooserFromLNKeyReleased(evt);
            }
        });
        jPanel4.add(jDateChooserFromLN, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 170, 30));

        jDateChooserToLN.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooserToLN.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserToLNPropertyChange(evt);
            }
        });
        jDateChooserToLN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDateChooserToLNKeyReleased(evt);
            }
        });
        jPanel4.add(jDateChooserToLN, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 170, 30));

        jLabel1.setText("Đến");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 30, 30));

        jLabel5.setText("Từ");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 20, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90))
        );

        jTabbedPane1.addTab("Thống kê lợi nhuận", jPanel1);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, 620));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 204, 0));

        txtQuantityProduct.setFont(new java.awt.Font("SF Pro Display", 1, 36)); // NOI18N
        txtQuantityProduct.setForeground(new java.awt.Color(255, 255, 255));
        txtQuantityProduct.setText("100");

        jLabel10.setFont(new java.awt.Font("SF Pro Display", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sản phẩm trong kho");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-monitor-80.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuantityProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(73, 73, 73))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtQuantityProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addGap(10, 10, 10))
        );

        jPanel10.setBackground(new java.awt.Color(255, 102, 0));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-supplier-80.png"))); // NOI18N

        txtQuantityNcc.setFont(new java.awt.Font("SF Pro Display", 1, 36)); // NOI18N
        txtQuantityNcc.setForeground(new java.awt.Color(255, 255, 255));
        txtQuantityNcc.setText("100");

        jLabel14.setFont(new java.awt.Font("SF Pro Display", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nhà cung cấp ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel13)
                .addGap(43, 43, 43)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuantityNcc, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtQuantityNcc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)))
                .addGap(10, 10, 10))
        );

        jPanel11.setBackground(new java.awt.Color(0, 204, 204));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-account-80.png"))); // NOI18N

        txtQuantityUser.setFont(new java.awt.Font("SF Pro Display", 1, 36)); // NOI18N
        txtQuantityUser.setForeground(new java.awt.Color(255, 255, 255));
        txtQuantityUser.setText("100");

        jLabel17.setFont(new java.awt.Font("SF Pro Display", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tài khoản người dùng");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuantityUser, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(73, 73, 73))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtQuantityUser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 130));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jDateChooserToLNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooserToLNKeyReleased

    }//GEN-LAST:event_jDateChooserToLNKeyReleased

    private void jDateChooserToLNPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserToLNPropertyChange
        try {
            // TODO add your handling code here:
            searchAllRepect();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jDateChooserToLNPropertyChange

    private void jDateChooserFromLNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooserFromLNKeyReleased

    }//GEN-LAST:event_jDateChooserFromLNKeyReleased

    private void jDateChooserFromLNPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserFromLNPropertyChange
        try {
            // TODO add your handling code here:
            searchAllRepect();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jDateChooserFromLNPropertyChange

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        loadDataToTable();
        jTextFieldSearch.setText("");
        jDateChooserFromLN.setCalendar(null);
        jDateChooserToLN.setCalendar(null);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        try {
            // TODO add your handling code here:
            searchAllRepect();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    private void btnResetThongKePrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetThongKePrActionPerformed
        // TODO add your handling code here:
        txtNamePr.setText("");
        jDateChooserFromPr.setCalendar(null);
        jDateChooserToPr.setCalendar(null);
        loadDataToTableThongKeProduct(ThongKeDAO.getInstance().getThongKe());
    }//GEN-LAST:event_btnResetThongKePrActionPerformed

    private void jDateChooserToPrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooserToPrKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooserToPrKeyReleased

    private void jDateChooserToPrPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserToPrPropertyChange
        try {
            // TODO add your handling code here:
            filterThongKeSanPham();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jDateChooserToPrPropertyChange

    private void jDateChooserFromPrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooserFromPrKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooserFromPrKeyReleased

    private void jDateChooserFromPrPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserFromPrPropertyChange
        try {
            // TODO add your handling code here:
            filterThongKeSanPham();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jDateChooserFromPrPropertyChange

    private void tblThongKeProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongKeProductMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblThongKeProductMouseClicked

    private void txtNamePrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamePrKeyReleased
        try {
            // TODO add your handling code here:
            filterThongKeSanPham();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtNamePrKeyReleased

    private void jButtonExportExcel(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportExcel
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("LoiNhuan");

                // Tạo một CellStyle cho border
                CellStyle borderStyle = wb.createCellStyle();
                borderStyle.setBorderTop(BorderStyle.THIN);
                borderStyle.setBorderBottom(BorderStyle.THIN);
                borderStyle.setBorderLeft(BorderStyle.THIN);
                borderStyle.setBorderRight(BorderStyle.THIN);

                // Tạo CellStyle cho header với font đậm
                CellStyle headerStyle = wb.createCellStyle();
                headerStyle.cloneStyleFrom(borderStyle); // Kế thừa border
                Font headerFont = wb.createFont();
                headerFont.setBold(true); // Font chữ đậm
                headerStyle.setFont(headerFont);

                // Thêm header vào Excel
                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tblCTLoiNhuan.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblCTLoiNhuan.getColumnName(i));
                    cell.setCellStyle(headerStyle); // Áp dụng header style
                }

                // Thêm dữ liệu vào Excel
                int rowCount = tblCTLoiNhuan.getRowCount();
                int colCount = tblCTLoiNhuan.getColumnCount();

                for (int j = 0; j < rowCount; j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < colCount; k++) {
                        Cell cell = row.createCell(k);
                        Object value = tblCTLoiNhuan.getValueAt(j, k);
                        if (value != null) {
                            cell.setCellValue(value.toString());
                        }
                        cell.setCellStyle(borderStyle); // Áp dụng border
                    }
                }

                // Lấy giá trị từ ô soLuong và tongTien
                int totalQuantity = Integer.parseInt(soLuong.getText().replaceAll("[^0-9]", ""));
                double totalAmount = Double.parseDouble(tongTien.getText().replaceAll("[^0-9.]", ""));

                // Thêm tổng số lượng và tổng tiền
                int summaryRowIndex = rowCount + 1;
                Row summaryRow = sheet.createRow(summaryRowIndex);

                // Cột "Tổng số lượng"
                Cell totalQuantityCell = summaryRow.createCell(0);
                totalQuantityCell.setCellValue("Tổng số lượng:");
                totalQuantityCell.setCellStyle(headerStyle);

                Cell totalQuantityValueCell = summaryRow.createCell(1);
                totalQuantityValueCell.setCellValue(totalQuantity);
                totalQuantityValueCell.setCellStyle(borderStyle);

                // Cột "Tổng tiền"
                Row totalAmountRow = sheet.createRow(summaryRowIndex + 1);
                Cell totalAmountCell = totalAmountRow.createCell(0);
                totalAmountCell.setCellValue("Tổng tiền:");
                totalAmountCell.setCellStyle(headerStyle);

                Cell totalAmountValueCell = totalAmountRow.createCell(1);
                totalAmountValueCell.setCellValue(String.format("%,.2f", totalAmount));
                totalAmountValueCell.setCellStyle(borderStyle);

                // Ghi file Excel
                FileOutputStream out = new FileOutputStream(saveFile);
                wb.write(out);
                wb.close();
                out.close();

                // Mở file sau khi lưu
                openFile(saveFile.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonExportExcel

    private void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean checkDate(Date dateTest, Date star, Date end) {
        return dateTest.getTime() >= star.getTime() && dateTest.getTime() <= end.getTime();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResetThongKePr;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private com.toedter.calendar.JDateChooser jDateChooserFromLN;
    private com.toedter.calendar.JDateChooser jDateChooserFromPr;
    private com.toedter.calendar.JDateChooser jDateChooserToLN;
    private com.toedter.calendar.JDateChooser jDateChooserToPr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel soLuong;
    private javax.swing.JTable tblCTLoiNhuan;
    private javax.swing.JTable tblThongKeProduct;
    private javax.swing.JLabel tongTien;
    private javax.swing.JTextField txtNamePr;
    private javax.swing.JLabel txtQuantityNcc;
    private javax.swing.JLabel txtQuantityProduct;
    private javax.swing.JLabel txtQuantityUser;
    // End of variables declaration//GEN-END:variables

    public void searchAllRepect() throws ParseException {
        String content = jTextFieldSearch.getText();
        ArrayList<ThongKeProduct> thongKe = new ArrayList<>();
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        ArrayList<SanPhamDTO> armt = SanPhamDAO.getInstance().getSanPhamToCTXuat();
        if (jDateChooserFromLN.getDate() != null || jDateChooserToLN.getDate() != null) {
            Date from = new Date();
            Date to = new Date();
            if (jDateChooserFromLN.getDate() != null && jDateChooserToLN.getDate() == null) {
                from = jDateChooserFromLN.getDate();
                to = new Date();
            } else if (jDateChooserToLN.getDate() != null && jDateChooserFromLN.getDate() == null) {
                String sDate1 = "01/01/2022";
                from = ConvertDate.getInstance().ChangeFrom(new SimpleDateFormat("dd/MM/yyyy").parse(sDate1));
                to = jDateChooserToLN.getDate();
            } else {
                from = jDateChooserFromLN.getDate();
                to = jDateChooserToLN.getDate();
                if (from.getTime() > to.getTime()) {
                    JOptionPane.showMessageDialog(this, "Thời gian không hợp lệ !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    jDateChooserFromLN.setCalendar(null);
                    jDateChooserToLN.setCalendar(null);
                }
            }
            thongKe = ThongKeDAO.getInstance().getThongKe(from, to);

        } else {
            thongKe = ThongKeDAO.getInstance().getThongKe();
        }

        for (SanPhamDTO i : armt) {
            // Tìm kiếm trong danh sách result dựa trên MaPhieu
            ThongKeProduct matchingPhieu = thongKe.stream()
                    .filter(phieu -> phieu.getMaMay().equals(i.getMaMay()) && phieu.getTenMay().equals(i.getTenMay()))
                    .findFirst()
                    .orElse(null);

            if (i.getTrangThai() == 1 && matchingPhieu != null) {
                result.add(i);
            }
        }

        if (!jTextFieldSearch.getText().equals("")) {
            result = searchSanPham(result, jTextFieldSearch.getText());
        }
        loadDataToTableSearch(result);
    }

    public void filterThongKeSanPham() throws ParseException {
        ArrayList<ThongKeProduct> thongKe = new ArrayList<>();
        if (jDateChooserFromPr.getDate() != null || jDateChooserToPr.getDate() != null) {
            Date from = new Date();
            Date to = new Date();
            if (jDateChooserFromPr.getDate() != null && jDateChooserToPr.getDate() == null) {
                from = jDateChooserFromPr.getDate();
                to = new Date();
            } else if (jDateChooserToPr.getDate() != null && jDateChooserFromPr.getDate() == null) {
                String sDate1 = "01/01/2022";
                from = ConvertDate.getInstance().ChangeFrom(new SimpleDateFormat("dd/MM/yyyy").parse(sDate1));
                to = jDateChooserToPr.getDate();
            } else {
                from = jDateChooserFromPr.getDate();
                to = jDateChooserToPr.getDate();
                if (from.getTime() > to.getTime()) {
                    JOptionPane.showMessageDialog(this, "Thời gian không hợp lệ !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    jDateChooserFromLN.setCalendar(null);
                    jDateChooserToLN.setCalendar(null);
                }
            }
            thongKe = ThongKeDAO.getInstance().getThongKe(from, to);

        } else {
            thongKe = ThongKeDAO.getInstance().getThongKe();
        }
        if (!txtNamePr.getText().equals("")) {
            thongKe = searchTenSanPhamThongKe(thongKe, txtNamePr.getText());
        }
        loadDataToTableThongKeProduct(thongKe);
    }

    private void loadDataToTableThongKeProduct(ArrayList<ThongKeProduct> thongKe) {
        try {
            DefaultTableModel tblModelAcc = (DefaultTableModel) tblThongKeProduct.getModel();
            tblModelAcc.setRowCount(0);
            for (int i = 0; i < thongKe.size(); i++) {
                tblModelAcc.addRow(new Object[]{
                    (i + 1), thongKe.get(i).getMaMay(), thongKe.get(i).getTenMay(), thongKe.get(i).getSlNhap(), thongKe.get(i).getSlXuat()
                });
            }
            tblThongKeProduct.getColumnModel().getColumn(2).setPreferredWidth(400);
        } catch (Exception e) {
        }
    }

    private ArrayList<ThongKeProduct> searchTenSanPhamThongKe(ArrayList<ThongKeProduct> arr, String name) {
        ArrayList<ThongKeProduct> result = new ArrayList<>();
        for (ThongKeProduct i : arr) {
            if (i.getMaMay().toLowerCase().contains(name.toLowerCase()) || i.getTenMay().toLowerCase().contains(name.toLowerCase())) {
                result.add(i);
            }
        }
        return result;
    }

    private ArrayList<SanPhamDTO> searchSanPham(ArrayList<SanPhamDTO> arr, String name) {
        ArrayList<SanPhamDTO> armt = SanPhamDAO.getInstance().getSanPhamToCTXuat();
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        for (int index = 0; index < arr.size(); index++) {
            SanPhamDTO i = arr.get(index);
            if (i.getTrangThai() == 1) {

                int soLuongXuat = (int) (i.getSoLuongNhap() - i.getSoLuong());
                int gia_xuat = (int) (i.getGia() + i.getGia() * i.getTiLeLai() / 100);
                int loi_nhuan = (int) ((gia_xuat - i.getGia()) * soLuongXuat);

                // Gộp tất cả các trường thành một chuỗi sử dụng concat
                String combinedFields = String.valueOf(index) // Concat index vào chuỗi
                        .concat(" ")
                        .concat(i.getMaMay())
                        .concat(" ")
                        .concat(i.getTenMay())
                        .concat(" ")
                        .concat(String.valueOf((int) i.getSoLuong()))
                        .concat(" ")
                        .concat(String.valueOf((int) i.getGia()))
                        .concat(" ")
                        .concat(i.getMaLoaiSanPham())
                        .concat(" ")
                        .concat(String.valueOf((int) soLuongXuat))
                        .concat(" ")
                        .concat(String.valueOf((int) gia_xuat))
                        .concat(" ")
                        .concat(String.valueOf((int) loi_nhuan))
                        .toLowerCase();
                if (combinedFields.contains(name)) {
                    result.add(i);
                }
            }
        }

        return result;
    }
}
