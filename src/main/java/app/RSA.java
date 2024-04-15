package app;

import java.math.BigInteger;
import java.util.Random;

public class RSA {

    // Hàm tính ước chung lớn nhất
//	public static int gcd(int a, int b) {
//	    while (b != 0) {
//	        int temp = b;
//	        b = a % b;
//	        a = temp;
//	    }
//	    return a;
//	}


    // Hàm tìm phần tử nghịch đảo modulo
	
//	C1:
	public static int modInverse(int a, int m) {
	    int m0 = m;
	    int y = 0;
	    int x = 1;

	    while (a > 1) {
	        int q = a / m;
	        int t = m;
	        m = a % m;
	        a = t;
	        t = y;
	        y = x - q * y;
	        x = t;
	    }
	    if (x < 0)
	        x += m0;
	    return x;
	}
	
//	c2
	
	public static int tinhkpm(int e ,int phi) {
    	int result=0;
    	int d=0;
//    	n*e-1/phi
    	for(int i=1;i<10000;i++) {
    		result=(i*e-1)%phi;
    		if(result==0) {
        		d=i;
//        		System.out.println(d); 
        		break;
        	}
    	}
    	
    	
    	return d;
    }
	// Hàm kiểm tra xem một số có phải là số nguyên tố không
	public static boolean isPrime(int number) {
	    if (number <= 1)
	        return false;
	    for (int i = 2; i * i <= number; i++) {
	        if (number % i == 0)
	            return false;
	    }
	    return true;
	}


    // Hàm tạo số nguyên tố ngẫu nhiên có độ dài bits
	

	public static int randomPrime(int bits) {
//	    Random random = new Random();
		MyRandom random= new MyRandom();
	    int randomInt;
	    do {
	        randomInt = random.nextInt((int) Math.pow(2, bits));
	    } while (!isPrime(randomInt));
	    return randomInt;
	}


    

    // Hàm tạo cặp khóa RSA với p và q ngẫu nhiên
	public static int[][] generateKeyPair(int bits, int p, int q) {
	    // Tính n và phi(n)
	    int n = p * q;
	    int phi = (p - 1) * (q - 1);

	    // Chọn số e sao cho 1 < e < phi và e là số nguyên tố cùng nhau với phi
	    int e = 17; // Hoặc chọn một số ngẫu nhiên thích hợp khác
	    // Tính d, là phần tử nghịch đảo của e trong modulo phi
//	    int d = modInverse(e, phi);
	    
	    int d = tinhkpm(e, phi);
	    // Tạo khóa công khai và khóa riêng tư
	    return new int[][]{{n, e}, {n, d}};
	}


	public static int modPow(int base, int exponent, int modulus) {
	    int result = 1;
	    while (exponent > 0) {
	        if (exponent % 2 == 1) {
	            result = (result * base) % modulus;
	        }
	        base = (base * base) % modulus;
	        exponent >>= 1; // exponent /= 2;
	    }
	    return result;
	}
	public static int modPow1(int base, int exponent, int modulus) {
	    int result = 1;
	    for (int i = 0; i < exponent; i++) {
	        result = (result * base) % modulus;
	    }
	    return result;
	}

	public static String encrypt(String message, int[] publicKey) {
	    int n = publicKey[0];
	    int e = publicKey[1];
	    StringBuilder ciphertext = new StringBuilder();

	    // Chuyển đổi mỗi ký tự hoặc số trong thông điệp thành mã Unicode và mã hóa chúng
	    for (int i = 0; i < message.length(); i++) {
	        int charCode = (int) message.charAt(i);
	        int encryptedChar = modPow1(charCode, e, n);
	        ciphertext.append(encryptedChar).append(' '); // Thêm dấu cách để phân biệt các ký tự mã hóa
	    }

	    return ciphertext.toString().trim(); // Xóa dấu cách ở cuối chuỗi mã hóa
	}

	public static String decrypt(String ciphertext, int[] privateKey) {
	    int n = privateKey[0];
	    int d = privateKey[1];
	    StringBuilder plaintext = new StringBuilder();

	    // Tách các khối mã hóa và giải mã chúng
	    String[] blocks = ciphertext.split(" ");

	    for (String block : blocks) {
	        // Giải mã khối
	        int encryptedCharCode = Integer.parseInt(block);
	        int decryptedCharCode = modPow1(encryptedCharCode, d, n);
	        char decryptedChar = (char) decryptedCharCode; // Chuyển mã Unicode thành ký tự
	        plaintext.append(decryptedChar); // Thêm ký tự đã giải mã vào plaintext
	    }

	    return plaintext.toString();
	}


 
}