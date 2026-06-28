package com.ddf.fdl.todaymenu.api.publicrecipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ddf.fdl.todaymenu.api.publicrecipe.entity.RecipeRaw;

public interface RecipeRawRepository extends JpaRepository<RecipeRaw, Long> {
    boolean existsByRcpSeq(String rcpSeq);
}
