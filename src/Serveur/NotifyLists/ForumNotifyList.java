package Serveur.NotifyLists;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import Serveur.Classes.Forum;

public class ForumNotifyList extends NotifyList<Forum>{

	public ForumNotifyList()
	{
		SAXBuilder sxb = new SAXBuilder();
		this.xmlUrl = "Forums.xml";
		try
		{
			this.document = sxb.build(new File(this.xmlUrl));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		this.racine = this.document.getRootElement();
		for (Element eForum : this.racine.getChildren("Forum"))
		{
			this.add((Forum) this.getObjectFromElement(eForum), false);
		}
	}
	
	@Override
	public void addToXML(Forum _f) {
		Element eForum = this.getElementFromObject(_f);
		if (eForum != null)
		{
			this.racine.addContent(eForum);
			System.out.println("Forum " + _f.getSujet() + " inséré dans la liste.");
		}
		
		this.racine.addContent(eForum);
	}

	@Override
	public void removeFromXML(Object _o) {
		if(_o instanceof Forum)
		{
			Forum f = (Forum) _o;
			this.racine.removeContent(this.getElementFromId(f.getSujet()));
		}
	}
	
	@Override
	public Element getElementFromId(String _id)
	{
		List<Element> sujets = this.racine.getChildren("Sujet");
		Iterator<Element> iterator = sujets.iterator();
		Element sujet = null;
		while(iterator.hasNext() && !( (sujet = iterator.next()).getText().equals(_id)));
		if(sujet != null)	
			return sujet.getParentElement();
		
		return null;
	}

	@Override
	protected Element getElementFromObject(Object _o) {
		Element eForum = null;
		
		if(_o instanceof Forum)
		{
			Forum f = (Forum) _o;
			eForum = new Element("Forum");
			
			Element eSujet = new Element("Sujet");
			eSujet.setText(f.getSujet());
		
			eForum.addContent(eSujet);
		}
		
		return eForum;
	}

	@Override
	protected Object getObjectFromElement(Element _e) {
		try {
			Forum f = new Forum(_e.getChildText("Sujet"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
}
