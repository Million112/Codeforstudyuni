package tab_main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
    
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        // Tạo đối tượng MessageDigest với thuật toán SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());  // Mã hóa mật khẩu
        StringBuilder hexString = new StringBuilder();
        
        // Chuyển đổi byte array thành chuỗi hex
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();  // Trả về mật khẩu đã được mã hóa
    }
}
