/**
 * ClientAPIServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public class ClientAPIServiceLocator extends org.apache.axis.client.Service implements webservice.ClientAPIService {

    public ClientAPIServiceLocator() {
    }


    public ClientAPIServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ClientAPIServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ClientAPI
    private java.lang.String ClientAPI_address = "http://localhost:8080/Projet3/services/ClientAPI";

    public java.lang.String getClientAPIAddress() {
        return ClientAPI_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ClientAPIWSDDServiceName = "ClientAPI";

    public java.lang.String getClientAPIWSDDServiceName() {
        return ClientAPIWSDDServiceName;
    }

    public void setClientAPIWSDDServiceName(java.lang.String name) {
        ClientAPIWSDDServiceName = name;
    }

    public webservice.ClientAPI getClientAPI() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ClientAPI_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getClientAPI(endpoint);
    }

    public webservice.ClientAPI getClientAPI(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webservice.ClientAPISoapBindingStub _stub = new webservice.ClientAPISoapBindingStub(portAddress, this);
            _stub.setPortName(getClientAPIWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setClientAPIEndpointAddress(java.lang.String address) {
        ClientAPI_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (webservice.ClientAPI.class.isAssignableFrom(serviceEndpointInterface)) {
                webservice.ClientAPISoapBindingStub _stub = new webservice.ClientAPISoapBindingStub(new java.net.URL(ClientAPI_address), this);
                _stub.setPortName(getClientAPIWSDDServiceName());
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
        if ("ClientAPI".equals(inputPortName)) {
            return getClientAPI();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice", "ClientAPIService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice", "ClientAPI"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ClientAPI".equals(portName)) {
            setClientAPIEndpointAddress(address);
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
