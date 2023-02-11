import Email.SendEmail
import Model.ModelEmails
import WebCrawler.FilterProducts
import WebCrawler.GenerateCSV
import WebCrawler.Product
import WebCrawler.WebCrawler

class Main {
    static void main(String[] args) {
        List<Product> products = new WebCrawler("Vinho Branco", "Rio Grande do Sul", "51", 2).getListProducts()

        List<Product> averageProducts = new FilterProducts(products).removeValuesGreaterAverage()

        new GenerateCSV().createCSCInFolderDownloads(averageProducts)

        List<String> emails = new ModelEmails().getAll()

        new SendEmail(averageProducts[0], averageProducts[-1]).sendMultiplesEmails(emails)
    }
}
