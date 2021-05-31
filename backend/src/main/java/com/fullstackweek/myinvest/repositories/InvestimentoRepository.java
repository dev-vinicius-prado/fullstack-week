package com.fullstackweek.myinvest.repositories;

import com.fullstackweek.myinvest.domain.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {

}
