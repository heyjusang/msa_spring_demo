# docker swarm init

docker service create -p 8888:8888 heyjusang/msatest-configserver
docker service create -p 8761:8761 heyjusang/msatest-discoveryserver
docker service create -p 8081:8081 heyjusang/msatest
docker service create -p 8080:8080 heyjusang/msatest-gateway

docker service scale msatest=3
