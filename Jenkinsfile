#!groovy


pipeline {
  environment {
    dockerImage = "test"
    registry = "localhost:5000"
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
        script {
          sh "cd $WORKSPACE"
          sh "ls -l > commandResult"
          dockerImage = docker.build registry + "/" + dockerImage + ":$BUILD_NUMBER -f $ORKSPACE ."
        }
      }
    }
    stage('Deploy Image') {
      steps{
        script {
          docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
          }
        }
      }
    }
    stage('Remove Unused docker image') {
      steps{
        sh "docker rmi $registry:$BUILD_NUMBER"
      }
    }
  }
}