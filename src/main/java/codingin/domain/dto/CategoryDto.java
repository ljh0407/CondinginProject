package codingin.domain.dto;
import codingin.domain.entity.CategoryEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class CategoryDto {  //카테고리

    private int cno;    //카테고리 번호

    private String cname;   //카테고리 이름

    public CategoryEntity toEntity(){
        return CategoryEntity
                .builder()
                .cno( this.cno )
                .cname(this.cname)
                .build();
    }
}
