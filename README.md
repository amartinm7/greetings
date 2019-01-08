![springboot](https://sdtimes.com/wp-content/uploads/2018/03/spring-boot-490x257.png)

# Greetings
Bootstrap application for testing a rest API

## ![springboot](./_media/icons/springboot.png) run springboot app
```bash
gradle wrapper
./gradlew build
./gradlew build bootRun
```

or run fat jar
```bash
java -jar build/libs/greetings-rest-service-0.1.0.jar
```

## ![docker](./_media/icons/docker.png) Dockerize the app
From the project root folder exec the commands to create a docker image and run it:

for macosx start the docker daemon
```bash
killall Docker && open /Applications/Docker.app
```

then execute the next commands in order to create the docker image and run it:
```bash
docker build -f docker/Dockerfile . -t greetings
docker run -p 8080:8080 greetings
```

to stop the application first we have to stop the docker process and then kill the docker process:
```bash
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
```

to clean the docker images from the system:
```bash
docker images
docker rmi PID
``` 

Once you have the dockerized app is really easy bring it to the cloud. You look for a cloud provider to deploy it and host it.

## ![swagger](./_media/icons/swagger.png) Swagger
You can see the swagger documentation in the following url:
- http://localhost:8080/swagger-ui.html

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
```xml
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
```bash
curl localhost:8080/actuator/health
curl localhost:8080/actuator/info
curl localhost:8080/actuator/shutdown
```


## killing processes

```bash
ps -aux | grep "greetings-rest-service-0.1.0.jar"
kill -9 $(jps | grep -i "greetings-rest-service-0.1.0.jar" | awk '{print $1}')
```

## installing spring cli
https://docs.spring.io/spring-boot/docs/2.0.5.RELEASE/reference/htmlsingle/#getting-started-installing-the-cli
```bash
sdk install springboot
```

## spring guides
https://spring.io/guides/gs/gradle/
https://spring.io/guides/gs/actuator-service/


## intellij tips

### avoid out folder
File | Project Structure | Project Settings | Modules | Paths tab | Compiler output
Select 'use module compile output path' and add the build main and test folders

### add navigation arrows to the toolbox
preferences | navigation bar toolbar
click + button add the left and right arrows from the navigation list

### organize imports like eclipse
preferences | editor | general | auto imports
marks the checks with 'unambiguos' labels 
