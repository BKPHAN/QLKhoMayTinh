/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import controller.SanPhamController;
import dao.ChiTietSanPhamDAO;
import dao.LoaiSanPhamDAO;
import dao.NhaCungCapDAO;
import dto.SanPhamDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.ChiTietSanPham;
import model.LoaiSanPham;
import model.NhaCungCap;

/**
 *
 * @author Tran Nhat Sinh
 */
public class SuaSanPham extends javax.swing.JDialog {

    /**
     * Creates new form ThemSP
     */
    private ProductForm owner;
    
    private JPanel listPanel;
    
    private JComboBox loaiSanPhamCbb;
    
    private List<JPanel> defaultAttributeList = new ArrayList<>();
    
    private List<JPanel> ctspList = new ArrayList<>();
    
    private String maSanPham;

    public SuaSanPham(javax.swing.JInternalFrame parent, javax.swing.JFrame owner, boolean modal) {
        super(owner, modal);
        this.owner = (ProductForm) parent;
        initComponents();
        setLocationRelativeTo(null);
        SanPhamDTO sp = this.owner.getChiTietSanPham();
        this.maSanPham = sp.getMaMay();
        List<ChiTietSanPham> chiTietSanPhamList = sp.getChiTietSanPhamList();
        
        jPanel1.setLayout(new BorderLayout());
        listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 3, 30, 30));
        listPanel.setBackground(Color.WHITE);
        
        // Thêm JPanel vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setPreferredSize(new Dimension(868, 500));
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        jPanel1.add(scrollPane, BorderLayout.CENTER);
        
//        addLabelAndTextFieldToListPane("Mã sản phẩm", "", listPanel);
        
        List<String> lspList = LoaiSanPhamDAO
                .getInstance().selectAll()
                .stream()
                .map(item -> item.getTenLoaiSanPham())
                .collect(Collectors.toList());
        lspList.add("Thêm loại sản phẩm mới");
        String[] lspComboboxData = lspList.stream().toArray(String[]::new);
        loaiSanPhamCbb = addLabelAndComboboxToListPane(
                "Loại sản phẩm", 
                sp.getTenLoaiSanPham(),
                lspComboboxData,
                this::cbxloaispItemStateChanged, 
                listPanel,
                defaultAttributeList
        );
        loaiSanPhamCbb.setEnabled(false);
        
        addLabelAndTextFieldToListPane("Tên sản phẩm", sp.getTenMay(), listPanel, defaultAttributeList);
        addLabelAndTextFieldToListPane("Đơn giá", String.valueOf(sp.getGia()), listPanel, defaultAttributeList);
        addLabelAndTextFieldToListPane("Số lượng", String.valueOf(sp.getSoLuong()), listPanel, defaultAttributeList);
        addLabelAndTextFieldToListPane("Tỉ lệ lãi", String.valueOf(sp.getTiLeLai()), listPanel, defaultAttributeList);
        addLabelAndTextFieldToListPane("Xuất xứ", sp.getXuatXu(), listPanel, defaultAttributeList);
        
        List<String> nccList = NhaCungCapDAO
                .getInstance().selectAll()
                .stream()
                .map(item -> item.getTenNhaCungCap())
                .collect(Collectors.toList());
        String[] nccComboboxData = nccList.stream().toArray(String[]::new);
        addLabelAndComboboxToListPane(
                "Nhà cung cấp",
                sp.getTenNhaCungCap(),
                nccComboboxData,
                null,
                listPanel, 
                defaultAttributeList
        );
        
        if (chiTietSanPhamList.size() > 0) {
            for (ChiTietSanPham item: chiTietSanPhamList) {
                addLabelAndTextFieldToListPane(
                    item.getTenThuocTinh(),
                    item.getGiaTriThuocTinh(),
                    listPanel,
                    ctspList
                );
            }
        }
    }
    
    private void cbxloaispItemStateChanged(ActionEvent evt) {                                           
        String value = ((JComboBox)evt.getSource()).getSelectedItem().toString();
        if (value.equals("Thêm loại sản phẩm mới")) {
            // them loai san pham
            ThemLoaiSanPham a = new ThemLoaiSanPham(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled);
            a.setVisible(true);
        }
    }  
    
    private void addLabelAndTextFieldToListPane(
            String key, 
            String value, 
            JPanel listPanel,
            List<JPanel> attributeList
    ) {
        JPanel itemWrapperPanel = new JPanel();
        itemWrapperPanel.setBackground(Color.WHITE);
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.setPreferredSize(new Dimension(250, 64));
        itemPanel.setBackground(Color.WHITE);

        JLabel label = new JLabel(key);
        JTextField textField = new JTextField(value);
        textField.setPreferredSize(new Dimension(200, 32)); // Kích thước JTextField
        textField.setBackground(Color.WHITE);

        itemPanel.add(label, BorderLayout.NORTH); // Label phía trên
        itemPanel.add(textField, BorderLayout.SOUTH); // TextField phía dưới
        itemWrapperPanel.add(itemPanel);
        listPanel.add(itemWrapperPanel);
        attributeList.add(itemWrapperPanel);
    }
    
    private void addTextFieldToListPane(JPanel listPanel, List<JPanel> attributeList) {
        JPanel itemWrapperPanel = new JPanel();
        itemWrapperPanel.setBackground(Color.WHITE);
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.setPreferredSize(new Dimension(250, 64));
        itemPanel.setBackground(Color.WHITE);

        JTextField keyTextField = new JTextField("Tên thuộc tính mới");
        keyTextField.setPreferredSize(new Dimension(150, 25));
        keyTextField.setBackground(Color.WHITE);
        
        JTextField valueTextField = new JTextField("Giá trị thuộc tính mới");
        valueTextField.setPreferredSize(new Dimension(200, 32));
        valueTextField.setBackground(Color.WHITE);

        itemPanel.add(keyTextField, BorderLayout.NORTH); // Label phía trên
        itemPanel.add(valueTextField, BorderLayout.SOUTH); // TextField phía dưới
        itemWrapperPanel.add(itemPanel);
        listPanel.add(itemWrapperPanel);
        attributeList.add(itemWrapperPanel);
    }
    
    private JComboBox addLabelAndComboboxToListPane(
            String key, 
            String value,
            String[] data, 
            ActionListener handleChage, 
            JPanel listPanel,
            List<JPanel> attributeList
    ) {
        JPanel itemWrapperPanel = new JPanel();
        itemWrapperPanel.setBackground(Color.WHITE);
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.setPreferredSize(new Dimension(250, 64));
        itemPanel.setBackground(Color.WHITE);

        JLabel label = new JLabel(key);
        JComboBox combobox = new JComboBox<>();
        combobox.setPreferredSize(new Dimension(200, 32)); // Kích thước JTextField
        combobox.setBackground(Color.WHITE);
        combobox.setModel(new javax.swing.DefaultComboBoxModel<>(data));
        combobox.setSelectedItem(value);
        if (handleChage != null) {
            combobox.addActionListener(handleChage);
        }

        itemPanel.add(label, BorderLayout.NORTH); // Label phía trên
        itemPanel.add(combobox, BorderLayout.SOUTH); // TextField phía dưới
        itemWrapperPanel.add(itemPanel);
        listPanel.add(itemWrapperPanel);
        attributeList.add(itemWrapperPanel);
        return combobox;
    }
    
    public void addNewAndSelectLoaiSanPham(String lsp) {
        loaiSanPhamCbb.insertItemAt(lsp, loaiSanPhamCbb.getItemCount() - 1);
        loaiSanPhamCbb.setSelectedItem(lsp);
    }
    
    private String[] getKeyAndValueFromJPanel(JPanel parentPanel) {
        String key = "", value = "";
        // Tìm mainPanel trong parenPanel
        for (Component comp : parentPanel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel mainPanel = (JPanel) comp;
                
                if (mainPanel.getComponents().length == 2) {
                    Component innerComp = mainPanel.getComponents()[0];
                    if (innerComp instanceof JLabel) {
                        key = ((JLabel) innerComp).getText();
                        innerComp = mainPanel.getComponents()[1];
                        if (innerComp instanceof JComboBox) {
                            value = ((JComboBox) innerComp).getSelectedItem().toString();
                        } else {
                            value = ((JTextField) innerComp).getText();
                        }
                    } else { // instanceof JTextField
                        key = ((JTextField) innerComp).getText();
                        innerComp = mainPanel.getComponents()[1];
                        value = ((JTextField) innerComp).getText(); // Lấy text từ JTextField
                    }
                }
            }
        }
        String[] result = {key, value};
        return result;
    }

    private SuaSanPham(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        editProductBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        addAttributeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cập nhật thông tin sản phẩm");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));

        jLabel1.setFont(new java.awt.Font("SF Pro Display", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CẬP NHẬT THÔNG TIN SẢN PHẨM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel1)
                .addContainerGap(245, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 60));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 880, 410));

        editProductBtn.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        editProductBtn.setForeground(new java.awt.Color(255, 255, 255));
        editProductBtn.setText("Cập nhật sản phẩm");
        editProductBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProductBtnActionPerformed(evt);
            }
        });

        cancelBtn.setBackground(new java.awt.Color(255, 0, 0));
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setText("Hủy bỏ");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        addAttributeBtn.setBackground(new java.awt.Color(3, 94, 252));
        addAttributeBtn.setForeground(new java.awt.Color(255, 255, 255));
        addAttributeBtn.setText("Thêm thuộc tính");
        addAttributeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAttributeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(addAttributeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                .addComponent(editProductBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(207, 207, 207)
                .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editProductBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addAttributeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 880, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editProductBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProductBtnActionPerformed
        // TODO add your handling code here:
        
        try {
            SanPhamDTO spMoi = new SanPhamDTO();
            setDataToSanPham(spMoi);
            boolean checkInput = checkInputSp(spMoi);
            if (!checkInput) return;
            
            List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();
            for (JPanel item: ctspList) {
                String[] data = getKeyAndValueFromJPanel(item);
                if (data.length != 2 || data[0].isBlank()) continue;
                chiTietSanPhamList.add(new ChiTietSanPham(data[0], data[1]));
            }
            
            spMoi.setChiTietSanPhamList(chiTietSanPhamList);
            spMoi.setMaMay(this.maSanPham);
            String updateSPResult = SanPhamController.getInstance().updateSanPham(spMoi);
            if (updateSPResult.equals("OK")) {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin sản phẩm thành công!");
                owner.loadDataToTable();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + updateSPResult + " !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_editProductBtnActionPerformed

    private void setDataToSanPham(SanPhamDTO spMoi) {
        for (JPanel item: defaultAttributeList) {
            String[] data = getKeyAndValueFromJPanel(item);
            switch(data[0]) {
                case "Loại sản phẩm":
                    LoaiSanPham lsp = LoaiSanPhamDAO.getInstance().findByName(data[1]);
                    if (lsp != null) {
                        spMoi.setMaLoaiSanPham(lsp.getMaLoaiSanPham());
                        spMoi.setTenLoaiSanPham(lsp.getTenLoaiSanPham());
                    }
                    break;
                case "Tên sản phẩm":
                    spMoi.setTenMay(data[1]);
                    break;
                case "Đơn giá":
                    double gia;
                    try {
                        if (data[1].isBlank()) {
                            gia = -1;
                        } else {
                            gia = Double.parseDouble(data[1]);
                        }
                    } catch (NumberFormatException e) {
                        gia = -2;
                    }
                    spMoi.setGia(gia);
                    break;
                case "Số lượng":
                    int sl;
                    try {
                        if (data[1].isBlank()) {
                            sl = -1;
                        } else {
                            sl = Integer.parseInt(data[1]);
                        }
                    } catch (NumberFormatException e) {
                        sl = -2;
                    }
                    spMoi.setSoLuong(sl);
                    break;
                case "Tỉ lệ lãi":
                    double tiLeLai;
                    try {
                        if (data[1].isBlank()) {
                            tiLeLai = -1;
                        } else {
                            tiLeLai = Double.parseDouble(data[1]);
                        }
                    } catch (NumberFormatException e) {
                        tiLeLai = -2;
                    }
                    spMoi.setTiLeLai(tiLeLai);
                    break;
                case "Xuất xứ":
                    spMoi.setXuatXu(data[1]);
                    break;
                case "Nhà cung cấp":
                    NhaCungCap ncc = NhaCungCapDAO.getInstance().findByName(data[1]);
                    if (ncc != null) {
                        spMoi.setMaNhaCungCap(ncc.getMaNhaCungCap());
                        spMoi.setTenNhaCungCap(ncc.getTenNhaCungCap());
                    }
                    break;
            }
        }
    }
    
    private boolean checkInputSp(SanPhamDTO spMoi) {
        if (spMoi.getMaLoaiSanPham().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại sản phẩm!");
            return false;
        }
        if (spMoi.getTenMay().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên máy!");
            return false;
        }
        if (spMoi.getSoLuong() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng!");
            return false;
        }
        if (spMoi.getSoLuong() == -2) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng đúng định dạng!");
            return false;
        }
        if (spMoi.getGia() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá sản phẩm!");
            return false;
        }
        if (spMoi.getGia() == -2) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá sản phẩm đúng định dạng!");
            return false;
        }
        if (spMoi.getTiLeLai() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tỉ lệ lãi!");
            return false;
        }
        if (spMoi.getTiLeLai() == -2) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tỉ lệ lãi đúng định dạng!");
            return false;
        }
        if (spMoi.getXuatXu().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập xuất xứ!");
            return false;
        }
        if (spMoi.getMaNhaCungCap().isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp!");
            return false;
        }
        return true;
    }
    
    private void addAttributeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAttributeBtnActionPerformed
        // TODO add your handling code here:
        addTextFieldToListPane(listPanel, ctspList);
        listPanel.revalidate();
        listPanel.repaint();
    }//GEN-LAST:event_addAttributeBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

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
            java.util.logging.Logger.getLogger(SuaSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuaSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuaSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuaSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SuaSanPham dialog = new SuaSanPham(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAttributeBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton editProductBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
