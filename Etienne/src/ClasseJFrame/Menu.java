package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Menu extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public Menu() {
		setPreferredSize(new Dimension(1000, 700));
		setMinimumSize(new Dimension(1000, 700));
		setMaximumSize(new Dimension(1000, 700));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 477, 224);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		Color color1 = Color.decode("#1B4F72");
		JPanel panel = new JPanel();
		//panel.setBackground(Color.decode("#2E86C1"));
		panel.setBackground(color1);
		panel.setBounds(0, -15, 1000, 100);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblEdgardEtienneSa = new JLabel("Edgard Etienne SA");
		lblEdgardEtienneSa.setFont(new Font("Dialog", Font.BOLD, 24));
		lblEdgardEtienneSa.setForeground(Color.WHITE);
		lblEdgardEtienneSa.setBounds(386, 30, 267, 47);
		panel.add(lblEdgardEtienneSa);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(375, 67, 278, 3);
		panel.add(panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.decode("#2E86C1"));
		panel_1.setBounds(0, 84, 221, 589);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton Client = new JButton("Article");
		Client.setBorderPainted(false);
		Client.setFont(new Font("Dialog", Font.BOLD, 18));
		Client.setForeground(Color.WHITE);
		Client.setBackground(Color.decode("#2E86C1"));
		Client.setBounds(0, 134, 221, 32);
		panel_1.add(Client);
		
		JButton btnNewButton_1 = new JButton("Famille d'article");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 18));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.decode("#2E86C1"));
		btnNewButton_1.setBounds(0, 191, 221, 32);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Client");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 18));
		btnNewButton.setBackground(Color.decode("#2E86C1"));
		btnNewButton.setBounds(0, 251, 221, 32);
		panel_1.add(btnNewButton);
		
		JButton btnRapport = new JButton("Rapport");
		btnRapport.setBorderPainted(false);
		btnRapport.setFont(new Font("Dialog", Font.BOLD, 18));
		btnRapport.setForeground(Color.WHITE);
		btnRapport.setBackground(Color.decode("#2E86C1"));
		btnRapport.setBounds(0, 313, 221, 32);
		panel_1.add(btnRapport);
		
		JButton btnCommander = new JButton("Commander");
		btnCommander.setBounds(0, 386, 221, 32);
		panel_1.add(btnCommander);
		btnCommander.setBorderPainted(false);
		btnCommander.setForeground(Color.WHITE);
		btnCommander.setFont(new Font("Dialog", Font.BOLD, 18));
		btnCommander.setBackground(Color.decode("#2E86C1"));
		btnCommander.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Commande frame = new Commande();
				frame.setVisible(true);
				//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
		});
		btnRapport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Rapport frame = new Rapport();
				frame.setVisible(true);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Client frame = new Client();
				frame.setVisible(true);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FamilleArticle frame = new FamilleArticle();
				frame.setVisible(true);
			}
		});
		Client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Article frame = new Article();
				frame.setVisible(true);
			}
		});
		
	}
}
