package codingin.domain.dto;
import codingin.domain.entity.BoardEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class BoardDto {
    private int bno;
    private String btitle; //제목
    private String  bcontent; //3. 내용
    private String bwriter; // 4. 작성자
    private String bfile; //; 5. 첨부파일
    private String bview; // 8. 조회수
    private String bgood; //9. 추천수
    private String bbad; //10. 비추천

    public BoardEntity toEntity(){
        return BoardEntity
                .builder()
                .bno( this.bno )
                .btitle( this.btitle )
                .bcontent( this.bcontent )
                .bwriter( this.bwriter)
                .bfile( this.bfile)
                .bview( this.bview)
                .bgood( this.bgood)
                .bbad( this.bbad)
                .build();
    }
}
