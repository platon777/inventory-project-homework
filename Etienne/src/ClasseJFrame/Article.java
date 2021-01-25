package ClasseJFrame;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClasseJava.BaseDeDonnees;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JEditorPane;
//import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
//import java.awt.Component;
//import ajouter.java;

public class Article extends JFrame {

	private JPanel contentPane;
	private JTable tableArticle;
	private JTextField recherche;
	
	private ResultSet rs;
	private BaseDeDonnees db;
	private DefaultTableModel model = new DefaultTableModel();
	private JTextField rechercherId;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Article frame = new Article();
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
	public Article() {
		setVisible(true);
		setMinimumSize(new Dimension(1000, 700));
		setMaximumSize(new Dimension(1000, 700));
		setResizable(false);
		db = new BaseDeDonnees ();
		model.addColumn("Id");
		model.addColumn("Libéllé");
		model.addColumn("Prix unitaire");
		model.addColumn("Date de création");
		model.addColumn("Quantité en stock");
		model.addColumn("Famille");
		
		//couleur 
		Color couleur_back =Color.decode("#F2F3F4");
		Color couleur1 = Color.decode("#17202A");
		Color couleur2 = Color.decode("#1C98CF");
		Color entete = Color.decode("#083346");
		Color vert =Color.decode("#27AE60");
		Color rouge =Color.decode("#D32222");
		Color orange =Color.decode("#E67E22");
		Color mauve =Color.decode("#6C3483");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 503);
		contentPane = new JPanel();
		contentPane.setFocusable(false);
		//contentPane.setBackground(Color.BLUE);
		
		contentPane.setBackground(couleur_back);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFocusable(false);
		scrollPane.setBounds(100, 192, 800, 340);
		contentPane.add(scrollPane);
		
		tableArticle = new JTable();
		tableArticle.setUpdateSelectionOnSort(false);
		tableArticle.setFocusable(false);
		tableArticle.setFont(new Font("Dialog", Font.BOLD, 12));
		
		tableArticle.getTableHeader().setOpaque(false);
		tableArticle.getTableHeader().setBackground(couleur2);
		tableArticle.getTableHeader().setForeground(Color.white);
		tableArticle.getTableHeader().setFont(new Font("Noto Sans CJK JP", Font.BOLD, 15) );
		
		
		tableArticle.setSelectionForeground(Color.WHITE);
		tableArticle.setSelectionBackground(Color.decode("#7FB3D5"));
		tableArticle.setRowHeight(30);
		tableArticle.setShowGrid(false);
		/*tableArticle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				libelle.setText(String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1)));
				prxUnit.setText(String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2)));
				//quantite.setValue(String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4)));
			}
		});*/
		scrollPane.setViewportView(tableArticle);
		
		JComboBox trierPar = new JComboBox();
		trierPar.setFocusable(false);
		trierPar.setBackground(Color.WHITE);
		trierPar.setModel(new DefaultComboBoxModel(new String[] {"Id", "Libell\u00E9", "Prix", "Date", "Quantit\u00E9", "Famille "}));
		trierPar.setBounds(713, 124, 140, 23);
		contentPane.add(trierPar);
		
		JComboBox rechercherPar = new JComboBox();
		rechercherPar.setFocusable(false);
		rechercherPar.setBackground(Color.WHITE);
		rechercherPar.setAutoscrolls(true);
		rechercherPar.setModel(new DefaultComboBoxModel(new String[] {"Id", "Libell\u00E9", "Prix", "Date", "Quantit\u00E9", "Famille "}));
		rechercherPar.setBounds(100, 122, 146, 26);
		contentPane.add(rechercherPar);
		
		JButton btnRecherche = new JButton("Recherche_bouton");
		btnRecherche.setFocusable(false);
		btnRecherche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				recherche.setForeground(Color.decode("#D0D3D4"));
				recherche.setText("Rechercher par");
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
		btnRecherche.setForeground(Color.WHITE);
		btnRecherche.setBorderPainted(false);
		btnRecherche.setBackground(couleur_back);
		btnRecherche.setIcon(new ImageIcon(this.getClass().getResource("/search4.png")));
		
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recherche.setText("Rechercher par");
				recherche.setForeground(Color.decode("#D0D3D4"));
				
				
				if (recherche.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "SVP entrer qq chose");
				} else {
					model.setRowCount(0);
					if (rechercherPar.getSelectedItem().toString().equals("Id")) {
						int etat = Integer.parseInt(recherche.getText());
						String query = "SELECT * FROM article, famille_article WHERE article.idFamille_Article = famille_article.idFamille_Article AND id_Article LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);	
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getInt("id_Article"),
										rs.getString("libelle_article"),
										rs.getDouble("prix_unitaire"),
										rs.getString("date_creation"),
										rs.getInt("qte_en_stock"),
										rs.getString("nom_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						tableArticle.setModel(model);
					} else if (rechercherPar.getSelectedItem().toString().equals("Libell�")) {
						String etat = recherche.getText();
						String query = "SELECT * FROM article, famille_article WHERE article.idFamille_Article = famille_article.idFamille_Article AND libelle_article LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);		
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getInt("id_Article"),
										rs.getString("libelle_article"),
										rs.getDouble("prix_unitaire"),
										rs.getString("date_creation"),
										rs.getInt("qte_en_stock"),
										rs.getString("nom_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						tableArticle.setModel(model);
					} else if (rechercherPar.getSelectedItem().toString().equals("Prix")) {
						String etat = recherche.getText();
						String query = "SELECT * FROM article, famille_article WHERE article.idFamille_Article = famille_article.idFamille_Article AND prix_unitaire LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);	
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getInt("id_Article"),
										rs.getString("libelle_article"),
										rs.getDouble("prix_unitaire"),
										rs.getString("date_creation"),
										rs.getInt("qte_en_stock"),
										rs.getString("nom_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						tableArticle.setModel(model);
					} else if (rechercherPar.getSelectedItem().toString().equals("Date")) {
						String etat = recherche.getText();
						String query = "SELECT * FROM article, famille_article WHERE article.idFamille_Article = famille_article.idFamille_Article AND date_creation LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);			
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getInt("id_Article"),
										rs.getString("libelle_article"),
										rs.getDouble("prix_unitaire"),
										rs.getString("date_creation"),
										rs.getInt("qte_en_stock"),
										rs.getString("nom_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						tableArticle.setModel(model);
					} else if (rechercherPar.getSelectedItem().toString().equals("Quantit�")) {
						int etat = Integer.parseInt(recherche.getText());
						String query = "SELECT * FROM article, famille_article WHERE article.idFamille_Article = famille_article.idFamille_Article AND qte_en_stock LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);			
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getInt("id_Article"),
										rs.getString("libelle_article"),
										rs.getDouble("prix_unitaire"),
										rs.getString("date_creation"),
										rs.getInt("qte_en_stock"),
										rs.getString("nom_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						tableArticle.setModel(model);
					} else {
						String etat = recherche.getText();
						String query = "SELECT * FROM article, famille_article WHERE article.idFamille_Article = famille_article.idFamille_Article AND nom_famille LIKE '%" +etat+ "%'";
						rs = db.executeQuery(query);		
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getInt("id_Article"),
										rs.getString("libelle_article"),
										rs.getDouble("prix_unitaire"),
										rs.getString("date_creation"),
										rs.getInt("qte_en_stock"),
										rs.getString("nom_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						tableArticle.setModel(model);
					}
				}
			}
		});
		btnRecherche.setBounds(540, 118, 67, 34);
		contentPane.add(btnRecherche);
		
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
		btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/sort1.png")));
		btnNewButton_1.setBorderPainted(false);
		//btnNewButton_1.setBackground(Color.decode("#F8F9F9"));
		btnNewButton_1.setBackground(couleur_back);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String etat = "";
				if(trierPar.getSelectedItem().toString().equals("Id")) {
					etat = "id_Article";
				} else if(trierPar.getSelectedItem().toString().equals("Libell�")){
					etat = "libelle_article";
				}
				else if(trierPar.getSelectedItem().toString().equals("Prix")){
					etat = "prix_unitaire";
				}
				else if(trierPar.getSelectedItem().toString().equals("Date")){
					etat = "date_creation";
				}
				else if(trierPar.getSelectedItem().toString().equals("Quantit�")){
					etat = "qte_en_stock";
				}
				else {
					etat = "nom_famille";
				}
				model.setRowCount(0);
				String query = "SELECT * FROM article, famille_article WHERE article.idFamille_Article = famille_article.idFamille_Article order by " +etat;
				rs = db.executeQuery(query);
				try {
					while (rs.next()) {
						model.addRow(new Object [] { rs.getInt("id_Article"),
								rs.getString("libelle_article"),
								rs.getDouble("prix_unitaire"),
								rs.getString("date_creation"),
								rs.getInt("qte_en_stock"),
								rs.getString("nom_famille")
						});
					}
				} catch(Exception e1) {
					System.err.println(e1.getMessage());
				}
				tableArticle.setModel(model);
			}
		});
		btnNewButton_1.setBounds(865, 124, 35, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.setFocusable(false);
		btnActualiser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnActualiser.setBackground(Color.decode("#EAECEE"));
				btnActualiser.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 22));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnActualiser.setBackground(couleur_back);
				btnActualiser.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
			}
		});
		btnActualiser.setIcon(new ImageIcon("/home/valthebest/eclipse-workspace/Etienne/images/refresh1.png"));
		btnActualiser.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
		btnActualiser.setForeground(vert);
		btnActualiser.setBorderPainted(false);
		btnActualiser.setBackground(couleur_back);
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualiser();
			}
		});
		btnActualiser.setBounds(740, 579, 194, 37);
		contentPane.add(btnActualiser);
		String query = "SELECT * FROM famille_article order by nom_famille";
		rs = db.executeQuery(query);
		try {
			while (rs.next()) {
				String contenu = rs.getString("nom_famille");
				//familleNom.addItem(contenu);
				//db.closeConnection();
				}
			} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(entete);
		panel.setBounds(0, -14, 1000, 102);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 12, 150, 90);
		lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("/logo2.png")));
		panel.add(lblNewLabel);
		
		JLabel lblGestionArticle = new JLabel("PRODUIT");
		lblGestionArticle.setFocusable(false);
		lblGestionArticle.setForeground(Color.WHITE);
		lblGestionArticle.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 40));
		lblGestionArticle.setBounds(484, 30, 182, 48);
		panel.add(lblGestionArticle);
		
		JLabel lblTrierPar = new JLabel("Trier par");
		lblTrierPar.setFocusable(false);
		lblTrierPar.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 16));
		lblTrierPar.setForeground(couleur1);
		lblTrierPar.setBounds(625, 124, 70, 23);
		contentPane.add(lblTrierPar);
		
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
		btnNewButton.setSelected(true);
		btnNewButton.setForeground(new Color(28, 152, 207));
		btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(couleur_back);
		btnNewButton.setBounds(78, 576, 154, 42);
		contentPane.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/add6.png")));
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setFocusable(false);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
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
		btnModifier.setForeground(new Color(230, 126, 34));
		btnModifier.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
		btnModifier.setBorderPainted(false);
		btnModifier.setBackground(new Color(242, 243, 244));
		btnModifier.setBounds(271, 576, 167, 42);
		contentPane.add(btnModifier);
		btnModifier.setIcon(new ImageIcon(this.getClass().getResource("/modify2.png")));
		
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
		btnSupprimer.setForeground(new Color(211, 34, 34));
		btnSupprimer.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
		btnSupprimer.setBorderPainted(false);
		btnSupprimer.setBackground(new Color(242, 243, 244));
		btnSupprimer.setBounds(501, 578, 194, 39);
		btnSupprimer.setIcon(new ImageIcon(this.getClass().getResource("/delete3.png")));
		contentPane.add(btnSupprimer);
		
		/*JLabel lblRechercherPar = new JLabel("Rechercher par");
		lblRechercherPar.setForeground(Color.decode("#B3B6B7"));
		lblRechercherPar.setBounds(271, 128, 119, 15);
		contentPane.add(lblRechercherPar);*/
		
		recherche = new JTextField();
		recherche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				recherche.setText("");
				recherche.setForeground(couleur1);
			}
			
		});
		recherche.setBounds(271, 124, 268, 23);
		
		contentPane.add(recherche);
		recherche.setForeground(Color.decode("#D0D3D4"));
		recherche.setFont(new Font("Dialog", Font.BOLD, 12));
		recherche.setColumns(10);
		actualiser();
		
	}
	
	public void actualiser() {
		model.setRowCount(0);
		String query = "SELECT * FROM article, famille_article WHERE article.idFamille_Article = famille_article.idFamille_Article";
		rs = db.executeQuery(query);
		try {
			while (rs.next()) {
				model.addRow(new Object [] { rs.getInt("id_Article"),
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
		tableArticle.setModel(model);
	}
}
