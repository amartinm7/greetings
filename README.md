![springboot](https://sdtimes.com/wp-content/uploads/2018/03/spring-boot-490x257.png)

# Greetings
Bootstrap application for testing a rest API

## create a new spring project from the scratch
First of all, you can generate the scaffolding of this project using the INITIALIZR tool: 
https://start.spring.io/

## run springboot app
./gradlew build bootRun

## get greetings
curl -X GET -i "http://localhost:8080/greetings?name=ant" -H "Content-type: application/json" -H "Accept: application/json"

## post greetings
curl -X POST -i "http://localhost:8080/dogreetings?name=World" -H "Content-type: application/json" -H "Accept: application/json" -d '{"id":"33", "content":"hello"}'

## References
https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#spring-webflux
http://cloud.spring.io/spring-cloud-static/Finchley.SR1/single/spring-cloud.html
https://docs.spring.io/spring-security/site/docs/4.0.x/reference/htmlsingle/#csrf-configure

## Spring Webflux
![spring webflux](https://spring.io/img/homepage/diagram-boot-reactor.svg)

## Spring Cloud

![spring cloud](https://spring.io/img/homepage/diagram-distributed-systems.svg)


