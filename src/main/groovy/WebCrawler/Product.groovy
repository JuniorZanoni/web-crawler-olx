package WebCrawler

class Product {

    String url, title, local
    Integer price

    Product(String url, String title, String local, Integer price) {
        this.url = url
        this.title = title
        this.local = local
        this.price = price
    }
}
