package com.ats.rtem.domain.query;

public class UserQueryConstants {

    public static final String SEARCH_USER_BY_KEYWORD_AND_DATE_RANGE = """
        SELECT *
        FROM tbl_user tu
        WHERE
          (:keyword IS NULL OR (
            LOWER(tu.first_name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.middle_name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.last_name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.user_name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.phone_number) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.country_code) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.region) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.city) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.district) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.barangay) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.street_address) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.postal_code) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.sss_number) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.tin_number) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.pagibig_number) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
            LOWER(tu.philhealth_number) LIKE LOWER(CONCAT('%', :keyword, '%'))
          ))
          AND (:startDate IS NULL OR tu.date_created >= :startDate)
          AND (:endDate IS NULL OR tu.date_created <= :endDate)
        """;

    public static final String SEARCH_USER_BY_DATE_RANGE = """
        SELECT *
        FROM tbl_user tu
        WHERE
          (:startDate IS NULL OR tu.date_created >= :startDate)
          AND (:endDate IS NULL OR tu.date_created <= :endDate)
        """;

    public static final String COUNT_USER_BY_DATE_RANGE = """
        SELECT COUNT(*)
        FROM tbl_user tu
        WHERE
          (:startDate IS NULL OR tu.date_created >= :startDate)
          AND (:endDate IS NULL OR tu.date_created <= :endDate)
        """;
}
