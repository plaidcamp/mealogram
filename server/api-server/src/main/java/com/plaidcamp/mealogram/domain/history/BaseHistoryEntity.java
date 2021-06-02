package com.plaidcamp.mealogram.domain.history;


import com.plaidcamp.mealogram.domain.BaseEntity;
import com.plaidcamp.mealogram.domain.user.UserAccount;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BaseHistoryEntity extends BaseEntity implements Serializable {
    @Column
    private UserAccount userAccount;

    @Column
    private Long changeSeq;

    @Column
    private BigDecimal value;


}
