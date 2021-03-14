enablePlugins(FlywayPlugin)

flywayUrl := "jdbc:sqlserver://localhost:1433;databaseName=foobar_database"
flywayUser := "SA"
flywayPassword := "Password123"

libraryDependencies ++= Seq("com.microsoft.sqlserver" % "mssql-jdbc" % "7.4.1.jre11")

pushRemoteCacheTo := Some(MavenCache("local-cache", baseDirectory.value / "remote-cache"))
