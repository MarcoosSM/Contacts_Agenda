package control;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import entities.Contact;

@SuppressWarnings("deprecation")
public class SAXHandler extends DefaultHandler {

	// attributes
	private Contact contact;
	ArrayList<String> emailList;
	ArrayList<String> mobilePhoneList;
	private ArrayList<Contact> contactList = new ArrayList<Contact>();
	private Stack<String> elementStack = new Stack<String>();
	private Stack<Contact> objectStack = new Stack<Contact>();

	// methods
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// Push it in element stack
		this.elementStack.push(qName);

		// If this is start of contact element then prepare a new contact instance and
		// push
		// it in object stack
		if ("contact".equals(qName)) {
			// New contact instance
			contact = new Contact();
			emailList = new ArrayList<String>();
			mobilePhoneList = new ArrayList<String>();

			this.objectStack.push(contact);
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		// Remove last added element
		this.elementStack.pop();

		// Contact instance has been constructed so pop it from object stack and push in
		// contact list
		if ("contact".equals(qName)) {
			Contact c = this.objectStack.pop();
			this.contactList.add(c);
		}
	}

	/**
	 * This will be called everytime parser encounter a value node
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {
		String value = new String(ch, start, length).trim();
		if (value.length() == 0) {
			return; // ignore white space
		}

		// handle the value based on to which element it belongs
		if ("name".equals(currentElement())) {
			Contact contact = (Contact) this.objectStack.peek();
			contact.setName(value);
		} else if ("surnames".equals(currentElement())) {
			Contact contact = (Contact) this.objectStack.peek();
			contact.setSurnames(value);
		} else if ("alias".equals(currentElement())) {
			Contact contact = (Contact) this.objectStack.peek();
			contact.setAlias(value);
		} else if ("email".equals(currentElement())) {
			Contact contact = (Contact) this.objectStack.peek();
			emailList.add(value);
			contact.setEmail(emailList);
		} else if ("address".equals(currentElement())) {
			Contact contact = (Contact) this.objectStack.peek();
			contact.setAddress(value);
		} else if ("phone".equals(currentElement())) {
			Contact contact = (Contact) this.objectStack.peek();
			contact.setPhone(value);
		} else if ("mobilePhone".equals(currentElement())) {
			Contact contact = (Contact) this.objectStack.peek();
			mobilePhoneList.add(value);
			contact.setMobilePhone(mobilePhoneList);
		}
	}

	/**
	 * Utility method for getting the current element in processing
	 */
	private String currentElement() {
		return this.elementStack.peek();
	}

	public ArrayList<Contact> getContactList() {
		return contactList;
	}

	public ArrayList<Contact> parseXml(InputStream in) {
		ArrayList<Contact> contactsList = new ArrayList<Contact>();
		try {
			// Create default handler instance
			SAXHandler handler = new SAXHandler();

			// Create parser from factory
			XMLReader parser = XMLReaderFactory.createXMLReader();

			// Register handler with parser
			parser.setContentHandler(handler);

			// Create an input source from the XML input stream
			InputSource source = new InputSource(in);

			// parse the document
			parser.parse(source);

			contactsList = handler.getContactList();

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return contactsList;
	}
}