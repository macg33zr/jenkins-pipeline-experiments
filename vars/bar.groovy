
//
// Something like:
//
// bar {
//    script = {
//        foo()
//        return 'Called foo'
//    }
// }
//
def call(body) {
    def config = [:]
    body.delegate = config
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body()
    
    // In the bar DSL element
    echo 'I am bar'
    
    // Expecting a script element as a closure
    assert config.script != null, 'A script element was not supplied'
    assert config.script instanceof Closure, 'The script element supplied must be a closure'
    
    // Call the script closure
    config.script.delegate = this
    config.script.resolveStrategy = Closure.DELEGATE_FIRST
    def result = config.script.call()
}
