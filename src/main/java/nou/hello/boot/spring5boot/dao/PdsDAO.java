package nou.hello.boot.spring5boot.dao;

import nou.hello.boot.spring5boot.model.Pds;
import nou.hello.boot.spring5boot.model.PdsAttach;

import java.util.List;

public interface PdsDAO {
    int insertPds(Pds p);

    int insertPdsAttach(PdsAttach pa);

    List<Pds> selectPds(int stnum);

    int countAllPds();

    Pds selectOnePds(String pno);
}
