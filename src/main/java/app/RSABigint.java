package app;

import java.math.BigInteger;


public class RSABigint {

    // Hàm tính ước chung lớn nhất
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = b;
            b = a.mod(b);
            a = temp;
        }
        return a;
    }

    // Hàm tìm phần tử nghịch đảo modulo
    public static BigInteger modInverse(BigInteger a, BigInteger m) {
        BigInteger m0 = m;
        BigInteger y = BigInteger.ZERO;
        BigInteger x = BigInteger.ONE;

        while (a.compareTo(BigInteger.ONE) > 0) {
            BigInteger[] qr = a.divideAndRemainder(m);
            BigInteger q = qr[0];
            BigInteger t = m;
            m = qr[1];
            a = t;
            t = y;
            y = x.subtract(q.multiply(y));
            x = t;
        }
        if (x.compareTo(BigInteger.ZERO) < 0)
            x = x.add(m0);
        return x;
    }

    // Hàm tạo số nguyên tố ngẫu nhiên có độ dài bits
    public static BigInteger randomPrime(int bits) {
        BigInteger random;
        do {
            random = new BigInteger(bits, new java.util.Random());
        } while (!isPrime(random));
        return random;
    }

    // Hàm kiểm tra xem một số có phải là số nguyên tố không
    public static boolean isPrime(BigInteger number) {
        if (number.compareTo(BigInteger.ONE) <= 0)
            return false;
        for (BigInteger i = BigInteger.TWO; i.compareTo(number.sqrt()) <= 0; i = i.add(BigInteger.ONE)) {
            if (number.mod(i).equals(BigInteger.ZERO))
                return false;
        }
        return true;
    }

    // Hàm tạo cặp khóa RSA với p và q ngẫu nhiên
    public static BigInteger[][] generateKeyPair(int bits, BigInteger p, BigInteger q) {
        // Tính n và phi(n)
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        
        // Chọn số e sao cho 1 < e < phi và e là số nguyên tố cùng nhau với phi
        BigInteger e = BigInteger.valueOf(17); // Hoặc chọn một số ngẫu nhiên thích hợp khác
        // Tính d, là phần tử nghịch đảo của e trong modulo phi
        BigInteger d = modInverse(e, phi);
        // Tạo khóa công khai và khóa riêng tư
        return new BigInteger[][]{{n, e}, {n, d}};
    }


    // Hàm mã hóa RSA
    public static String encrypt(String message, BigInteger[] publicKey) {
        BigInteger n = publicKey[0];
        BigInteger e = publicKey[1];
        StringBuilder ciphertext = new StringBuilder();

        // Chuyển đổi mỗi ký tự hoặc số trong thông điệp thành mã Unicode và mã hóa chúng
        for (int i = 0; i < message.length(); i++) {
            int charCode = (int) message.charAt(i);
            BigInteger encryptedChar = BigInteger.valueOf(charCode).modPow(e, n);
            ciphertext.append(encryptedChar).append(' '); // Thêm dấu cách để phân biệt các ký tự mã hóa
        }

        return ciphertext.toString().trim(); // Xóa dấu cách ở cuối chuỗi mã hóa
    }

    // Hàm giải mã RSA
 
    public static String decrypt(String ciphertext, BigInteger[] privateKey) {
        BigInteger n = privateKey[0];
        BigInteger d = privateKey[1];
        StringBuilder plaintext = new StringBuilder();

        // Tách các khối mã hóa và giải mã chúng
        String[] blocks = ciphertext.split(" ");

        for (String block : blocks) {
            // Giải mã khối
            BigInteger encryptedCharCode = new BigInteger(block);
            BigInteger decryptedCharCode = encryptedCharCode.modPow(d, n);
            char decryptedChar = (char) decryptedCharCode.intValue(); // Chuyển mã Unicode thành ký tự
            plaintext.append(decryptedChar); // Thêm ký tự đã giải mã vào plaintext
        }

        return plaintext.toString();
    }



//    public static void main(String[] args) {
//        // Test chương trình
//        BigInteger[][] keyPair = generateKeyPair(512);
//        BigInteger[] publicKey = keyPair[0];
//        BigInteger[] privateKey = keyPair[1];
//
//        String message = "Hello, world!";
//        String encryptedMessage = encrypt(message, publicKey);
//        System.out.println("Encrypted message: " + encryptedMessage);
//
//        String decryptedMessage = decrypt(encryptedMessage, privateKey);
//        System.out.println("Decrypted message: " + decryptedMessage);
//    }
}