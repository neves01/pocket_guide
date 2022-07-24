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