package model.service;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import model.dao.PostFileDAO;
import domain.PostFile;
import util.Path;

public class FileService {
    private static final FileService instance = new FileService();
    private PostFileDAO dao = new PostFileDAO();
    private FileService() {}
    public static FileService getInstance() { return instance; }


    public void saveFiles(HttpServletRequest request, long postNum) {
        try {
            Collection<Part> parts = request.getParts();
            for (Part part : parts) {
                if ("files".equals(part.getName()) && part.getSize() > 0) {
                    String oname = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String sname = UUID.randomUUID().toString() + "_" + oname;
                    String path = Path.FILE_STORE + File.separator + sname;

                    part.write(path);

                    PostFile f = new PostFile(0, sname, oname, path);
                    dao.insert(f, postNum);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public java.util.List<PostFile> findFilesByPost(long postNum) {
        return dao.listByPost(postNum);
    }

    public void deleteFilesByPost(long postNum) {
        dao.deleteByPost(postNum);
    }
}
