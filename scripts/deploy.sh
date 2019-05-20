#!/usr/bin/env bash

mvn clean package

echo 'copy files...'

scp -i ~/ssh/vDebian_AmazonPEM.pem target/gs-serving-web-content-0.1.0.jar ubuntu@ec2-52-10-132-99.us-west-2.compute.amazonaws.com:/home/ubuntu/

echo 'Restart ...'

ssh -i ~/ssh/vDebian_AmazonPEM.pem ubuntu@ec2-52-10-132-99.us-west-2.compute.amazonaws.com<< EOF

pgrep java | xargs kill -9
nohup java -jar gs-serving-web-content-0.1.0.jar > log.txt &
EOF

echo 'End...'