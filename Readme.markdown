Step-by-step to run the JMaglev demo
====================================

1.  Pull from github:

        git clone git://github.com/fabiokung/clustered-jruby.git

2.  Long time waiting, because terracotta-2.7.1 (vanilla) and jruby-complete (patched) are bundled.

3.  Enter the project directory:

        cd clustered-jruby

4.  Build it:

        mvn install
        
    (although `mvn package` is enough)

5.  Enter the jmaglev demo directory:

        cd jmaglev
    
6.  Start the terracotta server: 

        lib/terracotta-2.7.1/bin/start-tc-server.sh

7.  open another two terminals

8.  run the simplified jirb inside them:

        cd clustered-jruby/jmaglev
        ./bin/jmaglev jmaglev.rb

9.  Follow the [demo](http://fabiokung.com/2008/10/08/jruby-sharing-objects-across-multiple-runtimes-jmaglev "JMaglev Demo"). You will be able to share global variables among all jmaglevs:

        require 'hat'
        $hat
        require 'rabbit'
        $hat.put(Rabbit.new)

10. in the other terminal, try to see the magic hat contents:

        $hat
