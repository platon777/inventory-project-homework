package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ajouter extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	//couleur 
	Color couleur_back =Color.decode("#F2F3F4");
	Color couleur1 = Color.decode("#17202A");
	Color couleur2 = Color.decode("#1C98CF");
	Color entete = Color.decode("#083346");
	Color vert =Color.decode("#27AE60");
	Color rouge =Color.decode("#D32222");
	Color orange =Color.decode("#E67E22");
	Color mauve =Color.decode("#6C3483");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajouter frame = new Ajouter();
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
	public Ajouter() {

				
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 550);
		contentPane = new JPanel();
		contentPane.setBackground(couleur_back);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(couleur2);
		panel.setBounds(0, -16, 425, 71);
		contentPane.add(panel);
		
		JComboBox familleNom = new JComboBox();
		familleNom.setBackground(Color.decode("#AED6F1"));
		familleNom.setBounds(160, 102, 221, 22);
		contentPane.add(familleNom);
		
		JLabel lblFamille = new JLabel("Categorie");
		lblFamille.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFamille.setHorizontalAlignment(SwingConstants.CENTER);
		lblFamille.setForeground(new Color(23, 32, 42));
		lblFamille.setBackground(Color.BLUE);
		lblFamille.setBounds(37, 101, 101, 22);
		contentPane.add(lblFamille);
		
		JLabel lblLibell = new JLabel("Libellé");
		lblLibell.setFont(new Font("Dialog", Font.BOLD, 16));
		lblLibell.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibell.setForeground(new Color(23, 32, 42));
		lblLibell.setBounds(31, 170, 82, 31);
		contentPane.add(lblLibell);
		
		textField = new JTextField();
		textField.setText("");
		textField.setColumns(10);
		textField.setBounds(160, 177, 221, 20);
		contentPane.add(textField);
		
		JLabel lblPrixUnitaire = new JLabel("Prix unitaire");
		lblPrixUnitaire.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPrixUnitaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrixUnitaire.setForeground(new Color(23, 32, 42));
		lblPrixUnitaire.setBounds(38, 234, 118, 35);
		contentPane.add(lblPrixUnitaire);
		
		textField_1 = new JTextField();
		textField_1.setText("");
		textField_1.setColumns(10);
		textField_1.setBounds(160, 243, 221, 20);
		contentPane.add(textField_1);
		
		JLabel lblQuantit = new JLabel("Quantité");
		lblQuantit.setFont(new Font("Dialog", Font.BOLD, 16));
		lblQuantit.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantit.setForeground(new Color(23, 32, 42));
		lblQuantit.setBounds(28, 294, 110, 39);
		contentPane.add(lblQuantit);
		
		JSpinner quantite = new JSpinner();
		quantite.setBounds(160, 305, 221, 20);
		contentPane.add(quantite);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAnnuler.setBackground(Color.decode("#EAECEE"));
				btnAnnuler.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAnnuler.setBackground(couleur_back);
				btnAnnuler.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 16));
			}
		});
		btnAnnuler.setFocusable(false);
		btnAnnuler.setForeground(orange);
		btnAnnuler.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 16));
		btnAnnuler.setBorderPainted(false);
		btnAnnuler.setBackground(couleur_back);
		btnAnnuler.setBounds(224, 393, 189, 42);
		btnAnnuler.setIcon(new ImageIcon(this.getClass().getResource("/cancel1.png")));
		contentPane.add(btnAnnuler);
		
		JButton btnNewButton = new JButton("Enregistrer");
		btnNewButton.setFocusable(false);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setBackground(Color.decode("#EAECEE"));
				btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBackground(couleur_back);
				btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 16));
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/save1.png")));
		btnNewButton.setSelected(true);
		btnNewButton.setForeground(vert);
		btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 16));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(couleur_back);
		btnNewButton.setBounds(12, 393, 189, 42);
		contentPane.add(btnNewButton);
	}
}
