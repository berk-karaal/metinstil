import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JList;
import java.awt.TextArea;
import javax.swing.SwingConstants;

public class Formatter extends JFrame {

	private JPanel contentPane;
	private JTextField mtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formatter frame = new Formatter();
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
	public Formatter() {
		setTitle("Metinstil");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel mtnPnl = new JPanel();
		mtnPnl.setVisible(false);
		mtnPnl.setLayout(null);
		mtnPnl.setOpaque(false);
		mtnPnl.setBounds(37, 235, 423, 30);
		contentPane.add(mtnPnl);
		
		JLabel lblHarf = new JLabel("Harf arası ne olsun?");
		lblHarf.setForeground(Color.BLACK);
		lblHarf.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
		lblHarf.setBounds(0, 0, 250, 30);
		mtnPnl.add(lblHarf);
		
		mtn = new JTextField();
		mtn.setBounds(235, 0, 96, 29);
		mtnPnl.add(mtn);
		mtn.setColumns(10);
		
		JPanel spacePnl = new JPanel();
		spacePnl.setOpaque(false);
		spacePnl.setBounds(37, 235, 423, 30);
		contentPane.add(spacePnl);
		spacePnl.setLayout(null);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(172, 1, 45, 30);
		spacePnl.add(spinner);
		spinner.setFont(new Font("Source Code Pro", Font.BOLD, 18));
		spinner.setValue(1);
		
		JLabel lblBolukSays = new JLabel("Boşluk sayısı: ");
		lblBolukSays.setForeground(new Color(0, 0, 0));
		lblBolukSays.setFont(new Font("Source Code Pro", Font.PLAIN, 18));
		lblBolukSays.setBounds(0, 0, 168, 30);
		spacePnl.add(lblBolukSays);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// seçilen item değiştiğinde
				if(comboBox.getSelectedIndex()==0) {
					// boşluklu seçildi ise
					lblBolukSays.setText("Boşluk sayısı: ");
					spacePnl.setVisible(true);
					mtnPnl.setVisible(false);
				}else if(comboBox.getSelectedIndex()==6) {
					// Metin arası seçildi
					spacePnl.setVisible(false);
					mtnPnl.setVisible(true);
				}else {
					mtnPnl.setVisible(false);
					spacePnl.setVisible(false);
				}
			}
		});
		
		comboBox.setToolTipText("Stil seçiniz");
		comboBox.setForeground(Color.BLACK);
		comboBox.setFocusTraversalKeysEnabled(false);
		comboBox.setFocusable(false);
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBox.setBackground(new Color(102, 205, 170));
		comboBox.setFont(new Font("Noto Sans", Font.PLAIN, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Boşluklu", "Ters çevir", "Alt satıra", "Özel karakter", "Tırrek tarzı", "Ünlüleri sil", "Harf arası", "Yılan dili", "Harf arası \"|\""}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(37, 188, 162, 37);
		contentPane.add(comboBox);
		
		TextArea textArea = new TextArea();
		textArea.setFont(new Font("Noto Mono", Font.PLAIN, 19));
		textArea.setBounds(37, 289, 423, 114);
		contentPane.add(textArea);
		
		TextArea ta = new TextArea();
		ta.setFont(new Font("Noto Mono", Font.PLAIN, 19));
		ta.setBounds(37, 84, 423, 98);
		contentPane.add(ta);
		
		JButton btnDntr = new JButton("Dönüştür");
		btnDntr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String rs = ""; // return edilecek string
				String is = ta.getText(); //Bize girilen string
				int slct = comboBox.getSelectedIndex();
				if(slct == 0) {
					//Boşluklu stil
					String spaceString="";
					for(int i=0;i<Integer.valueOf(""+spinner.getValue());i++) {
						spaceString+=" ";
					}
					rs+=is.replaceAll("",spaceString).trim();	
				}
				else if(slct == 1) {
					//reverse
					rs=new StringBuilder(is).reverse().toString();
				}
				else if(slct==2) {
					// alt satır
					rs+=is.replaceAll("", "\n").trim();
				}
				else if(slct==3) {
					// özel karakter
					rs=is.toLowerCase().replaceAll("a", "@").replaceAll("i", "1").replaceAll("b", "8").replaceAll("t", "+").replaceAll("s", "5").replaceAll("ı", "1").replaceAll("o", "0").replaceAll("e", "3");
				}
				else if(slct == 4) {
					//Tırrek stil
					for(int i=0;i<is.length();i++) {
						rs+=(i%2==0)?Character.toUpperCase(is.charAt(i)):Character.toLowerCase(is.charAt(i));
					}
				}
				else if(slct==5) {
					// ünlüleri sil
					rs=is.replaceAll("[aeıioöuüAEIİOÖUÜ]", "");
				}
				else if(slct==6){
					// harflerin arasına metin koy koy
					
					for(int i=0;i<is.length()-1;i++) {
						rs+=is.charAt(i);
						if(is.charAt(i) != ' ' &&is.charAt(i+1) != ' ') {
							rs+=mtn.getText();
						}
					}
					rs+=is.charAt(is.length()-1);
				}
				else if(slct==7) {
					// yılan dili
					for(int i=0;i<is.length()-1;i++) {
						if(i==0) {
							if(is.charAt(i) != ' ') rs+="T";
						}else {
							if(is.charAt(i-1)==' ' && is.charAt(i) != ' ') rs+="T";
							else if (is.charAt(i) != ' ') rs+="s";
							else if (is.charAt(i)==' ') rs+=" ";
						}
					}
				}
				else if(slct==8){
					// harflerin arasına "|" koy koy
					
					for(int i=0;i<is.length()-1;i++) {
						rs+=is.charAt(i);
						if(is.charAt(i) != ' ' &&is.charAt(i+1) != ' ') {
							rs+="|";
						}
					}
					rs+=is.charAt(is.length()-1);
				}
				
				textArea.setText(rs);
			}
		});
		btnDntr.setFocusTraversalKeysEnabled(false);
		btnDntr.setFocusable(false);
		btnDntr.setRequestFocusEnabled(false);
		btnDntr.setFont(new Font("Noto Sans", Font.PLAIN, 18));
		btnDntr.setBackground(new Color(0, 139, 139));
		btnDntr.setForeground(new Color(0, 0, 0));
		btnDntr.setBounds(324, 188, 136, 37);
		contentPane.add(btnDntr);
		
		JLabel lblBurayaYaznz = new JLabel("Buraya yazınız");
		lblBurayaYaznz.setBounds(37, 67, 423, 13);
		contentPane.add(lblBurayaYaznz);
		
		JLabel lblktBuradaOlacak = new JLabel("Çıktı burada olacak");
		lblktBuradaOlacak.setBounds(37, 275, 423, 13);
		contentPane.add(lblktBuradaOlacak);
		
		JLabel lblMetinstil = new JLabel("Metinstil");
		lblMetinstil.setForeground(new Color(102, 205, 170));
		lblMetinstil.setFont(new Font("Lemon", Font.PLAIN, 39));
		lblMetinstil.setHorizontalAlignment(SwingConstants.CENTER);
		lblMetinstil.setBounds(37, 10, 423, 46);
		contentPane.add(lblMetinstil);
		
		
	
	}
}
