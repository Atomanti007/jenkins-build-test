#!groovy


pipeline {
  environment {
    dockerImage = "test"
    registry = "http://172.17.0.2:5000"
  }
  agent any
  stages {
    stage('Cloning Git') {
      steps {
        git 'https://github.com/Atomanti007/jenkins-build-test.git'
      }
    }
    stage('Building image') {
      steps{
        sh "docker build -t $registry/test:${BUILD_NUMBER} -f Dockerfile ."
      }
    }
    stage('Remove Unused docker image') {
      steps{
        sh "docker rmi $registry:$BUILD_NUMBER"
      }
    }
  }
}