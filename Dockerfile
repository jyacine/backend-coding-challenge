#---------------------------------------------------------------------------------------------------------------------------
# VERSION               1.0.0
#---------------------------------------------------------------------------------------------------------------------------
FROM openjdk:15-jdk-alpine3.11
LABEL maintainer="yacine.jaber@gmail.com" \
 name="github trending" \
 url="https://github.com/jyacine/backend-coding-challenge" \
 version="1.0" \
 vendor="gemography/jyacine" \
 description="This image is used to run github trending application" 

#---------------------------------------------------------------------------------------------------------------------------
# set env
#---------------------------------------------------------------------------------------------------------------------------
ARG JAR_FILE \
	APP_NAME
ENV APP_NAME=${APP_NAME} \
	APP_VERSION=${APP_VERSION} \
	APP_HOME=/etc/${APP_NAME} \
	CONFIG_HOME=/etc/${APP_NAME}/config \
	LIB_PATH=/usr/share/lib/${APP_NAME}.jar \
	APP_USERNAME=gemo \
	APP_USERID=1100 \
	APP_GROUPNAME=gemo \
	APP_GROUPID=1100


#---------------------------------------------------------------------------------------------------------------------------
# download curl
#---------------------------------------------------------------------------------------------------------------------------
RUN apk add --no-cache curl jq
#---------------------------------------------------------------------------------------------------------------------------
# create user
#---------------------------------------------------------------------------------------------------------------------------
RUN addgroup -g ${APP_USERID} -S ${APP_GROUPNAME} && adduser -u ${APP_GROUPID} -S ${APP_USERNAME} -G ${APP_USERNAME}
#---------------------------------------------------------------------------------------------------------------------------
# create directories
#---------------------------------------------------------------------------------------------------------------------------
RUN mkdir -p ${APP_HOME} ${CONFIG_HOME} /usr/share/lib/
COPY target/${JAR_FILE} ${LIB_PATH}
#---------------------------------------------------------------------------------------------------------------------------
# change the owner
#---------------------------------------------------------------------------------------------------------------------------
RUN chown --recursive ${APP_USERNAME}:${APP_GROUPNAME} ${APP_HOME} && \
	chown -R ${APP_USERNAME}:root ${APP_HOME} && \ 
	chown --recursive ${APP_USERNAME}:${APP_GROUPNAME} ${CONFIG_HOME} && \
	chown -R ${APP_USERNAME}:root ${CONFIG_HOME} && \ 
	chmod 775 ${LIB_PATH} && \
	chown ${APP_USERNAME}:root ${LIB_PATH}


#---------------------------------------------------------------------------------------------------------------------------
# start
#---------------------------------------------------------------------------------------------------------------------------
EXPOSE 8081 8082
HEALTHCHECK --interval=10s --timeout=3s --retries=3 --start-period=45s CMD curl -v --silent http://localhost:8082/actuator/health | jq --exit-status -n 'inputs | if has("status") then .status=="UP" else false end' > /dev/null || exit 1
USER gemo
CMD java -jar "${LIB_PATH}"