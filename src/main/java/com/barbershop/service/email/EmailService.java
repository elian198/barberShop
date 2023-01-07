package com.barbershop.service.email;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class EmailService {

        private JavaMailSender mailSender;

        public EmailService(JavaMailSender mailSender) {
            this.mailSender = mailSender;
        }

        public void send(String from, String to, String subject, String text) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        }

        public void sendWithAttach( String to, String subject,
                                   String text
                               ) throws MessagingException {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(System.getenv("EMAIL"));
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html(text), true);
            //helper.addAttachment(attachName, inputStream);
            mailSender.send(message);
        }
        public String mensaje(String nombre){
            return  "<h1>Bienvenido "+ nombre + "!!</h1><p>Su perfil de usuario fuè " +
                    "creado correctamente.<hr>Saludos cordiales de parte de BarberShop</p>";
        }

        public String avisoPocoDiasTurno(String nombre , LocalDate dia, LocalTime time){
            return  "<h1>Hola que tal  "+ nombre + "!!</h1><br><p> queriamos avisarle que tiene un tueno pendiente el dia <span>" + dia + "</span><br>A las: <span class='time'>" + time + "hs</span><hr>"+
                    "Gracias por elegirnos.BarberShop</p>";

        }

          public String Turno(String nombre , LocalDate dia, LocalTime time, String estado){
             if(estado == "turno") {
                 return
                       " <h1>Hola que tal " + nombre + "!</h1><p> Queriamos avisarle que tiene un turno pendiente el dia <span> " + dia + "</span><br>A las: <span class='time'>"+ time + "hs</span><hr>"
                         + "Gracias por Elegirnos de parte de BarberShop </p>";

             } else {
                 return "<h1>Hola que tal  " + nombre + "!</h1><p> Queriamos avisarle que el turno del dia:  <span>" + dia + "</span><br>A las: <span class='time'>"+ time +"hs</span> fue cancelado.<hr>"
                         + " Gracias por Elegirnos de parte de BarberShop.</p>";
               }
            }
            public String avisoTurnoPendiente(String firstName ,String lastName, LocalDate dia,LocalTime time, Double total){
               return "<h1>Hola que tal " + firstName + " " + lastName + "</h1> <br>" +
                       "<p> Queriamos informale que tiene pendiente un turno el dia: <span>" + dia + ".</span> <br>"
                       + "A las: <span class='time'>" + time + "hs</span><br>"
                       + "El total del servicio es: <span>$" + total + "</span><hr>"
                       + "Muchas gracias por elegirnos, de parte de barberShop </p>";

            }

            public  String codigo(String nombre, String codigo){
                return "<h1>Hola " + nombre + "</h1><p>" +
                        "para crear su usuario ingrese las siguientes 6 letras:  <span> "+ codigo + "</span></p>";

            }
            private String html(String varibles){
               return  "<html>" +
                        "<style type=\"text/css\">\n" +
                        " p { color: #A9F5F2; }\n" +
                        " img { width: 100% ; }\n" +
                        " h1 { width: 100%; background : #424242; color : #5858FA; border-botton :1px; }\n" +
                        "div {backgraound : #A9F5F2; }" +
                        "body {text-align  : center ; }" +
                        "span {color  : #5F04B4 ; }" +
                        ".time  {color : white; }" +
                        "</style> <body>" +
                        "<h1> BARBER SHOP</h1>" +
                        "<img src='https://www.shutterstock.com/image-vector/vintage-barbershop-sign-260nw-1176772093.jpg' /> " +
                         varibles + " </body> </hmtl> ";
            }


     }

