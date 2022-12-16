package codingin.domain.dto;
import codingin.domain.entity.CategoryEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class CategoryDto {
    private int cno;
    private String cname; // 최예은 12.16 추가
    public CategoryEntity toEntity(){
        return CategoryEntity
                .builder()
                .cno( this.cno )
                .cname(this.cname) // 최예은 12.16 추가
                .build();
    }
}
