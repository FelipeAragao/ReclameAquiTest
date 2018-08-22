# Remover Imagens
docker stop reclame-aqui-api reclame-aqui-db
docker rmi reclame-aqui-zuul reclame-aqui-api reclame-aqui-server

#!/bin/bash
# Delete all containers
docker rm $(docker ps -a -q)
# Delete all images
docker rmi $(docker images -q)