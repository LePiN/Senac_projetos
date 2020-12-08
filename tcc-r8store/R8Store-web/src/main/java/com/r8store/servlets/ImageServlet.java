package com.r8store.servlets;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;


@WebServlet("/Image/*")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ImageServlet() {
        super();
    }

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String requestedImage = request.getPathInfo();
        String imagePath = System.getProperty("user.home") + "/uploads/";
        
        try {
        	if (requestedImage == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND); 
                return;
            }
                        
            String[] names = requestedImage.split("/"); 
            String imageName = names[names.length - 1];
            String dir = "";
            
            for(int i = 1; i < names.length - 1; i++) { 
            	dir = dir + names[i] + "/";
            }
            
            File folder = new File(imagePath + dir);        
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {        	
            	String[] fileName = file.getName().split(Pattern.quote("."));
                if (file.isFile() && fileName[0].equals(imageName)) {               	
                	String extension = "." + FilenameUtils.getExtension(file.getName());
                	requestedImage = requestedImage + extension;
                	requestedImage = requestedImage.substring(1, requestedImage.length());
                	break;
                }
            }
        } catch(Exception e) {
        	System.out.println("Parece que houve um problema ao buscar a imagem. A imagem default será utilizada.");
        }        
        
        File image = new File(imagePath, URLDecoder.decode(requestedImage, "UTF-8"));
        
        if (!image.exists()) {
        	image = new File(imagePath, URLDecoder.decode("default.jpg", "UTF-8"));
        	if (!image.exists()) {
        		response.sendError(HttpServletResponse.SC_NOT_FOUND); 
                return;
        	}            
        }

        String contentType = getServletContext().getMimeType(image.getName());

        if (contentType == null || !contentType.startsWith("image")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); 
            return;
        }

        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));

        Files.copy(image.toPath(), response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

