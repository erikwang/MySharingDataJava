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

import java.io.Serializable;


public class MyOutput implements Serializable
{
    //=========================================================================
    //  Constructor
    //=========================================================================

    public MyOutput()
    {
        super();
        m_id = 0;
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

    public String getRunTime()
    {
        return m_runTime;
    }

    public void setRunTime(String runTime)
    {
        m_runTime = runTime;
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
    private String m_runTime;
    private String m_string;
    private static final long serialVersionUID = 2L;
}
