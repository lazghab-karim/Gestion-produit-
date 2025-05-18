package domaine;


import java.util.Set;

import javax.persistence.*;
import lombok.*;


@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "agent")
public class Agent {


      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "idAgent")
      private Integer AgentId;


      private String nom;
      
      private String prenom;
      
      private String email;
      
      @OneToMany(mappedBy = "Produit", cascade = CascadeType.ALL) 
      private Set<Produit> Produits;
}