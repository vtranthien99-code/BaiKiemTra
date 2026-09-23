package models;

public class BenhAnThuong extends BenhAn {
    private double phiNamVien;

    public BenhAnThuong(int stt, String maBenhAn, String maBenhNhan, String tenBenhNhan, String ngayNhapVien, String ngayRaVien, String lyDoNhapVien, double phiNamVien) {
        super(stt, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien);
        this.phiNamVien = phiNamVien;
    }

    public double getPhiNamVien() { return phiNamVien; }
    public void setPhiNamVien(double phiNamVien) { this.phiNamVien = phiNamVien; }

    @Override
    public String toCSVString() {
        return String.join(",", 
            String.valueOf(stt), maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, String.valueOf(phiNamVien), "", ""
        );
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Phí nằm viện: %.2f", phiNamVien);
    }
}
