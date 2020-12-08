package com.r8store.controller;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.r8store.model.dao.RatingDAO;
import com.r8store.model.entity.Rating;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;

import org.primefaces.event.CaptureEvent;

@Named
@SessionScoped
public class PhotoController extends Controller implements Serializable {

    private String resultText;

    public PhotoController() {
    }

    public void oncapture(CaptureEvent captureEvent) {
        try {
            if (captureEvent != null) {
                Result result = null;
                BufferedImage image = null;

                InputStream in = new ByteArrayInputStream(captureEvent.getData());

                image = ImageIO.read(in);

                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                
                result = new MultiFormatReader().decode(bitmap);
                if (result != null) {
                	RatingDAO rDAO = new RatingDAO();
                	Rating rating = rDAO.findByToken(result.getText());
                	
                	if (rating.getPerson() != null) {
            			this.addErrorMessage("Ops", "Este token já foi utilizado.");
            			return;
            		}
            		
            		this.redirect(this.getAbsolutePath() + "/user/rating/avaliarLoja.xhtml?token=" + rating.getToken());
                    setResultText(result.getText());
                }
            }
        } catch (NotFoundException ex) {
            // fall thru, it means there is no QR code in image
        } catch (ReaderException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getResultText() {
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }
}
