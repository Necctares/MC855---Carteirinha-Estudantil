package com.unicamp.controller;

import com.unicamp.service.PixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class PixController {

    // Show QR Code on browser
    @PostMapping(value="/generate-pix")
    public void generatePix(HttpServletResponse response
            , @RequestParam String cpf
            , @RequestParam String nome
            , @RequestParam String valor
    ) throws IOException {
        String json = PixService.generatePix(cpf, nome, valor);
        response.getWriter().write(json);
    }

    // Show QR Code on browser
    @GetMapping(value="/consult-pix")
    public void consultPix(HttpServletResponse response, @RequestParam String txid) throws IOException {
        String json = PixService.consultPix(txid);
        response.getWriter().write(json);
    }

}
