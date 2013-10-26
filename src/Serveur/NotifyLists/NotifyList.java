package Serveur.NotifyLists;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public abstract class NotifyList<E> extends ArrayList<E> {
	
	protected 			Document 	document;
	protected 			Element 	racine;
	protected			String		xmlUrl;
	
	public 		abstract void 		addToXML(E _o);
	public 		abstract void 		removeFromXML(Object _o);
	protected 	abstract Element 	getElementFromId(String _id);
	protected	abstract Element	getElementFromObject(Object _o);
	protected	abstract Object		getObjectFromElement(Element _e);
	
	public int getSize()
	{
		return super.size();
	}
	
	public boolean remove(Object _o)
	{
		this.reload();
		this.removeFromXML(_o);
		boolean res = super.remove(_o);
		this.saveXml();
		return res;
	}
	
	public boolean add(E _o)
	{
		boolean res = false;
		this.reload();
		if(!this.contains(_o))
		{
			this.addToXML(_o);
			res = super.add(_o);
			this.saveXml();
		}
		return res;
	}
	
	/**
	 * Méthode qui permet d'ajouter un élément dans la liste avec, ou non, l'ajout au fichier xml.
	 * @param _o Objet à ajouter à la liste.
	 * @param _addToXml Ajout au fichier XML ou non (cas de l'initialisation de la liste à partir du fichier xml)
	 * @return	Ajout réalisé ou non.
	 */
	public boolean add(E _o, boolean _addToXml)
	{
		boolean res = false;
		this.reload();
		if(!this.contains(_o))
		{
			if(_addToXml)
			{
				this.addToXML(_o);
			}
			res = super.add(_o);
			this.saveXml();
		}
		return res;
	}
	
	private void reload()
	{
		SAXBuilder sb = new SAXBuilder();
		
		try {
			this.document = sb.build(this.xmlUrl);
			this.racine = this.document.getRootElement();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Fichier " + this.xmlUrl + " rechargé.");
		
		System.out.println("Liste actualisée");
	}
	
	private void saveXml()
	{
	    XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	    
	    try {
			sortie.output(document, new FileOutputStream(this.xmlUrl));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    System.out.println("Fichier " + this.xmlUrl + " sauvegardé.");
	}
	
}