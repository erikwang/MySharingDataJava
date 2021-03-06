#!/bin/bash
# Runcom.platform.symphony.samples.SharingData.service.SharingDataService.sh
###############################################################################
#
# This file contains a template that can be used to execute the 
# "com.platform.symphony.samples.SampleApp.service.MyService" class.
#
# Copyright International Business Machines Corp, 1992-2013. US Government
# Users Restricted Rights - Use, duplication or disclosure restricted by GSA
# ADP Schedule Contract with IBM Corp.  
# Accelerating Intelligence(TM). All rights reserved. 
#
# NOTE: You should read the additional comments embedded in this file to 
# get further details on the variables used in this script. This file
# can be used to execute the class on UNIX hosts.
#
###############################################################################

###################
# 1. You do not need to modify JAVA_SDK_JARFILE.
# It has already been set by the Symphony Eclipse IDE pluggin. 
###################
JAVA_SDK_JARFILE=$SOAM_HOME/7.1/$EGO_MACHINE_TYPE/lib/JavaSoamApi.jar

###################
# 2. This is where we put your dependent JARs containing all the implementation 
# required for your Application to function.
###################
SOAM_APP_DEP_JARFILES=$SOAM_DEPLOY_DIR/SharingDataServiceJava.jar

###################
# 3. This is the main class used to start the Application code.
###################
SOAM_SERVICE_MAINCLASS=com.platform.symphony.samples.SharingData.service.SharingDataService

###################
# 4. Additional JVM options are specified here.
###################
JVM_OPTIONS=

###################
# Attention
# You do not need to modify the java start command.
# You can use the appropriate variable instead.
###################
java -classpath $JAVA_SDK_JARFILE:$SOAM_APP_JARFILES:$SOAM_APP_DEP_JARFILES $JVM_OPTIONS $SOAM_SERVICE_MAINCLASS
