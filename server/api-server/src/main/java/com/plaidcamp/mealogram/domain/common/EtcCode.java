package com.plaidcamp.mealogram.domain.common;

import com.plaidcamp.mealogram.domain.BaseEntity;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class EtcCode extends BaseEntity implements Serializable {

    @Column
    private String namespace;

    @Column
    private String maincode;

    @Column
    private String subcode;

    @Column
    private String value1;

    @Column
    private String value2;

}
