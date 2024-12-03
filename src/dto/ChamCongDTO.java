package dto;

public class ChamCongDTO {
    private String maChamCong;
    private String ngayChamCong;
    private String maNguoiChamCong;
    private String tenNguoiChamCong;
    private String gioVao;
    private String gioRa;
    private double trongGioHanhChinh;
    private double ngoaiGioHanhChinh;
    private double tong;

    public ChamCongDTO() {
    }

    public ChamCongDTO(String maChamCong, String ngayChamCong, String maNguoiChamCong, String tenNguoiChamCong, String gioVao, String gioRa) {
        this.maChamCong = maChamCong;
        this.ngayChamCong = ngayChamCong;
        this.maNguoiChamCong = maNguoiChamCong;
        this.tenNguoiChamCong = tenNguoiChamCong;
        this.gioVao = gioVao;
        this.gioRa = gioRa;
    }
    
    

    public String getMaChamCong() {
        return maChamCong;
    }

    public void setMaChamCong(String maChamCong) {
        this.maChamCong = maChamCong;
    }

    public String getNgayChamCong() {
        return ngayChamCong;
    }

    public void setNgayChamCong(String ngayChamCong) {
        this.ngayChamCong = ngayChamCong;
    }

    public String getMaNguoiChamCong() {
        return maNguoiChamCong;
    }

    public void setMaNguoiChamCong(String maNguoiChamCong) {
        this.maNguoiChamCong = maNguoiChamCong;
    }

    public String getTenNguoiChamCong() {
        return tenNguoiChamCong;
    }

    public void setTenNguoiChamCong(String tenNguoiChamCong) {
        this.tenNguoiChamCong = tenNguoiChamCong;
    }

    public String getGioVao() {
        return gioVao;
    }

    public void setGioVao(String gioVao) {
        this.gioVao = gioVao;
    }

    public String getGioRa() {
        return gioRa;
    }

    public void setGioRa(String gioRa) {
        this.gioRa = gioRa;
    }

    public double getTrongGioHanhChinh() {
        return trongGioHanhChinh;
    }

    public void setTrongGioHanhChinh(double trongGioHanhChinh) {
        this.trongGioHanhChinh = trongGioHanhChinh;
    }

    public double getNgoaiGioHanhChinh() {
        return ngoaiGioHanhChinh;
    }

    public void setNgoaiGioHanhChinh(double ngoaiGioHanhChinh) {
        this.ngoaiGioHanhChinh = ngoaiGioHanhChinh;
    }

    public double getTong() {
        return tong;
    }

    public void setTong(double tong) {
        this.tong = tong;
    }
    
    
    
}
