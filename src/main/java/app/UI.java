package app;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class UI extends JFrame {

	private JPanel contentPane;
	private JTextField txtTextp;
	private JTextField textq;
	private JTextField textphi;
	private JTextField textd;
	private JTextField textn;
	private JTextField texte;
	private JTextField textthongdiep;
	private JTextField textmahoa;
	private JTextField textdamahoa;
	private JTextField textgiaima;
	JTextArea txtnhapthongdiepAES;
	JTextArea txamahoaAES;
	JTextArea txagiaimaAES;
	private int p = 0;
	private int q = 0;
	private JTextField txtMaKhoaAES;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		setResizable(false);

		RSA rsa = new RSA();

		AES aes = new AES();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1048, 645);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1012, 584);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Demo AES", null, panel, null);
		panel.setLayout(null);

		JLabel a = new JLabel("Nhập Khóa");
		a.setFont(new Font("Tahoma", Font.BOLD, 20));
		a.setBounds(32, 30, 123, 35);
		panel.add(a);

		txtMaKhoaAES = new JTextField();
		txtMaKhoaAES.setColumns(10);
		txtMaKhoaAES.setBounds(175, 31, 586, 35);
		panel.add(txtMaKhoaAES);

		JButton btnLmMi_1 = new JButton("Làm mới");
		btnLmMi_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnLmMi_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLmMi_1.setBounds(792, 31, 165, 23);
		panel.add(btnLmMi_1);

		JLabel lblMHa_1 = new JLabel("Thông điệp đã mã hóa");
		lblMHa_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMHa_1.setBounds(390, 163, 513, 33);
		panel.add(lblMHa_1);

		JButton btnMahoaAES = new JButton("Mã hóa");
		btnMahoaAES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = txtMaKhoaAES.getText();
				String thongdiep = txtnhapthongdiepAES.getText();
				if (key.length() % 16 != 0) {
					JOptionPane.showMessageDialog(null, "Độ dài của key phải là 16 ký tự.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} else {
					byte[] ciphertext = aes.encrypt(thongdiep, key);
					String base64Ciphertext = aes.encodeBase64(ciphertext);
					txamahoaAES.setText(base64Ciphertext);
				}

			}
		});
		btnMahoaAES.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnMahoaAES.setBounds(267, 301, 113, 48);
		panel.add(btnMahoaAES);

		JLabel lblGiai_1 = new JLabel("Thông điệp đã giải mã");
		lblGiai_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblGiai_1.setBounds(757, 163, 513, 33);
		panel.add(lblGiai_1);

		JLabel lblNewLabel_1_3_2 = new JLabel("Thông điệp gốc ");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_3_2.setBounds(33, 163, 523, 33);
		panel.add(lblNewLabel_1_3_2);

		JButton btnGiaiMaAES = new JButton("Giải mã");
		btnGiaiMaAES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = txtMaKhoaAES.getText();
				String thongdiep = txtnhapthongdiepAES.getText();
				if (key.length() % 16 != 0) {
					JOptionPane.showMessageDialog(null, "Độ dài của key phải là 16 ký tự.", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} else {
					byte[] ciphertext = aes.encrypt(thongdiep, key);
					byte[] decrypted = aes.decrypt(ciphertext, key);
					txagiaimaAES.setText(aes.decryptedToString(decrypted).toString());
				}
			}
		});
		btnGiaiMaAES.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGiaiMaAES.setBounds(642, 301, 101, 48);
		panel.add(btnGiaiMaAES);

		Border border = BorderFactory.createLineBorder(Color.BLACK);		
		txtnhapthongdiepAES = new JTextArea();
		txtnhapthongdiepAES.setBorder(border);
		txtnhapthongdiepAES.setBounds(10, 235, 242, 201);
		txtnhapthongdiepAES.setLineWrap(true);
		txtnhapthongdiepAES.setWrapStyleWord(true);
		panel.add(txtnhapthongdiepAES);

		txamahoaAES = new JTextArea();
		txamahoaAES.setBorder(border);
		txamahoaAES.setBounds(390, 235, 242, 201);
		txamahoaAES.setLineWrap(true);
		txamahoaAES.setWrapStyleWord(true);
		panel.add(txamahoaAES);

		txagiaimaAES = new JTextArea();
		txagiaimaAES.setBorder(border);
		txagiaimaAES.setBounds(757, 235, 225, 201);
		txagiaimaAES.setLineWrap(true);
		txagiaimaAES.setWrapStyleWord(true);
		panel.add(txagiaimaAES);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Demo RSA", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tạo khóa");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 102, 33);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Số p");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(20, 55, 76, 19);
		panel_1.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Random p và q");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p = rsa.randomPrime(7);
				q = rsa.randomPrime(7);
				txtTextp.setText(p + "");
				textq.setText(q + "");
				textphi.setText((p - 1) * (q - 1) + "");
			}
		});
		btnNewButton.setBounds(140, 11, 208, 23);
		panel_1.add(btnNewButton);

		txtTextp = new JTextField();
		txtTextp.setBounds(117, 56, 86, 20);
		panel_1.add(txtTextp);
		txtTextp.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Số q");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(20, 85, 76, 19);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Khóa công khai");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(24, 132, 127, 33);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_1 = new JLabel("Số phi");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(20, 112, 76, 19);
		panel_1.add(lblNewLabel_1_1_1);

		textq = new JTextField();
		textq.setColumns(10);
		textq.setBounds(117, 86, 86, 20);
		panel_1.add(textq);

		textphi = new JTextField();
		textphi.setColumns(10);
		textphi.setBounds(117, 113, 86, 20);
		panel_1.add(textphi);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Số n");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1.setBounds(20, 176, 76, 19);
		panel_1.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Số e");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_2.setBounds(20, 216, 76, 19);
		panel_1.add(lblNewLabel_1_1_1_2);

		JLabel lblNewLabel_1_2_1 = new JLabel("Khóa bí mật");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2_1.setBounds(20, 259, 127, 33);
		panel_1.add(lblNewLabel_1_2_1);

		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Số d");
		lblNewLabel_1_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_2_1.setBounds(20, 310, 76, 19);
		panel_1.add(lblNewLabel_1_1_1_2_1);

		textd = new JTextField();
		textd.setEditable(false);
		textd.setColumns(10);
		textd.setBounds(150, 311, 86, 20);
		panel_1.add(textd);

		textn = new JTextField();
		textn.setEditable(false);
		textn.setColumns(10);
		textn.setBounds(150, 177, 86, 20);
		panel_1.add(textn);

		texte = new JTextField();
		texte.setEditable(false);
		texte.setColumns(10);
		texte.setBounds(150, 217, 86, 20);
		panel_1.add(texte);

		JButton btnTaoKhoa = new JButton("Tạo khóa");
		btnTaoKhoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textn.setText(p * q + "");
				texte.setText(17 + "");
				textd.setText(rsa.generateKeyPair(1024, p, q)[1][1] + "");
			}
		});
		btnTaoKhoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTaoKhoa.setBounds(140, 384, 208, 23);
		panel_1.add(btnTaoKhoa);

		JLabel lblMHa = new JLabel("Mã hóa");
		lblMHa.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMHa.setBounds(392, 11, 113, 33);
		panel_1.add(lblMHa);

		JLabel lblNewLabel_1_3 = new JLabel("Thông điệp");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(392, 59, 86, 19);
		panel_1.add(lblNewLabel_1_3);

		textthongdiep = new JTextField();
		textthongdiep.setBounds(392, 86, 256, 97);
		panel_1.add(textthongdiep);
		textthongdiep.setColumns(10);

		JButton btnMahoa = new JButton("Mã hóa");
		btnMahoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String message = textthongdiep.getText();
				int[] publicKey = rsa.generateKeyPair(1024, p, q)[0];
				String encryptedMessage = rsa.encrypt(message, publicKey);

				textmahoa.setText(encryptedMessage);
				System.out.println(encryptedMessage);

			}
		});
		btnMahoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnMahoa.setBounds(392, 216, 208, 23);
		panel_1.add(btnMahoa);

		JLabel lblNewLabel_1_3_1 = new JLabel("Thông điệp đã mã hóa");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3_1.setBounds(392, 270, 284, 19);
		panel_1.add(lblNewLabel_1_3_1);

		textmahoa = new JTextField();
		textmahoa.setColumns(10);
		textmahoa.setBounds(392, 311, 256, 97);
		panel_1.add(textmahoa);

		JButton btnLmMi = new JButton("Làm mới");
		btnLmMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textq.setText("");
				textmahoa.setText("");
			}
		});
		btnLmMi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLmMi.setBounds(392, 459, 208, 23);
		panel_1.add(btnLmMi);

		JLabel lblGiai = new JLabel("Giải Mã");
		lblGiai.setFont(new Font("Dialog", Font.BOLD, 20));
		lblGiai.setBounds(707, 11, 113, 33);
		panel_1.add(lblGiai);

		textdamahoa = new JTextField();
		textdamahoa.setColumns(10);
		textdamahoa.setBounds(705, 86, 256, 97);
		panel_1.add(textdamahoa);

		JLabel lblNewLabel_1_3_1_1 = new JLabel("Thông điệp đã mã hóa");
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3_1_1.setBounds(707, 59, 284, 19);
		panel_1.add(lblNewLabel_1_3_1_1);

		JLabel lblNewLabel_1_3_1_2 = new JLabel("Thông điệp đã giải mã");
		lblNewLabel_1_3_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3_1_2.setBounds(707, 270, 284, 19);
		panel_1.add(lblNewLabel_1_3_1_2);

		textgiaima = new JTextField();
		textgiaima.setColumns(10);
		textgiaima.setBounds(707, 311, 256, 97);
		panel_1.add(textgiaima);

		JButton btnGiiM = new JButton("Giải mã");
		btnGiiM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String ciphertext = textdamahoa.getText();
				int[] privateKey = rsa.generateKeyPair(1024, p, q)[1];
				String decryptedMessage = rsa.decrypt(ciphertext, privateKey);
//	                JOptionPane.showMessageDialog(null, "Decrypted Message: " + decryptedMessage);
//	                
//	                System.out.println("Decrypted Message: " + decryptedMessage);
//	                messageTextFieldDecrypt.setText("");
				textgiaima.setText(decryptedMessage);
				System.out.println(decryptedMessage);

			}
		});
		btnGiiM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGiiM.setBounds(707, 216, 208, 23);
		panel_1.add(btnGiiM);

		btnLmMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtTextp.setText("");
				textq.setText("");
				textphi.setText("");
				textd.setText("");
				textn.setText("");
				texte.setText("");
				textthongdiep.setText("");
				textmahoa.setText("");
				textdamahoa.setText("");
				textgiaima.setText("");
			}
		});
	}
}
