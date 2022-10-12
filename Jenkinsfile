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
                }
            }
        }
        stage("deploy"){
            steps{
                script {
                    gv.deployApp()
                }
            }
        }
        
    }
}