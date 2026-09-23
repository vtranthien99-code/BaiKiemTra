package controllers;

import exceptions.DuplicateMedicalRecordException;
import models.BenhAn;
import models.BenhAnThuong;
import models.BenhAnVIP;
import utils.CSVHelper;
import utils.Validator;

import java.util.List;
import java.util.Scanner;

public class BenhAnController {
    private Scanner scanner = new Scanner(System.in);
    private List<BenhAn> danhSachBenhAn;

    public BenhAnController() {
        this.danhSachBenhAn = CSVHelper.readBenhAnList();
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n-- CHƯƠNG TRÌNH QUẢN LÝ BỆNH ÁN --");
            System.out.println("1. Thêm mới");
            System.out.println("2. Xóa");
            System.out.println("3. Xem danh sách");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng: ");
            
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    themMoi();
                    break;
                case "2":
                    xoa();
                    break;
                case "3":
                    xemDanhSach();
                    break;
                case "4":
                    System.out.println("Đã thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private void themMoi() {
        System.out.println("\n-- Thêm mới bệnh án --");
        System.out.println("1. Bệnh án thường");
        System.out.println("2. Bệnh án VIP");
        System.out.print("Chọn loại bệnh án: ");
        String loai = scanner.nextLine();

        if (!loai.equals("1") && !loai.equals("2")) {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }

        try {
            int stt = danhSachBenhAn.isEmpty() ? 1 : danhSachBenhAn.get(danhSachBenhAn.size() - 1).getStt() + 1;

            String maBenhAn = inputMaBenhAn();
            System.out.print("Nhập mã bệnh nhân: ");
            String maBenhNhan = scanner.nextLine();
            System.out.print("Nhập tên bệnh nhân: ");
            String tenBenhNhan = scanner.nextLine();
            
            String ngayNhapVien = inputDate("Nhập ngày nhập viện (dd/MM/yyyy): ");
            String ngayRaVien = inputDateAndValidate("Nhập ngày ra viện (dd/MM/yyyy): ", ngayNhapVien);
            
            System.out.print("Nhập lý do nhập viện: ");
            String lyDoNhapVien = scanner.nextLine();

            BenhAn ba;
            if (loai.equals("1")) {
                System.out.print("Nhập phí nằm viện: ");
                double phiNamVien = Double.parseDouble(scanner.nextLine());
                ba = new BenhAnThuong(stt, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, phiNamVien);
            } else {
                String loaiVIP = inputLoaiVIP();
                String thoiHanVIP = inputDate("Nhập thời hạn VIP (dd/MM/yyyy): ");
                ba = new BenhAnVIP(stt, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, loaiVIP, thoiHanVIP);
            }

            danhSachBenhAn.add(ba);
            CSVHelper.writeBenhAnList(danhSachBenhAn);
            System.out.println("Thêm mới thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private String inputMaBenhAn() throws DuplicateMedicalRecordException {
        while (true) {
            System.out.print("Nhập mã bệnh án (BA-XXX): ");
            String ma = scanner.nextLine();
            if (!Validator.isValidMaBenhAn(ma)) {
                System.out.println("Mã bệnh án không đúng định dạng. Vui lòng nhập lại.");
                continue;
            }
            // Check duplicate
            boolean isDuplicate = danhSachBenhAn.stream().anyMatch(ba -> ba.getMaBenhAn().equals(ma));
            if (isDuplicate) {
                throw new DuplicateMedicalRecordException("Bệnh án đã tồn tại.");
            }
            return ma;
        }
    }

    private String inputDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String date = scanner.nextLine();
            if (Validator.isValidDate(date)) {
                return date;
            }
            System.out.println("Ngày không hợp lệ hoặc sai định dạng. Vui lòng nhập lại.");
        }
    }

    private String inputDateAndValidate(String prompt, String ngayNhap) {
        while (true) {
            String ngayRa = inputDate(prompt);
            if (Validator.isNgayNhapVienBeforeOrEqualNgayRaVien(ngayNhap, ngayRa)) {
                return ngayRa;
            }
            System.out.println("Ngày ra viện phải lớn hơn hoặc bằng ngày nhập viện. Vui lòng nhập lại.");
        }
    }

    private String inputLoaiVIP() {
        while (true) {
            System.out.print("Nhập loại VIP (VIP I, VIP II, VIP III): ");
            String loai = scanner.nextLine();
            if (Validator.isValidLoaiVIP(loai)) {
                return loai;
            }
            System.out.println("Loại VIP không hợp lệ. Vui lòng nhập lại.");
        }
    }

    private void xoa() {
        System.out.print("\nNhập mã bệnh án cần xóa: ");
        String ma = scanner.nextLine();
        
        BenhAn target = danhSachBenhAn.stream()
            .filter(ba -> ba.getMaBenhAn().equals(ma))
            .findFirst()
            .orElse(null);

        if (target == null) {
            System.out.println("Không tìm thấy bệnh án với mã " + ma);
            return;
        }

        System.out.print("Bạn có chắc chắn muốn xóa bệnh án này không? (Yes/No): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Yes")) {
            danhSachBenhAn.remove(target);
            CSVHelper.writeBenhAnList(danhSachBenhAn);
            System.out.println("Xóa thành công!");
            xemDanhSach();
        } else {
            System.out.println("Đã hủy xóa.");
        }
    }

    private void xemDanhSach() {
        System.out.println("\n-- Danh sách bệnh án --");
        if (danhSachBenhAn.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        for (BenhAn ba : danhSachBenhAn) {
            System.out.println(ba.toString());
        }
    }
}
