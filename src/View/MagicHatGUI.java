package View;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MagicHatGUI extends JFrame {

	protected JPanel w_panel;
	protected JTextField txtAd;
	protected JPanel panelPlayer;
	protected JLabel lblOyuncuBilgileri;
	protected JLabel lblAd;
	protected JButton btnBaslat;
	protected JButton btnYeniOyuncu;
	protected JPanel panelGame;
	protected JLabel lblSoru;
	protected JPanel panel;
	protected JLabel lblPuan;
	protected JLabel lblOyuncuAdi;
	protected JLabel lblSkor;
	protected JButton btnRestart;
	protected JButton btn_orta;
	protected JButton btn_sag;
	protected JButton btn_sol;
	protected JLabel lblSonuc;
	protected JLabel lblDurum;
	protected int whereBall = 1;
	protected static int count = 0;
	protected int highSkor = 0;
	protected String highName = "";
	protected JLabel lblHighSkor;
	protected JLabel lblHighName;
	protected JLabel lblBirinci;
	protected File dosya = null;
	protected FileWriter fw;
	protected BufferedWriter bw;
	protected ArrayList top = new ArrayList();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MagicHatGUI frame = new MagicHatGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MagicHatGUI() {
		setTitle("Magic Hat Game");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 942, 595);
		w_panel = new JPanel();
		w_panel.setBackground(Color.WHITE);
		w_panel.setForeground(Color.WHITE);
		setContentPane(w_panel);
		w_panel.setLayout(null);

		panelPlayer = new JPanel();
		panelPlayer.setBorder(new MatteBorder(2, 2, 5, 2, (Color) Color.LIGHT_GRAY));
		panelPlayer.setForeground(Color.BLACK);
		panelPlayer.setBackground(Color.WHITE);
		panelPlayer.setBounds(10, 10, 917, 180);
		w_panel.add(panelPlayer);
		panelPlayer.setLayout(null);

		lblOyuncuBilgileri = new JLabel("Oyuncu Bilgileri");
		lblOyuncuBilgileri.setBackground(Color.YELLOW);
		lblOyuncuBilgileri.setForeground(Color.RED);
		lblOyuncuBilgileri.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblOyuncuBilgileri.setBounds(10, 10, 192, 32);
		panelPlayer.add(lblOyuncuBilgileri);

		lblAd = new JLabel("Adinizi Giriniz:");
		lblAd.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblAd.setBounds(10, 44, 114, 32);
		panelPlayer.add(lblAd);

		txtAd = new JTextField();
		txtAd.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		txtAd.setBounds(122, 44, 179, 32);
		panelPlayer.add(txtAd);
		txtAd.setColumns(10);

		btnBaslat = new JButton("Baslat");
		btnBaslat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count = 0;
				lblSonuc.setText("");
				if (txtAd.getText().length() == 0) {
					panelGame.setVisible(false);
					txtAd.setText("");
					JOptionPane.showMessageDialog(null, "Lutfen Bir Kullanici Adi Giriniz", "Uyari",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						dosya = new File("C:/Users/mgmet/OneDrive/Masaustu/MagicHat.txt");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					panelGame.setVisible(true);
					lblOyuncuAdi.setText(txtAd.getText());
					lblSkor.setText("100");
					whereBall = (int) (Math.random() * 3 + 1);
				}
			}
		});
		btnBaslat.setForeground(Color.RED);
		btnBaslat.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		btnBaslat.setBounds(10, 94, 291, 40);
		panelPlayer.add(btnBaslat);

		btnYeniOyuncu = new JButton("Yeni Oyuncu");
		btnYeniOyuncu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelGame.setVisible(false);
				txtAd.setText("");
				JOptionPane.showMessageDialog(null, "Yeni Kullanici Bilgilerinizi Giriniz!", "Kayit",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnYeniOyuncu.setForeground(Color.GREEN);
		btnYeniOyuncu.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		btnYeniOyuncu.setBounds(394, 61, 310, 78);
		panelPlayer.add(btnYeniOyuncu);

		JButton btnQuit = new JButton("Cik");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dosya.exists()) {
					try {
						dosya.createNewFile();
						fw = new FileWriter(dosya);
						bw = new BufferedWriter(fw);
						for (int i = 0; top.size() > i; i++) {
							bw.write(top.get(i) + " ");
							i++;
							bw.write(top.get(i) + " \n");
						}
						bw.flush();
						bw.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				System.exit(0);
			}
		});
		btnQuit.setForeground(Color.RED);
		btnQuit.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		btnQuit.setBounds(779, 12, 128, 40);
		panelPlayer.add(btnQuit);

		panelGame = new JPanel();
		panelGame.setBorder(new MatteBorder(2, 2, 5, 2, (Color) Color.GRAY));
		panelGame.setBackground(Color.WHITE);
		panelGame.setBounds(10, 265, 917, 283);
		w_panel.add(panelGame);
		panelGame.setVisible(false);
		panelGame.setLayout(null);

		lblSoru = new JLabel("Kirmizi Top Hangi Sapkanin Anltinda? Tahmin Et!");
		lblSoru.setForeground(Color.BLUE);
		lblSoru.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblSoru.setBackground(Color.WHITE);
		lblSoru.setBounds(10, 10, 450, 32);
		panelGame.add(lblSoru);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(670, 10, 237, 263);
		panelGame.add(panel);
		panel.setLayout(null);

		lblPuan = new JLabel("SKOR");
		lblPuan.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuan.setForeground(Color.MAGENTA);
		lblPuan.setBackground(Color.WHITE);
		lblPuan.setToolTipText("");
		lblPuan.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblPuan.setBounds(10, 10, 217, 32);
		panel.add(lblPuan);

		lblOyuncuAdi = new JLabel("abc");
		lblOyuncuAdi.setVerticalAlignment(SwingConstants.BOTTOM);
		lblOyuncuAdi.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblOyuncuAdi.setBounds(10, 52, 192, 32);
		panel.add(lblOyuncuAdi);

		lblSkor = new JLabel("100");
		lblSkor.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblSkor.setBounds(10, 94, 192, 32);
		panel.add(lblSkor);

		btnRestart = new JButton("Yeniden Dene");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				whereBall = (int) (Math.random() * 3 + 1);
				count = 0;
				btn_orta.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
				btn_sag.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
				btn_sol.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
				lblSonuc.setText("");

			}
		});
		btnRestart.setForeground(Color.CYAN);
		btnRestart.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		btnRestart.setBounds(10, 156, 217, 97);
		panel.add(btnRestart);

		btn_orta = new JButton();
		btn_orta.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
		btn_orta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count++;
				if (count == 1) {
					if (whereBall == 2) {
						String swap;
						int puan;
						swap = lblSkor.getText();
						puan = Integer.valueOf(swap);
						puan += 150;
						swap = String.valueOf(puan);
						if (highSkor < puan) {
							highSkor = puan;
							highName = lblOyuncuAdi.getText();
							top.add(highName);
							top.add(highSkor);
							lblHighName.setText(highName);
							lblHighSkor.setText(String.valueOf(highSkor));
						}
						lblSkor.setText(swap);
						btn_orta.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/dolu.png")));
						lblSonuc.setText("Tebrikler Topu Buldunuz +150 Puan Kazandiniz");
						lblSonuc.setForeground(Color.GREEN);
					} else {
						String swap;
						int puan;
						swap = lblSkor.getText();
						puan = Integer.valueOf(swap);
						puan -= 100;
						btn_orta.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/bos.png")));
						lblSonuc.setText("Maalesef Topu Bulamadiniz -100 Puan Kaybettiniz");
						lblSonuc.setForeground(Color.RED);
						if (puan < 0) {
							JOptionPane.showMessageDialog(null, "Oldunuz!!Bastan Baslayiniz", "Uyari",
									JOptionPane.ERROR_MESSAGE);
							btn_orta.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
							panelGame.setVisible(false);
							txtAd.setText("");
						}
						swap = String.valueOf(puan);
						lblSkor.setText(swap);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Birden Fazla Secim Yapamazsiniz!!", "Uyari",
							JOptionPane.INFORMATION_MESSAGE);
					btn_orta.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
					btn_sag.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
					btn_sol.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
				}
			}
		});
		btn_orta.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		btn_orta.setBounds(245, 73, 157, 160);
		panelGame.add(btn_orta);

		btn_sag = new JButton();
		btn_sag.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
		btn_sag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count++;
				if (count == 1) {
					if (whereBall == 3) {
						String swap;
						int puan;
						swap = lblSkor.getText();
						puan = Integer.valueOf(swap);
						puan += 150;
						swap = String.valueOf(puan);
						if (highSkor < puan) {
							highSkor = puan;
							highName = lblOyuncuAdi.getText();
							top.add(highName);
							top.add(highSkor);
							lblHighName.setText(highName);
							lblHighSkor.setText(String.valueOf(highSkor));
						}
						lblSkor.setText(swap);
						btn_sag.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/dolu.png")));
						lblSonuc.setText("Tebrikler Topu Buldunuz +150 Puan Kazandiniz");
						lblSonuc.setForeground(Color.GREEN);
					} else {
						String swap;
						int puan;
						swap = lblSkor.getText();
						puan = Integer.valueOf(swap);
						puan -= 100;
						btn_sag.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/bos.png")));
						lblSonuc.setText("Maalesef Topu Bulamadiniz -100 Puan Kaybettiniz");
						lblSonuc.setForeground(Color.RED);
						if (puan < 0) {
							JOptionPane.showMessageDialog(null, "Oldunuz!!Bastan Baslayiniz", "Uyari",
									JOptionPane.ERROR_MESSAGE);
							btn_sag.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
							panelGame.setVisible(false);
							txtAd.setText("");
						}
						swap = String.valueOf(puan);
						lblSkor.setText(swap);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Birden Fazla Secim Yapamazsiniz!!", "Uyari",
							JOptionPane.INFORMATION_MESSAGE);
					btn_orta.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
					btn_sag.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
					btn_sol.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
				}
			}
		});
		btn_sag.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		btn_sag.setBounds(444, 73, 157, 160);
		panelGame.add(btn_sag);

		btn_sol = new JButton();
		btn_sol.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
		btn_sol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count++;
				if (count == 1) {
					if (whereBall == 1) {
						String swap;
						int puan;
						swap = lblSkor.getText();
						puan = Integer.valueOf(swap);
						puan += 150;

						if (highSkor < puan) {
							highSkor = puan;
							highName = lblOyuncuAdi.getText();
							top.add(highName);
							top.add(highSkor);
							lblHighName.setText(highName);
							lblHighSkor.setText(String.valueOf(highSkor));
						}

						swap = String.valueOf(puan);
						lblSkor.setText(swap);
						btn_sol.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/dolu.png")));
						lblSonuc.setForeground(Color.GREEN);
						lblSonuc.setText("Tebrikler Topu Buldunuz +150 Puan Kazandiniz");
					} else {
						String swap;
						int puan;
						swap = lblSkor.getText();
						puan = Integer.valueOf(swap);
						puan -= 100;
						btn_sol.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/bos.png")));
						lblSonuc.setForeground(Color.RED);
						lblSonuc.setText("Maalesef Topu Bulamadiniz -100 Puan Kaybettiniz");
						if (puan < 0) {
							JOptionPane.showMessageDialog(null, "Oldunuz!!Bastan Baslayiniz", "Uyari",
									JOptionPane.ERROR_MESSAGE);
							btn_sol.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
							panelGame.setVisible(false);
							txtAd.setText("");
						}
						swap = String.valueOf(puan);
						lblSkor.setText(swap);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Birden Fazla Secim Yapamazsiniz!!", "Uyari",
							JOptionPane.INFORMATION_MESSAGE);
					btn_orta.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
					btn_sag.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
					btn_sol.setIcon(new ImageIcon(MagicHatGUI.class.getResource("/img/kapali.png")));
				}
			}
		});
		btn_sol.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		btn_sol.setBounds(39, 73, 157, 160);
		panelGame.add(btn_sol);

		lblDurum = new JLabel("Durum");
		lblDurum.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDurum.setToolTipText("");
		lblDurum.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblDurum.setBounds(10, 235, 192, 38);
		panelGame.add(lblDurum);

		lblSonuc = new JLabel("");
		lblSonuc.setBackground(Color.WHITE);
		lblSonuc.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSonuc.setToolTipText("");
		lblSonuc.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblSonuc.setBounds(212, 235, 439, 38);
		panelGame.add(lblSonuc);

		lblBirinci = new JLabel("En Yuksek Skor  :");
		lblBirinci.setForeground(Color.ORANGE);
		lblBirinci.setToolTipText("");
		lblBirinci.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblBirinci.setBounds(105, 209, 225, 32);
		w_panel.add(lblBirinci);

		lblHighName = new JLabel("Dona");
		lblHighName.setForeground(Color.ORANGE);
		lblHighName.setToolTipText("");
		lblHighName.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblHighName.setBounds(435, 209, 146, 32);
		w_panel.add(lblHighName);

		lblHighSkor = new JLabel("1");
		lblHighSkor.setForeground(Color.ORANGE);
		lblHighSkor.setToolTipText("");
		lblHighSkor.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblHighSkor.setBounds(686, 209, 146, 32);
		w_panel.add(lblHighSkor);

	}
}
