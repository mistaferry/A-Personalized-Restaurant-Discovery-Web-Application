package ua.huryn.elasticsearch.repository.db;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.huryn.elasticsearch.entity.db.User;

import java.util.Optional;

public interface UserDbRepository  extends JpaRepository<User,Long> {
    @NotNull
    Optional<User> findById(@NotNull Long userId);
}
