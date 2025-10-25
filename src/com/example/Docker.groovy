#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {

    def source

    Docker(pipeline) {
        this.source = pipeline
    }

    def buildDockerImage(String imageName) {
        source.echo "building the Docker Image"
        source.sh "docker build -t $imageName"
    }

    def dockerLogin() {
        source.withCredentials([source.usernamePassword(credentialsId:'docker-hub-private-repo', passwordVariable: 'PASS', usernameVariable:'USER')]) {
            source.sh "echo '${source.PASS}'| docker login -u '${source.USER}' --password-stdin"
        }
    }

    def dockerPush() {
        source.sh "docker push $imageName"
    }
}