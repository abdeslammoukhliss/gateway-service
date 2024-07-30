FROM ubuntu:latest
LABEL authors="abdo"

ENTRYPOINT ["top", "-b"]