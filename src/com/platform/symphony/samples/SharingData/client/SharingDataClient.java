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


public class SharingDataClient{
    public static void main(String args[]){
        final String sessionType;
        final int sleeptime;
        final int numOfTask;
        final String cmd;
        
        //args[]
        //0 - sessionType, defined in application profile
        //1 - sleeptime
        //2 - number of tasks
        //3 - cmd to be executed in service
        
        sessionType = args[0];
        sleeptime =  new Integer(args[1]).intValue();
        numOfTask = new Integer(args[2]).intValue();
        cmd = args[3];
    	
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
                
                
                SessionCreationAttributes attributes = new SessionCreationAttributes();
                attributes.setSessionName("mySession");
                attributes.setSessionType(sessionType);
                attributes.setSessionFlags(Session.SYNC);
                attributes.setCommonData(commonData);

                // Create a session with the provided attributes. 
                Session session = null;
                
                try{
                    session = connection.createSession(attributes);

                    // Retrieve and print session ID. 
                    System.out.println("Session ID:" + session.getId());

                    // Now we will send some messages to our service. 
                    for (int taskCount = 0; taskCount < numOfTask; taskCount++){
                        // Create a message. 
                        MyInput myInput = new MyInput(taskCount, "Hello Grid !!");
                        myInput.setSleeptime(sleeptime);
                        
                        myInput.setCmd(cmd);
                        System.out.println("CMD is"+ cmd);
                        
                        // Create task attributes. 
                        TaskSubmissionAttributes taskAttr = new TaskSubmissionAttributes();
                        taskAttr.setTaskInput(myInput);

                        // Send it. 
                        TaskInputHandle input = session.sendTaskInput(taskAttr);

                        // Retrieve and print task ID. 
                        System.out.println("task submitted with ID : " + input.getId());
                    }
 
                    // Now get our results - will block here until all tasks
                    // retrieved. 
                    EnumItems enumOutput = session.fetchTaskOutput(numOfTask);
                
                    // Inspect results. 
                    TaskOutputHandle output = enumOutput.getNext();
                    while(output != null){
                        // Check for success of task. 
                        if (output.isSuccessful()){
                            // Get the message returned from the service. 
                            MyOutput myOutput = (MyOutput)output.getTaskOutput();

                            // Display content of reply. 
                            System.out.println("\nTask Succeeded [" +  output.getId() + "]");
                            System.out.println("Your Internal ID was : " + myOutput.getId());
                            System.out.println("Estimated runtime was recorded as : " + myOutput.getRunTime());
                            System.out.println(myOutput.getString());
                        }else{
                            // Get the exception associated with this task. 
                            SoamException ex = output.getException();
                            System.out.println("Task Not Successful : ");
                            System.out.println(ex.toString());
                        }
                        output = enumOutput.getNext();
					}						
                    // Now get our results - will block here until all tasks
                    // retrieved. 
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
