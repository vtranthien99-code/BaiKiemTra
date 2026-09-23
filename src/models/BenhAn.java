package models;

public abstract class BenhAn {
    protected int stt;
    protected String maBenhAn;
    protected String maBenhNhan;
    protected String tenBenhNhan;
    protected String ngayNhapVien;
    protected String ngayRaVien;
    protected String lyDoNhapVien;

    public BenhAn(int stt, String maBenhAn, String maBenhNhan, String tenBenhNhan, String ngayNhapVien, String ngayRaVien, String lyDoNhapVien) {
        this.stt = stt;
        this.maBenhAn = maBenhAn;
        this.maBenhNhan = maBenhNhan;
        this.tenBenhNhan = tenBenhNhan;
        this.ngayNhapVien = ngayNhapVien;
        this.ngayRaVien = ngayRaVien;
        this.lyDoNhapVien = lyDoNhapVien;
    }

    public int getStt() { return stt; }
    public void setStt(int stt) { this.stt = stt; }
    public String getMaBenhAn() { return maBenhAn; }
    public void setMaBenhAn(String maBenhAn) { this.maBenhAn = maBenhAn; }
    public String getMaBenhNhan() { return maBenhNhan; }
    public void setMaBenhNhan(String maBenhNhan) { this.maBenhNhan = maBenhNhan; }
    public String getTenBenhNhan() { return tenBenhNhan; }
    public void setTenBenhNhan(String tenBenhNhan) { this.tenBenhNhan = tenBenhNhan; }
    public String getNgayNhapVien() { return ngayNhapVien; }
    public void setNgayNhapVien(String ngayNhapVien) { this.ngayNhapVien = ngayNhapVien; }
    public String getNgayRaVien() { return ngayRaVien; }
    public void setNgayRaVien(String ngayRaVien) { this.ngayRaVien = ngayRaVien; }
    public String getLyDoNhapVien() { return lyDoNhapVien; }
    public void setLyDoNhapVien(String lyDoNhapVien) { this.lyDoNhapVien = lyDoNhapVien; }

    public abstract String toCSVString();
    
    @Override
    public String toString() {
        return String.format("STT: %d, Mã BA: %s, Mã BN: %s, Tên BN: %s, Ngày nhập: %s, Ngày ra: %s, Lý do: %s", 
                stt, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien);
    }
}
