package com.plaidcamp.mealogram.domain.history;

import com.plaidcamp.mealogram.domain.BaseEntity;
import com.plaidcamp.mealogram.domain.user.UserAccount;
import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class WeightHistory extends BaseHistoryEntity {


}
