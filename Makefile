######################################################################
#
# 		Makefile for Java Samples
#  ------- R E A D   M E   T O   A V O I D    F A I L U R E  --------
#
#
#
######################################################################

ARCH_BUILD=linux2.6-glibc2.3-x86_64
COMMON_DIR=com/platform/symphony/samples/SharingData/common
CLIENT_DIR=com/platform/symphony/samples/SharingData/client
SERVICE_DIR=com/platform/symphony/samples/SharingData/service

SERVICE_DIR2=com/platform/symphony/samples/SampleApp/service
SERVICE_DIR3=com/platform/symphony/samples/SampleApp/anotherservice

BIN=bin
SRC=src


JAVA_SDK_JARFILE=${SOAM_HOME}/7.1/$(ARCH_BUILD)/lib/JavaSoamApi.jar

SERVICE_SCRIPT=Runcom.platform.symphony.samples.SharingData.service.SharingDataService.sh
SERVICE_JAR=SharingDataServiceJava.jar
DEPLOYMENT_PACKAGE=SharingDataServiceJavaPackage.zip

SERVICE_SCRIPT2=Runcom.platform.symphony.samples.SampleApp.service.MyService.sh
SERVICE_JAR2=MySampleAppServiceJava.jar
DEPLOYMENT_PACKAGE2=MySampleServiceJavaPackage.zip

SERVICE_SCRIPT3=Runcom.platform.symphony.samples.SampleApp.anotherservice.MyService.sh
SERVICE_JAR3=AnotherMySampleAppServiceJava.jar
DEPLOYMENT_PACKAGE3=AnotherMySampleServiceJavaPackage.zip


## building process

build all: SharingDataJava jar SampleAppJava jar2 AnotherSampleAppJava jar3


makedir:
	if [ ! -d ${BIN} ]; then \
	mkdir -p ${BIN} ; \
	fi


SharingDataJava: makedir
	javac -d ${BIN} -classpath $(JAVA_SDK_JARFILE) ${SRC}/${COMMON_DIR}/MyInput.java
	javac -d ${BIN} -classpath $(JAVA_SDK_JARFILE) ${SRC}/${COMMON_DIR}/MyOutput.java
	javac -d ${BIN} -classpath $(JAVA_SDK_JARFILE) ${SRC}/${COMMON_DIR}/MyCommonData.java
	javac -d ${BIN} -classpath $(JAVA_SDK_JARFILE) ${SRC}/${COMMON_DIR}/MyException.java
	javac -d ${BIN} -classpath "$(JAVA_SDK_JARFILE):${BIN}" \
                ${SRC}/${CLIENT_DIR}/SharingDataClient.java
		javac -d ${BIN} -classpath "$(JAVA_SDK_JARFILE):${BIN}" \
                ${SRC}/${SERVICE_DIR}/SharingDataService.java

jar:
	cp ${SERVICE_SCRIPT} ${BIN}; \
        cd ${BIN};\
        jar cf ${SERVICE_JAR} ${COMMON_DIR}/MyInput.class ${COMMON_DIR}/MyOutput.class ${COMMON_DIR}/MyCommonData.class ${COMMON_DIR}/MyException.class ${SERVICE_DIR}/SharingDataService.class; \
        zip ${DEPLOYMENT_PACKAGE} ${SERVICE_JAR} ${SERVICE_SCRIPT}; \
        cp ${DEPLOYMENT_PACKAGE} ..; \
        rm -f *.jar ${DEPLOYMENT_PACKAGE} ${SERVICE_SCRIPT}

	
SampleAppJava: makedir
	javac -d ${BIN} -classpath $(JAVA_SDK_JARFILE) ${SRC}/${COMMON_DIR}/MyInput.java
	javac -d ${BIN} -classpath $(JAVA_SDK_JARFILE) ${SRC}/${COMMON_DIR}/MyOutput.java
	javac -d ${BIN} -classpath $(JAVA_SDK_JARFILE) ${SRC}/${COMMON_DIR}/MyCommonData.java
	javac -d ${BIN} -classpath "$(JAVA_SDK_JARFILE):${BIN}" \
		${SRC}/${SERVICE_DIR2}/MyService.java

jar2:
	cp ${SERVICE_SCRIPT2} ${BIN}
	cd ${BIN}; \
	jar cf ${SERVICE_JAR2} ${COMMON_DIR}/MyInput.class ${COMMON_DIR}/MyOutput.class ${COMMON_DIR}/MyCommonData.class ${SERVICE_DIR2}/MyService.class; \
	zip ${DEPLOYMENT_PACKAGE2} ${SERVICE_JAR2} ${SERVICE_SCRIPT2}; \
	cp ${DEPLOYMENT_PACKAGE2} ..; \
	rm -f *.jar ${DEPLOYMENT_PACKAGE2} ${SERVICE_SCRIPT2}
	
	
AnotherSampleAppJava: makedir
	javac -d ${BIN} -classpath $(JAVA_SDK_JARFILE) ${SRC}/${COMMON_DIR}/MyInput.java
	javac -d ${BIN} -classpath $(JAVA_SDK_JARFILE) ${SRC}/${COMMON_DIR}/MyOutput.java
	javac -d ${BIN} -classpath $(JAVA_SDK_JARFILE) ${SRC}/${COMMON_DIR}/MyCommonData.java
	javac -d ${BIN} -classpath "$(JAVA_SDK_JARFILE):${BIN}" \
		${SRC}/${SERVICE_DIR3}/MyService.java

jar3:
	cp ${SERVICE_SCRIPT3} ${BIN}
	cd ${BIN}; \
	jar cf ${SERVICE_JAR3} ${COMMON_DIR}/MyInput.class ${COMMON_DIR}/MyOutput.class ${COMMON_DIR}/MyCommonData.class ${SERVICE_DIR3}/MyService.class; \
	zip ${DEPLOYMENT_PACKAGE3} ${SERVICE_JAR3} ${SERVICE_SCRIPT3}; \
	cp ${DEPLOYMENT_PACKAGE3} ..; \
	rm -f *.jar ${DEPLOYMENT_PACKAGE3} ${SERVICE_SCRIPT3}

	
clean:
	-rm -f ./${DEPLOYMENT_PACKAGE}
	-rm -f ${BIN}/*.sh
	-rm -f ${BIN}/*.jar
	-rm -f ${BIN}/${COMMON_DIR}/*.class
	-rm -f ${BIN}/${COMMON_DIR}/*.jar
	-rm -f ${BIN}/${CLIENT_DIR}/*.class
	-rm -f ${BIN}/${CLIENT_DIR}/*.jar
	-rm -f ${BIN}/${SERVICE_DIR}/*.class
	-rm -f ${BIN}/${SERVICE_DIR}/*.jar
	-rm -f ./${DEPLOYMENT_PACKAGE2}
	-rm -f ${BIN}/${SERVICE_DIR2}/*.class
	-rm -f ${BIN}/${SERVICE_DIR2}/*.jar
	-rm -f ./${DEPLOYMENT_PACKAGE3}
	-rm -f ${BIN}/${SERVICE_DIR3}/*.class
	-rm -f ${BIN}/${SERVICE_DIR3}/*.jar
