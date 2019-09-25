#!groovy


pipeline {
  environment {
    dockerImage = "test"
    registry = "localhost:2375"
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
        sh "docker build -t ${registry}/test:${BUILD_NUMBER} -f Dockerfile ."
      }
    }
    stage('Remove Unused docker image') {
      steps{
        sh "echo $(ls)"
        sh "docker rmi ${registry}/${dockerImage}:${BUILD_NUMBER}"
      }
    }
  }
}