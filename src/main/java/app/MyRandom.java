package app;

import java.util.ArrayList;
import java.util.List;

public class MyRandom {
    private static long seed = System.currentTimeMillis();

    public static void setSeed(long newSeed) {
        seed = newSeed;
    }

    public static int nextInt(int bound) {
        if (bound <= 0)
            throw new IllegalArgumentException("bound must be positive");

        seed = (seed * 1103515245 + 12345) % (1L << 31);
        return (int) ((seed % Integer.MAX_VALUE) % bound);
    }
    
    public static int modPow1(int base, int exponent, int modulus) {
	    int result = 1;
	    for (int i = 0; i < exponent; i++) {
//	    	m = 2 ^ 29 % 35 = 32
//			m= base ^ exponent % modulus
	        result = (result * base) % modulus;
	    }
	    return result;
	}
 
    
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
    
    
    public static void main(String[] args) {
         
    	
//    	 MyRandom.setSeed(System.currentTimeMillis());
      
         
//             int randomTwoDigit = MyRandom.nextInt(90) + 10;
//             System.out.println("Random two-digit integer: " + randomTwoDigit);
//    	String message="H";
//    	int charCode = (int) message.charAt(0);
    	 String mang = "ABC as";
         StringBuilder ciphertext = new StringBuilder();
         List<String> mangso = new ArrayList<>();
         
//          Encrypt
         for (int i = 0; i < mang.length(); i++) {
             int charCode = (int) mang.charAt(i);
             ciphertext.append(modPow1(charCode, 17, 623)).append(" "); // Append a delimiter
         }
         
         System.out.println("Ciphertext: " + ciphertext);

         // Decrypt
         String[] blocks = ciphertext.toString().trim().split(" "); // Split ciphertext into blocks
         for (String block : blocks) {
        	    System.out.println(block+ " ");
        	}
         System.out.println();
         for (String block : blocks) {
             int encryptedCharCode = Integer.parseInt(block);
             int decryptedCharCode = modPow1(encryptedCharCode, 497, 623);
             char decryptedChar = (char) decryptedCharCode;
             System.out.print(decryptedChar);
         }
         
//         System.out.println(tinhkpm(17,40));
         
         
         System.out.println(   modPow1(65, 17, 623));
    }
}

