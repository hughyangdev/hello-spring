DROP TABLE IF EXISTS member CASCADE;
CREATE TABLE member
(
		id BIGINT generated BY DEFAULT AS IDENTITY, -- id가 null이면 자동으로 생성
		name VARCHAR(255),
		PRIMARY KEY (id)
);