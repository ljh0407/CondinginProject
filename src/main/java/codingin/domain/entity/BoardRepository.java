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


    // 12.22 db에서 최신글 4개 출력하는 query문
   @Query( value = "select * from board where cno=:cno ORDER BY bno desc limit 4 ;" ,nativeQuery = true)
    List<BoardEntity> getdesclist(int cno);

}
//최신글 4개 출력이니 bno로 판단을 해야하는지 아니면 cdate로 판단을 해야하는지?

//1.bno로 판단을 할 때
//select * from board where cno=1 ORDER BY bno desc limit 4 ;
//2 cdate로 판단
//select * from board where cno=1 ORDER BY cdate desc limit 4 ;
