FROM postgres

COPY script/ /docker-entrypoint-initdb.d/

ENTRYPOINT [ "docker-entrypoint.sh", "postgres" ]