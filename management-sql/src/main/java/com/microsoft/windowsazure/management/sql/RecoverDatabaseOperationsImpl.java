/**
 * 
 * Copyright (c) Microsoft and contributors.  All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

// Warning: This code was generated by a tool.
// 
// Changes to this file may cause incorrect behavior and will be lost if the
// code is regenerated.

package com.microsoft.windowsazure.management.sql;

import com.microsoft.windowsazure.AzureHttpStatus;
import com.microsoft.windowsazure.core.ServiceOperations;
import com.microsoft.windowsazure.core.utils.BOMInputStream;
import com.microsoft.windowsazure.core.utils.XmlUtility;
import com.microsoft.windowsazure.exception.ServiceException;
import com.microsoft.windowsazure.management.sql.models.RecoverDatabaseOperation;
import com.microsoft.windowsazure.management.sql.models.RecoverDatabaseOperationCreateParameters;
import com.microsoft.windowsazure.management.sql.models.RecoverDatabaseOperationCreateResponse;
import com.microsoft.windowsazure.tracing.CloudTracing;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
* Contains the operation to create recovery requests for Azure SQL Databases.
*/
public class RecoverDatabaseOperationsImpl implements ServiceOperations<SqlManagementClientImpl>, RecoverDatabaseOperations {
    /**
    * Initializes a new instance of the RecoverDatabaseOperationsImpl class.
    *
    * @param client Reference to the service client.
    */
    RecoverDatabaseOperationsImpl(SqlManagementClientImpl client) {
        this.client = client;
    }
    
    private SqlManagementClientImpl client;
    
    /**
    * Gets a reference to the
    * microsoft.windowsazure.management.sql.SqlManagementClientImpl.
    * @return The Client value.
    */
    public SqlManagementClientImpl getClient() {
        return this.client;
    }
    
    /**
    * Issues a recovery request for an Azure SQL Database.
    *
    * @param targetServerName Required. The name of the Azure SQL Database
    * Server on which to recover the source database.
    * @param parameters Required. Additional parameters for the Create Recover
    * Database Operation request.
    * @return Contains the response to the Create Recover Database Operation
    * request.
    */
    @Override
    public Future<RecoverDatabaseOperationCreateResponse> createAsync(final String targetServerName, final RecoverDatabaseOperationCreateParameters parameters) {
        return this.getClient().getExecutorService().submit(new Callable<RecoverDatabaseOperationCreateResponse>() { 
            @Override
            public RecoverDatabaseOperationCreateResponse call() throws Exception {
                return create(targetServerName, parameters);
            }
         });
    }
    
    /**
    * Issues a recovery request for an Azure SQL Database.
    *
    * @param targetServerName Required. The name of the Azure SQL Database
    * Server on which to recover the source database.
    * @param parameters Required. Additional parameters for the Create Recover
    * Database Operation request.
    * @throws MalformedURLException Thrown in case of an invalid request URL
    * @throws ProtocolException Thrown if invalid request method
    * @throws ParserConfigurationException Thrown if there was an error
    * configuring the parser for the response body.
    * @throws SAXException Thrown if there was an error parsing the response
    * body.
    * @throws TransformerException Thrown if there was an error creating the
    * DOM transformer.
    * @throws ServiceException Thrown if an unexpected response is found.
    * @throws IOException Signals that an I/O exception of some sort has
    * occurred
    * @return Contains the response to the Create Recover Database Operation
    * request.
    */
    @Override
    public RecoverDatabaseOperationCreateResponse create(String targetServerName, RecoverDatabaseOperationCreateParameters parameters) throws MalformedURLException, ProtocolException, ParserConfigurationException, SAXException, TransformerException, ServiceException, IOException {
        // Validate
        if (targetServerName == null) {
            throw new NullPointerException("targetServerName");
        }
        if (parameters == null) {
            throw new NullPointerException("parameters");
        }
        if (parameters.getSourceDatabaseName() == null) {
            throw new NullPointerException("parameters.SourceDatabaseName");
        }
        
        // Tracing
        boolean shouldTrace = CloudTracing.getIsEnabled();
        String invocationId = null;
        if (shouldTrace) {
            invocationId = Long.toString(CloudTracing.getNextInvocationId());
            HashMap<String, Object> tracingParameters = new HashMap<String, Object>();
            tracingParameters.put("targetServerName", targetServerName);
            tracingParameters.put("parameters", parameters);
            CloudTracing.enter(invocationId, this, "createAsync", tracingParameters);
        }
        
        // Construct URL
        String baseUrl = this.getClient().getBaseUri().toString();
        String url = "/" + (this.getClient().getCredentials().getSubscriptionId() != null ? this.getClient().getCredentials().getSubscriptionId().trim() : "") + "/services/sqlservers/servers/" + targetServerName.trim() + "/recoverdatabaseoperations";
        // Trim '/' character from the end of baseUrl and beginning of url.
        if (baseUrl.charAt(baseUrl.length() - 1) == '/') {
            baseUrl = baseUrl.substring(0, (baseUrl.length() - 1) + 0);
        }
        if (url.charAt(0) == '/') {
            url = url.substring(1);
        }
        url = baseUrl + "/" + url;
        
        // Create HTTP transport objects
        URL serverAddress = new URL(url);
        HttpURLConnection httpRequest = ((HttpURLConnection) serverAddress.openConnection());
        httpRequest.setRequestMethod("POST");
        httpRequest.setDoOutput(true);
        
        // Set Headers
        httpRequest.setRequestProperty("Content-Type", "application/xml");
        httpRequest.setRequestProperty("x-ms-version", "2012-03-01");
        
        // Set Credentials
        this.getClient().getCredentials().processRequest(httpRequest);
        
        // Serialize Request
        String requestContent = null;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document requestDoc = documentBuilder.newDocument();
        
        Element serviceResourceElement = requestDoc.createElementNS("http://schemas.microsoft.com/windowsazure", "ServiceResource");
        requestDoc.appendChild(serviceResourceElement);
        
        Element sourceDatabaseNameElement = requestDoc.createElementNS("http://schemas.microsoft.com/windowsazure", "SourceDatabaseName");
        sourceDatabaseNameElement.appendChild(requestDoc.createTextNode(parameters.getSourceDatabaseName()));
        serviceResourceElement.appendChild(sourceDatabaseNameElement);
        
        if (parameters.getSourceServerName() != null) {
            Element sourceServerNameElement = requestDoc.createElementNS("http://schemas.microsoft.com/windowsazure", "SourceServerName");
            sourceServerNameElement.appendChild(requestDoc.createTextNode(parameters.getSourceServerName()));
            serviceResourceElement.appendChild(sourceServerNameElement);
        }
        
        if (parameters.getTargetDatabaseName() != null) {
            Element targetDatabaseNameElement = requestDoc.createElementNS("http://schemas.microsoft.com/windowsazure", "TargetDatabaseName");
            targetDatabaseNameElement.appendChild(requestDoc.createTextNode(parameters.getTargetDatabaseName()));
            serviceResourceElement.appendChild(targetDatabaseNameElement);
        }
        
        DOMSource domSource = new DOMSource(requestDoc);
        StringWriter stringWriter = new StringWriter();
        StreamResult streamResult = new StreamResult(stringWriter);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(domSource, streamResult);
        requestContent = stringWriter.toString();
        httpRequest.setRequestProperty("Content-Type", "application/xml");
        
        // Send Request
        try {
            httpRequest.setFixedLengthStreamingMode(requestContent.getBytes().length);
            httpRequest.getOutputStream().write(requestContent.getBytes());
            int statusCode = httpRequest.getResponseCode();
            if (statusCode != AzureHttpStatus.CREATED) {
                ServiceException ex = ServiceException.createFromXml(requestContent, httpRequest.getResponseMessage(), httpRequest.getResponseCode(), httpRequest.getContentType(), httpRequest.getInputStream());
                if (shouldTrace) {
                    CloudTracing.error(invocationId, ex);
                }
                throw ex;
            }
            
            // Create Result
            RecoverDatabaseOperationCreateResponse result = null;
            // Deserialize Response
            InputStream responseContent = httpRequest.getInputStream();
            result = new RecoverDatabaseOperationCreateResponse();
            DocumentBuilderFactory documentBuilderFactory2 = DocumentBuilderFactory.newInstance();
            documentBuilderFactory2.setNamespaceAware(true);
            DocumentBuilder documentBuilder2 = documentBuilderFactory2.newDocumentBuilder();
            Document responseDoc = documentBuilder2.parse(new BOMInputStream(responseContent));
            
            Element serviceResourceElement2 = XmlUtility.getElementByTagNameNS(responseDoc, "http://schemas.microsoft.com/windowsazure", "ServiceResource");
            if (serviceResourceElement2 != null) {
                RecoverDatabaseOperation serviceResourceInstance = new RecoverDatabaseOperation();
                result.setOperation(serviceResourceInstance);
                
                Element requestIDElement = XmlUtility.getElementByTagNameNS(serviceResourceElement2, "http://schemas.microsoft.com/windowsazure", "RequestID");
                if (requestIDElement != null) {
                    String requestIDInstance;
                    requestIDInstance = requestIDElement.getTextContent();
                    serviceResourceInstance.setId(requestIDInstance);
                }
                
                Element sourceServerNameElement2 = XmlUtility.getElementByTagNameNS(serviceResourceElement2, "http://schemas.microsoft.com/windowsazure", "SourceServerName");
                if (sourceServerNameElement2 != null) {
                    String sourceServerNameInstance;
                    sourceServerNameInstance = sourceServerNameElement2.getTextContent();
                    serviceResourceInstance.setSourceServerName(sourceServerNameInstance);
                }
                
                Element sourceDatabaseNameElement2 = XmlUtility.getElementByTagNameNS(serviceResourceElement2, "http://schemas.microsoft.com/windowsazure", "SourceDatabaseName");
                if (sourceDatabaseNameElement2 != null) {
                    String sourceDatabaseNameInstance;
                    sourceDatabaseNameInstance = sourceDatabaseNameElement2.getTextContent();
                    serviceResourceInstance.setSourceDatabaseName(sourceDatabaseNameInstance);
                }
                
                Element targetDatabaseNameElement2 = XmlUtility.getElementByTagNameNS(serviceResourceElement2, "http://schemas.microsoft.com/windowsazure", "TargetDatabaseName");
                if (targetDatabaseNameElement2 != null) {
                    String targetDatabaseNameInstance;
                    targetDatabaseNameInstance = targetDatabaseNameElement2.getTextContent();
                    serviceResourceInstance.setTargetDatabaseName(targetDatabaseNameInstance);
                }
                
                Element nameElement = XmlUtility.getElementByTagNameNS(serviceResourceElement2, "http://schemas.microsoft.com/windowsazure", "Name");
                if (nameElement != null) {
                    String nameInstance;
                    nameInstance = nameElement.getTextContent();
                    serviceResourceInstance.setName(nameInstance);
                }
                
                Element typeElement = XmlUtility.getElementByTagNameNS(serviceResourceElement2, "http://schemas.microsoft.com/windowsazure", "Type");
                if (typeElement != null) {
                    String typeInstance;
                    typeInstance = typeElement.getTextContent();
                    serviceResourceInstance.setType(typeInstance);
                }
                
                Element stateElement = XmlUtility.getElementByTagNameNS(serviceResourceElement2, "http://schemas.microsoft.com/windowsazure", "State");
                if (stateElement != null) {
                    String stateInstance;
                    stateInstance = stateElement.getTextContent();
                    serviceResourceInstance.setState(stateInstance);
                }
            }
            
            result.setStatusCode(statusCode);
            result.setRequestId(httpRequest.getHeaderField("x-ms-request-id"));
            
            if (shouldTrace) {
                CloudTracing.exit(invocationId, result);
            }
            return result;
        } finally {
            if (httpRequest != null) {
                httpRequest.disconnect();
            }
        }
    }
}
