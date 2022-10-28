#!/usr/bin/env groovy

// global share library
// @Library('jenkins-share-library')

// dynamic import
library identifier: 'jenkins-share-library@main', retriever: modernSCM(
  [$class: 'GitSCMSource',
   remote: 'https://github.com/nobitran/jenkins-share-library.git',
   credentialsId: 'nobitran-github'])

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