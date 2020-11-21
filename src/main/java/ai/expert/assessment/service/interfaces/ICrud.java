package ai.expert.assessment.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICrud<T, ID> {
   public Page<T> getAllPageable(Pageable pageable);

   public List<T> getAll();
   
   public T create(T entity);

   public T update(T entity);

   public Optional<T> read(ID id);

   public void delete(ID id);
}
