#!/usr/bin/env groovy

@Library('jenkins-share-library')
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
                    gv.updateVersion('nobitran/node-app', 'dockerhub')
                }
            }
        }
        stage("test"){
            when {
                expression {
                    BRANCH_NAME != "master"
                }
            }
            steps{
                script {
                    gv.testApp()
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
    post {
        // Clean after build
        always {
            cleanWs(cleanWhenNotBuilt: false,
                    deleteDirs: true,
                    disableDeferredWipeout: true,
                    notFailBuild: true,
                   )
        }
    }
}