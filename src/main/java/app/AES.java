package app;

import java.util.Arrays;
import java.util.Base64;

public class AES {
	private static final int[] SBox = { 0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE,
			0xD7, 0xAB, 0x76, 0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72,
			0xC0, 0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15, 0x04,
			0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75, 0x09, 0x83, 0x2C,
			0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84, 0x53, 0xD1, 0x00, 0xED, 0x20,
			0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF, 0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33,
			0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8, 0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC,
			0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2, 0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E,
			0x3D, 0x64, 0x5D, 0x19, 0x73, 0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE,
			0x5E, 0x0B, 0xDB, 0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4,
			0x79, 0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08, 0xBA,
			0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A, 0x70, 0x3E, 0xB5,
			0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E, 0xE1, 0xF8, 0x98, 0x11, 0x69,
			0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF, 0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42,
			0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16 };

	private static final int[] invSBox = { 0x52, 0x09, 0x6A, 0xD5, 0x30, 0x36, 0xA5, 0x38, 0xBF, 0x40, 0xA3, 0x9E, 0x81,
			0xF3, 0xD7, 0xFB, 0x7C, 0xE3, 0x39, 0x82, 0x9B, 0x2F, 0xFF, 0x87, 0x34, 0x8E, 0x43, 0x44, 0xC4, 0xDE, 0xE9,
			0xCB, 0x54, 0x7B, 0x94, 0x32, 0xA6, 0xC2, 0x23, 0x3D, 0xEE, 0x4C, 0x95, 0x0B, 0x42, 0xFA, 0xC3, 0x4E, 0x08,
			0x2E, 0xA1, 0x66, 0x28, 0xD9, 0x24, 0xB2, 0x76, 0x5B, 0xA2, 0x49, 0x6D, 0x8B, 0xD1, 0x25, 0x72, 0xF8, 0xF6,
			0x64, 0x86, 0x68, 0x98, 0x16, 0xD4, 0xA4, 0x5C, 0xCC, 0x5D, 0x65, 0xB6, 0x92, 0x6C, 0x70, 0x48, 0x50, 0xFD,
			0xED, 0xB9, 0xDA, 0x5E, 0x15, 0x46, 0x57, 0xA7, 0x8D, 0x9D, 0x84, 0x90, 0xD8, 0xAB, 0x00, 0x8C, 0xBC, 0xD3,
			0x0A, 0xF7, 0xE4, 0x58, 0x05, 0xB8, 0xB3, 0x45, 0x06, 0xD0, 0x2C, 0x1E, 0x8F, 0xCA, 0x3F, 0x0F, 0x02, 0xC1,
			0xAF, 0xBD, 0x03, 0x01, 0x13, 0x8A, 0x6B, 0x3A, 0x91, 0x11, 0x41, 0x4F, 0x67, 0xDC, 0xEA, 0x97, 0xF2, 0xCF,
			0xCE, 0xF0, 0xB4, 0xE6, 0x73, 0x96, 0xAC, 0x74, 0x22, 0xE7, 0xAD, 0x35, 0x85, 0xE2, 0xF9, 0x37, 0xE8, 0x1C,
			0x75, 0xDF, 0x6E, 0x47, 0xF1, 0x1A, 0x71, 0x1D, 0x29, 0xC5, 0x89, 0x6F, 0xB7, 0x62, 0x0E, 0xAA, 0x18, 0xBE,
			0x1B, 0xFC, 0x56, 0x3E, 0x4B, 0xC6, 0xD2, 0x79, 0x20, 0x9A, 0xDB, 0xC0, 0xFE, 0x78, 0xCD, 0x5A, 0xF4, 0x1F,
			0xDD, 0xA8, 0x33, 0x88, 0x07, 0xC7, 0x31, 0xB1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xEC, 0x5F, 0x60, 0x51, 0x7F,
			0xA9, 0x19, 0xB5, 0x4A, 0x0D, 0x2D, 0xE5, 0x7A, 0x9F, 0x93, 0xC9, 0x9C, 0xEF, 0xA0, 0xE0, 0x3B, 0x4D, 0xAE,
			0x2A, 0xF5, 0xB0, 0xC8, 0xEB, 0xBB, 0x3C, 0x83, 0x53, 0x99, 0x61, 0x17, 0x2B, 0x04, 0x7E, 0xBA, 0x77, 0xD6,
			0x26, 0xE1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0C, 0x7D };

	private static final int[] rcon = { 0x00, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1B, 0x36 };

	private static final char[] BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
			.toCharArray();

	public static void main(String[] args) {
		String plaintext = "nhap mon attt10deser";
		String key = "ThIsIsAsEcReTkEy"; // Độ dài khóa phải là 16 byte
		
		byte[] ciphertext = encrypt(plaintext, key);
		String base64Ciphertext = encodeBase64(ciphertext);
		System.out.println("Encrypted: " + base64Ciphertext);
		System.out.println("Encrypted: " + base64Ciphertext.length());
		byte[] decrypted = decrypt(ciphertext, key);
		System.out.println("Decrypted: " + decryptedToString(decrypted));

	}

	static byte[] encrypt(String plaintext, String key) {
		byte[] bytePlaintext = plaintext.getBytes();
		byte[] byteKey = key.getBytes();
		
		int numBlocks = bytePlaintext.length / 16 + (bytePlaintext.length % 16 != 0 ? 1 : 0);
		
		byte[] ciphertext = new byte[numBlocks * 16]; // Tính kích thước của ciphertext dựa trên số lượng khối mã hóa
		
		int[][] subkeys = generateSubkeys(byteKey);

		for (int i = 0; i < bytePlaintext.length; i += 16) {
			byte[] block = Arrays.copyOfRange(bytePlaintext, i, i + 16);
			if (block.length < 16) {
				Arrays.fill(block, block.length, 16, (byte) (16 - block.length));
			}
			byte[] encryptedBlock = encrypt(block, subkeys);
			System.arraycopy(encryptedBlock, 0, ciphertext, i, 16);
		}
		return ciphertext;
	}

	private static byte[] encrypt(byte[] plaintext, int[][] subkeys) {
		byte[] state = new byte[16];
		for (int i = 0; i < 16; i++) {
			state[i] = plaintext[i];
		}
		addRoundKey(state, subkeys, 0);
		for (int round = 1; round < 10; round++) {
			subBytes(state);
			shiftRows(state);
			mixColumns(state);
			addRoundKey(state, subkeys, round);
		}
		subBytes(state);
		shiftRows(state);
		addRoundKey(state, subkeys, 10);

		return state;
	}

	private static int[][] generateSubkeys(byte[] key) {
		int[][] subkeys = new int[44][4];
		int[] temp = new int[4];
		int k = 0;
		// Khởi tạo 4 subkey đầu tiên từ key ban đầu
		for (int i = 0; i < 4; i++) {
			subkeys[i][0] = key[k++];
			subkeys[i][1] = key[k++];
			subkeys[i][2] = key[k++];
			subkeys[i][3] = key[k++];
		}
		// Tạo subkey cho các vòng lặp tiếp theo sử dụng phương thức key expansion
		for (int i = 4; i < 44; i++) {

			for (int j = 0; j < 4; j++) {
				temp[j] = subkeys[i - 1][j];
			}

			if (i % 4 == 0) {
				// Rotate
				int tempWord = temp[0];
				temp[0] = temp[1];
				temp[1] = temp[2];
				temp[2] = temp[3];
				temp[3] = tempWord;

				// Substitution
				for (int j = 0; j < 4; j++) {
					temp[j] = SBox[temp[j]];
				}
				// XOR với Rcon
				temp[0] ^= rcon[i / 4];
			}
			// XOR với Subkey Trước Đó
			for (int j = 0; j < 4; j++) {
				subkeys[i][j] = subkeys[i - 4][j] ^ temp[j];
			}
		}
		return subkeys;
	}

	private static void subBytes(byte[] state) {
		for (int i = 0; i < 16; i++) {
			state[i] = (byte) SBox[state[i] & 0xFF];
		}
	}

	private static void shiftRows(byte[] state) {
		byte[] temp = new byte[16];

		temp[0] = state[0];
		temp[1] = state[5];
		temp[2] = state[10];
		temp[3] = state[15];

		temp[4] = state[4];
		temp[5] = state[9];
		temp[6] = state[14];
		temp[7] = state[3];

		temp[8] = state[8];
		temp[9] = state[13];
		temp[10] = state[2];
		temp[11] = state[7];

		temp[12] = state[12];
		temp[13] = state[1];
		temp[14] = state[6];
		temp[15] = state[11];

		System.arraycopy(temp, 0, state, 0, 16);
	}

	private static void mixColumns(byte[] state) {
		for (int i = 0; i < 4; i++) {
			byte a = state[i * 4];
			byte b = state[i * 4 + 1];
			byte c = state[i * 4 + 2];
			byte d = state[i * 4 + 3];

			state[i * 4] = (byte) (mul((byte) 0x02, a) ^ mul((byte) 0x03, b) ^ c ^ d);
			state[i * 4 + 1] = (byte) (a ^ mul((byte) 0x02, b) ^ mul((byte) 0x03, c) ^ d);
			state[i * 4 + 2] = (byte) (a ^ b ^ mul((byte) 0x02, c) ^ mul((byte) 0x03, d));
			state[i * 4 + 3] = (byte) (mul((byte) 0x03, a) ^ b ^ c ^ mul((byte) 0x02, d));
//			System.out.println(a + " " + mul((byte) 0x03, a));
		}
	}

	private static byte mul(byte a, byte b) {
		byte p = 0;
		byte highBit = 0;
//		System.out.println();  
		//i = 0:  b = 14: 00001110    p:  0    a = 11: 00001011         
		//i = 1:  b =  7: 00000111    p: 22    a = 22: 00010110         
		//i = 2:  b =  3: 00000011    p: 58    a = 44: 00101100		
		//i = 3:  b =  1: 00000001    p: 98    a = 88: 01011000	
		//i = 4:  b =  0: 00000000    p: 98    a = 88: 01011000	
//		System.out.println("a,b = " + a + " " + b);
		for (int i = 0; i < 8; i++) {
//			System.out.println("i: " + i + " " + a + " " + b +  " "  + p);
			if ((b & 1) == 1) {
				p ^= a;
			}
			highBit = (byte) (a & 0x80);
//			System.out.print(highBit + " ");
			a <<= 1;
			if (highBit == (byte) 0x80) {
				a ^= 0x1B;
//				System.out.print(a);
			}
			b >>= 1;
//			System.out.println();
//			System.out.println("i: " + i + " " + a + " " + b +  " "  + p);
		}
//		System.out.println("p = " +p);
//		System.out.println();
		return p;
	}

	private static void addRoundKey(byte[] state, int[][] subkeys, int round) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				state[i * 4 + j] ^= (byte) subkeys[round * 4 + i][j];
			}
		}
	}

	static String encodeBase64(byte[] input) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < input.length; i += 3) {
			int chunk = (input[i] & 0xFF) << 16 | (i + 1 < input.length ? input[i + 1] & 0xFF : 0) << 8
					| (i + 2 < input.length ? input[i + 2] & 0xFF : 0);
			for (int j = 0; j < 4; j++) {
				if (i * 8 + j * 6 > input.length * 8) {
					result.append('=');
				} else {
					result.append(BASE64_CHARS[(chunk >> 6 * (3 - j)) & 0x3F]);
				}
			}
		}
		return result.toString();
	}

	static StringBuilder decryptedToString(byte[] arr) {
		StringBuilder decryptedString = new StringBuilder();
		for (int value : arr) {
			decryptedString.append((char) value);
		}
		return decryptedString;
	}

	static byte[] decrypt(byte[] ciphertext, String subkey) {
		int[][] subkeys = generateSubkeys(subkey.getBytes());
		byte[] decrypted = new byte[ciphertext.length];

		for (int i = 0; i < ciphertext.length; i += 16) {
			byte[] block = Arrays.copyOfRange(ciphertext, i, i + 16);
			byte[] decryptedBlock = decryptBlock(block, subkeys);
			System.arraycopy(decryptedBlock, 0, decrypted, i, 16);
		}

		return decrypted;
	}

	private static byte[] decryptBlock(byte[] block, int[][] subkeys) {
		byte[] state = new byte[16];
		for (int i = 0; i < 16; i++) {
			state[i] = block[i];
		}
		addRoundKey(state, subkeys, 10);
		invShiftRows(state);
		invSubBytes(state);
		for (int round = 9; round > 0; round--) {
			addRoundKey(state, subkeys, round);
			invMixColumns(state);
			invShiftRows(state);
			invSubBytes(state);
		}
		addRoundKey(state, subkeys, 0);
		return state;
	}

	private static void invShiftRows(byte[] state) {
		byte[] temp = new byte[16];

		temp[0] = state[0];
		temp[1] = state[13];
		temp[2] = state[10];
		temp[3] = state[7];

		temp[4] = state[4];
		temp[5] = state[1];
		temp[6] = state[14];
		temp[7] = state[11];

		temp[8] = state[8];
		temp[9] = state[5];
		temp[10] = state[2];
		temp[11] = state[15];

		temp[12] = state[12];
		temp[13] = state[9];
		temp[14] = state[6];
		temp[15] = state[3];

		System.arraycopy(temp, 0, state, 0, 16);
	}

	private static void invSubBytes(byte[] state) {
		for (int i = 0; i < 16; i++) {
			state[i] = (byte) invSBox[state[i] & 0xFF];
		}
	}

	private static void invMixColumns(byte[] state) {
		for (int i = 0; i < 4; i++) {
			byte a = state[i * 4];
			byte b = state[i * 4 + 1];
			byte c = state[i * 4 + 2];
			byte d = state[i * 4 + 3];

			state[i * 4] = (byte) (mul((byte) 0x0E, a) ^ mul((byte) 0x0B, b) ^ mul((byte) 0x0D, c)
					^ mul((byte) 0x09, d));
			state[i * 4 + 1] = (byte) (mul((byte) 0x09, a) ^ mul((byte) 0x0E, b) ^ mul((byte) 0x0B, c)
					^ mul((byte) 0x0D, d));
			state[i * 4 + 2] = (byte) (mul((byte) 0x0D, a) ^ mul((byte) 0x09, b) ^ mul((byte) 0x0E, c)
					^ mul((byte) 0x0B, d));
			state[i * 4 + 3] = (byte) (mul((byte) 0x0B, a) ^ mul((byte) 0x0D, b) ^ mul((byte) 0x09, c)
					^ mul((byte) 0x0E, d));
		}
	}

}
