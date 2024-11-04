package francescabattistini.springsecurity1.TOOLS;


import francescabattistini.springsecurity1.entities.Author;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component// così posso utilizzare i metodi dove vogliamo trasformandolo in un beans
public class JWT {
    @Value("${jwt.secret}")// il segreto sarà salvato in envpropertis e letto da application.properties, perchè dentro a signwith lo vuole in byte
    private String secret;
    // 1 dato un utente ci torni un token
    public String createToken(Author author){
    // usiamo la libreria che abbiamo aggiunto al pom Jwts( Jwts.builder() creare) (Jwts.parser() verificare)
        // builder ci permette di configurare tutto step by step , data creazione,scadenza e a chi appartiene
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))//data emissione così token che dura 0
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))//data di scadenza del token (in milesecondi
                .subject(String.valueOf(author.getId()))// propretario del token (NON METTERE DATI SENSIBILI QUI NON è CIFRATO)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))//firmare il token  algoritmo e il segreto HMAC +segreto
                .compact();//assemblerà tutto in una stringa.

    };
    //2 dato un token verificami se è ok
    private void verifyToken(String accesstoken){
        Jwts.parser();
    };

}
