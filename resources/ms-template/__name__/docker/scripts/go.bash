#!/bin/bash

source /root/set-error-handling.bash
source /root/jvm_memory.bash

# update configuration
ORIG_CONF_FILE=/mypackage/conf/logs-generator.conf
CONF_FILE=/etc/gaia/logs-generator.conf
RUN_FILE=/mypackage/bin/logs-generator
LOGBACK_FILE=/mypackage/conf/logback.xml

MYHOST=`hostname`
if [ "x$MYADDR" == "x" ]; then
	MYADDR=0.0.0.0
fi
if [ "x$MYBINDADDR" == "x" ]; then
	MYBINDADDR=0.0.0.0
fi

# import configuration suitable for current environment
source /root/env.bash

cp $ORIG_CONF_FILE $CONF_FILE

sed -i "s/<<local-ip>>/$MYADDR/g" $RUN_FILE
sed -i 's/DEFAULT_JVM_OPTS=\x27"/DEFAULT_JVM_OPTS=\x27/' $RUN_FILE
sed -i 's/"\x27$/\x27/' $RUN_FILE

cp /mypackage/conf/logback*.xml /etc/gaia/

JAVA_OPTS=`set_jvm_memory ${ENV} ${JAVA_OPTS}`

echo "JAVA_OPTS: $JAVA_OPTS"
echo "==================================================================================================="
echo "CONFIGURATION FILE"
echo "==================================================================================================="
cat $CONF_FILE
echo "==================================================================================================="
echo "JAVA RUN FILE"
cat $RUN_FILE

export JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCDetails -Xloggc:/var/log/gaia/__name__-gc.log"

if [ ! -z "$LOGZIO_METRICS_TOKEN" ]
then
    echo "Found metrics token for $ENV"
    MONITORING_METRICS_JMX2LOGZIO_AGENT="-javaagent:/root/jmx2logzio-javaagent.jar=LOGZIO_TOKEN=$LOGZIO_METRICS_TOKEN,LISTENER_URL=$LOGZIO_BULK_URL,SERVICE_NAME=__camelname__\,SERVICE_HOST=$EC2_NAME\,EXTRA_DIMENSIONS={env=$ENV:region=$REGION},WHITE_LIST_REGEX=LHCounter.\*\|com.logshero.\*\|io.logz.\*\|java.lang.\*\|com.zaxxer.hikari.\*\,POLLING_INTERVAL_IN_SEC=$KAFKA_METRICS_INTERVAL_IN_SEC"
fi
export JAVA_OPTS="$JAVA_OPTS $MONITORING_METRICS_JMX2LOGZIO_AGENT"

exec /mypackage/bin/__name__