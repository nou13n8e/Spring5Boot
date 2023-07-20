package nou.hello.boot.spring5boot.service;

import java.util.List;

public interface GalleryService {
    List<Gallery> readGallery(Integer cpg);
    int countGallery();
}
