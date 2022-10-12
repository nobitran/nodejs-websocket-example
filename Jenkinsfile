def gv

pipeline{
    agent any
    stages{
        stage("init"){
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build image"){

            steps{
                script {
                    gv.buildApp()
                    gv.deployApp()
                }
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub', 
                        usernameVariable: 'USERNAME', 
                        passwordVariable: 'PASSWORD'
                    )
                ]) {
                    sh 'docker build -t nobitran/node-app:1.0 .'
                    sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
                    sh 'docker push nobitran/node-app:1.0'
                }
            }

        }
    }
}