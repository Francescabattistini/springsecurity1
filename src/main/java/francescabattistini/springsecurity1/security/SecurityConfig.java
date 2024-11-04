package francescabattistini.springsecurity1.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity// serve a stabiire che la classe non sarà una classe qualsiasi di configurazione ma una specific Spring Security
public class SecurityConfig{
@Bean
//  qui togliamo le cos3e che non ci servono

SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
     // mi consente di :
    //-disabilitare tutti i comportamenti che non voglio:
    httpSecurity.formLogin(http -> http.disable()); // evita il login visivo, uso react per quello


}

}
