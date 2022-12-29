package codingin.domain.entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository extends JpaRepository<LetterEntity , Integer> {

    // dao ?  vs jpaquery :변수명

    //쪽지 리스트 출력 설정
    @Query( value = "select * from letter where lfrom = :mno ORDER BY lno desc" , nativeQuery = true)
    Page<LetterEntity> findByFromLetter( int mno , Pageable pageable );

    @Query( value = "select * from letter where lto = :mno ORDER BY lno desc" , nativeQuery = true)
    Page<LetterEntity> findByToLetter( int mno , Pageable pageable );
}
