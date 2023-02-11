package Email

import WebCrawler.Product

import javax.activation.DataHandler
import javax.activation.DataSource
import javax.activation.FileDataSource
import javax.mail.BodyPart
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Multipart
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

class SendEmail {

    Product lowestValue, highestValue

    SendEmail(Product lowestValue, Product highestValue) {
        this.lowestValue = lowestValue
        this.highestValue = highestValue
    }

    void sendOneEmail(String email) {
        String login = "projetoacelera@outlook.com"
        String password = "WebCrawler!"
        String host = "smtp-mail.outlook.com"

        Properties properties = System.getProperties()

        properties.put("mail.smtp.host", host)
        properties.put("mail.smtp.port", "587")
        properties.put("mail.smtp.auth", "true")
        properties.put("mail.smtp.starttls.enable","true")

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password)
            }
        })

        session.setDebug(true)

        try {
            MimeMessage message = new MimeMessage(session)
            message.setFrom(new InternetAddress(login))
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email))

            message.setSubject("Desafio Web Crawler OLX!")

            BodyPart messageBodyPart1 = new MimeBodyPart()
            messageBodyPart1.setText("" +
                    "Segue em anexo o relatório do produto. \n \n" +
                    "O produto de menor valor é ${lowestValue.title} - ${lowestValue.price} - ${lowestValue.local} - ${lowestValue.url} \n" +
                    "O produto de maior valor é ${highestValue.title} - ${highestValue.price} - ${highestValue.local} - ${highestValue.url}")

            MimeBodyPart attachment1 = new MimeBodyPart()
            String filename1 = "./Reports/products.csv"
            DataSource source1 = new FileDataSource(filename1)
            attachment1.setDataHandler(new DataHandler(source1))
            attachment1.setFileName(filename1)

            Multipart multipart = new MimeMultipart()
            multipart.addBodyPart(messageBodyPart1)
            multipart.addBodyPart(attachment1)

            message.setContent(multipart )

            Transport.send(message)
        } catch (MessagingException mex) {
            mex.printStackTrace()
        }
    }

    void sendMultiplesEmails(List<String> emails) {
        emails.forEach { sendOneEmail(it)}
    }
}