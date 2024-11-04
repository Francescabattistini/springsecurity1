package francescabattistini.springsecurity1.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity// serve a stabiire che la classe non sarà una classe qualsiasi di configurazione ma una specific Spring Security
public class SecurityConfig{
@Bean
//  qui togliamo le cos3e che non ci servono

 public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
     // mi consente di :
    //-disabilitare tutti i comportamenti che non voglio:
    httpSecurity.formLogin(http -> http.disable()); // evita il login visivo, uso react per quello
    httpSecurity.csrf(http->http.disable());//non voglio la protezione dallìattacco informatico perchè mi complicherebbe anche il lato frontend
    httpSecurity.sessionManagement((http->http.sessionCreationPolicy(SessionCreationPolicy.STATELESS)));
    // non abbiamo disaple ma session ecc statless senza stato, senza sessione  le disabilitiamo perchè non vogliamo utilizzare le sessioni
    // ma vogliamo utilizzare una sessione basata sul token(sarebbero più dispendiose in termini di performance)
    //poi vogliamo togliere il comportamento "non autorizzato 401
    httpSecurity.authorizeHttpRequests(http->http.requestMatchers("/**").permitAll());
// requestM disabilita la protezione 401 su tutti gli endpoint . (/**) applica questo su tutti gli endpoint e gli dico permetti a tutti.
    return httpSecurity.build();

}

}
