FROM postgres

ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_USER=postgres
ENV POSTGRES_DB=incluitech

COPY init-db.sh /docker-entrypoint-initdb.d/
ENTRYPOINT [ "docker-entrypoint.sh", "postgres" ]