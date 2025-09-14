package com.enit.service;

import java.util.List;

import jakarta.ejb.Local;

import com.enit.entities.Speciality;

@Local
public interface ILocalSpeciality {
    
    void createSpeciality(Speciality speciality);

    Speciality findSpecialityById(int idSpeciality);

    
    List<Speciality> findAllSpecialities();

    
    void updateSpeciality(Speciality speciality);

    
    void deleteSpeciality(int idSpeciality);
}
