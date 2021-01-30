docker run -d -p 8888:8888 heyjusang/msatest-configserver
docker run -d -p 8761:8761 heyjusang/msatest-discoveryserver
docker run -d -p 8081:8081 --add-host=host.docker.internal:host-gateway heyjusang/msatest
docker run -d -p 8080:8080 heyjusang/msatest-gateway
