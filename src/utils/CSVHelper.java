package utils;

import models.BenhAn;
import models.BenhAnThuong;
import models.BenhAnVIP;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    private static final String FILE_PATH = "data/medical_records.csv";
    private static final String HEADER = "STT,Mã bệnh án,Mã bệnh nhân,Tên bệnh nhân,Ngày nhập viện,Ngày ra viện,Lý do nhập viện,Phí nằm viện,Loại VIP,Thời hạn VIP";

    public static List<BenhAn> readBenhAnList() {
        List<BenhAn> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return list;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", -1);
                
                int stt = Integer.parseInt(parts[0]);
                String maBenhAn = parts[1];
                String maBenhNhan = parts[2];
                String tenBenhNhan = parts[3];
                String ngayNhapVien = parts[4];
                String ngayRaVien = parts[5];
                String lyDoNhapVien = parts[6];

                if (!parts[7].isEmpty()) {
                    double phiNamVien = Double.parseDouble(parts[7]);
                    list.add(new BenhAnThuong(stt, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, phiNamVien));
                } else {
                    String loaiVIP = parts[8];
                    String thoiHanVIP = parts[9];
                    list.add(new BenhAnVIP(stt, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, loaiVIP, thoiHanVIP));
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
        return list;
    }

    public static void writeBenhAnList(List<BenhAn> list) {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(HEADER);
            bw.newLine();
            for (BenhAn ba : list) {
                bw.write(ba.toCSVString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }
}
