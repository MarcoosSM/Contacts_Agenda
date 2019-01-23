package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import entities.Contact;

public class AgendaManagement {

	// functions

	// this method autosave the agenda
	public void autosaveAgenda(ArrayList<Contact> contactsList)
			throws ParserConfigurationException, TransformerException, IOException {
		String path = "saves/";
		String fileName = "agenda";
		exportBIN(contactsList, path, fileName);
	}

	// this method autoload the agenda
	public ArrayList<Contact> autoloadAgenda()
			throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException {
		ArrayList<Contact> savedContactList;
		String path = "saves/";
		String fileName = "agenda";
		File loadFile = new File(path + fileName + ".bin");
		if (loadFile.exists()) {
			savedContactList = importBIN(path, fileName);
		} else {
			savedContactList = new ArrayList<Contact>();
		}
		return savedContactList;
	}

	// this method call export methods according to their extension
	public void exportAgenda(ArrayList<Contact> contactsList, String path, String fileName, int ext)
			throws ParserConfigurationException, TransformerException, IOException {
		if (ext == 0) {
			exportTXT(contactsList, path, fileName);
		}
		if (ext == 1) {
			exportBIN(contactsList, path, fileName);
		}
		if (ext == 2) {
			exportXML(contactsList, path, fileName);
		}
	}

	// this method call import methods according to their extension
	public ArrayList<Contact> importAgenda(String path, String fileName)
			throws IOException, ParserConfigurationException, SAXException, ClassNotFoundException {
		ArrayList<Contact> importedContactList = null;
		File importedFileTXT = new File(path + fileName + ".txt");
		File importedFileBIN = new File(path + fileName + ".bin");
		File importedFileXML = new File(path + fileName + ".xml");
		if (importedFileTXT.exists() && !importedFileBIN.exists() && !importedFileXML.exists()) {
			importedContactList = importTXT(path, fileName);
		} else if (importedFileBIN.exists() && !importedFileTXT.exists() && !importedFileXML.exists()) {
			importedContactList = importBIN(path, fileName);
		} else if (importedFileXML.exists() && !importedFileBIN.exists() && !importedFileTXT.exists()) {
			importedContactList = importXML(path, fileName);
		}
		return importedContactList;
	}

	// this method export the arraylist in an txt file
	private void exportTXT(ArrayList<Contact> contactsList, String path, String fileName) throws IOException {
		File newFile = new File(path + fileName + ".txt");
		FileWriter fw = new FileWriter(newFile);
		for (int i = 0; i < contactsList.size(); ++i) {
			fw.write(contactsList.get(i).toString());
		}
		fw.close();
	}

	// this method export the arraylist in an bin file
	private void exportBIN(ArrayList<Contact> contactsList, String path, String fileName) throws IOException {
		File newFile = new File(path + fileName + ".bin");
		FileOutputStream fos = new FileOutputStream(newFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(contactsList);
		oos.flush();
		fos.close();
		oos.close();
	}

	// this method export the arraylist in an xml file
	private void exportXML(ArrayList<Contact> contactsList, String path, String fileName)
			throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		// root elements
		Document doc = db.newDocument();
		Element rootElement = doc.createElement("contacts");
		doc.appendChild(rootElement);
		for (int i = 0; i < contactsList.size(); ++i) {
			// contact
			Element contact = doc.createElement("contact");
			rootElement.appendChild(contact);

			// name
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(contactsList.get(i).getName()));
			contact.appendChild(name);

			// surnames
			Element surnames = doc.createElement("surnames");
			surnames.appendChild(doc.createTextNode(contactsList.get(i).getSurnames()));
			contact.appendChild(surnames);

			// alias
			Element alias = doc.createElement("alias");
			alias.appendChild(doc.createTextNode(contactsList.get(i).getAlias()));
			contact.appendChild(alias);

			// emails
			Element emails = doc.createElement("emails");
			for (int j = 0; j < contactsList.get(i).getEmail().size(); ++j) {
				Element email = doc.createElement("email");
				email.appendChild(doc.createTextNode(contactsList.get(i).getEmail().get(j)));
				emails.appendChild(email);
			}
			contact.appendChild(emails);

			// address
			Element address = doc.createElement("address");
			address.appendChild(doc.createTextNode(contactsList.get(i).getAddress()));
			contact.appendChild(address);

			// phone
			Element phone = doc.createElement("phone");
			phone.appendChild(doc.createTextNode(contactsList.get(i).getPhone()));
			contact.appendChild(phone);

			// mobilePhones
			Element mobilePhones = doc.createElement("mobilePhones");
			for (int k = 0; k < contactsList.get(i).getMobilePhone().size(); ++k) {
				Element mobilePhone = doc.createElement("mobilePhone");
				mobilePhone.appendChild(doc.createTextNode(contactsList.get(i).getMobilePhone().get(k)));
				mobilePhones.appendChild(mobilePhone);
			}
			contact.appendChild(mobilePhones);
		}
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		// Format xml file
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path + fileName + ".xml"));
		transformer.transform(source, result);
	}

	// this method import the arraylist of a txt file
	private ArrayList<Contact> importTXT(String path, String fileName) throws IOException {
		ArrayList<Contact> importedContactList = new ArrayList<Contact>();
		File newFile = new File(path + fileName + ".txt");
		if (newFile.exists()) {
			@SuppressWarnings("resource")
			Scanner s = new Scanner(newFile);
			while (s.hasNextLine()) {
				ArrayList<String> emails = new ArrayList<String>();
				ArrayList<String> mobilePhones = new ArrayList<String>();
				String[] data = s.nextLine().split(",");
				String name = "";
				if (data[0].contains("=")) {
					String[] finalData = data[0].split("=");
					name = finalData[1];
				}
				String surnames = "";
				if (data[1].contains("=")) {
					String[] finalData = data[1].split("=");
					surnames = finalData[1];
				}
				String alias = "";
				if (data[2].contains("=")) {
					String[] finalData = data[2].split("=");
					alias = finalData[1];
				}
				String email = "";
				if (data[3].contains("=")) {
					String[] finalData = data[3].split("=");
					email = finalData[1].substring(1, finalData[1].length() - 1);
					emails.add(email);
				}
				String address = "";
				if (data[4].contains("=")) {
					String[] finalData = data[4].split("=");
					address = finalData[1];
				}
				String phone = "";
				if (data[5].contains("=")) {
					String[] finalData = data[5].split("=");
					phone = finalData[1];
				}
				String mobilePhone = "";
				if (data[6].contains("=")) {
					String[] finalData = data[6].split("=");
					mobilePhone = finalData[1].substring(1, finalData[1].length() - 2);
					mobilePhones.add(mobilePhone);
				}
				Contact newContact = new Contact(name, surnames, alias, emails, address, phone, mobilePhones);
				importedContactList.add(newContact);
			}
		}
		return importedContactList;
	}

	// this method import the arraylist of a bin file
	@SuppressWarnings("unchecked")
	private ArrayList<Contact> importBIN(String path, String fileName) throws IOException, ClassNotFoundException {
		ArrayList<Contact> importedContactList = null;
		File newFile = new File(path + fileName + ".bin");
		if (newFile.exists()) {
			FileInputStream fis = new FileInputStream(newFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			importedContactList = (ArrayList<Contact>) ois.readObject();
			fis.close();
			ois.close();
		}
		return importedContactList;
	}

	// this method import the arraylist of a xml file
	private ArrayList<Contact> importXML(String path, String fileName)
			throws ParserConfigurationException, SAXException, IOException {
		ArrayList<Contact> importedContactList = null;
		File file = new File(path + fileName + ".xml");
		if (file.exists()) {

			// Create the parser instance
			SAXHandler handler = new SAXHandler();

			// Parse the file
			importedContactList = handler.parseXml(new FileInputStream(file));

			// Verify the result
			System.out.println(importedContactList);
		} else {
			importedContactList = new ArrayList<Contact>();
		}
		return importedContactList;
	}
}
