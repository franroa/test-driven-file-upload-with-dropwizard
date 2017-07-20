package com.franroa.fileupload.resources;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.*;

import static javax.ws.rs.core.MediaType.*;

@Path("/import")
@Produces(APPLICATION_JSON)
public class MyResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyResource.class);

    @POST
    @Consumes(MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
        String uploadedFileLocation = "uploads/" + fileDetail.getFileName();
        LOGGER.info(uploadedFileLocation);
        writeToFile(uploadedInputStream, uploadedFileLocation);
        return Response.ok().build();
    }

    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws IOException {
        int read;
        final int BUFFER_LENGTH = 1024;
        final byte[] buffer = new byte[BUFFER_LENGTH];
        OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
        while ((read = uploadedInputStream.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        out.flush();
        out.close();
    }
}
