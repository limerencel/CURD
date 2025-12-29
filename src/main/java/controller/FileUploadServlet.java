package controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=utf-8");
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> fileItems = upload.parseRequest(req);

            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    String originalFilename = fileItem.getName();
                    String suffix = originalFilename.substring(originalFilename.indexOf("."));

                    String uuidName = UUID.randomUUID().toString() + suffix;

                    fileItem.write(new File("D://aki//crap//" + uuidName));

                    String webPath = "/res/" + uuidName;

                    // return file path to frontend
                    resp.getWriter().write(webPath);

                    return;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
