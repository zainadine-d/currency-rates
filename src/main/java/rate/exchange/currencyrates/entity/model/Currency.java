package rate.exchange.currencyrates.entity.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "currencies")
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Setter
    @Getter
    @Column(name = "currency_code", length = 3)
    private String currencyCode;

    @Setter
    @Getter
    @Column(name = "currency_name")
    private String currencyName;

}