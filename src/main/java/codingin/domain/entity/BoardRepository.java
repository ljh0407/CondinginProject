package codingin.domain.entity;

import codingin.domain.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity , Integer> {
    //12.15 최예은 추가
    @Query( value = "SELECT * " +
            "FROM " +
            "board " +
            "WHERE cno = :cno and " +
            "IF( :key = '' , true , IF( :key = 'btitle' ,  btitle like %:keyword% , bcontent like %:keyword%  ) )" , nativeQuery = true )
    Page<BoardEntity> findbySearch( int cno, String key , String keyword , Pageable pageable);

    @Query( value = "select * from board where cno=:cno ORDER BY bno desc limit 4 ;" ,nativeQuery = true)
    List<BoardEntity> getdesclist(int cno);

}
