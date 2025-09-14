package com.enit.service;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.enit.entities.Speciality;



@Stateless
public class LocalSpecialityImpl implements ILocalSpeciality {

    @PersistenceContext(unitName = "UP_COURS") 
    private EntityManager em;

    @Override
    public void createSpeciality(Speciality speciality) {
        em.persist(speciality);
    }

    @Override
    public Speciality findSpecialityById(int idSpeciality) {
        return em.find(Speciality.class, idSpeciality);
    }

    @Override
    public List<Speciality> findAllSpecialities() {
        String query = "SELECT s FROM Speciality s";
        return em.createQuery(query, Speciality.class).getResultList();
    }

    @Override
    public void updateSpeciality(Speciality speciality) {
        em.merge(speciality);
    }

    @Override
    public void deleteSpeciality(int idSpeciality) {
        Speciality speciality = findSpecialityById(idSpeciality);
        if (speciality != null) {
            em.remove(speciality);
        }
    }
}