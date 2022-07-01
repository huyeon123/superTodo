package com.huyeon.apiserver.repository.history;

import com.huyeon.apiserver.model.dto.history.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserHistoryRepo extends JpaRepository<UserHistory, Long> {
    Optional<List<UserHistory>> findAllByUserId(Long userId);
}
