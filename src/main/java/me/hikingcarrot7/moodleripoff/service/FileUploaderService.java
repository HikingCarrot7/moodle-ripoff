package me.hikingcarrot7.moodleripoff.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.EntityPart;
import me.hikingcarrot7.moodleripoff.model.CloudFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@ApplicationScoped
public class FileUploaderService {
  @Inject private Cloudinary cloudinary;

  public CloudFile uploadFile(EntityPart file) {
    Map<?, ?> options = ObjectUtils.asMap(
        "folder", "moodle-ripoff",
        "resource_type", "auto",
        "use_filename", true,
        "unique_filename", true
    );
    return uploadFile(file, options);
  }

  private CloudFile uploadFile(EntityPart file, Map<?, ?> options) {
    try {
      return tryUploadFile(file, options);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private CloudFile tryUploadFile(EntityPart file, Map<?, ?> options) throws IOException {
    String fileName = file.getFileName().get();
    // Create a temporary file to upload to cloudinary
    File tempFile = File.createTempFile(getFileNameWithoutExtension(fileName), getFileExtension(fileName));
    try (FileOutputStream stream = new FileOutputStream(tempFile)) {
      stream.write(file.getContent().readAllBytes());
    }
    var result = cloudinary.uploader().upload(tempFile, options);
    String publicId = (String) result.get("public_id");
    String url = (String) result.get("secure_url");
    tempFile.delete();
    return new CloudFile(publicId, url, fileName);
  }

  public void deleteFile(CloudFile file) {
    try {
      cloudinary.uploader().destroy(file.getPublicId(), ObjectUtils.emptyMap());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String getFileNameWithoutExtension(String fileName) {
    int lastIndexOf = fileName.lastIndexOf(".");
    if (lastIndexOf == -1) {
      return fileName;
    }
    return fileName.substring(0, lastIndexOf);
  }

  private String getFileExtension(String fileName) {
    int lastIndexOf = fileName.lastIndexOf(".");
    if (lastIndexOf == -1) {
      return "";
    }
    return fileName.substring(lastIndexOf);
  }

}
