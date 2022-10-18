import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QRCodeService {

    public static ByteArrayInputStream generateQRCode(String url) {
        try {
            String contents = url;
            int width = 100;
            int height = 100;

            // generate QR code
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, width, height);

            // Convert to ByteArrayInputStream and return
            ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "JPG", imageStream);
            ByteArrayInputStream res = new ByteArrayInputStream(imageStream.toByteArray());
            return res;
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}