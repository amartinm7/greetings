![springboot](https://sdtimes.com/wp-content/uploads/2018/03/spring-boot-490x257.png)

# Greetings
Bootstrap application for testing a rest API

## create a new spring project from the scratch
First of all, you can generate the scaffolding of this project using the INITIALIZR tool: 
https://start.spring.io/

## run springboot app
./gradlew build bootRun

## Dockerize the app: create a docker image and run the project from command line
From the project root folder exec the commands to create a docker image and run it:

```bash
docker build -f docker/Dockerfile . -t greetings
docker run -p 8080:8080 greetings
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
```

## options greetings to know which methods are enabled in the greetings path or resource
curl -X OPTIONS -i "http://localhost:8080/greetings?name=ant" -H "Content-type: application/json" -H "Accept: application/json"

## get greetings
curl -X GET -i "http://localhost:8080/greetings?name=ant" -H "Content-type: application/json" -H "Accept: application/json"

## post greetings
curl -X POST -i "http://localhost:8080/doGreetings?name=World" -H "Content-type: application/json" -H "Accept: application/json" -d '{"id":"33", "content":"hello"}'

## References
https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#spring-webflux
http://cloud.spring.io/spring-cloud-static/Finchley.SR1/single/spring-cloud.html
https://docs.spring.io/spring-security/site/docs/4.0.x/reference/htmlsingle/#csrf-configure

## Spring Webflux
![spring webflux](https://spring.io/img/homepage/diagram-boot-reactor.svg)

## Spring Cloud

![spring cloud](https://spring.io/img/homepage/diagram-distributed-systems.svg)

## CORS: how it works?
In the server side is especified: 
* which request methods are exposed,
* which request headers are valid from the client, 
* which response headers are sent to the client
* which resources or paths are exposed for the methods

so the client can collect the methods exposed in a concrete resource or path using the OPTIONS method.
This method retrieve the methods available for the path and the response headers.
This response headers are the responsible to inform to the client if the CORS is enabled in this resource. 
Access-Control-Expose-Headers

More or less this using a spring xml config file:
```
<mvc:cors>
 
    <mvc:mapping path="/api/**"
        allowed-origins="http://domain1.com, http://domain2.com"
        allowed-methods="GET, PUT"
        allowed-headers="header1, header2, header3"
        exposed-headers="header1, header2" allow-credentials="false"
        max-age="123" />
 
    <mvc:mapping path="/resources/**"
        allowed-origins="http://domain1.com" />
 
</mvc:cors>
```

![Cors](./_media/cors_flow.png)


## actuator spring plugin for health check, status and stop service
```
curl localhost:8080/actuator/health
curl localhost:8080/actuator/info
curl localhost:8080/actuator/shutdown
```


## killing processes

```
ps -aux | grep "greetings-rest-service-0.1.0.jar"
kill -9 $(jps | grep -i "greetings-rest-service-0.1.0.jar" | awk '{print $1}')
```

## installing spring cli
https://docs.spring.io/spring-boot/docs/2.0.5.RELEASE/reference/htmlsingle/#getting-started-installing-the-cli
```
sdk install springboot
```