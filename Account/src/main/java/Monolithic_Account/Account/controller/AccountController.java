package Monolithic_Account.Account.controller;

import Monolithic_Account.Account.bo.AccountResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api")
public class AccountController {
 private final RestTemplate restTemplate;
 private static  final  String STOCK_API="http://localhost:8080/api";

    public AccountController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getAll")
    public AccountResponse getAllStocks(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return restTemplate.getForObject(STOCK_API+"/allStocks",AccountResponse.class);
    }
    @PostMapping("/filtered")
    public AccountResponse filteredByPrice(@RequestParam Integer price){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.postForObject(STOCK_API+"/filtered?price="+price,null,AccountResponse.class);
    }


}
