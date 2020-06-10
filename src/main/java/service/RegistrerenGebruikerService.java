package service;

import util.TEMP.TEMPGebruikerWrapper;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class RegistrerenGebruikerService {
    @PersistenceContext
    EntityManager em;

    public boolean checkEmail(TEMPGebruikerWrapper gebruiker) {
        TypedQuery<TEMPGebruikerWrapper> query = em.createQuery("SELECT g FROM TEMPGebruikerWrapper g WHERE g.email = :email", TEMPGebruikerWrapper.class);
        query.setParameter("email", gebruiker.getEmail());
        List<TEMPGebruikerWrapper> resultList = query.getResultList();
        System.out.println(resultList.size());
        return true;
    }
}
