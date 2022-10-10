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
            withCredentials([
                usernamePassword(
                    credentials: 'demo-account', 
                    usernameVariable: USER, 
                    passwordVariable: PWD
                )
            ])
            steps{
                echo "Deploying"
                echo "USER: $USER"
                echo "PWD: $PWD"
            }

        }
    }
}