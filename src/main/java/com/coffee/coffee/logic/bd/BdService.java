package com.coffee.coffee.logic.bd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BdService {

    @Autowired
    private BdDAO bdDAO;

    public void save(BdEntity bdEntity){
        bdDAO.save(bdEntity);
    }
}
