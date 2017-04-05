# Teaching-HEIGVD-RES-2017-Labo-SMTP

## Goal

The purpose of this lab is to send forged e-mails. A forged e-mail is a mail that appears to be sent by a certain person but in reality is issued by malicious users. This lab devlops a client application (TCP) in Java. This client application will use the Socket API to communicate with a SMTP server. The code contains a partial implementation of the SMTP protocol. Praticly, the application can send well-formed messages (according to the protocol) to a server. The content of the message and the people involved are avaliable in files. 
There is the possibiliy to use a mock server, to develop and test the client-server. 

## Settings

To change the addresses, you can go to the package data, file victims.utf8. It must respect the following format : one address per line. To change the e-mails, same package, you can add a new file named "mail"+[number]+".utf8". 

You can set the host name of the server and the port in the file SmtpServerInfo. By default, it's on localhost, port 25.

The program will first ask you to choose how many people you want to have in the group. Note that there is a number set by default.

The person who sends the e-mail is randomy chosen in the file.

The main class is MailBot, you can simply run it. At the end of each email send, you need to say "true" if you want to send another e-mail and "false" if you want to end the connexion.


If you want to make your own test or you don't have friends to spam, you can install a mock server. It will intercept you mail and you can check them in your browser.

You can find one here : https://github.com/tweakers-dev/MockMock

Once you installed it, just run it on the background and starts this application. 
