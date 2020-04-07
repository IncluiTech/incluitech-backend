FROM postgres

COPY init.sql /docker-entrypoint-initdb.d/

ENTRYPOINT [ "docker-entrypoint.sh", "postgres" ]