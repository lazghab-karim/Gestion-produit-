package domaine;


import javax.persistence.*;
import lombok.*;


@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "Produit")
public class Produit { 


	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "reference")
	  private Integer ProduitId;
	  
	  private String nom;
	  
      @ManyToOne
      @JoinColumn(name = "idAgent") 
      private Agent Agent;

      
}
