package Serveur.NotifyLists;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import Serveur.Classes.Message;

public class MessageNotifyList extends NotifyList<Message>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5142942402600364906L;

	public MessageNotifyList(String _url)
	{
		SAXBuilder sxb = new SAXBuilder();
		this.xmlUrl = _url;
		try {
			this.document = sxb.build(new File(this.xmlUrl+".xml"));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.racine = this.document.getRootElement();
		for (Element eMessage : this.racine.getChildren("Message"))
		{
			int idMessage = Integer.parseInt(eMessage.getChild("Id").getText());
			String contenuMessage = eMessage.getChild("Contenu").getText();
			String loginMessage = eMessage.getChild("Auteur").getText();
			Date dateMessage = new Date(Long.parseLong(eMessage.getChild("Date").getText()));
			
			this.add(new Message(idMessage, contenuMessage, loginMessage, dateMessage), false);
		}
	}
	
	
	@Override
	public void addToXML(Message _o) {
		Element eMessage = this.getElementFromObject(_o);
		if (eMessage != null)
		{
			this.racine.addContent(eMessage);
			System.out.println("Message " + _o.getId() + " inséré dans le fichier XML.");
		}		
	}

	@Override
	public void removeFromXML(Object _o) {
		if(_o instanceof Message)
		{
			Message m = (Message) _o;
			this.racine.removeContent(this.getElementFromId(Integer.toString(m.getId())));
		}
	}

	@Override
	protected Element getElementFromId(String _id) {
		
		List<Element> messages = this.racine.getChildren("Message");
		Iterator<Element> iterator = messages.iterator();
		Element message = null;
		while(iterator.hasNext())
		{
			message = iterator.next();
			if(Integer.parseInt(message.getChild("Id").getText()) == Integer.parseInt(_id))
			{
				return message;
			}
		}
		return null;
	}

	@Override
	protected Element getElementFromObject(Object _o) {
		Element eMessage = null;
		if (_o instanceof Message)
		{
			Message m = (Message) _o;
			Element eId = new Element("Id");
			eId.setText(Integer.toString(m.getId()));
			
			Element eContenu = new Element("Contenu");
			eContenu.setText(m.getContenu());
			
			Element eAuteur = new Element("Auteur");
			eAuteur.setText(m.getAuteur().getLogin());
			
			Element eDate = new Element("Date");
			eDate.setText(Long.toString(m.getDate().getTime()));
			
			eMessage = new Element("Message");
			eMessage.addContent(eId);
			eMessage.addContent(eContenu);
			eMessage.addContent(eAuteur);
			eMessage.addContent(eDate);
		}
		
		return eMessage;
	}

	@Override
	protected Object getObjectFromElement(Element _e) {
		
		int idMessage = Integer.parseInt(_e.getChild("Id").getText());
		String contenuMessage = _e.getChild("Contenu").getText();
		String loginMessage = _e.getChild("Auteur").getText();
		Date dateMessage = new Date(Long.parseLong(_e.getChild("Date").getText()));
		
		Message m = new Message(idMessage, contenuMessage, loginMessage, dateMessage);
		return m;
	}	

}
