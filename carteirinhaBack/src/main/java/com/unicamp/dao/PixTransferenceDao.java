package com.unicamp.dao;

import com.unicamp.entity.PixTransference;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PixTransferenceDao extends CrudRepository<PixTransference, String> {

    @Query(value="SELECT u from PixTransference u where u.ra = :studentRA", nativeQuery = true)
    List<PixTransference> getPixTransferencesByStudent(@Param("studentRA") Integer studentRA);

    @Query("SELECT u from PixTransference u where u.ra = :studentRA and active = 1")
    List<PixTransference> getActivePixTransferencesByStudent(@Param("studentRA") Integer studentRA);

    @Modifying
    @Query("UPDATE PixTransference SET completed = 1, active = 0 WHERE bb_id = :bbID")
    @Transactional
    void setCompletedPixTransferencesByStudent(@Param("bbID") String bbID);

    @Modifying
    @Query("UPDATE PixTransference SET expired = 1, active = 0 WHERE bb_id = :bbID")
    @Transactional
    void setExpiredPixTransferencesByStudent(@Param("bbID") String bbID);

}
