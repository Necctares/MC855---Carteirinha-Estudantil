import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@EnableAutoConfiguration
public class QRCodeController {

    // Show QR Code on browser
    @RequestMapping(value="/QRCode", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void getQRCode(HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        ByteArrayInputStream in = QRCodeService.generateQRCode("Hello world!");
        IOUtils.copy(in, response.getOutputStream());
    }

    public static void main(String[] args) {
        SpringApplication.run(QRCodeController.class, args);
    }

}