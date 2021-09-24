package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // jpa가 관리하는 entity로 매핑
public class Member {

    // @Id 로 PK를 매핑,
    // db에서 값을 넣으면 자동으로 생성해줌 -> identity
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 데이터를 구분하기 위해 시스템이 저장하는 식별값
    private String name;
    // 만약 db 컬럼명이 다르면 @Column(name = "db컬럼명") 으로 매핑한다.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
