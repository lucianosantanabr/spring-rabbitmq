package com.github.lucianosantanabr.domain.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "feature_flag")
public class FeatureFlag  implements Serializable {

  private static final long serialVersionUID = -2235102061496436052L;

  @Id
  @Column(name = "feature_flag_id", unique = true, nullable = false, length = 250)
  private String id;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at")
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "activated_at")
  private Date activatedAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "inactivated_at")
  private Date inactivatedAt;

  @Column(nullable = false, length = 15)
  private Boolean status;

}
