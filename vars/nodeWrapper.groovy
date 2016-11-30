/**
 * Experimenting with trying to wrap a node around something if it is not already in a node.
 */
def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    // Do I have access to current build here?
    echo "currentBuild = ${currentBuild?.toString()}"
    echo "currentBuild class = ${currentBuild?.getClass()?.getName()}"

    // Do I have access to the environment here?
    echo "env = ${env?.toString()}"
    echo "env class = ${env?.getClass()?.getName()}"
    echo "env.NODE_NAME = ${env?.NODE_NAME}"

    // Are we in a node?
    Boolean inNode = false
    if(env.NODE_NAME != null) {
        inNode = true
        echo "nodeWrapper - in a node"
    }

    // Some script that is going to execute. In my real world code, I need to be on a node executor for this.
    def toDo = {
        sh "echo 'node wrapper!'"
    }

    // Do it with or without instantiating a node as required.
    if(inNode) {

        // Just execute it
        toDo()

    } else {

        // The caller doesn't have a node, wrap it in one
        node { toDo() }
    }
}