#!/usr/bin/env groovy
def call() {
    echo "Building the JAR application for branch $BRANCH_NAME"
    sh 'mvn package'
}