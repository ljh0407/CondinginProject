package codingin.domain.dto;
import codingin.domain.entity.BoardEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class BoardDto {
    private int bno;
    private String btitle; //제목
    private String  bcontent; //3. 내용

    private String  bwrite; //4-1.  작성자

    private String  mno; //4-2.  작성자

    private int bview; // 8. 조회수

    private int bgood; //9. 추천수

    private int bbad; //10. 비추천


    //12.6 최예은 추가 글쓴이 일치여부 (수정,삭제때문에 추가)
    private boolean btnaction; //로그인한 사람과 글쓴이가 일치하는지 확인하기 위해서 추가함

    //12.12 최예은 추가
    private String bdate; //12.12 최예은 작성시간

    private String mprofile; // 12.12 최예은 프로필사진 추가


    public BoardEntity toEntity(){
        return BoardEntity
                .builder()
                .bno( this.bno )
                .btitle( this.btitle )
                .bcontent( this.bcontent )
                .build();
    }
}
