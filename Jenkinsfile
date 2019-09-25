#!groovy


pipeline {
  tools {
    jdk 'JDK 9'
  }
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
    stage('Test') {
      steps{
        sh "chmod +x gradlew"
        sh './gradlew clean test'
      }
    }
    stage('Gradle build') {
      steps{
        sh "chmod +x gradlew"
        sh './gradlew build'
      }
    }
    stage('Building image') {
      steps{
        sh "pwd"
        sh "ls"
        sh "docker build -t ${registry}/${dockerImage}:${BUILD_NUMBER} -f Dockerfile ."
      }
    }
    stage('Remove Unused docker image') {
      steps{
        sh "docker rmi ${registry}/${dockerImage}:${BUILD_NUMBER}"
      }
    }
  }
}