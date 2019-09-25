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
        sh(returnStdout: true, script: "pwd")
        sh "pwd"
        sh "echo pwd"
        sh "echo $(pwd)"
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