/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package jaxrs2x.clientProps.fat.keepAlive;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.junit.Test;

import componenttest.app.FATServlet;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/KeepAliveTimeoutTestServlet")
public class KeepAliveTimeoutTestServlet extends FATServlet {

    private Client client;

    @Override
    public void init() throws ServletException {
//        final Bus cxfBus = BusFactory.getDefaultBus();
//        cxfBus.setProperty("org.apache.cxf.transport.http.async.CONNECTION_TTL",
//                           7000);
        ClientBuilder cb = ClientBuilder.newBuilder();
//        cb.property("org.apache.cxf.transport.http.async.CONNECTION_TTL", 7000);
//        cb.property("com.ibm.ws.jaxrs.client.keepalive.connection", "close");
//        cb.property("com.ibm.ws.jaxrs.client.connection.timeout", 2000);
//        cb.property("com.ibm.ws.jaxrs.client.receive.timeout", 2000);
        client = cb.build();
    }

    @Override
    public void destroy() {
        client.close();
    }

    @Test
    public void testTimeout(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        long startTime = System.nanoTime();
        try { 
//            Response r = target(req).request().header("Keep-Alive", "timeout=2000,max=10").get();
//            Response r = target(req).request().header("Expect-Connection", "keep-alive").header("Keep-Alive2", "timeout=2000,max=10").get();
            Response r = target(req).request().header("Connection", "keep-alive").header("Expect-Connection", "kelp-alive").header("Keep-Alive", "timeout=2000,max=10").get();
        } catch (ProcessingException e) {
            System.out.println("Jim.... time= " + (((System.nanoTime() - startTime)) / 1000000));
        }
 //       assertEquals("success", r.readEntity(String.class));
    }
    
    private WebTarget target(HttpServletRequest request) {
        String base = "http://" + request.getServerName() + ':' + request.getServerPort() + "/keepAliveApp/rest/test/timeout%20today";
        return client.target(base);
    }

}