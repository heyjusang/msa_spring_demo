# create
curl -X POST \
http://localhost:8080/customer \
-H 'content-type: application/json' \
-d '{"id": 4, "name": "New Customer"}'

# delete
curl -X DELETE http://localhost:8080/customer/4

# update
curl -X PUT \
http://localhost:8080/customer/2 \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{"id": 4, "name": "Update Customer"}'

# complex object
curl -X POST \
http://localhost:8080/customer \
-H 'content-type: application/json' \
-d '{
    "id": 5, 
    "name": "New Customer",
    "telephone": {
        "countryCode": "+54",
        "telephoneNumber": "7123456789"
    }
}'

# ignore additional key (customerName)
curl -X POST \
http://localhost:8080/customer \
-H 'content-type: application/json' \
-d '{
    "id": 6, 
    "customerName": "New Customer"
}'

# wrong JSON format (400 BAD REQUEST)
curl -X POST \
http://localhost:8080/customer \
-H 'content-type: application/json' \
-d '{
    "id": 7, 
    "name": "New Customer",
}'

# wrong JSON format (400 BAD REQUEST)
:<<'END'
curl -X POST \
http://localhost:8080/customer \
-H 'content-type: application/json' \
-d '
    "id": 7, 
    "name": "New Customer"
'
END
