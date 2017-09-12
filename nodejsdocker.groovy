job('NodeJS Docker example') {
    scm {
        git('https://github.com/jhashivkr/jenkins-docker-nodejs-demo.git') {  node -> // is hudson.plugins.git.GitSCM
        node / gitConfigName('jhashivkr')
        node / gitConfigEmail('jhashivkr@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('jhashivkr/nodejs-example')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
