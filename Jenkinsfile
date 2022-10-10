def gv

pipeline{
    agent any
    tools {
        nodejs 'node-18.10.0'
    }
    parameters {
        choice(name: 'VERSION', choices: ['1.1', '1.2', '1.3'], description: 'Choose version')
        booleanParam(name: 'executeTests', defaultValue: true, description: 'Execute test')
    }
    stages{
        stage("init"){
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build"){
            steps{
                script {
                    gv.buildApp()
                }
            }
        
        }
        stage("test"){
            when {
                expression {
                    params.executeTests
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
                withCredentials([
                    usernamePassword(
                        credentialsId: 'demo-account', 
                        usernameVariable: 'USERNAME', 
                        passwordVariable: 'PASSWORD'
                    )
                ]) {
                    sh 'echo $PASSWORD'
                    // also available as a Groovy variable
                    echo USERNAME
                    // or inside double quotes for string interpolation
                    echo "username is $USERNAME"
                }
            }

        }
    }
}