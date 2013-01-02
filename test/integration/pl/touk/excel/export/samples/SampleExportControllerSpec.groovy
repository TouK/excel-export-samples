
package pl.touk.excel.export.samples
import grails.plugin.spock.IntegrationSpec
import pl.touk.excel.export.samples.samples.SampleExportController
import spock.lang.Unroll

class SampleExportControllerSpec extends IntegrationSpec {
    SampleExportController controller = new SampleExportController()

    @Unroll("should set download headers when calling #methodName")
    def "should set download headers when export to xlsx"() {
        when:
            controller.invokeMethod(methodName, null)
        then:
            controller.response.getHeader('Content-Type') == 'application/vnd.ms-excel'
            controller.response.getHeader('Content-disposition').startsWith('attachment')
        where:
            methodName << ['simplestExport', 'i18nExport', 'exportWithBuildInGetters', 'exportWithCustomGetter', 'exportWithDirectManipulation']

    }

}
