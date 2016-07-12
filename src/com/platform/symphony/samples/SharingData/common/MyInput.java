///////////////////////////////////////////////////////////////////////////////
//
// This file is a part of the SharingData sample which uses the Symphony API
// to demonstate the use of data that is common to all tasks in a session. 
// This file contains code which is common to the service and client
// implemenation of the sample. 
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
 
package com.platform.symphony.samples.SharingData.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;


public class MyInput implements Serializable
{
    //=========================================================================
    //  Constructors
    //=========================================================================

    public MyInput()
    {
        super();
        m_id = 0;
    }

    public MyInput(int id, String string, String fileurl)
    {
        super();
        m_id = id;
        m_string = string;
        makeDummy(fileurl);
    }


    //=========================================================================
    //  Accessors and Mutators
    //=========================================================================
    
    public int getId()
    {
        return m_id;
    }

    public void setId(int id)
    {
        m_id = id;
    }

    public String getString()
    {
        return m_string;
    }
    
    public void setString(String string)
    {
        m_string = string;
    }


    //=========================================================================
    //  Private Member Variables
    //=========================================================================

    private int m_id;
    private String m_string;
    private static final long serialVersionUID = 2L;
    private int sleeptime;
    private String cmd;
    public String dummy;
	private int hmtaskid;
    private int memoryconsume;
    private String functionToThrowException;
    
    public String getFunctionToThrowException() {
		return functionToThrowException;
	}

	public void setFunctionToThrowException(String functionToThrowException) {
		this.functionToThrowException = functionToThrowException;
	}

	public int getHmtaskid() {
		return hmtaskid;
	}

	public void setHmtaskid(int hmtaskid) {
		this.hmtaskid = hmtaskid;
	}

	public String getDummy() {
		return dummy;
	}

	public void setDummy(String dummy) {
		this.dummy = dummy;
	}

	public int getMemoryconsume() {
		return memoryconsume;
	}

	public void setMemoryconsume(int memoryconsume) {
		this.memoryconsume = memoryconsume;
	}
    
	public int getSleeptime() {
		return sleeptime;
	}

	public void setSleeptime(int sleeptime) {
		this.sleeptime = sleeptime;
	}

	
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	
	public String getCmd() {
		// TODO Auto-generated method stub
		return cmd;
	}
	
	public void makeDummy(String fileurl){
		
		File f = new File(fileurl);
		//the input.dat is the file to load into memory.
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream((int) f.length());
			byte[] block = new byte[4096];
			int readAmount = 0;
			while((readAmount = fis.read(block)) >= 0) {
				if(readAmount> 0) {
					buffer.write(block, 0, readAmount);
				}

			}
			buffer.flush();
			buffer.close();
			fis.close();
			byte[] fileData = buffer.toByteArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
