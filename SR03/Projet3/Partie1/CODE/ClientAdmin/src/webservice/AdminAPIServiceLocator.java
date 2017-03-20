/**
 * AdminAPIServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public class AdminAPIServiceLocator extends org.apache.axis.client.Service implements webservice.AdminAPIService {

    public AdminAPIServiceLocator() {
    }


    public AdminAPIServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AdminAPIServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AdminAPI
    private java.lang.String AdminAPI_address = "http://localhost:8080/Projet3/services/AdminAPI";

    public java.lang.String getAdminAPIAddress() {
        return AdminAPI_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AdminAPIWSDDServiceName = "AdminAPI";

    public java.lang.String getAdminAPIWSDDServiceName() {
        return AdminAPIWSDDServiceName;
    }

    public void setAdminAPIWSDDServiceName(java.lang.String name) {
        AdminAPIWSDDServiceName = name;
    }

    public webservice.AdminAPI getAdminAPI() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AdminAPI_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAdminAPI(endpoint);
    }

    public webservice.AdminAPI getAdminAPI(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webservice.AdminAPISoapBindingStub _stub = new webservice.AdminAPISoapBindingStub(portAddress, this);
            _stub.setPortName(getAdminAPIWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAdminAPIEndpointAddress(java.lang.String address) {
        AdminAPI_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (webservice.AdminAPI.class.isAssignableFrom(serviceEndpointInterface)) {
                webservice.AdminAPISoapBindingStub _stub = new webservice.AdminAPISoapBindingStub(new java.net.URL(AdminAPI_address), this);
                _stub.setPortName(getAdminAPIWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("AdminAPI".equals(inputPortName)) {
            return getAdminAPI();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice", "AdminAPIService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice", "AdminAPI"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AdminAPI".equals(portName)) {
            setAdminAPIEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
