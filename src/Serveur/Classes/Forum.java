package Serveur.Classes;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import Serveur.Interfaces.IForum;
import Serveur.NotifyLists.MessageNotifyList;

public class Forum extends UnicastRemoteObject implements IForum{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5202636066424668920L;
	
	private String sujet;
	private MessageNotifyList messages;
	private int nbMessages;
	
	public Forum(String _sujet) throws RemoteException
	{
		this.sujet = _sujet;
		this.nbMessages = 0;
		this.createXml();
		this.messages = new MessageNotifyList(this.getURL());
	}
	
	public String getSujet(){
		return this.sujet;
	}
	
	public String getURL()
	{
		return this.sujet.trim();
	}
	
	public MessageNotifyList getMessages()
	{
		return this.messages;
	}
	
	@Override
	public void addMessage(String _contenu, String _login)
	{
		Message message = new Message( ++nbMessages, _contenu, _login);
		this.messages.add(message);
	}
	
	@Override
	public boolean equals(Object _o)
	{
		if (_o instanceof Forum)
		{
			Forum f = (Forum) _o;
			return f.getSujet().equals(this.getSujet());
		}
		
		return false;
	}
	
	public boolean createXml()
	{

		File f = new File(this.getURL() + ".xml");
		
		if(!f.exists())
		{	
			try
			{
				Element root = new Element("Forum");
				Document doc = new Document(root);
				
				XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
				sortie.output(doc, new FileOutputStream(this.getURL() + ".xml"));
			}
			catch (java.io.IOException e)
			{
				System.out.println("Erreur :" + e);
			}
		    
		    return true;
		}
		
		return false;		
	}
}
