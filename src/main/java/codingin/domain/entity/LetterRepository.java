package codingin.domain.entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository extends JpaRepository<LetterEntity , Integer> {

/*
    @Query( value = "SELECT * " +   //쪽지 보내는 사람 검색
            "FROM " +
            "letter " +
            "WHERE lno = :lno and " +
            "IF( :key = '' , true , IF( :key = 'lfrom' ,  lfrom like %:keyword% ) )" , nativeQuery = true )
    Page<LetterEntity> findbylfrom(int lno, String key, String keyword, Pageable pageable);
*/

/*
    @Query( value = "SELECT * " +   //쪽지 받은 사람 검색
            "FROM " +
            "letter " +
            "WHERE lno = :lno and " +
            "IF( :key = '' , true , IF( :key = 'lto' ,  lto like %:keyword% ) )" , nativeQuery = true )
    Page<LetterEntity> findbylto(int lno, String key , String keyword , Pageable pageable);
*/

}
