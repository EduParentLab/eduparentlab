package model.service;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import model.dao.PostFileDAO;
import domain.PostFile;

public class FileService {
    private static final FileService instance = new FileService();
    private PostFileDAO dao = new PostFileDAO();
    private FileService() {}
    public static FileService getInstance() { return instance; }

    public void saveFiles(HttpServletRequest request, long postNum) {
        try {
            String uploadPath = request.getServletContext().getRealPath("/upload");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            Set<String> processedOriginalNames = new HashSet<>();

            for (Part part : request.getParts()) {
                if (!"files".equals(part.getName())) continue;

                if (part.getSubmittedFileName() == null || part.getSize() == 0) continue;

                String originalFileName = Paths.get(part.getSubmittedFileName())
                                               .getFileName().toString();

                if (!processedOriginalNames.add(originalFileName)) {
                    System.out.println("[SKIP] 중복 Part 감지: " + originalFileName);
                    continue;
                }

                String storedFileName = UUID.randomUUID().toString() + "_" + originalFileName;

                String savePath = uploadPath + File.separator + storedFileName;
                part.write(savePath);

                String webAccessiblePath = "/upload/" + storedFileName;

                PostFile file = new PostFile(0, storedFileName, originalFileName, webAccessiblePath);
                dao.insert(file, postNum);
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
