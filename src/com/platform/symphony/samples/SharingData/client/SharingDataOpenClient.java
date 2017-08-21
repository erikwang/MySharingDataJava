///////////////////////////////////////////////////////////////////////////////
//
// This file is a part of the SharingData sample which uses the Symphony API
// to demonstate the use of data that is common to all tasks in a session. 
// This file contains code which demonstrates the use of the Symphony API
// within the client for this sample. 
//
//
// Copyright International Business Machines Corp, 1992-2013. US Government
// Users Restricted Rights - Use, duplication or disclosure restricted by GSA
// ADP Schedule Contract with IBM Corp. 
// Accelerating Intelligence(TM). All rights reserved. 
//
// This exposed source code is the confidential and proprietary property of
// IBM Corporation. Your right to use is strictly limited by the terms of the
// license agreement entered into with IBM Corporation. 
//
/////////////////////////////////////////////////////////////////////////////// 
 
package com.platform.symphony.samples.SharingData.client;

import com.platform.symphony.soam.*;
import com.platform.symphony.samples.SharingData.common.*;


public class SharingDataOpenClient{
    public static void main(String args[]){
        final int sessionid;
        
        //args[]
        //0 - sessionID
       
        
        sessionid = new Integer(args[0]).intValue();
    	
    	try{
            /****************************************************************
             * We should initialize the API before using any API calls. 
             ****************************************************************/
            SoamFactory.initialize();

            // Set up application specific information to be supplied to
            // Symphony. 
            String appName = "SharingDataJava";

            // Set up application authentication information using the
            // default security provider. Ensure it exists for the lifetime
            // of the connection. 
            DefaultSecurityCallback securityCB = new DefaultSecurityCallback("Guest", "Guest");
            Connection connection = null;
            try{
                // Connect to the specified application. 
                connection = SoamFactory.connect(appName, securityCB);

                // Retrieve and print our connection ID. 
                System.out.println("connection ID=" + connection.getId());

                // Set up our common data to be shared by all task
                // invocations within this session
                MyCommonData commonData = new MyCommonData("Common Data To Be Shared");

                // Set up session creation attributes. 
                
                
                /*SessionCreationAttributes attributes = new SessionCreationAttributes();
                attributes.setSessionName("mySession");
                attributes.setSessionType(sessionType);
                attributes.setSessionFlags(Session.SYNC);
                attributes.setCommonData(commonData);
                attributes.setSessionPriority(1);*/
                
                SessionOpenAttributes oattribute = new SessionOpenAttributes();
                oattribute.setSessionId(""+sessionid);
                oattribute.setSessionName("mySession");
                                           
                // Create a session with the provided attributes. 
                Session session = null;
                
                try{
                    //session = connection.createSession(attributes);
                	session = connection.openSession(oattribute);
                    
                    // Retrieve and print session ID. 
                    System.out.println("Session ID:" + session.getId());
                } 
			
                finally{
                    /********************************************************
                     * Mandatory: Close session. 
                     ********************************************************/
                    if (session != null){
                        session.close();
                        System.out.println("Session closed");
                    }
                }
            }
            finally{
                /************************************************************
                 * Mandatory: Close connection. 
                 ************************************************************/
                if (connection != null){
                    connection.close();
                    System.out.println("Connection closed");
                }
            }
        }
        catch (Exception ex){
            // Report exception.
            System.out.println("exception caught ... ");
            System.out.println(ex.toString());
        }
        
    	finally{
            /****************************************************************
             * It is important that we always uninitialize the API. This is
             * the only way to ensure proper shutdown of the interaction
             * between the client and the system. 
             ****************************************************************/
            SoamFactory.uninitialize();
            System.out.println("All Done !!");
        }
    }
}