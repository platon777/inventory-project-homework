package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClasseJava.BaseDeDonnees;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JDesktopPane;
import java.awt.Dimension;
import java.awt.Font;

public class Commande extends JFrame {

	private JPanel contentPane;
	private JTable tableClient;
	private JTable tableArticle;
	private JLabel client;
	private JLabel article;
	private JSpinner quantite;
	
	private ResultSet rs;
	private BaseDeDonnees db;
	private DefaultTableModel model1 = new DefaultTableModel();
	private DefaultTableModel model2 = new DefaultTableModel();
	private DefaultTableModel model3 = new DefaultTableModel();
	private String idClient = "";
	private String idArticle = "";
	private ArrayList listArticle = new ArrayList<String>();
	private ArrayList listQuantite = new ArrayList<String>();
	private int compter = 0;
	public static String idCom = "";
	private JTable tableCommande;
	
	//couleur 
	Color couleur_back =Color.decode("#F2F3F4");
	Color couleur1 = Color.decode("#17202A");
	Color couleur2 = Color.decode("#1C98CF");
	Color entete = Color.decode("#083346");
	Color vert =Color.decode("#27AE60");
	Color rouge =Color.decode("#D32222");
	Color orange =Color.decode("#E67E22");
	Color mauve =Color.decode("#6C3483");
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Commande frame = new Commande();
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
	public Commande() {
		setMinimumSize(new Dimension(1000, 700));
		setMaximumSize(new Dimension(1000, 700));
		setResizable(false);
		db = new BaseDeDonnees ();
		
	
		
		model1.addColumn("Id");
		model1.addColumn("Nom");
		model1.addColumn("Pr�nom");
		model1.addColumn("T�l�phone");
		model1.addColumn("Adresse");
		model1.addColumn("Email");
		
		model2.addColumn("Id");
		model2.addColumn("Libell�");
		model2.addColumn("Prix unitaire");
		model2.addColumn("Date de cr�ation");
		model2.addColumn("Quantit� en stock");
		model2.addColumn("Famille");	
		
		model3.addColumn("Libell�");
		model3.addColumn("Prix unitaire");
		model3.addColumn("Quantit�");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 614);
		contentPane = new JPanel();
		contentPane.setBackground(couleur_back);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 202, 527, 157);
		contentPane.add(scrollPane);
		
		tableClient = new JTable();
		tableClient.getTableHeader().setOpaque(false);
		tableClient.getTableHeader().setBackground(couleur2);
		tableClient.getTableHeader().setForeground(Color.white);
		tableClient.getTableHeader().setFont(new Font("Noto Sans CJK JP", Font.BOLD, 14) );
		
		
		tableClient.setForeground(new Color(51, 51, 51));
		
		tableClient.setFillsViewportHeight(true);
		tableClient.setSelectionBackground(Color.decode("#7FB3D5"));
		tableClient.setSelectionForeground(Color.WHITE);
		tableClient.setRowHeight(30);
		tableClient.setShowGrid(false);
		tableClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idClient = tableClient.getValueAt(tableClient.getSelectedRow(), 0).toString();
				client.setText(String.valueOf(tableClient.getValueAt(tableClient.getSelectedRow(), 2))+ " " +String.valueOf(tableClient.getValueAt(tableClient.getSelectedRow(), 1)));
			}
		});
		scrollPane.setViewportView(tableClient);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 471, 527, 180);
		contentPane.add(scrollPane_1);
		
		tableArticle = new JTable();

		tableArticle.getTableHeader().setOpaque(false);
		tableArticle.getTableHeader().setBackground(couleur2);
		tableArticle.getTableHeader().setForeground(Color.white);
		tableArticle.getTableHeader().setFont(new Font("Noto Sans CJK JP", Font.BOLD, 14) );
		
		
		tableArticle.setSelectionForeground(Color.WHITE);
		tableArticle.setSelectionBackground(Color.decode("#7FB3D5"));
		
		tableArticle.setFillsViewportHeight(true);
		tableArticle.setShowGrid(false);
		tableArticle.setSelectionForeground(Color.WHITE);
		
		tableArticle.setRowHeight(30);
		tableArticle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idArticle = tableArticle.getValueAt(tableArticle.getSelectedRow(), 0).toString();
				article.setText(String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1)));
				//prix.setText(String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2)));
				//quantite.setModel(new SpinnerNumberModel(1, 0, Integer.parseInt(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5).toString()), 1);
			}
		});
		scrollPane_1.setViewportView(tableArticle);
		
		JLabel lblNewLabel = new JLabel("Client");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(746, 193, 96, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblArticle = new JLabel("Article");
		lblArticle.setFont(new Font("Dialog", Font.BOLD, 16));
		lblArticle.setHorizontalAlignment(SwingConstants.LEFT);
		lblArticle.setBounds(746, 251, 96, 20);
		contentPane.add(lblArticle);
		
		quantite = new JSpinner();
		quantite.setBackground(Color.WHITE);
		quantite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//prix.setText(quantite.getValue().toString());
			}
		});
		quantite.setBorder(new MatteBorder(1, 1, 1, 1, (Color) couleur2));
		quantite.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		quantite.setBounds(828, 311, 122, 20);
		contentPane.add(quantite);
		
		JLabel lblNature = new JLabel("Nature");
		lblNature.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNature.setHorizontalAlignment(SwingConstants.LEFT);
		lblNature.setBounds(746, 353, 96, 20);
		contentPane.add(lblNature);
		
		JComboBox nature = new JComboBox();
		nature.setBorder(new LineBorder(couleur2));
		nature.setBackground(Color.WHITE);
		nature.setModel(new DefaultComboBoxModel(new String[] {"Proforma", "Facture"}));
		nature.setBounds(828, 353, 122, 22);
		contentPane.add(nature);
		
		client = new JLabel("");
		client.setOpaque(true);
		client.setBackground(Color.WHITE);
		client.setBorder(new MatteBorder(1, 1, 1, 1, (Color) couleur2));
		client.setHorizontalAlignment(SwingConstants.LEFT);
		client.setBounds(746, 215, 242, 20);
		contentPane.add(client);
		
		article = new JLabel("");
		article.setOpaque(true);
		article.setBackground(Color.WHITE);
		article.setBorder(new LineBorder(couleur2));
		article.setHorizontalAlignment(SwingConstants.LEFT);
		article.setBounds(746, 270, 242, 20);
		contentPane.add(article);
		
		/*JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 503, 698, 102);
		contentPane.add(scrollPane_2);
		
		tableCommande = new JTable();
		tableCommande.setRowHeight(30);
		tableCommande.setShowGrid(false);
		scrollPane_2.setColumnHeaderView(tableCommande);
		*/
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(8, 51, 70));
		panel.setBounds(0, -20, 1000, 102);
		contentPane.add(panel);
		
		JLabel lblCommande = new JLabel("Commande");
		lblCommande.setForeground(Color.WHITE);
		lblCommande.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 40));
		lblCommande.setBounds(402, 31, 305, 48);
		panel.add(lblCommande);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(this.getClass().getResource("/logo2.png")));
		lblNewLabel_1.setBounds(0, 12, 166, 100);
		panel.add(lblNewLabel_1);
		
		JLabel lblQuantite = new JLabel("Quantite");
		lblQuantite.setFont(new Font("Dialog", Font.BOLD, 16));
		lblQuantite.setBounds(746, 312, 96, 15);
		contentPane.add(lblQuantite);
		
		JComboBox rechercherPar = new JComboBox();
		rechercherPar.setFocusable(false);
		rechercherPar.setBackground(Color.WHITE);
		rechercherPar.setBounds(10, 145, 152, 26);
		contentPane.add(rechercherPar);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(couleur2));
		textField.setForeground(new Color(208, 211, 212));
		textField.setColumns(10);
		textField.setBounds(174, 146, 160, 26);
		contentPane.add(textField);
		
		JButton btnRecherche = new JButton("Recherche_bouton");
		btnRecherche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRecherche.setIcon(new ImageIcon(this.getClass().getResource("/search3.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRecherche.setIcon(new ImageIcon(this.getClass().getResource("/search4.png")));
			}
		});
		btnRecherche.setForeground(Color.WHITE);
		btnRecherche.setIcon(new ImageIcon(this.getClass().getResource("/search4.png")));
		btnRecherche.setFocusable(false);
		btnRecherche.setBorderPainted(false);
		btnRecherche.setBackground(new Color(242, 243, 244));
		btnRecherche.setBounds(337, 138, 44, 40);
		contentPane.add(btnRecherche);
		
		JButton btnNewButton = new JButton("AJOUTER CLIENT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setActionCommand("Ajouter Client");
		btnNewButton.setFocusable(false);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				//btnNewButton.setBackground(Color.decode("#EAECEE"));
				btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 16));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBackground(couleur_back);
				btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 14));
			}
		});
		
		
		btnNewButton.setSelected(true);
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/add2.png")));
		btnNewButton.setForeground(new Color(28, 152, 207));
		btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 14));
		btnNewButton.setFocusable(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(242, 243, 244));
		btnNewButton.setBounds(370, 135, 203, 46);
		contentPane.add(btnNewButton);
		
		JComboBox trierPar = new JComboBox();
		trierPar.setFocusable(false);
		trierPar.setBackground(Color.WHITE);
		trierPar.setBounds(10, 418, 218, 23);
		contentPane.add(trierPar);
		
		textField_1 = new JTextField();
		textField_1.setBorder(new LineBorder(couleur2));
		textField_1.setForeground(new Color(208, 211, 212));
		textField_1.setFont(new Font("Dialog", Font.BOLD, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(278, 418, 218, 23);
		contentPane.add(textField_1);
		
		JButton btnRecherche_1 = new JButton("Recherche_bouton");
		btnRecherche_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRecherche_1.setIcon(new ImageIcon(this.getClass().getResource("/search3.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRecherche_1.setIcon(new ImageIcon(this.getClass().getResource("/search4.png")));
			}
		});
		btnRecherche_1.setIcon(new ImageIcon(this.getClass().getResource("/search4.png")));
		btnRecherche_1.setForeground(Color.WHITE);
		btnRecherche_1.setFocusable(false);
		btnRecherche_1.setBorderPainted(false);
		btnRecherche_1.setBackground(new Color(242, 243, 244));
		btnRecherche_1.setBounds(498, 412, 67, 34);
		contentPane.add(btnRecherche_1);
		
		JLabel lblTrierPar = new JLabel("Trier par ");
		lblTrierPar.setForeground(new Color(23, 32, 42));
		lblTrierPar.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 16));
		lblTrierPar.setFocusable(false);
		lblTrierPar.setBounds(10, 125, 96, 23);
		contentPane.add(lblTrierPar);
		
		JLabel lblTrierPar_1 = new JLabel("Trier par ");
		lblTrierPar_1.setForeground(new Color(23, 32, 42));
		lblTrierPar_1.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 16));
		lblTrierPar_1.setFocusable(false);
		lblTrierPar_1.setBounds(10, 387, 96, 23);
		contentPane.add(lblTrierPar_1);
		
		JLabel lblArticle_1 = new JLabel("ARTICLE");
		lblArticle_1.setForeground(new Color(23, 32, 42));
		lblArticle_1.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 25));
		lblArticle_1.setFocusable(false);
		lblArticle_1.setBounds(263, 383, 110, 23);
		contentPane.add(lblArticle_1);
		
		JLabel lblArticle_1_1 = new JLabel("CLIENT");
		lblArticle_1_1.setForeground(new Color(23, 32, 42));
		lblArticle_1_1.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 25));
		lblArticle_1_1.setFocusable(false);
		lblArticle_1_1.setBounds(253, 109, 88, 23);
		contentPane.add(lblArticle_1_1);
		
		JButton btnNewButton_1 = new JButton("COMMANDER");
		btnNewButton_1.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 14));
		btnNewButton_1.setForeground(couleur1);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				//btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/order2.png")));
				btnNewButton_1.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/order1.png")));
				btnNewButton_1.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 18));
			}
		});
		btnNewButton_1.setBackground(couleur_back);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/order1.png")));
		btnNewButton_1.setBounds(555, 577, 242, 74);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(576, 472, 412, 102);
		contentPane.add(scrollPane_1_1);
		
		table = new JTable();
		table.setRowHeight(18);
		table.setShowGrid(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"id Cmde", "Libelle", "prix /unite", "Quantite", "Montant"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(couleur2);
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(new Font("Noto Sans CJK JP", Font.BOLD, 12) );
		
		
		table.setSelectionForeground(Color.WHITE);
		table.setSelectionBackground(Color.decode("#7FB3D5"));
		
		scrollPane_1_1.setViewportView(table);
		
		JButton btnInsererCommande = new JButton("Inserer Commande");
		btnInsererCommande.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnInsererCommande.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 18));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnInsererCommande.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 16));
			}
		});
		btnInsererCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnInsererCommande.setSelected(true);
		btnInsererCommande.setIcon(new ImageIcon(this.getClass().getResource("/insert1.png")));
		btnInsererCommande.setForeground(new Color(28, 152, 207));
		btnInsererCommande.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 16));
		btnInsererCommande.setFocusable(false);
		btnInsererCommande.setBorderPainted(false);
		btnInsererCommande.setBackground(new Color(242, 243, 244));
		btnInsererCommande.setActionCommand("Ajouter Client");
		btnInsererCommande.setBounds(712, 400, 242, 40);
		contentPane.add(btnInsererCommande);
		
		JLabel lblTotal = new JLabel("TOTAL :");
		lblTotal.setOpaque(true);
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setBackground(entete);
		lblTotal.setBounds(815, 593, 173, 46);
		contentPane.add(lblTotal);
		
		tableClient();
		tableArticle();
		//tableCommande();
	}
	
	public void tableClient () {
		model1.setRowCount(0);
		rs = db.selectAll("client");
		try {
			while (rs.next()) {
				model1.addRow(new Object [] { rs.getString("idClient"),
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
		tableClient.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Id", "Nom", "Pr\u00E9nom", "T\u00E9l\u00E9phone", "Adresse"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
	}
	
	public void tableArticle () {
		model2.setRowCount(0);
		String query = "SELECT * FROM article, famille_article WHERE article.idFamille_Article = famille_article.idFamille_Article";
		rs = db.executeQuery(query);
		try {
			while (rs.next()) {
				model2.addRow(new Object [] { rs.getInt("id_Article"),
						rs.getString("libelle_article"),
						rs.getDouble("prix_unitaire"),
						rs.getString("date_creation"),
						rs.getInt("qte_en_stock"),
						rs.getString("nom_famille")
				});
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		tableArticle.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Id", "Lib\u00E9ll\u00E9", "Cat\u00E9gorie", "Prix/unit\u00E9", "Qt\u00E9 Stock"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Double.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
	}
}
