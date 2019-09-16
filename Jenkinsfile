#!groovy

node {
    stage('Download from git') {
        echo "Test"
    }

   stage('Checkout') {
      checkout scm
   }

   stage('Build') {
        sh "cd /var/jenkins_home/workspace/Build"
        sh "./gradlew build"
   }
}