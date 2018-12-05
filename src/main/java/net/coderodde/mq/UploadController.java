package net.coderodde.mq;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author rodde
 */
@WebServlet(name = "UploadController", urlPatterns = {"/lataaKuvake"})
@MultipartConfig
public class UploadController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response)
            throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            response.getWriter().println("1");
            return;
        }
        
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(5_000_000);
        ServletFileUpload servletFileUpload = 
                new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setSizeMax(5_000_000);
        FileItem fileItem = null;
        
        try {
            fileItem = servletFileUpload.parseRequest(request).get(0);
        } catch (FileUploadException ex) {
            response.getWriter().println("2");
            return;
        }
        
        response.getWriter().println("Received " + fileItem.get().length + " bytes.");
    }
}
