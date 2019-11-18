
release(){
	curl -f -n -X PATCH -H "Authorization: Bearer $HEROKU_API_KEY" https://api.heroku.com/apps/$1/formation \
	  -d '{
	  "updates": [
	    {
	      "type": "web",
	      "docker_image": "'"$(docker inspect registry.heroku.com/$1/web --format={{.Id}})"'"
	    }
	  ]
	}' \
	  -H "Content-Type: application/json" \
	  -H "Accept: application/vnd.heroku+json; version=3.docker-releases"
}

echo $HEROKU_API_KEY | docker login --username=amoniack7@gmail.com --password-stdin registry.heroku.com


docker build -t cm50/server .
docker tag cm50/server registry.heroku.com/cash-manager-50-server/web
docker push registry.heroku.com/cash-manager-50-server/web
release cash-manager-50-server
