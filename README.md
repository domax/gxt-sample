GWTP Basic with GXT
===================

Sample project that includes the following (most important) libraries:
* __Client side__: 
    - [GWT] v2.8.2
    - [GWT-Platform] v1.6 
    - [GXT] v4.0.0
    - [RestyGWT] v2.2.3
    - [GWT-Jackson] v0.15.0
* __Server side__: 
    - [Jetty] v9.2.14
    - [Jersey] v2.26
* __Both sides__: 
    - [Lombok] v1.16.20
    - [SLF4J] v1.7.25

Build
-----

    mvn clean package
    
Run in Development mode
-----------------------

    mvn initialize gwt:run


[GWT]: http://www.gwtproject.org/ 
[GWT-Platform]: http://dev.arcbees.com/gwtp/
[GXT]: http://docs.sencha.com/gxt/4.x/index.html
[RestyGWT]: https://resty-gwt.github.io/documentation/restygwt-user-guide.html
[GWT-Jackson]: https://github.com/nmorel/gwt-jackson
[Jetty]: https://www.eclipse.org/jetty/
[Jersey]: https://jersey.github.io/
[Lombok]: http://jnb.ociweb.com/jnb/jnbJan2010.html
[SLF4J]: https://www.slf4j.org/manual.html

