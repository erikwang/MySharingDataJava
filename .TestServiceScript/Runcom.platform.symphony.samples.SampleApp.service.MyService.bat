@echo off
rem Runcom.platform.symphony.samples.SampleApp.service.MyService.bat
rem ###############################################################################
rem #
rem # This file contains a template that can be used to execute the 
rem # "com.platform.symphony.samples.SampleApp.service.MyService" class.
rem #
rem # This file was generated by the Symphony Code Generation Tool 
rem # version 6.1.1.0 at Fri Dec 05 16:34:28 2014
rem #
rem # Copyright International Business Machines Corp, 1992-2013.
rem # US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
rem # Accelerating Intelligence(TM). All rights reserved.
rem #
rem # This file will be re-generated every time the packaging wizard is run from
rem # within the Symphony Eclipse Pluggin.
rem #
rem # NOTE: You should read the additional comments embedded in this file to 
rem # get further details on the variables used in this script. This file
rem # can be used to execute your class on Windows hosts.
rem #
rem ###############################################################################

rem ###################
rem # 1. You do not need to modify JAVA_SDK_JARFILE.
rem # It has already been set by the Symphony Eclipse IDE pluggin. 
rem ###################
if "%EGO_MACHINE_TYPE%"=="" set EGO_MACHINE_TYPE=w2k3_x64-vc7-psdk
set JAVA_SDK_JARFILE="%SOAM_HOME%\6.1.1\%EGO_MACHINE_TYPE%\lib\JavaSoamApi.jar"

rem ###################
rem # 2. This is where we put your dependent JARs containing all the implementation 
rem # required for your Application to function.
rem ###################
set SOAM_APP_DEP_JARFILES="%SOAM_DEPLOY_DIR%\com.platform.symphony.samples.SampleApp.anotherservice.jar;%SOAM_DEPLOY_DIR%\com.platform.symphony.samples.SampleApp.service.jar;%SOAM_DEPLOY_DIR%\com.platform.symphony.samples.SharingData.client.jar;%SOAM_DEPLOY_DIR%\com.platform.symphony.samples.SharingData.service.jar;%SOAM_DEPLOY_DIR%\com.platform.symphony.samples.SharingData.common.jar"

rem ###################
rem # 3. This is the main class used to start the Application code.
rem ###################
set SOAM_SERVICE_MAINCLASS=com.platform.symphony.samples.SampleApp.service.MyService

rem ###################
rem # 4. Additional JVM options are specified here.
rem ###################
set JVM_OPTIONS=

if "%SOAM_DEPLOY_DIR%"=="" set SOAM_DEPLOY_DIR=.

rem ###################
rem # Attention
rem # You do not need to modify the java start command.
rem # You can use the appropriate variable instead.
rem ###################

if "%1" == "validate" (goto VALIDATE)

java %JVM_OPTIONS% -classpath %JAVA_SDK_JARFILE%;%SOAM_APP_DEP_JARFILES% %SOAM_SERVICE_MAINCLASS%
goto END

:VALIDATE
java -classpath %JAVA_SDK_JARFILE%;%SOAM_APP_DEP_JARFILES% %SOAM_SERVICE_MAINCLASS%

:END

