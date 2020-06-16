package service;

import dao.BijlageDao;
import domain.Bijlage;
import resources.BijlageInput;
import util.BijlageMapper;
import util.Mapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.File;


@Stateless
public class BijlageService {

    @Inject
    private BijlageDao dao;

    @Inject
    private Mapper<BijlageInput, Bijlage> mapper;

    public BijlageService() {
    }

    public Bijlage verwerkNieuweBijlage(File dataIn, String bijlageNaam, String bijlageType, String artikelId) {
        BijlageInput bijlageInput = new BijlageInput(bijlageNaam, bijlageType, artikelId, dataIn);
        Bijlage bijlage = mapper.mapFromInputToEntity(bijlageInput);
        dao.persist(bijlage);
        return bijlage;
    }

}
