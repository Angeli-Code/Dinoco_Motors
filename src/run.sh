#!/bin/bash

cd bin/
java -cp .:/usr/share/java/mysql-connector-java.jar Main "$@"
