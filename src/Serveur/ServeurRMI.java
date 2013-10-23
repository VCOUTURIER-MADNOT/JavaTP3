package Serveur;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import Serveur.Gestion.GestionUtilisateur;

 
/**
 * Serveur qui cree une personne et la met a disposition des clients.
 * @author Cyril Rabat
 * @version 08/10/2013
 */
public class ServeurRMI {
 
    /**
     * Methode principale.
     * @param args inutilise
     */
    public static void main(String[] args) {
		try {
			GestionUtilisateur GU = new GestionUtilisateur();
			Naming.rebind("Serveur/Gestion", GU);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Serveur démarré !");
    }
 
}