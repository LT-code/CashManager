
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

echo $HEROKU_API_KEY | docker login --username=thomas.lopez@eptiech.eu --password-stdin registry.heroku.com

docker build -t $ODCKER_IMG_NAME/web .
docker tag $ODCKER_IMG_NAME/web registry.heroku.com/$ODCKER_IMG_NAME/web
docker push registry.heroku.com/$ODCKER_IMG_NAME/web
release $ODCKER_IMG_NAME
