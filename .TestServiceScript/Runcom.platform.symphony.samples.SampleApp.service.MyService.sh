#!/bin/bash
# Runcom.platform.symphony.samples.SampleApp.service.MyService.sh
###############################################################################
#
# This file contains a template that can be used to execute the 
# "com.platform.symphony.samples.SampleApp.service.MyService" class.
#
# This file was generated by the Symphony Code Generation Tool 
# version 6.1.1.0 at Fri Dec 05 16:34:28 2014
#
# Copyright International Business Machines Corp, 1992-2013.
# US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
# Accelerating Intelligence(TM). All rights reserved.
# 
# This file will be re-generated every time the packaging wizard is run from
# within the Symphony Eclipse Pluggin.
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
if [$EGO_MACHINE_TYPE = ""]; then
    EGO_MACHINE_TYPE=w2k3_x64-vc7-psdk
fi
JAVA_SDK_JARFILE="$SOAM_HOME/6.1.1/$EGO_MACHINE_TYPE/lib/JavaSoamApi.jar"

###################
# 2. This is where we put your dependent JARs containing all the implementation 
# required for your Application to function.
###################
SOAM_APP_DEP_JARFILES="$SOAM_DEPLOY_DIR/com.platform.symphony.samples.SampleApp.anotherservice.jar:$SOAM_DEPLOY_DIR/com.platform.symphony.samples.SampleApp.service.jar:$SOAM_DEPLOY_DIR/com.platform.symphony.samples.SharingData.client.jar:$SOAM_DEPLOY_DIR/com.platform.symphony.samples.SharingData.service.jar:$SOAM_DEPLOY_DIR/com.platform.symphony.samples.SharingData.common.jar"

###################
# 3. This is the main class used to start the Application code.
###################
SOAM_SERVICE_MAINCLASS="com.platform.symphony.samples.SampleApp.service.MyService"

###################
# 4. Additional JVM options are specified here.
###################
JVM_OPTIONS=""

###################
# Attention
# You do not need to modify the java start command.
# You can use the appropriate variable instead.
###################
if [ "$1" = "validate" ]; then
java -classpath "$JAVA_SDK_JARFILE:$SOAM_APP_JARFILES:$SOAM_APP_DEP_JARFILES" $SOAM_SERVICE_MAINCLASS
else
java -classpath "$JAVA_SDK_JARFILE:$SOAM_APP_JARFILES:$SOAM_APP_DEP_JARFILES" $JVM_OPTIONS $SOAM_SERVICE_MAINCLASS
fi