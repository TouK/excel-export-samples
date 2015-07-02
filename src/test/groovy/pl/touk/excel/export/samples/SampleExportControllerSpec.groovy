package pl.touk.excel.export.samples

import grails.test.mixin.TestFor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import pl.touk.excel.export.samples.samples.ProductFactory
import spock.lang.*
import pl.touk.excel.export.samples.samples.SampleExportController

@TestFor(SampleExportController)
class SampleExportControllerSpec extends Specification {
    ProductFactory productFactory
    @Autowired
    MessageSource messageSource

    def setup() {
        productFactory = new ProductFactory()
        controller.productFactory = productFactory
        messageSource = Mock(MessageSource)
        controller.messageSource = messageSource
    }

    @Unroll("should set download headers when calling #methodName")
    def "should set download headers when export to xlsx"() {
        when:
        controller.invokeMethod(methodName, null)
        then:
        controller.response.status == 200
        controller.response.getHeader('Content-Type') == 'application/vnd.ms-excel'
        controller.response.getHeader('Content-disposition').startsWith('attachment')
        where:
        methodName << ['simplestExport', 'exportWithBuildInGetters', 'exportWithCustomGetter',
                'exportWithDirectManipulation', 'exportWithMultipleSheets', 'exportWithChangedNameForFirstSheet',
                'exportObjectsWithToString']
    }


    def "test i18nExport"(){
        setup:
        controller.metaClass.message = { String code ->
            return code.tokenize('.')[0].toUpperCase()
        }

        when:
        controller.i18nExport()

        then:
        controller.response.status == 200
        controller.response.getHeader('Content-Type') == 'application/vnd.ms-excel'
        controller.response.getHeader('Content-disposition').startsWith('attachment')
    }
}
