package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClasseJava.BaseDeDonnees;

public class BonLivraison extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model = new DefaultTableModel();
	private ResultSet rs;
	private BaseDeDonnees db;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BonLivraison frame = new BonLivraison();
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
	public BonLivraison() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 690, 324);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		db = new BaseDeDonnees();
		model.addColumn("Id commande");
		model.addColumn("Nom client");
		model.addColumn("Prénom client");
		model.addColumn("Adresse client");
		model.addColumn("Date de livraison");
		model.addColumn("Libellé article");
		model.addColumn("Prix unitaire");
		model.addColumn("Quantite");
		model.addColumn("Montant");
		table();
	}

	public void table() {
		model.setRowCount(0);
		rs = db.selectAll("v_bon_livraison", "idCommande = '" +Commande.idCom+"'");
		try {
			while (rs.next()) {
				model.addRow(new Object [] { rs.getInt("idCommande"),
						rs.getString("nom_client"),
						rs.getString("prenom_client"),
						rs.getString("adresse_client"),
						rs.getString("date_livraison"),
						rs.getString("libelle_article"),
						rs.getDouble("prix_unitaire"),
						rs.getString("qte_article"),						
						rs.getDouble("montant")						
				});
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		table.setModel(model);
	}
}
