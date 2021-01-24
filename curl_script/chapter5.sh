# create 
curl -X POST \
http://localhost:8080/customer \
-H 'content-type: application/json' \
-d '{
    "id": 15, 
    "name": "New Customer",
    "telephone": {
        "countryCode": "+54",
        "telephoneNumber": "7123456789"
    }
}'

# delete
curl -X DELETE http://localhost:8080/customer/15
