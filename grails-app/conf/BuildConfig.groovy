grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.7
grails.project.source.level = 1.7
grails.project.war.file = "target/${appName}-${appVersion}.war"
//grails.plugin.location.'excel-export' = "/home/jnb/workspace/excel-export"

grails.project.dependency.resolution = {
    legacyResolve true

    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
        excludes 'xercesImpl', 'xml-apis'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits false // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        //mavenRepo("http://maven.touk.pl/nexus/content/repositories/releases")
    }

    dependencies {
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
        compile "net.sf.ehcache:ehcache-core:2.4.6"
    }

    plugins {
        runtime ":excel-export:0.2.0"

        test (":spock:0.7") {
            excludes 'groovy-all', "spock-grails-support"
        }

        runtime ":jquery:1.7.2"
        runtime ":resources:1.1.6"

        build ":tomcat:7.0.42"

        compile ':cache:1.0.0'
    }
}
