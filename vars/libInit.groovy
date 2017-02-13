def execute() {
    node() {
        stage('Checkout') {
            checkout scm
            String revision = "0101010"
            gitlabBuilds(builds: ["build", "test"]) {
                stage("build") {
                    gitlabCommitStatus("build") {
                        sh "mvn clean package -DskipTests -DgitRevision=$revision"
                    }
                }

                stage("test") {
                    gitlabCommitStatus("test") {
                        sh "mvn verify -DgitRevision=$revision"
                    }
                }
            }
        }
    }
}

return this
