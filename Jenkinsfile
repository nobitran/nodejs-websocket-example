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
                        usernameVariable: USER, 
                        passwordVariable: PWD
                    )
                ]) {
                    echo "USER: $USER"
                    echo "PWD: $PWD"
                }
            }

        }
    }
}