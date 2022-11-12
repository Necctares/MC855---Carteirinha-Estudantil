package com.unicamp.dao.mySql;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.unicamp.dao.PixTransferenceDao;
import com.unicamp.entity.PixTransference;
import com.unicamp.entity.Student;

@Repository
public class MySqlPixTransferenceDao implements PixTransferenceDao {
    private static Map<String, PixTransference> pix_transferences;

    @Override
    public Collection<PixTransference> getAllTransferences() {
        return this.pix_transferences.values();
    }

    @Override
    public Collection<PixTransference> getPixTransferencesByStudent(Student student) {
        Collection<PixTransference> ret_val = Collections.emptyList();
        for (PixTransference t : pix_transferences.values()){
            Student t_student = t.getStudent();
            if (t_student.getRa() == student.getRa())
                ret_val.add(t);
        }
        return ret_val;
    }

    @Override
    public PixTransference getPixTransferenceById(String id) {
        return this.pix_transferences.get(id);
    }

    @Override
    public void removePixTransferenceById(String id) {
        this.pix_transferences.remove(id);
    }

    @Override
    public void insertTransferenceToDb(PixTransference transference) {
        this.pix_transferences.put(transference.getId(), transference);
    }
}