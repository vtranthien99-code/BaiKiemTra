package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Validator {
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static boolean isValidMaBenhAn(String maBenhAn) {
        return Pattern.matches("^BA-\\d{3}$", maBenhAn);
    }

    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isNgayNhapVienBeforeOrEqualNgayRaVien(String ngayNhap, String ngayRa) {
        try {
            LocalDate admissionDate = LocalDate.parse(ngayNhap, DATE_FORMATTER);
            LocalDate dischargeDate = LocalDate.parse(ngayRa, DATE_FORMATTER);
            return !admissionDate.isAfter(dischargeDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidLoaiVIP(String loaiVIP) {
        return loaiVIP.equals("VIP I") || loaiVIP.equals("VIP II") || loaiVIP.equals("VIP III");
    }
}
