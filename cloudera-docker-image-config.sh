#!/bin/sh
docker run --hostname=quickstart.cloudera --privileged=true -t -i -v /Users//Users/mderri/Desktop/MIU courses/Big Data Course/Hadoop-Project:/src --publish-all=true -p 8888 cloudera/quickstart /usr/bin/docker-quickstart
