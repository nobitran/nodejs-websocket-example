#!/usr/bin/env groovy

// @Library('jenkins-share-library')
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
            when {
                expression {
                    BRANCH_NAME == "feature/update-version"
                }
            }
            steps{
                script {
                    gv.buildImage('nobitran/node-app', 1, 'dockerhub')
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
            when {
                expression {
                    BRANCH_NAME == "master"
                }
            }
            steps{
                script {
                    gv.deployApp()
                }
            }
        }
        
    }
}