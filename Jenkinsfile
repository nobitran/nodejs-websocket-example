pipeline{
    agent any
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
                        credentials: 'demo-account', 
                        usernameVariable: 'USER', 
                        passwordVariable: 'PWD'
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