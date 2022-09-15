# Docker

## Basic Docker file

<pre><code>FROM: ImageName

RUN 	apt-get update && \
	apt-get install vim

EXPOSE 8000
</code></pre>

## Entry Point vs CMD

CMD will be execute after Entry Point. If we put some arg after terminal command, CMD will be replaced.

<pre><code>COPY: docker-entrypoint.sh /

ENTRYPOINT ["/docker-entrypoint.sh"]

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
</code></pre>

Based on command below:

<code>docker run -dit --name Ubuntu --network mynetworking bash</code>

bash will replace the entire CMD command.

## Remove all Images, Containers and Volumes

<code>docker rm -f $(docker ps -qa) && docker rmi -f $(docker images -aq) && docker volume prune</code>

## Back up PostgreSQL inside docker container

<code>docker exec <postgresql_container> /bin/bash -c "/usr/bin/pg_dump -U <postgresql_user> <postgresql_database>"</code>

## Back up all Docker PostgreSQL databases

<code>docker exec -i <container_name> /usr/bin/pg_dumpall -U <postgresql_user> > postgres-backup.sql</code>

## Postgres Restore Database Command on Docker

<code>docker exec <container_name> pg_restore -U <postgresql_user> -d <database_name> /backups/postgres-backup.sql </code>
