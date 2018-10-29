/* this is the whole application!!!
install spring cli
macosx brew
brew update
$ brew tap pivotal/tap
$ brew install springboot
macosx MacPorts
$ sudo port install spring-boot-cli
linux
sdk install springboot

spring run app.groovy

Open localhost:8080
 */

@RestController
class ThisWillActuallyRun {

    @RequestMapping("/")
    String home() {
        return "Hello World!"
    }

}