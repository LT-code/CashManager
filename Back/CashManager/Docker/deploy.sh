
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

echo "---- Heroku connection"
echo $HEROKU_API_KEY | docker login --username=thomas.lopez@eptiech.eu --password-stdin registry.heroku.com

echo "---- Docker change tag"
docker tag $1 registry.heroku.com/epitech-cashmanager-msc/web

echo "---- Docker push image"
docker push registry.heroku.com/epitech-cashmanager-msc/web

echo "---- Release"
release epitech-cashmanager-msc
