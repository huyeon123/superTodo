package com.huyeon.apiserver.model.entity;

import com.huyeon.apiserver.model.entity.base.AuditEntity;
import com.huyeon.apiserver.model.listener.Auditable;
import com.huyeon.apiserver.model.listener.CommentHistoryListener;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(value = {AuditingEntityListener.class, CommentHistoryListener.class})
public class Comment extends AuditEntity implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "boardId")
    private Long boardId;

    @JoinColumn(name = "userEmail")
    private String userEmail;

    private String comment;
}
