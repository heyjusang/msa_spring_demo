# create with location header
curl -X POST \
http://localhost:8080/functional/customer \
-v \
-H 'content-type: application/json' \
-d '{"id": 10, "name": "New Customer"}'

# create with error
curl -X POST \
http://localhost:8080/functional/customer \
-H 'content-type: application/json' \
-d '{"id": 11, "name": "New Customer" bad json}'

# create duplicate customer
curl -X POST \
http://localhost:8080/functional/customer \
-v \
-H 'content-type: application/json' \
-d '{"id": 1, "name": "New Customer"}'
