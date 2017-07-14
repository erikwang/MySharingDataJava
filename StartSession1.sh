#!/bin/bash
###############################################################################
# If the version of Java is IBM JRE 1.6.0 (>=SR5) 32-bit, uncomment the following 2 lines.
#LD_PRELOAD=${SOAM_HOME}/6.1.1/linux2.6-glibc2.3-x86_64/lib/libsoambase.so
#export LD_PRELOAD
# If the version of Java is IBM JRE 1.6.0 (>=SR5) 64-bit, uncomment the following 2 lines.
#LD_PRELOAD=${SOAM_HOME}/6.1.1/linux2.6-glibc2.3-x86_64/lib64/libsoambase.so
#export LD_PRELOAD

#	//args[]
#        //s - sessionType, defined in application profile
#        //r - sleeptime
#        //m - number of tasks
#        //u - username
#        //x - password
#        //c - cmd to be executed in service instance
#        //d - use a file to simulate dummy common data
#        //R - session id to be re-connect to
#        //Q - close the client if tasks are submitted to a new created session as DETACH_ON_CLOSE
#        //C - add additional M memory usage for a task
#        //M - additional memory usage for a task
#        //E - throw fatal exception at call e.g. onSessionEnter - now it accepts "onSessionEnter" and "onInvoke"


#java -classpath ${SOAM_HOME}/6.1.1/linux2.6-glibc2.3-x86_64/lib/JavaSoamApi.jar:/home/erikwang/work/MySharingDataJava/bin com.platform.symphony.samples.SharingData.client.SharingDataClient SessionType1 1 3 "${EGO_SERVERDIR}/lim -V"

#java -classpath ${SOAM_HOME}/6.1.1/linux2.6-glibc2.3-x86_64/lib/JavaSoamApi.jar:/home/erikwang/work/MySharingDataJava/bin com.platform.symphony.samples.SharingData.client.SharingDataClient SessionType1 3 10000 "/bin/ls" "/opt/ibm/platformsymphony/kernel/log/dummy.log" -Xmx4096m -Xms4096m



#java -classpath ${SOAM_HOME}/7.1.1/linux-x86_64/lib/JavaSoamApi.jar:/home/erikwang/work/MySharingDataJava711/bin com.platform.symphony.samples.SharingData.client.SharingDataClient SessionType1 300 5 "/bin/ls" "./api.eksymch1.log" -Xmx4096m -Xms512m


echo The running command is:
java -classpath ${SOAM_HOME}/7.1/linux2.6-glibc2.3-x86_64/lib/JavaSoamApi.jar:/home/erikwang/work/MySharingDataJava71/bin com.platform.symphony.samples.SharingData.client.SharingDataClient -s SessionType1 -r 5000 -m 2 -u Admin -x Admin -c "/bin/hostname -f" -d ./deploy.sh -Q false 

#java -classpath ${SOAM_HOME}/7.1/linux2.6-glibc2.3-x86_64/lib/JavaSoamApi.jar:/home/erikwang/work/MySharingDataJava71/bin com.platform.symphony.samples.SharingData.client.SharingDataClient -u Admin -x Admin -R 1911

