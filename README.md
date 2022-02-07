# spring-boot-tests
## Run Tests
```
mvn verify -Ddocker_host=localhost
```

## Start/Stop containers for IDE tests running
```
mvn docker:start -Ddocker_host=localhost
mvn docker:stop -Ddocker_host=localhost
```
