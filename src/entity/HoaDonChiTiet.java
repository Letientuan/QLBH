/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author thong
 */
public class HoaDonChiTiet {

    private Long maHDCTTT;
    private HoaDon maHD;
    private SanPham maSP;
    private Integer soLuong;
    private Double donGia;
    private String tenSP;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(Long maHDCTTT, HoaDon maHD, SanPham maSP, Integer soLuong, Double donGia, String tenSP) {
        this.maHDCTTT = maHDCTTT;
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tenSP = tenSP;
    }

    public Long getMaHDCTTT() {
        return maHDCTTT;
    }

    public void setMaHDCTTT(Long maHDCTTT) {
        this.maHDCTTT = maHDCTTT;
    }

    public HoaDon getMaHD() {
        return maHD;
    }

    public void setMaHD(HoaDon maHD) {
        this.maHD = maHD;
    }

    public SanPham getMaSP() {
        return maSP;
    }

    public void setMaSP(SanPham maSP) {
        this.maSP = maSP;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    
}
