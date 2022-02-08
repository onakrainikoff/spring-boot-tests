# spring-boot-tests
## Run Tests
```
mvn verify -Dit=true

# docker daemon host can be customized with: -Ddocker_host=unix:///var/run/docker.sock
```

## Start/Stop containers for IDE tests running
```
mvn docker:start -Dit=true
mvn docker:stop -Dit=true

# Docker Daemon host can be customized with: -Ddocker_host=unix:///var/run/docker.sock
```
