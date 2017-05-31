def call(body) {
    body.delegate = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body()
    
    echo 'I am bar'
}
