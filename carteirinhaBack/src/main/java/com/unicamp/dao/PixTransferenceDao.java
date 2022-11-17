package com.unicamp.dao;

import com.unicamp.entity.PixTransference;
import com.unicamp.entity.Student;

import java.util.Collection;

public interface PixTransferenceDao extends CrudRepository<PixTransference, Integer> {

    @Query("SELECT * from PixTransference where student_ra = :studentRA")
    List<PixTransference> getPixTransferencesByStudent(@Param("studentRA") Integer studentRA);

    @Query("SELECT * from PixTransference where student_ra = :studentRA and active = 1")
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
