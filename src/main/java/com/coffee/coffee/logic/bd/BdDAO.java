package com.coffee.coffee.logic.bd;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BdDAO extends JpaRepository<BdEntity, Integer> {
    public BdEntity save(BdEntity user);
}
