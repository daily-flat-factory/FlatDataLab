package com.ddf.fdl.todaymenu.api.publicrecipe.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Table(name = "recipe_raw", schema = "today_menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeRaw {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rcp_seq", nullable = false, unique = true, length = 50)
    private String rcpSeq;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "raw_json", nullable = false, columnDefinition = "jsonb")
    private String rawJson;

    @CreationTimestamp
    @Column(name = "fetched_at", nullable = false, updatable = false)
    private OffsetDateTime fetchedAt;

    @Column(name = "source", nullable = false, length = 50)
    private String source  = "COOKRCP01";


    public RecipeRaw(String rcpSeq, String rawJson) {
        this.rcpSeq = rcpSeq;
        this.rawJson = rawJson;
        // this.source = "COOKRCP01";
    }
}
