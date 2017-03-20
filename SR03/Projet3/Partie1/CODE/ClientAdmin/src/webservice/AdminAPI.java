/**
 * AdminAPI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public interface AdminAPI extends java.rmi.Remote {
    public java.lang.String getCategory(int category_id) throws java.rmi.RemoteException;
    public java.lang.String createCategory(java.lang.String name) throws java.rmi.RemoteException;
    public java.lang.String deleteAd(int annonce_id) throws java.rmi.RemoteException;
    public java.lang.String updateAd(int annonce_id, java.lang.String name, int categorie_id, java.lang.String rue, java.lang.String ville, java.lang.String codepostal, java.lang.String telephone) throws java.rmi.RemoteException;
    public java.lang.String getAllAd() throws java.rmi.RemoteException;
    public java.lang.String getAd(int annonce_id) throws java.rmi.RemoteException;
    public java.lang.String deleteCategorie(int categorie_id) throws java.rmi.RemoteException;
    public java.lang.String updateCategorie(int categorie_id, java.lang.String name) throws java.rmi.RemoteException;
    public java.lang.String getAllCategorie() throws java.rmi.RemoteException;
    public java.lang.String createAd(java.lang.String name, int categorie_id, java.lang.String rue, java.lang.String ville, java.lang.String codepostal, java.lang.String telephone) throws java.rmi.RemoteException;
}
