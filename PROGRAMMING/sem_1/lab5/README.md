Тут что-то будэ...

для сборки:
mvn clean install

java -jar target/product-collection-manager.jar data.json direct


java -cp target/product-collection-manager.jar server.ServerMain data.json
java -cp target/product-collection-manager.jar client.ClientMain
