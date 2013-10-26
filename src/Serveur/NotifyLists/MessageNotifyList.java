package Serveur.NotifyLists;

import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;

import Serveur.Classes.Forum;
import Serveur.Classes.Message;

public class MessageNotifyList extends NotifyList<Message>{

	@Override
	public void addToXML(Message _o) {
		
		Element eId = new Element("Id");
		eId.setText(Integer.toString(_o.getId()));
		
		Element eContenu = new Element("Contenu");
		eContenu.setText(_o.getContenu());
		
		Element eAuteur = new Element("Auteur");
		eAuteur.setText(_o.getAuteur().getLogin());
		
		Element eDate = new Element("Date");
		eDate.setText(Long.toString(_o.getDate().getTime()));
		
		Element eMessage = new Element("Message");
		eMessage.addContent(eId);
		eMessage.addContent(eContenu);
		eMessage.addContent(eAuteur);
		eMessage.addContent(eDate);
		
		this.racine.addContent(eMessage);
		
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
		
		List<Element> ids = this.racine.getChildren("Id");
		Iterator<Element> iterator = ids.iterator();
		Element id = null;
		while(iterator.hasNext() && !( (id = iterator.next()).getText().equals(_id)));
		if(id != null)	
			return id.getParentElement();
		
		return null;
	}

	@Override
	protected Element getElementFromObject(Object _o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getObjectFromElement(Element _e) {
		// TODO Auto-generated method stub
		return null;
	}	

}
