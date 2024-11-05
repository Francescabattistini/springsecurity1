package francescabattistini.springsecurity1.security;

import francescabattistini.springsecurity1.TOOLS.JWT;
import francescabattistini.springsecurity1.exceptions.UnathorizLoginExeption;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component// non dimenticare il component sennò non si può utilizzare
public class JwtCheckerFilter extends OncePerRequestFilter {
    @Autowired
    private JWT jwt;



    @Override
    //dofilter interno è il metodo che verrà richiamato ad ogni richiesta a parte quele che stabiliremo che non ne abbiano bisogno)
    // questo filtro deve controllare che il token sia valido ,il token lo troveremo nell' autorization headers
    // i fitri hanno l'accesso a tutte le parti della richiesta e quindi anche agli headers
    //Request(richiesta) Response(risposta) filterchain( continua con la catena)
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // 1.controllare se nella richiesta è presente l'autorization header, e se è ben formato
        // "bearer ajuauaja" e non ha il formato giusto e se non c'è --> 401
        String loginHeader= request.getHeader("Authorizations");
        //"Authorization": "Bearer hahahssjssjdhnhhddhdhdhdhhdhdhnddhd"
        if(loginHeader== null|| loginHeader.startsWith("Bearer")) // se è uguale a null e non inizia con Bearer
            throw new UnathorizLoginExeption("inserire token nell'Authorization header");

    //2. estraiamo il token dall' header
        String accessToken = loginHeader.substring(7);// bearer 7 caratteri
        // prendiamo il metodo che avevamo scritto nel JWT per verificare il token

        //3. verifichiamo se è stato malipolato (verifichiamo la firma ) o se è scaduto ( verifichiamo expioration date)
         jwt.verifyToken(accessToken); // lo faccio dopo il metodo in JWT

        //4. se tutto è ok andiamo avanti( passiamo la richiesta al filtro o al controller)
         filterChain.doFilter(request,response);//tramite .doFilter(request,response) richiamo il prossimo membro dell catena o un filtro o un controller

        // 5. se qualcosa non va con il token 401

    }
    // Voglio disabilitare il filtro per tutte le richieste al controller Auth, quindi tutte le richieste che avranno come URL /auth/** non dovranno
    // avere il controllo del token , sennò tutte le richieste su auth mi richiederebbero il token

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/login/**"// se ha dentro "/login/ e poi qualsiasi cosa" allora mi tornerà TRue e il filtro non filtrerà
                ,request.getServletPath());
    }
    // fatto ciò andiamo a spostare il save dal controller e lo mettiamo nel Login controlle o auth
}
