package dicode;

//import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

import parsers.URLparser;

import java.util.Properties;

public class JavaMailSamples
{

    public static void main(String[] args)
    {
        SendAuthentication.Send(args[0], args[1], args[2], args[3]);

    }

}

class SendAuthentication
{

    public static void Send(String action, String user, String pass, String to)
    {
    	//URLparser pars = new URLparser();
		String host = URLparser.giveHost();
        String from = URLparser.giveFrom();
        String port = URLparser.giveMailPort();


        Properties prop = new Properties();

        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", port);

        try{

            SMTPAuthentication auth = new SMTPAuthentication();
            Session session = Session.getInstance(prop , auth );
            Message msg = (action.equals("newpass")) ? getMessageNewPass(session, from, to, user, pass):
            	getMessageActivation(session, from, to, user, pass);
            //System.out.println ("Sending ..." );

            Transport.send(msg);

            //System.out.println ("Message sent!");

        }

        catch (Exception e)
        {
            ExceptionManager.ManageException(e);

        }
    }

    private static MimeMessage getMessageNewPass(Session session, String from, String to, String user, String pass)
    {

        try{
    		String context = URLparser.giveContext();
            MimeMessage msg = new MimeMessage(session);
            msg.setContent("<p><a href=\""+context+"\"><img src=\"https://dicode-project.eu/data/images/logo_dicode_72.png\" width=\"50%\" height=\"50%\">" +
            		"</a></p><br>" + "<p>Your password for the Dicode Workbench has been re-established. <br>"+
            		"The new data to access your personal account is </p>" +
            		"<p>Username: " + user + "<br>" + "Password: " + pass + "</p>" +
            		"<p>We recomend you to change this password in the profile section once you log into " +
            		"<a href=\""+context+"\">Dicode Workbench</a>.</p>","text/html");
            /*msg.setContent("<p><a href=\"http://hodgkin.dia.fi.upm.es:8080/dicode\"><img src=\"https://dicode-project.eu/data/images/logo_dicode_72.png\" width=\"50%\" height=\"50%\">" +
            		"</a></p><br>" + "<p>Because of a security actualization we have established a new password for your account. <br>"+
            		"The new data to access your personal account is </p>" +
            		"<p>Username: " + user + "<br>" + "Password: " + pass + "</p>" +
            		"<p>We recomend you to change this password in the profile section to the older one once you log into " +
            		"<a href=\"http://hodgkin.dia.fi.upm.es:8080/dicode\">Dicode Workbench</a> to have full access to Cope_iT!.</p>","text/html");*/
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setFrom(new InternetAddress(from,"DICODE Workbench"));
            msg.setSubject("Reset your DICODE password");
            return msg;

        }

        catch (java.io.UnsupportedEncodingException ex)
        {

            ExceptionManager.ManageException(ex);
            return null;

        }

        catch (MessagingException ex)
        {

            ExceptionManager.ManageException(ex);
            return null;

        } 

    }

    private static MimeMessage getMessageActivation(Session session, String from, String to, String user, String code)
    {

        try{
        	//URLparser pars = new URLparser();
    		String context = URLparser.giveContext();
            MimeMessage msg = new MimeMessage(session);
            msg.setContent("<br><p><a href=\""+context+"\"><img src=\"https://dicode-project.eu/data/images/logo_dicode_72.png\" width=\"50%\" height=\"50%\">" +
            		"</a></p><br>" + "<p>A new account with the username <b>" + user + "</b> has been created in the Dicode Workbench. <br>"+
            		"Please, follow the link bellow or copy and paste it in your browser bar to complete the registration process and account activation: <br>" +
            		"<a href=\""+context+"/Activation?usr="+user+"&cd=" + code + "\">" + context+ "/Activation?usr="+user+"&cd=" + code + "</a></p>"+
            		"<p>If you do not have registered into Dicode Workbench please, ignore this email.</p>","text/html");
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setFrom(new InternetAddress(from,"DICODE Workbench"));
            msg.setSubject("DICODE account activation");
            return msg;

        }

        catch (java.io.UnsupportedEncodingException ex)
        {

            ExceptionManager.ManageException(ex);
            return null;

        }

        catch (MessagingException ex)
        {

            ExceptionManager.ManageException(ex);
            return null;

        } 

    }
}

class SMTPAuthentication extends javax.mail.Authenticator
{

    public PasswordAuthentication getPasswordAuthentication()
    {
    	//URLparser pars = new URLparser();
		String username = URLparser.giveUsername();
		String password = URLparser.givePass();

        return new PasswordAuthentication(username, password);

    }

}

class ExceptionManager
{

    public static void ManageException (Exception e)
    {

        System.out.println ("Se ha producido una exception");

        System.out.println (e.getMessage());

        //e.printStackTrace(System.out);

    }

}