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
            // when {
            //     expression {
            //         BRANCH_NAME == "master"
            //     }
            // }
            steps{
                script {
                    def version = gv.getCurrentVersion()
                    def new_version = increaseVersion(version)
                    gv.buildImageAndUpdateVersion('nobitran/node-app', new_version, 'dockerhub')
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