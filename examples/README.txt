This is a sample application that demonstrates the usage of the clustered-jruby TIM.
You can use this sample application to verify that the clustered-jruby TIM loads
and functions properly in a Terracotta-enabled application.

To use this sample application, first make sure you have the clustered-jruby TIM
installed into your local Maven repository by running 'mvn install' from the
clustered-jruby directory.  Once you have it installed, you can run this sample
application with 'mvn tc:run'.

This sample will start two JRuby runtimes and share a single global variable 
($shared) among them.
