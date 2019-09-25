#!groovy


pipeline {
  tools {
    jdk 'openjdk-11'
  }
  environment {
    dockerImage = "test"
    registry = "localhost:2375"
  }
  stages {
    stage('Cloning Git') {
      steps {
        git 'https://github.com/Atomanti007/jenkins-build-test.git'
      }
    }
    stage('Gradle build') {
      steps{
        sh "chmod +x gradlew"
        sh './gradlew clean test'
      }
    }
    stage('Building image') {
      steps{
        sh "pwd"
        sh "ls"
        sh "docker build -t ${registry}/test:${BUILD_NUMBER} -f Dockerfile ."
      }
    }
    stage('Remove Unused docker image') {
      steps{
        sh "docker rmi ${registry}/${dockerImage}:${BUILD_NUMBER}"
      }
    }
  }
}