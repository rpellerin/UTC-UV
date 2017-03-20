/**
 * AdminAPIService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public interface AdminAPIService extends javax.xml.rpc.Service {
    public java.lang.String getAdminAPIAddress();

    public webservice.AdminAPI getAdminAPI() throws javax.xml.rpc.ServiceException;

    public webservice.AdminAPI getAdminAPI(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
