package codingin.domain.entity;

import codingin.domain.BaseEntity;
import codingin.domain.dto.CategoryDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // 엔티티 정의
@Table(name = "category") // 테이블명 정의
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder @ToString
public class CategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;

    @Column(nullable = false)
    private String cname;

    @OneToMany( mappedBy = "categoryEntity")
    @Builder.Default
    private List<BoardEntity> boardEntityList  = new ArrayList<>();

    public CategoryDto toDto(){
        return CategoryDto
                .builder()
                .cno( this.cno )
                .cname(this.cname)
                .build();
    }
}
