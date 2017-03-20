package webservice;

public class AdminAPIProxy implements webservice.AdminAPI {
  private String _endpoint = null;
  private webservice.AdminAPI adminAPI = null;
  
  public AdminAPIProxy() {
    _initAdminAPIProxy();
  }
  
  public AdminAPIProxy(String endpoint) {
    _endpoint = endpoint;
    _initAdminAPIProxy();
  }
  
  private void _initAdminAPIProxy() {
    try {
      adminAPI = (new webservice.AdminAPIServiceLocator()).getAdminAPI();
      if (adminAPI != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)adminAPI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)adminAPI)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (adminAPI != null)
      ((javax.xml.rpc.Stub)adminAPI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webservice.AdminAPI getAdminAPI() {
    if (adminAPI == null)
      _initAdminAPIProxy();
    return adminAPI;
  }
  
  public java.lang.String getCategory(int category_id) throws java.rmi.RemoteException{
    if (adminAPI == null)
      _initAdminAPIProxy();
    return adminAPI.getCategory(category_id);
  }
  
  public java.lang.String createCategory(java.lang.String name) throws java.rmi.RemoteException{
    if (adminAPI == null)
      _initAdminAPIProxy();
    return adminAPI.createCategory(name);
  }
  
  public java.lang.String deleteAd(int annonce_id) throws java.rmi.RemoteException{
    if (adminAPI == null)
      _initAdminAPIProxy();
    return adminAPI.deleteAd(annonce_id);
  }
  
  public java.lang.String updateAd(int annonce_id, java.lang.String name, int categorie_id, java.lang.String rue, java.lang.String ville, java.lang.String codepostal, java.lang.String telephone) throws java.rmi.RemoteException{
    if (adminAPI == null)
      _initAdminAPIProxy();
    return adminAPI.updateAd(annonce_id, name, categorie_id, rue, ville, codepostal, telephone);
  }
  
  public java.lang.String getAllAd() throws java.rmi.RemoteException{
    if (adminAPI == null)
      _initAdminAPIProxy();
    return adminAPI.getAllAd();
  }
  
  public java.lang.String getAd(int annonce_id) throws java.rmi.RemoteException{
    if (adminAPI == null)
      _initAdminAPIProxy();
    return adminAPI.getAd(annonce_id);
  }
  
  public java.lang.String deleteCategorie(int categorie_id) throws java.rmi.RemoteException{
    if (adminAPI == null)
      _initAdminAPIProxy();
    return adminAPI.deleteCategorie(categorie_id);
  }
  
  public java.lang.String updateCategorie(int categorie_id, java.lang.String name) throws java.rmi.RemoteException{
    if (adminAPI == null)
      _initAdminAPIProxy();
    return adminAPI.updateCategorie(categorie_id, name);
  }
  
  public java.lang.String getAllCategorie() throws java.rmi.RemoteException{
    if (adminAPI == null)
      _initAdminAPIProxy();
    return adminAPI.getAllCategorie();
  }
  
  public java.lang.String createAd(java.lang.String name, int categorie_id, java.lang.String rue, java.lang.String ville, java.lang.String codepostal, java.lang.String telephone) throws java.rmi.RemoteException{
    if (adminAPI == null)
      _initAdminAPIProxy();
    return adminAPI.createAd(name, categorie_id, rue, ville, codepostal, telephone);
  }
  
  
}