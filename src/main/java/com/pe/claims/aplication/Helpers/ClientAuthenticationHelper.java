package com.pe.claims.aplication.Helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClientAuthenticationHelper {

   /* private final ClientCredentialRepository clientCredentialRepository;

    public ClientAuthenticationHelper(ClientCredentialRepository clientCredentialRepository) {
        this.clientCredentialRepository = clientCredentialRepository;
    }*/
   @Value("${api.key}")
   private String api_key;
   public boolean validateApiKey(String requestApiKey) {
        //this is a simplistic implementation. Prod
        //implementation will check for expired key and other business logic
        var optionalClientCred = "key_secret";//clientCredentialRepository.findByApiKey(requestApiKey);
        if(api_key.equals(requestApiKey)){
            return true;
        }
        return false;

        //return optionalClientCred.describeConstable().isPresent();
    }

}