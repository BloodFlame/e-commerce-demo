package com.youfan.goods.jpa;

import com.youfan.goods.entity.GoodInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ========================
 */
public interface GoodInfoRepository
    extends JpaRepository<GoodInfoEntity,Long>
{
}
