#!/bin/sh
#echo Running PhotoApp Services
echo Running PhotoApp Services

set -o allexport
source ./.env.production
set +o allexport

docker-compose up

read -n 1 -s -r -p "Press any key to continue"