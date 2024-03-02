package org.corefin.dao;

import org.corefin.dao.mappers.LoanMapper;
import org.corefin.dto.LoanDto;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;

// TODO: implement me
public class LoanDao implements BaseDao<LoanDto> {
    private Jdbi jdbi;
    public LoanDao(Jdbi jdbi) {
        this.jdbi = jdbi;
    }
    // TODO: Pass in Handle here
    @Override
    public LoanDto insert(LoanDto dto) {
        String insertQuery = """
                INSERT INTO "loan" (term, originated_amount, currency, target_interest_rate, effective_interest_rate, external_reference, start_date, end_date, status, timezone, region, state)
                VALUES (:term, :originated_amount, :currency, :target_interest_rate, :effective_interest_rate, :external_reference, :start_date, :end_date, :status, :timezone, :region, :state)
                """;
        jdbi.withHandle(
            handle -> {
                handle.createUpdate(insertQuery)
                        .bind("term", dto.term())
                        .bind("originated_amount", dto.originatedAmount())
                        .bind("currency", dto.currency())
                        .bind("target_interest_rate", dto.targetInterestRate())
                        .bind("effective_interest_rate", dto.effectiveInterestRate())
                        .bind("external_reference", dto.externalReference())
                        .bind("start_date", dto.startDate())
                        .bind("end_date", dto.endDate())
                        .bind("status", dto.status())
                        .bind("timezone", dto.timezone())
                        .bind("region", dto.region())
                        .bind("state", dto.state());
                return 0;
            }
        );
        return null;
    }

    @Override
    public LoanDto findById(String id) {
        return null;
    }
    @Override
    public void registerRowMapper() {
        this.jdbi.registerRowMapper(new LoanMapper());
    }
}
