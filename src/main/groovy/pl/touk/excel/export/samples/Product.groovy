package pl.touk.excel.export.samples

class Product {
    String name
    String description
    Date validTill
    Long productNumber
    Price price
    Long creationTimestamp = new Date().time
    String type
}
