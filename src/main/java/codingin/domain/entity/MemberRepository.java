package codingin.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity , Integer> {
    //1. 이메일 이용한 엔티티 검색 메소드
    Optional<MemberEntity> findByMemail(String memail );
}
