# Spring Boot 2 OAuth 2.0 test application
Tested:
- parallel authorization, both username and OAuth through several providers
- custom OAuth 2.0 provider based on Windows Server 2016 AD FS
## Certificate for AD FS
Custom configuration on AD FS needed self signed certificate, which was generated with openssl
``` bash
openssl req -x509 -days 365 -newkey rsa:2048 -keyout cert.pem -out cert.pem
openssl pkcs12 -export -in cert.pem -inkey cert.pem -out cert.pfx
openssl x509 -pubkey -outform der -in cert.pem -out cert.cer
```
It also required field 'Subject Alternative Name' inside of certificate with IP that matches AD FS server IP. It can be done by adding necessary configuration to openssl.cfg:
```
subjectAltName = @alt_names

[ alt_names ]
IP = # your AD FS IP
```
As certificate is self signed, our app also should trust this certificate, that was done by adding it to local keystore, and providing VM option
```
-Djavax.net.ssl.trustStore=keystore.jks
```
Also this keystore was used for app ssl config
