///////////////////////////////////////////////////////////////////////////////
//
// This file contains code which demonstrates the use of the Symphony API
// within a service. 
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

package com.platform.symphony.samples.SampleApp.service;

import com.platform.symphony.soam.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import com.platform.symphony.samples.SharingData.common.*;

public class MyService extends ServiceContainer
{
    MyService()
    {
        super();
    }


    public void onCreateService(ServiceContext serviceContext) throws SoamException
    {
        /********************************************************************
         * Do your service initialization here. 
         ********************************************************************/
    }

    public void onSessionEnter(SessionContext sessionContext) throws SoamException
    {
        /********************************************************************
         * Do your session-specific initialization here, when common data is
         * provided. 
         ********************************************************************/
//	 throw new SoamException("Erik Exception - onSessionEnter");
/*
 String path = "~/work/test.log";
 System.out.println("[Service] File will be "+path);
 File file = new File(path);
 System.out.println("[Service] File was newed");
 if(file.exists()){
 	System.out.println("Path exists, throw Fatal Exception");
	System.exit(2);
	throw new FatalException("Path exists");
 }else{
        System.out.println("[Service] File "+path+" deosn't exist, will try to create it.");
        try{
            file.createNewFile();
        }catch(IOException e){
            System.out.println("[Service] Exception when create File "+path);
            e.printStackTrace();
        }
        System.out.println("Path doesn't exists");
        System.exit(2);
}
*/
//
//
//

    }

    public void onInvoke (TaskContext taskContext) throws SoamException
    {
        /********************************************************************
         * Do your service logic here. This call applies to each task
         * submission. 
         ********************************************************************/
	
        // Get the input that was sent from the client 
        MyInput myInput = (MyInput)taskContext.getTaskInput();
        MyOutput myOutput = new MyOutput();

        // estimate and set our runtime
        Date date = new Date();
        myOutput.setRunTime(date.toString());
        
        int sleeptime = myInput.getSleeptime();
        
        // We simply echo the data back to the client 
        myOutput.setId(myInput.getId());
        StringBuffer sb = new StringBuffer();
        sb.append("[Sample Service]Client sent : ");
        sb.append(myInput.getString());
        sb.append("\n[Sample Service] - By listening a voice from client, Symphony replied : Hello Client !!");
	        myOutput.setString(sb.toString());

        // Set our output message 
        taskContext.setTaskOutput(myOutput);

	//Erik edit: add 1 s of sleep - keep slots
	try{
		Thread.sleep(sleeptime);
	}catch(InterruptedException ex){
		
	}
    }

    public void onSessionLeave() throws SoamException
    {
        /********************************************************************
         * Do your session-specific uninitialization here, when common data
         * is provided. 
         ********************************************************************/
    }

    public void onDestroyService() throws SoamException
    {
        /********************************************************************
         * Do your service uninitialization here. 
         ********************************************************************/
    }

    public void onApplicationAttach(ServiceContext serviceContext) throws SoamException
    {
    }
    public void onApplicationDetach() throws SoamException
    {
    }

    // Entry point to the service 
    public static void main(String args[])
    {
        // Return value of our service program 
        int retVal = 0;
        try
        {
            /****************************************************************
             * Do not implement any service initialization before calling the
             * ServiceContainer.run() method. If any service initialization
             * needs to be done, implement the onCreateService() handler for
             * your service container. 
             ****************************************************************/

            // Create the container and run it 
            MyService myContainer = new MyService();
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
