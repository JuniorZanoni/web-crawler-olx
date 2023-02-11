package WebCrawler

import groovyx.net.http.HttpBuilder
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements


class WebCrawler {
    String nameProduct, state, ddd
    Integer numberOfPages

    WebCrawler(String nameProduct, String state, String ddd, Integer numberOfPages) {
        this.nameProduct = nameProduct
        this.state = state
        this.ddd = ddd
        this.numberOfPages = numberOfPages
    }

    List<Product> getListProducts() {
        List<Product> listProducts = []

        Document pageProduct = getPageProduct(this.nameProduct)
        String linkWithState = pageProduct.getElementsMatchingOwnText(/${this.state}/).select("a").attr("href")

        Document pageWithState = Jsoup.connect(linkWithState).get()
        String linkWithStateAndDDD = pageWithState.getElementsMatchingOwnText(/.+${this.ddd} - .+/).attr("href")

        Document pageProductWithStateAndDDD = getPage("${linkWithStateAndDDD}&sp=2")

        this.numberOfPages.times {
            Element listProducts2 = pageProductWithStateAndDDD.getElementById("ad-list")
            Elements products2 = listProducts2.getElementsByAttribute("data-lurker_list_position")

            products2.forEach { product ->
                String url = product.attr("href")
                String title = product.attr("title")
                String price = product.getElementsByAttributeValueContaining("aria-label", "Preço do item").text()
                String local = product.getElementsByAttributeValueContaining("aria-label", "Localização").text()

                Integer priceConverted = price.split(" ")[1].replace(".", "").toInteger()

                Product produto = new Product(url, title, local, priceConverted)
                listProducts.add(produto)
            }

            if(listProducts.size() >= 50) {
                pageProductWithStateAndDDD = getPage(pageProductWithStateAndDDD.getElementsByAttributeValueContaining("data-lurker-detail", "next_page").attr("href"))
            }
        }

        return listProducts
    }

    private Document getPage(String link) {
        return (Document) HttpBuilder.configure { request.uri = link }.get()
    }

    private Document getPageProduct(String product) {
        String productWithoutSpaces = product.replace(" ", "%20")
        return (Document) getPage("https://www.olx.com.br/brasil?q=${productWithoutSpaces}")
    }
}
