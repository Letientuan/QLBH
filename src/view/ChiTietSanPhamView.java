/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import entity.DongSanPham;
import entity.MauSac;
import entity.NhaSanXuat;
import entity.ChatLieu;
import entity.SanPham;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import entity.SanPhammd;
import repository.ChatLieuRepository;
import repository.LoaiRepo;
import repository.Lrepo.isanphamrp;
import repository.MauRepository;
import repository.NSXRepository;
import repository.sanphamRp;
import ServiceImpl.SanPhamSerImpl;

/**
 *
 * @author thong
 */
public class ChiTietSanPhamView extends javax.swing.JPanel {

    /**
     * Creates new form ChiTietSanPhamView
     */
    private DefaultTableModel dtm = new DefaultTableModel();
    private SanPhamSerImpl spsv = new SanPhamSerImpl();
    private MauRepository maurp = new MauRepository();
    private NSXRepository nsxrp = new NSXRepository();
    private LoaiRepo dongrp = new LoaiRepo();
    private ChatLieuRepository chatlieurp = new ChatLieuRepository();

    private List<SanPhammd> listsp = spsv.getall();

    private List<MauSac> lisstmau = new ArrayList<>();
    private List<NhaSanXuat> listnsx = new ArrayList<>();
    private List<DongSanPham> listdong = new ArrayList<>();
    private List<ChatLieu> listchatlieu = new ArrayList<>();

    private DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel boxModel1 = new DefaultComboBoxModel();
    private DefaultComboBoxModel boxModel2 = new DefaultComboBoxModel();
    private DefaultComboBoxModel boxModel3 = new DefaultComboBoxModel();
    private DefaultComboBoxModel boxModel4 = new DefaultComboBoxModel();
    private DefaultComboBoxModel boxModel5 = new DefaultComboBoxModel();
    int vitri;

    public ChiTietSanPhamView() {
        initComponents();
        loadTable(listsp);
        loadCombobox();
    }

    private void loadTable(List<SanPhammd> list) {
        DefaultTableModel model = (DefaultTableModel) tbl_sanpham.getModel();
        model.setColumnCount(0);

        model.addColumn("Masp");
        model.addColumn("tên sp");
        model.addColumn("nsx");
        model.addColumn("màu sắc");
        model.addColumn("dòng sản phẩm ");
        model.addColumn("chất liệu");
        model.addColumn("size");
        model.addColumn("mô tả");
        model.addColumn("giá nhập");
        model.addColumn("giá bán");
        model.addColumn("số lượng");
        model.addColumn("ảnh");
        model.addColumn("trạng thái");

        model.setRowCount(0);
        for (SanPhammd sanPhammd : list) {
            Object[] row = new Object[]{
                sanPhammd.getMaSP(),
                sanPhammd.getTenSP(),
                sanPhammd.getTennsx(),
                sanPhammd.getTenmauSac(),
                sanPhammd.getTendongSP(),
                sanPhammd.getTenchatLieu(),
                sanPhammd.getSize(),
                sanPhammd.getMoTa(),
                sanPhammd.getGiaNhap(),
                sanPhammd.getGiaBan(),
                sanPhammd.getSoLuongTon(),
                sanPhammd.getAnhSp(),
                sanPhammd.gettrangthaisp(1)};
            model.addRow(row);
        }

        listsp = list;
    }

    private void loadCombobox() {
        boxModel = (DefaultComboBoxModel) cbo_mau.getModel();
        lisstmau = maurp.getAll();
        boxModel1 = (DefaultComboBoxModel) cbo_hang.getModel();
        listnsx = nsxrp.getAll();
        boxModel2 = (DefaultComboBoxModel) cbo_dongsp.getModel();
        listdong = dongrp.getAll();
        boxModel3 = (DefaultComboBoxModel) cbo_chatlieu.getModel();
        listchatlieu = chatlieurp.getAll();
        boxModel4 = (DefaultComboBoxModel) cbo_lochang.getModel();
        listnsx = nsxrp.getAll();
        boxModel5 = (DefaultComboBoxModel) cbo_locLoai.getModel();
        listdong = dongrp.getAll();

        loadComboboxmau();
        loadComboboxnsx();
        loadComboboxloai();
        loadComboboxchatlieu();
        loadComboboxloc();
    }

    private void loadComboboxmau() {
        cbo_mau.removeAllItems();
        lisstmau.forEach(s -> boxModel.addElement(s.getTenMS()));
    }

    private void loadComboboxnsx() {
        cbo_hang.removeAllItems();
        listnsx.forEach(s -> boxModel1.addElement(s.getTenNhaSanXuat()));
    }

    private void loadComboboxloai() {
        cbo_dongsp.removeAllItems();
        listdong.forEach(s -> boxModel2.addElement(s.getTenDongSP()));
    }

    private void loadComboboxloc() {
        cbo_locLoai.removeAllItems();
        listdong.forEach(s -> boxModel5.addElement(s.getTenDongSP()));
        cbo_lochang.removeAllItems();
        listnsx.forEach(s -> boxModel4.addElement(s.getTenNhaSanXuat()));
//        cbo_tranthai.removeAllItems();
//        listdong.forEach(s -> boxModel2.addElement(s.getTenDongSP()));
    }

    private void loadComboboxchatlieu() {
        cbo_chatlieu.removeAllItems();
        listchatlieu.forEach(s -> boxModel3.addElement(s.getTenCL()));
    }

    private int addSanPham() {

        SanPham sp = new SanPham();
        String masp = txt_masp.getText();
        String tensp = txt_tensp.getText();
        String mota = txt_mota.getText();
        int slt = Integer.valueOf(txt_soluong.getText());
        Double gianhap = Double.valueOf(txt_gianhap.getText());
        Double giaban = Double.valueOf(txt_giaban.getText());

        int tt = Integer.parseInt(txt_soluong.getText());

        if (tt == 0) {
            tt = 0;
            sp.setTrangthai(tt);
        } else {
            tt = 1;
            sp.setTrangthai(tt);
        }

        String nsx = cbo_hang.getSelectedItem().toString();
        for (NhaSanXuat n : listnsx) {
            if (n.getTenNhaSanXuat() == nsx) {
                int nhasx = n.getManhaSanXuat();
                sp.setNsx(nhasx);
            }
        }

        String mausac = cbo_mau.getSelectedItem().toString();
        for (MauSac n : lisstmau) {
            if (n.getTenMS() == mausac) {
                int mau = n.getMaMS();
                sp.setMamauSac(mau);
            }
        }

        String dong = cbo_dongsp.getSelectedItem().toString();
        for (DongSanPham d : listdong) {
            if (d.getTenDongSP().equalsIgnoreCase(dong)) {
                int dong1 = d.getMaDongSP();
                sp.setDongSP(dong1);
            }
        }

        String chatlieu = cbo_chatlieu.getSelectedItem().toString();
        for (ChatLieu n : listchatlieu) {
            if (n.getTenCL() == chatlieu) {
                int cl = n.getMaCL();
                sp.setChatLieu(cl);

            }
        }
        sp.setMaSP(masp);
        sp.setTenSP(tensp);
        sp.setGiaBan(giaban);
        sp.setGiaNhap(gianhap);
        sp.setMoTa(mota);
        sp.setSoLuongTon(slt);
        sp.setAnhSp(pathfile1);

        sp.setSize(size);
        spsv.add(sp);
        return 0;
    }

    public void updetesp() {

        SanPham sp = new SanPham();
        String masp = txt_masp.getText();
        String tensp = txt_tensp.getText();
        String mota = txt_mota.getText();
        int slt = Integer.valueOf(txt_soluong.getText());
        Double gianhap = Double.valueOf(txt_gianhap.getText());
        Double giaban = Double.valueOf(txt_giaban.getText());

        int tt = Integer.parseInt(txt_soluong.getText());

        if (tt == 0) {
            tt = 0;
            sp.setTrangthai(tt);
        } else {
            tt = 1;
            sp.setTrangthai(tt);
        }

        String nsx = cbo_hang.getSelectedItem().toString();
        for (NhaSanXuat n : listnsx) {
            if (n.getTenNhaSanXuat() == nsx) {
                int nhasx = n.getManhaSanXuat();
                sp.setNsx(nhasx);
            }
        }

        String mausac = cbo_mau.getSelectedItem().toString();
        for (MauSac n : lisstmau) {
            if (n.getTenMS() == mausac) {
                int mau = n.getMaMS();
                sp.setMamauSac(mau);
            }
        }

        String dong = cbo_dongsp.getSelectedItem().toString();
        for (DongSanPham d : listdong) {
            if (d.getTenDongSP().equalsIgnoreCase(dong)) {
                int dong1 = d.getMaDongSP();
                sp.setDongSP(dong1);
            }
        }

        String chatlieu = cbo_chatlieu.getSelectedItem().toString();
        for (ChatLieu n : listchatlieu) {
            if (n.getTenCL() == chatlieu) {
                int cl = n.getMaCL();
                sp.setChatLieu(cl);

            }
        }
        sp.setMaSP(masp);
        sp.setTenSP(tensp);
        sp.setGiaBan(giaban);
        sp.setGiaNhap(gianhap);
        sp.setMoTa(mota);
        sp.setSoLuongTon(slt);
        sp.setAnhSp(pathfile1);
        sp.setSize(size);

        spsv.update(sp);
        tk(-1);
        JOptionPane.showConfirmDialog(this, "updete thành công sản phẩm có mã là :" + masp);

    }

    public void tk(int status) {

        List<SanPhammd> output = spsv.getall();
        loadTable(output);
    }

    public void timkiem() {
        String ma = txt_timkiem.getText();
        List<SanPhammd> list = spsv.Timkiem(ma, tt);
        loadTable(list);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cbo_locLoai = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txt_timkiem = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        cbo_lochang = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        cbo_loctrangthai = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        Tên = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_masp = new javax.swing.JTextField();
        txt_tensp = new javax.swing.JTextField();
        txt_giaban = new javax.swing.JTextField();
        txt_gianhap = new javax.swing.JTextField();
        txt_soluong = new javax.swing.JTextField();
        cbo_dongsp = new javax.swing.JComboBox<>();
        cbo_mau = new javax.swing.JComboBox<>();
        cbo_hang = new javax.swing.JComboBox<>();
        cbo_size = new javax.swing.JComboBox<>();
        cbo_chatlieu = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_mota = new javax.swing.JTextArea();
        jpane_anh = new javax.swing.JLabel();
        btnAnh = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btn_xoa = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnChatLieu = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        btnChatLieu1 = new javax.swing.JButton();
        btnChatLieu2 = new javax.swing.JButton();
        btnChatLieu3 = new javax.swing.JButton();
        conhang = new javax.swing.JRadioButton();
        khongconhang = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_sanpham = new javax.swing.JTable();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setText("Loại:");

        cbo_locLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_locLoai, 0, 140, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cbo_locLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setText("Sản phẩm:");

        txt_timkiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_timkiemCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_timkiem, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setText("Hãng:");

        cbo_lochang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(cbo_lochang, 0, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cbo_lochang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setText("Tình trạng: ");

        cbo_loctrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Còn Hàng", "Hết Hàng" }));
        cbo_loctrangthai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_loctrangthaiItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(cbo_loctrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cbo_loctrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setPreferredSize(new java.awt.Dimension(1235, 706));

        Tên.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Tên.setText("Tên Sản phẩm: ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Loại: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Hãng:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Màu:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Size:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Chất Liệu:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Giá nhập:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Giá bán:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Mô tả:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Số lượng:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Trạng thái:");

        txt_masp.setEnabled(false);

        cbo_dongsp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbo_mau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_mau.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_mauItemStateChanged(evt);
            }
        });

        cbo_hang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_hang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_hangActionPerformed(evt);
            }
        });

        cbo_size.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L", "XL", "XXL", "XXXL" }));
        cbo_size.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_sizeItemStateChanged(evt);
            }
        });
        cbo_size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_sizeActionPerformed(evt);
            }
        });

        cbo_chatlieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txt_mota.setColumns(20);
        txt_mota.setRows(5);
        jScrollPane1.setViewportView(txt_mota);

        jpane_anh.setBackground(new java.awt.Color(255, 255, 255));
        jpane_anh.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAnh.setText("Chọn Ảnh");
        btnAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnhActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btn_xoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/Delete.png"))); // NOI18N
        btn_xoa.setText("Xóa");
        btn_xoa.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_add.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/New.png"))); // NOI18N
        btn_add.setText("  Thêm");
        btn_add.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/Change.png"))); // NOI18N
        jButton4.setText("  Sửa");
        jButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/Refresh-icon.png"))); // NOI18N
        jButton5.setText("Refresh");
        jButton5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        btnChatLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/kisspng-computer-icons-plus-sign-clip-art-plus-sign-5b4bfbdff0b3a7.2950963015317063359859.jpg"))); // NOI18N
        btnChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatLieuActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Mã Sản phẩm: ");

        btnChatLieu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/kisspng-computer-icons-plus-sign-clip-art-plus-sign-5b4bfbdff0b3a7.2950963015317063359859.jpg"))); // NOI18N
        btnChatLieu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatLieu1ActionPerformed(evt);
            }
        });

        btnChatLieu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/kisspng-computer-icons-plus-sign-clip-art-plus-sign-5b4bfbdff0b3a7.2950963015317063359859.jpg"))); // NOI18N
        btnChatLieu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatLieu2ActionPerformed(evt);
            }
        });

        btnChatLieu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iImage/kisspng-computer-icons-plus-sign-clip-art-plus-sign-5b4bfbdff0b3a7.2950963015317063359859.jpg"))); // NOI18N
        btnChatLieu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatLieu3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(conhang);
        conhang.setText("còn hàng");
        conhang.setAutoscrolls(true);
        conhang.setContentAreaFilled(false);
        conhang.setEnabled(false);

        buttonGroup1.add(khongconhang);
        khongconhang.setText("hết hàng");
        khongconhang.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel6))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(jLabel8)))
                                        .addGap(64, 64, 64))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(Tên, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbo_size, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_tensp)
                                    .addComponent(txt_masp)
                                    .addComponent(cbo_chatlieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbo_mau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbo_dongsp, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbo_hang, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChatLieu1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChatLieu2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChatLieu3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(conhang)
                                        .addGap(28, 28, 28)
                                        .addComponent(khongconhang))
                                    .addComponent(txt_gianhap, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_giaban, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jpane_anh, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnh)
                        .addGap(91, 91, 91)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_masp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(txt_gianhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel20))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_tensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(txt_giaban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Tên))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(cbo_dongsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12)
                                        .addComponent(txt_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnChatLieu1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnChatLieu2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(cbo_hang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13)
                                        .addComponent(conhang)
                                        .addComponent(khongconhang)))
                                .addGap(35, 35, 35))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jpane_anh, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAnh)
                                .addGap(12, 12, 12)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(cbo_mau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11))
                                    .addComponent(btnChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(cbo_size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbo_chatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                            .addComponent(btnChatLieu3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))))
        );

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Lọc sản phẩm");

        tbl_sanpham.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_sanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanphamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_sanpham);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1175, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
String pathfile1;
    private void btnAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnhActionPerformed
        JFileChooser f = new JFileChooser("C:\\Users\\ADMIN\\Desktop\\QLBH\\src");
        f.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = f.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File ftenanh = f.getSelectedFile();
            String pathfile = ftenanh.getAbsolutePath();
            pathfile1 = ftenanh.getAbsolutePath().replace("//", "--");
            System.out.println(pathfile1);
            BufferedImage b;
            try {
                b = ImageIO.read(ftenanh);
                jpane_anh.setIcon(new ImageIcon(b));
            } catch (Exception e) {
            }
        }

    }//GEN-LAST:event_btnAnhActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        addSanPham();
        tk(-1);

    }//GEN-LAST:event_btn_addActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:updetesp
        updetesp();
        tk(-1);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatLieuActionPerformed
        if(mauSac == null){
            mauSac = new ViewMauSac();
            mauSac.setVisible(true);
        }else{
            mauSac.toFront();
        }
    }//GEN-LAST:event_btnChatLieuActionPerformed

    private void btnChatLieu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatLieu1ActionPerformed
        if(dongsp == null){
            dongsp = new ViewDongSP();
            dongsp.setVisible(true);
        }else{
            dongsp.toFront();
        }
    }//GEN-LAST:event_btnChatLieu1ActionPerformed
    private static NhaSanXat nhaSanXatForm = null;
    private static ViewChatLieu chatLieu = null;
    private static ViewDongSP dongsp = null;
    private static ViewMauSac mauSac = null;
    private void btnChatLieu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatLieu2ActionPerformed
        if (nhaSanXatForm == null) {
            nhaSanXatForm = new NhaSanXat();
            nhaSanXatForm.setVisible(true);
        } else {

            nhaSanXatForm.toFront();
        }

    }//GEN-LAST:event_btnChatLieu2ActionPerformed

    private void btnChatLieu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatLieu3ActionPerformed
         if (chatLieu == null) {
            chatLieu = new ViewChatLieu();
            chatLieu.setVisible(true);
        } else {

            chatLieu.toFront();
        }
    }//GEN-LAST:event_btnChatLieu3ActionPerformed

    private void tbl_sanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanphamMouseClicked
        vitri = tbl_sanpham.getSelectedRow();
        SanPhammd sp = listsp.get(vitri);
//         SanPhammd sp = new SanPhammd();
        txt_masp.setText(sp.getMaSP());
        txt_tensp.setText(sp.getTenSP());
        txt_gianhap.setText(String.valueOf(sp.getGiaNhap()));
        txt_giaban.setText(String.valueOf(sp.getGiaBan()));
        txt_soluong.setText(String.valueOf(sp.getSoLuongTon()));
        txt_mota.setText(sp.getMoTa());

        List<String> liststrMS = new ArrayList<>();
        lisstmau.forEach(s -> liststrMS.add(s.getTenMS()));
        int indext = liststrMS.indexOf(sp.getTenmauSac());
        cbo_mau.setSelectedIndex(indext);

        List<String> liststrnsx = new ArrayList<>();
        listnsx.forEach(s -> liststrnsx.add(s.getTenNhaSanXuat()));
        int indext1 = liststrnsx.indexOf(sp.getTennsx());
        cbo_hang.setSelectedIndex(indext1);

        List<String> liststrdong = new ArrayList<>();
        listdong.forEach(s -> liststrdong.add(s.getTenDongSP()));
        int indext2 = liststrdong.indexOf(sp.getTendongSP());
        cbo_dongsp.setSelectedIndex(indext2);

        List<String> liststrcl = new ArrayList<>();
        listchatlieu.forEach(s -> liststrcl.add(s.getTenCL()));
        int indext3 = liststrnsx.indexOf(sp.getTennsx());
        cbo_hang.setSelectedIndex(indext3);

        String size = sp.getSize();
        if (size.equalsIgnoreCase("S")) {
            cbo_size.setSelectedIndex(0);
        } else if (size.equalsIgnoreCase("M")) {
            cbo_size.setSelectedIndex(1);
        } else if (size.equalsIgnoreCase("L")) {
            cbo_size.setSelectedIndex(2);
        } else if (size.equalsIgnoreCase("XL")) {
            cbo_size.setSelectedIndex(3);
        } else if (size.equalsIgnoreCase("XXL")) {
            cbo_size.setSelectedIndex(4);
        } else {
            cbo_size.setSelectedIndex(5);
        }

        if (listsp.get(vitri).getTrangthai() == 1) {
            conhang.setSelected(true);
        } else {
            khongconhang.setSelected(true);
        }
        jpane_anh.setIcon(new ImageIcon(String.valueOf(sp.getAnhSp())));
    }//GEN-LAST:event_tbl_sanphamMouseClicked

    private void cbo_mauItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_mauItemStateChanged

    }//GEN-LAST:event_cbo_mauItemStateChanged
    String size = "S";
    private void cbo_sizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_sizeItemStateChanged
        String a = cbo_size.getSelectedItem().toString();
        switch (a) {
            case "S":
                size = "S";
                break;
            case "M":
                size = "M";
                break;

            case "L":
                size = "L";
                break;
            case "XL":
                size = "XL";
                break;
            case "XXL":
                size = "XXL";
                break;
            case "XXXL":
                size = "XXXL";
                break;
            default:
                size = "S";
                break;
        }


    }//GEN-LAST:event_cbo_sizeItemStateChanged

    private void cbo_hangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_hangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_hangActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        String ma = txt_masp.getText();
        Integer row = tbl_sanpham.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chon dong cần xóa");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sản phẩm") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            spsv.Delete(ma);
            tk(-1);
            JOptionPane.showConfirmDialog(this, "đã xóa sản Phẩm có Mã Là :" + ma);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void cbo_sizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_sizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_sizeActionPerformed
    private int tt = -1;
    private void cbo_loctrangthaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_loctrangthaiItemStateChanged
        String f = cbo_loctrangthai.getSelectedItem().toString();

        switch (f) {
            case "Còn Hàng":
                tt = 1;
                break;

            case "Hết Hàng":
                tt = 0;
                break;

            default:
                tt = -1;
                break;
        }
        String ma = txt_timkiem.getText();
//        List<SanPhammd> output = spsv.Timkiem(ma, tt);
//        loadTable(output);

    }//GEN-LAST:event_cbo_loctrangthaiItemStateChanged

    private void txt_timkiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_timkiemCaretUpdate
        timkiem();
    }//GEN-LAST:event_txt_timkiemCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Tên;
    private javax.swing.JButton btnAnh;
    private javax.swing.JButton btnChatLieu;
    private javax.swing.JButton btnChatLieu1;
    private javax.swing.JButton btnChatLieu2;
    private javax.swing.JButton btnChatLieu3;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbo_chatlieu;
    private javax.swing.JComboBox<String> cbo_dongsp;
    private javax.swing.JComboBox<String> cbo_hang;
    private javax.swing.JComboBox<String> cbo_locLoai;
    private javax.swing.JComboBox<String> cbo_lochang;
    private javax.swing.JComboBox<String> cbo_loctrangthai;
    private javax.swing.JComboBox<String> cbo_mau;
    private javax.swing.JComboBox<String> cbo_size;
    private javax.swing.JRadioButton conhang;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jpane_anh;
    private javax.swing.JRadioButton khongconhang;
    private javax.swing.JTable tbl_sanpham;
    private javax.swing.JTextField txt_giaban;
    private javax.swing.JTextField txt_gianhap;
    private javax.swing.JTextField txt_masp;
    private javax.swing.JTextArea txt_mota;
    private javax.swing.JTextField txt_soluong;
    private javax.swing.JTextField txt_tensp;
    private javax.swing.JTextField txt_timkiem;
    // End of variables declaration//GEN-END:variables
}
