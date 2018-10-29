#!/usr/bin/env bash
curl -X GET -i "http://localhost:8080/greetings?name=ant" -H "Content-type: application/json" -H "Accept: application/json"
