Run the following command to see Flyway fail.

```
$ rm -rf remote-cache && \
  find -type d -name target -exec rm -r {} + && \
  docker run \
    --rm \
    --detach \
    --name mssql \
    --publish 1433:1433 \
    --env ACCEPT_EULA=Y \
    --env SA_PASSWORD=Password123 \
      mcr.microsoft.com/mssql/server:2017-CU14-ubuntu && \
  sleep 5s && \
  docker exec mssql \
    /opt/mssql-tools/bin/sqlcmd \
      -S localhost \
      -U SA \
      -P Password123 \
      -Q "CREATE DATABASE foobar_database;"
  git checkout master && \
  sbt compile && \
  sbt pushRemoteCache && \
  find -type d -name target -exec rm -r {} + && \
  git checkout moved-migration && \
  sbt pullRemoteCache && \
  sbt compile && \
  (sbt flywayMigrate || true) && \
  docker stop mssql
```