package codingin.domain.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity , Integer> {


    
    //12.15 최예은 추가

    @Query( value = "SELECT * " +
            "FROM " +
            "board " +
            "WHERE " +
            "IF( :key = '' , true , IF( :key = 'btitle' ,  btitle like %:keyword% , bcontent like %:keyword%  ) )" , nativeQuery = true )
    Page<BoardEntity> findbySearch( String key , String keyword , Pageable pageable);
                //카테고리번호는 뺐습니다.

}
