FROM postgres:16.1-alpine3.19 as dumper

COPY sqlfile.sql ./docker-entrypoint-initdb.d/

RUN ["sed", "-i", "s/exec \"$@\"/echo \"skipping...\"/", "/usr/local/bin/docker-entrypoint.sh"]

ARG POSTGRES_USER
ARG POSTGRES_PASSWORD
ENV POSTGRES_USER=$POSTGRES_USER
ENV POSTGRES_PASSWORD=$POSTGRES_PASSWORD
ENV PGDATA=/data

RUN ["/usr/local/bin/docker-entrypoint.sh", "postgres"]


FROM postgres:16.1-alpine3.19

COPY --from=dumper /data $PGDATA

