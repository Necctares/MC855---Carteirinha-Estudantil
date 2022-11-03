package com.unicamp.dao;

import com.unicamp.entity.PixTransference;
import com.unicamp.entity.Student;

import java.util.Collection;

public interface PixTransferenceDao {

    Collection<PixTransference> getAllTransferences();

    Collection<PixTransference> getPixTransferencesByStudent(Student student);

    PixTransference getPixTransferenceById(String id);

    void removePixTransferenceById(String id);

    void insertTransferenceToDb(PixTransference transference);
}