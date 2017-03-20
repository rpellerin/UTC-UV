package webservice;

public class ClientAPIProxy implements webservice.ClientAPI {
  private String _endpoint = null;
  private webservice.ClientAPI clientAPI = null;
  
  public ClientAPIProxy() {
    _initClientAPIProxy();
  }
  
  public ClientAPIProxy(String endpoint) {
    _endpoint = endpoint;
    _initClientAPIProxy();
  }
  
  private void _initClientAPIProxy() {
    try {
      clientAPI = (new webservice.ClientAPIServiceLocator()).getClientAPI();
      if (clientAPI != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)clientAPI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)clientAPI)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (clientAPI != null)
      ((javax.xml.rpc.Stub)clientAPI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webservice.ClientAPI getClientAPI() {
    if (clientAPI == null)
      _initClientAPIProxy();
    return clientAPI;
  }
  
  public java.lang.String getByParams(java.lang.String name, java.lang.String categoryName, java.lang.String street, java.lang.String city, java.lang.String postcode) throws java.rmi.RemoteException{
    if (clientAPI == null)
      _initClientAPIProxy();
    return clientAPI.getByParams(name, categoryName, street, city, postcode);
  }
  
  
}