package model;

public class ChamCong {
    private String maChamCong;
    private String ngayChamCong;
    private String nguoiChamCong;
    private String gioVao;
    private String gioRa;

    public ChamCong() {
    }

    public ChamCong(String maChamCong, String ngayChamCong, String nguoiChamCong, String gioVao, String gioRa) {
        this.maChamCong = maChamCong;
        this.ngayChamCong = ngayChamCong;
        this.nguoiChamCong = nguoiChamCong;
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

    public String getNguoiChamCong() {
        return nguoiChamCong;
    }

    public void setNguoiChamCong(String nguoiChamCong) {
        this.nguoiChamCong = nguoiChamCong;
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

}
