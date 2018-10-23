#bin/bash
curl -X POST -i "http://localhost:8080/dogreetings?name=World" -H "Content-type: application/json" -H "Accept: application/json" -d '{"id":"33", "content":"helloooo"}'
