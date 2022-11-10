package ma.example.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "actor")
@Relation(collectionRelation = "actors")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorModel extends RepresentationModel<ActorModel>
{
    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;

    private List<AlbumModel> albums;
}
