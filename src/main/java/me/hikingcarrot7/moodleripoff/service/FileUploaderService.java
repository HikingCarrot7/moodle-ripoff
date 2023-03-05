package me.hikingcarrot7.moodleripoff.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import me.hikingcarrot7.moodleripoff.model.CloudFile;
import me.hikingcarrot7.moodleripoff.model.WithCloudFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@ApplicationScoped
public class FileUploaderService {
  @Inject private Cloudinary cloudinary;

  public CloudFile uploadOrUpdateFile(WithCloudFile withCloudFile, byte[] newFile) {
    CloudFile updatedFile;
    if (withCloudFile.hasFile()) {
      CloudFile oldFile = withCloudFile.getFile();
      updatedFile = updateFile(oldFile.getPublicId(), newFile);
    } else {
      updatedFile = uploadFile(newFile);
    }
    return updatedFile;
  }

  public CloudFile updateFile(String publicId, byte[] file) {
    Map<?, ?> options = ObjectUtils.asMap(
        "public_id", publicId,
        "overwrite", true,
        "resource_type", "auto"
    );
    return uploadFile(file, options);
  }

  public CloudFile uploadFile(byte[] file) {
    Map<?, ?> options = ObjectUtils.asMap(
        "folder", "moodle-ripoff",
        "resource_type", "auto",
        "use_filename", true,
        "unique_filename", true
    );
    return uploadFile(file, options);
  }

  private CloudFile uploadFile(byte[] file, Map<?, ?> options) {
    try {
      return tryUploadFile(file, options);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private CloudFile tryUploadFile(byte[] file, Map<?, ?> options) throws IOException {
    File tempFile = File.createTempFile("tempFile", ".txt");
    try (FileOutputStream stream = new FileOutputStream(tempFile)) {
      stream.write(file);
    }
    var result = cloudinary.uploader().upload(tempFile, options);
    String publicId = (String) result.get("public_id");
    String url = (String) result.get("secure_url");
    tempFile.delete();
    return new CloudFile(publicId, url);
  }

  public void deleteFile(CloudFile file) {
    try {
      cloudinary.uploader().destroy(file.getPublicId(), ObjectUtils.emptyMap());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
