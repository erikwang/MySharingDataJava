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
import com.platform.symphony.samples.SampleApp.common.*;


public class SharingDataClient
{
    public static void main(String args[])
    {
        try
        {
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
            try
            {
                // Connect to the specified application. 
                connection = SoamFactory.connect(appName, securityCB);

                // Retrieve and print our connection ID. 
                System.out.println("connection ID=" + connection.getId());

                // Set up our common data to be shared by all task
                // invocations within this session
                com.platform.symphony.samples.SharingData.common.MyCommonData commonData = new com.platform.symphony.samples.SharingData.common.MyCommonData("Common Data To Be Shared");

                // Set up session creation attributes. 
                SessionCreationAttributes attributes = new SessionCreationAttributes();
                attributes.setSessionName("mySession");
                attributes.setSessionType("SessionType1");
                attributes.setSessionFlags(Session.SYNC);
                attributes.setCommonData(commonData);
								
		SessionCreationAttributes attributes2 = new SessionCreationAttributes();
                attributes2.setSessionName("mySession2");
                attributes2.setSessionType("SessionType2");
                attributes2.setSessionFlags(Session.SYNC);
                
                // Create a session with the provided attributes. 
                Session session = null;
		Session session2 = null;
                try
                {
                    session = connection.createSession(attributes);

                    // Retrieve and print session ID. 
                    System.out.println("Session ID:" + session.getId());

                    // Now we will send some messages to our service. 
                    int tasksToSend = 5;
                    for (int taskCount = 0; taskCount < tasksToSend; taskCount++)
                    {
                        // Create a message. 
                        com.platform.symphony.samples.SharingData.common.MyInput myInput = new com.platform.symphony.samples.SharingData.common.MyInput(taskCount, "Hello Grid !!");

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
                    EnumItems enumOutput = session.fetchTaskOutput(tasksToSend);
                
                    // Inspect results. 
                    TaskOutputHandle output = enumOutput.getNext();
                    while(output != null)
                    {
                        // Check for success of task. 
                        if (output.isSuccessful())
                        {
                            // Get the message returned from the service. 
                            com.platform.symphony.samples.SharingData.common.MyOutput myOutput = (com.platform.symphony.samples.SharingData.common.MyOutput)output.getTaskOutput();

                            // Display content of reply. 
                            System.out.println("\nTask Succeeded [" +  output.getId() + "]");
                            System.out.println("Your Internal ID was : " + myOutput.getId());
                            System.out.println("Estimated runtime was recorded as : " + myOutput.getRunTime());
                            System.out.println(myOutput.getString());
                        }
                        else
                        {
                            // Get the exception associated with this task. 
                            SoamException ex = output.getException();
                            System.out.println("Task Not Successful : ");
                            System.out.println(ex.toString());
                        }
                        output = enumOutput.getNext();
					}						
                    // Now get our results - will block here until all tasks
                    // retrieved. 
                   
			session2 = connection.createSession(attributes2);
		    // Retrieve and print session ID. 
                    System.out.println("[2nd session]Session ID:" + session2.getId());

                    // Now we will send some messages to our service. 
                    int tasksToSend2 = 5;
                    for (int taskCount2 = 0; taskCount2 < tasksToSend2; taskCount2++)
                    {
                        // Create a message
                        com.platform.symphony.samples.SampleApp.common.MyInput myInput2 = new com.platform.symphony.samples.SampleApp.common.MyInput(taskCount2, "Erik Wang from client says: Hello Grid !!");

                        // Set task submission attributes
                        TaskSubmissionAttributes taskAttr2 = new TaskSubmissionAttributes();
                        taskAttr2.setTaskInput(myInput2);

                        // Send it
                        TaskInputHandle input2 = session2.sendTaskInput(taskAttr2);

                        // Retrieve and print task ID
                        System.out.println("task submitted with ID : " + input2.getId());
                    }

                    // Now get our results - will block here until all tasks retrieved
                    EnumItems enumOutput2 = session2.fetchTaskOutput(tasksToSend2);

                    // Inspect results
                    TaskOutputHandle output2 = enumOutput2.getNext();
                    while (output2 != null)
                    {
                        // Check for success of task
                        if (output2.isSuccessful())
                        {
                            // Get the message returned from the service
                            com.platform.symphony.samples.SampleApp.common.MyOutput myOutput2 = (com.platform.symphony.samples.SampleApp.common.MyOutput)output2.getTaskOutput();

                            // Display content of reply
                            System.out.println("\nTask Succeeded [" +  output2.getId() + "]");
                            System.out.println("Your Internal ID was : " + myOutput2.getId());
                            System.out.println("Estimated runtime was recorded as : " + myOutput2.getRunTime());
                            System.out.println("Erik heard echo from Grid:"+myOutput2.getString());
                        }
                        else
                        {
                            // Get the exception associated with this task
                            SoamException ex = output2.getException();
                            System.out.println("Task Not Successful : ");
                            System.out.println(ex.toString());
                        }
                        output2 = enumOutput2.getNext();
                    }
			}


                finally
                {
                    /********************************************************
                     * Mandatory: Close session. 
                     ********************************************************/
                    if (session != null)
                    {
                        session.close();
                        System.out.println("Session closed");
                    }
					if (session2 != null)
                    {
                        session2.close();
                        System.out.println("Session2 closed");
                    }
                }
            }
            finally
            {
                /************************************************************
                 * Mandatory: Close connection. 
                 ************************************************************/
                if (connection != null)
                {
                    connection.close();
                    System.out.println("Connection closed");
                }
            }
        }
        catch (Exception ex)
        {
            // Report exception.
            System.out.println("exception caught ... ");
            System.out.println(ex.toString());
        }
        finally
        {
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
