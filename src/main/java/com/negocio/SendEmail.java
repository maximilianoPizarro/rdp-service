package com.negocio;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.modelo.Host;
import com.modelo.User;
import com.rdp.App;

public class SendEmail {
	
	
   public static void enviarEmail(String to, Host equipo, Host equipoModificado) {
      // Recipient's email ID needs to be mentioned.

      // Sender's email ID needs to be mentioned
      String from = "agentedeplaya@gmail.com";
      final String username = "agentedeplaya@gmail.com";//change accordingly
      final String password = "sugpadgcactysv";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "25");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
	   }
         });

      try {
	   // Create a default MimeMessage object.
       MimeMessage  message = new MimeMessage(session);
	
	   // Set From: header field of the header.
	   message.setFrom(new InternetAddress(from));
	
	   // Set To: header field of the header.
	   message.setRecipients(Message.RecipientType.CC,
               InternetAddress.parse(to));
	
	   // Set Subject: header field
	   message.setSubject("RDP - Informe de cambios");
	
	   MimeMultipart multipart = new MimeMultipart("related");
	   BodyPart messageBodyPart = new MimeBodyPart();
	   // Now set the actual message
	   messageBodyPart.setContent("<table style='font-family: Arial; text-align: left; width: 966px; height: 85px'border='1' cellspacing='2' cellpadding='2'><tbody><tr><td style='text-align: center'><img style='width: 80px; height: 90px' alt='Logo GCABA'title='Logo GCABA' src=\"cid:Logo-BA\"  class='CToWUd'></td><td style='background-color: rgb(255, 222, 0); text-align: center'><span style='font-size: 24px'> <b>RDP</b> - Informe de Cambios</span></td></tr><tr><td style='text-align: justify' colspan='2'><br><b>Estimados Señores/as</b> <br><br> Le informamos sobre los siguientes cambios en el equipo "+equipo.getRed_host()+" <br> Usted puede ingresar a la aplicación para ver el historial completo: <br><ul type='square'><li>Equipo sin modificar: <br><br>"+equipo.toStringMail()+"<b></b><br><br></li><li>Equipo modificado: <br><br>"+equipoModificado.toStringMail()+"<b></b></li></ul> <br>Saludos cordiales. <br> <br> <span style='font-size: 12px'><center><b><u>ATENCIÓN</u></b>: El presente es un mensaje generado automaticamente por el <br> <b>Sistema de Registro de Computadoras</b> del <b>Gobierno de la Ciudad Autonoma de Buenos Aires</b> y no debe ser respondido.<br></center></span></td></tr><tr><td style='text-align: center'><img style='width: 80px; height: 90px' alt='Logo GCABA'title='Logo GCABA' src='cid:Logo-BA' class='CToWUd'></td><td style='background-color: rgb(255, 222, 0); text-align: center'><span style='font-size: 12px'> El Sistema de Registro de Computadoras es implementado en el marco de la <b>Iniciativa de la DGCACTYSV</b> <br>emprendida por el <b>Gobierno de la Ciudad Autonoma de Buenos Aires</b>.</span></td></tr></tbody></table>", "text/html");
	   multipart.addBodyPart(messageBodyPart);
	   
	   messageBodyPart = new MimeBodyPart();
	   DataSource fds = new FileDataSource(App.class.getResource("/static/Logo.png").getPath().replaceFirst("/rdpservice-2.0.jar!", "").substring(6));  // File:\E:\workspace\RDP-service\target\app\static\Logo.png 
	   messageBodyPart.setDataHandler(new DataHandler(fds));
	   messageBodyPart.addHeader("Content-ID", "<Logo-BA>");
	   multipart.addBodyPart(messageBodyPart);
	   
	   
	   message.setContent(multipart);
	   // Send message
	   Transport.send(message);

	  // System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }
}