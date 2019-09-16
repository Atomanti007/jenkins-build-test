#!groovy

node {
    stage('Download from git') {
        echo "Test"
    }

   stage('Checkout') {
      checkout scm
   }

   stage('Build') {
        sh "gradlew build"
   }
}