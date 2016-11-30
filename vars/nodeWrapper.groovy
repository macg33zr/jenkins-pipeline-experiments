/**
 * Experimenting with trying to wrap a node around something if it is not already in a node.
 */
def call(body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    // Are we in a node? Just pass it in for now but I want to figure it out
    Boolean inNode = config.inNode?:false

    // Do I have access to current build here?
    echo "currentBuild = ${currentBuild?.toString()}"
    echo "currentBuild class = ${currentBuild?.getClass()?.getName()}"

    // Some script that is going to exectute
    def toDo = {
        sh "echo 'node wrapper!"
    }

    // Do it
    if(inNode) {

        // Just execute it
        toDo()

    } else {

        // The caller doesn't have a node, wrape it in one
        node { toDo() }
    }
}