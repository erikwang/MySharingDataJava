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

import java.util.ArrayList;
import java.util.List;
import com.platform.symphony.soam.*;
import com.platform.symphony.samples.SharingData.common.*;


//define a class for get args from submit command
class Option {
    String flag, opt;
    public Option(String flag, String opt) { this.flag = flag; this.opt = opt; }
    
    public String getflag(){
    	return this.flag;
    }
    
    public String getopt(){
    	return this.opt;
    }
}

public class SharingDataClient{
    public static void main(String args[]){
        String sessionType = null;
        String appName = null;
        int sleeptime = 0;
        int numOfTask = 0;
        int sessionid = 0;
        String username = null;
        String password = null;
        String fileurl = null;
        String cmd = null;
        boolean detach = false;
        int hmtaskid = 0;
        int memoryconsume = 100;
        String functionToThrowException = null;
        String functionToThrowUserException = null;
        String childProcess = null;
        String exitSI = null;
        
        List<Option> optsList = new ArrayList<Option>();
        
        //args[]
        //a - Indicate the application to connect, e.g. SharingDataJava - mandatory
        //s - Indicate sessionType, defined in application profile
        //r - Set sleeptime
        //m - Set number of tasks
        //u - Set username
        //x - Set password
        //c - A command to be executed in service instance
        //d - Use a file to simulate dummy common data
        //R - Session id to be re-connect to
        //Q - close the client if tasks are submitted to a new created session as DETACH_ON_CLOSE
        //C - Add additional M memory usage for a task
        //M - Additional memory usage for a task
        //e - Throw user defined fatal exception at call e.g. onSessionEnter - now it accepts "onInvoke"
        //E - Throw SOAM fatal exception at call e.g. onSessionEnter - now it accepts "onSessionEnter" and "onInvoke"
        //X - Let SI exit from a function - now it accepts "onInvoke"
        //P - Let SI spawn a child process. Currently it is a ping.exe, on Windows only.


        for (int t=0; t < args.length; t++){
        	switch (args[t].charAt(0)) {
            	case '-':
                    if (args[t].length() < 2){
                        throw new IllegalArgumentException("Not a valid argument: "+args[t]);
                    }else{
                    	if (args.length-1 == t){
                            throw new IllegalArgumentException("Expected arg after: "+args[t]);
                        }
                        // -opt
                        optsList.add(new Option(args[t], args[t+1]));
                        System.out.println("args = "+args[t]+";  value = "+args[t+1]);
                        switch (args[t].charAt(1)){
                        	
                        	case 'a':
                        		appName = args[t+1];
                        		break;
                        	case 's':
                        		sessionType = args[t+1];
                        		break;
                        	case 'r':
                        		try{
                        			sleeptime =  new Integer(args[t+1]).intValue();	
                        		}catch (Exception e){
                        			e.printStackTrace();
                        		}
                        		break;
                        	case 'm':
                        		numOfTask = new Integer(args[t+1]).intValue();
                        		break;
                        	case 'u':
                        		username = args[t+1];
                        		break;
                        	case 'x':
                        		password = args[t+1];
                        		break;
                        	case 'c':
                        		cmd = args[t+1];
                        		break;
                        	case 'd':
                        		fileurl = args[t+1];
                        		break;
                        	case 'R':
                        		sessionid = new Integer(args[t+1]).intValue();
                        		break;
                        	case 'Q':
                        		detach = new Boolean(args[t+1]);
                        		break;
                        	case 'C':
                        		hmtaskid = new Integer(args[t+1]).intValue();
                        		break;
                        	case 'M':
                        		memoryconsume = new Integer(args[t+1]).intValue();
                        		break;
                        	case 'E':
                        		functionToThrowException = args[t+1];
                        		break;
                        	case 'e':
                        		functionToThrowUserException = args[t+1];
                        		break;
                        	case 'P':
                        		childProcess = args[t+1];
                        		break;
                        	case 'X':
                        		exitSI = args[t+1];
                        		break;
                        }
                    }
                t++;
               }
        }
        
        //System.out.println("The args looks like:");
        //showOptsList(optsList);
           	
    	try{
            /****************************************************************
             * We should initialize the API before using any API calls. 
             ****************************************************************/
            SoamFactory.initialize();

            // Set up application specific information to be supplied to
            // Symphony. 
            //String appName = "SharingDataJava";

            // Set up application authentication information using the
            // default security provider. Ensure it exists for the lifetime
            // of the connection. 
            //DefaultSecurityCallback securityCB = new DefaultSecurityCallback("Guest", "Guest");
            DefaultSecurityCallback securityCB = new DefaultSecurityCallback(username, password);
            Connection connection = null;
            try{
                // Connect to the specified application. 
                connection = SoamFactory.connect(appName, securityCB);
                
                // Retrieve and print our connection ID. 
                System.out.println("connection ID=" + connection.getId());

                // Set up our common data to be shared by all task
                // invocations within this session
                SessionCreationAttributes attributes = new SessionCreationAttributes();
                
                if(fileurl != null){
                	MyCommonData commonData = new MyCommonData("Common Data To Be Shared");
                    commonData.makeDummy(fileurl);
                    System.out.println(fileurl+" has been load as dummy with size "+commonData.getDummysize());
                    attributes.setCommonData(commonData);
                    commonData.setFunctionToThrowException(functionToThrowException);
                    commonData.setString("The common data comes from a file "+fileurl);
                }

                // Set up session creation attributes. 
                attributes.setSessionName("mySession");
                attributes.setSessionType(sessionType);
                attributes.setSessionFlags(Session.SYNC);
                attributes.setSessionPriority(1);
                
                //Prepare for the session, either create a new one or open the existed one with session ID
                Session session = null;
                try{
                	if(sessionid != 0){
                		//connect to existed session
                		SessionOpenAttributes oattribute = new SessionOpenAttributes();
                		oattribute.setSessionId(""+sessionid);
                		session = connection.openSession(oattribute);
                	}else{
                		//Create a session with the provided attributes.
                		session = connection.createSession(attributes);
                		
                		//Sent tasks to the session if it is a new one
                		for (int taskCount = 0; taskCount < numOfTask; taskCount++){
                            // Create a message. 
                            //System.out.println("I am going to load a file from "+fileurl+" into Input object");
                        	MyInput myInput = new MyInput(taskCount, "Hello Grid !!",fileurl);
                            myInput.setSleeptime(sleeptime);
                            myInput.setHmtaskid(hmtaskid);
                            myInput.setMemoryconsume(memoryconsume);
                            myInput.setFunctionToThrowException(functionToThrowException);
                            myInput.setFunctionToThrowUserException(functionToThrowUserException);
                            myInput.setChildProcess(childProcess);
                            myInput.setCmd(cmd);
                            myInput.setExitSI(exitSI);

                            // Create task attributes. 
                            TaskSubmissionAttributes taskAttr = new TaskSubmissionAttributes();
                            taskAttr.setTaskInput(myInput);
                            
                            // Send it. 
                            TaskInputHandle input = session.sendTaskInput(taskAttr);

                            // Retrieve and print task ID. 
                            System.out.println("task submitted with ID : " + input.getId());
                        }
                	}
                                                         
                    // Retrieve and print session ID. 
                    System.out.println("Session ID:" + session.getId());
                    
                    if (detach){
                    	session.close(SessionCloseFlags.DETACH_ON_CLOSE);
                    	//session.close();
                    	System.out.println("Successfully detached from session " + session.getId());
                        session = null;
                    }else{
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
            ex.printStackTrace();
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
    
    public static void showOptsList(List<Option> list){
    	for (int i = 0; i < list.size(); i++){
    		System.out.println(list.get(i).getflag());
    		System.out.println(list.get(i).getopt());
    	}
    }
}