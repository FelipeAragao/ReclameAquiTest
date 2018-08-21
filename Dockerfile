FROM tutum/mongodb

RUN docker run -d -p 27017:27017 -p 28017:28017 -e MONGODB_USER="root" -e MONGODB_DATABASE="reclame-aqui-db" -e MONGODB_PASS="7410" tutum/mongodb
