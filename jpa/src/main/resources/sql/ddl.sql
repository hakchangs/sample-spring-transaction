DROP TABLE IF EXISTS member;
CREATE TABLE member (
    id BIGINT,
    name VARCHAR2(100),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS point;
CREATE TABLE point (
    id BIGINT,
    amount NUMERIC,
    member_id BIGINT,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS payment;
CREATE TABLE payment (
    id BIGINT AUTO_INCREMENT,
    amount NUMERIC,
    payer_id BIGINT,
    paid_date TIMESTAMP,
    PRIMARY KEY (id)
);
