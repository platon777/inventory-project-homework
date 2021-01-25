package ClasseJFrame;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Result;

import ClasseJava.BaseDeDonnees;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_Client;
	private JTextField rechercher;
	private ResultSet rs;
	private BaseDeDonnees db;
	private DefaultTableModel model = new DefaultTableModel();
	
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
					Client frame = new Client();
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
	public Client() {
		setMaximumSize(new Dimension(1000, 700));
		setMinimumSize(new Dimension(1000, 700));
		setResizable(false);
		db = new BaseDeDonnees ();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 503);
		contentPane = new JPanel();
		contentPane.setBackground(couleur_back);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		model.addColumn("Id");
		model.addColumn("Nom");
		model.addColumn("Prénom");
		model.addColumn("Téléphone");
		model.addColumn("Adresse");
		model.addColumn("Email");
		
		JScrollPane tableClient = new JScrollPane();
		tableClient.setFocusable(false);
		tableClient.setFont(new Font("Dialog", Font.BOLD, 12));
		tableClient.setBackground(Color.WHITE);
		tableClient.setForeground(couleur1);
		tableClient.setBounds(100, 192, 800, 343);
		contentPane.add(tableClient);
		
		table_Client = new JTable();
		
		table_Client.getTableHeader().setOpaque(false);
		table_Client.getTableHeader().setBackground(couleur2);
		table_Client.getTableHeader().setForeground(Color.white);
		table_Client.getTableHeader().setFont(new Font("Noto Sans CJK JP", Font.BOLD, 15) );
		
		
		table_Client.setForeground(new Color(51, 51, 51));
		table_Client.setShowHorizontalLines(false);
		table_Client.setFocusable(false);
		table_Client.setShowVerticalLines(false);
		table_Client.setRowHeight(30);
		table_Client.setSelectionForeground(Color.WHITE);
		table_Client.setSelectionBackground(Color.decode("#7FB3D5"));
		table_Client.setShowGrid(false);
		table_Client.setFont(new Font("Dialog", Font.BOLD, 12));
		table_Client.setIntercellSpacing(new Dimension(0, 0));
		table_Client.setBackground(Color.WHITE);
		table_Client.setGridColor(Color.WHITE);
		/*table_Client.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nom.setText(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 1)));
				prenom.setText(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 2)));
				tel.setText(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 3)));
				adresse.setText(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 4)));
				email.setText(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 5)));
			}
		});*/
		table_Client.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		tableClient.setViewportView(table_Client);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setFocusable(false);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setBackground(Color.decode("#EAECEE"));
				btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 22));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBackground(couleur_back);
				btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
			}
		});
		btnNewButton.setBorderPainted(false);
		btnNewButton.setSelected(true);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/add6.png")));
		btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
		btnNewButton.setBackground(couleur_back);
		btnNewButton.setForeground(couleur2);
		/*btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nom.getText().equals("") || prenom.getText().equals("") || tel.getText().equals("") || adresse.getText().equals("") || email.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "information incomplete");
				} else {
					String [] nomColonne = {"nom_client", "prenom_client", "tel_client", "adresse_client", "email_client"};
					String [] contenu = {nom.getText(), prenom.getText(), tel.getText(), adresse.getText(), email.getText()};
					db.insert("client", nomColonne, contenu);
					actualiser();
				}
			}
		});*/
		btnNewButton.setBounds(76, 577, 154, 42);
		contentPane.add(btnNewButton);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setFocusable(false);
		btnModifier.setBorderPainted(false);
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnModifier.setBackground(Color.decode("#FDEBD0"));
				btnModifier.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 22));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnModifier.setBackground(couleur_back);
				btnModifier.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
			}
		});
	
		
		btnModifier.setSelected(true);
		btnModifier.setIcon(new ImageIcon(this.getClass().getResource("/modify2.png")));
		btnModifier.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
		btnModifier.setBackground(couleur_back);
		btnModifier.setForeground(orange);
		//btnModifier.setBorder(BorderFactory.createLineBorder(Color.blue));
		/*btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nom.getText().equals("") || prenom.getText().equals("") || tel.getText().equals("") || adresse.getText().equals("") || email.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "information incomplete");
				} else {
					String [] nomColonne = {"nom_client", "prenom_client", "tel_client", "adresse_client", "email_client"};
					String [] contenu = {nom.getText(), prenom.getText(), tel.getText(), adresse.getText(), email.getText()};
					int i = Integer.parseInt(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 0)));
					db.update("client", nomColonne, contenu, "idClient = '" +i+ "'");
					actualiser();
				}
			}
		});*/
		btnModifier.setBounds(268, 577, 167, 42);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setFocusable(false);
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSupprimer.setBackground(Color.decode("#FDF2E9"));
				btnSupprimer.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 22));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSupprimer.setBackground(couleur_back);
				btnSupprimer.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
			}
		});
		btnSupprimer.setIcon(new ImageIcon(this.getClass().getResource("/delete3.png")));
		btnSupprimer.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
		btnSupprimer.setBorderPainted(false);
		btnSupprimer.setBackground(couleur_back);
		btnSupprimer.setForeground(rouge);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.parseInt(String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 0)));
				if (JOptionPane.showConfirmDialog(null, "Confirmer", "Attention", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					db.delete("client", "idClient = " +i);	
				}
				else {
					return;
				}
				actualiser();
			}
		});
		btnSupprimer.setBounds(470, 579, 194, 39);
		contentPane.add(btnSupprimer);
		
		/*JButton btnActualiser = new JButton("Actualiser");
		//btnActualiser.setIcon(new ImageIcon(this.getClass().getResource("/logo2.png")));
		btnActualiser.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 16));
		btnActualiser.setBorderPainted(false);
		btnActualiser.setBackground(couleur2);
		btnActualiser.setForeground(Color.WHITE);
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualiser();
			}
		});
		btnActualiser.setBounds(500, 510, 125, 35);
		contentPane.add(btnActualiser);*/
		
		JComboBox trierPar = new JComboBox();
		trierPar.setFocusable(false);
		trierPar.setBackground(Color.WHITE);
		trierPar.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nom", "Pr\u00E9nom", "T\u00E9l\u00E9phone", "Adresse\t", "Email"}));
		trierPar.setBounds(713, 124, 140, 23);
		contentPane.add(trierPar);
		
		JComboBox rechercherPar = new JComboBox();
		rechercherPar.setFocusable(false);
		rechercherPar.setBackground(Color.WHITE);
		rechercherPar.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nom", "Pr\u00E9nom", "T\u00E9l\u00E9phone", "Adresse\t", "Email"}));
		rechercherPar.setBounds(100, 122, 146, 26);
		contentPane.add(rechercherPar);
		
		rechercher = new JTextField();
		rechercher.setFocusable(false);
		rechercher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rechercher.setText("");
			}
		});
		rechercher.setBounds(271, 124, 268, 23);
		rechercher.setText("Rechercher par");
		rechercher.setForeground(Color.decode("#D0D3D4"));
		contentPane.add(rechercher);
		rechercher.setColumns(10);
		
		/*JButton btnNewButton_2 = new JButton("Retour");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setVisible(false);
				dispose();
				Menu frame = new Menu();
				frame.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(594, 593, 140, 23);
		contentPane.add(btnNewButton_2);
		*/
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(8, 51, 70));
		panel.setBounds(0, -26, 1010, 108);
		contentPane.add(panel);
		
		JLabel lblGestionArticle = new JLabel("Gestion  Client");
		lblGestionArticle.setForeground(Color.WHITE);
		lblGestionArticle.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 40));
		lblGestionArticle.setBounds(365, 42, 305, 48);
		panel.add(lblGestionArticle);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 24, 158, 95);
		panel.add(lblNewLabel_1);
		//lblNewLabel_1.setIcon(new ImageIcon("/home/valthebest/Pictures/logo2.png"));
		lblNewLabel_1.setIcon(new ImageIcon(this.getClass().getResource("/logo2.png")));
		
		JButton btnRecherche = new JButton("Recherche_bouton");
		btnRecherche.setFocusable(false);
		btnRecherche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rechercher.setForeground(Color.decode("#D0D3D4"));
				rechercher.setText("Rechercher par");
				rechercher.setForeground(Color.decode("#D0D3D4"));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				//btnRecherche.setBackground(Color.decode("#EAECEE"));
				btnRecherche.setIcon(new ImageIcon(this.getClass().getResource("/search3.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRecherche.setIcon(new ImageIcon(this.getClass().getResource("/search4.png")));
			}
		});
		btnRecherche.setIcon(new ImageIcon(this.getClass().getResource("/search4.png")));
		btnRecherche.setForeground(Color.WHITE);
		btnRecherche.setBorderPainted(false);
		btnRecherche.setBackground(new Color(242, 243, 244));
		btnRecherche.setBounds(540, 118, 67, 34);
		contentPane.add(btnRecherche);
		
		JLabel lblTrierPar = new JLabel("Trier par");
		lblTrierPar.setFocusable(false);
		lblTrierPar.setForeground(new Color(23, 32, 42));
		lblTrierPar.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 16));
		lblTrierPar.setBounds(625, 124, 70, 23);
		contentPane.add(lblTrierPar);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/sort1.png")));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/sort2.png")));				
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/sort1.png")));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(new Color(242, 243, 244));
		btnNewButton_1.setBounds(865, 124, 35, 23);
		contentPane.add(btnNewButton_1);
		
		table();
		
	}
	
	public void table() {
		model.setRowCount(0);
		rs = db.selectAll("client");
		try {
			while (rs.next()) {
				model.addRow(new Object [] { rs.getString("idClient"),
						rs.getString("nom_client"),
						rs.getString("prenom_client"),
						rs.getString("tel_client"),
						rs.getString("adresse_client"),
						rs.getString("email_client")
				});
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		table_Client.setModel(model);
	}
	
	public void actualiser () {
		table();
		/*nom.setText("");
		prenom.setText("");
		tel.setText("");
		adresse.setText("");
		email.setText("");*/
	}
}
