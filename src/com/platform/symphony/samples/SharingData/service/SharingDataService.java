///////////////////////////////////////////////////////////////////////////////
//
// This file is a part of the SharingData sample which uses the Symphony API
// to demonstate the use of data that is common to all tasks in a session. 
// This file contains code which is related to the service implemenation of
// the sample. 
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
package com.platform.symphony.samples.SharingData.service;

import com.platform.symphony.soam.*;
import com.platform.symphony.samples.SharingData.common.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;


public class SharingDataService extends ServiceContainer
{
    static int retVal = 0;
    boolean exflag = false;

    SharingDataService()
    {
        super();
    }

    public void onCreateService(ServiceContext serviceContext) throws SoamException
    {
        /********************************************************************
         * Do your service initialization here. 
         ********************************************************************/
	 myserviceContext = serviceContext;
    }

    public void onSessionEnter(SessionContext sessionContext) throws SoamException
    {
        /********************************************************************
         * Do your session-specific initialization here, when common data is
         * provided. 
         ********************************************************************/
	//MyOutput myOutput = new MyOutput();
	//StringBuffer sb = new StringBuffer();
        // Get the current session ID (if needed). 
        m_currentSID = sessionContext.getSessionId();

        // Populate our common data object. 
        m_commonData = (MyCommonData)sessionContext.getCommonData();
 	String path = "/home/erikwang/work/SharingData/test.log";
	//String path = "c:\\test.log";
	/*File file = new File(path);
	if(file.exists()){
	     myserviceContext.setControlCode(3);
	     exflag = true;
	     throw new FatalException("Path existed~~~");
	}else{
     	     try{
   	          file.createNewFile();
		  myserviceContext.setControlCode(2);
	     }catch(IOException e){
		     //sb.append("[Service] Exception when create File "+path);
		     e.printStackTrace();
	     }
	}*/
    }

    public void onInvoke (TaskContext taskContext) throws SoamException
    {
        /********************************************************************
         * Do your service logic here. This call applies to each task
         * submission. 
         ********************************************************************/


	// Populate our common data object.
	 //String path = "/home/erikwang/work/SharingData/test.log";
	 //String path = "c:\\test.log";
	 //System.out.println("[Service] File will be "+path);
	 //sb.append("[Service] File will be "+path);
	 //File file = new File(path);
	 //if(file.exists()){
	   // myserviceContext.setControlCode(2);
	   // throw new FatalException("Path existed~~~");
         //}else{
	//	 try{                                                                                                                                                                      file.createNewFile();
	//	      myserviceContext.setControlCode(2);
	//	 }catch(IOException e){
	//	      e.printStackTrace();
	//	 }
	//}	
	
	// Get the input that was sent from the client 
        /*
	if(exflag){
        	myserviceContext.setControlCode(3);
		throw new FatalException("Path existed~~~");
	}
	*/
    	MyInput myInput = (MyInput)taskContext.getTaskInput();

        MyOutput myOutput = new MyOutput();

        // Estimate and set our runtime. 
        Date date = new Date();
        myOutput.setRunTime(date.toString());
        
        int sleeptime = myInput.getSleeptime();
        String cmd = myInput.getCmd();

        // Echo the ID. 
        myOutput.setId(myInput.getId());
        StringBuffer sb = new StringBuffer();
    	
    	Runtime run = Runtime.getRuntime();  
    	sb.append("Command "+cmd+" will be done\n"); 
    	try {  
            Process p = run.exec(cmd);
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());  
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  
            sb.append("Doing command "+cmd+"\n"); 
            String lineStr;
            while ((lineStr = inBr.readLine()) != null)  
                sb.append("\n"+lineStr); 
            if (p.waitFor() != 0) {  
                if (p.exitValue() == 1)  
                    System.err.println("Command failed");
            }  
            inBr.close();  
            in.close();
        }catch (Exception e){
        	e.printStackTrace();
        	throw new FatalException("command "+e+" failed");
        }


        // Setup a reply to the client. 
        sb.append("Client sent : ");
        sb.append(myInput.getString());
        sb.append("\nSymphony replied : Hello Client !! with common data (\"");
        sb.append(m_commonData.getString());
        sb.append("\") for session(");
        sb.append(m_currentSID);
        sb.append("\nNow sleep <"+sleeptime+"> seconds...");
        sb.append(")");
        myOutput.setString(sb.toString());
        
        try{
        	Thread.sleep(sleeptime);
        }catch(Exception ex){
        	ex.printStackTrace();
        }

        // Set our output message 
        taskContext.setTaskOutput(myOutput);
    }

    public void onSessionLeave() throws SoamException
    {
        /********************************************************************
         * Do your session-specific uninitialization here, when common data
         * is provided. 
         ********************************************************************/

        // We get a chance to free the common data here. 
        m_currentSID = null;
        m_commonData = null;
    }

    public void onDestroyService() throws SoamException
    {
        /********************************************************************
         * Do your service uninitialization here. 
         ********************************************************************/
    }


    //=========================================================================
    //  Private Member Variables
    //=========================================================================

    private MyCommonData m_commonData;
    private String m_currentSID;
    private ServiceContext myserviceContext;
    private MyOutput myOutput;


    // Entry point to the service 
    public static void main(String args[])
    {
        // Return value of our service program 
        //int retVal = 0;
        try
        {
            /****************************************************************
             * Do not implement any service initialization before calling the
             * ServiceContainer.run() method. If any service initialization
             * needs to be done, implement the onCreateService() handler for
             * your service container. 
             ****************************************************************/

            // Create the container and run it 
            SharingDataService myContainer = new SharingDataService();	   
            //myContainer.serviceContext = myserviceContext;
	    myContainer.run();

            /****************************************************************
             * Do not implement any service uninitialization after calling
             * the ServiceContainer.run() method. If any service
             * uninitialization needs to be done, implement the
             * onDestroyService() handler for your service container since
             * there is no guarantee that the remaining code in main() will
             * be executed after calling ServiceContainer::run(). Also, in
             * some cases, the remaining code can even cause an orphan
             * service instance if the code cannot be finished. 
             ****************************************************************/
        }
        catch (Exception ex)
        {
            // Report the exception to stdout 
            System.out.println("Exception caught:");
            System.out.println(ex.toString());
            retVal = -1;
        }

        /********************************************************************
         * NOTE: Although our service program will return an overall failure
         * or success code it will always be ignored in the current revision
         * of the middleware. The value being returned here is for
         * consistency. 
         ********************************************************************/
        retVal = 100;
	System.exit(retVal);
    }
}
