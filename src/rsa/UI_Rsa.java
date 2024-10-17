package rsa;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.Cipher;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class UI_Rsa extends JFrame {
	protected AlgorithmRSA rsa;
	private JTextField txtGui;
	private JTextArea txtPrivateKey;
	private JTextArea txtPublicKey;
	private JComboBox<String> cmbSize;
	protected Cipher cipher;
	private JButton btnGui;
	private JTextField txtNhan;

	public static void main(String[] args) {
		UI_Rsa ui = new UI_Rsa();
		ui.setTitle("Encrypted and Decryted RSA algorithm");
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ui.setSize(680, 610);
		ui.setResizable(false);
		ui.setLocationRelativeTo(null);
		ui.setVisible(true);
	}

	public UI_Rsa() {
		rsa = new AlgorithmRSA();
		JLabel lblTitle = new JLabel("RSA Algorithm");
		lblTitle.setBounds(10, 0, 659, 26);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JPanel panel = new JPanel();
		panel.setBounds(10, 36, 649, 121);
		panel.setBackground(new Color(240, 240, 240));

		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 157, 649, 198);
		panel1.setBackground(new Color(240, 240, 240));

		JPanel panel2 = new JPanel();
		panel2.setBounds(10, 355, 649, 198);
		panel2.setLayout(null);
		panel2.setBackground(UIManager.getColor("Button.background"));

		JLabel lblNhan = new JLabel("Người nhận");
		lblNhan.setIcon(new ImageIcon(UI_Rsa.class.getResource("/img/user_mature.png")));
		lblNhan.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhan.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNhan.setBounds(0, 11, 78, 25);
		panel2.add(lblNhan);

		txtNhan = new JTextField();
		txtNhan.setColumns(10);
		txtNhan.setBounds(89, 12, 446, 23);
		panel2.add(txtNhan);

		JLabel lblGiaiMa = new JLabel("Giải mã");
		lblGiaiMa.setHorizontalAlignment(SwingConstants.LEFT);
		lblGiaiMa.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblGiaiMa.setBounds(0, 46, 78, 25);
		panel2.add(lblGiaiMa);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(89, 45, 549, 149);
		panel2.add(scrollPane1);

		JTextArea txtGiaiMa = new JTextArea();
		txtGiaiMa.setLineWrap(true);
		scrollPane1.setViewportView(txtGiaiMa);

		JButton btnGiaiMa = new JButton("Giải mã");
		btnGiaiMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mess = txtNhan.getText();
				String decrypt = rsa.decrypt(mess);
				txtGiaiMa.setText(decrypt);
			}
		});
		btnGiaiMa.setEnabled(false);
		btnGiaiMa.setBounds(545, 12, 85, 23);
		panel2.add(btnGiaiMa);
		panel1.setLayout(null);

		JLabel lblGui = new JLabel("Người gửi");
		lblGui.setIcon(new ImageIcon(UI_Rsa.class.getResource("/img/user_star.png")));
		lblGui.setHorizontalAlignment(SwingConstants.LEFT);
		lblGui.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblGui.setBounds(0, 11, 78, 25);
		panel1.add(lblGui);

		txtGui = new JTextField();
		txtGui.setBounds(89, 12, 446, 23);
		panel1.add(txtGui);
		txtGui.setColumns(10);

		JLabel lblMaHoa = new JLabel("Mã hóa");
		lblMaHoa.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblMaHoa.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaHoa.setBounds(0, 46, 78, 25);
		panel1.add(lblMaHoa);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 45, 549, 149);
		panel1.add(scrollPane);

		JTextArea txtMaHoa = new JTextArea();
		txtMaHoa.setLineWrap(true);
		scrollPane.setViewportView(txtMaHoa);

		btnGui = new JButton("send");
		btnGui.setEnabled(false);
		btnGui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				String mess = txtGui.getText();
				if (mess.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tin nhắn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String encrypt;
				try {
					encrypt = rsa.encrypt(mess);
					txtMaHoa.setText(encrypt);
					txtNhan.setText(encrypt);
					btnGiaiMa.setEnabled(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnGui.setBounds(545, 12, 85, 23);
		panel1.add(btnGui);
		panel.setLayout(null);

		JLabel lblSize = new JLabel("Kích thước");
		lblSize.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblSize.setHorizontalAlignment(SwingConstants.LEFT);
		lblSize.setBounds(0, 30, 80, 25);
		panel.add(lblSize);

		JLabel lblPrivateKey = new JLabel("Private key");
		lblPrivateKey.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblPrivateKey.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrivateKey.setBounds(0, 65, 80, 25);
		panel.add(lblPrivateKey);

		JLabel lblPublicKey = new JLabel("Public key");
		lblPublicKey.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblPublicKey.setHorizontalAlignment(SwingConstants.LEFT);
		lblPublicKey.setBounds(342, 65, 80, 25);
		panel.add(lblPublicKey);

		cmbSize = new JComboBox<String>();
		cmbSize.setFont(new Font("Tahoma", Font.PLAIN, 10));
		cmbSize.setModel(new DefaultComboBoxModel(new String[] {"512", "1024", "2048", "4096"}));
		cmbSize.setBounds(89, 30, 80, 25);
		panel.add(cmbSize);

		JScrollPane scrPrivateKey = new JScrollPane();
		scrPrivateKey.setBounds(89, 65, 230, 52);
		panel.add(scrPrivateKey);

		txtPrivateKey = new JTextArea();
		txtPrivateKey.setLineWrap(true);
		scrPrivateKey.setViewportView(txtPrivateKey);

		JScrollPane scrPublicKey = new JScrollPane();
		scrPublicKey.setBounds(415, 65, 230, 52);
		panel.add(scrPublicKey);

		txtPublicKey = new JTextArea();
		txtPublicKey.setLineWrap(true);
		scrPublicKey.setViewportView(txtPublicKey);

		JButton btnTaoKhoa = new JButton("Tạo khóa");
		btnTaoKhoa.setIcon(new ImageIcon(UI_Rsa.class.getResource("/img/vcard_key.png")));
		btnTaoKhoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int lengthBit = Integer.parseInt(cmbSize.getSelectedItem().toString());
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						rsa.keyRSA(lengthBit);
					}
				});
				t.start();
				
				try {
		            // Chờ cho luồng kết thúc
		            t.join();
		        } catch (InterruptedException ex) {
		            ex.printStackTrace();
		        }
				txtPublicKey.setText(rsa.getE().toString());
				txtPrivateKey.setText(rsa.getD().toString());
				btnGui.setEnabled(true);
			}
		});
		btnTaoKhoa.setBounds(179, 30, 140, 25);
		panel.add(btnTaoKhoa);

		JLabel lblNewLabel = new JLabel("Khóa");
		lblNewLabel.setIcon(new ImageIcon(UI_Rsa.class.getResource("/img/key_start.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(0, 0, 100, 25);
		panel.add(lblNewLabel);
		getContentPane().setLayout(null);
		getContentPane().add(panel1);
		getContentPane().add(panel);
		getContentPane().add(lblTitle);
		getContentPane().add(panel2);
	}

}
