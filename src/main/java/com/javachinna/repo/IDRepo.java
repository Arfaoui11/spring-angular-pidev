package com.javachinna.repo;

import com.javachinna.model.DislikesC;
import org.springframework.data.repository.CrudRepository;

public interface IDRepo extends CrudRepository<DislikesC,Integer> {
}
