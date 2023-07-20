package nou.hello.boot.spring5boot.mybatis;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GalleryMapper {
    int insertGallery(Gallery g);
    int insertGalAttach(GalAttach ga);
    List<Gallery> selectGallery(int stnum);
    Gallery selectOneGallery(String gno);
    int selectCountGallery();
    Gallery selectOneGalAttach(String gno);

}
