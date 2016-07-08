#!/bin/bash
###############################################################################
# If the version of Java is IBM JRE 1.6.0 (>=SR5) 32-bit, uncomment the following 2 lines.
#LD_PRELOAD=${SOAM_HOME}/6.1.1/linux2.6-glibc2.3-x86_64/lib/libsoambase.so
#export LD_PRELOAD
# If the version of Java is IBM JRE 1.6.0 (>=SR5) 64-bit, uncomment the following 2 lines.
#LD_PRELOAD=${SOAM_HOME}/6.1.1/linux2.6-glibc2.3-x86_64/lib64/libsoambase.so
#export LD_PRELOAD


# //args[]
# //0 - sessionType, defined in application profile
# //1 - sleeptime ms
# //2 - number of tasks
# //3 - a local command to be executed on the service
# //4 - a local file to be loaded as common data


#java -classpath ${SOAM_HOME}/6.1.1/linux2.6-glibc2.3-x86_64/lib/JavaSoamApi.jar:/home/erikwang/work/MySharingDataJava/bin com.platform.symphony.samples.SharingData.client.SharingDataClient SessionType1 1 3 "${EGO_SERVERDIR}/lim -V"

#java -classpath ${SOAM_HOME}/6.1.1/linux2.6-glibc2.3-x86_64/lib/JavaSoamApi.jar:/home/erikwang/work/MySharingDataJava/bin com.platform.symphony.samples.SharingData.client.SharingDataClient SessionType1 3 10000 "/bin/ls" "/opt/ibm/platformsymphony/kernel/log/dummy.log" -Xmx4096m -Xms4096m



java -classpath ${SOAM_HOME}/7.1.1/linux-x86_64/lib/JavaSoamApi.jar:/home/erikwang/work/MySharingDataJava711/bin com.platform.symphony.samples.SharingData.client.SharingDataClient SessionType1 300 5 "/bin/ls" "./api.eksymch1.log" -Xmx4096m -Xms512m

