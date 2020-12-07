#!/usr/bin/env bash
mvn clean package -T2
java -jar target/batch-learning-0.0.1-SNAPSHOT.jar "item=Cricket Hats" "package_date(date)=2020/01/01"