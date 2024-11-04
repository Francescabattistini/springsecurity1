package francescabattistini.springsecurity1.controllers;

import francescabattistini.springsecurity1.payloads.AuthorLoginResponseDto;
import francescabattistini.springsecurity1.payloads.LoginDto;
import francescabattistini.springsecurity1.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Log")
public class LoginController {
    @Autowired
    private LoginService loginService;//1
    // nell login mettiamo username e passw quindi mettiamo REQBODDY e dto
    public AuthorLoginResponseDto login(@RequestBody LoginDto body){//  public String login(@RequestBody LoginDto body) cambio stringa con dto per fare il json
        //verificare le credenziali e poi generare il token se tuto va bene


        return new AuthorLoginResponseDto (this.loginService.ceckCredenzialiEGeneraToken(body));//2 return this.loginService.ceckCredenzialiEGeneraToken(body); cambio per jason
        //3 passo a postman e creo utente  per verificare
        //4 il segreto deve essere di 32 caratteri minimo
        // però non va bene una cosa non è un json ma una stringa quindi si fa un payload AuthorLoginResponseDto
        //5vado nelle exhandel per gestire i 401
    }
}
