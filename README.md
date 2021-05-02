
# Overview
This project is about to demonstrate the coding challenge for hiring process of Gemography.
The challenge is explained in [**Gemography** (Coding challenge)](https://github.com/gemography/backend-coding-challenge) 

## Description
Backend-coding-challenge is maven project that implement the requirement descibed by the challenge.
This project provide a Rest  Service that expose two API:
- Github trending
- Service Metric
	
### Github trending
This endpoint can be used to dispay most trending repositories from a given date. 
It displays by default 100 repositories. You can change it by modifying the properties file

```properties
coding.challenge.github.nb_items=100
```
> **Note**: Max return repositories is 100

### Metric
The metrics are exposed thanks to actuator dependencies. You can use this endpoint to display the metric and application health.
> http://localhost:8082/actuator

## Technical
JDK 15 & maven (>3) are required
## Build & RUN
You can build the project by using the following command:
```bash
mvn clean install
```
> The delivery is a fatjar built by spring maven plugin

You can run the service using the fatjar created in target directory:
```bash
java -jar target/backend-coding-challenge-$version.jar
```
**version**: depend on the project version. In this case it's 0.0.1
## Build & RUN with docker
You can build the project and created the docker image:
```bash
mvn clean install -Pdocker
```
> The delivery is a docker image

If you need to specify docker repositories you can add the following parameter:
```bash
mvn clean install -Pdocker -Ddocker.repository=myrespository.com
```
You can run the service using the image created:
```bash
docker run -p 8081:8081 -p 8082:8082 -it gemography/backend-coding-challenge:0.0.1
```
## Try It
After running the service, you can send the request with swagger or command line
**SWAGGER**
> http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/trending-rest-service

**CMD**
```bash
curl -X GET "http://localhost:8081/github-trending/all?date=2021-04-20" -H  "accept: */*"
```
