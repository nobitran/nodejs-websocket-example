pipeline{
    agent any
    tools {
        nodejs 'node-18.10.0'
    }
    stages{
        stage("build"){
            steps{
                echo "Building"
            }
        
        }
        stage("test"){
            steps{
                echo "Testing"
            }
        
        }
        stage("deploy"){
            
            steps{
                echo "Deploying"
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