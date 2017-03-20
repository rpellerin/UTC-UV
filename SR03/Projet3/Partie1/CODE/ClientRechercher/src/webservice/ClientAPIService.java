/**
 * ClientAPIService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public interface ClientAPIService extends javax.xml.rpc.Service {
    public java.lang.String getClientAPIAddress();

    public webservice.ClientAPI getClientAPI() throws javax.xml.rpc.ServiceException;

    public webservice.ClientAPI getClientAPI(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
