package codingin.domain.dto;
import codingin.domain.entity.CategoryEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class CategoryDto {

    private int cno;
    private String cname;   //12.16 고은시 누락분 추가

    public CategoryEntity toEntity(){
        return CategoryEntity
                .builder()
                .cno( this.cno )
                .cname(this.cname)  //12.16 고은시 누락분 추가
                .build();
    }
}
