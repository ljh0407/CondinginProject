package codingin.domain.dto;
import codingin.domain.entity.BoardEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class BoardDto {
    private int bno;
    private String btitle; //제목
    private String  bcontent; //3. 내용

    private String  bwrite; //3.  작 성 자
    private String  mno; //3.  작 성 자

    private String bfile; //; 5. 첨부파일
    private String bview; // 8. 조회수
    private String bgood; //9. 추천수
    private String bbad; //10. 비추천

    //12.6 최예은 추가 글쓴이 일치여부 (수정,삭제때문에 추가)
    private boolean btnaction; //로그인한 사람과 글쓴이가 일치하는지 확인하기 위해서 추가함

    public BoardEntity toEntity(){
        return BoardEntity
                .builder()
                .bno( this.bno )
                .btitle( this.btitle )
                .bcontent( this.bcontent )
                .bfile( this.bfile)
                .bview( this.bview)
                .bgood( this.bgood)
                .bbad( this.bbad)
                .build();
    }
}
