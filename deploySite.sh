#! /bin/bash

# Builds and deploys the website to www.lagomframework.com

set -e

# Build the website
sbt clean web-stage

# Make the website a git repo
cd target/web/stage
git init
git add .
git commit -m "Website build"

# Push the repo to the master branch of the main repo
git push ../../.. master -f

# Push the repo to the website
cd ../../..
git push origin master -f
