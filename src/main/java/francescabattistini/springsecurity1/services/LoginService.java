package francescabattistini.springsecurity1.services;

import francescabattistini.springsecurity1.TOOLS.JWT;
import francescabattistini.springsecurity1.entities.Author;
import francescabattistini.springsecurity1.exceptions.UnathorizLoginExeption;
import francescabattistini.springsecurity1.payloads.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {// service dedicato per vedere se le credenziali vanno bene e ci prenderà un token
    @Autowired
    private AuthorsService authorsService;
    @Autowired
    private JWT jwt;

    public String ceckCredenzialiEGeneraToken(LoginDto body){
        //1 controllo credenziali
        //1.1 controllo nel db se esiste un utente con l'email fornita
        Author found = this.authorsService.findByEmail(body.email());// ho creato il metodo nel Authorservice
        // 1.2 verifico che la la password di quell'utente corrisponda a quella fornita.
        if(found.getEmail().equals(body.email())){
            //2 se è iok --->genero il token PER GENERARE IL TOKEN FAREMO UNA CLASSE DEDICATA NEI TOOLS
            String accessToken = jwt.createToken(found);
            //3 ritorno il token
            return  accessToken;
        }else{
            //4 se le credenziali sono errate -->401 ( unauthorized)
            throw new UnathorizLoginExeption("credenziali errate");
        }
// così il service ci faq quello che volevano passo al controller
    }
}
