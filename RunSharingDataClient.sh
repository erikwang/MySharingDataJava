#!/bin/bash
###############################################################################
# If the version of Java is IBM JRE 1.6.0 (>=SR5) 32-bit, uncomment the following 2 lines.
#LD_PRELOAD=${SOAM_HOME}/6.1.1/linux2.6-glibc2.3-x86_64/lib/libsoambase.so
#export LD_PRELOAD
# If the version of Java is IBM JRE 1.6.0 (>=SR5) 64-bit, uncomment the following 2 lines.
#LD_PRELOAD=${SOAM_HOME}/6.1.1/linux2.6-glibc2.3-x86_64/lib64/libsoambase.so
#export LD_PRELOAD
java -classpath ${SOAM_HOME}/6.1.1/linux2.6-glibc2.3-x86_64/lib/JavaSoamApi.jar:/home/erikwang/work/MySharingDataJava/bin com.platform.symphony.samples.SharingData.client.SharingDataClient SessionType1 5000 10

