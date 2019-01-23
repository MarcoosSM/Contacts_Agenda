package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import control.AgendaManagement;
import control.ContactManagement;
import entities.Contact;

public class ContactsAgendaInterface {

	// attributes
	ContactManagement cManager = new ContactManagement();
	AgendaManagement aManager = new AgendaManagement();
	ArrayList<String> addEmailList = new ArrayList<String>();
	ArrayList<String> addMobilePhoneList = new ArrayList<String>();

	// window attributes
	// frame
	private JFrame ContactsAgenda;

	// panels
	private JPanel mainPanel;
	private JPanel titlePanel;
	private JPanel buttonPanel;
	private JPanel addContactPanel;
	private JPanel addContactTitlePanel;
	private JPanel addContactDataPanel;
	private JPanel addContactButtonPanel;
	private JPanel searchContactPanel;
	private JPanel searchContactTitlePanel;
	private JPanel emptyPanel;
	private JPanel searchContactDataPanel;
	private JPanel searchContactButtonPanel;
	private JPanel export_importPanel;
	private JPanel export_importTitlePanel;
	private JPanel export_importDataPanel;
	private JPanel export_importButtonPanel;
	private JPanel moreInfoPanel;

	// textFields
	private JTextField nameTextField;
	private JTextField surnamesTextField;
	private JTextField aliasTextField;
	private JTextField emailTextField;
	private JTextField addressTextField;
	private JTextField phoneTextField;
	private JTextField mobilePhoneTextField;
	private JTextField searchTextField;
	private JTextField pathExportTextField;
	private JTextField fileNameExportTextField;
	private JTextField pathImportTextField;
	private JTextField fileNameImportTextField;

	// jlabels
	private JLabel lblTitleContactAgenda;
	private JLabel lblTitleAddContact;
	JLabel lblName;
	JLabel lblSurnames;
	JLabel lblAlias;

	// jbuttons
	JButton btnRemoveEmail;
	JButton btnRemoveMobilePhone;
	JButton btnSearch;
	JButton btnRemove;
	JButton btnModify;
	private JButton btnMoreInfo;

	// jseparator
	private JSeparator startSeparatorAddContact;
	private JSeparator endSeparatorAddContact;
	private JSeparator startSeparatorSearchContact;
	private JSeparator mediumSeparatorSearchContact;
	private JSeparator endSeparatorSearchContact;
	private JSeparator startSeparatorTitle;

	// jlists
	private JList<String> emailList;
	private JList<String> mobilePhoneList;

	// jtable
	private JTable contactsTable;
	private JTable moreInfoTable;

	// jcombobox
	JComboBox<String> searchComboBox;
	JComboBox<String> FileExtensionComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactsAgendaInterface window = new ContactsAgendaInterface();
					window.ContactsAgenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws ClassNotFoundException
	 */
	public ContactsAgendaInterface()
			throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException {
		initialize();
		cManager.setContactList(aManager.autoloadAgenda());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ContactsAgenda = new JFrame();
		ContactsAgenda.setResizable(false);
		ContactsAgenda.setTitle("Contacts Agenda");
		ContactsAgenda.setBounds(100, 100, 456, 516);
		ContactsAgenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ContactsAgenda.getContentPane().setLayout(null);

		addContactPanel = new JPanel();
		addContactPanel.setVisible(false);

		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 440, 477);
		ContactsAgenda.getContentPane().add(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));

		titlePanel = new JPanel();
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblTitleContactAgenda = new JLabel("Contact Agenda");
		lblTitleContactAgenda.setFont(new Font("Tahoma", Font.BOLD, 25));
		titlePanel.add(lblTitleContactAgenda);

		buttonPanel = new JPanel();
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.setLayout(null);

		JButton btnAddContact = new JButton("Add Contact");
		btnAddContact.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(1);
			}
		});

		startSeparatorTitle = new JSeparator();
		startSeparatorTitle.setBounds(0, 0, 440, 2);
		buttonPanel.add(startSeparatorTitle);
		btnAddContact.setBounds(92, 63, 256, 30);
		buttonPanel.add(btnAddContact);

		JButton btnSearchContact = new JButton("Search Contact");
		btnSearchContact.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSearchContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contactsTable.setModel(cManager.contactsTable(contactsTable, cManager.getContactList()));
				changePanel(2);
			}
		});
		btnSearchContact.setBounds(92, 156, 256, 30);
		buttonPanel.add(btnSearchContact);

		JButton btnExport_ImportAgenda = new JButton("Export/Import");
		btnExport_ImportAgenda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExport_ImportAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(3);
			}
		});
		btnExport_ImportAgenda.setBounds(92, 249, 256, 30);
		buttonPanel.add(btnExport_ImportAgenda);

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(92, 342, 256, 30);
		buttonPanel.add(btnExit);
		addContactPanel.setBounds(0, 0, 440, 477);
		ContactsAgenda.getContentPane().add(addContactPanel);
		addContactPanel.setLayout(new BorderLayout(0, 0));

		addContactTitlePanel = new JPanel();
		addContactPanel.add(addContactTitlePanel, BorderLayout.NORTH);

		lblTitleAddContact = new JLabel("Add Contact");
		lblTitleAddContact.setFont(new Font("Tahoma", Font.BOLD, 15));
		addContactTitlePanel.add(lblTitleAddContact);

		addContactDataPanel = new JPanel();
		addContactPanel.add(addContactDataPanel, BorderLayout.CENTER);
		addContactDataPanel.setLayout(null);

		startSeparatorAddContact = new JSeparator();
		startSeparatorAddContact.setBounds(0, 0, 440, 2);
		addContactDataPanel.add(startSeparatorAddContact);

		nameTextField = new JTextField();
		nameTextField.setBounds(196, 28, 178, 20);
		addContactDataPanel.add(nameTextField);
		nameTextField.setColumns(10);

		surnamesTextField = new JTextField();
		surnamesTextField.setBounds(196, 73, 178, 20);
		addContactDataPanel.add(surnamesTextField);
		surnamesTextField.setColumns(10);

		aliasTextField = new JTextField();
		aliasTextField.setBounds(196, 118, 178, 20);
		addContactDataPanel.add(aliasTextField);
		aliasTextField.setColumns(10);

		emailTextField = new JTextField();
		emailTextField.setBounds(196, 163, 157, 20);
		addContactDataPanel.add(emailTextField);
		emailTextField.setColumns(10);

		emailList = new JList<String>();
		emailList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = emailList.getSelectedIndex();
				if (row != -1) {
					btnRemoveEmail.setVisible(true);
				}
			}
		});
		emailList.setFont(new Font("Tahoma", Font.PLAIN, 11));
		emailList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneEmailList = new JScrollPane(emailList);
		scrollPaneEmailList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneEmailList.setBounds(196, 194, 178, 31);
		addContactDataPanel.add(scrollPaneEmailList);

		addressTextField = new JTextField();
		addressTextField.setBounds(196, 242, 178, 20);
		addContactDataPanel.add(addressTextField);
		addressTextField.setColumns(10);

		phoneTextField = new JTextField();
		phoneTextField.setBounds(196, 288, 178, 20);
		addContactDataPanel.add(phoneTextField);
		phoneTextField.setColumns(10);

		mobilePhoneTextField = new JTextField();
		mobilePhoneTextField.setBounds(196, 332, 157, 20);
		addContactDataPanel.add(mobilePhoneTextField);
		mobilePhoneTextField.setColumns(10);

		mobilePhoneList = new JList<String>();
		mobilePhoneList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = mobilePhoneList.getSelectedIndex();
				if (row != -1) {
					btnRemoveMobilePhone.setVisible(true);
				}
			}
		});
		mobilePhoneList.setFont(new Font("Tahoma", Font.PLAIN, 11));
		mobilePhoneList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPaneMobilePhoneList = new JScrollPane(mobilePhoneList);
		scrollPaneMobilePhoneList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneMobilePhoneList.setBounds(196, 363, 178, 31);
		addContactDataPanel.add(scrollPaneMobilePhoneList);

		lblName = new JLabel("Name*");
		lblName.setBounds(64, 31, 89, 14);
		addContactDataPanel.add(lblName);

		lblSurnames = new JLabel("Surnames*");
		lblSurnames.setBounds(64, 76, 89, 14);
		addContactDataPanel.add(lblSurnames);

		lblAlias = new JLabel("Alias");
		lblAlias.setBounds(64, 121, 89, 14);
		addContactDataPanel.add(lblAlias);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(64, 166, 89, 14);
		addContactDataPanel.add(lblEmail);

		JLabel lblAdress = new JLabel("Adress");
		lblAdress.setBounds(64, 245, 89, 14);
		addContactDataPanel.add(lblAdress);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(64, 290, 89, 14);
		addContactDataPanel.add(lblPhone);

		JLabel lblMobilePhone = new JLabel("Mobile Phone");
		lblMobilePhone.setBounds(64, 335, 89, 14);
		addContactDataPanel.add(lblMobilePhone);

		JButton btnAddEmail = new JButton("+");
		btnAddEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!emailTextField.getText().equals("")) {
					addEmailList.add(emailTextField.getText());
				}
				emailTextField.setText("");
				emailList.setModel(cManager.addEmailList(addEmailList));
			}
		});
		btnAddEmail.setMargin(new Insets(2, 2, 2, 2));
		btnAddEmail.setBounds(353, 163, 21, 20);
		addContactDataPanel.add(btnAddEmail);

		JButton btnAddMobilePhone = new JButton("+");
		btnAddMobilePhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!mobilePhoneTextField.getText().equals("")) {
					addMobilePhoneList.add(mobilePhoneTextField.getText());
				}
				mobilePhoneTextField.setText("");
				mobilePhoneList.setModel(cManager.addMobilePhoneList(addMobilePhoneList));
			}
		});
		btnAddMobilePhone.setMargin(new Insets(2, 2, 2, 2));
		btnAddMobilePhone.setBounds(353, 332, 21, 20);
		addContactDataPanel.add(btnAddMobilePhone);

		btnRemoveEmail = new JButton("-");
		btnRemoveEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (addEmailList.isEmpty() == false && emailList.getSelectedIndex() != -1) {
					if (addEmailList.size() == 1) {
						btnRemoveEmail.setVisible(false);
					}
					addEmailList.remove(emailList.getSelectedIndex());
					emailList.setModel(cManager.addEmailList(addEmailList));
				}
			}
		});
		btnRemoveEmail.setVisible(false);
		btnRemoveEmail.setMargin(new Insets(2, 4, 2, 4));
		btnRemoveEmail.setBounds(175, 194, 21, 31);
		addContactDataPanel.add(btnRemoveEmail);

		btnRemoveMobilePhone = new JButton("-");
		btnRemoveMobilePhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (addMobilePhoneList.isEmpty() == false && mobilePhoneList.getSelectedIndex() != -1) {
					if (addMobilePhoneList.size() == 1) {
						btnRemoveMobilePhone.setVisible(false);
					}
					addMobilePhoneList.remove(mobilePhoneList.getSelectedIndex());
					mobilePhoneList.setModel(cManager.addMobilePhoneList(addMobilePhoneList));
				}
			}
		});
		btnRemoveMobilePhone.setVisible(false);
		btnRemoveMobilePhone.setMargin(new Insets(2, 4, 2, 4));
		btnRemoveMobilePhone.setBounds(175, 363, 21, 31);
		addContactDataPanel.add(btnRemoveMobilePhone);

		endSeparatorAddContact = new JSeparator();
		endSeparatorAddContact.setBounds(0, 413, 440, 2);
		addContactDataPanel.add(endSeparatorAddContact);

		addContactButtonPanel = new JPanel();
		addContactPanel.add(addContactButtonPanel, BorderLayout.SOUTH);

		JButton btnBackAddContact = new JButton("Back");
		btnBackAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(0);
			}
		});
		addContactButtonPanel.add(btnBackAddContact);

		JButton btnResetContactData = new JButton("Reset");
		btnResetContactData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		addContactButtonPanel.add(btnResetContactData);

		JButton btnAdd = new JButton("Add Contact");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText();
				String surnames = surnamesTextField.getText();
				String alias = aliasTextField.getText();
				ArrayList<String> emails = new ArrayList<String>();
				for (int i = 0; i < emailList.getModel().getSize(); ++i) {
					emails.add(emailList.getModel().getElementAt(i));
				}
				String address = addressTextField.getText();
				String phone = phoneTextField.getText();
				ArrayList<String> mobilePhones = new ArrayList<String>();
				for (int i = 0; i < mobilePhoneList.getModel().getSize(); ++i) {
					mobilePhones.add(mobilePhoneList.getModel().getElementAt(i));
				}
				if (name.equals("") || surnames.equals("")) {
					JOptionPane.showMessageDialog(ContactsAgenda, "Name and Surnames can't be empty", "Error",
							JOptionPane.ERROR_MESSAGE);
					reset();
				} else {
					if (addEmailList.isEmpty()) {
						addEmailList.add("");
					}
					if (addMobilePhoneList.isEmpty()) {
						addMobilePhoneList.add("");
					}
					Contact newContact = new Contact(name, surnames, alias, emails, address, phone, mobilePhones);
					cManager.addContact(newContact);
					try {
						aManager.autosaveAgenda(cManager.getContactList());
					} catch (ParserConfigurationException | TransformerException | IOException e1) {
						JOptionPane.showMessageDialog(ContactsAgenda, "Autosave error!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
					reset();
				}
			}
		});
		addContactButtonPanel.add(btnAdd);

		export_importPanel = new JPanel();
		export_importPanel.setVisible(false);

		searchContactPanel = new JPanel();
		searchContactPanel.setVisible(false);
		searchContactPanel.setBounds(0, 0, 440, 477);
		ContactsAgenda.getContentPane().add(searchContactPanel);
		searchContactPanel.setLayout(new BorderLayout(0, 0));

		searchContactTitlePanel = new JPanel();
		searchContactPanel.add(searchContactTitlePanel, BorderLayout.NORTH);
		searchContactTitlePanel.setLayout(new BorderLayout(0, 0));

		searchComboBox = new JComboBox<String>();
		searchComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Name", "Surnames", "Alias" }));
		searchContactTitlePanel.add(searchComboBox, BorderLayout.WEST);

		searchTextField = new JTextField();
		searchContactTitlePanel.add(searchTextField, BorderLayout.CENTER);
		searchTextField.setColumns(10);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int searchFilter = searchComboBox.getSelectedIndex() + 1;
				String data = searchTextField.getText();
				contactsTable
						.setModel(cManager.contactsTable(contactsTable, cManager.searchContact(searchFilter, data)));
			}
		});
		searchContactTitlePanel.add(btnSearch, BorderLayout.EAST);

		emptyPanel = new JPanel();
		searchContactTitlePanel.add(emptyPanel, BorderLayout.NORTH);

		searchContactDataPanel = new JPanel();
		searchContactDataPanel.setLayout(new BorderLayout(0, 0));
		searchContactPanel.add(searchContactDataPanel, BorderLayout.CENTER);

		contactsTable = new JTable();
		contactsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnRemove.setEnabled(true);
				btnModify.setEnabled(true);
				btnMoreInfo.setEnabled(true);
			}
		});
		contactsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		searchContactDataPanel.add(contactsTable.getTableHeader(), BorderLayout.NORTH);

		JScrollPane scrollPaneContactsTable = new JScrollPane(contactsTable);
		searchContactDataPanel.add(scrollPaneContactsTable, BorderLayout.CENTER);

		searchContactButtonPanel = new JPanel();
		searchContactPanel.add(searchContactButtonPanel, BorderLayout.SOUTH);

		JButton btnBackSearchContact = new JButton("Back");
		btnBackSearchContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(0);
			}
		});
		searchContactButtonPanel.add(btnBackSearchContact);

		btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = (String) contactsTable.getModel().getValueAt(contactsTable.getSelectedRow(), 0);
				String surnames = (String) contactsTable.getModel().getValueAt(contactsTable.getSelectedRow(), 1);
				String alias = (String) contactsTable.getModel().getValueAt(contactsTable.getSelectedRow(), 2);
				String address = (String) contactsTable.getModel().getValueAt(contactsTable.getSelectedRow(), 3);
				String phone = (String) contactsTable.getModel().getValueAt(contactsTable.getSelectedRow(), 4);
				ArrayList<String> emails = cManager.getContactList().get(contactsTable.getSelectedRow()).getEmail();
				ArrayList<String> mobilePhones = cManager.getContactList().get(contactsTable.getSelectedRow())
						.getMobilePhone();
				Contact modifiedContact = new Contact(name, surnames, alias, emails, address, phone, mobilePhones);
				cManager.modifyContact(contactsTable.getSelectedRow(), modifiedContact);
				try {
					aManager.autosaveAgenda(cManager.getContactList());
				} catch (ParserConfigurationException | TransformerException | IOException e1) {
					JOptionPane.showMessageDialog(ContactsAgenda, "Autosave error!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				reset();
				contactsTable.setModel(cManager.contactsTable(contactsTable, cManager.getContactList()));
			}
		});

		btnMoreInfo = new JButton("More Information");
		btnMoreInfo.setEnabled(false);
		btnMoreInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> questionComboBox = new JComboBox<String>();
				questionComboBox.addItem("Emails");
				questionComboBox.addItem("Mobile Phones");
				JOptionPane.showOptionDialog(ContactsAgenda, questionComboBox, "Emails or Mobile Phones?",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, questionComboBox);
				if (questionComboBox.getSelectedItem().equals("Emails")) {
					changePanel(4);
					moreInfoTable.setModel(cManager.emailsTable(contactsTable,
							cManager.getContactList().get(contactsTable.getSelectedRow())));
				} else if (questionComboBox.getSelectedItem().equals("Mobile Phones")) {
					changePanel(4);
					moreInfoTable.setModel(cManager.mobilePhoneTable(contactsTable,
							cManager.getContactList().get(contactsTable.getSelectedRow())));
				}
			}
		});
		searchContactButtonPanel.add(btnMoreInfo);
		btnModify.setEnabled(false);
		searchContactButtonPanel.add(btnModify);

		btnRemove = new JButton("Remove");
		btnRemove.setEnabled(false);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cManager.removeContact(contactsTable.getSelectedRow());
				contactsTable.setModel(cManager.contactsTable(contactsTable, cManager.getContactList()));
				try {
					aManager.autosaveAgenda(cManager.getContactList());
				} catch (ParserConfigurationException | TransformerException | IOException e1) {
					JOptionPane.showMessageDialog(ContactsAgenda, "Autosave error!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				reset();
			}
		});
		searchContactButtonPanel.add(btnRemove);

		moreInfoPanel = new JPanel();
		moreInfoPanel.setVisible(false);
		moreInfoPanel.setBounds(0, 0, 440, 477);
		ContactsAgenda.getContentPane().add(moreInfoPanel);
		moreInfoPanel.setLayout(new BorderLayout(0, 0));

		JPanel moreInfoDataPanel = new JPanel();
		moreInfoPanel.add(moreInfoDataPanel, BorderLayout.CENTER);
		moreInfoDataPanel.setLayout(new BorderLayout(0, 0));

		moreInfoTable = new JTable();
		moreInfoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		moreInfoDataPanel.add(moreInfoTable.getTableHeader(), BorderLayout.NORTH);

		JScrollPane scrollPaneMoreInfoTable = new JScrollPane(moreInfoTable);
		moreInfoDataPanel.add(scrollPaneMoreInfoTable, BorderLayout.CENTER);

		JPanel moreInfoButtonPanel = new JPanel();
		moreInfoPanel.add(moreInfoButtonPanel, BorderLayout.SOUTH);

		JButton btnBackMoreInfo = new JButton("Back");
		btnBackMoreInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(2);
			}
		});
		moreInfoButtonPanel.add(btnBackMoreInfo);
		export_importPanel.setBounds(0, 0, 440, 477);
		ContactsAgenda.getContentPane().add(export_importPanel);
		export_importPanel.setLayout(new BorderLayout(0, 0));

		export_importTitlePanel = new JPanel();
		export_importPanel.add(export_importTitlePanel, BorderLayout.NORTH);

		JLabel lblTitleExport_Import = new JLabel("Export/Import Agenda");
		lblTitleExport_Import.setFont(new Font("Tahoma", Font.BOLD, 15));
		export_importTitlePanel.add(lblTitleExport_Import);

		export_importDataPanel = new JPanel();
		export_importPanel.add(export_importDataPanel, BorderLayout.CENTER);
		export_importDataPanel.setLayout(null);

		startSeparatorSearchContact = new JSeparator();
		startSeparatorSearchContact.setBounds(0, 0, 440, 2);
		export_importDataPanel.add(startSeparatorSearchContact);

		JLabel lblExportTitle = new JLabel("Export Agenda:");
		lblExportTitle.setBounds(10, 11, 124, 14);
		export_importDataPanel.add(lblExportTitle);
		lblExportTitle.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblPathExport = new JLabel("Path:");
		lblPathExport.setBounds(20, 60, 45, 14);
		export_importDataPanel.add(lblPathExport);

		JLabel lblFileNameExport = new JLabel("File Name:");
		lblFileNameExport.setBounds(222, 60, 69, 14);
		export_importDataPanel.add(lblFileNameExport);

		pathExportTextField = new JTextField();
		pathExportTextField.setBounds(20, 85, 192, 20);
		export_importDataPanel.add(pathExportTextField);
		pathExportTextField.setColumns(10);

		fileNameExportTextField = new JTextField();
		fileNameExportTextField.setBounds(222, 85, 119, 20);
		export_importDataPanel.add(fileNameExportTextField);
		fileNameExportTextField.setColumns(10);

		FileExtensionComboBox = new JComboBox<String>();
		FileExtensionComboBox.setBounds(351, 84, 65, 22);
		export_importDataPanel.add(FileExtensionComboBox);
		FileExtensionComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { ".txt", ".bin", ".xml" }));

		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = pathExportTextField.getText();
				String fileName = fileNameExportTextField.getText();
				int ext = FileExtensionComboBox.getSelectedIndex();
				try {
					aManager.exportAgenda(cManager.getContactList(), path, fileName, ext);
					JOptionPane.showMessageDialog(ContactsAgenda, "Successfully exported!", "EXPORTED!",
							JOptionPane.INFORMATION_MESSAGE);
					reset();
				} catch (ParserConfigurationException | TransformerException | IOException e1) {
					JOptionPane.showMessageDialog(ContactsAgenda, "Export error!", "ERROR!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnExport.setBounds(351, 158, 65, 23);
		export_importDataPanel.add(btnExport);

		mediumSeparatorSearchContact = new JSeparator();
		mediumSeparatorSearchContact.setBounds(0, 206, 440, 2);
		export_importDataPanel.add(mediumSeparatorSearchContact);

		JLabel lblImportTitle = new JLabel("Import Agenda:");
		lblImportTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblImportTitle.setBounds(10, 229, 124, 14);
		export_importDataPanel.add(lblImportTitle);

		JLabel lblPathImport = new JLabel("Path:");
		lblPathImport.setBounds(20, 276, 46, 14);
		export_importDataPanel.add(lblPathImport);

		JLabel lblFileNameImport = new JLabel("File Name:");
		lblFileNameImport.setBounds(222, 276, 69, 14);
		export_importDataPanel.add(lblFileNameImport);

		pathImportTextField = new JTextField();
		pathImportTextField.setBounds(20, 301, 192, 20);
		export_importDataPanel.add(pathImportTextField);
		pathImportTextField.setColumns(10);

		fileNameImportTextField = new JTextField();
		fileNameImportTextField.setBounds(222, 301, 119, 20);
		export_importDataPanel.add(fileNameImportTextField);
		fileNameImportTextField.setColumns(10);

		JButton btnImport = new JButton("Import");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = pathImportTextField.getText();
				String fileName = fileNameImportTextField.getText();
				try {
					try {
						cManager.setContactList(aManager.importAgenda(path, fileName));
						JOptionPane.showMessageDialog(ContactsAgenda, "Successfully imported!", "IMPORTED!",
								JOptionPane.INFORMATION_MESSAGE);
						reset();
					} catch (IOException | ParserConfigurationException e1) {
						JOptionPane.showMessageDialog(ContactsAgenda, "Import error!", "ERROR!",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (ClassNotFoundException | SAXException e1) {
					JOptionPane.showMessageDialog(ContactsAgenda, "Import error!", "ERROR!", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnImport.setBounds(351, 300, 65, 23);
		export_importDataPanel.add(btnImport);

		endSeparatorSearchContact = new JSeparator();
		endSeparatorSearchContact.setBounds(0, 413, 440, 2);
		export_importDataPanel.add(endSeparatorSearchContact);

		export_importButtonPanel = new JPanel();
		export_importPanel.add(export_importButtonPanel, BorderLayout.SOUTH);

		JButton btnBackExport_Import = new JButton("Back");
		btnBackExport_Import.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(0);
			}
		});
		export_importButtonPanel.add(btnBackExport_Import);
	}

	// methods

	// change the panels
	private void changePanel(int panelNumber) {
		if (panelNumber == 0) {
			mainPanel.setVisible(true);
			addContactPanel.setVisible(false);
			searchContactPanel.setVisible(false);
			export_importPanel.setVisible(false);
			reset();
		}
		if (panelNumber == 1) {
			mainPanel.setVisible(false);
			addContactPanel.setVisible(true);
		}
		if (panelNumber == 2) {
			mainPanel.setVisible(false);
			moreInfoPanel.setVisible(false);
			searchContactPanel.setVisible(true);
		}
		if (panelNumber == 3) {
			mainPanel.setVisible(false);
			export_importPanel.setVisible(true);
		}
		if (panelNumber == 4) {
			searchContactPanel.setVisible(false);
			moreInfoPanel.setVisible(true);
		}
	}

	// resets textfields, buttons and other components
	private void reset() {
		nameTextField.setText("");
		surnamesTextField.setText("");
		aliasTextField.setText("");
		emailTextField.setText("");
		addressTextField.setText("");
		phoneTextField.setText("");
		mobilePhoneTextField.setText("");
		searchTextField.setText("");
		pathExportTextField.setText("");
		pathImportTextField.setText("");
		fileNameExportTextField.setText("");
		fileNameImportTextField.setText("");
		mobilePhoneList.setModel(cManager.cleanMobilePhoneList());
		emailList.setModel(cManager.cleanEmailList());
		addMobilePhoneList.clear();
		addEmailList.clear();
		btnModify.setEnabled(false);
		btnRemove.setEnabled(false);
		btnMoreInfo.setEnabled(false);
		FileExtensionComboBox.setSelectedIndex(0);
		searchComboBox.setSelectedIndex(0);
	}
}
