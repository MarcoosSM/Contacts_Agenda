package control;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Contact;

public class ContactManagement {

	// attributes
	private ArrayList<Contact> contactList;

	// builder
	public ContactManagement() {
		contactList = new ArrayList<Contact>();
	}

	// getters and setters
	public ArrayList<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(ArrayList<Contact> contactList) {
		this.contactList = contactList;
	}

	// functions

	// this method add contacts
	public ArrayList<Contact> addContact(Contact newContact) {
		contactList.add(newContact);
		return contactList;
	}

	// this method remove contacts
	public ArrayList<Contact> removeContact(int selectedRow) {
		if (selectedRow != -1) {
			contactList.remove(selectedRow);
		}
		return contactList;
	}

	// this method modify contacts
	public ArrayList<Contact> modifyContact(int selectedRow, Contact modifiedContact) {
		contactList.set(selectedRow, modifiedContact);
		return contactList;
	}

	// this method search contacts with a filter
	public ArrayList<Contact> searchContact(int searchFilter, String data) {
		ArrayList<Contact> searchedContacts = new ArrayList<Contact>();
		if (searchFilter == 1) {
			for (int i = 0; i < contactList.size(); ++i) {
				if (contactList.get(i).getName().equals(data)) {
					searchedContacts.add(contactList.get(i));
				}
			}
		}
		if (searchFilter == 2) {
			for (int i = 0; i < contactList.size(); ++i) {
				if (contactList.get(i).getSurnames().equals(data)) {
					searchedContacts.add(contactList.get(i));
				}
			}
		}
		if (searchFilter == 3) {
			for (int i = 0; i < contactList.size(); ++i) {
				if (contactList.get(i).getAlias().equals(data)) {
					searchedContacts.add(contactList.get(i));
				}
			}
		}
		return searchedContacts;
	}

	// this method creates the contacts table model
	public DefaultTableModel contactsTable(JTable contactsTable, ArrayList<Contact> contacts) {
		try {
			DefaultTableModel model = null;
			model = new DefaultTableModel();
			model.addColumn("Name");
			model.addColumn("Surnames");
			model.addColumn("Alias");
			model.addColumn("Address");
			model.addColumn("Phone");
			for (int i = 0; i < contacts.size(); ++i) {
				String name = contacts.get(i).getName();
				String surnames = contacts.get(i).getSurnames();
				String alias = contacts.get(i).getAlias();
				String address = contacts.get(i).getAddress();
				String phone = contacts.get(i).getPhone();
				Object[] data = { name, surnames, alias, address, phone };
				model.addRow(data);
			}
			return model;
		} catch (NullPointerException e) {
			DefaultTableModel model = null;
			model = new DefaultTableModel();
			model.addColumn("Name");
			model.addColumn("Surnames");
			model.addColumn("Alias");
			model.addColumn("Address");
			model.addColumn("Phone");
			return model;
		}
	}

	// this method creates the emails table model
	public DefaultTableModel emailsTable(JTable contactsTable, Contact contact) {
		try {
			DefaultTableModel model = null;
			model = new DefaultTableModel();
			model.addColumn("Email");
			for (int i = 0; i < contact.getEmail().size(); ++i) {
				String email = contact.getEmail().get(i);
				Object[] data = { email };
				model.addRow(data);
			}
			return model;
		} catch (NullPointerException e) {
			DefaultTableModel model = null;
			model = new DefaultTableModel();
			model.addColumn("Email");
			return model;
		}
	}

	public DefaultTableModel mobilePhoneTable(JTable contactsTable, Contact contact) {
		try {
			DefaultTableModel model = null;
			model = new DefaultTableModel();
			model.addColumn("Mobile Phone");
			for (int i = 0; i < contact.getMobilePhone().size(); ++i) {
				String mobilePhone = contact.getMobilePhone().get(i);
				Object[] data = { mobilePhone };
				model.addRow(data);
			}
			return model;
		} catch (NullPointerException e) {
			DefaultTableModel model = null;
			model = new DefaultTableModel();
			model.addColumn("Mobile Phone");
			return model;
		}
	}

	// this method add emails to the email list model
	public DefaultListModel<String> addEmailList(ArrayList<String> emails) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < emails.size(); ++i) {
			model.addElement(emails.get(i));
		}
		return model;
	}

	// this method cleans the email list
	public DefaultListModel<String> cleanEmailList() {
		DefaultListModel<String> model = new DefaultListModel<String>();
		return model;
	}

	// this method add mobile phones to the mobile phone list model
	public DefaultListModel<String> addMobilePhoneList(ArrayList<String> mobilePhones) {
		DefaultListModel<String> model = null;
		model = new DefaultListModel<String>();
		for (int i = 0; i < mobilePhones.size(); ++i) {
			model.addElement(mobilePhones.get(i));
		}
		return model;
	}

	// this method cleans the mobile phone list
	public DefaultListModel<String> cleanMobilePhoneList() {
		DefaultListModel<String> model = new DefaultListModel<String>();
		return model;
	}
}
